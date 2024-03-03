## Java Class Design

### Reviewing OCA Concepts

1. Access Modifiers

| Can access                                                                | If that member is private? | If that member has default access? | If that member is protected? | If that member is public? |
|---------------------------------------------------------------------------|-----------------------------|-------------------------------------|-------------------------------|----------------------------|
| Member in the same class                                                  | yes                         | yes                                 | yes                           | yes                        |
| Member in another class in the same package                               | no                          | yes                                 | yes                           | yes                        |
| Member in a superclass in a different package                             | no                          | no                                  | yes                           | yes                        |
| Method/field in a class (that is not a superclass) in a different package | no | no                               | no                            | yes                        |

2. Overloading and Overriding

- The `method signature` is the method name and the parameter list. For overloading, the method
  parameters must vary by type and/or number.
- When multiple overloaded methods are present, Java looks for the closest match first. It
  tries to find the following:
    + Exact match by type
    + Matching a superclass type
    + Converting to a larger primitive type
    + Converting to an autoboxed type
    + Varargs
- For overriding, the overridden method has a few rules:
    + The access modifier must be the same or more accessible.
    + The return type must be the same or a more restrictive type, also known as covariant
      return types.
    + If any checked exceptions are thrown, only the same exceptions or subclasses of those
      exceptions are allowed to be thrown.

> The methods must not be static. (If they are, the method is hidden and not
overridden.)

3. Abstract Classes

- An abstract class may contain any number of methods including zero.
- The methods can be abstract or concrete.
- Abstract methods may not appear in a class that is not abstract.
- The first concrete subclass of an abstract class is required to implement all abstract
  methods that were not implemented by a superclass.

4. Static and Final

- `final` prevents a variable from changing or a method from being overridden.
- `static` makes a variable shared at the class level and uses the class name to refer to a method.
- `static` and `final` are allowed to be added on the class level too.

5. Imports

### Using instanceof

- In a `instanceof B`, the expression returns true if:
    + the reference to which a points is an instance of class B.
    + a subclass of B (directly or indirectly).
    + a class that implements the B interface (directly or indirectly).

- Tricky:
```java
class HeavyAnimal { }
class Hippo extends HeavyAnimal { }
class Elephant extends HeavyAnimal { }

Hippo anotherHippo = new Hippo();
boolean b5 = anotherHippo instanceof Elephant; // DOES NOT COMPILE
```

### Understanding Virtual Method Invocation

### Annotating Overridden Methods

When you see @Override show up on the exam, you must check carefully that the method is doing one of three things:
- Implementing a method from an interface
- Overriding a superclass method of a class shown in the example
- Overriding a method declared in Object, such as hashCode, equals, or toString

### Coding equals, hashCode, and toString

1. toString

```java
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
```

2. equals

```java
    public boolean equals(Object obj) {
        return (this == obj);
    }
```

The equals() method implements an equivalence relation on non‐null object references:
- **It is reflexive**: For any non‐null reference value x, x.equals(x) should return true.
- **It is symmetric**: For any non‐null reference values x and y, x.equals(y) should return
  true if and only if y.equals(x) returns true.
- **It is transitive**: For any non‐null reference values x, y, and z, if x.equals(y) returns
  true and y.equals(z) returns true, then x.equals(z) should return true.
- **It is consistent**: For any non‐null reference values x and y, multiple invocations of
  x.equals(y) consistently return true or consistently return false, provided no
  information used in equals comparisons on the objects is modified.
- For any non‐null reference value x, x.equals(null) should return false

3. hashCode

Whenever you override equals(), you are also expected to override hashCode().

```java
    @IntrinsicCandidate
    public native int hashCode();
```

The three points in the contract boil down to these:
- Within the same program, the result of hashCode() must not change. This means that
  you shouldn’t include variables that change in figuring out the hash code. In our hippo
  example, including the name is fine. Including the weight is not because hippos change
  weight regularly.
- If equals() returns true when called with two objects, calling hashCode() on each of
  those objects must return the same result. This means hashCode() can use a subset of
  the variables that equals() uses. You saw this in the card example. We used only one
  of the variables to determine the hash code.
- If equals() returns false when called with two objects, calling hashCode() on each of
  those objects does not have to return a different result. This means hashCode() results
  do not need to be unique when called on unequal objects.

### Working with Enums

```java
  public enum Season {
   WINTER, SPRING, SUMMER, FALL
  }
```

- An enumeration is like a fixed set of constants. In Java, an enum is a class that represents an
  enumeration.
- It is much better than a bunch of constants because it provides type‐safe checking.
- They are also comparable using == because they are like static final constants.

```java
  Season s = Season.SUMMER;
  System.out.println(Season.SUMMER); // SUMMER
  System.out.println(s == Season.SUMMER);
```

- Can't extend an enum

```java
  public enum ExtendedSeason extends Season { } // DOES NOT COMPILE
```

1. Using Enums in Switch Statements

```java
  Season summer = Season.SUMMER;
  switch (summer) {
    case WINTER: // Notice that we just typed the value of the enum rather than writing Season.WINTER.
      System.out.println("Get out the sled!");
      break;
    case SUMMER:
      System.out.println("Time for the pool!");
      break;
    default:
     System.out.println("Is it summer yet?");
  }
```

2. Adding Constructors, Fields, and Methods

- Enums can have more in them than just values. It is common to give state to each one.

```java
public enum Season {
  WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");
  private final String expectedVisitors;

  Season(String expectedVisitors) {
    this.expectedVisitors = expectedVisitors;
  }

  public String getExpectedVisitors() {
    return this.expectedVisitors;
  }
}
```

- The semicolon at the end of the enum values is optional only if the only thing in the enum is that list of values.

- The enum itself has an abstract method. This means that each and every enum value is required to implement this method. If we forget one, we get a compiler error.

```java
public enum Season {
   WINTER{
   public void printHours() { System. out .println("9am-3pm"); }
   }, SPRING{
   public void printHours() { System. out .println("9am-5pm"); }
   }, SUMMER{
   public void printHours() { System. out .println("9am-7pm"); }
   }, FALL{
   public void printHours() { System. out .println("9am-5pm"); }
   };
   public abstract void printHours();
} 
```

- If we don’t want each and every enum value to have a method, we can create a default implementation and override it only for the special cases:

```java
public enum Season3 {
   WINTER {
   public void printHours() { System.out.println("short hours"); }
   }, SUMMER {
   public void printHours() { System.out.println("long hours"); }
   }, SPRING, FALL;
   public void printHours() { System.out.println("default hours"); }
}
```

### Creating Nested Classes

A nested class is a class that is defined within another class. A nested class that is not
static is called an inner class. There are four of types of nested classes:

- A member inner class is a class defined at the same level as instance variables. It is not
  static. Often, this is just referred to as an inner class without explicitly saying the type.
- A local inner class is defined within a method.
- An anonymous inner class is a special case of a local inner class that does not have a
  name.
- A static nested class is a static class that is defined at the same level as static
  variables.

1. Member Inner Classes

A member inner class is defined at the member level of a class (the same level as the methods,
instance variables, and constructors). Member inner classes have the following properties:
- Can be declared public, private, or protected or use default access
- Can extend any class and implement interfaces
- Can be abstract or final
- Cannot declare static fields or methods
- Can access members of the outer class including private members

```java
public class Outer {
  private String greeting = "Hi";
  protected class Inner {
    public int repeat = 3;
    public void go() {
      for(int i = 0; i < repeat; i++) {
        System.out.println(greeting);
      }
    }
  }
  // member inner class
  public void callInner() {
    Inner inner = new Inner();
    inner.go();
  }

  public static void main(String[] args) {
    new Outer().callInner();
  }
}
```

The interface itself does not have to be public, though. Just like any inner class, an inner
interface can be private. This means that the interface can only be referred to within the
current outer class.

```java
public class CaseOfThePrivateInterface {
   private interface Secret {
   public void shh();
   }
   class DontTell implements Secret {
   public void shh() { }
   } 
}
```

2. Local Inner Classes

- A local inner class is a nested class defined within a method. Like local variables, a local
  inner class declaration does not exist until the method is invoked, and it goes out of scope
  when the method returns.
-  Local inner classes have the following properties:
    + They do not have an access specifier.
    + They cannot be declared static and cannot declare static fields or methods.
    + They have access to all fields and methods of the enclosing class.
    + They do not have access to local variables of a method unless those variables are final
      or effectively final. More on this shortly.

```java
public class Outer {
  private int length = 5;
  public void calculate() {
    final int width = 20; // local variable references are allowed if they are final or effectively final
    class Inner {
      public void multiply() {
        System.out.println(length * width);
      }
    }
    Inner inner = new Inner();
    inner.multiply();
  }
  public static void main(String[] args) {
    new Outer().calculate();
  }
}
```

3. Anonymous Inner Classes

- An anonymous inner class is a local inner class that does not have a name.
- It is declared and instantiated all in one statement using the new keyword.
- Anonymous inner classes are required to extend an existing class or implement an existing interface.
- They are useful when you have a short implementation that will not be used anywhere else.

```java
public class AnonInner {
  abstract class SaleTodayOnly {
    abstract int dollarsOff();
  }
  public int admission(int basePrice) {
    SaleTodayOnly saleTodayOnly = new SaleTodayOnly() {
      @Override
      int dollarsOff() {
        return 3;
      }
    }; //Pay special attention to the semicolon
    return basePrice - saleTodayOnly.dollarsOff();
  }
  public static void main(String[] args) {
    System.out.println(new AnonInner().admission(100));
  }
}
```

4. Static Nested Classes

- A static nested class is a static class defined at the member level.
- It can be instantiated without an object of the enclosing class, so it can’t access the instance variables without an explicit object of
  the enclosing class.
- In other words, it is like a regular class except for the following:
    + The nesting creates a namespace because the enclosing class name must be used to refer to it.
    + It can be made private or use one of the other access modifiers to encapsulate it.
    + The enclosing class can refer to the fields and methods of the static nested class.

```java
public class Enclosing {
  static class Nested {
    private int price = 6;
  }

  public static void main(String[] args) {
    Nested nested = new Nested();
    System.out.println(nested.price);
  }
}
```

- Importing a static Nested Class

```java
package bird;
public class Toucan {
     public static class Beak {}
}
package watcher;
import bird.Toucan.Beak; // regular import ok
public class BirdWatcher {
     Beak beak;
}

//And since it is static, alternatively you can use a static import:
import static bird.Toucan.Beak;
```

|                                                   | Member inner class                           | Local inner class                 | Anonymous inner class                            | static nested class                                            |
|---------------------------------------------------|----------------------------------------------|-----------------------------------|--------------------------------------------------|----------------------------------------------------------------|
| Access modifiers allowed                          | public, protected, private or default access | None. Already local to method     | None. Already local to statement                 | public, protected, private or default access                   |
| Can extend any class and any number of interfaces | Yes                                          | Yes                               | No-must have exactly one superclass or interface | Yes                                                            |
| Can be abstract                                   | Yes                                          | Yes                               | N/A-because no class defination                  | Yes                                                            |
| Can be final                                      | Yes                                          | Yes                               | N/A-because no class defination                  | Yes                                                            |
| Can access instance members of enclosing class    | Yes                                          | Yes                               | Yes                                              | No (not directly; requires an instance of the enclosing class) |
| Can access local variables of enclosing class     | No                                           | Yes-if final or effectively final | Yes-if final or effectively final                | No                                                             |
| Can declare static methods                        | No                                           | No                                | No                                               | Yes                                                            |

**Summary**

1. `Be able to identify the output of code using instanceof`:
    - `instanceof` checks if the left operand is the same class or interface (or a subclass) as the right operand.
    - If the left operand is null, the result is false.
    - If the two operands are not in the same class hierarchy, the code will not compile.
2. `Recognize correct and incorrect implementations of equals(), hashCode(), and toString()`:
    - public boolean equals(Object obj) returns false when called with null or a class of the wrong type.
    - public int hashCode() returns a number calculated with all or some of the instance variables used in equals().
    - public String toString() returns any String
3. `Be able to create enum classes. enums have a list of values`:
    - If that is all that is in the enum, the semicolon after the values is optional.
    - Enums can have instance variables, constructors, and methods.
    - The constructors are required to be private or package private.
    - Methods are allowed to be on the enum top level or in the individual enum values.
    - If the enum declares an abstract method, each enum value must implement it.
4. `Identify and use nested classes`:
    - A member inner class is instantiated with code such as outer.new Inner();.
    - Local inner classes are scoped to the end of the current block of code and not allowed to have static members.
    - Anonymous inner classes are limited to extending a class or implementing one interface.
    - A semicolon must end the statement creating an anonymous inner class.
    - Static nested classes cannot access the enclosing class instance variables.
5. `Know how to use imports and static imports`:
    - Classes can be imported by class name or wildcard.
    - Wildcards do not look at subdirectories.
    - In the event of a conflict, class name imports take precedence.
    - Static imports import static members.
    - They are written as import static, not static import.
    - Make sure that they are importing static methods or variables rather than class names.
6. `Understand the rules for method overriding and overloading`:
    - The Java compiler allows methods to be overridden in subclasses if certain rules are followed:
        + a method must have the same signature
        + be at least as accessible as the parent method
        + must not declare any new or broader exceptions
        + and must use covariant return types.
    - Methods are overloaded if they have the same method name but a different argument list.
    - An overridden method may use the optional @Override annotation.