## Generics and Collections

### Working with Generics

**Generic Classes**

- You can introduce generics into your own classes. 
- The syntax for introducing a generic is to declare a `formal type` parameter in angle brackets.

```java
public class Crate<T> {
    private T contents;

    public T emptyCrate() {
        return contents;
    }

    public void packCrate(T contents) {
        this.contents = contents;
    }
}
```
- The generic type T is available anywhere within the Crate class. 
- When you instantiate the class, you tell the compiler what T should be for that particular instance.

*Type erasure*

Behind the scenes, the compiler replaces all references to T in Crate with Object. In other words, after the code compiles, your generics are actually just Object types. The Crate class looks like the following:

```java
public class Crate {
    private Object contents;

    public Object emptyCrate() {
        return contents;
    }

    public void packCrate(Object contents) {
        this.contents = contents;
    }
}
```

**Generic Interfaces**

Just like a class, an interface can declare a formal type parameter. For example, the follow- ing Shippable interface uses a generic type as the argument to its ship() method:

```java
public interface Shippable<T> {
    void ship(T t);
}
```

*What You Can’t do with Generic Types*

1. Call the constructor. `new T()`
2. Create an array of that static type.
3. Call instanceof.
4. Use a primitive type as a generic type parameter.
5. Create a static variable as a generic type parameter. 

**Generic Methods**

- This is often useful for static methods since they aren’t part of an instance that can declare the type. 
- However, it is also allowed on non-static methods as well.

```java
public static <T> Crate<T> ship(T t) {
    System.out.println("Preparing " + t);
    return new Crate<T>();
}
```

- You can specify the type explicitly to make it obvious what the type is:

```java
Box.<String>ship("package");
Box.<String[]>ship(args);
```

**Interacting with Legacy Code**

**Bounds**

- A bounded parameter type is a generic type that specifies a bound for the generic. 
- A wildcard generic type is an unknown generic type represented with a question mark (?). 

| Type of bound                | Syntax                              | Example                                  |
|------------------------------|-------------------------------------|------------------------------------------|
| Unbounded wildcard           | `?`                                 | `List<?> l = new ArrayList<String>();`   |
| Wildcard with an upper bound | `? extends type`                | `List<? extends Exception> l = new ArrayList<RuntimeException>();` |
| Wildcard with a lower bound  | `? super type`                   | `List<? super Exception> l = new ArrayList<Object>();`  |

*Unbounded Wildcards*

- An unbounded wildcard represents any data type. 
- You use ? when you want to specify that any type is OK with you.

```java
public static void printList(List<?> list) {
    for (Object x : list) System.out.println(x);
}

public static void main(String[] args) {
    List<String> keywords = new ArrayList<>();
    keywords.add("java");
    printList(keywords);
}
```

*Upper-Bounded Wildcards*

- We’ve established that a generic type can’t just use a subclass:
```java
ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT COMPILE
```
- Instead, we need to use a wildcard:
```java
List<? extends Number> list = new ArrayList<Integer>();
```

- The list becomes logically immutable. 

```java
static class Sparrow extends Bird {
}

static class Bird {
}

public static void main(String[] args) {
    List<? extends Bird> birds = new ArrayList<Bird>();
    birds.add(new Sparrow()); // DOES NOT COMPILE birds.add(new Bird()); // DOES NOT COMPILE
}
```

*Lower-Bounded Wildcards*

