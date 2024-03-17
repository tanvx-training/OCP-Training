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

Exactly! Here's a breakdown of how Java categorizes exceptions:

### Exception Hierarchy:

1. **java.lang.Error**:
   - Subclasses of `Error` represent exceptional conditions that a program should not attempt to catch or handle. They typically denote serious errors from which a program cannot recover.

2. **java.lang.RuntimeException**:
   - Subclasses of `RuntimeException` are runtime exceptions or unchecked exceptions. They represent exceptional conditions that occur at runtime and are generally caused by programming errors. Java does not enforce handling or declaration of runtime exceptions.

3. **java.lang.Exception**:
   - Subclasses of `Exception` that do not subclass `RuntimeException` are checked exceptions. They represent exceptional conditions that a well-behaved application should anticipate and handle.
   - Java requires checked exceptions to be either caught and handled or declared (thrown) in the method signature using the `throws` clause.

### Example:

```java
import java.io.*;

public class ExceptionExample {
    public static void main(String[] args) {
        try {
            // Code that may throw exceptions
            int result = divide(10, 0); // This will throw an ArithmeticException (RuntimeException)
        } catch (ArithmeticException e) {
            // Handling arithmetic exceptions
            System.out.println("Error: Division by zero");
        } catch (Exception e) {
            // Handling other checked exceptions
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method that throws a checked exception
    public static int divide(int dividend, int divisor) throws IOException {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return dividend / divisor;
    }
}
```

In this example:
- We have a method `divide(int dividend, int divisor)` that throws an `ArithmeticException` (a subclass of `RuntimeException`) if the divisor is zero.
- In the `main()` method, we attempt to call `divide(10, 0)`, which will throw an `ArithmeticException`.
- We catch this exception using a `try-catch` block and handle it appropriately.
- We also demonstrate catching other checked exceptions using a catch block for `Exception`.

Absolutely! You've summarized the behavior of `try-catch-finally` blocks and the handling of exceptions well. Here's a breakdown of the key points:

### Behavior of `try-catch-finally` Blocks:

1. **Multiple Catch Blocks**:
   - A `try` statement can have multiple `catch` blocks to handle different types of exceptions.
   - Each `catch` block can catch a specific type of exception.
   - Java looks for an exception that can be caught by each `catch` block in the order in which they appear, and the first match is executed.
   - Only one `catch` block will run for each `try` block.

2. **Finally Block**:
   - The `finally` block, if present, is executed after the `try` block, regardless of whether an exception occurs or not.
   - Code inside the `finally` block will always run, even if an exception is thrown, and even if there is a `return` statement inside the `try` or `catch` blocks.

3. **Exception Propagation**:
   - If both the `catch` and `finally` blocks throw exceptions, the one thrown from the `finally` block will be propagated to the caller, overriding any exception thrown from the `catch` block.

4. **Common Checked Exceptions**:
   - Checked exceptions are exceptions that must be either caught and handled or declared in the method signature using the `throws` clause.
   - Examples of common checked exceptions include `ParseException`, `IOException`, and `SQLException`.

### Example:

```java
import java.text.ParseException;
import java.io.IOException;
import java.sql.SQLException;

public class TryCatchFinallyExample {
    public static void main(String[] args) {
        try {
            // Code that may throw exceptions
            throwCheckedException();
        } catch (ParseException e) {
            // Handling ParseException
            System.out.println("ParseException occurred");
        } catch (IOException e) {
            // Handling IOException
            System.out.println("IOException occurred");
        } catch (SQLException e) {
            // Handling SQLException
            System.out.println("SQLException occurred");
        } finally {
            // Code in the finally block always runs
            System.out.println("Finally block executed");
            // If both catch and finally throw exceptions, the one from finally gets thrown
            throwUncheckedException();
        }
    }

    // Method that throws a checked exception
    public static void throwCheckedException() throws ParseException, IOException, SQLException {
        // Code that may throw checked exceptions
    }

    // Method that throws an unchecked exception
    public static void throwUncheckedException() {
        // Code that throws an unchecked exception
        throw new NullPointerException("Unchecked exception occurred");
    }
}
```

In this example:
- We have a `try-catch-finally` block with multiple `catch` blocks to handle different types of exceptions (`ParseException`, `IOException`, and `SQLException`).
- Only one `catch` block will run based on the type of exception thrown.
- The `finally` block always executes, even if an exception occurs or if a `return` statement is encountered in the `try` or `catch` blocks.
- If both the `catch` and `finally` blocks throw exceptions, the one from the `finally` block will be propagated to the caller.

You've got it! Here's a breakdown of multi-catch blocks in Java:

### Multi-catch Blocks:

1. **Catching Multiple Exception Types**:
   - Multi-catch blocks allow catching multiple exception types in the same `catch` block.
   - Exception types are separated by a pipe `|` symbol (`|`) within the parentheses of the `catch` block.
   - This feature was introduced in Java 7 to simplify code by reducing redundancy when handling multiple exception types.

2. **Exception Types Relationship**:
   - The exception types listed in a multi-catch block are not allowed to have a subclass/superclass relationship. This restriction prevents ambiguity in exception handling.

3. **Effectively Final Variable**:
   - The variable used in a multi-catch expression is effectively final, meaning you cannot reassign it within the block.
   - This ensures consistency and clarity in exception handling.

### Example:

```java
import java.io.FileNotFoundException;
import java.io.IOException;

public class MultiCatchExample {
    public static void main(String[] args) {
        try {
            // Code that may throw exceptions
            throwException();
        } catch (FileNotFoundException | IOException e) {
            // Handling multiple exception types in a single catch block
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }

    // Method that throws exceptions
    public static void throwException() throws IOException {
        // Code that throws exceptions
        throw new FileNotFoundException("File not found");
    }
}
```

In this example:
- We use a multi-catch block to handle both `FileNotFoundException` and `IOException` in the same catch block.
- Since `FileNotFoundException` is a subclass of `IOException`, they cannot be caught together in a multi-catch block. If attempted, it will result in a compile-time error.
- The variable `e` used in the catch block is effectively final, meaning it cannot be reassigned within the block.

You've got the essence of try-with-resources down pat! Here's a detailed explanation:

### Try-With-Resources:

1. **Automatic Resource Management**:
   - Try-with-resources allows Java to automatically manage resources by taking care of calling the `close()` method.
   - This ensures that resources are properly closed regardless of whether an exception occurs or not, promoting cleaner and safer resource management.

2. **AutoCloseable Interface**:
   - Objects instantiated within the try clause must implement the `AutoCloseable` interface.
   - The `AutoCloseable` interface declares a single method `close()` which is invoked to release resources.

3. **Suppressed Exceptions**:
   - If an exception occurs in the try block and one or more `close()` methods also throw exceptions, Java uses suppressed exceptions to keep track of both.
   - Suppressed exceptions allow multiple exceptions to be associated with a single try block.

4. **Retrieving Suppressed Exceptions**:
   - The `getSuppressed()` method of the `Throwable` class allows retrieving suppressed exceptions.
   - This method returns an array of suppressed exceptions associated with the primary exception.

5. **No Catch or Finally Blocks Required**:
   - Unlike traditional try statements, try-with-resources does not require a catch or finally block to be present.
   - However, you can still include catch and finally blocks if needed.

### Example:

```java
import java.io.*;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            // Retrieving suppressed exceptions, if any
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed Exception: " + suppressed.getMessage());
            }
        }
    }
}
```

In this example:
- We use try-with-resources to automatically close the `BufferedReader` created for reading a file.
- If an exception occurs during reading or while closing the reader, the primary exception is caught, and any suppressed exceptions are retrieved and handled.
- The `getSuppressed()` method allows access to suppressed exceptions associated with the primary exception.

You've summarized assertions well! Here's a breakdown of key points:

### Assertions in Java:

1. **Definition**:
   - An assertion is a boolean expression placed at a specific point in the code, indicating an expected condition that should always be true.
   - It's a way to validate assumptions about the program's state during development and debugging.

2. **Failure Handling**:
   - If an assertion fails (the boolean expression evaluates to false), Java throws an `AssertionError`.
   - This indicates a violation of the expected condition.

3. **Best Practices**:
   - Assertions are primarily used for debugging and should not change the state of any variables or have side effects.
   - They serve as checks to validate internal correctness assumptions.
   - They are often disabled in production code due to performance considerations.

4. **Enabling Assertions**:
   - Assertions are disabled by default at runtime.
   - To enable assertions, you use the `-ea` or `--enableassertions` flag when running the Java Virtual Machine (JVM).
   - This flag tells the JVM to enable assertions in all classes.

5. **Disabling Assertions**:
   - Assertions can be explicitly disabled using the `-da` or `--disableassertions` flag when running the JVM.
   - Alternatively, you can disable assertions for specific packages or classes by specifying them after the flag.

### Example:

```java
public class AssertionExample {
    public static void main(String[] args) {
        int x = 10;
        
        // Assertion to check if x is positive
        assert x > 0 : "x should be positive";
        
        // If assertion fails, AssertionError is thrown
        // It indicates a violation of the expected condition
        System.out.println("After assertion");
    }
}
```

In this example:
- We have an assertion `assert x > 0 : "x should be positive";` to ensure that the variable `x` is positive.
- If `x` is not positive when the assertion is encountered during runtime, an `AssertionError` with the message `"x should be positive"` will be thrown.
- Assertions are disabled by default. To enable them, we use the `-ea` flag when running the program.

# Exam Essentials

**Determine if an exception is checked or unchecked**

You've got it! Let's break it down:

### Checked and Unchecked Exceptions:

1. **Checked Exceptions**:
   - Checked exceptions are exceptions that must be either caught and handled or declared in the method signature using the `throws` clause.
   - They are part of the `Exception` class hierarchy but not the `RuntimeException` hierarchy.
   - Examples of common checked exceptions include `DateTimeParseException`, `IOException`, and `SQLException`.

2. **Unchecked Exceptions**:
   - Unchecked exceptions, also known as runtime exceptions, are exceptions that do not need to be declared or caught explicitly.
   - They are subclasses of `RuntimeException` or `Error`, or their subclasses.
   - Unchecked exceptions can occur at runtime and are typically caused by programming errors or unexpected conditions.

### Example:

```java
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class ExceptionExample {
    public static void main(String[] args) {
        try {
            // Code that may throw exceptions
            throwCheckedException(); // Example of a checked exception
            throwUncheckedException(); // Example of an unchecked exception
        } catch (IOException e) {
            // Handling IOException (checked exception)
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }

    // Method that throws a checked exception
    public static void throwCheckedException() throws IOException {
        // Code that may throw a checked exception
        throw new IOException("IOException occurred");
    }

    // Method that throws an unchecked exception
    public static void throwUncheckedException() {
        // Code that throws an unchecked exception
        throw new NullPointerException("Unchecked exception occurred");
    }
}
```

In this example:
- `throwCheckedException()` method throws a checked exception (`IOException`), which must be either caught or declared.
- `throwUncheckedException()` method throws an unchecked exception (`NullPointerException`), which does not need to be explicitly handled or declared.
- `DateTimeParseException` and `SQLException` are other examples of common checked exceptions.

**Recognize when to use throw vs. throws**

You've got the distinction between `throw` and `throws` down perfectly!

### When to Use `throw` vs. `throws`:

1. **`throw` Keyword**:
   - The `throw` keyword is used to explicitly throw an exception within a method.
   - It is followed by an instance of an exception class or a subclass, indicating the specific exception to be thrown.
   - For example: `throw new RuntimeException("Something went wrong");`

2. **`throws` Keyword**:
   - The `throws` keyword is used in the method declaration to specify that the method may throw one or more types of exceptions.
   - It lists the exception types that the method can throw, allowing the caller to handle them appropriately.
   - For example: `public void readFile() throws IOException, FileNotFoundException { ... }`

### Example:

```java
import java.io.*;

public class ExceptionExample {
    // Example of using throws keyword in method declaration
    public void readFile() throws IOException, FileNotFoundException {
        // Code that may throw IOException or FileNotFoundException
    }

    // Example of using throw keyword to throw a RuntimeException
    public void performOperation() {
        // Code that may throw a RuntimeException
        throw new RuntimeException("Something went wrong");
    }

    public static void main(String[] args) {
        ExceptionExample example = new ExceptionExample();
        
        try {
            // Calling a method that declares exceptions using throws
            example.readFile();
        } catch (IOException e) {
            // Handling IOException
            System.out.println("IOException occurred: " + e.getMessage());
        }

        // Calling a method that throws a RuntimeException using throw
        example.performOperation(); // This will throw a RuntimeException
    }
}
```

In this example:
- The `readFile()` method declares that it may throw `IOException` or `FileNotFoundException` using the `throws` keyword.
- The `performOperation()` method directly throws a `RuntimeException` using the `throw` keyword.
- When calling `readFile()`, the caller must handle the declared exceptions or propagate them using `throws`.
- When calling `performOperation()`, the thrown `RuntimeException` can be caught and handled by the caller or propagated up the call stack.

**Create code using multi-catch**

Certainly! Here's an example demonstrating the use of multi-catch with exception handling:

```java
import java.io.*;
import java.text.ParseException;

public class MultiCatchExample {
    public static void main(String[] args) {
        try {
            // Code that may throw multiple exceptions
            throwExceptions();
        } catch (IOException | ParseException e) {
            // Handling multiple exceptions in a single catch block
            System.out.println("An IOException or ParseException occurred: " + e.getMessage());
        }
    }

    // Method that throws multiple exceptions
    public static void throwExceptions() throws IOException, ParseException {
        // Code that may throw IOException or ParseException
        throw new IOException("IOException occurred");
    }
}
```

In this example:
- We have a `throwExceptions()` method that declares it may throw both `IOException` and `ParseException`.
- In the `main()` method, we use a single catch block with multi-catch to handle both `IOException` and `ParseException`.
- The catch block handles both exceptions uniformly, printing a message indicating that either an `IOException` or a `ParseException` occurred.

**Identify the similarities and differences between a traditional try statement and try-with-resources statement**

You've captured the key similarities and differences between traditional `try` statements and `try-with-resources` statements quite well. Let's break it down:

### Similarities:

1. **Exception Handling**:
   - Both traditional `try` statements and `try-with-resources` statements provide a mechanism for exception handling in Java.
   - They allow developers to handle exceptions that may occur within a block of code.

2. **Automatic Resource Management**:
   - Both constructs can be used to manage resources, such as file streams or database connections.
   - They ensure that resources are properly closed or released, even if an exception occurs.

### Differences:

1. **Syntax**:
   - Traditional `try` statements require at least one `catch` block or a `finally` block.
   - `try-with-resources` statements allow for a simpler syntax where resources are declared and automatically closed, without the need for explicit catch or finally blocks.

2. **Resource Management**:
   - `try-with-resources` statements are specifically designed for resource management.
   - They automatically manage the lifecycle of resources declared within the try-with-resources block, closing them in reverse order of their creation.

3. **Suppressed Exceptions**:
   - `try-with-resources` statements are allowed to create suppressed exceptions both in the try clause and when closing resources.
   - Traditional `try` statements do not create suppressed exceptions by combining the try and finally (or catch) clauses.

### Example:

```java
import java.io.*;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        try (
            FileReader fileReader = new FileReader("example.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
```

In this example:
- We use a `try-with-resources` statement to automatically manage the resources (`FileReader` and `BufferedReader`) for reading a file.
- If an `IOException` occurs during the reading process, it is caught and handled in the catch block.
- The resources are automatically closed at the end of the `try` block, even if an exception occurs, ensuring proper resource management.

**Know how to enable assertions**

Absolutely! Enabling assertions in Java is essential for utilizing them effectively. By default, assertions are disabled at runtime for performance reasons. To enable assertions, you use the `-ea` or `--enableassertions` flag when running the Java Virtual Machine (JVM) or a specific Java application.

Here's how to do it:

### Command Line:

```bash
java -ea YourJavaProgram
```

### Eclipse IDE:

1. Right-click on your project and select "Run As" -> "Run Configurations..."
2. In the "Arguments" tab, add `-ea` or `--enableassertions` to the "VM arguments" section.
3. Click "Apply" and then "Run" to execute your program with assertions enabled.

### IntelliJ IDEA:

1. Go to "Run" -> "Edit Configurations..."
2. In the "VM options" field, add `-ea` or `--enableassertions`.
3. Click "OK" to save the configuration and then run your program.

### NetBeans IDE:

1. Right-click on your project and select "Properties".
2. In the "Run" category, add `-ea` or `--enableassertions` to the "VM Options" field.
3. Click "OK" to apply the changes and then run your program.

Remember to always ensure that assertions are enabled when running code that utilizes them, especially if you're dealing with questions or scenarios involving assertion testing!