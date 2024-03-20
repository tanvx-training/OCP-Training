# Generics and Collections

## Reviewing OCA Collections

### Array and ArrayList

Sure, I'd be happy to explain both Arrays and ArrayLists in Java.

#### Arrays:

An array in Java is a fixed-size container that holds a fixed number of elements of the same data type. Once you declare an array, its size cannot be changed. Here's how you declare and initialize an array:

```java
// Declare an array of integers with size 5
int[] myArray = new int[5];

// Initialize the array with values
myArray[0] = 10;
myArray[1] = 20;
myArray[2] = 30;
myArray[3] = 40;
myArray[4] = 50;
```

You can also declare and initialize an array in one line:

```java
int[] myArray = {10, 20, 30, 40, 50};
```

To access elements of the array, you use the index (starting from 0):

```java
int firstElement = myArray[0]; // Accessing the first element
int thirdElement = myArray[2]; // Accessing the third element
```

#### ArrayLists:

ArrayList is a part of the Java Collections Framework and provides dynamic arrays in Java. Unlike arrays, ArrayLists can dynamically grow and shrink in size. Here's how you can use ArrayList:

```java
import java.util.ArrayList;

// Create an ArrayList of integers
ArrayList<Integer> myList = new ArrayList<>();

// Add elements to the ArrayList
myList.add(10);
myList.add(20);
myList.add(30);
myList.add(40);
myList.add(50);
```

You can also specify the initial capacity of the ArrayList:

```java
ArrayList<Integer> myList = new ArrayList<>(10); // Initial capacity is 10
```

To access elements of the ArrayList, you use the `get()` method:

```java
int firstElement = myList.get(0); // Accessing the first element
int thirdElement = myList.get(2); // Accessing the third element
```

ArrayList also provides various methods to manipulate its elements such as `add()`, `remove()`, `size()`, `clear()`, etc. It's a very versatile data structure and widely used in Java programming.

### Searching and Sorting

Certainly! Java Collections Framework provides utility methods for sorting and searching collections such as ArrayLists. Here's how you can use `Collections.sort()` and `Collections.binarySearch()`:

#### Sorting with `Collections.sort()`:

```java
import java.util.ArrayList;
import java.util.Collections;

public class SortingExample {
    public static void main(String[] args) {
        // Create an ArrayList of integers
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(5);
        myList.add(3);
        myList.add(8);
        myList.add(1);
        myList.add(9);
        
        // Sort the ArrayList
        Collections.sort(myList);
        
        // Print the sorted ArrayList
        System.out.println("Sorted ArrayList: " + myList);
    }
}
```

#### Searching with `Collections.binarySearch()`:

```java
import java.util.ArrayList;
import java.util.Collections;

public class SearchingExample {
    public static void main(String[] args) {
        // Create a sorted ArrayList of integers
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(3);
        myList.add(5);
        myList.add(7);
        myList.add(9);
        
        // Search for an element in the ArrayList
        int index = Collections.binarySearch(myList, 5);
        
        // Print the index of the element if found, otherwise print "Element not found"
        if (index >= 0) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found");
        }
    }
}
```

Remember that `Collections.binarySearch()` requires the list to be sorted in ascending order. If the list is not sorted, the result of the binary search is undefined.

These methods provide convenient ways to sort and search collections in Java without having to implement sorting and searching algorithms manually.

### Wrapper Classes and Autoboxing

Wrapper classes in Java provide a way to convert primitive data types into objects. This is useful when you need to treat primitive data types as objects, such as when working with collections or when passing parameters to methods that expect objects. Autoboxing is the automatic conversion of primitive data types to their corresponding wrapper class objects, and unboxing is the reverse process. Let's explore wrapper classes and autoboxing in Java:

### Wrapper Classes:

Java provides a set of predefined classes called wrapper classes for each of the primitive data types. Here are the primitive data types along with their corresponding wrapper classes:

- `byte` - `Byte`
- `short` - `Short`
- `int` - `Integer`
- `long` - `Long`
- `float` - `Float`
- `double` - `Double`
- `char` - `Character`
- `boolean` - `Boolean`

Wrapper classes provide methods to convert strings to primitive data types and vice versa, perform mathematical operations, and more.

### Autoboxing and Unboxing:

Autoboxing and unboxing were introduced in Java 5 to automatically convert between primitive data types and their corresponding wrapper classes. This simplifies code and makes it more readable.

#### Autoboxing:
Autoboxing automatically converts primitive data types to their corresponding wrapper class objects. Here's an example:

```java
int intValue = 10;
Integer integerValue = intValue; // Autoboxing: int to Integer
```

#### Unboxing:
Unboxing automatically converts wrapper class objects to their corresponding primitive data types. Here's an example:

```java
Integer integerValue = 20;
int intValue = integerValue; // Unboxing: Integer to int
```

### Example:

```java
public class WrapperExample {
    public static void main(String[] args) {
        // Autoboxing
        int primitiveInt = 100;
        Integer wrapperInt = primitiveInt; // Autoboxing: int to Integer
        
        // Unboxing
        Double wrapperDouble = 3.14;
        double primitiveDouble = wrapperDouble; // Unboxing: Double to double
        
        // Using wrapper classes
        Integer x = 10;
        System.out.println("x = " + x);
        
        // Converting wrapper class object to primitive data type
        int y = x.intValue();
        System.out.println("y = " + y);
        
        // Converting string to integer using wrapper class
        String str = "100";
        int z = Integer.parseInt(str);
        System.out.println("z = " + z);
        
        // Using wrapper classes in collections
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(5);
        myList.add(10);
        System.out.println("Elements in ArrayList: " + myList);
    }
}
```

Wrapper classes and autoboxing simplify coding in Java by allowing primitive data types to be treated as objects and vice versa without explicitly converting them. They are widely used in Java programming, especially when working with collections and APIs that expect objects rather than primitive data types.

### The Diamond Operator

The diamond operator, introduced in Java 7, is a syntactic sugar feature that simplifies the instantiation of generic classes. It allows you to omit the type parameters of a generic class constructor when the type can be inferred from the context. This makes the code more concise and readable. Let's dive deeper into the diamond operator:

### Without Diamond Operator:

Before Java 7, when you wanted to create an instance of a generic class, you had to specify the type parameters twice, once when declaring the variable and again when creating the instance. For example:

```java
List<String> myList = new ArrayList<String>();
```

### With Diamond Operator:

With the diamond operator, you can omit the type parameters in the constructor, and the compiler will infer the type from the variable declaration. Here's how you use the diamond operator:

```java
List<String> myList = new ArrayList<>();
```

The diamond operator (`<>`) is used instead of repeating the type parameters in the constructor. This makes the code cleaner and less prone to errors, especially when dealing with complex generic types.

### Benefits of Using Diamond Operator:

1. **Conciseness**: The diamond operator reduces boilerplate code by eliminating redundant type information.

2. **Readability**: It improves code readability by focusing on the essential parts of the code.

3. **Maintainability**: With fewer type parameters specified explicitly, the code becomes easier to maintain and refactor.

4. **Type Safety**: The compiler ensures type safety by inferring the correct type parameters based on the context.

### Considerations:

1. **Compatibility**: The diamond operator is compatible with classes that have a constructor with type parameters. It cannot be used with anonymous inner classes or raw types.

2. **Type Inference**: While the diamond operator simplifies code, it relies on type inference by the compiler. Ensure that the compiler can correctly infer the types in your code.

3. **Code Clarity**: While the diamond operator can enhance code clarity, it's essential to strike a balance and use it judiciously. Sometimes explicitly specifying type parameters can improve code readability, especially in complex scenarios.

### Example:

```java
import java.util.ArrayList;
import java.util.List;

public class DiamondOperatorExample {
    public static void main(String[] args) {
        // Without diamond operator
        List<String> list1 = new ArrayList<String>();

        // With diamond operator
        List<String> list2 = new ArrayList<>();
    }
}
```

In this example, both `list1` and `list2` are instantiated as `ArrayList` objects with type parameter `String`, but the diamond operator makes the code cleaner and more concise.

## Working with Generics

### Generic Classes

Sure! Generics in Java allow you to create classes, interfaces, and methods that can work with any data type. They provide type safety by allowing you to specify the type(s) that a class or method can operate on, without compromising the flexibility and reusability of your code. Let's explore how to create generic classes in Java:

#### Syntax of Generic Classes:

To create a generic class in Java, you use angle brackets (`< >`) to specify one or more type parameters. Here's the basic syntax:

```java
public class ClassName<T> {
    // Class body
}
```

- `T` is a type parameter, which can be replaced by any reference type (e.g., `Integer`, `String`, custom class).
- Inside the class, you can use `T` as a placeholder for the actual type that will be provided when an instance of the class is created.

#### Example of a Generic Class:

Let's create a simple generic class called `Box`, which can hold any type of object:

```java
public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
```

In this example:
- `T` is a type parameter that represents the type of the item stored in the `Box`.
- The `setItem()` method takes an argument of type `T` and sets it as the item inside the `Box`.
- The `getItem()` method returns the item stored in the `Box`.

#### Using Generic Classes:

You can create instances of generic classes by specifying the actual type(s) inside the angle brackets. Here's how you can use the `Box` class:

```java
public class Main {
    public static void main(String[] args) {
        // Create a Box for storing strings
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello, World!");
        String str = stringBox.getItem();
        System.out.println("String in the box: " + str);

        // Create a Box for storing integers
        Box<Integer> intBox = new Box<>();
        intBox.setItem(123);
        int num = intBox.getItem();
        System.out.println("Integer in the box: " + num);
    }
}
```

In this example, `stringBox` and `intBox` are instances of the `Box` class, each storing a different type of object. The compiler ensures type safety by enforcing the specified types at compile time.

#### Benefits of Generic Classes:

1. **Type Safety**: Generics provide compile-time type checking, reducing the likelihood of type-related errors at runtime.
2. **Code Reusability**: Generic classes can be reused with different types, promoting code reuse and modularity.
3. **Performance**: Generics do not incur any runtime overhead due to type erasure, as the compiler substitutes the type parameters with the appropriate types during compilation.

Generic classes are a powerful feature of Java that allows you to create flexible and type-safe code for a wide range of applications. They are widely used in collections, algorithms, and APIs to provide greater flexibility and type safety.

#### Type erasure

Type erasure is a process in Java where the compiler removes type parameters and replaces them with their bounding or the most specific bound if the type parameter is unbounded. This is done during the compilation process, and it's a key feature that enables Java's backward compatibility with older versions of the language that didn't support generics.

##### How Type Erasure Works:

1. **Type Parameter Removal**: During compilation, all generic type information is removed, and the code is translated to non-generic code. This means that type parameters are replaced with their raw types or their upper bounds if bounded.

2. **Raw Types**: If no explicit bound is specified, the type parameter is replaced with its raw type, which is the erasure of the type parameter. For example, `List<T>` becomes `List`.

3. **Upper Bound Substitution**: If a type parameter has a bound, such as `T extends Number`, the type parameter is replaced with the bound type (`Number` in this case). If multiple bounds are specified, the leftmost bound is used.

##### Example of Type Erasure:

Consider the following generic class:

```java
public class Example<T> {
    private T data;

    public Example(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
```

After compilation, the code undergoes type erasure, and it becomes:

```java
public class Example {
    private Object data;

    public Example(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
```

In this example, the type parameter `T` is replaced with `Object` because there are no bounds specified.

##### Limitations of Type Erasure:

1. **Lack of Runtime Type Information (RTTI)**: Due to type erasure, generic type information is not available at runtime. This means you can't perform certain operations like checking the type of a generic object at runtime using `instanceof`.

2. **Unsafe Cast Warnings**: Type erasure can lead to unchecked type casts, which may result in compiler warnings or errors. This can happen when casting to generic types or performing operations that involve type parameters.

3. **Incompatibility with Overloaded Methods**: If two methods have the same erasure after type erasure, they are considered duplicate and will result in a compilation error. This can lead to potential compatibility issues in legacy code.

Despite these limitations, type erasure enables Java's backward compatibility and allows generic code to interoperate seamlessly with legacy code that doesn't support generics. It's an essential concept to understand when working with generics in Java.

### Generic Interfaces

Generic interfaces in Java allow you to create interfaces that can work with any data type. This provides flexibility and type safety when defining contracts for classes that implement the interface. Let's explore how to create and use generic interfaces in Java:

#### Syntax of Generic Interfaces:

To create a generic interface in Java, you use angle brackets (`< >`) to specify one or more type parameters. Here's the basic syntax:

```java
public interface InterfaceName<T> {
    // Method declarations
}
```

- `T` is a type parameter, which can be replaced by any reference type (e.g., `Integer`, `String`, custom class).
- Inside the interface, you can use `T` as a placeholder for the actual type that will be provided when a class implements the interface.

#### Example of a Generic Interface:

Let's create a simple generic interface called `Box`, which represents an object that can hold any type of item:

```java
public interface Box<T> {
    void setItem(T item);
    T getItem();
}
```

In this example:
- `T` is a type parameter that represents the type of the item stored in the box.
- The `setItem()` method takes an argument of type `T` and sets it as the item inside the box.
- The `getItem()` method returns the item stored in the box.

#### Implementing Generic Interfaces:

Classes that implement a generic interface must specify the actual type(s) for the type parameter(s). Here's how you can implement the `Box` interface:

```java
public class MyBox<T> implements Box<T> {
    private T item;

    @Override
    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public T getItem() {
        return item;
    }
}
```

In this example, `MyBox` is a class that implements the `Box` interface with a specific type `T`. The methods `setItem()` and `getItem()` are implemented according to the contract defined in the interface.

#### Using Generic Interfaces:

You can use generic interfaces to create instances of classes that implement the interface with specific types. Here's how you can use the `Box` interface and the `MyBox` class:

```java
public class Main {
    public static void main(String[] args) {
        Box<String> stringBox = new MyBox<>();
        stringBox.setItem("Hello, World!");
        String str = stringBox.getItem();
        System.out.println("String in the box: " + str);

        Box<Integer> intBox = new MyBox<>();
        intBox.setItem(123);
        int num = intBox.getItem();
        System.out.println("Integer in the box: " + num);
    }
}
```

In this example, `stringBox` and `intBox` are instances of the `MyBox` class, each storing a different type of item. The compiler ensures type safety by enforcing the specified types at compile time.

Generic interfaces are a powerful feature of Java that allows you to create flexible and type-safe contracts for classes. They are widely used in Java's standard library and APIs to provide greater flexibility and type safety.

Certainly! Here's the information about what you can't do with generic types:

1. **Call the constructor (`new T()`)**: Generic types in Java are erased at runtime, so the compiler doesn't know the exact type of `T`. Due to this limitation, you cannot create new instances of a generic type using `new T()`.

2. **Create an array of that static type**: Since arrays in Java store their component type at runtime, creating an array of a generic type is not allowed because the exact type of `T` is not known at runtime due to type erasure.

3. **Call `instanceof`**: You cannot use `instanceof` with generic types because the type information of generic types is erased at runtime. Therefore, `instanceof` cannot determine the type of a generic object at runtime.

4. **Use a primitive type as a generic type parameter**: Java does not allow primitive types (such as `int`, `char`, `double`, etc.) as type arguments for generics. You must use their corresponding wrapper classes (e.g., `Integer`, `Character`, `Double`, etc.) instead.

5. **Create a static variable as a generic type parameter**: Static variables in Java are shared across all instances of a class, and they are associated with the class itself rather than individual instances. Since generic types are not associated with a specific class until runtime, you cannot create static variables of generic type parameters. 

### Generic Methods

Generic methods in Java allow you to create methods that can work with any data type, similar to generic classes. They provide flexibility and type safety when defining methods that operate on generic types. Let's explore how to create and use generic methods in Java:

#### Syntax of Generic Methods:

To create a generic method in Java, you use angle brackets (`< >`) to specify one or more type parameters before the return type. Here's the basic syntax:

```java
public <T> returnType methodName(parameterList) {
    // Method body
}
```

- `<T>` is a type parameter, which can be replaced by any reference type (e.g., `Integer`, `String`, custom class).
- Inside the method, you can use `T` as a placeholder for the actual type that will be provided when the method is called.

#### Example of a Generic Method:

Let's create a simple generic method called `printArray`, which prints the elements of an array of any type:

```java
public class GenericMethodExample {
    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
}
```

In this example:
- `<T>` is a type parameter that represents the type of elements in the array.
- The method `printArray` takes an array of type `T` as a parameter and prints each element.

#### Using Generic Methods:

You can invoke generic methods by specifying the actual type(s) for the type parameter(s) when calling the method. Here's how you can use the `printArray` method:

```java
public class Main {
    public static void main(String[] args) {
        GenericMethodExample example = new GenericMethodExample();

        // Array of integers
        Integer[] intArray = {1, 2, 3, 4, 5};
        example.printArray(intArray);

        // Array of strings
        String[] strArray = {"Hello", "World", "!"};
        example.printArray(strArray);
    }
}
```

In this example, the `printArray` method is called twice, once with an array of integers and once with an array of strings. The compiler ensures type safety by enforcing the specified types at compile time.

#### Benefits of Generic Methods:

1. **Type Safety**: Generic methods provide compile-time type checking, reducing the likelihood of type-related errors at runtime.
2. **Code Reusability**: Generic methods can be reused with different types, promoting code reuse and modularity.
3. **Flexibility**: Generic methods allow you to define methods that can work with any data type, increasing the flexibility of your code.

Generic methods are a powerful feature of Java that allows you to create flexible and type-safe methods for a wide range of applications. They are widely used in Java's standard library and APIs to provide greater flexibility and type safety.

### Interacting with Legacy Code

Interacting with legacy code in Java, especially when it doesn't support generics, can be challenging. However, Java's generics feature is designed to maintain compatibility with older versions of the language. Here are some strategies and techniques for interacting with legacy code when using generics:

#### 1. Raw Types:
If you're dealing with legacy code that predates Java 5 (when generics were introduced), you might encounter raw types. Raw types are generic types without type parameters. While using raw types is discouraged because they bypass type safety, they can be useful for interacting with legacy code that doesn't support generics.

```java
List list = new ArrayList(); // Legacy code using raw types
```

#### 2. Suppressing Warnings:
When working with legacy code that generates unchecked warnings due to the use of raw types or unchecked casts, you can use the `@SuppressWarnings("unchecked")` annotation to suppress these warnings selectively.

```java
@SuppressWarnings("unchecked")
List<String> list = new ArrayList(); // Suppressing unchecked warning
```

#### 3. Type Casting:
In situations where you need to interact with legacy code that returns raw types, you can use type casting to convert the raw types to generic types. However, this approach should be used with caution to avoid `ClassCastException`.

```java
List rawList = someLegacyMethod(); // Legacy code returning raw types
List<String> list = (List<String>) rawList; // Type casting
```

#### 4. Wrapper Classes:
If you need to work with primitive types in legacy code that doesn't support generics, you can use wrapper classes (e.g., `Integer`, `Double`) instead of primitive types.

```java
List<Integer> intList = new ArrayList<>(); // Using wrapper class instead of primitive type
```

#### 5. Bridge Methods:
When overriding or implementing methods from legacy code that uses raw types, the compiler may generate bridge methods to maintain compatibility with erasure. These bridge methods allow your code to interact seamlessly with legacy code.

#### 6. Gradual Refactoring:
Consider gradually refactoring legacy code to use generics where possible. This approach allows you to modernize the codebase incrementally without disrupting existing functionality.

```java
// Legacy method using raw types
public List getData() { ... }

// Refactored method using generics
public List<String> getData() { ... }
```

#### 7. Documentation and Testing:
When working with legacy code, thorough documentation and comprehensive testing are crucial. Document any interactions with legacy code, including any workarounds or considerations related to generics.

#### Conclusion:
Interacting with legacy code when using generics in Java requires careful consideration and possibly some compromises. By using techniques such as raw types, suppressing warnings, type casting, wrapper classes, bridge methods, gradual refactoring, documentation, and testing, you can effectively integrate generics into legacy codebases while maintaining compatibility and ensuring type safety.

### Bounds

#### Unbounded Wildcards

Unbounded wildcards in Java generics are represented by the `<?>` symbol and allow you to create generic types that can accept any type of argument. They provide maximum flexibility when you don't need to impose any constraints on the type of the elements. Let's delve into the details of unbounded wildcards:

### Syntax:
```java
List<?> myList = new ArrayList<>();
```
In this example, `<?>` indicates that `myList` can be assigned any type of list, regardless of its generic type.

### Usage:
1. **Read-Only Access**: Unbounded wildcards are often used when you need to access elements from a collection without knowing the exact type of the elements.

```java
public void printList(List<?> list) {
    for (Object element : list) {
        System.out.println(element);
    }
}
```

2. **Method Arguments**: Unbounded wildcards can be used as method parameters to accept any type of collection.

```java
public void processList(List<?> list) {
    // Process the elements of the list
}
```

3. **Assigning to Generic References**: You can use unbounded wildcards to assign generic references to any type of generic object.

```java
List<?> anyList = new ArrayList<String>();
List<?> anotherList = new LinkedList<Integer>();
```

### Benefits:
- **Flexibility**: Unbounded wildcards provide the maximum flexibility in terms of accepting different types of arguments.
- **Generics Compatibility**: They allow you to interact with legacy code or APIs that don't use generics.

### Drawbacks:
- **Limited Operations**: Since the exact type of the elements is unknown, you can't perform certain operations that require knowledge of the type.

### Example:
```java
import java.util.List;

public class UnboundedWildcardExample {
    public void printList(List<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        UnboundedWildcardExample example = new UnboundedWildcardExample();
        List<String> stringList = List.of("apple", "banana", "orange");
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);

        example.printList(stringList); // prints: apple, banana, orange
        example.printList(integerList); // prints: 1, 2, 3, 4, 5
    }
}
```

In this example, the `printList` method accepts lists of any type using an unbounded wildcard (`<?>`). This allows it to print the elements of both `String` and `Integer` lists without needing to know the exact type of the elements.

#### Upper-Bounded Wildcards

Upper-bounded wildcards in Java generics are represented by the `<? extends Type>` symbol and allow you to create generic types that can accept any subtype of a specific type. They provide flexibility when you need to work with different types that share a common supertype. Let's explore the details of upper-bounded wildcards:

### Syntax:
```java
List<? extends Number> numberList = new ArrayList<>();
```
In this example, `<? extends Number>` indicates that `numberList` can be assigned any type of list whose elements are subtypes of `Number`.

### Usage:
1. **Read-Only Access**: Upper-bounded wildcards are commonly used when you need to access elements from a collection without modifying them.

```java
public double sumOfList(List<? extends Number> list) {
    double sum = 0;
    for (Number number : list) {
        sum += number.doubleValue();
    }
    return sum;
}
```

2. **Method Arguments**: They can be used as method parameters to accept lists containing any subtype of a specific type.

```java
public void processList(List<? extends Shape> list) {
    // Process the elements of the list
}
```

### Benefits:
- **Polymorphism**: Upper-bounded wildcards enable polymorphic behavior by allowing the use of different subtypes of a common supertype.
- **Flexibility**: They provide flexibility in accepting different subtypes of a specific type.

### Drawbacks:
- **Limited Operations**: Since the exact subtype of the elements is unknown, you can't perform certain operations that are specific to the subtype.

### Example:
```java
import java.util.List;

public class UpperBoundedWildcardExample {
    public double sumOfList(List<? extends Number> list) {
        double sum = 0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        UpperBoundedWildcardExample example = new UpperBoundedWildcardExample();
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3, 4.4, 5.5);

        double sum1 = example.sumOfList(integerList); // returns the sum of integers: 15.0
        double sum2 = example.sumOfList(doubleList); // returns the sum of doubles: 16.5
    }
}
```

In this example, the `sumOfList` method accepts lists of any subtype of `Number` using an upper-bounded wildcard (`<? extends Number>`). This allows it to calculate the sum of elements for both `Integer` and `Double` lists without needing to know the exact subtype of the elements.

#### Lower-Bounded Wildcards

Lower-bounded wildcards in Java generics are represented by the `<? super Type>` symbol and allow you to create generic types that can accept any supertype of a specific type. They provide flexibility when you need to work with different types that share a common subtype. Let's explore the details of lower-bounded wildcards:

### Syntax:
```java
List<? super Integer> integerList = new ArrayList<>();
```
In this example, `<? super Integer>` indicates that `integerList` can be assigned any type of list whose elements are supertypes of `Integer`.

### Usage:
1. **Write Access**: Lower-bounded wildcards are commonly used when you need to add elements to a collection of a specific type or its supertypes.

```java
public void addNumbers(List<? super Integer> list) {
    for (int i = 1; i <= 5; i++) {
        list.add(i);
    }
}
```

2. **Method Arguments**: They can be used as method parameters to accept lists containing any supertype of a specific type.

```java
public void processList(List<? super Rectangle> list) {
    // Process the elements of the list
}
```

### Benefits:
- **Polymorphism**: Lower-bounded wildcards enable polymorphic behavior by allowing the use of different supertypes of a common subtype.
- **Flexibility**: They provide flexibility in accepting different supertypes of a specific type.

### Drawbacks:
- **Limited Operations**: Since the exact supertype of the elements is unknown, you can't perform certain operations that are specific to the supertype.

### Example:
```java
import java.util.List;
import java.util.ArrayList;

public class LowerBoundedWildcardExample {
    public void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        LowerBoundedWildcardExample example = new LowerBoundedWildcardExample();
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        example.addNumbers(numberList); // adds integers to numberList
        example.addNumbers(objectList); // adds integers to objectList
    }
}
```

In this example, the `addNumbers` method accepts lists of any supertype of `Integer` using a lower-bounded wildcard (`<? super Integer>`). This allows it to add integers to both `Number` and `Object` lists without needing to know the exact supertype of the elements.

### Using Lists, Sets, Maps, and Queues

A collection is a group of objects contained within a single object. In Java, the Collections Framework, found in the `java.util` package, provides a set of classes for storing and manipulating collections. There are four main interfaces in the Java Collections Framework:

#### 1. List:
- A list is an ordered collection of elements that allows duplicate entries.
- Elements in a list can be accessed by an integer index.
- Example implementations: `ArrayList`, `LinkedList`.

#### 2. Set:
- A set is a collection that does not allow duplicate entries.
- It ensures uniqueness of elements.
- Example implementations: `HashSet`, `TreeSet`.

#### 3. Queue:
- A queue is a collection that orders its elements in a specific order for processing.
- A typical queue processes its elements in a first-in, first-out (FIFO) order, but other orderings are possible.
- Example implementations: `LinkedList`, `PriorityQueue`.

#### 4. Map:
- A map is a collection that maps keys to values, with no duplicate keys allowed.
- The elements in a map are key/value pairs.
- Example implementations: `HashMap`, `TreeMap`.

#### Key Features:
- **Flexibility**: The Java Collections Framework provides a wide range of interfaces and implementations to suit different needs.
- **Type Safety**: Generics are extensively used in the framework to ensure type safety.
- **Performance**: Different implementations offer different performance characteristics, allowing developers to choose the best option for their requirements.
- **Utility Methods**: The framework includes utility methods for common collection operations such as searching, sorting, and iteration.


#### Common Collections Methods

Here's a breakdown of common collection methods along with their method signatures and descriptions:

| Method      | Method Signature                | Description                                                                                        |
|-------------|---------------------------------|----------------------------------------------------------------------------------------------------|
| add()       | `boolean add(E element)`        | Adds the specified element to the collection if it's not already present. Returns true if added.   |
| remove()    | `boolean remove(Object o)`      | Removes the specified element from the collection if it's present. Returns true if removed.        |
| isEmpty()   | `boolean isEmpty()`             | Checks if the collection is empty. Returns true if empty, false otherwise.                         |
| size()      | `int size()`                    | Returns the number of elements in the collection.                                                  |
| clear()     | `void clear()`                  | Removes all elements from the collection.                                                          |
| contains()  | `boolean contains(Object o)`    | Checks if the collection contains the specified element. Returns true if present, false otherwise. |

These methods are fundamental for manipulating and querying collections in Java, providing essential functionality for managing elements within collections.

#### Using the List Interface

Certainly! Here's a re-presentation of the characteristics and usage of lists in Java:

##### List in Java:

- **Definition**: A list is an ordered collection of elements that can contain duplicate entries.

- **Index-based Access**: Items in a list can be retrieved and inserted at specific positions based on an integer index, similar to arrays.

- **Common Usage**: Lists are frequently used in programming when there's a need to maintain an ordered collection of objects. They offer flexibility in managing data structures and are essential for various operations such as sorting, searching, and iteration.

- **Example Implementations**: Java provides several implementations of the List interface, including ArrayList, LinkedList, and Vector. Each implementation has its characteristics in terms of performance, memory usage, and suitability for specific use cases.

- **Ordered Collection**: Unlike sets, where elements are unordered, lists maintain the order in which elements are inserted. This property makes lists suitable for scenarios where the order of elements matters.

- **Allow Duplicates**: Lists allow duplicate elements, meaning the same element can appear multiple times in the list. This feature distinguishes lists from sets, which enforce uniqueness of elements.

##### Comparing List Implementations

When comparing ArrayList and LinkedList, it's essential to consider their performance characteristics, especially in terms of time complexity (Big O notation) for common operations. Let's analyze their performance:

###### ArrayList:
- **Insertion/Deletion at the End (Append)**:
  - **Time Complexity**: O(1) on average.
  - **Explanation**: Appending elements to the end of the ArrayList involves adding elements to the underlying array, which typically has constant-time complexity. However, if the array needs to be resized, the operation may take O(n) time due to copying elements to a new array.
- **Insertion/Deletion at the Beginning or Middle**:
  - **Time Complexity**: O(n).
  - **Explanation**: Inserting or deleting elements in the middle of the ArrayList requires shifting elements to accommodate the change, resulting in a linear time complexity proportional to the number of elements.

###### LinkedList:
- **Insertion/Deletion at the End (Append)**:
  - **Time Complexity**: O(1) on average.
  - **Explanation**: Appending elements to the end of the LinkedList involves adding a new node at the end, which can be done in constant time by updating the tail reference.
- **Insertion/Deletion at the Beginning**:
  - **Time Complexity**: O(1).
  - **Explanation**: Inserting or deleting elements at the beginning of the LinkedList involves updating the head reference, resulting in constant-time complexity.
- **Insertion/Deletion in the Middle**:
  - **Time Complexity**: O(n).
  - **Explanation**: Inserting or deleting elements in the middle of the LinkedList requires traversing the list to find the insertion or deletion point, resulting in linear time complexity proportional to the number of elements.

###### Choosing Between ArrayList and LinkedList:
- **Access Pattern**: If your application requires frequent random access or traversal but infrequent insertions or deletions, ArrayList may be more suitable due to its O(1) random access time complexity. Otherwise, LinkedList may offer better performance for frequent insertions and deletions.
- **Memory Consideration**: LinkedList generally consumes less memory than ArrayList due to its node-based structure. Consider memory usage based on the size of the list and available resources.
- **Specific Use Cases**: Evaluate the specific requirements and access patterns of your application to determine the most suitable implementation.

###### Conclusion:
Both ArrayList and LinkedList have their strengths and weaknesses, and the choice between them depends on the specific requirements and access patterns of your application. Understanding their performance characteristics, especially in terms of time complexity, is crucial for making an informed decision.

##### Working with List Methods

Here's a breakdown of common methods for working with lists in Java, along with their signatures, descriptions, and examples:

| Method Signature                  | Method Description                                     | Example                                      |
|-----------------------------------|--------------------------------------------------------|----------------------------------------------|
| `void add(E element)`             | Adds `element` to the end of the list.                    | `list.add("apple");`                        |
| `void add(int index, E element)`  | Adds `element` at the specified `index` and moves the rest toward the end. | `list.add(0, "banana");`                   |
| `E get(int index)`                | Returns the element at the specified `index`.             | `String fruit = list.get(0);`              |
| `int indexOf(Object o)`           | Returns the first matching index of `o` or -1 if not found. | `int index = list.indexOf("apple");`       |
| `int lastIndexOf(Object o)`       | Returns the last matching index of `o` or -1 if not found.  | `int lastIndex = list.lastIndexOf("apple");` |
| `void remove(int index)`          | Removes the element at the specified `index` and moves the rest toward the front. | `list.remove(0);`                          |
| `E set(int index, E e)`           | Replaces the element at the specified `index` with `e` and returns the original element. | `String replaced = list.set(0, "orange");` |

###### Method Descriptions:

1. **`add(E element)`:** Adds the specified element to the end of the list.
2. **`add(int index, E element)`:** Inserts the specified element at the specified position in the list, shifting the subsequent elements to the right.
3. **`get(int index)`:** Returns the element at the specified index in the list.
4. **`indexOf(Object o)`:** Returns the index of the first occurrence of the specified element in the list, or -1 if the list does not contain the element.
5. **`lastIndexOf(Object o)`:** Returns the index of the last occurrence of the specified element in the list, or -1 if the list does not contain the element.
6. **`remove(int index)`:** Removes the element at the specified position in the list, shifting any subsequent elements to the left.
7. **`set(int index, E e)`:** Replaces the element at the specified position in the list with the specified element.

###### Examples:
```java
List<String> list = new ArrayList<>();

// Adding elements
list.add("apple");
list.add(0, "banana");

// Getting element
String fruit = list.get(0);

// Finding index
int index = list.indexOf("apple");

// Removing element
list.remove(0);

// Replacing element
String replaced = list.set(0, "orange");
```

These methods provide essential functionality for manipulating and accessing elements within lists in Java. They allow you to add, retrieve, remove, and replace elements at specific positions in the list efficiently.

### Using the Set Interface

When working with sets in Java, there are specific characteristics and considerations to keep in mind:

##### Set Characteristics:
- **Uniqueness**: Sets do not allow duplicate entries. Each element in a set must be unique.
- **Ordering**: Sets do not guarantee the order in which elements are stored or iterated over. The order may vary depending on the set implementation.
- **Efficient Lookup**: Sets provide efficient lookup operations for determining whether an element is present in the set.

##### Common Use Cases:
- **Removing Duplicates**: Sets are commonly used when you need to eliminate duplicate elements from a collection.
- **Membership Testing**: Sets are suitable for membership testing, where you need to quickly determine whether a specific element is present in the collection.
- **Maintaining a Unique Collection**: When you want to maintain a collection of unique elements, sets provide a convenient solution.

#### Comparing Set Implementations

Comparing HashSet and TreeSet involves examining their characteristics, performance, and suitability for different use cases:

##### HashSet:
- **Internal Implementation**: Backed by a hash table data structure using HashMap internally.
- **Performance**:
  - **Insertion/Deletion/Search**: O(1) average time complexity for insertion, deletion, and search operations.
  - **Ordering**: Elements are not ordered; the iteration order may change over time and does not follow any specific order.
- **Duplicates**: HashSet does not allow duplicate elements; attempting to add a duplicate element has no effect.
- **Suitability**:
  - Suitable for scenarios where fast insertion, deletion, and membership testing are required and the order of elements is not important.
  - Ideal for implementing sets where uniqueness is a primary concern, and the order of elements does not matter.

##### TreeSet:
- **Internal Implementation**: Backed by a Red-Black tree data structure.
- **Performance**:
  - **Insertion/Deletion/Search**: O(log n) time complexity for insertion, deletion, and search operations.
  - **Ordering**: Elements are stored in sorted order based on their natural ordering or a custom Comparator provided during TreeSet initialization.
- **Duplicates**: TreeSet does not allow duplicate elements; attempting to add a duplicate element has no effect.
- **Suitability**:
  - Suitable for scenarios where elements need to be stored in sorted order and efficient search operations are required.
  - Ideal when you need a sorted set with guaranteed ordering and uniqueness of elements.

##### Choosing Between HashSet and TreeSet:
- **Performance Consideration**: HashSet typically offers better performance for most operations due to its O(1) average time complexity for common operations. However, TreeSet provides guaranteed ordering of elements, which can be beneficial if sorted order is required.
- **Ordered Requirement**: If you need elements to be stored in sorted order and efficient search operations, TreeSet is the preferred choice. Otherwise, HashSet may be more suitable for its faster performance.
- **Space Consideration**: TreeSet generally consumes more memory than HashSet due to the additional overhead of maintaining the Red-Black tree structure.

##### Example Usage:
```java
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetComparisonExample {
    public static void main(String[] args) {
        // HashSet example
        Set<String> hashSet = new HashSet<>();
        hashSet.add("banana");
        hashSet.add("apple");
        hashSet.add("orange");

        // TreeSet example
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("banana");
        treeSet.add("apple");
        treeSet.add("orange");

        System.out.println("HashSet: " + hashSet); // May not preserve insertion order
        System.out.println("TreeSet: " + treeSet); // Sorted order based on natural ordering
    }
}
```

In this example, we demonstrate the usage of HashSet and TreeSet implementations. HashSet does not guarantee the ordering of elements, while TreeSet stores elements in sorted order based on their natural ordering. Choose the appropriate implementation based on your specific requirements regarding ordering, performance, and memory usage.

#### Working with Set Methods

Here's an overview of common methods for working with sets in Java, along with their signatures, descriptions, and examples:

| Method Signature             | Description                                                 | Example                                   |
|------------------------------|-------------------------------------------------------------|-------------------------------------------|
| `boolean add(E e)`           | Adds the specified element to the set if it is not already present. | `set.add("apple");`                      |
| `boolean contains(Object o)` | Returns true if the set contains the specified element.     | `boolean containsApple = set.contains("apple");` |
| `boolean remove(Object o)`   | Removes the specified element from the set if it is present. | `boolean removed = set.remove("apple");` |
| `int size()`                 | Returns the number of elements in the set.                  | `int setSize = set.size();`              |
| `boolean isEmpty()`          | Returns true if the set contains no elements.               | `boolean empty = set.isEmpty();`         |
| `void clear()`               | Removes all of the elements from the set.                   | `set.clear();`                           |

##### Method Descriptions:

1. **`add(E e)`:** Adds the specified element to the set if it is not already present.
2. **`contains(Object o)`:** Returns true if the set contains the specified element.
3. **`remove(Object o)`:** Removes the specified element from the set if it is present.
4. **`size()`:** Returns the number of elements in the set.
5. **`isEmpty()`:** Returns true if the set contains no elements.
6. **`clear()`:** Removes all of the elements from the set.

##### Examples:
```java
Set<String> set = new HashSet<>();

// Adding elements
set.add("apple");

// Checking if element is present
boolean containsApple = set.contains("apple");

// Removing element
boolean removed = set.remove("apple");

// Getting size of the set
int setSize = set.size();

// Checking if set is empty
boolean empty = set.isEmpty();

// Clearing the set
set.clear();
```

These methods provide essential functionality for manipulating and accessing elements within sets in Java. They allow you to add, retrieve, remove, and check the size and emptiness of sets efficiently.

The `NavigableSet` interface provides additional methods for navigating and querying sorted sets, enhancing the functionality of the `TreeSet` implementation. Let's explore these methods:

### Method Descriptions:

1. **`E lower(E e)`:**
  - Returns the greatest element in this set strictly less than the given element `e`, or null if there is no such element.
  - Example: If the set contains {1, 3, 5, 7} and `e` is 4, `lower(4)` returns 3.

2. **`E floor(E e)`:**
  - Returns the greatest element in this set less than or equal to the given element `e`, or null if there is no such element.
  - Example: If the set contains {1, 3, 5, 7} and `e` is 4, `floor(4)` returns 4.

3. **`E ceiling(E e)`:**
  - Returns the smallest element in this set greater than or equal to the given element `e`, or null if there is no such element.
  - Example: If the set contains {1, 3, 5, 7} and `e` is 4, `ceiling(4)` returns 5.

4. **`E higher(E e)`:**
  - Returns the smallest element in this set strictly greater than the given element `e`, or null if there is no such element.
  - Example: If the set contains {1, 3, 5, 7} and `e` is 4, `higher(4)` returns 5.

### Example Usage:
```java
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        NavigableSet<Integer> set = new TreeSet<>();
        
        // Adding elements
        set.add(2);
        set.add(4);
        set.add(6);
        set.add(8);
        
        // Using NavigableSet methods
        System.out.println("Lower than 5: " + set.lower(5)); // Output: 4
        System.out.println("Floor of 5: " + set.floor(5));   // Output: 4
        System.out.println("Ceiling of 5: " + set.ceiling(5)); // Output: 6
        System.out.println("Higher than 5: " + set.higher(5)); // Output: 6
    }
}
```

In this example, we create a `TreeSet` and add some elements to it. Then, we use the `NavigableSet` methods to perform operations such as finding elements lower than, higher than, or equal to a given element. These methods are particularly useful when working with sorted sets like `TreeSet`.

### Using the Queue Interface

The Queue interface in Java represents a collection designed for holding elements prior to processing, following the First-In-First-Out (FIFO) principle. Here's an overview of using the Queue interface:

##### Characteristics of Queue:
- **Ordering**: Elements are added to the end of the queue and removed from the front, maintaining a specific order.
- **FIFO Principle**: The element that is added first is the one that is removed first.
- **Usage**: Queues are commonly used when elements need to be processed in the order they were added, such as in task scheduling or event handling.

##### Common Methods of Queue:
- **`boolean offer(E e)`:** Adds the specified element to the end of the queue if possible, returning true upon success.
- **`E poll()`:** Retrieves and removes the head of the queue, returning null if the queue is empty.
- **`E peek()`:** Retrieves, but does not remove, the head of the queue, returning null if the queue is empty.
- **`boolean isEmpty()`:** Returns true if the queue contains no elements.
- **`int size()`:** Returns the number of elements in the queue.

#### Comparing Queue Implementations

When comparing LinkedList and ArrayDeque for implementing the Queue interface, there are several factors to consider, including performance, memory usage, and suitability for specific use cases:

##### LinkedList:
- **Internal Implementation**: Implemented as a doubly-linked list.
- **Performance**:
  - **Insertion/Deletion at Both Ends**: O(1) time complexity for adding or removing elements from both the beginning and end of the list.
  - **Random Access**: O(n) time complexity for random access operations.
- **Memory Usage**: May consume more memory than ArrayDeque due to the overhead of maintaining node references.
- **Suitability**:
  - Suitable for scenarios where frequent insertion or deletion at both ends of the queue is required.
  - Ideal when memory usage is not a concern, and the queue size is relatively small or predictable.

##### ArrayDeque:
- **Internal Implementation**: Implemented as a resizable array.
- **Performance**:
  - **Insertion/Deletion at Both Ends**: O(1) amortized time complexity for adding or removing elements from both the beginning and end of the deque.
  - **Random Access**: O(1) time complexity for random access operations.
- **Memory Usage**: Generally consumes less memory than LinkedList due to the continuous array storage.
- **Suitability**:
  - Suitable for scenarios where fast insertion or deletion at both ends of the deque is required.
  - Ideal when memory efficiency is a concern or when the queue size may grow dynamically.

##### Choosing Between LinkedList and ArrayDeque:
- **Performance Consideration**: ArrayDeque generally offers better performance for most operations due to its constant-time complexity for adding or removing elements at both ends of the deque.
- **Memory Consideration**: If memory efficiency is crucial or if the queue size may grow dynamically, ArrayDeque may be more suitable.
- **Specific Use Cases**: Evaluate the specific requirements and access patterns of your application to determine the most suitable implementation.

#### Working with Queue Methods

Here's an overview of the common methods for working with queues in Java, along with their descriptions:

### Adding Elements:
- **`boolean add(E e)`:** Adds the specified element to the end of the queue. Throws an exception if the queue is full.
- **`boolean offer(E e)`:** Adds the specified element to the end of the queue. Returns true if successful, false if the queue is full.

### Removing Elements:
- **`E remove()`:** Removes and returns the first element of the queue. Throws an exception if the queue is empty.
- **`E poll()`:** Removes and returns the first element of the queue. Returns null if the queue is empty.

### Accessing First Element:
- **`E element()`:** Returns the first element of the queue without removing it. Throws an exception if the queue is empty.
- **`E peek()`:** Returns the first element of the queue without removing it. Returns null if the queue is empty.

### Queue and Stack Methods:
- **`void push(E e)`:** Adds an element to the front of the queue. This method is typically used for implementing stacks.

### Stack Methods (Not Part of Queue Interface):
- **`E pop()`:** Removes and returns the first element of the stack (LIFO order). Throws an exception if the stack is empty.

These methods provide essential functionality for manipulating and accessing elements within queues and stacks in Java. They allow you to add, remove, and access elements efficiently, following the FIFO (First-In-First-Out) principle for queues and the LIFO (Last-In-First-Out) principle for stacks.

```java
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
  public static void main(String[] args) {
    // Create a queue using LinkedList
    Queue<String> queue = new LinkedList<>();

    // Use the offer() method to add elements to the queue
    queue.offer("Apple");
    queue.offer("Banana");
    queue.offer("Orange");

    // Use the peek() method to view the next element without removing it
    System.out.println("Next element in the queue: " + queue.peek()); // Next element in the queue: Apple

    // Use the poll() method to remove and return the next element
    String removedElement = queue.poll();
    System.out.println("Removed element from the queue: " + removedElement); // Removed element from the queue: Apple

    // Use the element() method to get the next element without removing it
    String nextElement = queue.element();
    System.out.println("Next element in the queue: " + nextElement); // Next element in the queue: Banana

    // Use the remove() method to remove and return the next element
    String removedElement2 = queue.remove();
    System.out.println("Removed element from the queue: " + removedElement2); // Removed element from the queue: Banana
  }
}
```

### Map

Maps are advantageous when you need to associate keys with values, providing efficient lookup, insertion, and deletion operations based on the keys. Here's why and when you should use a Map:

##### Advantages of Map:
1. **Fast Lookup**: Maps provide fast lookup times, typically O(1) or O(log n) depending on the implementation, allowing you to retrieve values associated with keys quickly.
2. **Key-Value Association**: Maps allow you to establish a relationship between keys and values, making it easy to store and retrieve data based on keys.
3. **Efficient Updates**: Maps support efficient updates, enabling you to modify the value associated with a key or insert new key-value pairs with minimal overhead.
4. **No Duplicate Keys**: Maps do not allow duplicate keys, ensuring each key uniquely identifies a value.
5. **Versatile Use Cases**: Maps are versatile and can be used for various purposes, such as caching, indexing, counting occurrences, and implementing data structures like hash tables.

##### When to Use a Map:
- **Unique Key-Value Associations**: When you need to store unique associations between keys and values, such as storing user preferences, configuration settings, or database records.
- **Fast Lookup Requirements**: When you require fast lookup operations based on keys, such as searching for elements by ID, indexing data, or implementing a dictionary.
- **Grouping and Aggregation**: When you need to group or aggregate data based on common attributes or keys, such as grouping items by category or summarizing sales data by region.
- **Efficient Data Retrieval**: When you need to efficiently retrieve, update, or delete data based on keys without traversing the entire collection, such as implementing a cache or managing session data in web applications.

##### Example Use Cases:
1. **Storing User Information**: Storing user profiles where each user ID (key) is associated with user details (value).
2. **Caching Data**: Caching frequently accessed data where keys represent query parameters, and values represent query results.
3. **Indexing Documents**: Indexing documents in a search engine where keys represent terms, and values represent document IDs or occurrences.
4. **Counting Occurrences**: Counting occurrences of elements in a collection where keys represent elements, and values represent counts.
5. **Implementing Databases**: Implementing in-memory databases or key-value stores where keys represent primary keys, and values represent records.

In summary, use a Map when you need to establish a relationship between keys and values, require efficient lookup operations, and want to leverage key-value associations for various data manipulation tasks. Maps provide a versatile and efficient way to manage and access data based on keys in Java applications.

#### Comparing Map Implementations

When comparing HashMap and TreeMap for implementing the Map interface in Java, several factors come into play, including performance, ordering, memory usage, and suitability for specific use cases:

##### HashMap:
- **Internal Implementation**: Implemented as a hash table using an array of buckets with linked lists of entries for collision resolution.
- **Performance**:
  - **Insertion/Deletion/Search**: O(1) average-case time complexity for insertion, deletion, and search operations, assuming a good hash function and load factor.
  - **Ordering**: Elements are not ordered. Iteration order is not guaranteed and may change over time.
- **Memory Usage**: Generally consumes less memory than TreeMap due to simpler internal structure.
- **Suitability**:
  - Suitable for scenarios where fast insertion, deletion, and lookup operations are required, and the order of elements is not important.
  - Ideal when memory efficiency and performance for basic map operations are critical.

##### TreeMap:
- **Internal Implementation**: Implemented as a Red-Black tree, a balanced binary search tree.
- **Performance**:
  - **Insertion/Deletion/Search**: O(log n) time complexity for insertion, deletion, and search operations, where n is the number of elements in the map.
  - **Ordering**: Elements are ordered based on their natural ordering or a custom Comparator provided during TreeMap initialization. Iteration order follows the natural order of keys.
- **Memory Usage**: Generally consumes more memory than HashMap due to the overhead of maintaining the tree structure.
- **Suitability**:
  - Suitable for scenarios where elements need to be maintained in sorted order based on keys and efficient search operations are required.
  - Ideal when you need a sorted map with guaranteed ordering and support for range queries or operations.

##### Choosing Between HashMap and TreeMap:
- **Performance Consideration**: HashMap typically offers better performance for most operations due to its constant-time complexity for common operations. However, TreeMap provides guaranteed ordering of elements, which can be beneficial for certain use cases.
- **Memory Consideration**: TreeMap generally consumes more memory than HashMap due to the additional overhead of maintaining the tree structure.
- **Ordered Requirement**: If you need elements to be stored in sorted order and efficient search operations, TreeMap is the preferred choice. Otherwise, HashMap may be more suitable for its faster performance and lower memory usage.

##### Example Usage:
```java
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapComparisonExample {
    public static void main(String[] args) {
        // HashMap example
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "Three");
        hashMap.put(1, "One");
        hashMap.put(2, "Two");

        // TreeMap example
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "Three");
        treeMap.put(1, "One");
        treeMap.put(2, "Two");

        System.out.println("HashMap: " + hashMap); // Order may vary
        System.out.println("TreeMap: " + treeMap); // Elements are sorted by keys
    }
}
```

In this example, we create maps using both HashMap and TreeMap implementations. HashMap does not guarantee the ordering of elements, while TreeMap stores elements in sorted order based on their natural ordering. Depending on your specific requirements regarding ordering, performance, and memory usage, you can choose the appropriate implementation.

#### Working with Map Methods

Here's an overview of common methods for working with maps in Java, along with their descriptions and examples:

##### Working with Map Methods:

| Method                            | Description                                                              | Example                                             |
|-----------------------------------|--------------------------------------------------------------------------|-----------------------------------------------------|
| `void clear()`                    | Removes all keys and values from the map.                                | `map.clear();`                                      |
| `boolean isEmpty()`               | Returns whether the map is empty.                                        | `boolean empty = map.isEmpty();`                    |
| `int size()`                      | Returns the number of entries (key/value pairs) in the map.              | `int mapSize = map.size();`                         |
| `V get(Object key)`               | Returns the value mapped by the specified key, or null if none is found. | `Integer value = map.get("key");`                  |
| `V put(K key, V value)`           | Associates the specified value with the specified key in the map.        | `Integer oldValue = map.put("key", value);`        |
| `V remove(Object key)`            | Removes the mapping for the specified key from the map, if present.      | `Integer removedValue = map.remove("key");`        |
| `boolean containsKey(Object key)` | Returns true if the map contains a mapping for the specified key.        | `boolean containsKey = map.containsKey("key");`    |
| `boolean containsValue(Object)`   | Returns true if the map contains one or more mappings to the specified value. | `boolean containsValue = map.containsValue(value);` |
| `Set<K> keySet()`                 | Returns a set view of the keys contained in the map.                     | `Set<K> keys = map.keySet();`                       |
| `Collection<V> values()`          | Returns a collection view of the values contained in the map.            | `Collection<V> allValues = map.values();`           |

##### Example Usage:
```java
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        // Creating a HashMap
        Map<String, Integer> map = new HashMap<>();

        // Adding key-value pairs
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Removing a key-value pair
        Integer removedValue = map.remove("two");

        // Checking if a key exists
        boolean containsKey = map.containsKey("one");

        // Checking if a value exists
        boolean containsValue = map.containsValue(3);

        // Getting the size of the map
        int mapSize = map.size();

        // Clearing the map
        map.clear();
        
        // Checking if the map is empty
        boolean empty = map.isEmpty();
    }
}
```

In this example, we create a HashMap and perform various operations using the provided methods:
- Adding key-value pairs using `put()`.
- Removing a key-value pair using `remove()`.
- Checking if a key exists using `containsKey()`.
- Checking if a value exists using `containsValue()`.
- Getting the size of the map using `size()`.
- Clearing the map using `clear()`.
- Checking if the map is empty using `isEmpty()`.

### Comparing Collection Types

##### Java Collections Framework types

| Type     | Can contain duplicate elements? | Elements ordered?                | Has keys and values? | Must add/remove in specific order?    |
|----------|---------------------------------|----------------------------------|----------------------|---------------------------------------|
| List     | Yes                             | Yes (by index)                   | No                   | No                                    |
| Map      | Yes (for values)                | No                               | Yes                  | No                                    |
| Queue    | Yes                             | Yes (retrieved in defined order) | No                   | Yes                                   |
| Set      | No                              | No                               | No                   | No                                    |


##### Collection attributes

1. Interface: List

| Type        | Sorted? | Calls hashCode? | Calls compareTo? |
|-------------|---------|-----------------|------------------|
| ArrayList   | No      | No              | No               |
| LinkedList  | No      | No              | No               |
| Stack       | No      | No              | No               |
| Vector      | No      | No              | No               |

2. Interface: Queue

| Type        | Sorted? | Calls hashCode? | Calls compareTo? |
|-------------|---------|-----------------|------------------|
| ArrayDeque  | No      | No              | No               |
| LinkedList  | No      | No              | No               |

3. Interface: Map

| Type        | Sorted? | Calls hashCode? | Calls compareTo? |
|-------------|---------|-----------------|------------------|
| HashMap     | No      | Yes             | No               |
| Hashtable   | No      | Yes             | No               |
| TreeMap     | Yes     | No              | Yes              |

4. Interface: Set

| Type        | Sorted? | Calls hashCode? | Calls compareTo? |
|-------------|---------|-----------------|------------------|
| HashSet     | No      | Yes             | No               |
| TreeSet     | Yes     | No              | Yes              |

- All data structures allow nulls except these:
  + TreeMap — no null keys
  + Hashtable — no null keys or values
  + TreeSet — no null elements
  + ArrayDeque — no null elements

##### Choosing the right collection type

| Which class do you choose when you want ____________                                                                                                                                 | Answer (single best type) | Reason                                                                                                                                                                                                                                                                        |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| to pick the top zoo map off a stack of maps                                                                                                                                          | ArrayDeque                | The description is of a last-in, first-out data structure, so you need a stack, which is a type of Queue. (Stack would also match this description, but it shouldn’t be used for new code.)                                                                                   |
| to sell tickets to people in the order in which they appear in line and tell them their position in line                                                                             | LinkedList                | The description is of a first-in, first-out data structure, so you need a queue. You also needed indexes, and LinkedList is the only class to match both requirements.                                                                                                        |
| to write down the first names of all of the elephants so that you can tell them to your friend’s three-year-old every time she asks. (The elephants do not have unique first names.) | ArrayList                 | Since there are duplicates, you need a list rather than a set. You will be accessing the list more often than updating it, since three-year-olds ask the same question over and over, making an ArrayList better than a LinkedList. Vector and Stack aren’t used in new code. |
| to list the unique animals that you want to see at the zoo today                                                                                                                     | HashSet                   | The keyword in the description is unique. When you see “unique,” think “set.” Since there were no requirements to have a sorted order or to remember the insertion order, you use the most efficient set.                                                                     |
| to list the unique animals that you want to see at the zoo today in alphabetical order                                                                                               | TreeSet                   | Since it says “unique,” you need a set. This time, you need to sort, so you cannot use a HashSet.                                                                                                                                                                             |
| to look up animals based on a unique identifier                                                                                                                                      | HashMap                   | Looking up by key should make you think of a map. Since you have no ordering or sorting requirements, you should use the most basic map.                                                                                                                                      |

### Comparator vs. Comparable

In Java, you can sort objects that you create by implementing the Comparable interface. This interface allows objects to define their natural ordering, enabling them to be used in data structures like lists, sets, and arrays that require comparison. Additionally, Java provides a class called Comparator, which allows you to specify a custom order for objects if you want to sort them differently than their natural order.

#### Comparable

The Comparable interface in Java allows objects to define their natural ordering, enabling them to be sorted based on their inherent properties. Here's a comprehensive overview of Comparable:

###### Purpose:
- **Defining Natural Ordering**: Comparable provides a way for objects to define their natural ordering, which is based on certain properties or attributes of the objects.

###### Usage:
- **Interface Implementation**: Classes that need to define their natural ordering implement the Comparable interface.

###### Method:
- **compareTo()**: The key method defined in Comparable. It compares the current object with another object of the same type and returns an integer value:
  - Negative value: Indicates that the current object is less than the other object.
  - Zero: Indicates that the current object is equal to the other object.
  - Positive value: Indicates that the current object is greater than the other object.
- Example:
  ```java
  public class MyClass implements Comparable<MyClass> {
      private int value;

      // Constructor, getters, setters

      @Override
      public int compareTo(MyClass other) {
          return Integer.compare(this.value, other.value);
      }
  }
  ```

###### Usage:
- **Sorting**: Objects implementing Comparable can be sorted using methods like `Collections.sort()` or `Arrays.sort()`.
- **Binary Search**: Classes implementing Comparable can also be used with methods like `Collections.binarySearch()` for binary search operations.

###### Considerations:
- **Consistent with equals()**: The natural ordering defined by compareTo() should be consistent with the equals() method to ensure consistent behavior across collections and algorithms.

###### Benefits:
- **Simplicity**: Provides a straightforward way to define the natural ordering of objects.
- **Integration**: Objects implementing Comparable seamlessly integrate with existing Java APIs for sorting and searching.

###### Example Use Cases:
- Sorting lists of custom objects based on a specific property (e.g., sorting a list of Person objects based on age).
- Storing objects in sorted collections like TreeSet to maintain a sorted order based on their natural ordering.

###### Tips:
- Implementing Comparable enables more efficient sorting and searching of objects in Java.
- Ensure that the compareTo() method adheres to the natural ordering semantics to avoid unexpected behavior.

In summary, the Comparable interface in Java allows objects to define their natural ordering, making them sortable and searchable using standard Java APIs. By implementing Comparable, classes can seamlessly integrate with sorting and searching algorithms, providing greater flexibility and efficiency in Java applications.

#### Comparator

The Comparator interface in Java provides a way to define custom ordering for objects, allowing for sorting based on criteria other than the natural ordering of objects. Here's an in-depth presentation of Comparator:

###### Purpose:
- **Custom Ordering**: Comparator allows objects to be sorted based on criteria specified by the Comparator rather than the natural ordering of the objects.

###### Usage:
- **Separate Class or Lambda Expression**: You can implement Comparator as a separate class or use lambda expressions for more concise code.

###### Method:
- **compare()**: The primary method defined in Comparator. It compares two objects and returns an integer value:
  - Negative value: Indicates that the first object is less than the second object.
  - Zero: Indicates that the first object is equal to the second object.
  - Positive value: Indicates that the first object is greater than the second object.
- Example:
  ```java
  public class MyComparator implements Comparator<MyClass> {
      @Override
      public int compare(MyClass obj1, MyClass obj2) {
          // Custom comparison logic
      }
  }
  ```

###### Usage:
- **Sorting**: Comparator instances can be passed to methods like `Collections.sort()` or `Arrays.sort()` to sort objects based on custom criteria.
- **Custom Ordering**: Allows for flexible sorting based on any property or criteria defined by the Comparator.

###### Benefits:
- **Flexibility**: Provides flexibility in defining custom sorting criteria for objects.
- **Reusability**: Comparator implementations can be reused across different sorting scenarios.
- **Separation of Concerns**: Allows for separation of sorting logic from the objects being sorted.

###### Example Use Cases:
- Sorting a list of custom objects based on multiple criteria (e.g., sorting a list of Person objects by age and then by name).
- Defining custom sorting for built-in Java classes that do not implement Comparable (e.g., sorting String objects based on length rather than lexicographic order).

###### Tips:
- Ensure consistency in comparison logic to avoid unexpected behavior during sorting.
- Use lambda expressions for simple comparison logic to reduce boilerplate code.

In summary, the Comparator interface in Java provides a powerful mechanism for defining custom ordering for objects, allowing for flexible sorting based on specific criteria. By implementing Comparator, developers gain greater control over sorting behavior, enabling efficient and customized sorting in Java applications.

#### Comparison of Comparable and Comparator

Comparing Comparable and Comparator in Java provides insight into their respective roles in sorting objects and allows for a better understanding of when to use each interface. Here's a comprehensive comparison:

###### Comparable Interface:
- **Purpose**: Defines natural ordering for objects within the class itself.
- **Usage**: Implemented by the class whose objects need to be sorted.
- **Method**: Define the `compareTo()` method within the class to compare the current object with another object of the same type.
- **Sorting**: Objects implementing Comparable can be sorted using methods like `Collections.sort()` or `Arrays.sort()`.
- **Example**: Sorting a list of objects based on their inherent properties like age or name.
- **Benefits**:
  - Simplicity: Provides a straightforward way to define natural ordering.
  - Integration: Seamlessly integrates with Java APIs for sorting and searching.

###### Comparator Interface:
- **Purpose**: Defines custom ordering for objects separate from their natural ordering.
- **Usage**: Implemented as a separate class or using lambda expressions to specify custom sorting criteria.
- **Method**: Implement the `compare()` method to compare two objects based on custom criteria.
- **Sorting**: Comparator instances can be passed to sorting methods like `Collections.sort()` to sort objects based on custom criteria.
- **Example**: Sorting a list of objects based on properties different from their inherent properties, such as sorting a list of Person objects by their salary.
- **Benefits**:
  - Flexibility: Allows for custom sorting based on any property or criteria defined by the Comparator.
  - Reusability: Comparator implementations can be reused across different sorting scenarios.

###### When to Use Each:
- **Comparable**:
  - Use when objects have a natural ordering that should be consistent across collections and algorithms.
  - Suitable when the sorting criteria are inherent to the objects themselves.
- **Comparator**:
  - Use when you need to sort objects based on different criteria or when objects do not implement Comparable.
  - Ideal for scenarios where the sorting criteria are external to the objects being sorted or need to be dynamically defined.

###### Considerations:
- **Consistency**: Comparable's `compareTo()` should be consistent with the equals() method, while Comparator's `compare()` method can define any custom comparison logic.
- **Integration**: Comparable integrates with existing Java APIs and collections, while Comparator provides more flexibility in defining sorting criteria.

###### Example Use Cases:
- Sorting a list of Person objects by age (Comparable).
- Sorting a list of Employee objects by salary (Comparator).

###### Tips:
- Understand the distinction between natural ordering and custom ordering when deciding between Comparable and Comparator.
- Choose the appropriate interface based on the sorting requirements and the nature of the objects being sorted.

In summary, Comparable and Comparator offer distinct approaches to sorting objects in Java, providing flexibility and control over the sorting process. By understanding their differences and use cases, developers can effectively implement sorting logic tailored to their specific requirements.

### Searching and Sorting

Searching and sorting in Java with Comparable and Comparator interfaces offer powerful mechanisms to efficiently organize and retrieve data. Here's a detailed presentation on how to perform searching and sorting using these interfaces:

###### Searching and Sorting with Comparable:
- **Sorting**: Objects implementing Comparable can be sorted using methods like `Collections.sort()` or `Arrays.sort()`.
  - Example:
    ```java
    List<MyClass> list = new ArrayList<>();
    list.add(new MyClass(3));
    list.add(new MyClass(1));
    list.add(new MyClass(2));
    Collections.sort(list); // Sort using Comparable
    ```
- **Binary Search**: Once sorted, you can use methods like `Collections.binarySearch()` to perform binary search operations.
  - Example:
    ```java
    int index = Collections.binarySearch(list, new MyClass(2)); // Binary search using Comparable
    ```

###### Searching and Sorting with Comparator:
- **Sorting**: Comparator instances can be passed to sorting methods like `Collections.sort()` to sort objects based on custom criteria.
  - Example:
    ```java
    List<MyClass> list = new ArrayList<>();
    list.add(new MyClass(3));
    list.add(new MyClass(1));
    list.add(new MyClass(2));
    Collections.sort(list, new MyComparator()); // Sort using Comparator
    ```
- **Binary Search**: When using Comparator for sorting, you need to specify the same Comparator instance for binary search.
  - Example:
    ```java
    int index = Collections.binarySearch(list, new MyClass(2), new MyComparator()); // Binary search using Comparator
    ```

###### Best Practices:
- **Consistency**: Ensure that the comparison logic is consistent between sorting and searching operations.
- **Efficiency**: Use binary search for sorted lists to achieve O(log n) time complexity.
- **Comparator Reusability**: Reuse Comparator instances for consistent sorting behavior across multiple operations.

###### Example Use Cases:
- Sorting a list of Employee objects by salary using Comparator.
- Searching for a specific object in a sorted list of Person objects based on age using Comparable.

###### Tips:
- Understand the difference between natural ordering (Comparable) and custom ordering (Comparator) when choosing the appropriate interface.
- Use Comparable for classes where the sorting criteria are inherent to the objects themselves.
- Use Comparator for defining custom sorting criteria external to the objects being sorted or for dynamic sorting requirements.

By leveraging Comparable and Comparator interfaces, Java developers can efficiently search and sort collections of objects based on their natural or custom ordering criteria, offering flexibility and performance in data manipulation operations.

### Additions in Java 8

#### Using Method References

Method references provide a concise way to refer to methods or constructors using a special syntax. Let's explore how method references work and how they can be used effectively in Java:

###### What are Method References?
Method references allow you to treat methods as first-class citizens, enabling you to pass them around as arguments to functions or assign them to variables. They provide a compact and readable alternative to lambda expressions when you're simply invoking an existing method.

###### Syntax:
There are different types of method references, each corresponding to a different method signature. The general syntax for method references is:

```
ContainingClass::methodName
```

###### Types of Method References:
1. **Reference to a Static Method**:
   ```
   ContainingClass::staticMethodName
   ```

2. **Reference to an Instance Method of a Particular Object**:
   ```
   object::instanceMethodName
   ```

3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type**:
   ```
   ContainingClass::instanceMethodName
   ```

4. **Reference to a Constructor**:
   ```
   ClassName::new
   ```

###### Example Usages:
1. **Reference to a Static Method**:
   ```java
   // Using lambda expression
   Function<String, Integer> parseInt = s -> Integer.parseInt(s);

   // Using method reference
   Function<String, Integer> parseInt = Integer::parseInt;
   ```

2. **Reference to an Instance Method of a Particular Object**:
   ```java
   // Using lambda expression
   Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

   // Using method reference
   Consumer<String> printUpperCase = System.out::println;
   ```

3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type**:
   ```java
   // Using lambda expression
   Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);

   // Using method reference
   Comparator<String> comparator = String::compareToIgnoreCase;
   ```

4. **Reference to a Constructor**:
   ```java
   // Using lambda expression
   Supplier<List<String>> listSupplier = () -> new ArrayList<>();

   // Using method reference
   Supplier<List<String>> listSupplier = ArrayList::new;
   ```

###### When to Use Method References:
- Use method references when you're simply delegating to an existing method without adding additional logic.
- They can improve readability and conciseness, especially for simple method invocations.

###### Tips:
- Understand the different types of method references and their corresponding syntax.
- Practice using method references to become familiar with their usage and advantages.
- Use them judiciously, especially in situations where they enhance code readability and maintainability.

By leveraging method references, Java developers can write cleaner and more expressive code, enhancing the readability and maintainability of their applications. They offer a powerful tool for simplifying method invocations and promoting a functional programming style in Java.

#### Removing Conditionally

Removing elements from a collection based on certain conditions is a common task in Java programming. Let's explore various approaches, including the use of lambda expressions and the removeIf() method introduced in Java 8:

###### Traditional Approach:
In pre-Java 8 versions, you can iterate over the collection and remove elements that meet the specified condition using an Iterator:

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);

Iterator<Integer> iterator = numbers.iterator();
while (iterator.hasNext()) {
    Integer number = iterator.next();
    if (number % 2 == 0) {
        iterator.remove(); // Remove even numbers
    }
}
```

###### Using Lambda Expressions (Java 8+):
With Java 8, lambda expressions provide a more concise and expressive way to remove elements conditionally using the removeIf() method:

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);

numbers.removeIf(number -> number % 2 == 0); // Remove even numbers
```

###### When to Use Each Approach:
- **Traditional Approach**: Use when working with versions of Java prior to Java 8 or when you need to perform complex removal logic that is not easily expressed with a lambda.
- **Lambda Expressions and removeIf()**: Use in Java 8 or later for simpler and more concise removal of elements based on straightforward conditions.

###### Tips:
- Prefer the removeIf() method with lambda expressions when working with Java 8 or later, as it provides cleaner and more readable code.
- Be cautious when modifying a collection while iterating over it to avoid ConcurrentModificationException.

By utilizing these techniques, you can efficiently remove elements from collections based on various conditions, improving code clarity and maintainability in your Java applications.

#### Updating All Elements

Updating all elements in a collection with a specific transformation is a common operation in Java programming. Let's explore how to achieve this using various approaches, including traditional loops and functional programming features introduced in Java 8:

###### Traditional Approach:
In pre-Java 8 versions, you can iterate over the collection and update each element individually:

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);

for (int i = 0; i < numbers.size(); i++) {
    numbers.set(i, numbers.get(i) * 2); // Double each element
}
```

###### Using Stream API and Lambda Expressions (Java 8+):
With Java 8, you can utilize the Stream API along with lambda expressions to apply a transformation to all elements in the collection:

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);

numbers.replaceAll(number -> number * 2); // Double each element
```

###### When to Use Each Approach:
- **Traditional Approach**: Suitable for versions of Java prior to Java 8 or when working with complex update logic that is not easily expressed with lambda expressions.
- **Stream API and Lambda Expressions**: Preferred in Java 8 or later for simpler and more concise transformation of all elements in a collection.

###### Tips:
- Prefer the use of lambda expressions and functional programming features introduced in Java 8 when applicable, as they provide cleaner and more readable code.
- Be cautious when modifying a collection while iterating over it to avoid ConcurrentModificationException.

By leveraging these techniques, you can efficiently update all elements in a collection with a specific transformation, enhancing code clarity and maintainability in your Java applications.

#### Looping through a Collection

Looping through a collection is a fundamental operation in Java programming, allowing you to iterate over the elements and perform various tasks. Let's explore different approaches for looping through collections:

###### Traditional Approach with Enhanced for-loop:
Using the enhanced for-loop (also known as the "for-each" loop) introduced in Java 5, you can iterate through each element of the collection:

```java
List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");

for (String fruit : fruits) {
    System.out.println(fruit);
}
```

###### Using Iterator:
You can use an Iterator to manually iterate over the collection's elements and perform operations:

```java
List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
Iterator<String> iterator = fruits.iterator();

while (iterator.hasNext()) {
    String fruit = iterator.next();
    System.out.println(fruit);
}
```

###### Using Stream API (Java 8+):
With Java 8, you can use the Stream API to process collections in a functional style:

```java
List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");

fruits.stream().forEach(fruit -> System.out.println(fruit));
```

###### When to Use Each Approach:
- **Enhanced for-loop**: Use when you want a simple and concise way to iterate over all elements of a collection without explicit indexing.
- **Iterator**: Use when you need more control over the iteration process, such as removing elements during iteration or iterating over multiple collections simultaneously.
- **Stream API**: Use when working with Java 8 or later and when you want to leverage functional programming features like mapping, filtering, and reducing.

###### Tips:
- Choose the appropriate looping approach based on the requirements of your task and the version of Java you are using.
- Be cautious when modifying a collection while iterating over it to avoid ConcurrentModificationException.

By leveraging these looping techniques, you can effectively traverse collections and perform tasks on their elements in Java applications, improving code readability and maintainability.

#### Using New Java 8 Map APIs 

###### merge()

In Java 8, the Map interface introduced several new methods to simplify common tasks when working with maps. One of these methods is `merge()`, which provides a convenient way to update the value associated with a specified key in the map. Let's explore how to use the `merge()` method:

1. Syntax:
```java
V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
```

2. Parameters:
- `key`: The key whose associated value is to be merged.
- `value`: The value to be merged with the existing value for the specified key.
- `remappingFunction`: A function to compute the new value by combining the existing value and the new value, if the key is present.

3. Behavior:
- If the specified key is not present in the map, the `merge()` method adds the key-value pair to the map.
- If the specified key is present and the remapping function returns a non-null value, the existing value associated with the key is replaced with the new value.
- If the specified key is present and the remapping function returns null, the key is removed from the map.

4. Use Cases:
- Updating values in a map based on certain conditions or computations.
- Handling scenarios where you need to add or modify values in a map based on specific rules.

5. Tips:
- Ensure that the remapping function provided to `merge()` returns a non-null value if you want to update the existing value.
- Use lambda expressions or method references to define the remapping function concisely.

By using the `merge()` method, you can easily update values in a map without the need for additional checks or conditional logic, simplifying your code and making it more expressive.

```java
import java.util.HashMap;
import java.util.Map;

public class MergeExample {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        
        scores.put("Alice", 10);
        scores.put("Bob", 20);
        scores.put("Charlie", 30);
        

        scores.merge("Alice", 5, (oldValue, newValue) -> oldValue + newValue);
        // Scores after merging: {Alice=15, Bob=20, Charlie=30}
        System.out.println("Scores after merging: " + scores);
        
        scores.merge("Bob", null, (oldValue, newValue) -> oldValue + newValue);
        // Scores after merging with null: {Alice=15, Bob=null, Charlie=30}
        System.out.println("Scores after merging with null: " + scores);
        
        scores.merge("David", null, (oldValue, newValue) -> oldValue + newValue);
        // Scores after merging with missing key: {Alice=15, Bob=null, Charlie=30, David=null}
        System.out.println("Scores after merging with missing key: " + scores);
        
        scores.merge("Charlie", null, (oldValue, newValue) -> null);
        // Scores after merging with null value returned: {Alice=15, Bob=null, David=null}
        System.out.println("Scores after merging with null value returned: " + scores);
    }
}
```

###### computeIfPresent() and computeIfAbsent()
In Java 8, the `Map` interface introduced two powerful methods: `computeIfPresent()` and `computeIfAbsent()`. These methods provide a convenient way to compute new values for existing keys or add values for absent keys based on specific conditions. Let's explore how to use them:

1. `computeIfPresent()` Method:
This method computes a new value for the specified key if the key is present in the map, using the provided mapping function.

2. Syntax:
```java
V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
```

3. Example:
```java
```java
Map<String, Integer> counts = new HashMap<>();
counts.put("Jenny", 1);
BiFunction<String, Integer, Integer> mapper = (k, v) -> v + 1;
Integer jenny = counts.computeIfPresent("Jenny", mapper);
Integer sam = counts.computeIfPresent("Sam", mapper);
System.out.println(counts); // {Jenny=2}
System.out.println(jenny); // 2
System.out.println(sam); // null
```
```

4. `computeIfAbsent()` Method:
This method computes a new value for the specified key if the key is not already associated with a value or is mapped to null, using the provided mapping function.

5. Syntax:
```java
V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
```

6. Example:

- For computeIfAbsent(), the functional interface runs only when the key isn’t present or is null:

```java
Map<String, Integer> counts = new HashMap<>();
counts.put("Jenny", 15);
counts.put("Tom", null);
Function<String, Integer> mapper = (k) -> 1;
Integer jenny = counts.computeIfAbsent("Jenny", mapper); // 15
Integer sam = counts.computeIfAbsent("Sam", mapper); // 1
Integer tom = counts.computeIfAbsent("Tom", mapper); // 1
System.out.println(counts); // {Tom=1, Jenny=15, Sam=1}
```

7. Use Cases:
- **`computeIfPresent()`**:
  - Updating values in a map based on specific conditions when the key is present.
  - Applying transformations to existing values.

- **`computeIfAbsent()`**:
  - Adding default values for keys that are not already present in the map.
  - Computing new values for absent keys based on specific criteria.

8. Tips:
- Ensure that the mapping functions provided to `computeIfPresent()` and `computeIfAbsent()` are pure functions, i.e., they do not have side effects.
- Use lambda expressions or method references to define mapping functions concisely.

By leveraging `computeIfPresent()` and `computeIfAbsent()` methods, you can efficiently compute new values for existing keys or add values for absent keys based on specific conditions, enhancing the flexibility and usability of your Java applications.

**The basics of the merge and compute methods**

Here's a comparison of the scenarios and behaviors of `merge()`, `computeIfAbsent()`, and `computeIfPresent()` methods in Java, along with the functional interfaces they use:

| Scenario                    | `merge`                                                                  | `computeIfAbsent`                                  | `computeIfPresent`                                              |
|-----------------------------|--------------------------------------------------------------------------|----------------------------------------------------|-----------------------------------------------------------------|
| Key already in map          | Result of function is applied to existing value.                         | No action.                                         | Result of function is applied to existing value.                |
| Key not already in map      | Result of function is added to the map.                                  | Add new value to map.                              | No action.                                                      |
| Functional Interface used   | `BiFunction` (Takes existing value and new value. Returns new value.)    | `Function` (Takes key and returns new value.)      | `BiFunction` (Takes key and existing value. Returns new value.) | 

These methods provide powerful ways to update maps based on specific conditions, adding flexibility and convenience to map manipulation in Java programming.

**Merge and compute methods when nulls are involved**

Here's a comparison of the behaviors of `merge()`, `computeIfAbsent()`, and `computeIfPresent()` methods in various scenarios when dealing with null values in the map and the mapping functions:

| Key has               | Mapping function returns | `merge`                                            | `computeIfAbsent`                                     | `computeIfPresent`                  |
|-----------------------|---------------------------|----------------------------------------------------|-------------------------------------------------------|-------------------------------------|
| null value in map     | null                      | Remove key from map.                               | Do not change map.                                    | Do not change map.                  |
| null value in map     | Not null                  | Set key to mapping function result.                | Add key to map with mapping function result as value. | Do not change map.                  |
| Non-null value in map | null                      | Remove key from map.                               | Do not change map.                                    | Remove key from map.                |
| Non-null value in map | Not null                  | Set key to mapping function result.                | Do not change map.                                    | Set key to mapping function result. |
| Key not in map        | null                      | Add key to map.                                    | Do not change map.                                    | Do not change map.                  |
| Key not in map        | Not null                  | Add key to map.                                    | Add key to map with mapping function result as value. | Do not change map.                  |

These methods provide flexible ways to handle null values and apply mapping functions to update maps according to specific conditions, enhancing the versatility of map manipulation in Java programming.

# Summary

Generics in Java provide a way to create classes, interfaces, and methods that operate on a type parameter rather than a specific data type. This allows you to write reusable and type-safe code. Here's how to define a generic class in Java:

### Syntax:
To create a generic class, you add a type parameter in angle brackets `<T>` after the class name. You can use any name for the type parameter, but single uppercase letters like `T`, `E`, `K`, and `V` are common conventions.

```java
public class ClassName<T> {
    // Class members and methods
}
```

### Example:
Let's create a simple generic class called `Box` that holds an object of type `T`:

```java
public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
```

### Usage:
You can instantiate a generic class by specifying the type argument in angle brackets when creating an object:

```java
Box<Integer> integerBox = new Box<>();
integerBox.setItem(10);
System.out.println(integerBox.getItem()); // Output: 10

Box<String> stringBox = new Box<>();
stringBox.setItem("Hello");
System.out.println(stringBox.getItem()); // Output: Hello
```

### Benefits of Generics:
1. **Type Safety**: Generics provide compile-time type checking, ensuring type safety and preventing runtime errors.
2. **Code Reusability**: Generic classes and methods can be used with different data types, promoting code reusability.
3. **Reduced Code Duplication**: Generics eliminate the need for writing multiple versions of the same code for different data types.

### Tips:
- Choose meaningful names for type parameters to improve code readability.
- Use generics when writing classes, interfaces, or methods that need to operate on different data types in a type-safe manner.

By utilizing generics, you can write more flexible and reusable code that is less error-prone and easier to maintain in Java applications.

The diamond operator (`<>`) in Java is a shorthand notation introduced in Java 7 that allows you to specify generic types without explicitly providing the type arguments. It infers the type arguments from the context, making code more concise and readable. Here's how it works:

### Usage:
When creating an instance of a generic class, you can use the diamond operator (`<>`) to let the compiler infer the type arguments based on the context:

```java
List<String> names = new ArrayList<>(); // Diamond operator used to infer type argument String
Map<String, Integer> scores = new HashMap<>(); // Diamond operator used to infer type arguments String and Integer
```

### Benefits:
1. **Conciseness**: The diamond operator reduces verbosity by allowing you to omit redundant type arguments, resulting in cleaner and more readable code.
2. **Type Safety**: Despite omitting explicit type arguments, the compiler ensures type safety by inferring the correct types based on the context.

### Example:
Consider the following code snippet without and with the diamond operator:

```java
// Without diamond operator
List<String> names = new ArrayList<String>();

// With diamond operator
List<String> names = new ArrayList<>();
```

Both versions are equivalent, but the latter is more concise and preferred in modern Java programming.

### Limitations:
- The diamond operator can only be used when the compiler can infer the type arguments from the context. It cannot be used with anonymous inner classes or when the type inference is ambiguous.

### Best Practices:
- Use the diamond operator (`<>`) whenever possible to improve code readability and reduce redundancy.
- Be mindful of backward compatibility when using the diamond operator, especially when working with codebases that need to support older Java versions.

By using the diamond operator, you can write more concise and expressive code while maintaining type safety in Java applications.

Generics in Java allow you to specify wildcards, which are used to represent unknown types in generic classes, methods, or interfaces. Wildcards provide flexibility when dealing with generic types. Let's explore the commonly used wildcards:

### Unbounded Wildcard (`<?>`):
- The unbounded wildcard (`<?>`) represents any type.
- It is useful when you want to work with a collection of unknown type.
- It allows you to read from a collection but does not allow adding or removing elements.

### Upper Bounded Wildcard (`<? extends Object>`):
- The upper bounded wildcard (`<? extends Object>`) represents any type that is Object or extends it.
- It is useful when you want to accept a collection of any subtype of a specific type.
- It allows you to read from a collection but does not allow adding or removing elements.

### Upper Bounded Wildcard with Interface (`<? extends MyInterface>`):
- The upper bounded wildcard with an interface (`<? extends MyInterface>`) represents any type that implements the specified interface `MyInterface`.
- It is useful when you want to accept a collection of any subtype of a specific interface.
- Similar to the upper bounded wildcard, it allows you to read from a collection but does not allow adding or removing elements.

### Lower Bounded Wildcard (`<? super Number>`):
- The lower bounded wildcard (`<? super Number>`) represents any type that is `Number` or a superclass of `Number`.
- It is useful when you want to accept a collection of a specific type or its supertypes.
- It allows you to add elements to a collection but does not allow reading elements in a type-safe manner.

### Compiler Error with Unbounded or Upper-Bounded Wildcard:
- A compiler error occurs if you try to add or remove elements from a collection with an unbounded or upper-bounded wildcard.
- This is because the compiler cannot guarantee the type safety of adding or removing elements due to the unknown type.

### Example:
```java
List<?> unboundedList = new ArrayList<>(); // Unbounded wildcard
List<? extends Number> upperBoundedList = new ArrayList<>(); // Upper bounded wildcard

// Compiler error: Cannot add elements to a collection with an unbounded or upper-bounded wildcard
unboundedList.add(10); // Error
upperBoundedList.add(10); // Error
```

### Best Practices:
- Use wildcards to make your code more flexible and reusable.
- Be cautious when using wildcards, especially when adding or removing elements from collections, to ensure type safety.

By understanding and using wildcards effectively, you can write more versatile and type-safe generic code in Java.

When you work with code that doesn't use generics, also known as legacy code or raw types, Java issues compiler warnings to alert you about potential issues. These warnings indicate that the code might not be type-safe and could lead to runtime errors. Here's a breakdown of the concepts involved:

### Compiler Warnings for Legacy Code:
- When you use raw types or ignore type safety by not using generics, Java compiler issues warnings to notify you about potential issues.
- These warnings serve as a reminder to update the code to use generics for better type safety and readability.
- Ignoring these warnings might lead to runtime errors such as `ClassCastException` when incompatible types are encountered at runtime.

### Difference Between Compiler Warning and Compiler Error:
- Compiler warning: Indicates potential issues in the code but still allows the compilation process to proceed, producing a class file.
- Compiler error: Indicates syntax or semantic errors that prevent the compilation process from completing successfully, resulting in the absence of a class file.

### Unboxing and Generics:
- Unboxing, the process of converting wrapper class objects to their primitive types, can lead to compiler errors when generics are not used.
- Without generics, the compiler cannot infer the correct types, leading to potential issues during unboxing operations.

### Example:
```java
// Legacy code without generics
List list = new ArrayList(); // Raw type
list.add("Hello"); // Compiler warning: unchecked call to add(E)
String value = (String) list.get(0); // Compiler warning: unchecked cast

// Unboxing without generics
List<Integer> intList = new ArrayList<>();
intList.add(10);
int intValue = intList.get(0); // Compiler error: incompatible types
```

### Best Practices:
- Always use generics to ensure type safety and prevent potential runtime errors.
- Pay attention to compiler warnings and address them by updating legacy code to use generics wherever possible.
- Avoid ignoring compiler warnings, as they often indicate issues that can lead to runtime errors.

By addressing compiler warnings and embracing generics, you can write safer and more robust Java code that is less prone to runtime errors and easier to maintain.

In Java, each primitive type has a corresponding wrapper class, which allows you to treat primitive types as objects. This conversion between primitive types and their corresponding wrapper classes can happen automatically when needed, thanks to a feature called autoboxing and unboxing. Let's delve into these concepts:

### Autoboxing:
- Autoboxing is the automatic conversion of primitive types to their corresponding wrapper classes.
- When you assign a primitive value to an object of its corresponding wrapper class or pass a primitive value to a method that expects an object of the wrapper class, autoboxing automatically converts the primitive value to the wrapper class object.

### Example of Autoboxing:
```java
int primitiveInt = 10;
Integer wrapperInteger = primitiveInt; // Autoboxing
```

### Unboxing:
- Unboxing is the automatic conversion of wrapper class objects to their corresponding primitive types.
- When you assign a wrapper class object to a primitive variable or pass a wrapper class object to a method that expects a primitive type, unboxing automatically extracts the primitive value from the wrapper class object.

### Example of Unboxing:
```java
Integer wrapperInteger = 20;
int primitiveInt = wrapperInteger; // Unboxing
```

### Automatic Conversion Selection:
- When performing method calls, Java will prioritize methods with exact primitive type signatures over methods with wrapper type signatures.
- For example, if a method has both `remove(int n)` and `remove(Object o)` signatures, and you call it with an `int` argument, Java will choose the method with the `int` parameter rather than autoboxing to call the method with the `Object` parameter.

### Example:
```java
List<Integer> list = new ArrayList<>();
list.add(10); // Autoboxing to Integer
list.remove(10); // remove(int n) is called, not remove(Object o)
```

### Benefits:
- Autoboxing and unboxing make code more readable and concise by allowing you to mix primitive types and their corresponding wrapper classes seamlessly.
- They reduce the need for manual conversions between primitive types and their wrapper classes, improving code readability and maintainability.

### Considerations:
- While autoboxing and unboxing are convenient, they can introduce performance overhead in certain situations, such as frequent conversions in performance-critical code.

By understanding autoboxing and unboxing, you can leverage them effectively to write cleaner and more expressive Java code.

The Java Collections Framework provides a rich set of data structures to store and manipulate collections of objects efficiently. Here's an overview of the main types of data structures along with their corresponding interfaces and implementations:

### List:
- An ordered collection of elements that allows duplicate entries.
- Subinterfaces: `List`
- Implementations: `ArrayList`, `LinkedList`, `Vector`, `Stack`

### Set:
- A collection that does not allow duplicate elements.
- Subinterface: `Set`
- Implementations: `HashSet`, `TreeSet`

### Queue:
- A collection that orders elements for processing, typically following the FIFO (First-In, First-Out) order.
- Subinterface: `Queue`
- Implementations: `LinkedList`, `ArrayDeque`

### Map:
- A collection that maps unique keys to values.
- Interface: `Map`
- Implementations: `HashMap`, `TreeMap`, `Hashtable`

### Notable Implementations:
- **ArrayList**: A standard resizable list implemented using an array.
- **LinkedList**: A list that allows easy addition and removal from the beginning or end.
- **HashSet**: Uses the `hashCode()` method to find unordered elements. Does not allow duplicates.
- **TreeSet**: A sorted and navigable set. Does not allow null values.
- **HashMap**: Uses `hashCode()` to find keys. Does not guarantee the order of keys.
- **TreeMap**: A sorted map implementation. Does not allow null keys.
- **ArrayDeque**: A double-ended queue that supports FIFO and LIFO operations. Does not allow null values.
- **Hashtable**: An older version of HashMap that does not allow null keys or values. Thread-safe but less efficient than HashMap.

### Interfaces and Inheritance:
- The `Collection` interface is the parent interface of `List`, `Set`, and `Queue`, but not `Map`.
- The `Map` interface stands alone and does not extend `Collection`.

### Thread Safety:
- `Vector` and `Hashtable` are older thread-safe versions of `ArrayList` and `HashMap`, respectively. However, they are less efficient due to their synchronized nature.

Understanding these data structures and their implementations is essential for efficient data manipulation and algorithm design in Java. Each data structure has its advantages and use cases, so it's crucial to choose the appropriate one based on the requirements of your application.

In Java, the `Comparable` and `Comparator` interfaces are used to define custom ordering for objects. Here's a breakdown of each interface and how they are used:

### Comparable Interface:
- The `Comparable` interface is used to define the natural ordering of objects.
- It declares a single method: `compareTo(Object o)`.
- The `compareTo` method returns:
  - A negative number if the object is smaller than the argument.
  - Zero if the two objects are equal.
  - A positive number otherwise.
- The `compareTo` method is declared in the class of the object being compared.
- Implementing the `Comparable` interface allows objects to be compared and sorted using the `Collections.sort()` method.

### Example of Comparable:
```java
public class MyClass implements Comparable<MyClass> {
    private int value;

    // Constructor, getters, setters

    @Override
    public int compareTo(MyClass other) {
        return Integer.compare(this.value, other.value);
    }
}
```

### Comparator Interface:
- The `Comparator` interface is used to define custom ordering of objects that don't implement the `Comparable` interface or to provide alternative sorting criteria.
- It declares a single method: `compare(Object o1, Object o2)`.
- The `compare` method returns:
  - A negative number if the first argument is smaller than the second.
  - Zero if the two objects are equal.
  - A positive number otherwise.
- The `compare` method can be implemented in any class and takes two parameters.
- `Comparator` can be implemented using anonymous inner classes, lambda expressions, or method references.

### Example of Comparator with Lambda:
```java
List<MyClass> list = new ArrayList<>();
// Populate list

Collections.sort(list, (o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
```

### Key Differences:
- `Comparable` is implemented by the class of the objects being compared, while `Comparator` can be implemented in any class.
- `Comparable` defines the natural ordering of objects, while `Comparator` allows for custom ordering.
- `Comparable` is often used for objects that have a natural ordering, while `Comparator` is used for more flexible and customized sorting.

### Best Practices:
- Use `Comparable` for natural ordering when objects have an inherent ordering.
- Use `Comparator` for custom ordering or when sorting objects that don't implement `Comparable`.
- Prefer lambda expressions or method references when implementing `Comparator` for concise and readable code.

Understanding and utilizing `Comparable` and `Comparator` interfaces allow for flexible and customizable sorting of objects in Java applications.

In Java, both the `Arrays` and `Collections` classes provide methods for sorting and searching collections of objects. These methods ensure consistency in sorting and searching by accepting an optional `Comparator` parameter. Additionally, the `Collection` interface offers several methods that take lambda expressions for flexible and concise manipulation of collections. Let's explore these concepts:

### Sorting and Searching:
- The `Arrays` class provides a `sort()` method for sorting arrays of objects.
- The `Collections` class provides a `sort()` method for sorting collections of objects.
- Both methods accept an optional `Comparator` parameter to define custom sorting order.
- The `Collections` class also provides a `binarySearch()` method for searching sorted collections.

### Example of Sorting with Comparator:
```java
List<String> names = new ArrayList<>();
// Populate list

Collections.sort(names, (s1, s2) -> s1.compareTo(s2)); // Sort alphabetically
```

### Example of Binary Search:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
int index = Collections.binarySearch(numbers, 5); // Search for 5
```

### Consistency in Sort Order:
- It's important to use the same sorting order for both sorting and searching operations to ensure consistent results.
- By providing the same `Comparator` to both the sorting and searching methods, the order is well-defined and consistent.

### Lambda Expressions in Collection Methods:
- The `Collection` interface offers methods that take lambda expressions for convenient manipulation of collections.
- Methods such as `removeIf()`, `forEach()`, and `merge()` allow you to perform operations on elements of a collection using lambda expressions.

### Example of Using Lambda Expressions:
```java
List<Integer> numbers = new ArrayList<>();
// Populate list

// Remove all even numbers using lambda expression
numbers.removeIf(n -> n % 2 == 0);

// Perform an operation on each element using lambda expression
numbers.forEach(System.out::println);
```

### Benefits:
- Utilizing `Comparator` parameters ensures consistency in sorting and searching operations.
- Lambda expressions in collection methods offer concise and readable ways to manipulate collections.

### Best Practices:
- Use custom `Comparator` implementations when sorting collections with specific requirements.
- Leverage lambda expressions in collection methods for streamlined manipulation of collections.

By understanding and utilizing the sorting and searching methods provided by the `Arrays` and `Collections` classes, along with the flexibility of lambda expressions in collection methods, you can efficiently manipulate and manage collections in Java.

In Java, a method reference is a concise syntax for writing lambda expressions that refer to methods. Method references provide a compact and readable way to pass methods as arguments to functional interfaces. There are four types of method references:

### 1. Static Method Reference:
- Refers to a static method of a class.
- Syntax: `ClassName::staticMethodName`.

### Example:
```java
// Lambda expression
Function<String, Integer> toInt = s -> Integer.parseInt(s);

// Method reference
Function<String, Integer> toIntRef = Integer::parseInt;
```

### 2. Instance Method Reference to a Specific Instance:
- Refers to an instance method of a specific object.
- Syntax: `objectReference::instanceMethodName`.

### Example:
```java
String str = "Hello";

// Lambda expression
Function<Integer, Character> charAt = i -> str.charAt(i);

// Method reference
Function<Integer, Character> charAtRef = str::charAt;
```

### 3. Instance Method Reference with the Instance Supplied at Runtime:
- Refers to an instance method where the instance is provided at runtime.
- Syntax: `ClassName::instanceMethodName`.

### Example:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Lambda expression
Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

// Method reference
Consumer<String> printUpperCaseRef = System.out::println;
```

### 4. Constructor Reference:
- Refers to a constructor of a class.
- Syntax: `ClassName::new`.

### Example:
```java
// Lambda expression
Supplier<String> newString = () -> new String();

// Constructor reference
Supplier<String> newStringRef = String::new;
```

### Benefits of Method References:
- Enhanced readability: Method references provide a concise and clear syntax, especially for simple lambda expressions.
- Code reuse: Method references allow you to reuse existing methods without rewriting them as lambda expressions.
- Improved maintainability: By using method references, code becomes more maintainable and less prone to errors.

### Best Practices:
- Use method references when possible, especially for simple lambda expressions that directly map to method calls.
- Choose the appropriate type of method reference based on the context and requirements of your code.

By understanding and utilizing method references, you can write cleaner and more expressive code in Java.

# Exam Essentials

**Pick the correct type of collection from a description**

Based on the descriptions provided:

1. A collection that allows duplicates and maintains the order of elements: **List**
2. A collection that does not allow duplicates: **Set**
3. A collection that orders its elements to allow retrievals from one or both ends: **Queue**
4. A collection that maps keys to values: **Map**

Here's a brief explanation of each:

- **List**: Allows duplicates and maintains the order of elements. Examples of implementations include `ArrayList`, `LinkedList`, and `Vector`.
- **Set**: Does not allow duplicates and does not guarantee the order of elements. Examples of implementations include `HashSet` and `TreeSet`.
- **Queue**: Orders its elements to allow retrievals from one or both ends. Examples of implementations include `LinkedList` and `ArrayDeque`.
- **Map**: Maps keys to values, where each key is unique. Examples of implementations include `HashMap`, `TreeMap`, and `Hashtable`.

Understanding these differences is essential for selecting the appropriate collection type based on the specific requirements of your application.

**Identify valid and invalid uses of generics**

Understanding the correct and incorrect uses of generics is essential for writing type-safe and flexible code in Java. Here are examples of valid and invalid uses of generics, along with explanations:

### Valid Uses of Generics:

1. **Declaring a Generic Class or Interface**:
  - Valid: `class MyClass<T> { }`
  - Explanation: Declaring a generic class or interface with a type parameter `<T>` allows it to work with any type.

2. **Using Generics in Methods**:
  - Valid: `public <T> void myMethod(T param) { }`
  - Explanation: Using generics in methods allows for type flexibility and type safety within the method's scope.

3. **Creating Generic Collections**:
  - Valid: `List<T> myList = new ArrayList<>();`
  - Explanation: Generics are commonly used with collections to enforce type safety and allow for generic algorithms.

4. **Wildcard Types**:
  - Valid: `List<? extends Number> list = new ArrayList<Integer>();`
  - Explanation: Wildcard types allow for greater flexibility in handling unknown types, such as upper-bounded wildcards (`<? extends X>`) or lower-bounded wildcards (`<? super X>`).

### Invalid Uses of Generics:

1. **Using Raw Types**:
  - Invalid: `List myList = new ArrayList();`
  - Explanation: Using raw types bypasses type safety checks and should be avoided in favor of parameterized types.

2. **Using Generics with Primitives**:
  - Invalid: `List<int> myList = new ArrayList<>();`
  - Explanation: Generics do not work with primitive types; instead, use their corresponding wrapper classes (e.g., `Integer`, `Double`, `Boolean`).

3. **Creating Instances of Type Parameters**:
  - Invalid: `T myInstance = new T();`
  - Explanation: Type parameters cannot be instantiated directly because their type is not known at compile time.

4. **Using Static Members with Generics**:
  - Invalid: `private static T myStaticField;`
  - Explanation: Static members cannot directly reference type parameters because they are shared among all instances of the class.

Understanding these valid and invalid uses of generics helps ensure that your code is type-safe and follows best practices in Java programming.

**Recognize the difference between compiler warnings and errors when dealing with legacy code**

When dealing with legacy code in Java, it's important to understand the distinction between compiler warnings and errors, as they indicate different levels of issues in your code:

### Compiler Warnings:
- **Occurrence**: Compiler warnings occur when using non-generic types, such as raw types, in your code.
- **Example**: Using a raw type like `ArrayList` without specifying its generic type parameter (`ArrayList myList = new ArrayList();`).
- **Consequences**: Compiler warnings indicate potential issues with type safety. While the code compiles, it may lead to unexpected behavior or `ClassCastException` at runtime due to type mismatch.
- **Recommendation**: Address compiler warnings by using generics (`ArrayList<String> myList = new ArrayList<>();`) to ensure type safety and avoid runtime errors.

### Compiler Errors:
- **Occurrence**: Compiler errors occur when attempting to unbox values from a legacy collection, such as trying to unbox from a raw `ArrayList`.
- **Example**: Attempting to unbox an `Integer` from a raw `ArrayList`: `int value = (int) myList.get(0);`.
- **Consequences**: Compiler errors prevent the code from compiling because unboxing from a raw collection is not type-safe and can lead to runtime errors.
- **Recommendation**: Resolve compiler errors by using generics and properly specifying the type of collections to ensure type safety and prevent potential `ClassCastException` at runtime.

Understanding the difference between compiler warnings and errors helps developers identify and address potential issues in legacy code effectively. By addressing compiler warnings and errors, developers can ensure type safety and prevent unexpected runtime errors.

**Differentiate between Comparable and Comparator**

In Java, `Comparable` and `Comparator` are interfaces used for sorting objects, but they serve different purposes and are implemented differently:

### Comparable Interface:
- **Purpose**: Classes that implement `Comparable` define a natural ordering for their objects.
- **Method**: Classes implementing `Comparable` provide a `compareTo()` method, which compares the current object with another object.
- **Usage**: Objects implementing `Comparable` are said to have a natural ordering and can be sorted using methods like `Collections.sort()` or `Arrays.sort()`.
- **Limitation**: A class can have only one natural ordering defined by `Comparable`.

### Example:
```java
public class Person implements Comparable<Person> {
    private int age;
    // Other fields and methods

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}
```

### Comparator Interface:
- **Purpose**: `Comparator` provides a way to define multiple sorting criteria or to sort objects of classes that don't implement `Comparable`.
- **Method**: Implementations of `Comparator` provide a `compare()` method, which takes two objects and returns a negative integer, zero, or a positive integer.
- **Usage**: Different `Comparator` implementations can provide different sorting orders for the same class.
- **Implementation**: `Comparator` can be implemented using lambda expressions or anonymous classes.

### Example:
```java
List<Person> people = new ArrayList<>();
// Sort by age ascending
Collections.sort(people, (a, b) -> a.getAge() - b.getAge());
// Sort by name descending
Collections.sort(people, (a, b) -> b.getName().compareTo(a.getName()));
```

### Key Differences:
- **Purpose**: `Comparable` defines a natural ordering within the class, while `Comparator` allows for multiple sorting criteria and is applied externally.
- **Method Signature**: `Comparable` has `compareTo()` method, taking one parameter, while `Comparator` has `compare()` method, taking two parameters.
- **Number of Orders**: A class can have only one natural ordering via `Comparable`, but multiple `Comparator` implementations can define different sort orders.

Understanding the difference between `Comparable` and `Comparator` helps in designing and implementing sorting logic in Java applications, providing flexibility and consistency in sorting objects.

**Understand the behavior and usage of the sort and binary search methods**

In Java, the `Collections` and `Arrays` classes provide methods for sorting and searching collections and arrays, respectively. Understanding the behavior and usage of the `sort()` and `binarySearch()` methods is crucial for efficiently managing and retrieving data. Here's an overview:

### `sort()` Method:
- **Behavior**: The `sort()` method is used to sort elements of a collection or array in ascending order. It rearranges the elements according to their natural ordering (if they implement `Comparable`) or based on a custom sorting criteria provided by a `Comparator`.
- **Usage**:
  - For Collections: `Collections.sort(List<T> list)` or `Collections.sort(List<T> list, Comparator<? super T> comparator)`.
  - For Arrays: `Arrays.sort(T[] array)` or `Arrays.sort(T[] array, Comparator<? super T> comparator)`.
- **Optional Comparator Parameter**: The overloaded versions of the `sort()` method allow you to specify a custom sorting order using a `Comparator`.

### `binarySearch()` Method:
- **Behavior**: The `binarySearch()` method is used to search for a specified element in a sorted collection or array. It employs the binary search algorithm, which requires the collection or array to be sorted beforehand.
- **Usage**:
  - For Collections: `Collections.binarySearch(List<? extends Comparable<? super T>> list, T key)` or `Collections.binarySearch(List<? extends T> list, T key, Comparator<? super T> comparator)`.
  - For Arrays: `Arrays.binarySearch(T[] array, T key)` or `Arrays.binarySearch(T[] array, int fromIndex, int toIndex, T key)` or `Arrays.binarySearch(T[] array, T key, Comparator<? super T> comparator)`.
- **Precondition**: The collection or array must be sorted in ascending order according to the same ordering criteria used for the search.

### Usage Guidelines:
- Ensure the collection or array is sorted before performing a binary search using the `sort()` method.
- Use the appropriate overload of `sort()` based on whether you need to use the natural ordering or a custom sorting criteria.
- When using `binarySearch()`, ensure that the collection or array is sorted in the same order as the search criteria.
- Provide a `Comparator` if the natural ordering of elements does not match the order required for sorting or searching.

Understanding the behavior and usage of `sort()` and `binarySearch()` methods allows for efficient manipulation and retrieval of data in Java collections and arrays. Always ensure that the elements are sorted correctly before performing searches to achieve accurate results.

**Map method references to the “long form” lambda**

Method references provide a concise way to refer to methods or constructors. They can be thought of as shorthand for lambda expressions. Understanding how to map method references to their corresponding lambda expressions, and vice versa, is essential for writing clean and readable code in Java. Here's how method references are mapped to the "long form" lambda expressions and vice versa:

### Mapping Method References to Lambda Expressions:

1. **Static Method Reference**:
  - Method Reference: `Class::staticMethod`
  - Equivalent Lambda Expression: `(args) -> Class.staticMethod(args)`

   Example:
   ```java
   // Method Reference
   Function<Integer, String> methodRef = String::valueOf;

   // Equivalent Lambda Expression
   Function<Integer, String> lambdaExp = (num) -> String.valueOf(num);
   ```

2. **Instance Method Reference of an Object**:
  - Method Reference: `object::instanceMethod`
  - Equivalent Lambda Expression: `(args) -> object.instanceMethod(args)`

   Example:
   ```java
   // Method Reference
   Consumer<String> methodRef = System.out::println;

   // Equivalent Lambda Expression
   Consumer<String> lambdaExp = (str) -> System.out.println(str);
   ```

3. **Instance Method Reference of a Class Type**:
  - Method Reference: `Class::instanceMethod`
  - Equivalent Lambda Expression: `(obj, args) -> obj.instanceMethod(args)`

   Example:
   ```java
   // Method Reference
   Comparator<String> methodRef = String::compareToIgnoreCase;

   // Equivalent Lambda Expression
   Comparator<String> lambdaExp = (str1, str2) -> str1.compareToIgnoreCase(str2);
   ```

4. **Constructor Reference**:
  - Method Reference: `Class::new`
  - Equivalent Lambda Expression: `(args) -> new Class(args)`

   Example:
   ```java
   // Method Reference
   Supplier<List<String>> methodRef = ArrayList::new;

   // Equivalent Lambda Expression
   Supplier<List<String>> lambdaExp = () -> new ArrayList<>();
   ```

### Mapping Lambda Expressions to Method References:

Lambda expressions can be mapped to method references when they directly call a method without any additional logic.

Example:
```java
// Lambda Expression
Consumer<String> lambdaExp = (str) -> System.out.println(str);

// Equivalent Method Reference
Consumer<String> methodRef = System.out::println;
```

Understanding how to map between method references and lambda expressions provides flexibility in writing expressive and concise code in Java. Both constructs offer different levels of abstraction and can be used interchangeably depending on readability and context.