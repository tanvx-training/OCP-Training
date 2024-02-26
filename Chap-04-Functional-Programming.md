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
```
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

```
Consumer<String> c1 = System.out::println;
Consumer<String> c2 = x -> System.out.println(x);
  
c1.accept("Annie");
c2.accept("Annie");
```

Example BiConsumer:

```
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

```
Predicate<String> p1 = String::isEmpty;
Predicate<String> p2 = x -> x.isEmpty();
System.out.println(p1.test(""));
System.out.println(p2.test(""));
```

Example BiPredicate:

```
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

```
Function<String, Integer> f1 = String::length;
Function<String, Integer> f2 = x -> x.length();
System.out.println(f1.apply("cluck")); // 5
System.out.println(f2.apply("cluck")); // 5
```

Example BiFunction:

```
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

```
T apply(T t);
T apply(T t1, T t2);
```

Example UnaryOperator:

```
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

