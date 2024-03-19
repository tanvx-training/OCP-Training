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

- You use a map when you want to identify values by a key.

#### Comparing Map Implementations

|                   | HashMap                                                                            | TreeMap                                                                                                    |
|-------------------|------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| Data Structure    | Uses a hash table to store key-value pairs.                                        | Uses a Red-Black tree to store key-value pairs.                                                            |
| Main Benefit      | Offers constant-time performance for basic operations, such as get() and put().    | Maintains keys in sorted order, allowing efficient operations for range views and nearest neighbor search. |
| Order of Elements | Does not maintain any order of its keys.                                           | Maintains keys in sorted (ascending) order.                                                                |
| Performance       | Generally faster for basic operations (get, put, containsKey).                     | Slower for basic operations due to the overhead of maintaining the tree structure.                         |
| Usage             | Suitable for general-purpose use, especially when order is not important.          | Suitable when keys need to be maintained in sorted order or when range queries are needed.                 |

#### Working with Map Methods

| Method                          | Description                                                                          | Example                                                                                    |
|---------------------------------|--------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| void clear()                    | Removes all keys and values from the map.                                            | map.clear();                                                                               |
| boolean isEmpty()               | Returns whether the map is empty.                                                    | boolean empty = map.isEmpty();                                                             |
| int size()                      | Returns the number of entries (key/value pairs) in the map.                          | int mapSize = map.size();                                                                  |
| V get(Object key)               | Returns the value mapped by key or null if none is mapped.                           | Integer value = map.get("key");                                                            |
| V put(K key, V value)           | Adds or replaces key/value pair. Returns previous value or null.                     | Integer oldValue = map.put("key", value);                                                  |
| V remove(Object key)            | Removes and returns value mapped to key. Returns null if none.                       | Integer removedValue = map.remove("key");                                                  |
| boolean containsKey(Object key) | Returns whether key is in map.                                                       | boolean containsKey = map.containsKey("key");                                              |
| boolean containsValue(Object)   | Returns whether value is in map.                                                     | boolean containsValue = map.containsValue(value);                                          |
| Set<K> keySet()                 | Returns set of all keys.                                                             | Set<K> keys = map.keySet();                                                                |
| Collection<V> values()          | Returns Collection of all values.                                                    | Collection<V> allValues = map.values();                                                    |

### Comparing Collection Types

*Java Collections Framework types*

| Type     | Can contain duplicate elements? | Elements ordered?                | Has keys and values? | Must add/remove in specific order?    |
|----------|---------------------------------|----------------------------------|----------------------|---------------------------------------|
| List     | Yes                             | Yes (by index)                   | No                   | No                                    |
| Map      | Yes (for values)                | No                               | Yes                  | No                                    |
| Queue    | Yes                             | Yes (retrieved in defined order) | No                   | Yes                                   |
| Set      | No                              | No                               | No                   | No                                    |


*Collection attributes*

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

*Choosing the right collection type*

| Which class do you choose when you want ____________                                                                                                                                 | Answer (single best type) | Reason                                                                                                                                                                                                                                                                        |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| to pick the top zoo map off a stack of maps                                                                                                                                          | ArrayDeque                | The description is of a last-in, first-out data structure, so you need a stack, which is a type of Queue. (Stack would also match this description, but it shouldn’t be used for new code.)                                                                                   |
| to sell tickets to people in the order in which they appear in line and tell them their position in line                                                                             | LinkedList                | The description is of a first-in, first-out data structure, so you need a queue. You also needed indexes, and LinkedList is the only class to match both requirements.                                                                                                        |
| to write down the first names of all of the elephants so that you can tell them to your friend’s three-year-old every time she asks. (The elephants do not have unique first names.) | ArrayList                 | Since there are duplicates, you need a list rather than a set. You will be accessing the list more often than updating it, since three-year-olds ask the same question over and over, making an ArrayList better than a LinkedList. Vector and Stack aren’t used in new code. |
| to list the unique animals that you want to see at the zoo today                                                                                                                     | HashSet                   | The keyword in the description is unique. When you see “unique,” think “set.” Since there were no requirements to have a sorted order or to remember the insertion order, you use the most efficient set.                                                                     |
| to list the unique animals that you want to see at the zoo today in alphabetical order                                                                                               | TreeSet                   | Since it says “unique,” you need a set. This time, you need to sort, so you cannot use a HashSet.                                                                                                                                                                             |
| to look up animals based on a unique identifier                                                                                                                                      | HashMap                   | Looking up by key should make you think of a map. Since you have no ordering or sorting requirements, you should use the most basic map.                                                                                                                                      |

### Comparator vs. Comparable

- Java provides an interface called Comparable. If your class implements Comparable , it can be used 
in these data structures that require comparison. 
- There is also a class called Comparator , which is used to specify that you want to use a different
order than the object itself provides

**Comparable**

```java
public interface Comparable<T> {

  public int compareTo(T o);
}
```

*There are three rules to know:*
- The number zero is returned when the current object is equal to the argument to compareTo().
- A number less than zero is returned when the current object is smaller than the argument to 
compareTo().
- A number greater than zero is returned when the current object is larger than the argument to 
compareTo().

```java
public class Animal implements java.util.Comparable<Animal> {
    private int id;

    public int compareTo(Animal a) {
        return id - a.id;
    }

    public static void main(String[] args) {
        Animal a1 = new Animal();
        Animal a2 = new Animal();
        
        a1.id = 5;
        a2.id = 7;
        
        System.out.println(a1.compareTo(a2)); // -2
        System.out.println(a1.compareTo(a1)); // 0
        System.out.println(a2.compareTo(a1)); // 2
    }
}
```

*Remember that id – a.id sorts in ascending order and a.id – id sorts in descending order*

**Comparator**

Sometimes you want to sort an object that did not implement Comparable, or you want to sort objects
in different ways at different times.

```java
public interface Comparator<T> {

  int compare(T o1, T o2);
}
```

**Comparison of Comparable and Comparator**

| Difference                                   | Comparable          | Comparator          |
|----------------------------------------------|---------------------|---------------------|
| Package name                                 | java.lang           | java.util           |
| Interface must be implemented by class       | Yes                 | No                  |
| Method name in interface                     | compareTo           | compare             |
| Number of parameters                         | 1                   | 2                   |
| Common to declare using a lambda             | No                  | Yes                 |

### Searching and Sorting

Just like searching and sorting, you can tell collections that require sorting that you wish
to use a specific Comparator, for example:

```java
Set<Rabbit> rabbit=new TreeSet<>(new Comparator<Rabbit>(){
  public int compare(Rabbit r1,Rabbit r2){
        return r1.id=r2.id;
  }
});
rabbit.add(new Rabbit());
```

### Additions in Java 8

**Using Method References**

There are four formats for method references:
- Static methods
- Instance methods on a particular instance
- Instance methods on an instance to be determined at runtime
- Constructors

**Removing Conditionally**

```java
boolean removeIf(Predicate<? super E> filter)
```

Java 8 introduces a new method called removeIf. Before this, we had the ability to remove
a specified object from a collection or a specified index from a list. Now we can specify
what should be deleted using a block of code.

```java
List<String> list = new ArrayList<>();
list.add("Magician");
list.add("Assistant");
System.out.println(list); // [Magician, Assistant]
list.removeIf(s -> s.startsWith("A"));
System.out.println(list);
```

**Updating All Elements**

```
void replaceAll(UnaryOperator<E> o)
```

- Another new method introduced on Lists is replaceAll. Java 8 lets you pass a lambda
expression and have it applied to each element in the list. The result replaces the current
value of that element.
- It uses a UnaryOperator, which takes one parameter and returns a value of the same
type.

```java
List<Integer> list = Arrays.asList(1, 2, 3);
list.replaceAll(x -> x*2);
System.out.println(list); // [2, 4, 6]
```

**Looping through a Collection**

This time, we’ve used a Consumer, which takes a single parameter and doesn’t return
anything

```java
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
```

**Using New Java 8 Map APIs**

*merge()*

- The merge() method allows adding logic to the problem of what to choose.
- The merge() method also has logic for what happens if nulls or missing keys are involved. In this 
case, it doesn’t call the BiFunction at all, and it simply uses the new value.
- The final thing to know about merge() is what happens when the mapping function is called and 
returns null. The key is removed from the map when this happens.

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

*computeIfPresent() and computeIfAbsent()*

- In a nutshell, computeIfPresent() calls the BiFunction if the requested key is found.

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

*The basics of the merge and compute methods*

| Scenario                     | merge                               | computeIfAbsent                    | computeIfPresent                   |
|------------------------------|-------------------------------------|------------------------------------|------------------------------------|
| Key already in map           | Result of function                 | No action                         | Result of function                 |
| Key not already in map       | Result of function               | Add new value to map                | No action                          |
| Functional Interface used   | BiFunction (Takes existing value and new value. Returns new value.) | Function (Takes key and returns new value.) | BiFunction (Takes key and existing value. Returns new value.) |


*Merge and compute methods when nulls are involved*

| Key has               | Mapping functions returns | merge                               | computeIfAbsent                                       | computeIfPresent                    |
|-----------------------|---------------------------|-------------------------------------|-------------------------------------------------------|-------------------------------------|
| null value in map     | null                      | Remove key from map.                | Do not change map.                                    | Do not change map.                  |
| null value in map     | Not null                  | Set key to mapping function result. | Add key to map with mapping function result as value. | Do not change map.                  |
| Non-null value in map | null                      | Remove key from map.                | Do not change map.                                    | Remove key from map.                |
| Non-null value in map | Not null                  | Set key to mapping function result. | Do not change map.                                    | Set key to mapping function result. |
| Key not in map        | null                      | Add key to map.                     | Do not change map.                                    | Do not change map.                  |
| Key not in map        | Not null                  | Add key to map.                     | Add key to map with mapping function result as value. | Do not change map.                  |

**Summary**

*Pick the correct type of collection from a description*:

- A List allows duplicates and orders the elements. 
- A Set does not allow duplicates. 
- A Queue orders its elements to allow retrievals from one or both ends. 
- A Map maps keys to value. 
- Be familiar with the differences of implementations of these interfaces.

*Identify valid and invalid uses of generics*:

- `<T>` represents a type parameter. 
- Any name can be used, but a single uppercase letter is the convention. 
- `<?>` is an unbounded wildcard.
- `<? extends X>` is an upper-bounded wildcard and applies to both classes and interfaces. 
- `<? super X>` is a lower-bounded wildcard.

*Recognize the difference between compiler warnings and errors when dealing with legacy code*:

- A compiler warning occurs when using non-generic types, and a ClassCastException might occur at 
runtime. 
- A compiler error occurs when trying to unbox from a legacy collection.

*Differentiate between Comparable and Comparator:*

- Classes that implement Comparable are said to have a natural ordering and implement the compareTo()
method. 
- A class is allowed to have only one natural ordering. 
- A Comparator takes two objects in the compare() method. 
- Different Comparators can have different sort orders. 
- A Comparator is often implemented using a lambda such as (a, b) -> a.num – b.num.

*Understand the behavior and usage of the sort and binary search methods:*

- The Collections and Arrays classes provide overloaded sort() and binarySearch() methods.
- They take an optional Comparator parameter. 
- The list or array must be sorted before it is searched using the same definition of order for both.

*Map method references to the “long form” lambda:*

- Be able to convert method references into regular lambda expressions and vice versa. 
- For example, System.out::print and x -> System.out.print(x) are equivalent.