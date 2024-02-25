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

> A LinkedList is special because it implements both List and Queue. It has all of the
> methods of a List. It also has additional methods to facilitate adding or removing from the
> beginning and/or end of the list.

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

|                   | LinkedList                                                                              | ArrayDeque                                                                               |
|-------------------|-----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| Storage           | Uses a doubly linked list to store elements.                                            | Uses a dynamic array to store elements.                                                  |
| Main Benefit      | Adding and removing elements at both ends have constant time complexity.                | Adding and removing elements at both ends have constant time complexity.                 |
| Tradeoff          | Accessing elements by index takes linear time complexity.                               | Accessing elements by index takes linear time complexity.                                |
| Suitable Use Case | Suitable when you frequently need to add or remove elements from both ends of the list. | Suitable when you frequently need to add or remove elements from both ends of the deque. |
| Implementation    | Implemented as a linked list.                                                           | Implemented as a dynamic array.                                                          |

*Working with Queue Methods*

| Method                     | Description                                                                               |
|----------------------------|-------------------------------------------------------------------------------------------|
| **Add Element**            |                                                                                           |
| `boolean add(E e)`         | Adds an element to the end of the queue, throws an exception if full.                     |
| `boolean offer(E e)`       | Adds an element to the end of the queue, returns true if successful.                      |
| **Remove Element**         |                                                                                           |
| `E remove()`               | Removes and returns the first element of the queue, throws an exception if empty.         |
| `E poll()`                 | Removes and returns the first element of the queue, returns null if empty.                |
| **Access First Element**   |                                                                                           |
| `E element()`              | Returns the first element of the queue without removing it, throws an exception if empty. |
| `E peek()`                 | Returns the first element of the queue without removing it, returns null if empty.        |
| **Queue and Stack Methods**|                                                                                           |
| `void push(E e)`           | Adds an element to the front of the queue.                                                |
| **Stack Methods**          |                                                                                           |
| `E pop()`                  | Removes and returns the first element of the stack, throws an exception if empty.         |

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

**Map**
- You use a map when you want to identify values by a key.

*Comparing Map Implementations*

|                   | HashMap                                                                            | TreeMap                                                                                                    |
|-------------------|------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| Data Structure    | Uses a hash table to store key-value pairs.                                        | Uses a Red-Black tree to store key-value pairs.                                                            |
| Main Benefit      | Offers constant-time performance for basic operations, such as get() and put().    | Maintains keys in sorted order, allowing efficient operations for range views and nearest neighbor search. |
| Order of Elements | Does not maintain any order of its keys.                                           | Maintains keys in sorted (ascending) order.                                                                |
| Performance       | Generally faster for basic operations (get, put, containsKey).                     | Slower for basic operations due to the overhead of maintaining the tree structure.                         |
| Usage             | Suitable for general-purpose use, especially when order is not important.          | Suitable when keys need to be maintained in sorted order or when range queries are needed.                 |

*Working with Map Methods*

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

**Comparing Collection Types**

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