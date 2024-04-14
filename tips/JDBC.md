**Automatic JDBC Driver Loading:**

- **Previous Practice**: In the past, applications needed to explicitly load JDBC drivers using `Class.forName()`.

- **Enhancements in JDBC 4.0**:
    - The `DriverManager` methods `getConnection` and `getDrivers` now support the Java Standard Edition Service Provider mechanism.
    - JDBC 4.0 Drivers must include the file `META-INF/services/java.sql.Driver`.
    - This file contains the name of the JDBC driver's implementation of `java.sql.Driver`.
    - For example, to load the `my.sql.Driver` class, the `META-INF/services/java.sql.Driver` file would contain the entry:
      ```
      my.sql.Driver
      ```

- **Driver Loading Mechanism**:
    - When the method `getConnection` is called, the `DriverManager` will attempt to locate a suitable driver.
    - It searches among those loaded at initialization and those loaded explicitly using the same classloader as the current applet or application.

**ResultSetMetaData Overview:**

- **Purpose**: Provides information about the result set obtained after executing a query.
- **Retrieval**: Obtain this object by calling `getMetaData()` on a ResultSet.

**Important Methods**:

1. `getColumnCount()`:
  - Returns the number of columns in the result set.

2. `getColumnName(int col)`:
  - Returns the name of the column at the specified index.
  - Column index starts from 1.

3. `getColumnLabel(int col)`:
  - Returns the label of the column at the specified index.
  - Useful when the column name is not available.

4. `getColumnType(int col)`:
  - Returns the SQL type of the column at the specified index.
  - Provides information about the data type of the column.

**Note**: Column index in these methods starts from 1, not 0.