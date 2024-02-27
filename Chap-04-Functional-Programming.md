## Functional Programming

### Using Variables in Lambdas

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

### Working with Built-In Functional Interfaces

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

*Implementing Supplier*

```java
@FunctionalInterface
public class Supplier<T> {

  public T get();
} 
```

You can use a Supplier to call:

```java
Supplier<LocalDate> s1 = LocalDate::now;
Supplier<LocalDate> s2 = () -> LocalDate.now();
  
LocalDate d1 = s1.get();
LocalDate d2 = s2.get();
  
System.out.println(d1);
System.out.println(d2);
```

*Implementing Consumer and BiConsumer*

```java
@FunctionalInterface
public class Consumer<T> {

  void accept(T t);
}

@FunctionalInterface
public class BiConsumer<T, U> {

  void accept(T t, U u);
}
```

Example Consumer:

```java
Consumer<String> c1 = System.out::println;
Consumer<String> c2 = x -> System.out.println(x);
  
c1.accept("Annie");
c2.accept("Annie");
```

Example BiConsumer:

```java
Map<String, Integer> map = new HashMap<>();
BiConsumer<String, Integer> b1 = map::put;
BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
  
b1.accept("chicken", 7);
b2.accept("chick", 1);
  
System.out.println(map); //{chicken=7, chick=1}
```

**Implementing Predicate and BiPredicate**

```java
@FunctionalInterface
public class Predicate<T> {

  boolean test(T t);
}

@FunctionalInterface
public class BiPredicate<T, U> {

  boolean test(T t, U u);
}
```

Example Predicate:

```java
Predicate<String> p1 = String::isEmpty;
Predicate<String> p2 = x -> x.isEmpty();
System.out.println(p1.test(""));
System.out.println(p2.test(""));
```

Example BiPredicate:

```java
BiPredicate<String, String> b1 = String::startsWith;
BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
System.out.println(b1.test("chicken", "chick"));
System.out.println(b2.test("chicken", "chick"));
```

**Implementing Function and BiFunction**

```java
@FunctionalInterface
public class Function<T, R> {

  R apply(T t);
}

@FunctionalInterface
public class BiFunction<T, U, R> {

  R apply(T t, U u);
}
```

Example Function:

```java
Function<String, Integer> f1 = String::length;
Function<String, Integer> f2 = x -> x.length();
System.out.println(f1.apply("cluck")); // 5
System.out.println(f2.apply("cluck")); // 5
```

Example BiFunction:

```java
BiFunction<String, String, String> b1 = String::concat;
BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);
System.out.println(b1.apply("baby ", "chick")); // baby chick
System.out.println(b2.apply("baby ", "chick")); // baby chick
```

**Implementing UnaryOperator and BinaryOperator**

- UnaryOperator and BinaryOperator are a special case of a function.
- They require all type parameters to be the same type.

```java

@FunctionalInterface
public class UnaryOperator<T>
    extends Function<T, T> {

}

@FunctionalInterface
public class BinaryOperator<T>
    extends BiFunction<T, T, T> {

}
```

This means that method signatures look like this:

```java
T apply(T t);
T apply(T t1, T t2);
```

Example UnaryOperator:

```java
UnaryOperator<String> u1 = String::toUpperCase;
UnaryOperator<String> u2 = x -> x.toUpperCase();
System.out.println(u1.apply("chirp"));
System.out.println(u2.apply("chirp"));
```

Example BinaryOperator:

```java
BinaryOperator<String> b1 = String::concat;
BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);
System.out.println(b1.apply("baby ", "chick")); // baby chick
System.out.println(b2.apply("baby ", "chick")); // baby chick
```

### Returning an Optional

- Optional instance methods

| Method                 | When Optional Is Empty                       | When Optional Contains a Value |
|------------------------|----------------------------------------------|--------------------------------|
| get()                  | Throws an exception                          | Returns value                  |
| ifPresent(Consumer c)  | Does nothing                                 | Calls Consumer c with value    |
| isPresent()            | Returns false                                | Returns true                   |
| orElse(T other)        | Returns other parameter                      | Returns value                  |
| orElseGet(Supplier s)  | Returns result of calling Supplier           | Returns value                  |
| orElseThrow(Suppliers) | Throws exception created by calling Supplier | Returns value                  |
                                                           
```java
public static Optional<Double> average(int… scores) {
    if (scores.length == 0) return Optional.empty();
    int sum = 0;
    for (int score: scores) sum += score;
    return Optional.of((double) sum / scores.length);
}

Optional<Double> opt = average();
System.out.println(opt.orElse(Double.NaN)); // NaN
System.out.println(opt.orElseGet(() -> Math.random())); // 0.49775932295380165
System.out.println(opt.orElseThrow(() -> new IllegalStateException())); // Exception in thread "main" java.lang.IllegalStateException...
```

### Using Streams

- In Java, a stream represents a sequence of data, and a stream pipeline refers to the operations 
performed on this stream to produce a result.
- Each task on the assembly line is dependent on the completion of the previous one.
- A distinctive aspect of an assembly line is that each task operates on each item in the sequence, 
and once processed, the item moves forward and doesn't return.
- Stream operations, akin to tasks on an assembly line, occur in a sequential manner, forming a pipeline.

1. There are three parts to a stream pipeline:

- **Source**: Where the stream comes from.
- **Intermediate operations**: Transforms the stream into another one. There can be as few
or as many intermediate operations as you’d like. Since streams use lazy evaluation, the
intermediate operations do not run until the terminal operation runs.
- **Terminal operation**: Actually produces a result. Since streams can be used only once,
the stream is no longer valid after a terminal operation completes.

2. Intermediate vs. terminal operations

| Aspect                                  | Intermediate Operations                                         | Terminal Operations                                            |
|-----------------------------------------|-----------------------------------------------------------------|----------------------------------------------------------------|
| Scenario                                | For transforming, filtering, or sorting data within the stream. | For producing a result or side effect, terminating the stream. |
| Required part of a useful pipeline?     | No                                                              | Yes                                                            |
| Can exist multiple times in a pipeline? | Yes                                                             | No                                                             |
| Return type is a stream type?           | Yes                                                             | No                                                             |
| Executed upon method call?              | No                                                              | Yes                                                            |
| Stream valid after call?                | Yes                                                             | No                                                             |

3. Creating Stream Sources

There are a few ways to create a finite stream:

```java
Stream<String> empty = Stream.empty(); // count = 0
Stream<Integer> singleElement = Stream.of(1); // count = 1
Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
```

Java provides a convenient way to convert from a list to a stream:

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> fromList = list.stream();
Stream<String> fromListParallel = list.parallelStream(); 
```

4. Using Common Terminal Operations

| Method                                | What Happens for Infinite Streams | Return Value | Reduction |
|---------------------------------------|-----------------------------------|--------------|-----------|
| allMatch() / anyMatch() / noneMatch() | Sometimes terminates              | boolean      | No        |
| collect()                             | Does not terminate                | Varies       | Yes       |
| count()                               | Does not terminate                | long         | Yes       |
| findAny() / findFirst()               | Terminates                        | Optional<T>  | No        |
| forEach()                             | Does not terminate                | void         | No        |
| min() / max()                         | Does not terminate                | Optional<T>  | Yes       |
| reduce()                              | Does not terminate                | Varies       | Yes       |

**count()**

- The count() method determines the number of elements in a finite stream.
- Method signature:

```java
long count()
```

- Example:

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
System.out.println(s.count()); // 3
```

**min() and max()**

- The min() and max() methods allow you to pass a custom comparator and find the smallest or largest 
value in a finite stream according to that sort order.
- Method signature:

```java
Optional<T> min(<? super T> comparator)
Optional<T> max(<? super T> comparator)
```

- Example:

```java
Stream<String> s = Stream.of("monkey", "ape", "bonobo");
Optional<String> min = s.min((s1, s2) -> s1.length()—s2.length());
min.ifPresent(System.out::println); // ape
```

**findAny() and findFirst()**

- The `findAny()` and `findFirst()` methods return an element of the stream unless the stream
is empty.
- If the stream is empty, they return an empty Optional.
- Works with an `infinite` stream.
- Method signature:

```java
Optional<T> findAny()
Optional<T> findFirst()
```

- Example:

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
Stream<String> infinite = Stream.generate(() -> "chimp");
s.findAny().ifPresent(System.out::println); // monkey
infinite.findAny().ifPresent(System.out::println); // chimp
```

**allMatch() , anyMatch() and noneMatch()**

- The `allMatch()` , `anyMatch()` and `noneMatch()` methods search a stream and return information about 
how the stream pertains to the predicate.
- These may or may not terminate for infinite streams. It depends on the data.
- Method signature:

```java
boolean anyMatch(Predicate <? super T> predicate)
boolean allMatch(Predicate <? super T> predicate)
boolean noneMatch(Predicate <? super T> predicate)
```

- Example:

```java
List<String> list = Arrays.asList("monkey", "2", "chimp");
Stream<String> infinite = Stream.generate(() -> "chimp");
Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
System.out.println(list.stream().anyMatch(pred)); // true
System.out.println(list.stream().allMatch(pred)); // false
System.out.println(list.stream().noneMatch(pred)); // false
System.out.println(infinite.anyMatch(pred)); // true
```

**forEach()**

- A looping construct is available.
- Method signature:

```java
void forEach(Consumer<? super T> action)
```

- Notice that this is the only terminal operation with a return type of void.
- Example:

```java
Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
s.forEach(System.out::print); // MonkeyGorillaBonobo
```

- Notice that you can’t use a traditional for loop on a stream:

```java
Stream s = Stream.of(1);
for (Integer i: s) {} // DOES NOT COMPILE
```

**reduce()**

- The reduce() method combines a stream into a single object.
- Method signature:

```java
T reduce(T identity, BinaryOperator<T> accumulator)
    
Optional<T> reduce(BinaryOperator<T> accumulator)

<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
```

- Example:

```java
Stream<String> stream = Stream.of("w", "o", "l", "f");
String word = stream.reduce("", (s, c) -> s + c);
System.out.println(word); // wolf

Stream<Integer> stream = Stream.of(3, 5, 6);
System.out.println(stream.reduce(1, (a, b) -> a*b));
```

- There are three choices for what is in the Optional:
  + If the stream is empty, an empty Optional is returned.
  + If the stream has one element, it is returned.
  + If the stream has multiple elements, the accumulator is applied to combine them.
The following illustrates each of these scenarios:

```java
BinaryOperator<Integer> op = (a, b) -> a * b;
Stream<Integer> empty = Stream.empty();
Stream<Integer> oneElement = Stream.of(3);
Stream<Integer> threeElements = Stream.of(3, 5, 6);
empty.reduce(op).ifPresent(System.out::print); // no output
oneElement.reduce(op).ifPresent(System.out::print); // 3
threeElements.reduce(op).ifPresent(System.out::print); // 90
```

**collect()**

- The collect() method is a special type of reduction called a mutable reduction.
- 