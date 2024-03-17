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

Absolutely! JDBC (Java Database Connectivity) is a fundamental technology for Java developers working with databases. Here's a breakdown of the key SQL statements and JDBC interfaces:

### SQL Statements:
1. **SELECT:**
  - Used to retrieve data from one or more tables in a database.
  - Syntax: `SELECT column1, column2, ... FROM table_name WHERE condition;`
  - Example: `SELECT * FROM customers WHERE country='USA';`

2. **INSERT:**
  - Used to insert new rows of data into a table in a database.
  - Syntax: `INSERT INTO table_name (column1, column2, ...) VALUES (value1, value2, ...);`
  - Example: `INSERT INTO customers (name, email) VALUES ('John Doe', 'john@example.com');`

3. **UPDATE:**
  - Used to modify existing data in a table in a database.
  - Syntax: `UPDATE table_name SET column1=value1, column2=value2, ... WHERE condition;`
  - Example: `UPDATE customers SET email='newemail@example.com' WHERE id=1;`

4. **DELETE:**
  - Used to remove existing data from a table in a database.
  - Syntax: `DELETE FROM table_name WHERE condition;`
  - Example: `DELETE FROM customers WHERE id=1;`

### JDBC Interfaces:
1. **Driver:**
  - Manages the database connection and communication with the database.
  - Loaded dynamically using `Class.forName()` method.
  - Example: `Class.forName("com.mysql.jdbc.Driver");`

2. **Connection:**
  - Represents a connection to a specific database.
  - Used to establish communication with the database.
  - Example: `Connection connection = DriverManager.getConnection(url, username, password);`

3. **Statement:**
  - Used to execute SQL queries against the database.
  - Provides methods like `executeQuery()` for SELECT statements and `executeUpdate()` for INSERT, UPDATE, DELETE statements.
  - Example: `Statement statement = connection.createStatement();`

4. **ResultSet:**
  - Represents the result set of a SQL query.
  - Used to retrieve data returned by a SELECT statement.
  - Iterated over to access each row of the result set.
  - Example:
    ```java
    ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
    while (resultSet.next()) {
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        // Process the data...
    }
    ```

Understanding these key SQL statements and JDBC interfaces is essential for interacting with databases in Java applications. They provide the foundation for accessing and manipulating data stored in relational databases.

Absolutely, the JDBC URL is essential for establishing a connection to a database. Here's how it's typically structured:

```
jdbc:vendor://location/database
```

Let's break down each part:

1. **jdbc:** This is the protocol specifier, indicating that JDBC should be used for the connection.

2. **vendor:** This part specifies the name of the vendor or product of the database. For example, for MySQL, it might be `mysql`. For Oracle, it might be `oracle`.

3. **location:** This part specifies the location of the database. It can be either `localhost` (indicating that the database is on the same machine as the application) or an IP address. Optionally, it may include a port number if the database server is running on a non-default port.

4. **database:** This is the name of the database you want to connect to. It's the specific database within the database server that your application will interact with.

Putting it all together, here are a few examples:

- MySQL database running on localhost with default port:
  ```
  jdbc:mysql://localhost/mydatabase
  ```

- PostgreSQL database running on a remote server with IP address `192.168.1.100` and port `5432`:
  ```
  jdbc:postgresql://192.168.1.100:5432/mydatabase
  ```

- Oracle database with SID `orcl` running on localhost:
  ```
  jdbc:oracle:thin:@localhost:1521:orcl
  ```

The JDBC URL varies slightly depending on the specific database vendor and configuration, but the general structure remains the same.

Absolutely, the `DriverManager.getConnection()` method is crucial for establishing a connection to a database. Here's how it typically works:

1. **Using DriverManager:** You can obtain a connection to a database by calling the `getConnection()` method of the `DriverManager` class. This method typically takes a JDBC URL and optionally, a username and password for authentication.

2. **Driver Discovery:** Modern JDBC drivers often come with a file named `java.sql.Driver` in the `META-INF/services` directory within the JAR. This file contains the fully qualified name of the driver implementation class. When the JVM loads the driver class, it registers itself with `DriverManager`, allowing it to be discovered and used without explicitly loading the driver class.

3. **Older Drivers:** In the case of older JDBC drivers that don't use the service discovery mechanism, you need to manually load the driver class using `Class.forName()`. This method dynamically loads the driver class into the JVM, allowing it to register itself with `DriverManager`.

Here's how you might use `DriverManager.getConnection()`:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // JDBC URL for MySQL
        String url = "jdbc:mysql://localhost/mydatabase";

        // Username and password for authentication
        String username = "username";
        String password = "password";

        try {
            // Get connection using DriverManager
            Connection connection = DriverManager.getConnection(url, username, password);

            // Do something with the connection

            // Close the connection when done
            connection.close();
        } catch (SQLException e) {
            // Handle any errors
            e.printStackTrace();
        }
    }
}
```

Using `DriverManager.getConnection()` along with the appropriate JDBC URL allows your Java application to connect to a database and perform database operations.

Absolutely, ResultSet types are important when working with JDBC. Here's a breakdown of the three ResultSet types:

1. **TYPE_FORWARD_ONLY:**
  - This is the default ResultSet type if none is specified explicitly.
  - You can traverse the data in the ResultSet only in a forward direction, one row at a time.
  - You cannot move the cursor backward or jump to a specific row.
  - It's the most efficient type in terms of memory and performance.
  - It's suitable for situations where you only need to process the data sequentially and do not require random access to rows.

2. **TYPE_SCROLL_INSENSITIVE:**
  - With this ResultSet type, you can traverse the data in any order, both forward and backward.
  - It's insensitive to changes made in the database while traversing the ResultSet. That means you won't see any changes made by other users or operations.
  - It's useful when you need to scroll through the data in a non-sequential manner but don't need to see real-time changes from other transactions.

3. **TYPE_SCROLL_SENSITIVE:**
  - Similar to TYPE_SCROLL_INSENSITIVE, you can traverse the data in any order.
  - However, this type is sensitive to changes made in the database while traversing the ResultSet. That means you'll see real-time changes made by other users or operations.
  - It's useful when you need to see real-time changes to the data while traversing the ResultSet.

When you request a ResultSet type that is not supported by the JDBC driver, it will automatically downgrade to a supported type. For example, if you request TYPE_SCROLL_SENSITIVE but the driver does not support it, it may downgrade to TYPE_SCROLL_INSENSITIVE or even TYPE_FORWARD_ONLY.

Here's how you might specify the ResultSet type when creating a Statement:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/mydatabase";
        String username = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");

            // Process the ResultSet

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

In this example, `ResultSet.TYPE_SCROLL_INSENSITIVE` is specified as the ResultSet type when creating the Statement.

Absolutely, let's break down the two modes for ResultSet concurrency:

1. **CONCUR_READ_ONLY:**
  - This is the default concurrency mode if none is specified explicitly.
  - It indicates that the ResultSet is read-only. You can traverse through the data and read its contents, but you cannot modify the data in the underlying database through this ResultSet.
  - This mode is suitable when you only need to retrieve and display data, and you have no intention of updating or deleting rows.

2. **CONCUR_UPDATABLE:**
  - This mode allows both reading and updating of the ResultSet. You can navigate through the data and make changes to the ResultSet's rows, which will reflect in the underlying database when you commit the changes.
  - It's useful when you need to modify data retrieved from the database and want those changes to be reflected in the database itself.

When creating a Statement, you can specify the concurrency mode along with the ResultSet type. Here's how you might do it:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/mydatabase";
        String username = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");

            // Process the ResultSet

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

In this example, `ResultSet.CONCUR_UPDATABLE` is specified as the ResultSet concurrency mode when creating the Statement.

Absolutely, here's a breakdown of these methods:

1. **executeQuery():**
  - This method is used to execute a SQL SELECT statement.
  - It returns a ResultSet object containing the data retrieved by the SELECT query.
  - You typically use this method when you expect the SQL query to return a result set, such as when retrieving rows from a database table.

2. **executeUpdate():**
  - This method is used to execute SQL INSERT, UPDATE, DELETE, or DDL (Data Definition Language) statements.
  - It returns an integer value representing the number of rows affected by the SQL statement.
  - For INSERT, UPDATE, and DELETE statements, it returns the count of affected rows.
  - For DDL statements, such as CREATE TABLE or ALTER TABLE, it returns 0.
  - You typically use this method when you want to execute SQL statements that modify the database in some way.

3. **execute():**
  - This method is a more general-purpose method that can execute any SQL statement.
  - It returns a boolean value indicating whether the first result is a ResultSet (true) or an update count or there is no result (false).
  - You can use this method when you're not sure about the type of SQL statement you're executing, or if you want to handle both query and update statements in a single method call.

Here's an example demonstrating the usage of these methods:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/mydatabase";
        String username = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();

            // Example of executeQuery() for SELECT
            ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");
            // Process the ResultSet

            // Example of executeUpdate() for INSERT, UPDATE, DELETE
            int rowsAffected = statement.executeUpdate("UPDATE my_table SET column1 = 'newValue' WHERE id = 1");
            System.out.println("Rows affected: " + rowsAffected);

            // Example of execute() for general purpose
            boolean isResultSet = statement.execute("DROP TABLE my_table");
            if (!isResultSet) {
                int updateCount = statement.getUpdateCount();
                System.out.println("Update count: " + updateCount);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

In this example:
- `executeQuery()` is used for a SELECT query.
- `executeUpdate()` is used for an UPDATE statement.
- `execute()` is used for a DROP TABLE statement, which is a DDL statement.

Absolutely, here's a breakdown of these methods and how to use them:

1. **Moving through a ResultSet:**
  - To move the cursor to the next row in a ResultSet, you use the `next()` method.
  - This method returns `true` if there is a next row, and `false` if there are no more rows.
  - It's commonly used in a `while` loop to iterate through all the rows of the ResultSet.

```java
ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");
while (resultSet.next()) {
    // Process the current row
}
```

2. **Getting data from columns:**
  - Once the cursor is positioned on a row, you can retrieve data from the columns using various `get` methods.
  - For example, `getString(int columnIndex)` or `getString(String columnLabel)` is used to retrieve a String value from the specified column.
  - Column indexes start from 1, not 0.

```java
String value1 = resultSet.getString(1);  // Retrieves the value from the first column
String valueA = resultSet.getString("columnA");  // Retrieves the value from the column named "columnA"
```

3. **Date and Time methods:**
  - `getDate(int columnIndex)` and `getDate(String columnLabel)` methods are used to retrieve `java.sql.Date` values from columns.
  - `getTime(int columnIndex)` and `getTime(String columnLabel)` methods are used to retrieve `java.sql.Time` values from columns.
  - `getTimestamp(int columnIndex)` and `getTimestamp(String columnLabel)` methods are used to retrieve `java.sql.Timestamp` values from columns.
  - These methods allow you to work with date and time values stored in the database columns.

```java
java.sql.Date dateValue = resultSet.getDate(2);  // Retrieves a date value from the second column
java.sql.Time timeValue = resultSet.getTime("timeColumn");  // Retrieves a time value from the column named "timeColumn"
java.sql.Timestamp timestampValue = resultSet.getTimestamp("timestampColumn");  // Retrieves a timestamp value from the column named "timestampColumn"
```

4. **getObject() method:**
  - The `getObject(int columnIndex)` and `getObject(String columnLabel)` methods are used to retrieve values from columns of any type.
  - These methods return a generic `Object`, and you'll need to cast it to the appropriate type based on the column's data type.

```java
Object obj = resultSet.getObject(3);  // Retrieves the value from the third column as an Object
```

These methods provide flexibility in retrieving various types of data from ResultSet columns based on their data types.

Absolutely! Here's how you can use these methods:

1. **Moving to absolute or relative positions:**
  - You can move the cursor to an absolute position using the `absolute(int row)` method, where `row` is the desired row number.
  - To move the cursor to a relative position, you use the `relative(int rows)` method, where `rows` is the number of rows to move forward (positive value) or backward (negative value).

```java
resultSet.absolute(3); // Moves the cursor to the third row
resultSet.relative(2); // Moves the cursor two rows forward from the current position
```

2. **Moving to first and last rows:**
  - The `first()` method moves the cursor to the first row in the ResultSet.
  - The `last()` method moves the cursor to the last row in the ResultSet.

```java
resultSet.first(); // Moves the cursor to the first row
resultSet.last(); // Moves the cursor to the last row
```

3. **Moving to next and previous rows:**
  - The `next()` method moves the cursor to the next row in the ResultSet.
  - The `previous()` method moves the cursor to the previous row in the ResultSet.

```java
resultSet.next(); // Moves the cursor to the next row
resultSet.previous(); // Moves the cursor to the previous row
```

4. **Moving outside the ResultSet boundaries:**
  - The `beforeFirst()` method moves the cursor to a position before the first row.
  - The `afterLast()` method moves the cursor to a position after the last row.

```java
resultSet.beforeFirst(); // Moves the cursor to a position before the first row
resultSet.afterLast(); // Moves the cursor to a position after the last row
```

Each of these methods returns `true` if the cursor is pointing to a valid row, and `false` otherwise. These methods provide flexibility in navigating through a scrollable ResultSet in various ways.

Absolutely, proper resource management is crucial in JDBC to avoid resource leaks and ensure optimal performance. Here's a summary of how JDBC resources should be closed:

1. **Connection:** When you are finished using a `Connection` object, you should explicitly close it by calling its `close()` method. This action also closes any associated `Statement` and `ResultSet` objects.

```java
connection.close();
```

2. **Statement:** Similarly, when you are done with a `Statement` object, you should close it using its `close()` method. This action also closes any associated `ResultSet` objects.

```java
statement.close();
```

3. **ResultSet:** If you have obtained a `ResultSet` object from executing a query, you should close it once you have finished processing the results.

```java
resultSet.close();
```

It's important to note that closing a higher-level resource (such as a `Connection` or `Statement`) will automatically close any lower-level resources associated with it (such as `ResultSet` objects). Additionally, when you execute another SQL statement using the same `Statement` object, any previously open `ResultSet` associated with that `Statement` will be automatically closed.

Proper resource management not only prevents resource leaks but also ensures that resources are released promptly, allowing them to be reused efficiently by the underlying database system.

# Exam Essentials

**Name the core four JDBC interfaces that you need to know for the exam and where they are defined.**

The core four JDBC interfaces and their definitions are:

1. **Driver:** The `Driver` interface is defined in the `java.sql` package. It provides a standard interface for database vendors to implement JDBC drivers. Implementations of the `Driver` interface are typically found in database-specific JAR files.

2. **Connection:** The `Connection` interface is also defined in the `java.sql` package. It represents a connection to a database and provides methods for creating `Statement` objects and managing transactions.

3. **Statement:** The `Statement` interface is defined in the `java.sql` package as well. It represents a SQL statement that is sent to the database for execution. There are different subtypes of `Statement`, such as `PreparedStatement` and `CallableStatement`, which provide additional features.

4. **ResultSet:** The `ResultSet` interface is defined in the `java.sql` package. It represents the result set of a SQL query and provides methods for navigating through the rows and retrieving data from the query result.

These interfaces are part of the core Java APIs and are used for database connectivity and interaction. Implementations of these interfaces are provided by JDBC drivers, which are typically packaged in database-specific JAR files.

**Identify correct and incorrect JDBC URLs**

Correct JDBC URLs follow the format described, starting with `jdbc:` followed by the vendor/product name, another colon, and then a database-specific connection string containing the location and name of the database. Here are some examples of correct JDBC URLs:

1. MySQL:
  - `jdbc:mysql://localhost:3306/mydatabase`
  - `jdbc:mysql://127.0.0.1:3306/mydatabase`

2. PostgreSQL:
  - `jdbc:postgresql://localhost:5432/mydatabase`
  - `jdbc:postgresql://127.0.0.1:5432/mydatabase`

3. Oracle:
  - `jdbc:oracle:thin:@localhost:1521:mydatabase`

Incorrect JDBC URLs may deviate from this format or contain errors. Examples of incorrect JDBC URLs could include missing or incorrect syntax, incorrect port numbers, or invalid database names. For instance:

- `jdbc:mysql://localhost/mydatabase`: Missing port number for MySQL.
- `jdbc:oracle:@localhost:1521:mydatabase`: Incorrect syntax for Oracle JDBC URL.
- `jdbc:postgresql://localhost:mydatabase`: Missing port number and incorrect format for PostgreSQL.

**Describe how to get a Connection using DriverManager**

To get a connection using `DriverManager`, follow these steps:

1. **Include the Driver JAR in the Classpath**: Ensure that the JDBC driver JAR file for the database you're connecting to is included in the classpath of your Java application. This allows Java to locate the driver implementation classes.

2. **Call `DriverManager.getConnection(url)`**: Use this method overload if your database connection does not require a username and password. Pass the JDBC URL specific to your database as a parameter to this method. For example:
   ```java
   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase");
   ```

3. **Call `DriverManager.getConnection(url, username, password)`**: If your database connection requires authentication, use this method overload. Pass the JDBC URL along with the username and password as parameters. For example:
   ```java
   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
   ```

4. **Handle Exceptions**: Remember to handle any exceptions that may be thrown during the connection process. Common exceptions include `SQLException` and `ClassNotFoundException`.

Here's a basic example of getting a connection using `DriverManager`:
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver (not required for newer JDBC drivers)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Get a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Use the connection...
            
            // Close the connection when done
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
```

Ensure that you replace `"com.mysql.cj.jdbc.Driver"`, `"jdbc:mysql://localhost:3306/mydatabase"`, `"username"`, and `"password"` with the appropriate values for your database configuration.

**Create a Statement using different options**

To create a `Statement` with different options, you can use the `createStatement()` method of a `Connection` object. Here's how you can create a `Statement` with various options:

1. **Using Defaults**: If you don't specify any options, the `Statement` is created with default settings. By default, the `ResultSet` type is `TYPE_FORWARD_ONLY`, and the `ResultSet` concurrency mode is `CONCUR_READ_ONLY`.

2. **Specifying ResultSet Type and Concurrency Mode**: You can specify the `ResultSet` type and concurrency mode by passing the appropriate constants to the `createStatement()` method. The options for `ResultSet` type are `TYPE_FORWARD_ONLY`, `TYPE_SCROLL_INSENSITIVE`, and `TYPE_SCROLL_SENSITIVE`. The options for `ResultSet` concurrency mode are `CONCUR_READ_ONLY` and `CONCUR_UPDATABLE`.

Here's how you can create a `Statement` with different options:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            // Get a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Creating a Statement with default options
            Statement defaultStatement = connection.createStatement();
            
            // Creating a Statement with specified ResultSet type (TYPE_SCROLL_INSENSITIVE) and ResultSet concurrency mode (CONCUR_UPDATABLE)
            Statement customStatement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            
            // Use the statements...
            
            // Close the connection when done
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

In this example, `defaultStatement` is created using the default options, while `customStatement` is created with a specified `ResultSet` type of `TYPE_SCROLL_INSENSITIVE` and a `ResultSet` concurrency mode of `CONCUR_UPDATABLE`.

**Choose which method on Statement to run given a SQL statement**

When executing SQL statements with JDBC, you should choose the appropriate method on the `Statement` interface based on the type of SQL statement:

1. **For SELECT SQL Statement**: Use `executeQuery()`. This method is specifically designed for executing SELECT statements and returns a `ResultSet` object containing the result set generated by the query.

2. **For Other SQL Statements (INSERT, UPDATE, DELETE, etc.)**: Use `executeUpdate()`. This method is used to execute SQL statements that do not return a `ResultSet`, such as INSERT, UPDATE, DELETE, CREATE TABLE, etc. It returns an integer indicating the number of rows affected by the operation.

Here's how you can use these methods:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            // Get a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Create a Statement
            Statement statement = connection.createStatement();
            
            // Example SELECT SQL statement
            String selectQuery = "SELECT * FROM my_table";
            
            // Example INSERT SQL statement
            String insertQuery = "INSERT INTO my_table (column1, column2) VALUES ('value1', 'value2')";
            
            // Execute SELECT SQL statement
            ResultSet resultSet = statement.executeQuery(selectQuery);
            // Process the ResultSet
            
            // Execute INSERT SQL statement
            int rowsAffected = statement.executeUpdate(insertQuery);
            System.out.println("Rows affected: " + rowsAffected);
            
            // Close the connection when done
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

In this example, `executeQuery()` is used for executing a SELECT SQL statement (`selectQuery`), and `executeUpdate()` is used for executing an INSERT SQL statement (`insertQuery`).

**Loop through a forward only ResultSet**

To loop through a forward-only `ResultSet`, you typically use a while loop and call the `next()` method inside it to move the cursor to the next row. Here's how you can do it:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            // Get a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a Statement
            Statement statement = connection.createStatement();

            // Example SELECT SQL statement
            String selectQuery = "SELECT * FROM my_table";

            // Execute SELECT SQL statement
            ResultSet resultSet = statement.executeQuery(selectQuery);
            
            // Loop through the ResultSet
            while (resultSet.next()) {
                // Retrieve data from the ResultSet
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // Process the data (print it, etc.)
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Close the ResultSet, Statement, and connection when done
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

In this example:
- We create a `ResultSet` by executing a SELECT SQL statement.
- We then loop through the `ResultSet` using a while loop.
- Inside the loop, we call `resultSet.next()` to move the cursor to the next row.
- We retrieve data from each column using methods like `getInt()` and `getString()`.
- We process the retrieved data as needed.
- Finally, we close the ResultSet, Statement, and connection in a `finally` block or in the `try-with-resources` block to ensure proper cleanup even if an exception occurs.

**Navigate within a scrollable ResultSet**

To navigate within a scrollable `ResultSet`, you can use methods like `absolute(int row)` to move the cursor to a specific row. Here's how you can do it:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            // Get a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a Statement
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Example SELECT SQL statement
            String selectQuery = "SELECT * FROM my_table";

            // Execute SELECT SQL statement
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Move the cursor to the fourth row
            boolean success = resultSet.absolute(4);
            if (success) {
                // Retrieve data from the current row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // Process the data (print it, etc.)
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Move the cursor to a location immediately before the result
            resultSet.absolute(0);

            // Move the cursor to the last row
            resultSet.last();
            // Retrieve data from the last row
            int lastId = resultSet.getInt("id");
            String lastName = resultSet.getString("name");
            // Process the data (print it, etc.)
            System.out.println("Last ID: " + lastId + ", Last Name: " + lastName);

            // Close the ResultSet, Statement, and connection when done
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

In this example:
- We create a `ResultSet` with a scrollable type (`TYPE_SCROLL_INSENSITIVE`) and a read-only concurrency mode (`CONCUR_READ_ONLY`).
- We use the `absolute(int row)` method to move the cursor to specific rows.
- We retrieve data from the current row after moving the cursor.
- Finally, we close the ResultSet, Statement, and connection in a `finally` block or in the `try-with-resources` block to ensure proper cleanup even if an exception occurs.

**Identify when a resource should be closed**

It's important to close JDBC resources in the correct order to avoid resource leaks. Here's the order in which you should close JDBC resources:

1. **ResultSet**: Close the `ResultSet` first. This releases its associated database cursor and any resources it holds.
2. **Statement**: Close the `Statement` next. This releases any resources associated with executing the SQL statement.
3. **Connection**: Close the `Connection` last. This releases the connection to the database and any resources it holds.

Closing resources in this order ensures that dependent resources are properly released. Additionally, closing any resource automatically closes the resources that it depends on, as JDBC handles resource cleanup in a hierarchical manner.

# Practice

**Question 1**: You are asked to establish a connection to a MySql database in the “localhost”, called “School”. You are given the password as “abcd” and the username as the “admin”. 

Answer:

- "jdbc:mysql://localhost:3306/School?user=admin&password=abcd"
- "jdbc:mysql://:3306/School?user=admin&password=abcd"

Explanation:

**jdbc:mysql://[host][:port]/[database][?property1][=value1]...**

- **host**:  - The host name where MySQL server is running. Default is 127.0.0.1 - the IP address of localhost.
- **port**:  - The port number where MySQL is listening for connection. Default is 3306.
- **database**: - The name of an existing database on MySQL server. If not specified, the connection starts no current database.
- **property**:  - The name of a supported connection properties. "user" and "password" are 2 most important properties.
- **value**:  - The value for the specified connection property.

**Question 2**: We can create a JdbcRowSet object by using one of the following methods;

Answer:

- By using the JdbcRowSetImpl class’ constructor that takes a ResultSet object.
- By using the JdbcRowSetImpl class’ constructor that takes a Connection object.
- By using the JdbcRowSetImpl class’ default constructor.
- By using an instance of RowSetFactory, which is created from the class RowSetProvider.

**Question 3**: Which of the following are true about the isolation levels? 

Answer:

| Isolation Level              | Transactions  | Dirty Reads    | Non-Repeatable Reads | Phantom Reads   |
|------------------------------|---------------|----------------|----------------------|-----------------|
| TRANSACTION_NONE             | Not supported | Not applicable | Not applicable       | Not applicable  |
| TRANSACTION_READ_COMMITTED   | Supported     | Prevented      | Allowed              | Allowed         |
| TRANSACTION_READ_UNCOMMITTED | Supported     | Allowed        | Allowed              | Allowed         |
| TRANSACTION_REPEATABLE_READ  | Supported     | Prevented      | Prevented            | Allowed         |
| TRANSACTION_SERIALIZABLE     | Supported     | Prevented      | Prevented            | Prevented       |

