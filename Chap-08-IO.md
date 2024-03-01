# IO

## Understanding Files and Directories

### Conceptualizing the File System

- A `file` is record within a file system that stores user and system data. Files are organized
using directories
- A `directory` is a record within a file system that contains files as well
as other directories.
- Finally, the root directory is the topmost directory in the file system, from
which all files and directories inherit. 
  + In Windows, it is denoted with a drive name such as `c:\ `
  + While on Linux it is denoted with a single forward slash `/.`
- A `path` is a String representation of a fi le or directory within a fi le system.

### Introducing the File Class

```java
java.io.File
```

The File class is used to read information about existing files and directories, list the contents 
of a directory, and create/delete files and directories.

**Creating a File Object**

- A File object often is initialized with String containing either an absolute or relative path
to the file or directory within the file system.
- The `absolute` path of a file or directory is the full path from the root directory to the file or 
directory, including all subdirectories that contain the file or directory.
> /home/smith/data/zoo.txt
- The `relative` path of a file or directory is the path from the current working directory to file 
or directory
> data/zoo.txt

*Example uses the absolute path:*

```java
File file = new File("/home/smith/data/zoo.txt");
```

*Example joins an existing File path with a relative child path:*

```java
File parent = new File("/home/smith");
File child = new File(parent, "data/zoo.txt");
```

**Working with a File Object**

| Method Name       | Description                                                                                                                                    |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| exists()          | Returns true if the file or directory exists.                                                                                                  |
| getName()         | Returns the name of the file or directory denoted by this path.                                                                                |
| getAbsolutePath() | Returns the absolute pathname string of this path.                                                                                             |
| isDirectory()     | Returns true if the file denoted by this path is a directory.                                                                                  |
| isFile()          | Returns true if the file denoted by this path is a file.                                                                                       |
| length()          | Returns the number of bytes in the file. For performance reasons, the file system may allocate more bytes on disk than the file actually uses. |
| lastModified()    | Returns the number of milliseconds since the epoch when the file was last modified.                                                            |
| delete()          | Deletes the file or directory. If this pathname denotes a directory, then the directory must be empty in order to be deleted.                  |
| renameTo(File)    | Renames the file denoted by this path.                                                                                                         |
| mkdir()           | Creates the directory named by this path.                                                                                                      |
| mkdirs()          | Creates the directory named by this path including any nonexistent parent directories.                                                         |
| getParent()       | Returns the abstract pathname of this abstract pathname’s parent or null if this pathname does not name a parent directory.                    |
| listFiles()       | Returns a File[] array denoting the files in the directory.                                                                                    |

## Introducing Streams 

I/O refers to the nature of how data is accessed, either by reading the data from a resource (input),
or writing the data to a resource (output).

### Stream Fundamentals

All Java Streams Use Byte

### Stream Nomenclature

**Byte Streams vs. Character Streams**

a. The java.io API defines two sets of classes for reading and writing streams:
- Those with `Stream` in their name.
- Those with `Reader/Writer` in their name.

b. Differences between Streams and Readers/Writers

- The stream classes are used for inputting and outputting all types of binary or byte
data.
- The reader and writer classes are used for inputting and outputting only character and
String data.

**Input and Output**

- Most Input stream classes have a corresponding Output class and vice versa.
- It follows, then, that most Reader classes have a corresponding Writer class.
- There are exceptions to this rule. For the exam, you should know that PrintWriter has no accompanying 
PrintReader class. Likewise, the PrintStream class has no corresponding InputStream class.

**Low-Level vs. High-Level Streams**

- A low-level stream connects directly with the source of the data, such as a file, an array,
or a String. 
- Alternatively, a high-level stream is built on top of another stream using wrapping. 

```java
try (BufferedReader bufferedReader = new BufferedReader(new FileReader("zoo-data.txt"))) {
   System.out.println(bufferedReader.readLine());
}
```

- High-level streams can take other high-level streams as input.

```java
try (ObjectInputStream objectStream = new ObjectInputStream(
                                         new BufferedInputStream(
                                            new FileInputStream("zoo-data.txt")))) {
   System.out.println(objectStream.readObject());
}
```

**Stream Base Classes**

The java.io library defines four abstract classes that are the parents of all stream classes
defined within the API:
- InputStream
- OutputStream
- Reader
- Writer

**Decoding Java I/O Class Names**

1. Review of java.io Class Properties

- A class with the word InputStream or OutputStream in its name is used for reading or
writing binary data, respectively.
- A class with the word Reader or Writer in its name is used for reading or writing
character or string data, respectively.
- Most, but not all, input classes have a corresponding output class.
- A low-level stream connects directly with the source of the data.
- A high-level stream is built on top of another stream using wrapping.
- A class with Buffered in its name reads or writes data in groups of bytes or characters
and often improves performance in sequential file systems.

>When wrapping a stream you can mix and match only types that inherit from the same
abstract parent stream.

**Common Stream Operations**

1. Closing the Stream: `close()`

Since streams are considered resources, it is imperative that they be closed after they
are used lest they lead to resource leaks.

2. Flushing the Stream: ` flush()`

- When data is written to an OutputStream, the underlying operating system does not necessarily 
guarantee that the data will make it to the file immediately. 
- In many operating systems, the data may be cached in memory, with a write occurring only after a 
temporary cache is filled or after some amount of time has passed.
- If the data is cached in memory and the application terminates unexpectedly, the data
would be lost, because it was never written to the file system.

3. Marking the Stream: `mark(int)` and `reset()`

- The InputStream and Reader classes include mark(int) and reset() methods to move
the stream back to an earlier position.
- Before calling either of these methods, you should call the markSupported() method, which returns 
true only if mark() is supported.