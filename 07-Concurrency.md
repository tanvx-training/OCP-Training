# Concurrency

## Introducing Threads

- A thread is the smallest unit of execution that can be scheduled by the operating system.
- A process is a group of associated threads that execute in the same, shared environment.
- By shared environment, we mean that the threads in the same process share the same memory space 
and can communicate directly with one another.

### Distinguishing Thread Types

- A system thread is created by the JVM and runs in the background of the application.
For example, the garbage-collection thread is a system thread that is created by the JVM
and runs in the background.
- A user-defined thread is one created by the application developer to accomplish a specific task.
- Both system and user-defined threads can be marked as daemon threads. 

### Understanding Thread Concurrency 

- The property of executing multiple threads and processes at the same time is referred to as concurrency.
- Operating systems use a thread scheduler to determine which threads should be currently executing.
- A context switch is the process of storing a thread’s current state and later restoring the state of the thread to continue execution.

**Java thread priority constants**

| Constant Variable     | Value |
|-----------------------|-------|
| Thread.MIN_PRIORITY   | 1     |
| Thread.NORM_PRIORITY  | 5     | // default
| Thread.MAX_PRIORITY   | 10    |


### Introducing Runnable

In Java, the `Runnable` interface is used to create a thread that can be executed concurrently with other threads. It's typically used to define the task or code that should be executed by a thread.

Here's the `Runnable` interface and its `run()` method signature:

```java
public interface Runnable {
    public abstract void run();
}
```

The `run()` method contains the code that defines the task to be performed by the thread. When a class implements the `Runnable` interface, it must provide an implementation for the `run()` method. This method will be executed when the thread is started.

### Creating a Thread

Two common ways to define the task for a `Thread` instance in Java:

1. **Provide a Runnable object or lambda expression to the Thread constructor:** This approach involves implementing the `Runnable` interface and passing an instance of the class implementing `Runnable` to the `Thread` constructor. Alternatively, you can use a lambda expression to represent the `Runnable` instance.

2. **Create a class that extends Thread and overrides the run() method:** In this approach, you create a new class that extends the `Thread` class and override its `run()` method. This method will contain the code that you want the thread to execute when it's started.

```java
public class Main {
    public static void main(String[] args) {
        // Approach 1: Using a Runnable object
        Runnable myRunnable = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hello from Runnable thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(myRunnable);
        thread1.start();

        // Approach 2: Extending the Thread class
        Thread thread2 = new Thread() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Hello from Extended Thread: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread2.start();
    }
}
```
| Criteria               | Using Runnable Object                                                       | Extending Thread Class                                                  |
|------------------------|-----------------------------------------------------------------------------|-------------------------------------------------------------------------|
| Separation of concerns | Promotes separation of task logic from threading mechanism, adhering to SRP | Tight coupling between task logic and threading mechanism               |
| Flexibility            | Allows class to extend another class                                        | Prevents class from extending any other class due to single inheritance |
| Reusability            | Same Runnable instance can be passed to multiple threads                    | Not applicable - each thread has its own task logic                     |
| Simplicity             | Requires creating a separate Runnable object                                | May be simpler for small, one-off tasks                                 |
| Familiarity            | May seem less intuitive for developers used to extending thread class       | More intuitive for developers accustomed to extending thread class      |

### Polling with Sleep

Thread.sleep() method requests the current thread of execution rest for a specified number of milliseconds. 

```java
public class CheckResults {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        // Creating a new thread that increments the counter
        new Thread(() -> {
            for(int i = 0; i < 500; i++)
                CheckResults.counter++;
        }).start();

        // Main thread checks if the counter has reached 100
        while(CheckResults.counter < 100) {
            System.out.println("Not reached yet");
            Thread.sleep(1000); // Sleep for 1 second
        }

        // Once the counter reaches 100, print "Reached!"
        System.out.println("Reached!");
    }
}
```

## Creating Threads with the ExecutorService

With the announcement of the Concurrency API, Java introduced the ExecutorService, which creates and manages threads for you. 

### Introducing the Single-Thread Executor

The `SingleThreadExecutor` is a type of `ExecutorService` in Java that manages a single worker thread for executing tasks sequentially. It's suitable for scenarios where you need tasks to be executed in a predictable order, one after the other, without the need for parallelism or concurrency.

Here's a brief overview of the `SingleThreadExecutor`:

1. **Creating a SingleThreadExecutor:**
   You can create a `SingleThreadExecutor` using the `Executors.newSingleThreadExecutor()` factory method.

   ```java
   ExecutorService executorService = Executors.newSingleThreadExecutor();
   ```

   This creates an `ExecutorService` with a single worker thread.

2. **Submitting tasks for execution:**
   Tasks can be submitted for execution using the `submit()` method, similar to other `ExecutorService` implementations.

   ```java
   executorService.submit(() -> {
       // Task logic goes here
       System.out.println("Task executed by thread: " + Thread.currentThread().getName());
   });
   ```

   Tasks are executed sequentially, one after the other, in the order they are submitted.

3. **Shutting down the SingleThreadExecutor:**
   After you're done using the `SingleThreadExecutor`, you should shut it down to release its resources properly.

   ```java
   executorService.shutdown();
   ```

   This initiates an orderly shutdown of the executor, allowing previously submitted tasks to execute before terminating the thread.

Here's a simple example demonstrating the usage of `SingleThreadExecutor`:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit tasks for execution
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                System.out.println("Task executed by thread: " + Thread.currentThread().getName());
            });
        }

        // Shutdown the SingleThreadExecutor
        executorService.shutdown();
    }
}
```

In this example, a `SingleThreadExecutor` is created, and 5 tasks are submitted for execution. The tasks will be executed sequentially by the single worker thread managed by the executor. Finally, the executor is shut down to release its resources.

### Shutting Down a Thread Executor

- Once you have finished using a thread executor, it is important that you call the shutdown() method. A thread executor creates a non-daemon thread on the first task that is executed, so failing to call shutdown() will result in your application never terminating.

- If a new task is submitted to the thread executor while it is shutting down, a RejectedExecutionException will be thrown. Once all active tasks have been completed, isShutdown() and isTerminated() will both return true.

![Roadmap](executor-shutdown.png)

Both `shutdown()` and `shutdownNow()` are methods provided by the `ExecutorService` interface in Java for shutting down the executor and releasing its resources. However, they differ in their behavior:

1. **shutdown():**
    - The `shutdown()` method initiates an orderly shutdown of the executor.
    - It allows previously submitted tasks to execute before terminating the executor.
    - After calling `shutdown()`, the executor will not accept new tasks for execution, but it will continue to execute any tasks that have already been submitted.
    - This method returns immediately without waiting for the previously submitted tasks to complete.

   ```java
   executorService.shutdown();
   ```

2. **shutdownNow():**
    - The `shutdownNow()` method attempts to stop all actively executing tasks and halts the processing of waiting tasks.
    - It interrupts the executor's worker threads and attempts to stop them immediately.
    - This method returns a list of tasks that were awaiting execution but were never started.
    - Tasks that have already begun execution may not be gracefully stopped and may continue running to completion, depending on the nature of the tasks.

   ```java
   List<Runnable> unfinishedTasks = executorService.shutdownNow();
   ```

Both `shutdown()` and `shutdownNow()` should be followed by a call to `awaitTermination()` if you want to wait for all tasks to complete before proceeding further.

Here's a simplified example demonstrating the usage of `shutdown()` and `shutdownNow()`:

```java
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks for execution
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                // Task logic goes here
                System.out.println("Task executed by thread: " + Thread.currentThread().getName());
            });
        }

        // Initiating shutdown of the executor
        executorService.shutdown();

        // Optional: Wait for all tasks to complete
        try {
            executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Alternatively, you can use shutdownNow() to forcefully terminate the executor
        // List<Runnable> unfinishedTasks = executorService.shutdownNow();
    }
}
```

In this example, the `shutdown()` method is used to initiate an orderly shutdown of the executor, allowing previously submitted tasks to complete. Optionally, you can use `shutdownNow()` to forcefully terminate the executor and obtain a list of tasks that were awaiting execution.

### Submitting Tasks

- The `execute()` method takes a Runnable lambda expression or instance and completes the task asynchronously.
- `submit()` returns a Future object that can be used to determine if the task is complete.

**ExecutorService method**

| Method Name                                                              | Description                                                                                                                                                           |
|--------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `void execute(Runnable command)`                                         | Executes a Runnable task at some point in the future.                                                                                                                 |
| `Future<?> submit(Runnable task)`                                        | Executes a Runnable task at some point in the future and returns a Future representing the task.                                                                      |
| `<T> Future<T> submit(Callable<T> task)`                                 | Executes a Callable task at some point in the future and returns a Future representing the pending results of the task.                                               |
| `<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)` | Executes the given tasks, synchronously returning the results of all tasks as a Collection of Future objects, in the same order they were in the original collection. |
| `<T> T invokeAny(Collection<? extends Callable<T>> tasks)`               | Executes the given tasks, synchronously returning the result of one of finished tasks, cancelling any unfinished tasks.                                               |

Here's an example demonstrating each of the methods you provided:

```java
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Method: execute(Runnable command)
        executor.execute(() -> {
            System.out.println("Task executed by thread: " + Thread.currentThread().getName());
        });

        // Method: submit(Runnable task)
        Future<?> futureRunnable = executor.submit(() -> {
            System.out.println("Runnable task executed by thread: " + Thread.currentThread().getName());
        });

        // Method: submit(Callable<T> task)
        Future<String> futureCallable = executor.submit(() -> {
            System.out.println("Callable task executed by thread: " + Thread.currentThread().getName());
            return "Callable Result";
        });

        // Method: invokeAll(Collection<? extends Callable<T>> tasks)
        Collection<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(() -> "Task 1");
        callableTasks.add(() -> "Task 2");
        List<Future<String>> futures = executor.invokeAll(callableTasks);
        for (Future<String> future : futures) {
            System.out.println("Result of invokeAll task: " + future.get());
        }

        // Method: invokeAny(Collection<? extends Callable<T>> tasks)
        Collection<Callable<Integer>> callableTasks2 = new ArrayList<>();
        callableTasks2.add(() -> {
            Thread.sleep(2000); // Simulating task 1 taking 2 seconds
            return 1;
        });
        callableTasks2.add(() -> {
            Thread.sleep(1000); // Simulating task 2 taking 1 second
            return 2;
        });
        Integer result = executor.invokeAny(callableTasks2);
        System.out.println("Result of invokeAny task: " + result);

        executor.shutdown();
    }
}
```

This example demonstrates each method as follows:

1. `execute(Runnable command)`: Submits a Runnable task for execution.
2. `submit(Runnable task)`: Submits a Runnable task for execution and returns a Future representing the task.
3. `submit(Callable<T> task)`: Submits a Callable task for execution and returns a Future representing the pending result of the task.
4. `invokeAll(Collection<? extends Callable<T>> tasks)`: Executes the given tasks synchronously, returning the results of all tasks as a Collection of Future objects.
5. `invokeAny(Collection<? extends Callable<T>> tasks)`: Executes the given tasks synchronously, returning the result of one of the finished tasks, cancelling any unfinished tasks.

**Submitting Task Collections**

- The `invokeAll()` method executes all tasks in a provided collection and returns a List of ordered Future objects, with one Future object corresponding to each submitted task, in the order they were in the original collection. 
- The `invokeAny()` method executes a collection of tasks and returns the result of one of the tasks that successfully completes execution, cancelling all unfinished tasks.
- Finally, the `invokeAll()` method will wait indefinitely until all tasks are complete, while the `invokeAny()` method will wait indefinitely until at least one task completes.

### Waiting for Results

**Future methods**

| Method Name                                     | Description                                                                                                                                                           |
|-------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `boolean isDone()`                              | Returns true if the task was completed, threw an exception, or was cancelled.                                                                                         |
| `boolean isCancelled()`                         | Returns true if the task was cancelled before it completely normally.                                                                                                  |
| `boolean cancel(boolean mayInterruptIfRunning)` | Attempts to cancel execution of the task.                                                                                                                           |
| `V get()`                                       | Retrieves the result of a task, waiting endlessly if it is not yet available.                                                                                         |
| `V get(long timeout, TimeUnit unit)`            | Retrieves the result of a task, waiting the specified amount of time. If the result is not ready by the time the timeout is reached, a checked TimeoutException will be thrown. |

Here's a Java code example demonstrating each of the methods you provided:

```java
import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submit a task that returns a result
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(2000); // Simulate some task taking time
            return 42;
        });

        // Method: isDone()
        System.out.println("Task done? " + future.isDone());

        // Method: isCancelled()
        System.out.println("Task cancelled? " + future.isCancelled());

        // Method: cancel()
        // Uncomment to test cancelling the task
        // future.cancel(true);

        // Method: get()
        // Retrieves the result of a task, waiting endlessly if it is not yet available.
        Integer result = future.get();
        System.out.println("Task result: " + result);

        // Method: get(long timeout, TimeUnit unit)
        // Retrieves the result of a task, waiting the specified amount of time.
        // If the result is not ready by the time the timeout is reached, a TimeoutException will be thrown.
        Future<Integer> futureWithTimeout = executor.submit(() -> {
            Thread.sleep(5000); // Simulate some task taking time
            return 84;
        });

        try {
            Integer resultWithTimeout = futureWithTimeout.get(3, TimeUnit.SECONDS);
            System.out.println("Task result with timeout: " + resultWithTimeout);
        } catch (TimeoutException e) {
            System.out.println("Task timed out!");
        }

        executor.shutdown();
    }
}
```

In this example:

1. We create an `ExecutorService` using a single-threaded executor.
2. Submit a task that returns a result (`Integer` in this case).
3. Demonstrate each of the methods:
   - `isDone()`: Checks if the task is done.
   - `isCancelled()`: Checks if the task is cancelled.
   - `cancel(boolean mayInterruptIfRunning)`: Attempts to cancel the task.
   - `get()`: Retrieves the result of the task, waiting indefinitely if necessary.
   - `get(long timeout, TimeUnit unit)`: Retrieves the result of the task, waiting the specified amount of time, throwing a `TimeoutException` if the timeout is reached.

**TimeUnit values**

| Enum Name                  | Description                                         |
|----------------------------|-----------------------------------------------------|
| `TimeUnit.NANOSECONDS`     | Time in one-billionth of a second (1/1,000,000,000) |
| `TimeUnit.MICROSECONDS`    | Time in one-millionth of a second (1/1,000,000)     |
| `TimeUnit.MILLISECONDS`    | Time in one-thousandth of a second (1/1,000)        |
| `TimeUnit.SECONDS`         | Time in seconds                                     |
| `TimeUnit.MINUTES`         | Time in minutes                                     |
| `TimeUnit.HOURS`           | Time in hours                                       |
| `TimeUnit.DAYS`            | Time in days                                        |

#### Introducing Callable

The `Callable` interface in Java is similar to `Runnable`, but it can return a result and throw a checked exception. It represents a task that can be executed asynchronously by a thread and is primarily used in conjunction with `ExecutorService` to submit tasks for execution.

Here's a brief overview of `Callable`:

1. **Definition:**
   - `Callable` is a functional interface introduced in Java 5 (along with `ExecutorService` and `Future`) in the `java.util.concurrent` package.
   - It declares a single method: `call()`, which returns a result of a specified type (`V`) and may throw an `Exception`.

2. **Usage:**
   - To use a `Callable`, you typically create a class that implements the `Callable` interface and provides the implementation for the `call()` method.

   ```java
   import java.util.concurrent.Callable;

   public class MyCallable implements Callable<String> {
       public String call() throws Exception {
           // Task logic goes here
           return "Result of the Callable";
       }
   }
   ```

3. **Submitting Callable to ExecutorService:**
   - You can submit a `Callable` task for execution by an `ExecutorService` using the `submit()` method.
   - The `submit()` method returns a `Future` object representing the pending result of the task.

   ```java
   ExecutorService executorService = Executors.newFixedThreadPool(5);
   Future<String> future = executorService.submit(new MyCallable());
   ```

4. **Getting the Result:**
   - You can obtain the result of a `Callable` task by calling the `get()` method on the `Future` object returned by `submit()`.
   - The `get()` method blocks until the result is available or throws an exception if the computation fails.

   ```java
   String result = future.get();
   ```

5. **Exception Handling:**
   - Since the `call()` method of `Callable` can throw a checked exception, you need to handle it either by catching it or by declaring it in the method signature.

   ```java
   public String call() throws Exception {
       // Task logic goes here
   }
   ```

The `Callable` interface provides a way to execute tasks asynchronously and obtain results, making it a valuable tool for concurrent programming in Java. It's commonly used in scenarios where tasks need to perform computations or operations that may take some time to complete.

#### Waiting for All Tasks to Finish

To wait for all tasks to finish in an `ExecutorService`, you can use the `awaitTermination()` method after shutting down the executor. This method blocks the current thread until all tasks have completed execution after a shutdown request, or until the specified timeout is reached.

Here's how you can wait for all tasks to finish:

```java
import java.util.concurrent.*;

public class WaitForAllTasksExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit tasks for execution
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                System.out.println("Task executed by thread: " + Thread.currentThread().getName());
            });
        }

        // Shutdown the ExecutorService
        executor.shutdown();

        try {
            // Wait for all tasks to complete
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Task interrupted");
        }

        System.out.println("All tasks have finished.");
    }
}
```

In this example:

1. We create an `ExecutorService` with a fixed thread pool of size 5.
2. We submit 10 tasks for execution using the `submit()` method.
3. After submitting all tasks, we shut down the executor using the `shutdown()` method.
4. We call `awaitTermination()` on the executor, specifying `Long.MAX_VALUE` as the timeout, which means waiting indefinitely until all tasks complete.
5. We catch `InterruptedException` in case the current thread is interrupted while waiting for termination.
6. Once all tasks have finished, we print a message indicating that all tasks have finished.

### Scheduling Tasks

To schedule tasks for execution at a specific time or after a certain delay, you can use the ScheduledExecutorService interface in Java. This interface extends the ExecutorService interface and provides additional methods for scheduling tasks.

You can create a ScheduledExecutorService using the factory method Executors.newScheduledThreadPool(int).

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
```

**ScheduledExecutorService methods**

| Method Name                                                                            | Description                                                                                                                                                                           |
|----------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `schedule(Callable<V> callable, long delay, TimeUnit unit)`                            | Creates and executes a `Callable` task after the given delay.                                                                                                                         |
| `schedule(Runnable command, long delay, TimeUnit unit)`                                | Creates and executes a `Runnable` task after the given delay.                                                                                                                         |
| `scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)` | Creates and executes a `Runnable` task after the given initial delay, creating a new task every period value that passes.                                                             |
| `scheduleAtFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)` | Creates and executes a `Runnable` task after the given initial delay and subsequently with the given delay between the termination of one execution and the commencement of the next. |

Here's a Java code example demonstrating the use of each scheduling method in the `ScheduledExecutorService`:

```java
import java.util.concurrent.*;

public class ScheduledTaskExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Method: schedule(Callable<V> callable, long delay, TimeUnit unit)
        // Creates and executes a Callable task after the given delay
        scheduler.schedule(() -> {
            System.out.println("Callable task executed after 2 seconds.");
            return "Callable Result";
        }, 2, TimeUnit.SECONDS);

        // Method: schedule(Runnable command, long delay, TimeUnit unit)
        // Creates and executes a Runnable task after the given delay
        scheduler.schedule(() -> {
            System.out.println("Runnable task executed after 3 seconds.");
        }, 3, TimeUnit.SECONDS);

        // Method: scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
        // Creates and executes a Runnable task after the given initial delay, creating a new task every period value that passes
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Task executed repeatedly with fixed rate, initial delay of 1 second, and period of 2 seconds.");
        }, 1, 2, TimeUnit.SECONDS);

        // Method: scheduleAtFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
        // Creates and executes a Runnable task after the given initial delay and subsequently with the given delay between the termination of one execution and the commencement of the next
        scheduler.scheduleAtFixedDelay(() -> {
            System.out.println("Task executed repeatedly with fixed delay, initial delay of 1 second, and subsequent delay of 2 seconds.");
        }, 1, 2, TimeUnit.SECONDS);

        // Sleep for some time to observe the scheduled tasks
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shut down the scheduler
        scheduler.shutdown();
    }
}
```

In this example:

1. We create a `ScheduledExecutorService` with a single thread.
2. We schedule tasks using each of the scheduling methods:
   - `schedule(Callable<V> callable, long delay, TimeUnit unit)`
   - `schedule(Runnable command, long delay, TimeUnit unit)`
   - `scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)`
   - `scheduleAtFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)`
3. We observe the scheduled tasks for a while.
4. Finally, we shut down the scheduler.

**ScheduledFuture<V>**

`ScheduledFuture<V>` is an interface in Java that represents the result of a scheduled task executed by a `ScheduledExecutorService`. It extends the `Future<V>` interface and provides additional methods for retrieving information about the scheduled task.

Here are some key points about `ScheduledFuture<V>`:

1. **Extended from `Future<V>`:**
   - Like `Future<V>`, `ScheduledFuture<V>` represents the result of an asynchronous computation.
   - It provides methods for checking if the computation is complete, retrieving the result, and handling exceptions.

2. **Additional Methods:**
   - `ScheduledFuture<V>` provides methods to retrieve information specific to scheduled tasks, such as the time remaining until the task is executed.

3. **Cancellation:**
   - Like `Future<V>`, `ScheduledFuture<V>` can be cancelled using the `cancel(boolean mayInterruptIfRunning)` method.
   - This allows you to cancel the execution of a scheduled task before it runs or after it has started, depending on the value of the `mayInterruptIfRunning` parameter.

4. **Time Information:**
   - `ScheduledFuture<V>` provides methods to retrieve information about the scheduled time of the task.
   - You can get the delay remaining before the task is executed using the `getDelay(TimeUnit unit)` method.

5. **Usage with `ScheduledExecutorService`:**
   - When you schedule a task using a `ScheduledExecutorService`, you receive a `ScheduledFuture<V>` object representing the pending result of the task.
   - You can use this object to interact with the scheduled task, such as cancelling it or retrieving information about its execution.

Here's a simplified example demonstrating the usage of `ScheduledFuture<V>` with a scheduled task:

```java
import java.util.concurrent.*;

public class ScheduledFutureExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule a task to execute after a delay of 2 seconds
        ScheduledFuture<?> future = scheduler.schedule(() -> {
            System.out.println("Task executed after 2 seconds.");
        }, 2, TimeUnit.SECONDS);

        // Check if the task is done
        if (future.isDone()) {
            System.out.println("Task is done.");
        }

        // Get the remaining delay until the task is executed
        long delay = future.getDelay(TimeUnit.SECONDS);
        System.out.println("Remaining delay: " + delay + " seconds");

        // Cancel the task
        future.cancel(false);

        // Shut down the scheduler
        scheduler.shutdown();
    }
}
```

In this example, we schedule a task using a `ScheduledExecutorService` and receive a `ScheduledFuture<?>` object representing the pending result of the task. We then interact with this object by checking if the task is done, retrieving the remaining delay until the task is executed, and cancelling the task. Finally, we shut down the scheduler.

### Increasing Concurrency with Pools

A thread pool is a group of pre-instantiated reusable threads that are available to perform a set of arbitrary tasks.

**Executors methods**

| Method Name                            | Return Type                | Description                                                                                                                                                                        |
|----------------------------------------|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `newSingleThreadExecutor()`            | `ExecutorService`          | Creates a single-threaded executor that uses a single worker thread operating off an unbounded queue. Results are processed sequentially in the order in which they are submitted. |
| `newSingleThreadScheduledExecutor()`   | `ScheduledExecutorService` | Creates a single-threaded executor that can schedule commands to run after a given delay or to execute periodically.                                                               |
| `newCachedThreadPool()`                | `ExecutorService`          | Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.                                                   |
| `newFixedThreadPool(int nThreads)`     | `ExecutorService`          | Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.                                                                                |
| `newScheduledThreadPool(int nThreads)` | `ScheduledExecutorService` | Creates a thread pool that can schedule commands to run after a given delay or to execute periodically.                                                                            |

Here's a Java code example demonstrating the use of each thread pool creation method:

```java
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Method: newSingleThreadExecutor()
        // Creates a single-threaded executor that uses a single worker thread operating off an unbounded queue
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            System.out.println("Task executed by singleThreadExecutor.");
        });

        // Method: newSingleThreadScheduledExecutor()
        // Creates a single-threaded executor that can schedule commands to run after a given delay or to execute periodically
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledExecutor.schedule(() -> {
            System.out.println("Task scheduled by singleThreadScheduledExecutor to run after 2 seconds.");
        }, 2, TimeUnit.SECONDS);

        // Method: newCachedThreadPool()
        // Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> {
            System.out.println("Task executed by cachedThreadPool.");
        });

        // Method: newFixedThreadPool(int nThreads)
        // Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        fixedThreadPool.execute(() -> {
            System.out.println("Task executed by fixedThreadPool.");
        });

        // Method: newScheduledThreadPool(int nThreads)
        // Creates a thread pool that can schedule commands to run after a given delay or to execute periodically
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.schedule(() -> {
            System.out.println("Task scheduled by scheduledThreadPool to run after 3 seconds.");
        }, 3, TimeUnit.SECONDS);

        // Shutdown all the thread pools
        singleThreadExecutor.shutdown();
        singleThreadScheduledExecutor.shutdown();
        cachedThreadPool.shutdown();
        fixedThreadPool.shutdown();
        scheduledThreadPool.shutdown();
    }
}
```

In this example:

1. We use each thread pool creation method to create different types of thread pools.
2. We execute tasks or schedule tasks using each thread pool accordingly.
3. Finally, we shut down all the thread pools to release their resources properly.

Using `Runtime.getRuntime().availableProcessors()` is a common approach to determine an appropriate pool size for a thread pool based on the number of available processors on the system. This method returns the number of processors available to the Java virtual machine.

Here's how you can use it to choose a pool size:

```java
import java.util.concurrent.*;

public class ThreadPoolWithAvailableProcessors {
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        // Create a thread pool with a size based on available processors
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);

        // Submit tasks for execution
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("Task executed by thread: " + Thread.currentThread().getName());
            });
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
```

In this example:

1. We use `Runtime.getRuntime().availableProcessors()` to get the number of available processors.
2. We create a fixed-size thread pool using `Executors.newFixedThreadPool()` with the number of available processors as the pool size.
3. We submit tasks for execution to the thread pool.
4. Finally, we shut down the executor to release its resources properly.

By using the number of available processors as the pool size, you can maximize the utilization of system resources while avoiding excessive thread creation, which can lead to resource contention and performance degradation.

## Synchronizing Data Access

### Protecting Data with Atomic Classes

- Atomic is the property of an operation to be carried out as a single unit of execution without any interference by another thread. 
- A thread-safe atomic version of the increment operator would be one that performed the read and write of the variable as a single operation, not allowing any other threads to access the variable during the operation.

**Atomic classes**

| Class Name             | Description                                                                       |
|------------------------|-----------------------------------------------------------------------------------|
| `AtomicBoolean`        | A boolean value that may be updated atomically                                    |
| `AtomicInteger`        | An int value that may be updated atomically                                       |
| `AtomicIntegerArray`   | An int array in which elements may be updated atomically                          |
| `AtomicLong`           | A long value that may be updated atomically                                       |
| `AtomicLongArray`      | A long array in which elements may be updated atomically                          |
| `AtomicReference`      | A generic object reference that may be updated atomically                         |
| `AtomicReferenceArray` | An array of generic object references in which elements may be updated atomically |

Here's a Java code example demonstrating the usage of some atomic classes:

```java
import java.util.concurrent.atomic.*;

public class AtomicClassesExample {
    public static void main(String[] args) {
        // AtomicBoolean example
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        System.out.println("AtomicBoolean value: " + atomicBoolean.get());

        // AtomicInteger example
        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println("AtomicInteger value: " + atomicInteger.get());

        // AtomicIntegerArray example
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(3);
        atomicIntegerArray.set(0, 100);
        atomicIntegerArray.set(1, 200);
        atomicIntegerArray.set(2, 300);
        System.out.println("AtomicIntegerArray values: " +
                atomicIntegerArray.get(0) + ", " +
                atomicIntegerArray.get(1) + ", " +
                atomicIntegerArray.get(2));

        // AtomicLong example
        AtomicLong atomicLong = new AtomicLong(1000L);
        System.out.println("AtomicLong value: " + atomicLong.get());

        // AtomicLongArray example
        AtomicLongArray atomicLongArray = new AtomicLongArray(2);
        atomicLongArray.set(0, 500L);
        atomicLongArray.set(1, 1000L);
        System.out.println("AtomicLongArray values: " +
                atomicLongArray.get(0) + ", " +
                atomicLongArray.get(1));

        // AtomicReference example
        AtomicReference<String> atomicReference = new AtomicReference<>("Hello");
        System.out.println("AtomicReference value: " + atomicReference.get());

        // AtomicReferenceArray example
        AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray<>(2);
        atomicReferenceArray.set(0, "One");
        atomicReferenceArray.set(1, "Two");
        System.out.println("AtomicReferenceArray values: " +
                atomicReferenceArray.get(0) + ", " +
                atomicReferenceArray.get(1));
    }
}
```

This example demonstrates the creation and usage of various atomic classes:

- `AtomicBoolean`: Demonstrates the creation of an `AtomicBoolean` object and retrieval of its value.
- `AtomicInteger`: Demonstrates the creation of an `AtomicInteger` object and retrieval of its value.
- `AtomicIntegerArray`: Demonstrates the creation of an `AtomicIntegerArray` object and setting/retrieval of its values.
- `AtomicLong`: Demonstrates the creation of an `AtomicLong` object and retrieval of its value.
- `AtomicLongArray`: Demonstrates the creation of an `AtomicLongArray` object and setting/retrieval of its values.
- `AtomicReference`: Demonstrates the creation of an `AtomicReference` object and retrieval of its value.
- `AtomicReferenceArray`: Demonstrates the creation of an `AtomicReferenceArray` object and setting/retrieval of its values.

These atomic classes provide atomic operations for various data types and are useful for synchronizing data access in multithreading environments.

**Common atomic methods**

| Class Name          | Description                                                                |
|---------------------|----------------------------------------------------------------------------|
| get()               | Retrieve the current value                                                 |
| set()               | Set the given value, equivalent to the assignment = operator               |
| getAndSet()         | Atomically sets the new value and returns the old value                    |
| incrementAndGet()   | For numeric classes, atomic pre-increment operation equivalent to ++value  |
| getAndIncrement()   | For numeric classes, atomic post-increment operation equivalent to value++ |
| decrementAndGet()   | For numeric classes, atomic pre-decrement operation equivalent to --value  |
| getAndDecrement()   | For numeric classes, atomic post-decrement operation equivalent to value-- |

Here's a Java code example demonstrating the usage of atomic methods:

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicMethodsExample {
    public static void main(String[] args) {
        // Create an AtomicInteger
        AtomicInteger atomicInt = new AtomicInteger(0);

        // get(): Retrieve the current value
        int currentValue = atomicInt.get();
        System.out.println("Current value: " + currentValue);

        // set(int newValue): Set the given value
        atomicInt.set(10);
        System.out.println("New value after set(): " + atomicInt.get());

        // getAndSet(int newValue): Atomically sets the new value and returns the old value
        int oldAndSet = atomicInt.getAndSet(20);
        System.out.println("Old value returned by getAndSet(): " + oldAndSet);
        System.out.println("Current value after getAndSet(): " + atomicInt.get());

        // incrementAndGet(): Atomic pre-increment operation
        int incrementedValue = atomicInt.incrementAndGet();
        System.out.println("Value after incrementAndGet(): " + incrementedValue);

        // getAndIncrement(): Atomic post-increment operation
        int oldIncrementedValue = atomicInt.getAndIncrement();
        System.out.println("Old value returned by getAndIncrement(): " + oldIncrementedValue);
        System.out.println("Current value after getAndIncrement(): " + atomicInt.get());

        // decrementAndGet(): Atomic pre-decrement operation
        int decrementedValue = atomicInt.decrementAndGet();
        System.out.println("Value after decrementAndGet(): " + decrementedValue);

        // getAndDecrement(): Atomic post-decrement operation
        int oldDecrementedValue = atomicInt.getAndDecrement();
        System.out.println("Old value returned by getAndDecrement(): " + oldDecrementedValue);
        System.out.println("Current value after getAndDecrement(): " + atomicInt.get());
    }
}
```

In this example:

- We create an `AtomicInteger` and perform various atomic operations on it.
- Each atomic method is demonstrated with its corresponding description:
   - `get()`: Retrieve the current value.
   - `set(int newValue)`: Set the given value.
   - `getAndSet(int newValue)`: Atomically sets the new value and returns the old value.
   - `incrementAndGet()`: Atomic pre-increment operation.
   - `getAndIncrement()`: Atomic post-increment operation.
   - `decrementAndGet()`: Atomic pre-decrement operation.
   - `getAndDecrement()`: Atomic post-decrement operation.

This example illustrates how atomic methods can be used to perform thread-safe operations on shared variables without the need for explicit synchronization.

### Improving Access with Synchronized Blocks

Synchronized blocks in Java provide a way to control access to critical sections of code, ensuring that only one thread can execute the synchronized block at a time. This helps prevent race conditions and maintains data consistency in multithreaded environments.

Here's how synchronized blocks work and how you can use them to improve access:

1. **Syntax**:
   ```java
   synchronized (object) {
       // Critical section of code
   }
   ```

2. **Working Principle**:
   - When a thread enters a synchronized block, it acquires the intrinsic lock (also known as the monitor lock) associated with the specified object.
   - Other threads attempting to enter synchronized blocks synchronized on the same object must wait until the lock is released by the current thread.
   - Once the critical section of code within the synchronized block is executed or an exception is thrown, the lock is released, allowing other waiting threads to acquire it.

3. **Improving Access**:
   - You can use synchronized blocks to ensure that only one thread at a time can access critical sections of your code where shared data is being modified.
   - This prevents concurrent access to shared resources, avoiding potential data corruption or inconsistent states.

4. **Example**:
   ```java
   public class SynchronizedExample {
       private int count = 0;

       public void increment() {
           synchronized (this) {
               count++;
           }
       }

       public int getCount() {
           synchronized (this) {
               return count;
           }
       }
   }
   ```
   - In this example, both the `increment()` and `getCount()` methods are synchronized on the same object (`this`), ensuring that only one thread can execute them at a time.
   - This guarantees that the `count` variable is accessed and modified atomically, preventing race conditions.

5. **Performance Considerations**:
   - While synchronized blocks ensure thread safety, they can introduce performance overhead due to thread contention.
   - It's essential to synchronize only the critical sections of code that modify shared data, keeping the synchronized blocks as short as possible to minimize the impact on performance.

By using synchronized blocks effectively, you can improve access to shared resources and maintain data integrity in multithreaded Java applications.

### Synchronizing Methods

Synchronizing methods in Java is another way to control access to critical sections of code, similar to synchronized blocks. When a method is declared as synchronized, only one thread can execute that method on a particular instance of the class at a time. This ensures that multiple threads cannot concurrently execute synchronized methods on the same instance, thereby preventing data corruption or inconsistent states.

Here's how you can synchronize methods and improve access:

1. **Syntax**:
   ```java
   public synchronized void methodName() {
       // Critical section of code
   }
   ```

2. **Working Principle**:
   - When a synchronized method is called, the intrinsic lock (monitor lock) associated with the object instance on which the method is invoked is acquired.
   - Other threads attempting to execute synchronized methods on the same object instance must wait until the lock is released.
   - Once the method execution completes or an exception is thrown, the lock is released, allowing other waiting threads to acquire it.

3. **Improving Access**:
   - By synchronizing methods, you can ensure that only one thread at a time can execute critical sections of code within those methods.
   - This prevents concurrent access to shared resources and helps maintain data consistency in multithreaded environments.

4. **Example**:
   ```java
   public class SynchronizedMethodExample {
       private int count = 0;

       public synchronized void increment() {
           count++;
       }

       public synchronized int getCount() {
           return count;
       }
   }
   ```
   - In this example, both the `increment()` and `getCount()` methods are declared as synchronized, ensuring that only one thread can execute them at a time on a particular instance of the class.
   - This guarantees atomic access to the `count` variable, preventing race conditions and ensuring data integrity.

5. **Performance Considerations**:
   - While synchronized methods provide thread safety, they may introduce performance overhead due to contention for the intrinsic lock.
   - It's important to synchronize only the critical methods that modify shared data, avoiding unnecessary synchronization on methods that don't require it.

By synchronizing methods effectively, you can improve access to shared resources and mitigate potential concurrency issues in your Java applications.

### Understanding the Cost of Synchronization

Understanding the cost of synchronization is crucial for designing efficient multithreaded Java applications. While synchronization ensures thread safety and prevents race conditions, it can introduce overhead that impacts performance. Here are some key considerations regarding the cost of synchronization:

1. **Locking Overhead**:
   - Synchronization typically involves acquiring and releasing locks, which incurs overhead.
   - Acquiring a lock requires the thread to enter a monitor state and possibly wait until the lock becomes available.
   - Releasing a lock involves notifying waiting threads and transitioning the thread out of the monitor state.

2. **Contention**:
   - Contention occurs when multiple threads compete for access to synchronized resources.
   - High contention can lead to threads spending significant time waiting for locks, reducing overall throughput and responsiveness.
   - Designing concurrency control mechanisms to minimize contention is essential for improving performance.

3. **Granularity**:
   - The granularity of synchronization impacts performance.
   - Fine-grained locking, where locks are acquired for small sections of code, can reduce contention but may increase overhead due to frequent lock acquisition and release.
   - Coarse-grained locking, where locks are acquired for larger sections of code, may reduce overhead but can increase contention if multiple threads need access to the same lock.

4. **Locking Mechanisms**:
   - Java provides various locking mechanisms, such as intrinsic locks (synchronized methods and blocks), explicit locks (ReentrantLock), and atomic variables.
   - Different locking mechanisms have different performance characteristics.
   - Choosing the appropriate locking mechanism based on the application's concurrency requirements and performance goals is essential.

5. **Locking Hierarchy**:
   - Nested locking or acquiring multiple locks in different order can lead to deadlock.
   - Deadlock occurs when two or more threads are blocked indefinitely, waiting for each other to release locks.
   - Careful design and analysis of locking hierarchies are necessary to avoid deadlock scenarios.

6. **Alternative Approaches**:
   - In some cases, avoiding synchronization altogether may be possible by using thread-local variables, immutable objects, or non-blocking algorithms.
   - Non-blocking algorithms, such as atomic variables and concurrent collections, provide thread safety without the need for locks and can improve scalability in highly concurrent scenarios.

7. **Performance Profiling**:
   - Performance profiling tools, such as profilers and thread profilers, can help identify synchronization bottlenecks and areas for optimization.
   - Profiling should be performed under realistic workload conditions to accurately assess the impact of synchronization on performance.

In summary, while synchronization is essential for ensuring thread safety in multithreaded Java applications, it's important to carefully consider its cost and optimize synchronization strategies to achieve the desired balance between concurrency and performance.

## Using Concurrent Collections

### Introducing Concurrent Collections


