**HashMap vs ConcurrentHashMap:**

- **HashMap**:
    - Supports adding null keys as well as null values.
    - Null keys and null values are permissible.

- **ConcurrentHashMap**:
    - Does not support null keys or null values.
    - Attempting to insert a null key or null value will result in a NullPointerException.

**Important Note**:
Some candidates have reported encountering questions specifically about this aspect of ConcurrentHashMap in assessments or interviews. It's crucial to remember that ConcurrentHashMap doesn't allow null keys or null values, unlike HashMap.

**Deque Interface Overview:**

1. **Deque as Queue**:
  - FIFO structure (First In First Out)
  - Methods:
    - `offer(e)` / `add(e)`: Add element to the end (similar)
    - `poll()` / `remove()`: Remove element from the front (similar)
  - Other methods: `peek()`, `peekFirst()`

2. **Deque as Stack**:
  - LIFO structure (Last In First Out)
  - Methods:
    - `push(e)`: Add element to the front
    - `pop()`: Remove element from the front

**Additional Methods**:
- **Queue Behavior**:
  - `pollFirst()` / `pollLast()`: Remove from front / end
  - `removeFirst()` / `removeLast()`: Remove from front / end (throw exception if empty)
  - `offerFirst(e)` / `offerLast(e)`: Add to front / end
  - `addFirst(e)` / `addLast(e)`: Add to front / end
- **Retrieving Without Removing**:
  - `peekLast()`: Retrieve last element without removing
  - `element()`: Retrieve head of queue (throw exception if empty)

**Important Note**:
- No `pushFirst(e)` or `pushLast(e)` methods.
- Difference between `offer(e)` and `add(e)`: `add(e)` throws exception for capacity lack.

Understanding these methods is crucial for utilizing Deque effectively in various scenarios. Let me know if you need further clarification!

**Storing Key-Value Pairs in a Map:**

1. **Computing Hashcode**:
  - Hashcode of the key is computed to identify the bucket for storage.

2. **Storing Key-Value Pairs**:
  - Key-value pair is stored in the identified bucket, wrapped in a Map.Entry object.
  - If multiple keys have the same hashcode, they're stored in the same bucket. Comparison is done using the `equals` method.

**Lookup Process in a Map:**

1. **Computing Hashcode**:
  - Hashcode of the key is computed to identify the bucket to search.

2. **Searching in Bucket**:
  - For all key-value pairs in the bucket, comparison is done with the supplied key using the `equals` method.
  - If a match is found, the corresponding value is returned.

**Importance of Consistent Hashcode**:
- `hashCode()` must return the same value for two objects that are equal according to `equals()`.
- Ensures proper retrieval of values from the map using equivalent keys.

**Example**:
- Inconsistent hashcode behavior can lead to failure in retrieving values even if keys are equal.
- If different hashcodes are returned for equal objects, the map looks in different buckets, resulting in unsuccessful lookup.

**Option 3**: Returning the same hashcode for all objects:
- All key-value pairs are stored in the same bucket.
- A match can be found, although it requires calling `equals()` on all objects.