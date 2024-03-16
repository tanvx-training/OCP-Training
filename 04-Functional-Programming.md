# Functional Programming

## Using Variables in Lambdas

- Lambda expressions can access static variables, instance variables, effectively final
method parameters, and effectively final local variables

```java
package chap04.lambdas;

public class MainApp {

  static String run = "run";
  String walk = "walk";

  void play(Gorilla gorilla){
    System.out.println(gorilla.move());
  }

  void everyonePlay(boolean baby) {
    String approach = "amble";
    play(() -> run); // Access static variables
    play(() -> walk); // Access instance variables
    play(() -> baby ? "hitch a ride" : "run"); // Access method parameters
    play(() -> approach); // Access local variables
  }

  public static void main(String[] args) {
    MainApp app = new MainApp();
    app.everyonePlay(true);
  }
}
interface Gorilla {
  String move();
}
```

## Working with Built-In Functional Interfaces

**Common functional interfaces**

| Functional Interfaces | # Parameters | Return Type | Single Abstract Method |
|-----------------------|--------------|-------------|------------------------|
| Supplier<T>           | 0            | T           | get                    |
| Consumer<T>           | 1 (T)        | void        | accept                 |
| BiConsumer<T, U>      | 2 (T, U)     | void        | accept                 |
| Predicate<T>          | 1 (T)        | boolean     | test                   |
| BiPredicate<T, U>     | 2 (T, U)     | boolean     | test                   |
| Function<T, R>        | 1 (T)        | R           | apply                  |
| BiFunction<T, U, R>   | 2 (T, U)     | R           | apply                  |
| UnaryOperator<T>      | 1 (T)        | T           | apply                  |
| BinaryOperator<T>     | 2 (T, T)     | T           | apply                  |

### Implementing Supplier

Sure! In Java 8, a Supplier is a functional interface defined in the `java.util.function` package. It represents a supplier of results, which does not accept any input but produces a result.

Here's the definition of the `Supplier` interface:

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

As you can see, it's a generic interface, meaning it can supply objects of any type specified by the type parameter `T`. The single abstract method defined in this interface is `get()`, which takes no arguments and returns a result of type `T`.

You can use the `Supplier` interface to create instances where you need to provide a function that supplies a result, without taking any input.

Here's a simple example of how you can use the `Supplier` interface:

```java
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        // Creating a Supplier that supplies a random integer between 1 and 100
        Supplier<Integer> randomSupplier = () -> (int) (Math.random() * 100) + 1;

        // Getting and printing the supplied random integer
        System.out.println("Random number: " + randomSupplier.get());
    }
}
```

In this example, we create a `Supplier` called `randomSupplier` that supplies a random integer between 1 and 100 each time its `get()` method is called. Then, we call the `get()` method to obtain and print the random integer.

### Implementing Consumer and BiConsumer

Certainly! In Java 8, `Consumer` and `BiConsumer` are functional interfaces used to consume (accept) input arguments but do not return any result. The difference between them is that `Consumer` accepts a single argument, while `BiConsumer` accepts two arguments.

Here are the definitions of `Consumer` and `BiConsumer` interfaces:

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

```java
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```

Let's implement them with examples:

### Consumer Example:
```java
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        // Creating a Consumer to print a message
        Consumer<String> messageConsumer = message -> System.out.println("Message received: " + message);

        // Using the Consumer to consume a message
        messageConsumer.accept("Hello, world!");
    }
}
```

In this example, we created a `Consumer` called `messageConsumer` that takes a `String` as input and prints a message. We then use the `accept()` method to consume the message "Hello, world!".

### BiConsumer Example:
```java
import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void main(String[] args) {
        // Creating a BiConsumer to concatenate and print two strings
        BiConsumer<String, String> stringConcatenator = (str1, str2) -> System.out.println(str1 + str2);

        // Using the BiConsumer to concatenate and print two strings
        stringConcatenator.accept("Hello, ", "world!");
    }
}
```

In this example, we created a `BiConsumer` called `stringConcatenator` that takes two `String` inputs and concatenates them, printing the result. Then, we use the `accept()` method to consume two strings "Hello, " and "world!" and concatenate them before printing the result.

### Implementing Predicate and BiPredicate

Certainly! In Java 8, `Predicate` and `BiPredicate` are functional interfaces used for evaluating conditions and returning boolean results. `Predicate` evaluates a single argument, while `BiPredicate` evaluates two arguments.

Here are the definitions of `Predicate` and `BiPredicate` interfaces:

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

```java
@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
}
```

Let's implement them with examples:

### Predicate Example:
```java
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        // Creating a Predicate to check if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Using the Predicate to test if a number is even
        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 7 even? " + isEven.test(7));
    }
}
```

In this example, we created a `Predicate` called `isEven` that tests if a number is even by checking if the remainder of dividing the number by 2 is 0. We then use the `test()` method to test if numbers 10 and 7 are even.

### BiPredicate Example:
```java
import java.util.function.BiPredicate;

public class BiPredicateExample {
    public static void main(String[] args) {
        // Creating a BiPredicate to check if the sum of two numbers is greater than 10
        BiPredicate<Integer, Integer> isSumGreaterThanTen = (num1, num2) -> (num1 + num2) > 10;

        // Using the BiPredicate to test if the sum of two numbers is greater than 10
        System.out.println("Is the sum of 5 and 6 greater than 10? " + isSumGreaterThanTen.test(5, 6));
        System.out.println("Is the sum of 3 and 4 greater than 10? " + isSumGreaterThanTen.test(3, 4));
    }
}
```

In this example, we created a `BiPredicate` called `isSumGreaterThanTen` that tests if the sum of two numbers is greater than 10. We then use the `test()` method to test if the sums of (5 + 6) and (3 + 4) are greater than 10.

### Implementing Function and BiFunction

Certainly! In Java 8, `Function` and `BiFunction` are functional interfaces used to represent functions that accept one and two arguments, respectively, and produce a result.

Here are the definitions of `Function` and `BiFunction` interfaces:

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```

Let's implement them with examples:

### Function Example:
```java
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // Creating a Function to convert a string to its length
        Function<String, Integer> stringLengthFunction = str -> str.length();

        // Using the Function to get the length of a string
        System.out.println("Length of 'Hello': " + stringLengthFunction.apply("Hello"));
        System.out.println("Length of 'Java': " + stringLengthFunction.apply("Java"));
    }
}
```

In this example, we created a `Function` called `stringLengthFunction` that takes a `String` as input and returns its length using the `length()` method. We then use the `apply()` method to get the length of strings "Hello" and "Java".

### BiFunction Example:
```java
import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        // Creating a BiFunction to concatenate two strings
        BiFunction<String, String, String> stringConcatenator = (str1, str2) -> str1 + str2;

        // Using the BiFunction to concatenate two strings
        System.out.println("Concatenated string: " + stringConcatenator.apply("Hello, ", "world!"));
    }
}
```

In this example, we created a `BiFunction` called `stringConcatenator` that takes two `String` inputs and concatenates them using the `+` operator. We then use the `apply()` method to concatenate two strings "Hello, " and "world!".

### Implementing UnaryOperator and BinaryOperator

In Java 8, `UnaryOperator` and `BinaryOperator` are specialized forms of the `Function` interface. They are functional interfaces that represent operations on a single and two operands respectively, where the operands and the result are all of the same type.

Here are the definitions of `UnaryOperator` and `BinaryOperator` interfaces:

```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    // Inherits the apply method from Function<T, T>
}
```

```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
    // Inherits the apply method from BiFunction<T, T, T>
}
```

Let's implement them with examples:

### UnaryOperator Example:
```java
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {
        // Creating a UnaryOperator to square a number
        UnaryOperator<Integer> square = num -> num * num;

        // Using the UnaryOperator to square a number
        System.out.println("Square of 5: " + square.apply(5));
        System.out.println("Square of -3: " + square.apply(-3));
    }
}
```

In this example, we created a `UnaryOperator` called `square` that squares an integer. Since `UnaryOperator` extends `Function<T, T>`, we can directly use it as a function to apply an operation on a single operand.

### BinaryOperator Example:
```java
import java.util.function.BinaryOperator;

public class BinaryOperatorExample {
    public static void main(String[] args) {
        // Creating a BinaryOperator to find the maximum of two numbers
        BinaryOperator<Integer> max = (num1, num2) -> num1 > num2 ? num1 : num2;

        // Using the BinaryOperator to find the maximum of two numbers
        System.out.println("Maximum of 5 and 8: " + max.apply(5, 8));
        System.out.println("Maximum of -3 and 10: " + max.apply(-3, 10));
    }
}
```

In this example, we created a `BinaryOperator` called `max` that returns the maximum of two integers. Since `BinaryOperator` extends `BiFunction<T, T, T>`, we can directly use it as a function to apply an operation on two operands.

### Returning an Optional

- In Java 8 and later versions, the Optional class is used to represent a container object that may or may not contain a non-null value. It helps in handling scenarios where a method may or may not return a value, or where the value might be absent due to various reasons, such as querying a database or reading a file.

- The Optional class provides methods to work with potentially null values in a more expressive and concise way, without explicitly checking for null references, thereby reducing the chances of NullPointerExceptions.

**Optional instance methods**

| Method                 | When Optional Is Empty                       | When Optional Contains a Value |
|------------------------|----------------------------------------------|--------------------------------|
| get()                  | Throws an exception                          | Returns value                  |
| ifPresent(Consumer c)  | Does nothing                                 | Calls Consumer c with value    |
| isPresent()            | Returns false                                | Returns true                   |
| orElse(T other)        | Returns other parameter                      | Returns value                  |
| orElseGet(Supplier s)  | Returns result of calling Supplier           | Returns value                  |
| orElseThrow(Suppliers) | Throws exception created by calling Supplier | Returns value                  |

Certainly! Below is a code example demonstrating the usage of each method of the `Optional` class in different scenarios:

```java
import java.util.Optional;

public class OptionalMethodsExample {
    public static void main(String[] args) {
        // Creating an empty Optional
        Optional<String> emptyOptional = Optional.empty();

        // Creating an Optional with a value
        Optional<String> optionalWithValue = Optional.of("Hello");

        // get()
        try {
            String value1 = emptyOptional.get(); // Throws NoSuchElementException
        } catch (Exception e) {
            System.out.println("get() on empty Optional: " + e.getMessage());
        }
        String value2 = optionalWithValue.get();
        System.out.println("get() on Optional with value: " + value2);

        // ifPresent(Consumer)
        emptyOptional.ifPresent(val -> System.out.println("ifPresent() on empty Optional: " + val));
        optionalWithValue.ifPresent(val -> System.out.println("ifPresent() on Optional with value: " + val));

        // isPresent()
        System.out.println("isPresent() on empty Optional: " + emptyOptional.isPresent());
        System.out.println("isPresent() on Optional with value: " + optionalWithValue.isPresent());

        // orElse(T)
        String value3 = emptyOptional.orElse("Default"); // Returns Default
        String value4 = optionalWithValue.orElse("Default"); // Returns Hello
        System.out.println("orElse() on empty Optional: " + value3);
        System.out.println("orElse() on Optional with value: " + value4);

        // orElseGet(Supplier)
        String value5 = emptyOptional.orElseGet(() -> "Default"); // Returns Default
        String value6 = optionalWithValue.orElseGet(() -> "Default"); // Returns Hello
        System.out.println("orElseGet() on empty Optional: " + value5);
        System.out.println("orElseGet() on Optional with value: " + value6);

        // orElseThrow(Supplier)
        try {
            String value7 = emptyOptional.orElseThrow(() -> new IllegalArgumentException("No value present"));
        } catch (Exception e) {
            System.out.println("orElseThrow() on empty Optional: " + e.getMessage()); // Throws IllegalArgumentException
        }
        String value8 = optionalWithValue.orElseThrow(() -> new IllegalArgumentException("No value present"));
        System.out.println("orElseThrow() on Optional with value: " + value8);
    }
}
```

In this example, we create two `Optional` instances: one empty and one containing the value "Hello". Then, we demonstrate each method:

- `get()`: Trying to retrieve a value from an empty `Optional` throws a `NoSuchElementException`.
- `ifPresent(Consumer)`: The provided `Consumer` is only invoked if the `Optional` contains a value.
- `isPresent()`: Indicates whether the `Optional` contains a value.
- `orElse(T)`: Provides a default value if the `Optional` is empty.
- `orElseGet(Supplier)`: Provides a default value calculated by a `Supplier` if the `Optional` is empty.
- `orElseThrow(Supplier)`: Throws an exception provided by a `Supplier` if the `Optional` is empty.

## Using Streams

Absolutely! Streams in Java provide a clean and efficient way to process collections of data in a functional style. Introduced in Java 8, streams allow you to perform complex data manipulation operations such as filtering, mapping, reducing, and more, with minimal code and increased readability.

### Overview of Java Streams:

1. **Definition**:
  - A stream is a sequence of elements from a source that supports aggregate operations.
  - It does not store data, but rather allows you to process data from a source (like collections, arrays, or I/O channels) in a functional manner.

2. **Key Characteristics**:
  - **Sequence of Elements**: Streams provide a sequence of elements from a source, which can be processed in a pipeline.
  - **Functional Operations**: Streams support functional-style operations such as map, filter, reduce, etc., which can be chained together to form complex data processing pipelines.
  - **Laziness**: Stream operations are typically lazy, meaning they are only executed when necessary. Intermediate operations (like map or filter) do not process elements until a terminal operation (like forEach or collect) is invoked.
  - **Parallelism**: Streams can exploit parallelism to enhance performance. Many stream operations can be executed in parallel on multi-core architectures, providing better scalability.

3. **Stream Pipeline**:
  - A stream pipeline consists of a source (e.g., a collection), zero or more intermediate operations, and a terminal operation.
  - Intermediate operations transform the stream into another stream, and terminal operations produce a result or a side-effect.

4. **Common Operations**:
  - **Filtering**: Selecting elements based on certain criteria.
  - **Mapping**: Transforming elements from one type to another.
  - **Sorting**: Arranging elements in a particular order.
  - **Reducing**: Aggregating elements into a single result (e.g., sum, average).
  - **Collecting**: Accumulating elements into a collection or a summary result.

5. **Using Streams**:
  - Obtain a stream from a data source using methods like `stream()` or `parallelStream()` available in collections or arrays.
  - Chain together intermediate operations to transform or filter elements as needed.
  - Terminate the stream with a terminal operation to produce a result or perform a side-effect.

**Intermediate vs. terminal operations**

Here's a comparison between intermediate and terminal operations of Java Streams in a markdown table:

| Feature                      | Intermediate Operations                                  | Terminal Operations                                              |
|------------------------------|----------------------------------------------------------|------------------------------------------------------------------|
| Execution Control            | Lazy, executed only when a terminal operation is invoked | Trigger the processing of the stream and produce a result        |
| Returns                      | Stream (allows chaining)                                 | Non-stream result (e.g., a value, collection, or side-effect)    |
| Examples                     | `filter`, `map`, `sorted`, `distinct`, `limit`           | `forEach`, `collect`, `reduce`, `count`, `anyMatch`, `findFirst` |
| Usage                        | Used for data transformation and filtering               | Used to produce a result or side-effect from the stream          |
| Order of Execution           | Can be chained together in any order                     | Must be the last operation in the stream pipeline                |
| Side-Effect Operations       | Can't be used to perform side-effects                    | Can be used to perform side-effects (e.g., printing, saving)     |


### Creating Stream Sources

Certainly! There are several ways to create streams in Java. Here are a few common ways:

1. **From a Collection**:
  - You can create a stream from a collection using the `stream()` method provided by the `Collection` interface. For example:
    ```java
    List<String> myList = Arrays.asList("a", "b", "c");
    Stream<String> streamFromList = myList.stream();
    ```

2. **From an Array**:
  - Arrays also have a method `stream()` which can be used to create a stream from an array:
    ```java
    String[] myArray = {"a", "b", "c"};
    Stream<String> streamFromArray = Arrays.stream(myArray);
    ```

3. **Using `Stream.of()`**:
  - You can use the static `of()` method in the `Stream` class to create a stream from individual elements or an array of elements:
    ```java
    Stream<String> streamOfElements = Stream.of("a", "b", "c");
    ```

4. **From Stream Builder**:
  - You can use the `Stream.Builder` class to manually build a stream by adding elements to it:
    ```java
    Stream.Builder<String> builder = Stream.builder();
    builder.add("a").add("b").add("c");
    Stream<String> builtStream = builder.build();
    ```

5. **Using Stream.generate() or Stream.iterate()**:
  - You can use the `generate()` or `iterate()` static methods in the `Stream` class to create infinite streams:
    ```java
    // Generate an infinite stream of random numbers
    Stream<Double> randomStream = Stream.generate(Math::random);

    // Generate an infinite stream of sequential numbers starting from 1
    Stream<Integer> sequentialStream = Stream.iterate(1, n -> n + 1);
    ```

These are some of the common ways to create streams in Java. Depending on your data source and requirements, you can choose the appropriate method to create streams. For example, if you have a collection or an array, you can use `stream()` or `Arrays.stream()`, respectively. If you need an infinite stream, you can use `generate()` or `iterate()`.

### Using Common Terminal Operations

Sure! Here's an overview of common terminal operations in Java Streams:

| Method                                | What Happens for Infinite Streams | Return Value | Reduction |
|---------------------------------------|-----------------------------------|--------------|-----------|
| `allMatch(Predicate)`                 | Sometimes terminates              | boolean      | No        |
| `anyMatch(Predicate)`                 | Sometimes terminates              | boolean      | No        |
| `noneMatch(Predicate)`                | Sometimes terminates              | boolean      | No        |
| `collect(Collector)`                  | Does not terminate                | Varies       | Yes       |
| `count()`                             | Does not terminate                | long         | Yes       |
| `findAny()`                           | Terminates                        | `Optional<T>`| No        |
| `findFirst()`                         | Terminates                        | `Optional<T>`| No        |
| `forEach(Consumer)`                   | Does not terminate                | void         | No        |
| `min(Comparator)` / `max(Comparator)` | Does not terminate                | `Optional<T>`| Yes       |
| `reduce(BinaryOperator)`              | Does not terminate                | Varies       | Yes       |

### Common Terminal Operations:

1. **`allMatch(Predicate)` / `anyMatch(Predicate)` / `noneMatch(Predicate)`**:
  - These methods test the elements of the stream against a given predicate.
  - For infinite streams, they may terminate if the condition is met or may continue processing until the end of the stream.

```java
import java.util.stream.Stream;

public class MatchExample {
  public static void main(String[] args) {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Check if all elements are even
    boolean allEven = numbers.allMatch(num -> num % 2 == 0);
    System.out.println("All elements are even: " + allEven); // Output: false

    // Reset the stream since it's already consumed
    numbers = Stream.of(1, 2, 3, 4, 5);

    // Check if any element is greater than 5
    boolean anyGreaterThan5 = numbers.anyMatch(num -> num > 5);
    System.out.println("Any element greater than 5: " + anyGreaterThan5); // Output: false

    // Reset the stream
    numbers = Stream.of(1, 2, 3, 4, 5);

    // Check if none of the elements are negative
    boolean noneNegative = numbers.noneMatch(num -> num < 0);
    System.out.println("None of the elements are negative: " + noneNegative); // Output: true
  }
}
```

2. **`collect(Collector)`**:
  - This method accumulates the elements of the stream into a collection, applying the provided `Collector`.
  - It does not terminate the stream and returns a result depending on the collector used.

```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        // Collect elements into a list
        List<Integer> numberList = numbers.collect(Collectors.toList());
        System.out.println("Collected list: " + numberList); // Output: [1, 2, 3, 4, 5]
    }
}
```

3. **`count()`**:
  - This method counts the number of elements in the stream.
  - It does not terminate the stream and returns a long value representing the count.

```java
import java.util.stream.Stream;

public class CountExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        // Count the number of elements
        long count = numbers.count();
        System.out.println("Number of elements: " + count); // Output: 5
    }
}
```

4. **`findAny()` / `findFirst()`**:
  - These methods return an arbitrary element of the stream.
  - They terminate the stream and return an `Optional` containing the found element, if any.

```java
import java.util.Optional;
import java.util.stream.Stream;

public class FindExample {
  public static void main(String[] args) {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Find any element
    Optional<Integer> anyElement = numbers.findAny();
    System.out.println("Any element: " + anyElement.orElse(null)); // Output: Any element: 1

    // Reset the stream
    numbers = Stream.of(1, 2, 3, 4, 5);

    // Find the first element
    Optional<Integer> firstElement = numbers.findFirst();
    System.out.println("First element: " + firstElement.orElse(null)); // Output: First element: 1
  }
}
```

5. **`forEach(Consumer)`**:
  - This method performs an action for each element of the stream.
  - It does not terminate the stream and returns void.

```java
import java.util.stream.Stream;

public class ForEachExample {
  public static void main(String[] args) {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Print each element
    numbers.forEach(System.out::println);
  }
}
```

6. **`min(Comparator)` / `max(Comparator)`**:
  - These methods find the minimum or maximum element of the stream based on the provided comparator.
  - They do not terminate the stream and return an `Optional` containing the minimum or maximum element, if present.

```java
import java.util.Optional;
import java.util.stream.Stream;

public class MinMaxExample {
  public static void main(String[] args) {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Find the minimum element
    Optional<Integer> minElement = numbers.min(Integer::compareTo);
    System.out.println("Minimum element: " + minElement.orElse(null)); // Output: Minimum element: 1

    // Reset the stream
    numbers = Stream.of(1, 2, 3, 4, 5);

    // Find the maximum element
    Optional<Integer> maxElement = numbers.max(Integer::compareTo);
    System.out.println("Maximum element: " + maxElement.orElse(null)); // Output: Maximum element: 5
  }
}
```

7. **`reduce(BinaryOperator)`**:
  - This method performs a reduction operation on the elements of the stream.
  - It does not terminate the stream and returns a result based on the binary operator provided.

```java
import java.util.stream.Stream;

public class ReduceExample {
  public static void main(String[] args) {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Sum of elements using reduce
    int sum = numbers.reduce(0, Integer::sum);
    System.out.println("Sum of elements: " + sum); // Output: 15
  }
}
```

These terminal operations enable you to perform various tasks on streams, including testing elements, collecting results, counting elements, finding specific elements, performing actions on elements, finding minimum or maximum elements, and reducing elements into a single result.

### Using Common Intermediate Operations 

Certainly! Here's an introduction to each common intermediate operation in Java Streams, along with a code example for each:

### `filter(Predicate)`:
- **Description**: Filters elements of the stream based on a given predicate.
- **Example**: Filtering even numbers from a stream of integers.
```java
import java.util.stream.Stream;

public class FilterExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter even numbers
        Stream<Integer> evenNumbers = numbers.filter(num -> num % 2 == 0);
        
        // Print filtered numbers
        evenNumbers.forEach(System.out::println); // Output: 2, 4, 6, 8, 10
    }
}
```

### `distinct()`:
- **Description**: Removes duplicate elements from the stream.
- **Example**: Removing duplicate elements from a stream of strings.
```java
import java.util.stream.Stream;

public class DistinctExample {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("apple", "banana", "apple", "orange", "banana");
        
        // Remove duplicates
        Stream<String> distinctWords = words.distinct();
        
        // Print distinct words
        distinctWords.forEach(System.out::println); // Output: apple, banana, orange
    }
}
```

### `limit(long)`:
- **Description**: Limits the size of the stream to the specified maximum length.
- **Example**: Limiting the stream to the first 3 elements.
```java
import java.util.stream.Stream;

public class LimitExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        
        // Limit to the first 3 elements
        Stream<Integer> limitedNumbers = numbers.limit(3);
        
        // Print limited numbers
        limitedNumbers.forEach(System.out::println); // Output: 1, 2, 3
    }
}
```

### `skip(long)`:
- **Description**: Skips the specified number of elements from the beginning of the stream.
- **Example**: Skipping the first 2 elements from a stream of integers.
```java
import java.util.stream.Stream;

public class SkipExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        
        // Skip the first 2 elements
        Stream<Integer> skippedNumbers = numbers.skip(2);
        
        // Print skipped numbers
        skippedNumbers.forEach(System.out::println); // Output: 3, 4, 5
    }
}
```

### `map(Function)`:
- **Description**: Applies a function to each element of the stream, producing a new stream of the transformed elements.
- **Example**: Mapping each string to its length.
```java
import java.util.stream.Stream;

public class MapExample {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("apple", "banana", "orange");
        
        // Map each word to its length
        Stream<Integer> wordLengths = words.map(String::length);
        
        // Print word lengths
        wordLengths.forEach(System.out::println); // Output: 5, 6, 6
    }
}
```

### `flatMap(Function)`:
- **Description**: Flattens the elements of nested streams into a single stream.
- **Example**: Flattening a stream of lists into a single stream of integers.
```java
import java.util.List;
import java.util.stream.Stream;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<Integer>> listOfLists = List.of(
            List.of(1, 2, 3),
            List.of(4, 5),
            List.of(6, 7, 8)
        );
        
        // Flatten the list of lists
        Stream<Integer> flattenedStream = listOfLists.stream().flatMap(List::stream);
        
        // Print flattened stream
        flattenedStream.forEach(System.out::println); // Output: 1, 2, 3, 4, 5, 6, 7, 8
    }
}
```

### `sorted()`:
- **Description**: Sorts the elements of the stream.
- **Example**: Sorting a stream of integers.
```java
import java.util.stream.Stream;

public class SortedExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5);
        
        // Sort the numbers
        Stream<Integer> sortedNumbers = numbers.sorted();
        
        // Print sorted numbers
        sortedNumbers.forEach(System.out::println); // Output: 1, 1, 2, 3, 4, 5, 5, 6, 9
    }
}
```

### `peek(Consumer)`:
- **Description**: Performs an action for each element of the stream without changing its elements.
- **Example**: Printing each element of a stream while inspecting them.
```java
import java.util.stream.Stream;

public class PeekExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        
        // Peek at each element and print it
        Stream<Integer> peekedNumbers = numbers.peek(System.out::println);
        
        // Print the peeked numbers
        peekedNumbers.forEach(num -> {}

); // Output: 1, 2, 3, 4, 5
    }
}
```

These examples demonstrate how to use each common intermediate operation in Java Streams, including how they are applied to different types of streams and the results they produce.

### Putting Together the Pipeline

Putting together the pipeline in Java Streams refers to the process of chaining multiple intermediate and terminal operations to process data in a concise and efficient manner. A stream pipeline consists of a source, zero or more intermediate operations, and a terminal operation.

### Components of a Stream Pipeline:

1. **Source**:
  - The source provides the data for the stream pipeline. It could be a collection, an array, an I/O channel, or a generator function.
  - The source typically creates an initial stream from which further operations can be applied.

2. **Intermediate Operations**:
  - Intermediate operations transform or filter the elements of the stream. They are lazy, meaning they do not process elements until a terminal operation is invoked.
  - These operations include `filter`, `map`, `flatMap`, `distinct`, `sorted`, `limit`, `skip`, `peek`, and more.
  - Intermediate operations return a new stream, allowing for method chaining.

3. **Terminal Operation**:
  - The terminal operation produces a result or side-effect from the stream. It triggers the processing of the entire stream pipeline.
  - Terminal operations include `forEach`, `collect`, `reduce`, `count`, `anyMatch`, `allMatch`, `noneMatch`, `findAny`, `findFirst`, and more.
  - Once a terminal operation is invoked, the stream is consumed, and further operations cannot be applied to it.

### Chaining Operations:
- Stream operations can be chained together to form a pipeline. Intermediate operations are typically chained after the source, followed by a terminal operation.
- Intermediate operations are lazy, so they are only executed when a terminal operation is invoked.
- This lazy evaluation allows for efficient processing of large datasets, as only the necessary elements are processed.

### Example:
```java
import java.util.stream.Stream;

public class StreamPipelineExample {
    public static void main(String[] args) {
        // Source: Create a stream from a list of integers
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        // Intermediate operations: Filter even numbers and map them to their square
        Stream<Integer> processedNumbers = numbers
                .filter(num -> num % 2 == 0)
                .map(num -> num * num);

        // Terminal operation: Print the processed numbers
        processedNumbers.forEach(System.out::println); // Output: 4, 16
    }
}
```

In this example:
- We create a stream from a list of integers as the source.
- We apply intermediate operations (`filter` and `map`) to filter even numbers and map them to their squares.
- Finally, we apply a terminal operation (`forEach`) to print the processed numbers.

Putting together the pipeline allows for concise and readable code for data processing in Java Streams, promoting functional programming principles and facilitating efficient stream processing.

### Printing a Stream

Certainly! There are several ways to print the elements of a stream in Java. Here are a few common approaches:

### Using forEach(Consumer):
The `forEach` terminal operation allows you to perform an action for each element of the stream. You can pass a `Consumer` function to `forEach` to specify the action to be performed.

Example:
```java
import java.util.stream.Stream;

public class PrintStreamExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        
        // Print each element using forEach
        numbers.forEach(System.out::println);
    }
}
```

### Using collect(Collectors.joining()):
The `collect` terminal operation allows you to accumulate the elements of the stream into a collection. You can use `Collectors.joining()` to concatenate the elements into a single string.

Example:
```java
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintStreamExample {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("apple", "banana", "orange");
        
        // Concatenate elements into a single string
        String result = words.collect(Collectors.joining(", "));
        
        // Print the concatenated string
        System.out.println(result); // Output: apple, banana, orange
    }
}
```

### Using peek(Consumer):
The `peek` intermediate operation allows you to perform an action for each element of the stream without changing its elements. It's often used for debugging or logging purposes.

Example:
```java
import java.util.stream.Stream;

public class PrintStreamExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        
        // Peek at each element and print it
        numbers.peek(System.out::println).forEach(num -> {});
    }
}
```

### Using toArray() and Arrays.toString():
If you want to print the elements of a stream as an array, you can use the `toArray` terminal operation to convert the stream to an array and then use `Arrays.toString()` to print the array.

Example:
```java
import java.util.Arrays;
import java.util.stream.Stream;

public class PrintStreamExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        
        // Convert stream to array and print the array
        Integer[] array = numbers.toArray(Integer[]::new);
        System.out.println(Arrays.toString(array)); // Output: [1, 2, 3, 4, 5]
    }
}
```

These are a few common ways to print the elements of a stream in Java. Choose the approach that best fits your requirements and coding style.

## Working with Primitives

Primitive streams in Java, such as `IntStream`, `LongStream`, and `DoubleStream`, are needed primarily for performance reasons and to avoid the overhead of boxing and unboxing primitive values.

Here are some key reasons why primitive streams are beneficial:

1. **Efficiency**: Primitive streams operate directly on primitive data types (`int`, `long`, `double`), which are more memory-efficient and faster to process compared to their boxed counterparts (`Integer`, `Long`, `Double`). Operating directly on primitive values avoids the overhead of object creation and garbage collection associated with boxing and unboxing.

2. **Performance**: Primitive streams leverage specialized implementations and optimizations for primitive types, resulting in better performance for numerical computations and processing large datasets. These optimizations include loop unrolling, vectorization, and other low-level optimizations provided by the JVM.

3. **Reduced Memory Consumption**: Primitive streams consume less memory compared to streams of boxed objects. This is especially beneficial when dealing with large datasets or when memory consumption needs to be minimized.

4. **Functional Programming with Primitives**: Primitive streams allow for functional programming paradigms to be applied to primitive data types, enabling concise and expressive code for numerical operations, filtering, mapping, and reduction.

5. **Integration with Existing APIs**: Primitive streams seamlessly integrate with existing APIs and libraries that deal with primitive types, such as mathematical libraries, numerical computation frameworks, and low-level data processing tasks.

Overall, primitive streams are essential for efficient and performant processing of primitive data types in Java, especially in scenarios where performance, memory consumption, and numerical computations are critical considerations. They provide a streamlined and optimized approach to working with primitive values in streams, enhancing the capabilities of Java's stream API.

### Creating Primitive Streams

#### 1. Using `IntStream`, `LongStream`, and `DoubleStream` Static Methods:
You can use the static methods provided by `IntStream`, `LongStream`, and `DoubleStream` to create primitive streams.

##### Example:
```java
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        // Create an IntStream from 1 to 5 (inclusive)
        IntStream intStream = IntStream.rangeClosed(1, 5);
        intStream.forEach(System.out::println); // Output: 1, 2, 3, 4, 5
    }
}
```

#### 2. Converting Arrays to Primitive Streams:
You can use the `Arrays.stream()` method to convert arrays of primitive types (`int[]`, `long[]`, `double[]`) to corresponding primitive streams.

##### Example:
```java
import java.util.Arrays;
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        
        // Convert int array to IntStream
        IntStream intStream = Arrays.stream(array);
        intStream.forEach(System.out::println); // Output: 1, 2, 3, 4, 5
    }
}
```

#### 3. Using `Stream.mapToInt()`, `Stream.mapToLong()`, and `Stream.mapToDouble()`:
You can use the `mapToInt()`, `mapToLong()`, and `mapToDouble()` methods on a stream of objects to map them to primitive streams.

##### Example:
```java
import java.util.stream.Stream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("1", "2", "3", "4", "5");
        
        // Map strings to ints and create IntStream
        IntStream intStream = stringStream.mapToInt(Integer::parseInt);
        intStream.forEach(System.out::println); // Output: 1, 2, 3, 4, 5
    }
}
```

### 4. Using `Random.ints()`, `Random.longs()`, and `Random.doubles()`:
You can use the `ints()`, `longs()`, and `doubles()` methods provided by the `Random` class to generate streams of random primitive values.

#### Example:
```java
import java.util.Random;
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        // Generate an IntStream of 5 random integers between 1 and 100
        IntStream intStream = new Random().ints(5, 1, 100);
        intStream.forEach(System.out::println); // Output: Random integers between 1 and 100
    }
}
```

**Mapping methods between types of streams**

| Source Stream Class | To Create Stream | To Create DoubleStream | To Create IntStream   | To Create LongStream     |
|---------------------|------------------|------------------------|-----------------------|--------------------------|
| Stream              | map              | mapToDouble            | mapToInt              | mapToLong                |
| DoubleStream        | mapToObj         | map                    | mapToInt              | mapToLong                |
| IntStream           | mapToObj         | mapToDouble            | map                   | mapToLong                |
| LongStream          | mapToObj         | mapToDouble            | mapToInt              | map                      |

**Function parameters when mapping between types of streams**

| Source Stream Class | To Create Stream | To Create DoubleStream | To Create IntStream   | To Create LongStream  |
|---------------------|------------------|------------------------|-----------------------|-----------------------|
| Stream              | Function         | ToDoubleFunction       | ToIntFunction         | ToLongFunction        |
| DoubleStream        | DoubleFunction   | DoubleUnaryOperator    | DoubleToIntFunction   | DoubleToLongFunction  |
| IntStream           | IntFunction      | IntToDoubleFunction    | IntUnaryOperator      | IntToLongFunction     |
| LongStream          | LongFunction     | LongToDoubleFunction   | LongToIntFunction     | LongUnaryOperator     |

Certainly! Here are code examples for each mapping method between types of streams:

### Mapping methods between types of streams:

#### `map()`:
```java
import java.util.stream.Stream;

public class MappingExample {
    public static void main(String[] args) {
        // Create a Stream of integers
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        
        // Map each integer to its string representation and create a Stream
        Stream<String> mappedStream = stream.map(Object::toString);
        
        // Print each element of the mapped Stream
        mappedStream.forEach(System.out::println); // Output: "1", "2", "3", "4", "5"
    }
}
```

#### `mapToDouble()`:
```java
import java.util.stream.IntStream;

public class MappingExample {
    public static void main(String[] args) {
        // Create an IntStream of integers
        IntStream intStream = IntStream.rangeClosed(1, 5);
        
        // Map each integer to its double value and create a DoubleStream
        DoubleStream doubleStream = intStream.mapToDouble(i -> i * 1.5);
        
        // Print each element of the mapped DoubleStream
        doubleStream.forEach(System.out::println); // Output: 1.5, 3.0, 4.5, 6.0, 7.5
    }
}
```

#### `mapToInt()`:
```java
import java.util.stream.Stream;

public class MappingExample {
    public static void main(String[] args) {
        // Create a Stream of strings
        Stream<String> stream = Stream.of("1", "2", "3", "4", "5");
        
        // Map each string to its integer value and create an IntStream
        IntStream intStream = stream.mapToInt(Integer::parseInt);
        
        // Print each element of the mapped IntStream
        intStream.forEach(System.out::println); // Output: 1, 2, 3, 4, 5
    }
}
```

#### `mapToLong()`:
```java
import java.util.stream.DoubleStream;

public class MappingExample {
    public static void main(String[] args) {
        // Create a DoubleStream of floating-point numbers
        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5);
        
        // Map each double to its long value and create a LongStream
        LongStream longStream = doubleStream.mapToLong(d -> (long) d);
        
        // Print each element of the mapped LongStream
        longStream.forEach(System.out::println); // Output: 1, 2, 3, 4, 5
    }
}
```

These examples demonstrate how to use each mapping method to transform elements between different types of streams in Java.

### Using Optional with Primitive Streams 

**Optional types for primitives** 

|                                | OptionalDouble            | OptionalInt              | OptionalLong            |
|--------------------------------|---------------------------|--------------------------|-------------------------|
| **Getting as a primitive**     | `getAsDouble()`           | `getAsInt()`             | `getAsLong()`           |
| **orElseGet() parameter type** | `DoubleSupplier`          | `IntSupplier`            | `LongSupplier`          |
| **Return type of max()**       | `OptionalDouble`          | `OptionalInt`            | `OptionalLong`          |
| **Return type of sum()**       | `double`                  | `int`                    | `long`                  |
| **Return type of avg()**       | `OptionalDouble`          | `OptionalDouble`         | `OptionalDouble`        |

Certainly! Here are code examples demonstrating the usage of methods associated with `OptionalDouble`, `OptionalInt`, and `OptionalLong`:

### Getting as a primitive:

```java
import java.util.OptionalDouble;

public class OptionalExample {
    public static void main(String[] args) {
        OptionalDouble optionalDouble = OptionalDouble.of(3.14);
        
        // Getting the value as a primitive double
        double value = optionalDouble.getAsDouble();
        System.out.println("Value as double: " + value); // Output: Value as double: 3.14
    }
}
```

```java
import java.util.OptionalInt;

public class OptionalExample {
    public static void main(String[] args) {
        OptionalInt optionalInt = OptionalInt.of(42);
        
        // Getting the value as a primitive int
        int value = optionalInt.getAsInt();
        System.out.println("Value as int: " + value); // Output: Value as int: 42
    }
}
```

```java
import java.util.OptionalLong;

public class OptionalExample {
    public static void main(String[] args) {
        OptionalLong optionalLong = OptionalLong.of(1234567890L);
        
        // Getting the value as a primitive long
        long value = optionalLong.getAsLong();
        System.out.println("Value as long: " + value); // Output: Value as long: 1234567890
    }
}
```

### orElseGet() with parameter type:

```java
import java.util.OptionalDouble;
import java.util.Random;
import java.util.function.DoubleSupplier;

public class OptionalExample {
    public static void main(String[] args) {
        OptionalDouble optionalDouble = OptionalDouble.empty();
        
        // Using orElseGet() with a DoubleSupplier
        double value = optionalDouble.orElseGet(() -> new Random().nextDouble());
        System.out.println("Value with orElseGet(): " + value);
    }
}
```

### Return type of max():

```java
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class OptionalExample {
    public static void main(String[] args) {
        DoubleStream doubleStream = DoubleStream.of(3.14, 2.71, 1.618);
        
        // Getting the maximum value as an OptionalDouble
        OptionalDouble max = doubleStream.max();
        System.out.println("Max value: " + max.getAsDouble());
    }
}
```

```java
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OptionalExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(42, 7, 99);
        
        // Getting the maximum value as an OptionalInt
        OptionalInt max = intStream.max();
        System.out.println("Max value: " + max.getAsInt());
    }
}
```

```java
import java.util.OptionalLong;
import java.util.stream.LongStream;

public class OptionalExample {
    public static void main(String[] args) {
        LongStream longStream = LongStream.of(1234567890L, 9876543210L, 5432109876L);
        
        // Getting the maximum value as an OptionalLong
        OptionalLong max = longStream.max();
        System.out.println("Max value: " + max.getAsLong());
    }
}
```

### Return type of sum():

```java
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class OptionalExample {
    public static void main(String[] args) {
        DoubleStream doubleStream = DoubleStream.of(3.14, 2.71, 1.618);
        
        // Getting the sum as a double
        double sum = doubleStream.sum();
        System.out.println("Sum: " + sum);
    }
}
```

```java
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OptionalExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(42, 7, 99);
        
        // Getting the sum as an int
        int sum = intStream.sum();
        System.out.println("Sum: " + sum);
    }
}
```

```java
import java.util.OptionalLong;
import java.util.stream.LongStream;

public class OptionalExample {
    public static void main(String[] args) {
        LongStream longStream = LongStream.of(1234567890L, 9876543210L, 5432109876L);
        
        // Getting the sum as a long
        long sum = longStream.sum();
        System.out.println("Sum: " + sum);
    }
}
```

### Return type of avg():

```java
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class OptionalExample {
    public static void main(String[] args) {
        DoubleStream doubleStream = DoubleStream.of(3.14, 2.71, 1.618);
        
        // Getting the average as an OptionalDouble
        OptionalDouble average = doubleStream.average();
        System.out.println("Average: " + average

.getAsDouble());
    }
}
```

```java
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class OptionalExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(42, 7, 99);
        
        // Getting the average as an OptionalDouble
        OptionalDouble average = intStream.average();
        System.out.println("Average: " + average.getAsDouble());
    }
}
```

```java
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public class OptionalExample {
    public static void main(String[] args) {
        LongStream longStream = LongStream.of(1234567890L, 9876543210L, 5432109876L);
        
        // Getting the average as an OptionalDouble
        OptionalDouble average = longStream.average();
        System.out.println("Average: " + average.getAsDouble());
    }
}
```

### Summarizing Statistics

Summarizing Statistics is a feature in Java that allows you to calculate various statistical values for a collection of elements, such as count, sum, min, max, and average. It's commonly used in conjunction with streams to efficiently compute these statistics on large datasets.

Java provides the `IntSummaryStatistics`, `LongSummaryStatistics`, and `DoubleSummaryStatistics` classes in the `java.util` package to facilitate this functionality. These classes provide methods to collect statistical data about a stream of primitive values.

Here's a brief overview of each class:

- `IntSummaryStatistics`: Collects statistics such as count, sum, min, max, and average for a stream of integer values.
- `LongSummaryStatistics`: Collects statistics such as count, sum, min, max, and average for a stream of long values.
- `DoubleSummaryStatistics`: Collects statistics such as count, sum, min, max, and average for a stream of double values.

You can use these classes along with streams to efficiently compute statistics without having to manually iterate over the elements of the stream.

Here's a basic example demonstrating the usage of `IntSummaryStatistics` to compute statistics for a stream of integers:

```java
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class StatisticsExample {
    public static void main(String[] args) {
        IntStream stream = IntStream.of(1, 2, 3, 4, 5);
        
        // Collect statistics for the stream
        IntSummaryStatistics stats = stream.summaryStatistics();
        
        // Print statistics
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
    }
}
```

In this example, we create an `IntStream` of integers and then use the `summaryStatistics()` method to obtain an `IntSummaryStatistics` object. We can then access various statistical values such as count, sum, min, max, and average using the methods provided by the `IntSummaryStatistics` class.

Overall, Summarizing Statistics provides a convenient way to compute statistical data for streams of primitive values in Java. It's particularly useful when dealing with large datasets where manual computation of statistics would be inefficient.

### Learning the Functional Interfaces for Primitives

#### Functional Interfaces for boolean

Functional interfaces for boolean in Java provide a way to define lambda expressions or method references that accept and return boolean values. These functional interfaces are part of the `java.util.function` package and are useful when working with boolean operations in functional-style programming or with streams.

Here are some commonly used functional interfaces for boolean:

1. **Predicate\<T>**: Represents a predicate (boolean-valued function) of one argument. It is typically used to filter elements based on a condition.

2. **BooleanSupplier**: Represents a supplier of boolean-valued results. It does not accept any arguments but provides a boolean result.

3. **BooleanUnaryOperator**: Represents an operation on a single boolean operand that produces a boolean result. It's similar to UnaryOperator\<Boolean>, but specialized for boolean values.

4. **BooleanBinaryOperator**: Represents an operation upon two boolean operands and produces a boolean result. It's similar to BinaryOperator\<Boolean>, but specialized for boolean values.

5. **Predicate\<T>**, **BooleanSupplier**, **BooleanUnaryOperator**, and **BooleanBinaryOperator** are the main functional interfaces for boolean in Java.

Here's a brief overview of each functional interface:

- **Predicate\<T>**:
  ```java
  @FunctionalInterface
  public interface Predicate<T> {
      boolean test(T t);
  }
  ```
  Example usage:
  ```java
  Predicate<Integer> isPositive = num -> num > 0;
  System.out.println(isPositive.test(5)); // Output: true
  ```

- **BooleanSupplier**:
  ```java
  @FunctionalInterface
  public interface BooleanSupplier {
      boolean getAsBoolean();
  }
  ```
  Example usage:
  ```java
  BooleanSupplier alwaysTrue = () -> true;
  System.out.println(alwaysTrue.getAsBoolean()); // Output: true
  ```

- **BooleanUnaryOperator**:
  ```java
  @FunctionalInterface
  public interface BooleanUnaryOperator {
      boolean applyAsBoolean(boolean operand);
  }
  ```
  Example usage:
  ```java
  BooleanUnaryOperator negate = bool -> !bool;
  System.out.println(negate.applyAsBoolean(true)); // Output: false
  ```

- **BooleanBinaryOperator**:
  ```java
  @FunctionalInterface
  public interface BooleanBinaryOperator {
      boolean applyAsBoolean(boolean left, boolean right);
  }
  ```
  Example usage:
  ```java
  BooleanBinaryOperator and = (left, right) -> left && right;
  System.out.println(and.applyAsBoolean(true, false)); // Output: false
  ```

These functional interfaces provide a way to work with boolean values in a functional programming style, making your code more concise and expressive. They are especially useful when combined with streams or when defining complex boolean operations.

#### Functional Interfaces for double, int, and long

Functional interfaces for double, int, and long in Java are similar to functional interfaces for boolean values but are specialized for primitive numeric types. These interfaces provide a way to define lambda expressions or method references that accept and return primitive double, int, or long values. They are part of the `java.util.function` package and are commonly used when working with primitive numeric types in functional-style programming or with streams.

Here are some commonly used functional interfaces for primitive numeric types:

1. **DoublePredicate**: Represents a predicate (boolean-valued function) of one double argument.

2. **IntPredicate**: Represents a predicate (boolean-valued function) of one int argument.

3. **LongPredicate**: Represents a predicate (boolean-valued function) of one long argument.

4. **DoubleSupplier**: Represents a supplier of double-valued results.

5. **IntSupplier**: Represents a supplier of int-valued results.

6. **LongSupplier**: Represents a supplier of long-valued results.

7. **DoubleUnaryOperator**: Represents an operation on a single double operand that produces a double result.

8. **IntUnaryOperator**: Represents an operation on a single int operand that produces an int result.

9. **LongUnaryOperator**: Represents an operation on a single long operand that produces a long result.

10. **DoubleBinaryOperator**: Represents an operation upon two double operands and produces a double result.

11. **IntBinaryOperator**: Represents an operation upon two int operands and produces an int result.

12. **LongBinaryOperator**: Represents an operation upon two long operands and produces a long result.

These functional interfaces provide a way to work with primitive numeric types in a functional programming style, making your code more concise and expressive. They are especially useful when combined with streams or when defining complex numeric operations.

Here's a brief overview of each functional interface:

- **DoublePredicate**:
  ```java
  @FunctionalInterface
  public interface DoublePredicate {
      boolean test(double value);
  }
  ```

- **IntPredicate**:
  ```java
  @FunctionalInterface
  public interface IntPredicate {
      boolean test(int value);
  }
  ```

- **LongPredicate**:
  ```java
  @FunctionalInterface
  public interface LongPredicate {
      boolean test(long value);
  }
  ```

- **DoubleSupplier**:
  ```java
  @FunctionalInterface
  public interface DoubleSupplier {
      double getAsDouble();
  }
  ```

- **IntSupplier**:
  ```java
  @FunctionalInterface
  public interface IntSupplier {
      int getAsInt();
  }
  ```

- **LongSupplier**:
  ```java
  @FunctionalInterface
  public interface LongSupplier {
      long getAsLong();
  }
  ```

- **DoubleUnaryOperator**:
  ```java
  @FunctionalInterface
  public interface DoubleUnaryOperator {
      double applyAsDouble(double operand);
  }
  ```

- **IntUnaryOperator**:
  ```java
  @FunctionalInterface
  public interface IntUnaryOperator {
      int applyAsInt(int operand);
  }
  ```

- **LongUnaryOperator**:
  ```java
  @FunctionalInterface
  public interface LongUnaryOperator {
      long applyAsLong(long operand);
  }
  ```

- **DoubleBinaryOperator**:
  ```java
  @FunctionalInterface
  public interface DoubleBinaryOperator {
      double applyAsDouble(double left, double right);
  }
  ```

- **IntBinaryOperator**:
  ```java
  @FunctionalInterface
  public interface IntBinaryOperator {
      int applyAsInt(int left, int right);
  }
  ```

- **LongBinaryOperator**:
  ```java
  @FunctionalInterface
  public interface LongBinaryOperator {
      long applyAsLong(long left, long right);
  }
  ```

These functional interfaces provide a wide range of operations for working with primitive numeric types in Java, making your code more flexible and efficient when dealing with numeric data.

## Working with Advanced Stream Pipeline Concepts

### Linking Streams to the Underlying Data

In this example, we have a list of strings named `cats` initially containing two elements, "Annie" and "Ripley". We create a stream from this list using the `stream()` method provided by the `Collection` interface. Then, we add another element "KC" to the `cats` list.

Here's the code:

```java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> cats = new ArrayList<>();
        cats.add("Annie");
        cats.add("Ripley");

        // Create a stream from the list
        Stream<String> stream = cats.stream();

        // Add another element to the list
        cats.add("KC");

        // Print the count of elements in the stream
        System.out.println(stream.count()); // Output: 3
    }
}
```

When we call `stream.count()`, it triggers the processing of the stream and calculates the number of elements in the stream. Despite adding "KC" to the `cats` list after creating the stream, the stream still includes all elements, including "KC", because the stream maintains a connection to the underlying list. Therefore, the output is `3`, indicating that there are three elements in the stream ("Annie", "Ripley", and "KC").

### Chaining Optionals

Chaining Optionals with streams involves using Optional-returning methods in stream operations. This allows you to handle situations where stream operations might result in empty streams or null values gracefully. The idea is to combine the flexibility of streams with the safety features provided by Optional.

Here's how you can chain Optionals with streams:

1. **Mapping Stream Elements to Optionals**: Use `map` or `flatMap` to transform stream elements into Optionals. With `map`, you'll end up with a stream of Optionals, while `flatMap` allows you to flatten the stream of Optionals into a single stream.

2. **Filtering and Processing Optionals**: After mapping elements to Optionals, you can filter out empty Optionals using `filter` and then perform further processing using methods like `ifPresent`, `orElse`, or `orElseThrow`.

3. **Handling Absent Values**: If the stream produces no elements or the mapped operation returns empty Optionals, you can use `orElse`, `orElseGet`, or `orElseThrow` to provide default values or handle exceptions.

Here's a basic example demonstrating chaining Optionals with streams:

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave", "Eva", "Frank", "Grace");

        // Transform names to Optionals and filter out names starting with 'C'
        names.stream()
             .map(name -> name.startsWith("C") ? Optional.of(name) : Optional.empty())
             .filter(Optional::isPresent)
             .map(Optional::get)
             .forEach(System.out::println); // Output: Charlie

        // Transform names to uppercase Optionals and provide default value if empty
        List<String> emptyList = Arrays.asList();
        String result = emptyList.stream()
                                 .map(name -> Optional.ofNullable(name).map(String::toUpperCase))
                                 .flatMap(Optional::stream)
                                 .findFirst()
                                 .orElse("No names available");
        System.out.println("Result: " + result); // Output: Result: No names available
    }
}
```

In this example:

- We transform names from a list to Optionals based on a condition (starting with 'C') and filter out empty Optionals.
- We then extract the values from the Optionals using `get` and print them.
- Next, we transform names to uppercase Optionals, flatten the stream of Optionals using `flatMap`, and provide a default value if the stream is empty using `orElse`.

### Collecting Results

#### Collecting Using Basic Collectors

#### Collecting into Maps

Introducing collecting into maps while working with Java streams is a common requirement when you need to transform and organize data into a map structure. Java provides the `Collectors.toMap()` method for this purpose. Here's how you can use it:

```java
import java.util.*;
import java.util.stream.Collectors;

public class MapCollectingExample {
    public static void main(String[] args) {
        // Sample list of objects
        List<Person> people = Arrays.asList(
                new Person("John", 25),
                new Person("Alice", 30),
                new Person("Bob", 35)
        );

        // Collecting names of people into a Map with name as key and age as value
        Map<String, Integer> nameToAgeMap = people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));

        // Displaying the map
        System.out.println("Name to Age Map: " + nameToAgeMap);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

In this example:

1. We have a `Person` class with attributes `name` and `age`.
2. We create a list of `Person` objects.
3. We then use Java streams on this list, calling `collect()` with `Collectors.toMap()` to collect the elements of the stream into a map.
4. In `toMap()`, we specify two functions: one for extracting keys (names) and another for extracting values (ages) from `Person` objects.

Make sure that the keys you are extracting are unique, otherwise, you may encounter an `IllegalStateException` due to duplicate keys when collecting to a map.

#### Collecting Using Grouping, Partitioning, and Mapping 

**Grouping**

Certainly! In Java streams, grouping is a technique that allows you to categorize elements of a stream based on certain criteria. It's a fundamental operation provided by the `Collectors` class, particularly through the `groupingBy` collector.

Here's the primary signature of `groupingBy`:

```java
public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
```

This signature indicates that `groupingBy` takes a classification function (`Function<? super T, ? extends K> classifier`) which maps elements of type `T` to keys of type `K`. It then collects the elements into a `Map` where the keys are the result of applying the classification function and the values are lists containing the elements that share the same key.

Here's a code example demonstrating the usage of grouping in Java streams:

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry", "cherry", "blackberry");

        // Group words by their first letter
        Map<Character, List<String>> groupedWords = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0)));

        // Print the result
        groupedWords.forEach((letter, wordList) -> {
            System.out.println("Words starting with '" + letter + "':");
            wordList.forEach(System.out::println);
            System.out.println(); // Just for better readability
        });
    }
}
```

Output:
```
Words starting with 'a':
apple
apricot

Words starting with 'b':
banana
blueberry
blackberry

Words starting with 'c':
cherry
```

In this example, we have a list of words, and we want to group them based on their first letter. We achieve this by calling `collect(Collectors.groupingBy(word -> word.charAt(0)))`. This groups the words by their first letter, resulting in a map where the keys are characters (first letters) and the values are lists of words starting with the corresponding letter.

This illustrates how to use grouping in Java streams to categorize elements based on certain criteria.

**Partitioning**

Certainly! In Java streams, partitioning is a special case of grouping where the result is a map with only two possible keys: true and false. It's used to partition elements of a stream into two groups based on a predicate. The `Collectors.partitioningBy()` method is used for this purpose.

Here's the primary signature of `partitioningBy`:

```java
public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate)
```

This signature indicates that `partitioningBy` takes a predicate (`Predicate<? super T> predicate`) which tests each element of the stream. It then collects the elements into a map where the keys are Boolean values (`true` or `false`) representing whether the element satisfies the predicate, and the values are lists containing the elements that pass (`true`) or fail (`false`) the predicate.

Here's a code example demonstrating the usage of partitioning in Java streams:

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Partition numbers into even and odd
        Map<Boolean, List<Integer>> evenOddPartition = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        // Print the result
        List<Integer> evenNumbers = evenOddPartition.get(true);
        List<Integer> oddNumbers = evenOddPartition.get(false);

        System.out.println("Even numbers: " + evenNumbers);
        System.out.println("Odd numbers: " + oddNumbers);
    }
}
```

Output:
```
Even numbers: [2, 4, 6, 8, 10]
Odd numbers: [1, 3, 5, 7, 9]
```

In this example, we have a list of numbers, and we want to partition them into two groups: even and odd. We achieve this by calling `collect(Collectors.partitioningBy(n -> n % 2 == 0))`. This partitions the numbers into two groups based on whether they are even (`true`) or odd (`false`).

This illustrates how to use partitioning in Java streams to split elements into two groups based on a predicate.

**Mapping**

In Java streams, mapping is a powerful operation that allows you to transform elements of a stream using a mapping function. It's often used to convert elements from one type to another or to extract certain properties from objects in the stream. The `Collectors.mapping()` method is used for this purpose.

Here's the primary signature of `mapping`:

```java
public static <T, U, A, R> Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper, Collector<? super U, A, R> downstream)
```

This signature indicates that `mapping` takes two parameters: a mapping function (`Function<? super T, ? extends U> mapper`) and a downstream collector (`Collector<? super U, A, R> downstream`). It applies the mapping function to each element of the stream, collects the mapped elements using the downstream collector, and returns the result.

Here's a code example demonstrating the usage of mapping in Java streams:

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MappingExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry", "cherry");

        // Map words to their lengths
        List<Integer> wordLengths = words.stream()
                .collect(Collectors.mapping(String::length, Collectors.toList()));

        // Print the result
        System.out.println("Lengths of words: " + wordLengths);
    }
}
```

Output:
```
Lengths of words: [5, 6, 7, 9, 6]
```

In this example, we have a list of words, and we want to map each word to its length. We achieve this by calling `collect(Collectors.mapping(String::length, Collectors.toList()))`. This applies the mapping function `String::length` to each word in the stream, collecting the lengths into a list.

This illustrates how to use mapping in Java streams to transform elements using a mapping function.

# Summary

### Lambdas in Java

Lambdas in Java are anonymous functions that can be used to implement functional interfaces concisely. They can reference:
- **Static variables**: Variables that belong to the class itself.
- **Instance variables**: Variables that belong to an instance of a class.
- **Effectively final parameters**: Parameters that are not explicitly declared final but are treated as final because they are not reassigned.
- **Effectively final local variables**: Local variables declared within a method or lambda expression that are not explicitly declared final but are treated as final because they are not reassigned.

### Functional Interfaces

A functional interface is an interface that contains only one abstract method. In Java, lambdas can be used to implement functional interfaces. Here are some commonly used functional interfaces in Java:

- **Supplier\<T>**: Represents a supplier of results. Method `T get()` returns a value of type T.
- **Consumer\<T>**: Represents an operation that accepts a single input argument and returns no result. Method `void accept(T t)` is used.
- **BiConsumer\<T, U>**: Represents an operation that accepts two input arguments and returns no result. Method `void accept(T t, U u)` is used.
- **Predicate\<T>**: Represents a predicate (boolean-valued function) of one argument. Method `boolean test(T t)` returns a boolean value.
- **BiPredicate\<T, U>**: Represents a predicate (boolean-valued function) of two arguments. Method `boolean test(T t, U u)` returns a boolean value.
- **Function\<T, R>**: Represents a function that accepts one argument and produces a result. Method `R apply(T t)` is used.
- **BiFunction\<T, U, R>**: Represents a function that accepts two arguments and produces a result. Method `R apply(T t, U u)` is used.
- **UnaryOperator\<T>**: Represents an operation on a single operand that produces a result of the same type as its operand. Method `T apply(T t)` is used.
- **BinaryOperator\<T>**: Represents an operation upon two operands of the same type, producing a result of the same type as the operands. Method `T apply(T t1, T t2)` is used.

These interfaces enable functional programming constructs in Java, allowing concise and expressive code through lambda expressions.
eel free to adjust the presentation according to your specific needs!

### Optional in Java

Optional is a container object that may or may not contain a non-null value. It provides methods to check and retrieve the value safely. Key features include:

- **Presence Checking**: You can check if an Optional contains a value using methods like `ifPresent()` or `isPresent()`.
- **Value Retrieval**: You can retrieve the value inside an Optional using methods like `get()` (be cautious as it may throw `NoSuchElementException` if the Optional is empty).
- **Functional Interface Parameters**: Optional provides methods that take functional interfaces as parameters:
  - `ifPresent(Consumer<T> consumer)`: Executes the consumer if a value is present.
  - `orElseGet(Supplier<? extends T> supplier)`: Returns the value if present, otherwise returns the result of the supplier.
  - `orElseThrow(Supplier<? extends X> exceptionSupplier)`: Returns the value if present, otherwise throws an exception produced by the supplier.

### Optional Types for Primitives

For primitive types, Java provides optional counterparts that avoid boxing and unboxing overhead. These include:
- **DoubleSupplier**: Provides an optional double value. Method `double getDouble()` is used.
- **IntSupplier**: Provides an optional int value. Method `int getInt()` is used.
- **LongSupplier**: Provides an optional long value. Method `long getLong()` is used.

These optional types for primitives allow safer handling of primitive values in situations where absence is a valid state.

### Stream Pipeline Components

A stream pipeline in Java consists of three main parts:

1. **Source**: The source is required and it creates the data in the stream. It can be a collection, an array, an I/O channel, or even a generator function. Examples include:
  - `Collection.stream()`
  - `Stream.of(T...)`
  - `Files.lines(Path)`

2. **Intermediate Operations**: These are optional operations that transform the elements of the stream. They are not executed until a terminal operation is called. Examples include:
  - `filter(Predicate)`
  - `map(Function)`
  - `flatMap(Function)`
  - `sorted(Comparator)`

3. **Terminal Operations**: These operations produce a result or side-effect and terminate the stream. They trigger the execution of intermediate operations. Examples include:
  - `forEach(Consumer)`
  - `count()`
  - `collect(Collectors.toList())`
  - `anyMatch(Predicate)`

The source creates the data, intermediate operations transform it, and terminal operations produce a result or side-effect.

### Primitive Streams in Java

Java provides primitive streams specialized for double, int, and long data types. These include:

- **DoubleStream**: A stream of double values.
- **IntStream**: A stream of int values.
- **LongStream**: A stream of long values.

### Additional Methods

In addition to the methods available in the Stream interface, primitive streams have some additional methods:

- **range(int startInclusive, int endExclusive)**: Creates a sequential IntStream with elements from the start (inclusive) to the end (exclusive).
- **rangeClosed(int startInclusive, int endInclusive)**: Creates a sequential IntStream with elements from the start (inclusive) to the end (inclusive).
- **average()**: Computes the average of the elements.
- **max()**: Finds the maximum element.
- **min()**: Finds the minimum element.
- **sum()**: Computes the sum of the elements.
- **summaryStatistics()**: Computes various summary statistics (count, sum, min, average, max) in one call.

### Functional Interfaces

Java also provides functional interfaces specific to primitive streams, suitable for operations involving double, int, and long primitives. These include:

- **DoubleSupplier**
- **IntSupplier**
- **LongSupplier**

These interfaces allow for more efficient and specialized processing when working with primitive streams.

### Collectors in Java Streams

In Java streams, a Collector is used to transform the elements of a stream into a traditional collection, such as a List, Set, or Map. Collectors provide various methods to perform aggregation operations on the elements of a stream.

### Grouping and Partitioning with Collectors

- **Grouping**: The `groupingBy` collector allows you to group elements of a stream based on certain criteria, creating a map where keys are derived from the elements and values are lists of elements sharing the same key.

- **Partitioning**: The `partitioningBy` collector is similar to grouping, but it always partitions the stream into two groups based on a predicate, resulting in a map with keys `true` and `false`.

### Characteristics

- **Keys in Partitioning**: Partitioning always results in a map with two keys (`true` and `false`), even if one of the partitions is empty.

- **Complex Mapping**: Collectors can be used to create complex maps in a single line, allowing you to group fields or perform custom transformations easily.

Absolutely, reviewing and understanding the tables in the chapter is essential for mastering Java streams. Here's a summary focusing on the key tables mentioned:

### Memorization Guide:

1. **Table 4.1**: This table likely covers the functional interfaces and their associated methods, including:

  - Supplier<T>
  - Consumer<T>
  - BiConsumer<T, U>
  - Predicate<T>
  - BiPredicate<T, U>
  - Function<T, R>
  - BiFunction<T, U, R>
  - UnaryOperator<T>
  - BinaryOperator<T>

   Understanding these interfaces and their method signatures is crucial for working effectively with Java streams.

2. **Table 4.6 and Table 4.7**: These tables cover the primitive streams and their associated methods, such as:

  - DoubleStream: Methods like `range()`, `rangeClosed()`, `average()`, `max()`, `min()`, `sum()`, `summaryStatistics()`.
  - IntStream: Similar methods as DoubleStream but tailored for int values.
  - LongStream: Similar methods as DoubleStream but tailored for long values.

   Being familiar with these tables, especially the methods and their functionalities, is key.

### Understanding Laziness:

Remembering that streams are lazily evaluated is crucial. This means that intermediate operations are not executed until a terminal operation is called. Also, method references or lambdas passed to stream methods are not executed immediately but only when the terminal operation triggers the stream processing.

### Conclusion:

While memorizing the tables is important, understanding their contents and implications is equally essential. Additionally, grasping the concept of laziness in stream evaluation helps in writing efficient and concise code.

By focusing on understanding and applying the concepts from these tables, you'll be well-equipped to work effectively with Java streams.

# Exam Essentials

**Identify the correct functional interface given the number of parameters, return type, and method name—and vice versa**

1.**Supplier**:
  - Number of parameters: 0
  - Return type: T
  - Typical method name: `get()`

2. **Consumer**:
  - Number of parameters: 1
  - Return type: void
  - Typical method name: `accept(T)`

3. **BiConsumer**:
  - Number of parameters: 2
  - Return type: void
  - Typical method name: `accept(T, U)`

4. **Function**:
  - Number of parameters: 1
  - Return type: R
  - Typical method name: `apply(T)`

5. **BiFunction**:
  - Number of parameters: 2
  - Return type: R
  - Typical method name: `apply(T, U)`

6. **Predicate**:
  - Number of parameters: 1
  - Return type: boolean
  - Typical method name: `test(T)`

7. **BiPredicate**:
  - Number of parameters: 2
  - Return type: boolean
  - Typical method name: `test(T, U)`

These are the most common functional interfaces used in Java, and they have corresponding binary versions (e.g., `BiConsumer`, `BiFunction`, `BiPredicate`) for operations involving two parameters.

Additionally, there are primitive versions for operations involving primitive types, such as `IntSupplier`, `DoubleConsumer`, `LongFunction`, etc.

Understanding these interfaces and their method signatures is crucial for effectively working with Java streams and functional programming constructs.

**Write code that uses Optional**

Certainly! Here's an example demonstrating the usage of `Optional` in Java:

```java
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        // Creating an Optional with a non-null value
        Optional<String> optionalValue = Optional.of("Hello, world!");

        // Retrieval using ifPresent() method
        optionalValue.ifPresent(value -> System.out.println("Value present: " + value));

        // Retrieval using get() method (caution: throws NoSuchElementException if value is not present)
        String retrievedValue = optionalValue.get();
        System.out.println("Retrieved value: " + retrievedValue);

        // Creating an Optional with an empty value
        Optional<String> emptyOptional = Optional.empty();

        // Retrieval using orElseGet() method (provides a default value if the Optional is empty)
        String orElseValue = emptyOptional.orElseGet(() -> "Default Value");
        System.out.println("Value retrieved using orElseGet(): " + orElseValue);
    }
}
```

In this example:
- We create an `Optional` with a non-null value using `Optional.of("Hello, world!")`.
- We then demonstrate retrieval of the value using `ifPresent()` and `get()` methods.
- We also create an empty `Optional` using `Optional.empty()`.
- Finally, we demonstrate retrieval using the `orElseGet()` method, which provides a default value if the `Optional` is empty.

Make sure to handle `NoSuchElementException` appropriately when using `get()` method, or prefer using `ifPresent()` or `orElse*()` methods to avoid such exceptions.

**Recognize which operations cause a stream pipeline to execute**

Absolutely, recognizing the operations that cause a stream pipeline to execute is crucial for understanding how Java streams work efficiently. Here's a breakdown:

### Stream Execution in Java:

1. **Intermediate Operations**:
  - Intermediate operations, such as `filter()`, `map()`, `flatMap()`, and `sorted()`, do not execute immediately.
  - They are lazy, meaning they are queued up but not performed until a terminal operation is encountered.

2. **Terminal Operations**:
  - Terminal operations are the ones that trigger the execution of the entire stream pipeline.
  - Examples include:
    - `collect()`: Collects the elements of the stream into a collection or other data structure.
    - `forEach()`: Performs an action for each element of the stream.
    - `min()`, `max()`: Finds the minimum or maximum element in the stream according to a comparator.
    - `reduce()`: Performs a reduction on the elements of the stream.

3. **No Terminal Operation**:
  - If there's no terminal operation in the stream pipeline, no processing will occur.
  - The intermediate operations will not be executed, and the stream will remain untouched until a terminal operation is encountered.

### Example:

```java
List<String> words = Arrays.asList("apple", "banana", "cherry");

// Intermediate operation (filter)
Stream<String> filteredStream = words.stream().filter(word -> word.startsWith("a"));

// No processing yet, as there's no terminal operation

// Terminal operation (forEach)
filteredStream.forEach(System.out::println); // Execution happens here
```

In this example, the intermediate operation `filter()` is performed lazily, and the stream remains untouched until the terminal operation `forEach()` is encountered, triggering the execution of the pipeline.

Understanding the distinction between intermediate and terminal operations helps in writing efficient and concise stream pipelines in Java.

**Determine which terminal operations are reductions**

Absolutely! Terminal operations that are reductions are those that combine all elements of the stream to produce a result. Here's a breakdown of the reductions you mentioned:

### Reductions in Java Streams:

1. **collect()**:
  - The `collect()` method is a terminal operation that accumulates the elements of the stream into a mutable result container, such as a Collection or a StringBuilder.

2. **count()**:
  - The `count()` method counts the number of elements in the stream. It's also a reduction operation because it combines all elements to determine the count.

3. **max()**:
  - The `max()` method finds the maximum element in the stream according to the natural order defined by the elements' compareTo method or a comparator. It's a reduction operation as it combines all elements to find the maximum.

4. **min()**:
  - The `min()` method finds the minimum element in the stream according to the natural order defined by the elements' compareTo method or a comparator. Like `max()`, it's also a reduction operation.

5. **reduce()**:
  - The `reduce()` method performs a reduction on the elements of the stream using an associative accumulation function and returns an `Optional` describing the reduced value, if any.

### Mutable Reductions:

- A mutable reduction collects elements into a mutable result container as it goes. The `collect()` method is an example of a mutable reduction, where you accumulate elements into a Collection or other mutable data structure.

### Conclusion:

Understanding which terminal operations are reductions helps in recognizing when stream processing involves combining elements to produce a result. Whether it's counting elements, finding maximum or minimum values, or performing custom reductions, knowing these operations is crucial for effective stream processing in Java.

**Write code for common intermediate operations.**

Sure, here's how you can use the `filter()`, `map()`, and `flatMap()` methods in Java streams:

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperationsExample {
    public static void main(String[] args) {
        // Example data
        List<String> words = Arrays.asList("apple", "banana", "cherry", "kiwi", "orange");
        List<List<String>> nestedLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("cherry", "kiwi"),
                Arrays.asList("orange"),
                Arrays.asList(),
                Arrays.asList("grape")
        );

        // filter(): Filter words starting with 'a'
        List<String> filteredWords = words.stream()
                .filter(word -> word.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println("Filtered words: " + filteredWords);

        // map(): Convert words to uppercase
        List<String> uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase words: " + uppercaseWords);

        // flatMap(): Flatten nested lists and remove empty lists
        List<String> flattenedList = nestedLists.stream()
                .flatMap(List::stream)
                .filter(word -> !word.isEmpty()) // Remove empty strings
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattenedList);
    }
}
```

In this example:
- The `filter()` method is used to filter words starting with 'a'.
- The `map()` method is used to convert each word to uppercase.
- The `flatMap()` method is used to flatten nested lists into a single level and remove empty lists.

You can run this code to see the results of applying these intermediate operations to the example data.

**Compare primitive streams to Stream.**

Certainly! Here's a comparison between primitive streams (DoubleStream, IntStream, LongStream) and the general-purpose Stream class, along with their corresponding primitive Optional classes and functional interfaces:

### Primitive Streams vs. Stream:

1. **Primitive Streams (DoubleStream, IntStream, LongStream)**:
  - Specialized streams for handling primitive data types: double, int, and long.
  - Provide specialized methods for working with primitive data efficiently.
  - Offer methods like `sum()`, `average()`, `max()`, `min()`, etc., tailored for primitive types.

2. **Stream**:
  - General-purpose stream for handling objects of any reference type.
  - Can be used with any Java object, including user-defined classes.
  - Offers a wide range of intermediate and terminal operations for processing data in a flexible manner.

### Primitive Optional Classes:

1. **OptionalDouble**:
  - Represents an optional double value.
  - Provides methods like `getAsDouble()` to retrieve the value safely.

2. **OptionalInt**:
  - Represents an optional int value.
  - Provides methods like `getAsInt()` to retrieve the value safely.

3. **OptionalLong**:
  - Represents an optional long value.
  - Provides methods like `getAsLong()` to retrieve the value safely.

### Functional Interfaces for Primitives:

1. **DoubleSupplier, IntSupplier, LongSupplier**:
  - Functional interfaces for supplying double, int, and long values, respectively.
  - They are used for operations that produce primitive values.

2. **DoubleConsumer, IntConsumer, LongConsumer**:
  - Functional interfaces for consuming double, int, and long values, respectively.
  - They are used for operations that consume primitive values.

3. **DoubleFunction<R>, IntFunction<R>, LongFunction<R>**:
  - Functional interfaces for functions that accept a primitive value and return a result of type R.

4. **DoubleUnaryOperator, IntUnaryOperator, LongUnaryOperator**:
  - Functional interfaces for unary operations on double, int, and long values, respectively.

5. **DoubleBinaryOperator, IntBinaryOperator, LongBinaryOperator**:
  - Functional interfaces for binary operations on double, int, and long values, respectively.

### Conclusion:

Primitive streams and Optional classes provide specialized support for primitive data types, offering performance benefits and convenience when working with primitive values. Functional interfaces tailored for primitive types allow for efficient and concise functional programming with primitive streams in Java.

**Convert primitive stream types to other primitive stream types.**

Exactly! Here's a breakdown of how you can convert between primitive stream types in Java:

### Converting Primitive Stream Types:

1. **To Stream**:
  - To convert a primitive stream (DoubleStream, IntStream, LongStream) to a Stream of boxed elements, you can use the `mapToObj()` method.

2. **To DoubleStream**:
  - To convert a stream of primitive type (e.g., Stream<Integer>, IntStream, LongStream) to a DoubleStream, you can use the `mapToDouble()` method.

3. **To IntStream**:
  - To convert a stream of primitive type (e.g., Stream<Double>, DoubleStream, LongStream) to an IntStream, you can use the `mapToInt()` method.

4. **To LongStream**:
  - To convert a stream of primitive type (e.g., Stream<Integer>, IntStream, DoubleStream) to a LongStream, you can use the `mapToLong()` method.

### Example:

Here's a code example demonstrating the conversion between different primitive stream types:

```java
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreamConversionExample {
    public static void main(String[] args) {
        // Example 1: Converting to Stream
        DoubleStream doubleStream = DoubleStream.of(1.2, 2.3, 3.4);
        Stream<Double> boxedStream = doubleStream.mapToObj(Double::valueOf);

        // Example 2: Converting to DoubleStream
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        DoubleStream doubleStreamFromInt = integerStream.mapToDouble(Integer::doubleValue);

        // Example 3: Converting to IntStream
        LongStream longStream = LongStream.of(10L, 20L, 30L);
        IntStream intStreamFromLong = longStream.mapToInt(Math::toIntExact);

        // Example 4: Converting to LongStream
        Stream<String> stringStream = Stream.of("100", "200", "300");
        LongStream longStreamFromString = stringStream.mapToLong(Long::parseLong);
    }
}
```

In this example:
- We demonstrate how to convert between different primitive stream types using `mapToObj()`, `mapToDouble()`, `mapToInt()`, and `mapToLong()` methods.
- Each conversion method is used to transform elements of one primitive stream type into another primitive stream type.

These conversion methods provide flexibility and convenience when working with different primitive data types in Java streams.

**Translate coding using method references into lambdas and vice versa.**

Absolutely! Here's a guide on how to translate between method references and lambdas in Java:

### Method References to Lambdas:

1. **Static Method Reference**:
  - Method Reference: `ClassName::staticMethodName`
  - Equivalent Lambda: `(args) -> ClassName.staticMethodName(args)`

2. **Instance Method Reference on Object**:
  - Method Reference: `object::instanceMethodName`
  - Equivalent Lambda: `(args) -> object.instanceMethodName(args)`

3. **Instance Method Reference on Class Type**:
  - Method Reference: `ClassName::instanceMethodName`
  - Equivalent Lambda: `(obj, args) -> obj.instanceMethodName(args)`

### Lambdas to Method References:

Not all lambdas can be rewritten using method references. However, for simple cases where the lambda body just calls a method with the same parameters, you can usually rewrite it as a method reference.

For example:
- Lambda: `(args) -> System.out.println(args)`
- Equivalent Method Reference: `System.out::println`

### Example:

Here's a translation example of converting method references to lambdas and vice versa:

```java
import java.util.Arrays;
import java.util.List;

public class MethodReferenceAndLambdaExample {
    public static void main(String[] args) {
        // Method reference to lambda
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        words.forEach(System.out::println); // Method reference
        words.forEach(word -> System.out.println(word)); // Equivalent lambda

        // Lambda to method reference
        words.forEach(word -> System.out.println(word)); // Lambda
        words.forEach(System.out::println); // Equivalent method reference
    }
}
```

In this example:
- The first part demonstrates translating a method reference to a lambda.
- The second part demonstrates translating a lambda to a method reference.

Both methods achieve the same result but showcase different syntax styles. It's essential to understand both to write concise and readable code in Java.

**Use peek() to inspect the stream.**

Certainly! Here's how you can use the `peek()` method to inspect elements as they flow through a stream:

```java
import java.util.stream.Stream;

public class PeekExample {
    public static void main(String[] args) {
        // Example stream of integers
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        // Use peek() to inspect elements before they pass to the next operation
        numbers
            .peek(num -> System.out.println("Inspecting element: " + num)) // Print each element
            .map(num -> num * 2) // Perform some mapping operation
            .forEach(System.out::println); // Print the mapped elements
    }
}
```

In this example:
- We create a stream of integers using `Stream.of()`.
- We use the `peek()` method to inspect each element as it passes through the stream pipeline. The lambda inside `peek()` prints each element before it undergoes any further processing.
- After `peek()`, we perform a mapping operation using `map()` to double each element.
- Finally, we use `forEach()` to print the mapped elements after the `peek()` operation.

The `peek()` method allows us to observe the elements in the stream without modifying them. It's particularly useful for debugging and understanding how elements are processed in a stream pipeline.

**Search a stream.**

Certainly! Here's how you can search a stream using the mentioned methods:

### Finding Elements:

1. **findFirst()**:
  - Returns the first element of the stream, wrapped in an Optional.
  - Useful for streams where order matters.

2. **findAny()**:
  - Returns any element of the stream, wrapped in an Optional.
  - Useful for parallel streams or when order doesn't matter.

### Matching Elements:

1. **anyMatch(Predicate)**:
  - Returns true if any element of the stream matches the given predicate, false otherwise.
  - Short-circuits and stops processing once a matching element is found.

2. **allMatch(Predicate)**:
  - Returns true if all elements of the stream match the given predicate, false otherwise.
  - Stops processing and returns false as soon as any element doesn't match the predicate.

3. **noneMatch(Predicate)**:
  - Returns true if none of the elements of the stream match the given predicate, false otherwise.
  - Short-circuits and stops processing once a matching element is found.

### Caution:

- **Infinite Streams**: Be cautious when using `anyMatch()`, `allMatch()`, and `noneMatch()` with infinite streams. They may hang if there's no match in an infinite stream with some data, as they need to evaluate the entire stream to determine the result.
- **Terminal Operations**: These methods are terminal operations, meaning they trigger the execution of the stream pipeline.

### Example:

```java
import java.util.Optional;
import java.util.stream.Stream;

public class StreamSearchExample {
    public static void main(String[] args) {
        // Example stream
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        // Finding elements
        Optional<Integer> firstElement = numbers.findFirst();
        System.out.println("First element: " + firstElement.orElse(null));

        // Matching elements
        boolean anyMatch = Stream.of(1, 2, 3, 4, 5).anyMatch(num -> num == 3);
        System.out.println("Any match: " + anyMatch);

        boolean allMatch = Stream.of(1, 2, 3, 4, 5).allMatch(num -> num > 0);
        System.out.println("All match: " + allMatch);

        boolean noneMatch = Stream.of(1, 2, 3, 4, 5).noneMatch(num -> num == 6);
        System.out.println("None match: " + noneMatch);
    }
}
```

In this example:
- We use `findFirst()` to find the first element of the stream.
- We use `anyMatch()`, `allMatch()`, and `noneMatch()` to check for matching conditions in the stream.
- Each of these methods returns a boolean or an Optional containing the found element, depending on the method used.

**Sort a stream.**

Certainly! Here's how you can sort a stream using the `sorted()` method in Java:

### Sorting a Stream:

1. **Natural Sort Order**:
  - Use the `sorted()` method without any parameters to sort the stream using the natural sort order.
  - Elements are sorted according to their natural order, e.g., for numbers, it's in ascending order, and for strings, it's in lexicographical order.

2. **Custom Sort Order**:
  - Use the `sorted(Comparator)` method with a comparator as a parameter to specify a custom sort order.
  - Elements are sorted according to the rules defined by the provided comparator.

### Example:

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSortingExample {
    public static void main(String[] args) {
        // Example list of strings
        List<String> words = Arrays.asList("banana", "apple", "orange", "grape", "cherry");

        // Sorting using natural sort order
        List<String> naturalSorted = words.stream()
                .sorted() // Natural sort order
                .collect(Collectors.toList());
        System.out.println("Natural sort order: " + naturalSorted);

        // Sorting using custom sort order (length)
        List<String> lengthSorted = words.stream()
                .sorted(Comparator.comparing(String::length)) // Custom sort order based on length
                .collect(Collectors.toList());
        System.out.println("Sorted by length: " + lengthSorted);
    }
}
```

In this example:
- We have a list of strings (`words`) that we want to sort.
- We use the `sorted()` method without parameters to sort the stream using the natural sort order, resulting in a list sorted alphabetically.
- We use the `sorted(Comparator)` method with a comparator that compares the length of strings to sort the stream based on string length, resulting in a list sorted by length.

Both methods return a sorted stream, which we then collect into a list using the `collect()` method.