## OCP Java 8 - Tips & Tricks

#### Chapter 1: Advanced Class Design

- Define static content inside non static inner class which is illegal.
- Can’t override Object class methods using default method.
- Enum implements Comparable via the natural order of the enum (the order in which the values are declared)


#### Chapter 2: Design Patterns and Principles

#### Chapter 3: Generics and Collections

- `default boolean remove(Object key, Object value)`: This removes the entry for the specified key only if it is currently mapped to the specified value.

- Remember that HashMap supports adding null key as well as null values but ConcurrentHashMap does not. Inserting null key or null in a ConcurrentHashMap will throw a NullPointerException. Some candidates have reported getting a question on this aspect of ConcurrentHashMap.
- 
#### Chapter 4: Functional Programming

- Function interface has following default method which returns a function that always returns its input argument.
`static Function identity()`

#### Chapter 5: Dates, Strings and Localization

- A resource bundle file could be a properties file or a class file.

- The abstract class ResourceBundle has two subclasses: PropertyResourceBundle and ListResourceBundle.

- A PropertyResourceBundle is backed by a properties file. A properties file is a plain-text file that contains translatable text. Properties files are not part of the Java source code, and they can contain values for String objects only. If you need to store other types of objects, use a ListResourceBundle instead.

- The ListResourceBundle class manages resources with a convenient list. Each ListResourceBundle is backed by a class file. You can store any locale-specific object in a ListResourceBundle. To add support for an additional Locale, you create another source file and compile it into a class file.

- The ResourceBundle class is flexible. If you first put your locale-specific String objects in a PropertyResourceBundle and then later decided to use ListResourceBundle instead, there is no impact on your code. For example, the following call to getBundle will retrieve a ResourceBundle for the appropriate Locale, whether ButtonLabel is backed up by a class or by a properties file:

>ResourceBundle introLabels = ResourceBundle.getBundle("ButtonLabel", currentLocale);
#### Chapter 6: Exceptions and Assertions

- You cannot include classes that are related by inheritance in the same multi-catch block.

- When an assert statement has two expressions, the second expression must return a value.

- The main difference between AutoCloseable and Closeable is that AutoCloseable has Exception in the signature and Closeable has only IOException in the signature.

- Assertions can be enabled or disabled for specific packages or classes. To specify a class, use the class name. To specify a package, use the package name followed by "..." (three dots):
```
java -ea:<class> myPackage.MyProgram
java -da:<package>... myPackage.MyProgram
```

#### Chapter 7: Concurrency
- The exam needs you to understand and differentiate among Deadlock, Starvation, and Livelock. The following are brief descriptions taken from Oracle Java Tutorial:

1. Deadlock describes a situation where two or more threads are blocked forever, waiting for each other. For example, two threads T1 and T2 need a File and a Printer. T1 acquires the lock for the file and is about to acquire the lock for the Printer but before it could acquire the lock, T2 acquires the lock for the Printer and tries to acquire the lock for the file (which is already held by T1). So now, both the threads keep waiting for ever for each other to release their locks and neither will be able to proceed.

2. Starvation describes a situation where a thread is unable to gain regular access to shared resources and is unable to make progress. This happens when shared resources are made unavailable for long periods by "greedy" threads. For example, suppose an object provides a synchronized method that often takes a long time to return. If one thread invokes this method frequently, other threads that also need frequent synchronized access to the same object will often be blocked.

3. Livelock: A thread often acts in response to the action of another thread. If the other thread's action is also a response to the action of another thread, then livelock may result. As with deadlock, livelocked threads are unable to make further progress. However, the threads are not blocked — they are simply too busy responding to each other to resume work. For example, after acquiring the File lock, T1 tries to acquire the Printer lock. Finding the Printer lock to be already taken, it releases the lock for the File and notifies T2. At the same time, T2 tries to acquire the File lock and seeing that it is already taken it releases Printer lock and notifies T1. This process can go on and on, both the threads releasing and acquiring the locks in tandem but none of them getting both the locks at the same time. So neither of the threads is blocked but neither of the threads is able to do any real work. All they are doing is notifying each other.

Note: Most of the concrete classes that implement Executor, actually implement java.util.concurrent.ExecutorService interface. ExecutorService extends Executor.


#### Chapter 8: IO

- Java SE 8 has new features for reading files, following is such a method that can be used to read file.
`public static List readAllLines(Path path) throws IOException`
- 
#### Chapter 9: NIO.2

#### Chapter 10: JDBC 

- ResultSetMetaData gives you the information about the result of executing a query. You can retrieve this object by calling getMetaData() on ResultSet.

- ResultSetMetaData contains several methods that tell you about the ResultSet. Some important methods are:
getColumnCount(), getColumnName(int col), getColumnLabel(int col), and getColumnType(int col). Remember that the column index starts from 1

- When creating connection URL for mysql we can follow format: 
```
jdbc:mysql://[host][:port]/[database][?property1][=value1]...
```

- We can use both Class.forName() and DriverManager.registerDriver() method for registering the JDBC driver.