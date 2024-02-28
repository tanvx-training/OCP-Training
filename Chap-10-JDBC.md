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

