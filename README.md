## OCP Java 8 - Tips & Tricks

#### Chapter 1: Advanced Class Design

- Define static content inside non static inner class which is illegal.
- Can’t override Object class methods using default method.
- Enum implements Comparable via the natural order of the enum (the order in which the values are declared)


#### Chapter 2: Design Patterns and Principles

#### Chapter 3: Generics and Collections

- `default boolean remove(Object key, Object value)`: This removes the entry for the specified key only if it is currently mapped to the specified value.

#### Chapter 4: Functional Programming

- Function interface has following default method which returns a function that always returns its input argument.
`static Function identity()`

#### Chapter 5: Dates, Strings and Localization

#### Chapter 6: Exceptions and Assertions

You cannot include classes that are related by inheritance in the same multi-catch block.

#### Chapter 7: Concurrency
The exam needs you to understand and differentiate among Deadlock, Starvation, and Livelock. The following are brief descriptions taken from Oracle Java Tutorial:

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

ResultSetMetaData gives you the information about the result of executing a query. You can retrieve this object by calling getMetaData() on ResultSet.
ResultSetMetaData contains several methods that tell you about the ResultSet. Some important methods are:
getColumnCount(), getColumnName(int col), getColumnLabel(int col), and getColumnType(int col). Remember that the column index starts from 1

When creating connection URL for mysql we can follow format: 
```
jdbc:mysql://[host][:port]/[database][?property1][=value1]...
```