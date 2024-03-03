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
