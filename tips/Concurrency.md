1. **Deadlock**:
    - **Description**: Two or more threads are blocked forever, waiting for each other.
    - **Example**: Threads T1 and T2 each hold a lock and are waiting for the other's lock to proceed.
    - **Outcome**: Threads are unable to progress and remain in a blocked state indefinitely.

2. **Starvation**:
    - **Description**: A thread is unable to gain regular access to shared resources and is unable to make progress.
    - **Cause**: Shared resources are unavailable for long periods due to "greedy" threads hogging them.
    - **Example**: One thread frequently invoking a synchronized method, causing other threads to be frequently blocked.
    - **Outcome**: Thread remains blocked or delayed in accessing resources, impacting its ability to complete its tasks.

3. **Livelock**:
    - **Description**: Threads are not blocked but are too busy responding to each other to resume work, resulting in no progress.
    - **Cause**: Threads are in a constant state of mutual interaction or response, preventing either from making progress.
    - **Example**: Threads T1 and T2 continuously releasing and acquiring locks in response to each other's actions.
    - **Outcome**: Threads remain active but unable to perform meaningful work, stuck in a loop of interaction.

**General Logic of Using Fork/Join Framework:**

1. **Check Task Size**:
   - **Description**: Determine if the task is small enough to be executed directly without splitting.
   - **Action**:
      - If the task is small enough:
         - Perform it without further splitting.
      - Else:
         - Proceed to step 2 to split the task.

2. **Split Task**:
   - **Description**: Split the task into multiple smaller tasks, typically at least 2.
   - **Action**:
      - Divide the task into smaller subtasks.
      - Create subtasks representing these smaller portions of the original task.

3. **Submit Subtasks**:
   - **Description**: Submit the subtasks back to the fork/join pool for execution.
   - **Action**:
      - Use `invokeAll(list of tasks)` to submit the subtasks to the pool.
      - The fork/join framework manages the execution of these subtasks concurrently.

**Note**:
- The framework handles the splitting and joining of tasks, as well as the distribution of tasks among available threads in the pool.
- This approach leverages parallelism to improve the performance of tasks that can be broken down into smaller units of work.
