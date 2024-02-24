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

*Putting It All Together

### Using Lists, Sets, Maps, and Queues

- A collection is a group of objects contained in a single object. 
- The Java Collections Framework is a set of classes in java.util for storing collections. 
- There are four main interfaces in the Java Collections Framework:
  + `List`: A list is an ordered collection of elements that allows duplicate entries. Elements
  in a list can be accessed by an int index.
  + `Set`: A set is a collection that does not allow duplicate entries.
  + `Queue`: A queue is a collection that orders its elements in a specific order for processing.
A typical queue processes its elements in a first-in, first-out order, but other orderings are possible.
  + `Map`: A map is a collection that maps keys to value, with no duplicate keys allowed.
The elements in a map are key/value pairs.

**Common Collections Methods**

| Method         | Method Signature                        | Description                                                      |
|----------------|-----------------------------------------|------------------------------------------------------------------|
| add()          | `boolean add(E element)`                | Adds the specified element to the collection (if not already present). |
| remove()       | `boolean remove(Object o)`              | Removes the specified element from the collection (if present).  |
| isEmpty()      | `boolean isEmpty()`                     | Returns true if the collection is empty, false otherwise.         |
| size()         | `int size()`                            | Returns the number of elements in the collection.                 |
| clear()        | `void clear()`                          | Removes all elements from the collection.                         |
| contains()     | `boolean contains(Object o)`            | Returns true if the collection contains the specified element, false otherwise. |

**Using the List Interface**

- You use a list when you want an ordered collection that can contain duplicate entries. 
- Items can be retrieved and inserted at specific positions in the list based on an `int index` much
like an array. 
- Lists are commonly used because there are many situations in programming
where you need to keep track of a list of objects.

*Comparing List Implementations*

>A LinkedList is special because it implements both List and Queue. It has all of the
methods of a List. It also has additional methods to facilitate adding or removing from the
beginning and/or end of the list.

|                     | ArrayList                                                    | LinkedList                                                  |
|---------------------|--------------------------------------------------------------|-------------------------------------------------------------|
| Main Benefit        | Look up any element in constant time.                        | Access, add, and remove from the beginning and end in constant time. |
| Adding/Removing     | Slower than accessing an element.                            | Adding or removing from the beginning or end is fast, but dealing with an arbitrary index takes linear time. |
| Suitable Use Case   | Reading more often than (or the same amount as) writing to the ArrayList. | Using as a Queue.                                          |

*Working with List Methods*

| Method Signature                  | Method Description                                     | Example                                      |
|-----------------------------------|--------------------------------------------------------|----------------------------------------------|
| `void add(E element)`             | Adds `element` to the end of the list.                    | `list.add("apple");`                        |
| `void add(int index, E element)`  | Adds `element` at the specified `index` and moves the rest toward the end. | `list.add(0, "banana");`                   |
| `E get(int index)`                | Returns the element at the specified `index`.             | `String fruit = list.get(0);`              |
| `int indexOf(Object o)`           | Returns the first matching index of `o` or -1 if not found. | `int index = list.indexOf("apple");`       |
| `int lastIndexOf(Object o)`       | Returns the last matching index of `o` or -1 if not found.  | `int lastIndex = list.lastIndexOf("apple");` |
| `void remove(int index)`          | Removes the element at the specified `index` and moves the rest toward the front. | `list.remove(0);`                          |
| `E set(int index, E e)`           | Replaces the element at the specified `index` with `e` and returns the original element. | `String replaced = list.set(0, "orange");` |

**Using the Set Interface**

- You use a set when you don’t want to allow duplicate entries.
- You aren’tconcerned with the order in which

*Comparing Set Implementations*

|                 | HashSet                                                | TreeSet                                              |
|-----------------|--------------------------------------------------------|------------------------------------------------------|
| Storage         | Uses a hash table to store elements.                   | Stores elements in a sorted tree structure.          |
| Main Benefit    | Adding elements and checking if an element is in the set both have constant time. | Set is always in sorted order.                       |
| Tradeoff        | Lose the order in which elements were inserted.        | Adding and checking if an element is present are both O(log n). |
| Common Usage    | Most common set implementation, suitable when order of elements doesn't matter. | Suitable when elements need to be kept in sorted order. |
| Special Feature | -                                                        | Implements NavigableSet interface, allowing slicing of the collection. |

*Working with Set Methods*

| Method Signature             | Description                                                 | Example                                   |
|------------------------------|-------------------------------------------------------------|-------------------------------------------|
| `boolean add(E e)`           | Adds the specified element to the set if it is not already present. | `set.add("apple");`                      |
| `boolean contains(Object o)` | Returns true if the set contains the specified element.     | `boolean containsApple = set.contains("apple");` |
| `boolean remove(Object o)`   | Removes the specified element from the set if it is present. | `boolean removed = set.remove("apple");` |
| `int size()`                 | Returns the number of elements in the set.                  | `int setSize = set.size();`              |
| `boolean isEmpty()`          | Returns true if the set contains no elements.               | `boolean empty = set.isEmpty();`         |
| `void clear()`               | Removes all of the elements from the set.                   | `set.clear();`                           |

`TreeSet` implements the NavigableSet interface. This interface provides some interesting methods. Their method signatures are as follows:

| Method          | Description                                                               |
|-----------------|---------------------------------------------------------------------------|
| `E lower(E e)`  | Returns the greatest element in this set strictly less than the given element `e`, or null if there is no such element. |
| `E floor(E e)`  | Returns the greatest element in this set less than or equal to the given element `e`, or null if there is no such element. |
| `E ceiling(E e)`| Returns the smallest element in this set greater than or equal to the given element `e`, or null if there is no such element. |
| `E higher(E e)` | Returns the smallest element in this set strictly greater than the given element `e`, or null if there is no such element. |

*Using the Queue Interface*

- You use a queue when elements are added and removed in a specific order. 
- Queues are typically used for sorting elements prior to processing them.

*Comparing Queue Implementations*

|                   | LinkedList                                             | ArrayDeque                                             |
|-------------------|--------------------------------------------------------|--------------------------------------------------------|
| Storage           | Uses a doubly linked list to store elements.          | Uses a dynamic array to store elements.                |
| Main Benefit      | Adding and removing elements at both ends have constant time complexity. | Adding and removing elements at both ends have constant time complexity. |
| Tradeoff          | Accessing elements by index takes linear time complexity. | Accessing elements by index takes linear time complexity. |
| Suitable Use Case | Suitable when you frequently need to add or remove elements from both ends of the list. | Suitable when you frequently need to add or remove elements from both ends of the deque. |
| Implementation    | Implemented as a linked list.                         | Implemented as a dynamic array.                       |

*Working with Queue Methods*

| Method                     | Description                                                                       |
|----------------------------|-----------------------------------------------------------------------------------|
| **Add Element**            |                                                                                   |
| `boolean add(E e)`         | Adds an element to the end of the queue, throws an exception if full.            |
| `boolean offer(E e)`       | Adds an element to the end of the queue, returns true if successful.             |
| **Remove Element**         |                                                                                   |
| `E remove()`               | Removes and returns the first element of the queue, throws an exception if empty. |
| `E poll()`                 | Removes and returns the first element of the queue, returns null if empty.        |
| **Access First Element**   |                                                                                   |
| `E element()`              | Returns the first element of the queue without removing it, throws an exception if empty. |
| `E peek()`                 | Returns the first element of the queue without removing it, returns null if empty. |
| **Queue and Stack Methods**|                                                                                   |
| `void push(E e)`           | Adds an element to the front of the queue.                                       |
| **Stack Methods**          |                                                                                   |
| `E pop()`                  | Removes and returns the first element of the stack, throws an exception if empty. |

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