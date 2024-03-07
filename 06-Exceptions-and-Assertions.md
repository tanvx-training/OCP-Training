# Exceptions and Assertions

## Reviewing Exceptions

### Categories of Exceptions

**Types of exceptions**

| Type              | How to recognize                                                           | Recommended for program to catch? | Is program required to catch or declare? |
|-------------------|----------------------------------------------------------------------------|-----------------------------------|------------------------------------------|
| Runtime exception | `RuntimeException` or its subclasses                                       | Yes                               | No                                       |
| Checked exception | `Exception` or its subclasses but not `RuntimeException` or its subclasses | Yes                               | Yes                                      |
| Error             | `Error` or its subclasses                                                  | No                                | No                                       |

### Exceptions on the OCP

**OCP checked exceptions**

| Exception                                                                                  | Used when                                                                                                                                              | Checked or unchecked?   | Where to find more details   |
|--------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|------------------------------|
| `java.text.ParseException`                                                                 | Converting a String to a number.                                                                                                                       | Checked                 | Chapter 5                    |
| `java.io.IOException`, `java.io.FileNotFoundException`, `java.io.NotSerializableException` | Dealing with IO and NIO.2 issues. IOException is the parent class. There are a number of subclasses. You can assume any java. io exception is checked. | Checked                 | Chapter 9                    |
| `java.sql.SQLException`                                                                    | Dealing with database issues. SQLException is the parent class. Again, you can assume any java.sql exception is checked.                               | Checked                 | Chapter 10                   |

**OCP runtime exceptions**

| Exception                                                                     | Used when                                                              | Checked or unchecked? | Where to find more details   |
|-------------------------------------------------------------------------------|------------------------------------------------------------------------|-----------------------|------------------------------|
| `java.lang.ArrayStoreException`                                               | Trying to store the wrong data type in an array.                       | Unchecked             | Chapter 3                    |
| `java.time.DateTimeException`                                                 | Receiving an invalid format string for a date.                         | Unchecked             | Chapter 3                    |
| `java.util.MissingResourceException`                                          | Trying to access a key or resource bundle that does not exist.         | Unchecked             | Chapter 5                    |
| `java.lang.IllegalStateException`, `java.lang.UnsupportedOperationException`  | Attempting to run an invalid operation in collections and concurrency. | Unchecked             | Chapters 3 and 7             |

### Try Statement

Two rules about catch clauses and provide code examples for each rule:

1. **Order of Catch Blocks**:
    - Java evaluates catch blocks in the order in which they appear. It's illegal to declare a subclass exception in a catch block that appears lower in the list than a superclass exception. This is because it would result in unreachable code.
    - Example:
   
```java
      try {
          // Code that may throw exceptions
      } catch (FileNotFoundException e) {
          // Handle FileNotFoundException
      } catch (IOException e) { // This catch block must appear after FileNotFoundException
          // Handle IOException
      }
```

2. **Declaration of Catch Blocks**:
    - Java only allows catch blocks to be declared for exception types that could potentially be thrown by the try clause body. This prevents the declaration of catch blocks for exception types that will never occur, avoiding unreachable code.
    - Example:
   
```java
      try {
          // Code that may throw exceptions
      } catch (IOException e) {
          // Handle IOException
      }
```

In this example, if the try block does not contain any code that throws `IOException`, declaring a catch block for `IOException` would result in unreachable code.

These rules ensure that exception handling is logically structured and prevents issues with unreachable code in your Java programs.

### Throw vs. Throws

`throw` and `throws` are related but serve different purposes in Java exception handling:

1. **throw**:
    - `throw` is used to explicitly throw an exception within a method or a block of code.
    - It is followed by an instance of an exception class or a subclass of `Throwable`.
    - When a `throw` statement is encountered, the Java runtime system starts looking for the nearest `catch` block that can handle the thrown exception.
    - Example:
      ```java
      public void validateAge(int age) {
          if (age < 0) {
              throw new IllegalArgumentException("Age cannot be negative");
          }
      }
      ```

2. **throws**:
    - `throws` is used in method declarations to indicate that the method may throw certain types of exceptions.
    - It specifies the types of exceptions that the method can throw, allowing the caller of the method to handle them appropriately.
    - Multiple exception types can be declared using a comma-separated list.
    - Example:
      ```java
      public void readFile(String filename) throws FileNotFoundException, IOException {
          // Code to read the file
      }
      ```

In summary, `throw` is used to raise an exception manually within a method, while `throws` is used in the method signature to indicate the types of exceptions that the method may throw.