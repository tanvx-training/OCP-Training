# JDBC

## Introducing Relational Databases and SQL

- A relational database is a database that is organized into tables, which consist of rows and columns. 
- There are two main ways to access a relational database from Java:
    + Java Database Connectivity Language (JDBC): Accesses data as rows and columns. JDBC is the API covered in this chapter.
    + Java Persistence API (JPA): Accesses data through Java objects using a concept called object-relational mapping (ORM). The idea is that you don’t have to write as much code, and you get your data in Java objects. JPA is not on the exam, and therefore it is not covered in this chapter.
    + A relational database is accessed through Structured Query Language (SQL).

### Identifying the Structure of a Relational Database

### Writing Basic SQL Statements

There are four types of statements for working with the data in tables:
- INSERT: Add a new row to the table
- SELECT: Retrieve data from the table
- UPDATE: Change zero or more rows in the table 
- DELETE: Remove zero or more rows from the table

## Introducing the Interfaces of JDBC

- Need to know four key interfaces of JDBC.
- These concrete classes come from the JDBC driver.
- Each database has a different JAR file with these classes.

**Key JDBC interfaces**

- All database classes are in the package `java.sql`, so we will omit the imports going forward.
- Key JDBC interfaces:

| Object      | Description                                   |
|-------------|-----------------------------------------------|
| Driver      | Knows how to get a connection to the database |
| Connection  | Knows how to communicate with the database    |
| Statement   | Knows how to run the SQL                      |
| ResultSet   | Knows what was returned by a SELECT query     |

## Connecting to a Database

### Building a JDBC URL

- The JDBC URL format: *jdbc:postgres://localhost:5432/zoo*
- Protocol: *jdbc*
- Product/Vendor Name: *postgres*
- Database Specific Connection Details: *//localhost:5432/zoo*
- Colon Separators

### Getting a Database Connection

- There are two main ways to get a Connection: `DriverManager` or `DataSource`. 
- A `DataSource` is a factory, and it has more features than `DriverManager`

```java
public class JdbcHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/ocp";
        String username = "postgres";
        String password = "password";
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(url, username, password);
    }
}
```

- `Class.forName()` loads a class. This lets DriverManager use a Driver, even if the JAR
doesn’t have a META-INF/service/java.sql.Driver file

**JDBC 3.0 vs. 4.0 drivers JDBC**

|                 | JDBC <= 3.0 Driver              | JDBC >= 4.0 Driver     |
|-----------------|---------------------------------|------------------------|
| Contains        | java.sql.Driver (required)      | Yes                    |
| Java will use   | java.sql.Driver file if present | Yes                    |
| Required to use | Class.forName                   | No                     |
| Allowed to use  | Class.forName                   | Yes                    |

## Obtaining a Statement

- In order to run SQL, you need to tell a Statement about it.
- the simple signature:

```java
Statement stmt = conn.createStatement();
```

- There’s another one that you need to know for the exam:

```java
Statement stmt = conn.createStatement(
  ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
```

###  Choosing a ResultSet Type

**ResultSet type options**

| ResultSet Type                    | Can Go Backward | See Latest Data from Database Table    | Supported by Most Drivers |
|-----------------------------------|-----------------|----------------------------------------|---------------------------|
| ResultSet.TYPE_FORWARD_ONLY       | No              | No                                     | Yes                       |
| ResultSet.TYPE_SCROLL_INSENSITIVE | Yes             | No                                     | Yes                       |
| ResultSet.TYPE_SCROLL_SENSITIVE   | Yes             | Yes                                    | No                        |

### Choosing a ResultSet Concurrency Mode

**ResultSet concurrency mode options**

| ResultSet Type              | Can Read Data | Can Update Data | Supported by All Drivers |
|-----------------------------|---------------|-----------------|--------------------------|
| ResultSet.CONCUR_READ_ONLY  | Yes           | No              | Yes                      |
| ResultSet.CONCUR_UPDATABLE  | Yes           | Yes             | No                       |

## Executing a Statement

**SQL runnable by execute method**

| Method               | DELETE | INSERT | SELECT | UPDATE |
|----------------------|--------|--------|--------|--------|
| stmt.execute()       | Yes    | Yes    | Yes    | Yes    |
| stmt.executeQuery()  | No     | No     | Yes    | No     |
| stmt.executeUpdate() | Yes    | Yes    | No     | Yes    |

**Return types of executes**

| Method               | Return Type   | What Is Returned for SELECT       | What Is Returned for DELETE/INSERT/UPDATE |
|----------------------|---------------|-----------------------------------|-------------------------------------------|
| stmt.execute()       | boolean       | true                              | false                                     |
| stmt.executeQuery()  | ResultSet     | The rows and columns returned     | n/a                                       |
| stmt.executeUpdate() | int           | n/a                               | Number of rows added/changed/removed      |

## Getting Data from a ResultSet

### Reading a ResultSet

Remember the following:
- Always use an if statement or while loop when calling rs.next().
- Column indexes begin with 1.

### Getting Data for a Column

**ResultSet get methods**

| Method Name    | Return Type        | Example Database Type |   
|----------------|--------------------|-----------------------|
| getBoolean     | boolean            | BOOLEAN               |
| getDate        | java.sql.Date      | DATE                  |
| getDouble      | double             | DOUBLE                |
| getInt         | int                | INTEGER               |
| getLong        | long               | BIGINT                |
| getObject      | Object             | Any type              |
| getString      | String             | CHAR, VARCHAR         |
| getTime        | java.sql.Time      | TIME                  |
| getTimeStamp   | java.sql.Timestamp | TIMESTAMP             |

**JDBC date and time types**

| JDBC Type            | Java 8 Type             | Contains                   |
|----------------------|-------------------------|----------------------------|
| java.sql.Date        | java.time.LocalDate     | Date only                  |
| java.sql.Time        | java.time.LocalTime     | Time only                  |
| java.sql.Timestamp   | java.time.LocalDateTime | Both date and time         |

### Scrolling ResultSet

**Navigating a ResultSet**

| Method                       | Description                                                  | Requires Scrollable ResultSet |
|------------------------------|--------------------------------------------------------------|-------------------------------|
| boolean absolute(int rowNum) | Move cursor to the specified row number                      | Yes                           |
| void afterLast()             | Move cursor to a location immediately after the last row     | Yes                           |
| void beforeFirst()           | Move cursor to a location immediately before the first row   | Yes                           |
| boolean first()              | Move cursor to the first row                                 | Yes                           |
| boolean last()               | Move cursor to the last row                                  | Yes                           |
| boolean next()               | Move cursor one row forward                                  | No                            |
| boolean previous()           | Move cursor one row backward                                 | Yes                           |
| boolean relative(int rowNum) | Move cursor forward or backward the specified number of rows | Yes                           |

## Closing Database Resources

- Closing a JDBC resource should close any resources that it created. In particular, the following are
true:
  + Closing a Connection also closes the Statement and ResultSet.
  + Closing a Statement also closes the ResultSet.

- There’s another way to close a ResultSet. JDBC automatically closes a ResultSet when
you run another SQL statement from the same Statement. 

- It is very important to close resources in the right order.

## Dealing with Exceptions

Each of these methods gives you a different piece of information:
- The getMessage() method returns a human-readable message as to what went wrong.
- The getSQLState() method returns a code as to what went wrong.
- By comparison, getErrorCode() is a database-specific code. 

# Summary

1. Name the core four JDBC interfaces that you need to know for the exam and where they
are defined.

- The four key interfaces are Driver, Connection, Statement, and ResultSet.
- The interfaces are part of the core Java APIs. 
- The implementations are part of a database JAR file.

2. Identify correct and incorrect JDBC URLs.

- A JDBC URL starts with jdbc:, and it is followed by the vendor/product name. 
- Next comes another colon and then a database-specific connection string. 
- This database-specific string includes the location, such as localhost or an IP address with an 
optional port. 
- It also contains the name of the database.

3. Describe how to get a Connection using DriverManager.

- After including the driver JAR in the classpath, call DriverManager.getConnection(url) or DriverManager.
- getConnection(url, username, password) to get a driver-specific Connection implementation class.

4. Create a Statement using different options.

- When creating a Statement, you can use the defaults. 
- Alternatively, you can specify the ResultSet type followed by the ResultSet concurrency mode. 
- The options for ResultSet type are TYPE_FORWARD_ONLY, TYPE_SCROLL_INSENSITIVE, and TYPE_SCROLL_SENSITIVE.
- The options for ResultSet concurrency mode are CONCUR_READ_ONLY and CONCUR_UPDATABLE.

5. Choose which method on Statement to run given a SQL statement.

- For a SELECT SQL statement, use executeQuery() or execute().
- For other SQL statements, use executeUpdate() or execute().

6. Loop through a forward only ResultSet.

- Before trying to get data from a ResultSet, you call rs.next() inside an if statement or while loop.
- This ensures that the cursor is in a valid position. 
- To get data from a column, call a method like getString(1) or getString("a"). 
- Remember that column indexes begin with 1.

7. Navigate within a scrollable ResultSet.

- The rows in a ResultSet are numbered starting with 1.
- Calling absolute(4) moves the cursor to the fourth row. 
- Calling absolute(0) moves the cursor to a location immediately before the result.
- Calling absolute(-1) moves the cursor to the last row.

8. Identify when a resource should be closed.

- If you’re closing all three resources, the ResultSet must be closed first, followed by the Statement, 
and then followed by the Connection. 
- Closing an object later in this list automatically closes those earlier in the list.