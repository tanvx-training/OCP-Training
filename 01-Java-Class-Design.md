# Java Class Design

## Reviewing OCA Concepts

### Access Modifiers

Access modifiers in Java are keywords used to specify the accessibility or visibility of classes, methods, and variables in Java programs. There are four types of access modifiers in Java:

1. **Public (`public`)**: A member (class, method, or variable) with the `public` modifier is accessible from any other class in the same package or from any other package.

2. **Protected (`protected`)**: A member with the `protected` modifier is accessible within its own package and by subclasses (regardless of whether they are in the same package or a different package).

3. **Default (no modifier)**: If no access modifier is specified, the member has package-level accessibility. It is accessible only within its own package.

4. **Private (`private`)**: A member with the `private` modifier is accessible only within its own class. It cannot be accessed from outside the class, not even from subclasses.

Here's a brief overview of how each access modifier is used:

- **Public**: Used to declare members that need to be accessible from outside the class or package.
- **Protected**: Used to provide limited access to members within the same package and subclasses.
- **Default**: Used when no access modifier is specified. Provides access only within the same package.
- **Private**: Used to restrict access to members to only the declaring class itself.

Access modifiers help control the visibility and accessibility of classes, methods, and variables, allowing developers to enforce encapsulation and manage the interaction between different parts of the codebase.

### Overloading and Overriding

Overloading and overriding are two important concepts in object-oriented programming, particularly in Java.

**Overloading**:
- Overloading refers to the ability to define multiple methods in the same class with the same name but different parameters.
- Overloaded methods must have different parameter lists, which can differ in the number of parameters, types of parameters, or order of parameters.
- Overloading allows methods to perform similar tasks with different inputs, making the code more readable and flexible.
- Overloaded methods are resolved at compile time based on the number and types of arguments passed to them.

Example of method overloading:
```java
class Calculator {
    // Overloaded methods with different parameter lists
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

**Overriding**:
- Overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass.
- The method in the subclass must have the same name, return type, and parameter list as the method in the superclass.
- Overriding allows a subclass to provide its own implementation of a method defined in its superclass, thus customizing or extending the behavior of the superclass method.
- Overridden methods are resolved at runtime based on the actual object type (dynamic method dispatch).

Example of method overriding:
```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}
```

In summary, overloading is the ability to define multiple methods with the same name but different parameters within the same class, while overriding is the ability to provide a specific implementation of a method in a subclass that is already defined in its superclass.

> The methods must not be static. (If they are, the method is hidden and not
overridden.)

### Abstract Classes

Abstract classes are classes in Java that cannot be instantiated directly, meaning you cannot create objects of an abstract class using the `new` keyword. Instead, abstract classes serve as blueprints for other classes and may contain abstract methods, which are declared but not implemented in the abstract class. Abstract classes can also have concrete methods, which are implemented in the abstract class itself.

Here are some key points about abstract classes:

1. **Declaration**: An abstract class is declared using the `abstract` keyword in its class definition.

   ```java
   abstract class Animal {
       // Abstract method
       abstract void makeSound();

       // Concrete method
       void sleep() {
           System.out.println("Animal sleeps");
       }
   }
   ```

2. **Abstract Methods**: Abstract methods are declared without a body and must be implemented by non-abstract subclasses. They serve as placeholders for methods that will be implemented by subclasses.

3. **Concrete Methods**: Concrete methods in an abstract class have a body and provide default behavior that can be inherited by subclasses.

4. **Instantiation**: Abstract classes cannot be instantiated directly, meaning you cannot create objects of an abstract class. However, you can create references of an abstract class type.

5. **Subclassing**: Subclasses of an abstract class must provide implementations for all abstract methods inherited from the abstract class, unless the subclass itself is also declared as abstract.

Here's an example demonstrating the usage of an abstract class:

```java
abstract class Animal {
    abstract void makeSound(); // Abstract method

    void sleep() { // Concrete method
        System.out.println("Animal sleeps");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog(); // Creating object of subclass
        dog.makeSound(); // Output: Dog barks
        dog.sleep(); // Output: Animal sleeps
    }
}
```

In this example, `Animal` is an abstract class with one abstract method `makeSound()` and one concrete method `sleep()`. The `Dog` class extends the `Animal` class and provides an implementation for the `makeSound()` method. An object of the `Dog` class is created and assigned to a reference of type `Animal`.

### Static and Final

Static and final are two keywords in Java that are used to modify the behavior of variables, methods, and classes.

1. **Static**:
    - When a member (variable or method) is declared as static, it belongs to the class rather than any specific instance of the class.
    - There is only one copy of a static member shared by all instances of the class.
    - Static variables are initialized only once, at the start of the execution of the program.
    - Static methods can be called directly on the class without the need to instantiate an object of the class.
    - Static variables and methods are commonly used for utility methods and constants.

Example:
```java
public class MyClass {
    static int staticVariable;
    static final int CONSTANT_VALUE = 10;

    static void staticMethod() {
        // Method body
    }
}
```

2. **Final**:
    - When a member (variable, method, or class) is declared as final, its value or implementation cannot be changed.
    - For variables, once initialized, their value cannot be altered.
    - For methods, they cannot be overridden in subclasses.
    - For classes, they cannot be subclassed (extended).

Example:
```java
public class MyClass {
    final int finalVariable = 10;

    final void finalMethod() {
        // Method body
    }
}
```

Combining static and final:
- Static and final can be used together for constants, creating variables that cannot be changed and are shared among all instances of the class.
- A common use case is defining constants that are independent of any specific instance of a class.

Example:
```java
public class MathConstants {
    public static final double PI = 3.14159;
    public static final double E = 2.71828;
}
```

In this example, `PI` and `E` are constants that are both static (shared among all instances) and final (cannot be changed after initialization).

### Imports

In Java, the `import` statement is used to make classes and packages accessible in your source code. It allows you to use classes from other packages without having to specify the package name each time you use them.

There are different forms of the `import` statement:

1. **Single Type Import**:
    - This form imports a single class or interface.
    - Syntax: `import packageName.className;`
    - Example: `import java.util.ArrayList;`

2. **Multiple Type Import**:
    - This form imports multiple classes or interfaces from the same package.
    - Syntax: `import packageName.className1; import packageName.className2;`
    - Example:
      ```java
      import java.util.ArrayList;
      import java.util.LinkedList;
      ```

3. **Wildcard Import**:
    - This form imports all the classes and interfaces from a package.
    - Syntax: `import packageName.*;`
    - Example: `import java.util.*;`

4. **Static Import**:
    - This form imports the static members (variables and methods) of a class.
    - Syntax: `import static packageName.className.staticMember;`
    - Example: `import static java.lang.Math.PI;`

5. **Static Wildcard Import**:
    - This form imports all the static members of a class.
    - Syntax: `import static packageName.className.*;`
    - Example: `import static java.lang.Math.*;`

It's important to use imports judiciously to avoid namespace collisions and keep your code clean and readable. While wildcard imports (`import packageName.*;`) can save typing, they may lead to ambiguity and confusion, especially in larger codebases. It's generally recommended to import only the classes you need explicitly.

## Using instanceof

The `instanceof` keyword in Java is used to test whether an object is an instance of a particular class or interface. It evaluates to `true` if the object is an instance of the specified type, and `false` otherwise. It also takes into account inheritance, meaning that if the object is an instance of a subclass of the specified type, `instanceof` will still return `true`.

Here's the basic syntax:

```java
object instanceof ClassName
```

Example:

```java
// Define a class hierarchy
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

// Create objects
Animal animal = new Animal();
Dog dog = new Dog();
Cat cat = new Cat();

// Check instance of
System.out.println(animal instanceof Animal);  // true
System.out.println(dog instanceof Animal);     // true (because Dog is a subclass of Animal)
System.out.println(cat instanceof Animal);     // true (because Cat is a subclass of Animal)

System.out.println(dog instanceof Dog);        // true
System.out.println(cat instanceof Dog);        // false
```

`instanceof` is particularly useful when you have a reference to an object of a superclass and you want to check if it's actually an instance of a subclass before casting it. This helps prevent `ClassCastException` errors.

```java
if (animal instanceof Dog) {
    Dog myDog = (Dog) animal;
    // Now it's safe to use myDog
}
```

## Understanding Virtual Method Invocation

Virtual Method Invocation (VMI) is a mechanism in object-oriented programming languages, like Java, that allows a method to be dynamically invoked based on the actual type of the object rather than the declared type of the reference variable. This means that when you call a method on an object, the method that gets executed is determined by the actual type of the object at runtime.

In Java, all non-static methods are virtual by default. When you call a method on an object, the JVM determines the appropriate method to execute based on the actual type of the object at runtime. This allows for polymorphic behavior, where different objects can respond differently to the same method call.

Here's an example to illustrate virtual method invocation:

```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meows");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog(); // Creating Dog object but referring to it as Animal
        Animal cat = new Cat(); // Creating Cat object but referring to it as Animal

        dog.makeSound(); // Calls Dog's makeSound() method
        cat.makeSound(); // Calls Cat's makeSound() method
    }
}
```

In this example, even though the objects `dog` and `cat` are referred to as `Animal`, the actual methods that get called (`makeSound()`) are determined by the actual type of the objects (`Dog` and `Cat`) at runtime. This is virtual method invocation in action.

## Annotating Overridden Methods

In Java, annotations can be used to provide additional metadata about classes, methods, fields, and other program elements. When it comes to overriding methods, there are certain annotations that can be used to indicate the intention or provide additional information about the overridden methods. Here are some commonly used annotations for annotating overridden methods:

1. `@Override`: This annotation is used to indicate that a method is intended to override a method in a superclass. It helps improve code readability and provides compile-time checks to ensure that the method is actually overriding a method from the superclass. If the method with `@Override` annotation does not override a method from the superclass, the compiler will generate an error.

   ```java
   class Parent {
       void method() {
           System.out.println("Parent method");
       }
   }

   class Child extends Parent {
       @Override
       void method() { // This method overrides the method in the superclass
           System.out.println("Child method");
       }
   }
   ```

2. `@Deprecated`: This annotation is used to indicate that a method is deprecated, meaning that it is no longer recommended for use. When overriding a deprecated method from a superclass, you can also use `@Deprecated` annotation to indicate that the overriding method is deprecated as well.

   ```java
   class Parent {
       @Deprecated
       void deprecatedMethod() {
           System.out.println("Deprecated method");
       }
   }

   class Child extends Parent {
       @Deprecated
       @Override
       void deprecatedMethod() {
           System.out.println("Child's deprecated method");
       }
   }
   ```

3. `@SuppressWarnings`: This annotation is used to suppress compiler warnings. Sometimes, when overriding a method, you may encounter warnings that you want to suppress. In such cases, you can use `@SuppressWarnings` annotation.

   ```java
   class Parent {
       void method() {
           System.out.println("Parent method");
       }
   }

   class Child extends Parent {
       @SuppressWarnings("unused")
       @Override
       void method() { // This method overrides the method in the superclass
           System.out.println("Child method");
       }
   }
   ```

These annotations help improve code quality, readability, and maintainability by providing additional information about overridden methods.

## Coding equals, hashCode, and toString

### toString()

The `toString()` method in Java is a method of the `Object` class that is overridden to return a string representation of an object. This method is commonly overridden in classes to provide a meaningful string representation of their instances. The string returned by `toString()` is typically used for debugging, logging, or displaying information about an object.

Here's an example of how you can implement the `toString()` method in a custom class:

```java
public class MyClass {
    private String name;
    private int age;

    // Constructor
    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "MyClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass("John", 30);
        System.out.println(obj); // Output: MyClass{name='John', age=30}
    }
}
```

In this example:
- We have a class `MyClass` with two fields: `name` and `age`.
- We override the `toString()` method to return a string containing the values of the `name` and `age` fields.
- Inside the overridden `toString()` method, we concatenate the class name (`MyClass`) with the values of the fields.
- In the `main()` method, we create an instance of `MyClass` and print it. The `toString()` method is implicitly called when we concatenate the object with a string using `System.out.println()`.

When you run this code, it will print:

```
MyClass{name='John', age=30}
```

This demonstrates how the `toString()` method provides a concise and meaningful representation of an object's state.

### equals()

`The `equals()` method in Java is used to compare two objects for equality. By default, the `equals()` method in the `Object` class checks whether two references refer to the same object in memory. However, in many cases, you'll want to override this method in your own classes to define a custom equality comparison based on the content of the objects rather than their memory addresses.

When overriding the `equals()` method, you typically compare the values of the instance variables of two objects to determine if they are equal. The method should return `true` if the objects are considered equal according to your custom comparison logic, and `false` otherwise.

Here's a basic outline of how you can implement the `equals()` method in a custom class:

```java
public class MyClass {
    private String name;
    private int age;

    // Constructor, getters, setters, etc.

    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        // Check if the objects are the same instance
        if (this == obj) {
            return true;
        }
        // Check if the object is null or belongs to a different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Convert the object to the same class and compare instance variables
        MyClass myClass = (MyClass) obj;
        return age == myClass.age &&
               Objects.equals(name, myClass.name);
    }
}
```

In this example:
- We have a class `MyClass` with two fields: `name` and `age`.
- We override the `equals()` method to compare the values of the `name` and `age` fields of two `MyClass` objects.
- Inside the overridden `equals()` method:
    - We first check if the two objects are the same instance using the `==` operator.
    - Then we check if the `obj` is `null` or belongs to a different class using the `getClass()` method.
    - Finally, we compare the values of the fields using the `equals()` method of the `Objects` class.

This is a basic implementation of the `equals()` method. Depending on your specific requirements, you may need to customize it further to suit your needs.

Absolutely! These are fundamental principles to ensure that the `equals()` method behaves correctly and consistently. Let's break down each of these points:

1. **Reflexive**: An object should equal itself. This ensures that `x.equals(x)` always returns `true`.

2. **Symmetric**: If object `x` equals object `y`, then `y` should also equal `x`. This ensures that the order of comparison doesn't matter.

3. **Transitive**: If `x` equals `y` and `y` equals `z`, then `x` should also equal `z`. This ensures that equality is transitive across multiple objects.

4. **Consistent**: Multiple invocations of `x.equals(y)` should consistently return the same result, as long as the objects being compared haven't been modified.

5. **Non-null**: Comparing an object to `null` should always return `false`. This ensures that `x.equals(null)` is always `false`.

Implementing the `equals()` method according to these principles helps ensure that objects behave correctly when used in collections like `HashSet` or `HashMap`, where proper equality comparison is crucial for maintaining the integrity of the collection.

### hashCode()

Sure! Here's an introduction to the `hashCode()` method along with the contract:

The `hashCode()` method is used to generate a unique integer value for an object. It's often used in conjunction with the `equals()` method to ensure proper behavior when objects are stored in collections like HashMap or HashSet.

After the contract, three key points stand out:
1. **Stability**: Within the same program execution, the result of `hashCode()` must remain constant. This means that the variables used to calculate the hash code should not be mutable if their values affect the hash code. For example, if an object's weight changes regularly, it should not be included in the hash code calculation.
2. **Consistency with equals()**: If the `equals()` method returns true when comparing two objects, then the `hashCode()` method must return the same value for both objects. However, it's essential to note that `hashCode()` can use a subset of the variables used in the `equals()` comparison.
3. **Uniqueness**: If the `equals()` method returns false when comparing two objects, the `hashCode()` method is not required to return a different value for each object. This means that hash codes for unequal objects do not need to be unique.

By adhering to these principles, the `hashCode()` method ensures that objects can be effectively stored and retrieved from hash-based collections while maintaining consistency and correctness in the application.

## Working with Enums

Enums in Java provide a way to define a fixed set of constants, offering type safety and compile-time checking. Here's an overview of working with enums:

1. **Declaration**:
   Enums are declared using the `enum` keyword followed by the enum name and a list of enum constants enclosed in curly braces:
   ```java
   enum Day {
       MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
   }
   ```

2. **Accessing Enum Constants**:
   Enum constants are accessed using dot notation. They are comparable using `==` because they are like static final constants:
   ```java
   Day today = Day.MONDAY;
   if (today == Day.MONDAY) {
       System.out.println("It's Monday!");
   }
   ```

3. **Enum Methods**:
   Enums can have methods, fields, and constructors just like regular classes. Each enum constant can have its own behavior:
   ```java
   enum TrafficLight {
       RED("Stop"), GREEN("Go"), YELLOW("Slow down");

       private String action;

       TrafficLight(String action) {
           this.action = action;
       }

       public String getAction() {
           return action;
       }
   }
   ```

4. **Iteration**:
   You can iterate over all enum constants using the `values()` method:
   ```java
   for (Day day : Day.values()) {
       System.out.println(day);
   }
   ```

5. **Immutability**:
   Enums are immutable and cannot be extended. This ensures that enum constants remain constant throughout the execution of a program.

Enums are commonly used to represent a predefined set of related constants, providing type safety and ease of use. They are ideal for scenarios where a variable should only take on one of a fixed set of values.

### Using Enums in Switch Statements

Enums are often used in switch statements to handle different cases based on the enum constant. Here's how you can use enums in switch statements in Java:

1. **Define an Enum**:
   First, define an enum with the desired constants:
   ```java
   public enum Day {
       MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
   }
   ```

2. **Use Enum in Switch**:
   Use the enum type in a switch statement:
   ```java
   Day today = Day.MONDAY;

   switch (today) {
       case MONDAY:
           System.out.println("It's Monday!");
           break;
       case TUESDAY:
           System.out.println("It's Tuesday!");
           break;
       case WEDNESDAY:
           System.out.println("It's Wednesday!");
           break;
       // Handle other cases...
       default:
           System.out.println("It's not a weekday.");
   }
   ```

3. **Handling Default Case**:
   It's a good practice to include a default case to handle unexpected enum values:
   ```java
   default:
       System.out.println("Unknown day!");
   ```

4. **Fall-through**:
   Unlike regular switch statements, switch statements with enums do not fall through to the next case. Each case must end with a break statement unless you want to deliberately fall through to the next case.

Using enums in switch statements enhances code readability and helps enforce type safety by ensuring that only valid enum constants are handled. It's a clean and concise way to handle different cases based on enum values.

### Adding Constructors, Fields, and Methods

To add constructors, fields, and methods to an enum in Java, follow these steps:

1. **Define Enum Constants**:
   Define the enum constants along with any fields they may have. Enum constants are declared at the beginning of the enum definition.

2. **Add Constructors**:
   You can define constructors for enum constants. Each enum constant can have its own constructor with specific parameters.

3. **Declare Fields**:
   Enum constants can have fields just like regular Java classes. These fields can be initialized in the enum constructor or directly assigned values.

4. **Define Methods**:
   You can add methods to enums to provide additional functionality. These methods can be invoked on enum constants.

Here's an example demonstrating these concepts:

```java
public enum Day {
    MONDAY("Monday"), 
    TUESDAY("Tuesday"), 
    WEDNESDAY("Wednesday"), 
    THURSDAY("Thursday"), 
    FRIDAY("Friday"), 
    SATURDAY("Saturday"), 
    SUNDAY("Sunday");

    private final String displayName;

    // Constructor
    private Day(String displayName) {
        this.displayName = displayName;
    }

    // Getter method
    public String getDisplayName() {
        return displayName;
    }

    // Custom method
    public boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }
}
```

In this example:
- Each enum constant has a `displayName` field.
- The constructor initializes the `displayName` field for each enum constant.
- The `getDisplayName()` method returns the display name of the enum constant.
- The `isWeekend()` method checks if the day is a weekend day.

You can then use these constructors, fields, and methods when working with enum constants. For example:

```java
Day today = Day.MONDAY;
System.out.println(today.getDisplayName());  // Output: Monday
System.out.println(today.isWeekend());       // Output: false
```

## Creating Nested Classes

### Member Inner Classes

A member inner class is defined within the body of another class, at the same level as the methods, instance variables, and constructors. Here are the key properties of member inner classes:

- **Access Modifiers**: Member inner classes can be declared with any access modifier - public, private, protected, or default (package-private).
- **Inheritance and Interfaces**: They can extend any class and implement interfaces, just like any other regular class.
- **Abstract or Final**: Member inner classes can be declared abstract or final, allowing for flexibility in design.
- **No Static Members**: Unlike static nested classes, member inner classes cannot declare static fields or methods.
- **Access to Outer Class Members**: They have access to all members of the outer class, including private members. This allows them to interact closely with the outer class.

Here's an example demonstrating the use of a member inner class:

```java
public class OuterClass {
    private int outerField;

    public OuterClass(int outerField) {
        this.outerField = outerField;
    }

    // Member inner class
    public class InnerClass {
        public void innerMethod() {
            System.out.println("Inner method called");
            System.out.println("Value of outerField: " + outerField); // Accessing outerField
        }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        OuterClass outer = new OuterClass(10);
        InnerClass inner = outer.new InnerClass(); // Creating an instance of InnerClass
        inner.innerMethod(); // Calling innerMethod
    }
}
```

In this example:
- `OuterClass` contains a member inner class named `InnerClass`.
- `InnerClass` has access to the `outerField` of `OuterClass` and can directly access it.
- We create an instance of `InnerClass` within the `main` method of `OuterClass` and call the `innerMethod` method, demonstrating the interaction between the outer and inner classes.

### Local Inner Classes

A local inner class is a nested class defined within a method. It shares some similarities with local variables in that its declaration does not exist until the method is invoked, and it goes out of scope when the method returns. Here are the key properties of local inner classes:

- **No Access Specifier**: Local inner classes do not have an access specifier like public, private, protected, or default (package-private). They are only accessible within the method where they are defined.
- **Non-Static**: They cannot be declared as static and also cannot declare static fields or methods. They are tied to the instance of the enclosing class.
- **Access to Enclosing Class Members**: Local inner classes have access to all fields and methods of the enclosing class, similar to member inner classes.
- **Access to Method Variables**: They do not have direct access to local variables of a method unless those variables are final or effectively final. This means that local variables that are used within the local inner class must be either declared as final or not modified within the method after their initialization.

Here's an example demonstrating the use of a local inner class:

```java
public class OuterClass {
    private int outerField = 10;

    public void outerMethod() {
        final int localVar = 20; // Local variable

        // Local inner class defined within the outerMethod
        class LocalInnerClass {
            public void innerMethod() {
                System.out.println("Inner method called");
                System.out.println("Value of outerField: " + outerField); // Accessing outerField
                System.out.println("Value of localVar: " + localVar); // Accessing localVar
            }
        }

        // Creating an instance of LocalInnerClass and calling innerMethod
        LocalInnerClass inner = new LocalInnerClass();
        inner.innerMethod();
    }

    // Main method for testing
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.outerMethod();
    }
}
```

In this example:
- `OuterClass` contains a method `outerMethod`, within which a local inner class `LocalInnerClass` is defined.
- `LocalInnerClass` has access to both the `outerField` of `OuterClass` and the `localVar` of `outerMethod`.
- An instance of `LocalInnerClass` is created within `outerMethod` and its `innerMethod` is called.

### Anonymous Inner Classes

Correct! An anonymous inner class is a special type of local inner class that does not have a name. Instead of being defined separately, it is declared and instantiated in a single statement using the `new` keyword. Here are some key points about anonymous inner classes:

- **No Name**: They are called "anonymous" because they do not have a name like regular classes.
- **Single Statement**: They are defined and instantiated in a single statement.
- **Extends or Implements**: Anonymous inner classes must extend an existing class or implement an existing interface.
- **Short Implementations**: They are particularly useful when you have a short implementation that will not be reused elsewhere in your code.

Here's an example to illustrate the usage of an anonymous inner class:

```java
public class OuterClass {
    public void displayMessage() {
        // Anonymous inner class implementing the Runnable interface
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous inner class!");
            }
        };

        // Using the anonymous inner class
        Thread thread = new Thread(runnable);
        thread.start();
    }

    // Main method for testing
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.displayMessage();
    }
}
```

In this example:
- `OuterClass` contains a method `displayMessage`, within which an anonymous inner class implementing the `Runnable` interface is declared and instantiated.
- The anonymous inner class provides an implementation for the `run` method of the `Runnable` interface.
- An instance of `Thread` is created using the anonymous inner class, and its `start` method is called to execute the `run` method of the anonymous inner class.

### Static Nested Classes

Exactly! A static nested class is defined at the member level of an outer class, but it is not an inner class because it is marked as static. Here are some key characteristics of a static nested class:

- **Static**: It is marked with the `static` keyword.
- **No Access to Enclosing Instance**: Since it is static, it does not have access to the instance variables or methods of the enclosing class unless they are also static.
- **Namespace**: It creates a namespace of its own, meaning that the name of the enclosing class must be used to refer to it.
- **Encapsulation**: It can have its access modifiers, allowing it to be encapsulated and hidden from the outside.
- **Enclosing Class Access**: The enclosing class can access the fields and methods of the static nested class directly.

Here's an example to illustrate the usage of a static nested class:

```java
public class OuterClass {
    private static int staticVar = 10;

    static class StaticNestedClass {
        void display() {
            System.out.println("Static nested class: " + staticVar);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Instantiating the static nested class
        OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();
        nestedObject.display();
    }
}
```

In this example:
- `OuterClass` contains a static nested class `StaticNestedClass`.
- `StaticNestedClass` can access the static variable `staticVar` directly from the enclosing class `OuterClass`.
- The `main` method demonstrates how to instantiate and use the static nested class without needing an instance of the enclosing class.

|                                                   | Member inner class                           | Local inner class                 | Anonymous inner class                            | static nested class                                            |
|---------------------------------------------------|----------------------------------------------|-----------------------------------|--------------------------------------------------|----------------------------------------------------------------|
| Access modifiers allowed                          | public, protected, private or default access | None. Already local to method     | None. Already local to statement                 | public, protected, private or default access                   |
| Can extend any class and any number of interfaces | Yes                                          | Yes                               | No-must have exactly one superclass or interface | Yes                                                            |
| Can be abstract                                   | Yes                                          | Yes                               | N/A-because no class defination                  | Yes                                                            |
| Can be final                                      | Yes                                          | Yes                               | N/A-because no class defination                  | Yes                                                            |
| Can access instance members of enclosing class    | Yes                                          | Yes                               | Yes                                              | No (not directly; requires an instance of the enclosing class) |
| Can access local variables of enclosing class     | No                                           | Yes-if final or effectively final | Yes-if final or effectively final                | No                                                             |
| Can declare static methods                        | No                                           | No                                | No                                               | Yes                                                            |

# Summary

Absolutely! The `instanceof` keyword in Java is used to check if an object is an instance of a particular class or interface. Here's a breakdown of how it works:

- **Basic Usage**: The syntax is `object instanceof Class`, where `object` is the object being tested and `Class` is the class or interface being checked against.
- **Subclasses and Subinterfaces**: `instanceof` considers subclasses and subinterfaces. If an object is an instance of a subclass or subinterface, `instanceof` will return `true`.
- **Handling Null**: If the object being checked is `null`, `instanceof` will always return `false`.
- **Compiler Error**: If the compiler can determine at compile time that there's no way for `instanceof` to return `true`, it will generate a compiler error. This can happen when checking against unrelated types.
- **Virtual Method Invocation**: Java uses virtual method invocation to find the right method to call, which means that it looks at the actual runtime type of the object to determine which method implementation to use, even if the method is called from a superclass.

Here's a simple example demonstrating the usage of `instanceof`:

```java
class Animal {}
class Dog extends Animal {}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();

        // Check if 'animal' is an instance of 'Animal' and 'Dog'
        System.out.println(animal instanceof Animal);  // true
        System.out.println(animal instanceof Dog);     // true

        // Check if 'animal' is an instance of 'Object'
        System.out.println(animal instanceof Object);  // true

        // Check if 'animal' is an instance of an unrelated class 'String'
        // This will cause a compile-time error because 'animal' cannot be an instance of 'String'
        // System.out.println(animal instanceof String);  // Compile error

        // Check if 'null' is an instance of 'Object'
        System.out.println(null instanceof Object);    // false
    }
}
```

In this example:
- `Dog` is a subclass of `Animal`.
- The `main` method demonstrates various uses of `instanceof` to check the type of an object.

Absolutely! Let's delve into the details of these three important methods:

1. **toString() Method**:
   - Purpose: It provides a human-readable representation of the object. This method is often used for debugging or logging purposes.
   - Implementation: By default, the `toString()` method in the `Object` class returns a string consisting of the class name followed by the "@" symbol and the object's hash code in hexadecimal. However, classes can override this method to provide a custom string representation.
   - Example:

     ```java
     public class MyClass {
         private int id;
         private String name;

         // Constructor and other methods...

         @Override
         public String toString() {
             return "MyClass{id=" + id + ", name='" + name + "'}";
         }
     }
     ```

2. **equals() Method**:
   - Purpose: It specifies the criteria for determining whether two objects are equal. By default, the `equals()` method in the `Object` class compares object references for equality (i.e., whether they refer to the same object in memory).
   - Implementation: Classes can override this method to provide their own definition of equality based on specific instance variables. The method should return `true` if the objects are logically equal and `false` otherwise.
   - Example:

     ```java
     @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;
         if (obj == null || getClass() != obj.getClass()) return false;
         MyClass other = (MyClass) obj;
         return id == other.id && Objects.equals(name, other.name);
     }
     ```

3. **hashCode() Method**:
   - Purpose: It provides a hash code value for an object. This method is used by hash-based collections such as `HashMap` to determine the bucket where an object should be stored.
   - Implementation: The default implementation in the `Object` class generates a hash code based on the memory address of the object. However, classes should override this method to generate hash codes based on the object's state (i.e., its instance variables).
   - Example:

     ```java
     @Override
     public int hashCode() {
         return Objects.hash(id, name);
     }
     ```

Remember:
- It's important for the `equals()` and `hashCode()` methods to be consistent. If two objects are equal (`obj1.equals(obj2)` returns `true`), their hash codes should be equal as well (`obj1.hashCode() == obj2.hashCode()`).
- Classes should always override `hashCode()` if they override `equals()`.
- These methods are crucial for proper functioning when objects are used in collections or compared for equality.

Absolutely, enums are a powerful feature in Java. Here's a breakdown of their key characteristics:

1. **Definition and Usage**:
   - The `enum` keyword is used to define an enumeration, which represents a fixed set of constants.
   - Enums are commonly used to represent things like days of the week, months, status codes, and more.
   - Enums provide type-safety, meaning that only the defined enum values can be assigned to variables of that enum type.

2. **Switch Statements**:
   - Enums are often used in switch statements to perform different actions based on the value of the enum.
   - Each enum value can be used in a `case` label within the switch statement.

3. **Instance Variables, Constructors, and Methods**:
   - Enums can have instance variables, constructors, and methods, just like regular classes.
   - Each enum value can have its own set of instance variables, allowing them to store specific data.
   - Enums can define methods that operate on the enum values or provide behavior specific to each value.

4. **Abstract Enums**:
   - An enum can be declared as abstract, which means that it contains abstract methods that each enum value must implement.
   - This allows enums to have value-specific behavior that can be customized for each constant.

5. **Concrete Enums**:
   - If an enum provides a default implementation for its methods, it can be considered concrete.
   - Enum values can choose whether to override these default implementations or use them as-is.

Example:

```java
public enum DayOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String displayName;

    // Constructor
    DayOfWeek(String displayName) {
        this.displayName = displayName;
    }

    // Method to get the display name of the day
    public String getDisplayName() {
        return displayName;
    }
}
```

Usage:

```java
DayOfWeek day = DayOfWeek.MONDAY;
switch (day) {
    case MONDAY:
        System.out.println("It's Monday!");
        break;
    case TUESDAY:
        System.out.println("It's Tuesday!");
        break;
    // Other cases...
}

System.out.println("Display name of Monday: " + DayOfWeek.MONDAY.getDisplayName());
```

Absolutely, let's break down the four types of nested classes in Java:

1. **Member Inner Classes**:
   - Defined at the member level of a class, like methods or instance variables.
   - Require an instance of the outer class to be instantiated.
   - Have access to private members of the outer class.
   - Can be declared with any access modifier (`public`, `private`, `protected`, or default).

2. **Local Inner Classes**:
   - Defined within a method of a class.
   - Can access private members of the enclosing class.
   - Have access to local variables of the enclosing method if they are declared `final` or effectively final.
   - Do not have access specifiers (e.g., `public`, `private`, etc.) as they are local to the method.

3. **Anonymous Inner Classes**:
   - A special type of local inner class that does not have a name.
   - Declared and instantiated at the same time using the `new` keyword.
   - Required to extend exactly one class by name or implement exactly one interface.
   - Often used for short, one-time implementations of interfaces or extending classes.

4. **Static Nested Classes**:
   - Defined as a static member of the outer class.
   - Can be instantiated without an instance of the outer class.
   - Similar to regular top-level classes but are nested for better organization and encapsulation.
   - Cannot directly access non-static members of the outer class.

Example demonstrating each type:

```java
public class Outer {
    private int outerField = 10;

    // Member Inner Class
    public class MemberInner {
        public void printOuterField() {
            System.out.println("Outer field: " + outerField);
        }
    }

    // Method with Local Inner Class
    public void createLocalInner() {
        int localVar = 20;
        class LocalInner {
            public void printLocalVar() {
                System.out.println("Local var: " + localVar);
            }
        }
        LocalInner localInner = new LocalInner();
        localInner.printLocalVar();
    }

    // Method with Anonymous Inner Class
    public void createAnonymousInner() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class running...");
            }
        };
        runnable.run();
    }

    // Static Nested Class
    public static class StaticNested {
        public void printMessage() {
            System.out.println("Static nested class message");
        }
    }
}
```

Usage:

```java
Outer outer = new Outer();
Outer.MemberInner memberInner = outer.new MemberInner();
memberInner.printOuterField();

outer.createLocalInner();

outer.createAnonymousInner();

Outer.StaticNested staticNested = new Outer.StaticNested();
staticNested.printMessage();
```

These nested classes provide a way to logically group classes together and encapsulate them within another class, improving code organization and readability.

Absolutely, let's summarize the key concepts covered in this chapter:

1. **Access Modifiers**:
   - Control the visibility of classes, methods, and fields.
   - `public`, `private`, `protected`, and default (no modifier).
   - Determine where a class, method, or field can be accessed from.

2. **Overloading**:
   - Defining multiple methods in the same class with the same name but different parameters.
   - Parameters may differ in number, type, or order.

3. **Overriding**:
   - Providing a new implementation for a method in a subclass that is already defined in its superclass.
   - Requires the method signature (name and parameters) to match that of the superclass method.
   - Used for achieving polymorphism and dynamic method dispatch.

4. **Abstract Classes**:
   - Classes that cannot be instantiated but can be subclassed.
   - May contain abstract methods (methods without a body) that must be implemented by subclasses.
   - Used to define a common interface for a group of related subclasses.

5. **Static**:
   - Modifier used to declare members that belong to the class itself, rather than instances of the class.
   - Static fields and methods can be accessed without creating an instance of the class.

6. **Final**:
   - Modifier used to make a class, method, or field immutable or unchangeable.
   - A final class cannot be subclassed, a final method cannot be overridden, and a final field cannot be reassigned.

7. **Imports**:
   - Used to make classes from other packages available in the current source file.
   - Allow you to reference classes by their simple names rather than their fully qualified names.

8. **@Override Annotation**:
   - Optional annotation used to indicate that a method is intended to override a method in a superclass or implement a method from an interface.
   - Helps improve code readability and ensures that methods are actually overriding as intended.

These concepts are fundamental to understanding Java programming and are essential for writing robust and maintainable code.

# Exam Essentials

**Be able to identify the output of code using instanceof**

Absolutely, understanding how `instanceof` works is crucial for predicting code behavior. Here's a quick summary:

- **Usage**: `instanceof` is a binary operator used to check if an object is an instance of a particular class or interface.
- **Syntax**: `object instanceof Class`
- **Behavior**:
   - Returns `true` if the object on the left-hand side is an instance of the class or interface on the right-hand side, or if it's a subclass/subinterface.
   - Returns `false` if the object is `null` or not an instance of the specified class/interface.
- **Examples**:
  ```java
  Object obj = new String("Hello");
  System.out.println(obj instanceof String);  // Output: true
  
  Object obj2 = null;
  System.out.println(obj2 instanceof String); // Output: false
  
  Object obj3 = new Integer(10);
  System.out.println(obj3 instanceof String); // Output: false
  ```

By understanding how `instanceof` works, you can effectively use it to check object types at runtime, enabling more flexible and dynamic code behavior.

**Recognize correct and incorrect implementations of equals(), hashCode(), and toString()**

Here's how you can recognize correct and incorrect implementations of `equals()`, `hashCode()`, and `toString()`:

- **equals(Object obj)**:
   - **Correct**: Returns `false` when called with `null` or a class of the wrong type, and compares the relevant instance variables to determine equality.
   - **Incorrect**: Returns `true` or throws an exception when called with `null` or a class of the wrong type, or does not properly compare instance variables for equality.

- **hashCode()**:
   - **Correct**: Returns a consistent integer calculated based on all or some of the instance variables used in `equals()`.
   - **Incorrect**: Returns a random or inconsistent integer, or does not consider the same set of instance variables used in `equals()`.

- **toString()**:
   - **Correct**: Returns any string representation of the object, providing useful information about its state.
   - **Incorrect**: Returns `null`, an empty string, or does not provide meaningful information about the object's state.

Here's an example demonstrating correct implementations:

```java
public class MyClass {
    private int id;
    private String name;

    // Constructor, getters, setters...

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyClass myObj = (MyClass) obj;
        return id == myObj.id && Objects.equals(name, myObj.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```

These implementations ensure consistent behavior across `equals()`, `hashCode()`, and `toString()`, providing correctness and predictability in your code.

**Be able to create enum classes. enums have a list of values**

Certainly! Here's an example demonstrating the creation of an enum class:

```java
public enum DayOfWeek {
    // Enum values
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    // Instance variables (optional)
    private final String abbreviation;

    // Constructor (private or package-private)
    DayOfWeek() {
        this.abbreviation = name().substring(0, 3);
    }

    // Method
    public String getAbbreviation() {
        return abbreviation;
    }

    // Example of abstract method
    public abstract boolean isWeekend();
}
```

In this example:

- We define an enum class `DayOfWeek` representing the days of the week.
- Enum values are listed (`MONDAY`, `TUESDAY`, etc.).
- An instance variable `abbreviation` is declared to store the abbreviation of each day.
- The constructor initializes the `abbreviation` variable based on the name of the enum value.
- A method `getAbbreviation()` is provided to retrieve the abbreviation.
- An abstract method `isWeekend()` is declared, which each enum value must implement.

This demonstrates the basic structure of an enum class in Java.

**Identify and use nested classes**

Nested classes in Java provide a way to logically group classes that are only used in one place, which increases encapsulation and creates more readable and maintainable code. Here's a breakdown of the types of nested classes:

1. **Member Inner Class**:
   - Declared at the member level of a class.
   - Can be instantiated with code like `outer.new Inner()`.
   - Can access members of the enclosing class, including private members.
   - Can be declared with any access modifier (public, private, protected, or default).
   - Can be abstract or final.
   - Cannot declare static fields or methods.

2. **Local Inner Class**:
   - Defined within a method or scope block.
   - Scoped to the end of the current block of code.
   - Cannot have static members.
   - Can access all fields and methods of the enclosing class.
   - Can access local variables of the enclosing method if they are final or effectively final.

3. **Anonymous Inner Class**:
   - A special type of local inner class that doesn't have a name.
   - Declared and instantiated in one statement using the `new` keyword.
   - Limited to extending a class or implementing an interface.
   - Must end with a semicolon.
   - Useful for short implementations that won't be reused elsewhere.

4. **Static Nested Class**:
   - Defined at the member level and marked as `static`.
   - Can be instantiated without an instance of the enclosing class.
   - Cannot access instance variables of the enclosing class without an explicit object.
   - Creates a namespace with the enclosing class name.
   - Can be made private or use other access modifiers for encapsulation.
   - The enclosing class can refer to fields and methods of the static nested class.

Each type of nested class has its own use cases and characteristics, allowing developers to choose the most appropriate type based on their specific requirements.

**Know how to use imports and static imports**

Imports in Java allow you to reference classes, interfaces, enums, and other types from different packages without typing their fully qualified names each time. Here's how to use imports and static imports effectively:

1. **Class Imports**:
   - You can import individual classes by their fully qualified names.
   - For example: `import java.util.ArrayList;`
   - This allows you to refer to `ArrayList` without specifying its package each time.

2. **Wildcard Imports**:
   - You can use wildcard imports to import all classes in a package.
   - For example: `import java.util.*;`
   - Wildcard imports don't include classes from subpackages.

3. **Conflicting Imports**:
   - If there's a conflict between two classes with the same name from different packages, the class name import takes precedence over the wildcard import.
   - For example, if you import `java.util.*` and `java.sql.*`, and there's a class named `Date` in both packages, `java.util.Date` will be used.

4. **Static Imports**:
   - Static imports allow you to access static members (fields and methods) of a class directly, without specifying the class name.
   - For example: `import static java.lang.Math.*;`
   - This allows you to use `sqrt()` instead of `Math.sqrt()` after the import.

5. **Importing Enums and Interfaces**:
   - Enums and interfaces can also be imported using regular class imports.
   - For example: `import java.util.concurrent.TimeUnit;`
   - This allows you to refer to `TimeUnit` without specifying its package each time.

Remember to use imports judiciously to keep your code clean and readable. Avoid excessive use of wildcard imports, as it can lead to namespace pollution and make code harder to understand. Additionally, use static imports sparingly and only for commonly used static members to enhance code readability.

**Understand the rules for method overriding and overloading**

Method overriding and overloading are fundamental concepts in Java that allow developers to create more flexible and polymorphic code. Here's an overview of the rules for method overriding and overloading:

1. **Method Overriding**:
   - Method overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass.
   - Rules for method overriding:
      - The method in the subclass must have the same signature (method name and parameter list) as the method in the superclass.
      - The method in the subclass must be at least as accessible as the method in the superclass (e.g., if the superclass method is public, the overriding method cannot be private).
      - The subclass method can't declare any new or broader checked exceptions than those declared by the superclass method. It can declare narrower exceptions or throw no exceptions.
      - If the superclass method returns a reference to an object, the subclass method can return a subtype of that object (covariant return type).
   - Optional: You can use the `@Override` annotation to indicate that a method in a subclass is intended to override a method in the superclass. This annotation helps catch errors at compile time if the method signature doesn't match any method in the superclass.

2. **Method Overloading**:
   - Method overloading occurs when multiple methods in the same class or different classes have the same name but different parameter lists.
   - Rules for method overloading:
      - The methods must have the same name but different parameter lists (different number or types of parameters).
      - Return types can be different or even the same.
      - Methods can be overloaded in the same class or in subclasses.
      - Overloaded methods can have different access modifiers and can declare different exceptions.

By understanding and applying these rules, developers can effectively use method overriding and overloading to enhance code reusability, flexibility, and readability in their Java applications.
