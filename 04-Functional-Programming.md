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