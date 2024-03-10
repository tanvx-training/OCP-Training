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

## Creating Custom Exceptions 

Creating custom exceptions in Java allows you to define your own exception types to handle specific error conditions in your applications. Here's how you can create custom exceptions in Java:

```java
// Define your custom exception class by extending the Exception class or any of its subclasses.
public class CustomException extends Exception {
    // You can include constructors to provide different ways of initializing your custom exception.
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}

// Example of how to use your custom exception.
public class Main {
    public static void main(String[] args) {
        try {
            // Example of throwing your custom exception.
            throw new CustomException("This is a custom exception.");
        } catch (CustomException e) {
            // Handle the custom exception.
            System.out.println("Caught custom exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

In the example above:

- We define a custom exception class `CustomException` that extends the `Exception` class. This class can have constructors to initialize the exception with a custom message and/or a cause.
- In the `Main` class, we demonstrate how to use the custom exception by throwing it within a `try-catch` block. When the custom exception is thrown, it can be caught and handled appropriately.

Remember to handle exceptions properly in your code to ensure robustness and reliability.

## Using Multi-catch

Multi-catch is a feature introduced in Java 7 that allows you to catch multiple types of exceptions in a single catch block. Here's an example of how to use multi-catch:

```java
public class Main {
    public static void main(String[] args) {
        try {
            // Code that may throw different types of exceptions.
            // For demonstration purposes, let's intentionally throw two types of exceptions.
            if (Math.random() < 0.5) {
                throw new NullPointerException("Null pointer exception occurred.");
            } else {
                throw new IllegalArgumentException("Illegal argument exception occurred.");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            // Catch block for handling both NullPointerException and IllegalArgumentException.
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
```

In the example above:

- Within the `try` block, we intentionally throw either a `NullPointerException` or an `IllegalArgumentException` based on a random condition.
- In the `catch` block, we use the `|` operator to catch both `NullPointerException` and `IllegalArgumentException` in a single catch block.
- Inside the catch block, we handle both types of exceptions. Since both exceptions are subclasses of `RuntimeException`, we can catch them together.

Using multi-catch can make code more concise and readable when handling multiple related exceptions with the same error handling logic. However, it's important to note that multi-catch is limited to exceptions that are not in an inheritance relationship (i.e., siblings in the exception hierarchy).

>Remember that the exceptions can be listed in any order within the catch clause. However, the variable name must appear
only once and at the end. 

## Using Try-With-Resources

The new try-with-resources statement automatically closes all resources opened in the
try clause. This feature is also known as automatic resource management, because Java
automatically takes care of the closing.

### Try-With-Resources Basics

The try-with-resources statement in Java is used to automatically close resources like streams, connections, etc., when the try block finishes execution, either normally or abnormally (due to an exception). It ensures that the resources are closed properly, even if an exception is thrown, and it simplifies the code by reducing the boilerplate code for resource management.

Here's the basic syntax of the try-with-resources statement:

```java
try (ResourceType1 resource1 = new ResourceType1(); 
     ResourceType2 resource2 = new ResourceType2();
     // Add more resources if needed
    ) {
    // Code that uses the resources
} catch (ExceptionType e) {
    // Exception handling code
}
```

In this syntax:

- The `try` block contains the code that uses the resources.
- Inside the parentheses after the `try` keyword, you declare and initialize the resources that need to be managed. Each resource declaration must be of a type that implements the `AutoCloseable` interface.
- After the `try` block, you can have one or more `catch` blocks to handle exceptions if they occur during the execution of the try block.

**Legal vs. illegal configurations with a traditional try statement**

|                        | 0 finally blocks | 1 finally block | 2 or more finally blocks |
|------------------------|------------------|-----------------|--------------------------|
| 0 catch blocks         | Not legal        | Legal           | Not legal                |
| 1 or more catch blocks | Legal            | Legal           | Not legal                |

**Legal vs. illegal configurations with a try-with-resources statement**

|                        | 0 finally blocks | 1 finally block | 2 or more finally blocks |
|------------------------|------------------|-----------------|--------------------------|
| 0 catch blocks         | Legal            | Legal           | Not legal                |
| 1 or more catch blocks | Legal            | Legal           | Not legal                |


### AutoCloseable

The `AutoCloseable` interface in Java is implemented by classes whose instances need to perform cleanup operations when they are no longer needed, such as closing files, database connections, or network connections. The interface contains a single method, `close()`, which is invoked when the object implementing `AutoCloseable` is no longer needed, typically in a try-with-resources statement.

Here's the declaration of the `AutoCloseable` interface:

```java
public interface AutoCloseable {
    void close() throws Exception;
}
```

As you can see, the `close()` method can throw an `Exception`. However, when using try-with-resources, any exceptions thrown by the `close()` method are suppressed if there are other exceptions thrown in the try block. These suppressed exceptions can be accessed via the `getSuppressed()` method of the caught exception.

Classes that implement the `AutoCloseable` interface should provide an implementation of the `close()` method to release any resources they hold. For example, a `BufferedReader` or `FileReader` would close the underlying stream in their `close()` methods.

Here's an example of using `AutoCloseable` in a try-with-resources statement:

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

In this example, the `BufferedReader` is automatically closed when the try block exits, regardless of whether an exception occurs or not. This is because `BufferedReader` implements `AutoCloseable`, so it can be used in a try-with-resources statement.

**AutoCloseable vs. Closeable**

`AutoCloseable` and `Closeable` are both interfaces in Java that are used to indicate that a class's instances need to perform cleanup operations when they are no longer needed, such as closing files, database connections, or network connections. However, there are some differences between them:

1. **AutoCloseable**:
   - Introduced in Java 7.
   - Contains a single method `close()` that doesn't declare any checked exceptions.
   - Designed primarily for use with try-with-resources statement.
   - Allows for automatic resource management with try-with-resources.
   - Any exceptions thrown by the `close()` method are suppressed if there are other exceptions thrown in the try block.

2. **Closeable**:
   - Introduced in Java 5.
   - Extends the `AutoCloseable` interface.
   - Contains a single method `close()` that declares `IOException`.
   - Typically used for classes that represent resources that can be closed.
   - Primarily used before Java 7 when try-with-resources was not available.
   - Closeable is the parent interface of AutoCloseable.

In general, if you are working with Java 7 or later, it's recommended to use `AutoCloseable` for new classes, especially when you intend to use try-with-resources. However, if you are working with older code or libraries that use `Closeable`, you may need to implement or work with classes that implement `Closeable` instead.

### Suppressed Exceptions

Suppressed exceptions in Java refer to exceptions that occur during the closing of resources in a try-with-resources block, which are then suppressed in favor of the primary exception thrown within the try block. This mechanism was introduced along with the `AutoCloseable` interface and the try-with-resources statement in Java 7.

When using try-with-resources, if an exception is thrown during the execution of the try block and another exception is thrown while closing the resources in the finally block (e.g., when calling the `close()` method on an `AutoCloseable` resource), the exception thrown during closing is suppressed. This means that it doesn't propagate up the call stack by default. Instead, it's added as a suppressed exception to the primary exception that was thrown within the try block.

Suppressed exceptions can be retrieved and processed by calling the `getSuppressed()` method on the primary exception object. This allows you to handle both the primary exception and any suppressed exceptions that occurred during resource cleanup.

Here's a simple example demonstrating suppressed exceptions:

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("nonexistent-file.txt"));
            try (br) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file:");
                e.printStackTrace();
                Throwable[] suppressed = e.getSuppressed();
                for (Throwable t : suppressed) {
                    System.err.println("Suppressed exception: " + t);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while opening the file:");
            e.printStackTrace();
        }
    }
}
```

In this example:

- We attempt to open and read from a nonexistent file within a try-with-resources block.
- Since the file doesn't exist, an `IOException` is thrown within the try block.
- When the try block exits, the `BufferedReader` is automatically closed. However, attempting to close a `BufferedReader` that was not successfully opened also results in an `IOException`.
- This second `IOException` is suppressed, and the primary exception thrown within the try block (file not found) is caught and handled.
- We retrieve and print any suppressed exceptions using the `getSuppressed()` method on the primary exception object.

### Putting It Together

Two new rules for the order in which code runs in a try-with-resources
statement:
- Resources are closed after the try clause ends and before any catch/finally clauses.
- Resources are closed in the reverse order from which they were created.

Here's the rewritten example:

```java
public class Auto implements AutoCloseable {
    int num;
    
    Auto(int num) {
        this.num = num;
    }
    
    public void close() {
        System.out.println("Close: " + num);
    }
    
    public static void main(String[] args) {
        try (Auto a1 = new Auto(1); Auto a2 = new Auto(2)) {
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("Caught exception: " + e);
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

In this rewritten example:

- The `Auto` class implements the `AutoCloseable` interface, providing a `close()` method to handle resource cleanup.
- In the `main` method, we create two instances of `Auto` (`a1` and `a2`) within a try-with-resources block.
- We intentionally throw a `RuntimeException` inside the try block.
- Since a `RuntimeException` is thrown, it's caught by the catch block, printing "Caught exception: ..." along with the exception message.
- Regardless of whether an exception occurs or not, the `finally` block is executed, printing "Finally block executed".

## Rethrowing Exceptions

Rethrowing exceptions in Java allows you to handle an exception in one part of your code and then propagate it to another part for further handling or logging. This can be useful when you want to add more context to the exception or when you want to handle different aspects of the exception in different parts of your codebase.

Here's how you can rethrow exceptions in Java:

```java
public class RethrowExample {
    public static void main(String[] args) {
        try {
            someMethod();
        } catch (Exception e) {
            // Handle the exception or add more context
            System.err.println("Exception caught: " + e.getMessage());
            // Rethrow the exception
            throw e;
        }
    }
    
    public static void someMethod() throws Exception {
        // Simulate an exception
        throw new Exception("Simulated exception");
    }
}
```

In this example:

- The `someMethod()` method throws an `Exception`.
- In the `main()` method, we call `someMethod()` within a try-catch block to catch any `Exception` that it throws.
- Inside the catch block, we handle the exception (e.g., by logging a message) and then rethrow it using the `throw` keyword followed by the caught exception (`e`).

When an exception is rethrown, it propagates up the call stack until it's caught by another try-catch block or until it reaches the top-level of the program, where it may cause the program to terminate if not handled.

Rethrowing exceptions can be useful for allowing different parts of your code to handle exceptions differently, while still ensuring that they are properly logged or propagated up the call stack for higher-level handling.

## Working with Assertions

### The assert Statement

The `assert` statement in Java is a debugging aid that tests a boolean expression to verify that it's true. If the expression is false, an AssertionError is thrown. It's primarily used for debugging purposes to check invariants in your code during development and testing.

Here's the basic syntax of the `assert` statement:

```java
assert booleanExpression;
```

Additionally, you can provide an optional message along with the boolean expression:

```java
assert booleanExpression : message;
```

When the `assert` statement is encountered during program execution:

- If the boolean expression evaluates to true, nothing happens, and the program continues execution as normal.
- If the boolean expression evaluates to false, an AssertionError is thrown. If a message is provided, it's included in the AssertionError.
- By default, assertions are disabled at runtime. To enable them, you need to run the Java Virtual Machine (JVM) with the `-ea` or `-enableassertions` option.

Here's an example demonstrating the usage of the `assert` statement:

```java
public class AssertionExample {
    public static void main(String[] args) {
        int x = 10;
        
        // Assert that x is greater than 0
        assert x > 0 : "x should be greater than 0";
        
        System.out.println("x is: " + x);
    }
}
```

In this example:

- We have a variable `x` initialized to 10.
- The `assert` statement checks whether `x > 0`. If it's false, an AssertionError with the message "x should be greater than 0" is thrown.
- Since `x` is indeed greater than 0, the assertion passes, and the program continues execution normally.

### Enabling Assertions

To enable assertions in Java, you can use the `-ea` or `-enableassertions` option when running your Java program. This option instructs the Java Virtual Machine (JVM) to enable assertions.

Here's how you can enable assertions:

1. **Command Line**: When running your Java program from the command line, include the `-ea` option:

   ```
   java -ea YourClassName
   ```

   or

   ```
   java -enableassertions YourClassName
   ```

   Replace `YourClassName` with the name of your main class.

2. **Eclipse IDE**:
   - Go to "Run" menu > "Run Configurations".
   - Select your Java application from the left panel.
   - Go to the "Arguments" tab.
   - In the "VM arguments" section, add `-ea` or `-enableassertions`.
   - Click "Apply" and then "Run".

3. **IntelliJ IDEA**:
   - Go to "Run" menu > "Edit Configurations".
   - Select your application from the left panel.
   - In the "VM options" field, add `-ea` or `-enableassertions`.
   - Click "OK" to save the configuration.
   - Run your application as usual.

Enabling assertions allows you to catch logical errors and debug your code more effectively during development and testing. However, it's important to remember that assertions are not intended for error handling in production code, as they can be disabled at runtime, and their use may impact performance.

### Using Assertions

Assertions are a useful tool for debugging and verifying assumptions in your code. They're especially helpful during development and testing phases to catch logical errors early. Here's how you can use assertions effectively in Java:

1. **Checking Preconditions**: Use assertions to verify that preconditions are met before executing a method. For example, checking that parameters passed to a method are valid.

    ```java
    public void process(int value) {
        assert value >= 0 : "Value must be non-negative";
        // Process the value
    }
    ```

2. **Checking Invariants**: Assert that certain conditions hold true at specific points in your code to ensure consistency.

    ```java
    public void update(int newValue) {
        assert newValue != 0 : "New value cannot be zero";
        // Update the state with the new value
    }
    ```

3. **Debugging**: Use assertions to check intermediate results or assumptions during debugging.

    ```java
    public void processData(int[] data) {
        assert data != null : "Data array must not be null";
        assert data.length > 0 : "Data array must not be empty";

        // Process the data
    }
    ```

4. **Documentation**: Use assertions to document and enforce expected behavior in your code. This makes your code more self-explanatory and easier to maintain.

    ```java
    /**
     * Calculates the factorial of a non-negative integer.
     *
     * @param n the non-negative integer
     * @return the factorial of n
     */
    public int factorial(int n) {
        assert n >= 0 : "Input must be non-negative";
        
        // Calculate factorial
        // ...
    }
    ```

Remember that assertions can be disabled at runtime, so they should not be used for critical error handling in production code. They're primarily intended for debugging and testing purposes. Use them judiciously to catch logical errors and verify assumptions during development.


# Summary

1. **Determine if an exception is checked or unchecked**:
- Checked exceptions are in the Exception class hierarchy but not the RuntimeException hierarchy.
- DateTimeParseException, IOException, and SQLException are common checked exceptions

2. **Recognize when to use throw vs. throws**:
- The throw keyword is used when you actually want to throw an exception. 
- For example, throw new RuntimeException(). The `throws` keyword is used in a method declaration.

3. **Create code using multi-catch**:
- The multiple exception types are separated by a pipe (|).
- They are not allowed to have a subclass/superclass relationship.

4. **Identify the similarities and differences between a traditional try statement and try-with-resources statement**:
- A traditional try statement is required to have at least one catch block or a `finally block`. 
- A try-with-resources statement is allowed to omit both.
- A try-with-resources statement is allowed to create suppressed exceptions in the try clause or
when closing resources. 
- Neither is allowed to create suppressed exceptions by combining the try and finally (or catch) clauses.

5. **Know how to enable assertions**:
- Assertions are disabled by default. 
- Watch for a question that uses assertions but does not enable them or a question that tests your knowledge of
how assertions are enabled from the command line.