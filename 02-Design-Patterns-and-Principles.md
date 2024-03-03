## Design Patterns and Principles

### Designing an Interface

- An interface is an abstract data type, similar to a class that defines a
  list of public abstract methods that any class implementing the interface must provide.
- An interface may also include constant public static final variables, default methods, and
  static methods.

```java
// Define interface
public interface Fly {
     public int getWingSpan() throws Exception;
     public static final int MAX_SPEED = 100;
     public default void land() {
        System.out.println("Animal is landing");
     }
     public static double calculateSpeed(float distance, double time) {
        return distance/time;
     }
}
// Implementation
public class Eagle implements Fly {
     public int getWingSpan() {
        return 15;
     }
     public void land() {
        System.out.println("Eagle is diving fast");
     }
}
```

**Purpose of an Interface**

- An interface provides a way for one individual to develop code that uses anotherindividual’s code,
without having access to the other individual’s underlying implementation.
- Interfaces can facilitate rapid application development by enabling development teams to create 
applications in parallel, rather than being directly dependent on each other.

### Introducing Functional Programming

- Java defines `a functional interface` as an interface that contains a single abstract method.
- Functional interfaces are used as the basis for lambda expressions in functional programming.
- A lambda expression is a block of code that gets passed around, like an anonymous method.

**Defining a Functional Interface**

- While it is a good practice to mark a functional interface with the @FunctionalInterface
  annotation for clarity, it is not required with functional programming.

- All three are valid functional interfaces:
```
@FunctionalInterface
public interface Sprint {
     public void sprint(Animal animal);
}

public interface Run extends Sprint {}

public interface SprintFaster extends Sprint {
     public void sprint(Animal animal);
}
public interface Skip extends Sprint {
     public default int getHopCount(Kangaroo kangaroo) {return 10;}
     public static void skip(int speed) {}
}
```

**Implementing Functional Interfaces with Lambdas**

- Recall that lambda expressions rely on the notion of deferred execution.
- Deferred execution means that code is specified now but runs later.
- Even though the execution is deferred, the compiler will still validate that the code syntax is 
properly formed.

**Understanding Lambda Syntax**

- The syntax of lambda expressions is tricky because many parts are optional.

**Spotting Invalid Lambdas**

- Parentheses can be omitted only if there is exactly one parameter and the data type is not specified.

```java
Duck d -> d.quack() // DOES NOT COMPILE
a,d -> d.quack() // DOES NOT COMPILE
Animal a, Duck d -> d.quack() // DOES NOT COMPILE
```

- When using {} in the body of the lambda expression, you must use the return statement if the 
functional interface method that lambda implements returns a value.

```java
() -> true // 0 parameters
a -> {return a.startsWith("test");} // 1 parameter
(String a) -> a.startsWith("test") // 1 parameter
(int x) -> {} // 1 parameter
(int y) -> {return;} // 1 parameter
```

**Applying the Predicate Interface**

```java
public interface Predicate<T> {
     public boolean test(T t);
}
```

### Implementing Polymorphism

- Polymorphism is the ability of a single interface to support multiple underlying forms.
- In Java, this allows multiple types of objects to be passed to a single method or class.

```java
public interface LivesInOcean { public void makeSound(); }
public class Dolphin implements LivesInOcean {
     public void makeSound() { System.out.println("whistle"); }
}
public class Whale implements LivesInOcean {
     public void makeSound() { System.out.println("sing"); }
}
public class Oceanographer {
     public void checkSound(LivesInOcean animal) {
         animal.makeSound();
     }
     public void main(String[] args) {
         Oceanographer o = new Oceanographer();
         o.checkSound(new Dolphin()); // whistle
         o.checkSound(new Whale()); // sing
     }
}
```

**Distinguishing between an Object and a Reference**

Following two rules:
- The type of the object determines which properties exist within the object in memory.
- The type of the reference to the object determines which methods and variables are accessible to
the Java program.

```java
Lemur lemur = new Lemur();
Object lemurAsObject = lemur;
```

**Casting Object References**

Basic rules to keep in mind when casting variables:
- Casting an object from a subclass to a superclass doesn’t require an explicit cast.
- Casting an object from a superclass to a subclass requires an explicit cast.
- The compiler will not allow casts to unrelated types.
```java
public class Bird {}
public class Fish {
     public static void main(String[] args) {
         Fish fish = new Fish();
         Bird bird = (Fish)bird; // DOES NOT COMPILE
     }
}
```
- Even when the code compiles without issue, an exception may be thrown at runtime if the object 
being cast is not actually an instance of that class.

```java
public class Rodent {
}
public class Capybara extends Rodent {
     public static void main(String[] args) {
         Rodent rodent = new Rodent();
         Capybara capybara = (Capybara)rodent; // Throws ClassCastException at runtime
     }
}
```

### Understanding Design Principles

A design principle is an established idea or best practice that facilitates the software design-process.
In this section, we will discuss design principles for creating Java classes and why-those principles 
lead to better and more manageable code bases. In general, following good-design principles leads to:
- More logical code
- Code that is easier to understand
- Classes that are easier to reuse in other relationships and applications
- Code that is easier to maintain and that adapts more readily to changes in the application requirements

**Encapsulating Data**

- In software development, encapsulation is the idea of combining fields and methods in a class such that the methods
  operate on the data, as opposed to the users of the class accessing the fields directly.
- In Java, it is commonly implemented with `private` instance members that have `public` methods to retrieve or modify the
  data, commonly referred to as getters and setters, respectively.

**Creating JavaBeans**

| Rule                                                                                                 | Example                                                                        | 
|------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| Properties are private.                                                                              | private int age;                                                               | 
| Getter for non‐boolean properties begins with get.                                                   | public int getAge() {return age;}                                              | 
| Getters for boolean properties may begin with is or get.                                             | public boolean isBird() {return bird;} public boolean getBird() {return bird;} | 
| Setter methods begin with set                                                                        | public void setAge(int age) {this.age = age;}                                  | 
| Followed by the first letter of property in uppercase and followed by there of the property name     | public void setNumChildr(int numChildren) {this.numChildren = numChildren;}    |

**Applying the Is‐a Relationship**

**Applying the Has‐a Relationship**

**Composing Objects**

### Working with Design Patterns

- A design pattern is an established general solution to a commonly occurring software development problem.
- The purpose of a design pattern is to leverage the wealth of knowledge of developers who have come before you in order
  to solve old problems that you may encounter easily.

**Applying the Singleton Pattern**

1. Type: `creational patterns`- a type of software design pattern that manages the creation of objects within an application.

2. Problem: How do we create an object in memory only once in an application and have it shared by multiple classes?

3. Solution:
    - The singleton pattern is a creational pattern focused on creating only one instance of an object in memory within
      an application, sharable by all classes and threads within the application.
    - The globally available object created by the singleton pattern is referred to as a singleton.
    - Singletons may also improve performance by loading reusable data that would otherwise be time consuming to store
      and reload each time it is needed.

```java
public class HayStorage {
     private int quantity = 0;
     private HayStorage() {}
     private static final HayStorage instance = new HayStorage();
     public static HayStorage getInstance() {
         return instance;
     }
     public synchronized void addHay(int amount) {
         quantity += amount;
     }
     public synchronized boolean removeHay (int amount) {
         if(quantity < amount) return false;
         quantity -= amount;
         return true;
     }
     public synchronized int getHayQuantity() {
         return quantity;
     }
}
```

*Applying Lazy Instantiation to Singletons*

Another technique is to delay creation of the singleton until the first time the getInstance() method is called:

```java
// Lazy instantiation
public class VisitorTicketTracker {
     private static VisitorTicketTracker instance;
     private VisitorTicketTracker() {
     }
     public static VisitorTicketTracker getInstance() {
         if(instance == null) {
             instance = new VisitorTicketTracker(); // NOT THREAD-SAFE!
         }
         return instance;
     }
     // Data access methods
     ...
}
```

+ Lazy instantiation reduces memory usage and improves performance when an application starts up.
+ Not create the singleton object when the class is loaded but rather the first time it is requested by a client.

*Creating Unique Singletons*

+ Thread safety is the property of an object that guarantees safe execution by multiple threads at the same time.

```java
public static synchronized VisitorTicketTracker getInstance() {
     if(instance == null) {
         instance = new VisitorTicketTracker();
     }
     return instance;
}
```

*Singletons with Double‐Checked Locking*

+ This solution is better than our previous version, as it performs the synchronization step only when the singleton does not exist.
+ If our singleton is accessed thousands of times over many hours or days, this means that only the fi rst few calls would require
  synchronization, and the rest would not.

```java
private static volatile VisitorTicketTracker instance;
     public static VisitorTicketTracker getInstance() {
         if(instance == null) {
             synchronized(VisitorTicketTracker.class) {
                 if(instance == null) {
                    instance = new VisitorTicketTracker();
                 }
         }
     }
     return instance;
}
```

**Creating Immutable Objects**

1. Type: `creational pattern`

2. Problem: How do we create read‐only objects that can be shared and used by multiple classes?

3. Solution:
    - The immutable object pattern is a creational pattern based on the idea of creating objects whose state does not
      change after they are created and can be easily shared across multiple classes.
    - Immutable objects go hand and hand with encapsulation, except that no setter methods exist that modify the object.
    - Since the state of an immutable object never changes, they are inherently thread‐safe.

*Applying an Immutable Strategy*

1. Use a constructor to set all properties of the object.
2. Mark all of the instance variables private and final .
3. Don’t define any setter methods.
4. Don’t allow referenced mutable objects to be modified or accessed directly.
5. Prevent methods from being overridden.

```java
import java.util.*
public final class Animal {
     private final String species;
     private final int age;
     private final List<String> favoriteFoods;
     public Animal(String species, int age, List<String> favoriteFoods) {
         this.species = species;
         this.age = age;
         if(favoriteFoods == null) {
             throw new RuntimeException("favoriteFoods is required");
         }
         this.favoriteFoods = new ArrayList<String>(favoriteFoods);
     }
     public String getSpecies() {
         return species;
     }
     public int getAge() {
         return age;
     }
     public int getFavoriteFoodsCount() {
         return favoriteFoods.size();
     }
     public String getFavoriteFood(int index) {
         return favoriteFoods.get(index);
     }
}
```

*“Modifying” an Immutable Object*

+ How do we modify immutable objects if they are inherently unmodifiable?
> The answer is, we can’t! Alternatively, we can create new immutable objects that contain all of the same
information as the original object plus whatever we wanted to change.

### Using the Builder Pattern

1. Type: `creational pattern`

2. Problem: As our data objects grow in size, the constructor may grow to contain many attributes. How do we create an
   object that requires numerous values to be set at the time the object is instantiated?

3. Solution:
    - The builder pattern is a creational pattern in which parameters are passed to a builder object, often through method
      chaining, and an object is generated with a final build call.
    - It is often used with immutable objects, since immutable objects do not have setter methods and must be created
      with all of their parameters set, although it can be used with mutable objects as well.

```java
import java.util.*
public class AnimalBuilder {
     private String species;
     private int age;
     private List<String> favoriteFoods;
     public AnimalBuilder setAge(int age) {
         this.age = age;
         return this;
     }
     public AnimalBuilder setSpecies(String species) {
         this.species = species;
         return this;
     }
     public AnimalBuilder setFavoriteFoods(List<String> favoriteFoods) {
         this.favoriteFoods = favoriteFoods;
         return this;
     }
     public Animal build() {
         return new Animal(species,age,favoriteFoods);
     }
}
```

The following two code snippets are both valid uses of this builder:

```java
AnimalBuilder duckBuilder = new AnimalBuilder();
duckBuilder
 .setAge(4)
 .setFavoriteFoods(Arrays.asList("grass","fish")).setSpecies("duck");
Animal duck = duckBuilder.build();

Animal flamingo = new AnimalBuilder()
 .setFavoriteFoods(Arrays.asList("algae","insects"))
 .setSpecies("flamingo").build();
```

### Creating Objects with the Factory Pattern

1. Type: `creational pattern`

2. Problem: How do we write code that creates objects in which the precise type of the object
   may not be known until runtime?

3. Solution:
    - The factory pattern, sometimes referred to as the factory method pattern, is a creational pattern based on the idea
      of using a factory class to produce instances of objects based on a set of input parameters.
    - It is similar to the builder pattern, although it is focused on supporting class polymorphism.

```java
public abstract class Food {
     private int quantity;
     public Food(int quantity) {
         this.quantity = quantity;
     }
     public int getQuantity() {
         return quantity;
     }
     public abstract void consumed();
}

public class Hay extends Food {
     public Hay(int quantity) {
         super(quantity);
     }
     public void consumed() {
         System.out.println("Hay eaten: "+getQuantity());
     }
}

public class Pellets extends Food {
     public Pellets(int quantity) {
         super(quantity);
     }
     public void consumed() {
         System.out.println("Pellets eaten: "+getQuantity());
     }
}

public class Fish extends Food {
     public Fish(int quantity) {
         super(quantity);
     }
     public void consumed() {
         System.out.println("Fish eaten: "+getQuantity());
     }
}
```

Now, let’s define a FoodFactory using the factory pattern that returns a food type based on some set of inputs:

```java
public class FoodFactory {
     public static Food getFood(String animalName) {
     switch(animalName) {
         case "zebra": return new Hay(100);
         case "rabbit": return new Pellets(5);
         case "goat": return new Pellets(30);
         case "polar bear": return new Fish(10);
     }
     // Good practice to throw an exception if no matching subclass could be found
     throw new UnsupportedOperationException("Unsupported animal: "+animalName);
     }
}

public class ZooKeeper {
     public static void main(String[] args) {
         final Food food = FoodFactory.getFood("polar bear");
         food.consumed();
     }
}
```

### Summary

1. **Be able to write code that declares, implements, and/or extends interfaces.**
- An interface is like an abstract class that defines a set of public abstract methods, which classes implementing the
  interface must provide.
- A class may implement multiple interfaces as well as extend classes that implement interfaces, allowing for limited
  multiple inheritance in Java.
- Interfaces may extend other interfaces, although they may not extend a class and vice versa.
- Interfaces may also contain `public static final` constant values, `public static methods`, and `public default methods`.

2. **Know how to create and recognize a functional interface.**
- A functional interface is one that has exactly one abstract method.
- It is the primary manner in which lambda expressions are passed between methods.
- Java includes a Predicate interface for testing a generic type and returning a boolean expression.

3. **Be able to write valid lambda expressions.**
- A lambda expression is like an anonymous method that can be passed to a method, relying on deferred execution to process the
  expression at a later time.
- It has various syntax options, both long and short.
- Lambda expressions are used throughout Java 8 and in numerous questions on the exam.

4. **Understand polymorphism.**
- An object in Java may take on a variety of forms, in part depending on the reference used to access the object.
- The type of the object determines which properties exist within the object in memory, whereas the type of the reference to
  the object determines which methods and variables are accessible to the Java program.
- An instance can be automatically cast to a superclass or interface reference without an explicit cast.
- Alternatively, an explicit cast is required if the reference is being narrowed to a subclass of the object.
- The Java compiler doesn’t permit casting to unrelated types.
- Finally, you should be able to distinguish between compile‐time casting errors and those that will
  not occur until runtime, throwing a ClassCastException.

5. **Understand the importance of design principles and design patterns.**
- A design principle is an established idea or best practice that facilitates the software design process.
- A design pattern is an established general solution to a commonly occurring software development problem.

6. **Know how to implement encapsulation.**
- Encapsulation is based on the idea of combining fields and methods in a class such that the methods operate on the data,
  as opposed to users of the class accessing the fields directly.
- It can be used to prevent users from creating object states that violate class invariants.
- In Java, it is often implemented with JavaBeans, using the private access modifier on instance variables and public getter and setter methods.

7. **Be able to apply the is‐a and has‐a tests.**
- The is‐a test is used to test whether an object is of a particular type, and it is used for both classes and interfaces.
- The has‐a test is used to determine whether an object contains a reference to another object as an instance property

8. **Be able to apply object composition and distinguish it from inheritance.**
- Object composition is the idea of creating a class by connecting other classes as members using the has‐a principle.
- Inheritance is the idea of creating a class that inherits all of its reusable methods and objects from a parent class.
- Both are used to create complex data models, each with its own advantages and disadvantages.

9. **Be able to apply creational patterns including the singleton pattern and the immutable object pattern**
- The singleton and immutable object patterns are both types of creational patterns, which are design patterns that
  facilitate the creation of objects with an application.
- The singleton pattern solves the problem of how to create a single instance of an object in memory that multiple classes
  can share by centralizing the object‐creation mechanisms.
- The immutable object pattern is used to create read‐only objects that cannot be modified by other classes.
- Although immutable objects cannot be modified, they can be copied to new immutable objects with the updated information.