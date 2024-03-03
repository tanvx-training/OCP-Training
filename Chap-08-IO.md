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

#### Byte Streams vs. Character Streams

a. The java.io API defines two sets of classes for reading and writing streams:
- Those with `Stream` in their name.
- Those with `Reader/Writer` in their name.

b. Differences between Streams and Readers/Writers

- The stream classes are used for inputting and outputting all types of binary or byte
data.
- The reader and writer classes are used for inputting and outputting only character and
String data.

#### Input and Output

- Most Input stream classes have a corresponding Output class and vice versa.
- It follows, then, that most Reader classes have a corresponding Writer class.
- There are exceptions to this rule. For the exam, you should know that PrintWriter has no accompanying 
PrintReader class. Likewise, the PrintStream class has no corresponding InputStream class.

#### Low-Level vs. High-Level Streams

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

#### Stream Base Classes

The java.io library defines four abstract classes that are the parents of all stream classes
defined within the API:
- InputStream
- OutputStream
- Reader
- Writer

#### Decoding Java I/O Class Names

**Review of java.io Class Properties**

- A class with the word InputStream or OutputStream in its name is used for reading or
writing binary data, respectively.
- A class with the word Reader or Writer in its name is used for reading or writing
character or string data, respectively.
- Most, but not all, input classes have a corresponding output class.
- A low-level stream connects directly with the source of the data.
- A high-level stream is built on top of another stream using wrapping.
- A class with Buffered in its name reads or writes data in groups of bytes or characters
and often improves performance in sequential file systems.

> When wrapping a stream you can mix and match only types that inherit from the same
> abstract parent stream.

**The java.io stream classes**

| Class Name          | Low/High Level | Description                                                                                                   |
|---------------------|----------------|---------------------------------------------------------------------------------------------------------------|
| InputStream         | N/A            | The abstract class all InputStream classes inherit from.                                                      |
| OutputStream        | N/A            | The abstract class all OutputStream classes inherit from.                                                     |
| Reader              | N/A            | The abstract class all Reader classes inherit from.                                                           |
| Writer              | N/A            | The abstract class all Writer classes inherit from.                                                           |
| FileInputStream     | Low            | Reads file data as bytes.                                                                                     |
| FileOutputStream    | Low            | Writes file data as bytes.                                                                                    |
| FileReader          | Low            | Reads file data as characters.                                                                                |
| FileWriter          | Low            | Writes file data as characters.                                                                               |
| BufferedReader      | High           | Reads character data from an existing Reader in a buffered manner, which improves efficiency and performance. |
| BufferedWriter      | High           | Writes character data to an existing Writer in a buffered manner, which improves efficiency and performance.  |
| ObjectInputStream   | High           | Deserializes primitive Java data types and graphs of Java objects from an existing InputStream.               |
| ObjectOutputStream  | High           | Serializes primitive Java data types and graphs of Java objects to an existing OutputStream.                  |
| InputStreamReader   | High           | Reads character data from an existing InputStream.                                                            |
| OutputStreamWriter  | High           | Writes character data to an existing OutputStream.                                                            |
| PrintStream         | High           | Writes formatted representations of Java objects to a binary stream.                                          |
| PrintWriter         | High           | Writes formatted representations of Java objects to a text-based output stream.                               |


#### Common Stream Operations

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

4. Skipping over Data: `skip(long)`

## Working with Streams

### The FileInputStream and FileOutputStream Classes

- They are used to read bytes from a file or write bytes to a file, respectively. 
- These classes include constructors that take a File object or String, representing a path to the file.
- The `FileInputStream` class also contains overloaded versions of the `read()` method,
which take a pointer to a byte array where the data is written. The method returns an
integer value indicating how many bytes can be read into the byte array. It is also used by
Buffered classes to improve performance.
- A `FileOutputStream` object is accessed by writing successive bytes using the `write(int)`
method. Like the FileInputStream class, the FileOutputStream also contains overloaded
versions of the `write()` method that allow a byte array to be passed and can be used by
Buffered classes. 

```java
import java.io.*;

public class CopyFileSample {

  public static void copy(File source, File destination) throws IOException {
    try (InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination)) {
      int b;
      while ((b = in.read()) != -1) {
        out.write(b);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    File source = new File("Zoo.class");
    File destination = new File("ZooCopy.class");
    copy(source, destination);
  }
}
```

### The BufferedInputStream and BufferedOutputStream Classes

- Instead of reading the data one byte at a time, we use the underlying read(byte[]) method
of BufferedInputStream, which returns the number of bytes read into the provided byte array.
- The number of bytes read is important for two reasons:
  + First, if the value returned is 0, then we know that we have reached the end of the file and can 
stop reading from the BufferedInputStream.
  + Second, the last read of the file will likely only partially fill the byte array, since it is 
unlikely for the file size to be an exact multiple of our buffer array size.

> Why Use the Buffered Classes?
> 
> Buffered classes in Java, such as `BufferedReader` and `BufferedWriter`, offer several advantages over their unbuffered counterparts:
> 
>1. **Efficiency**: Buffered classes use internal buffers to reduce the number of I/O operations, which can significantly improve performance, especially when dealing with large amounts of data. Instead of reading or writing one character or byte at a time, buffered classes read or write chunks of data at once, reducing the overhead associated with I/O operations.
>
>2. **Reduced System Calls**: Buffered classes minimize the number of system calls made to the underlying operating system, which can be expensive in terms of performance. By buffering data in memory and operating on it in larger chunks, buffered classes help reduce the overhead of system calls.
>
>3. **Convenience**: Buffered classes provide additional methods and functionalities that make common I/O operations easier to perform. For example, `BufferedReader` provides methods like `readLine()` for reading lines of text, while `BufferedWriter` offers methods like `newLine()` for writing platform-independent newline characters.
>
>4. **Support for Mark and Reset**: Buffered classes often support the `mark(int)` and `reset()` methods, allowing you to mark a position in the stream and later reset to that position. This feature is useful when you need to reread data from a certain point in the stream.
>
>5. **Integration with Other Streams**: Buffered classes can be easily layered on top of other input and output streams, allowing you to add buffering to any existing stream. This flexibility makes them versatile and widely applicable in various I/O scenarios.
>
>In summary, buffered classes provide improved performance, additional functionality, and greater flexibility compared to unbuffered I/O classes, making them a preferred choice for many I/O operations in Java applications.

```java
import java.io.*;

public class CopyBufferFileSample {

  public static void copy(File source, File destination) throws IOException {
    try (
            InputStream in = new BufferedInputStream(new FileInputStream(source));
            OutputStream out = new BufferedOutputStream(
                    new FileOutputStream(destination))) {
      byte[] buffer = new byte[1024];
      int lengthRead;
      while ((lengthRead = in.read(buffer)) > 0) {
        out.write(buffer, 0, lengthRead);
        out.flush();
      }
    }
  }
}
```

### The FileReader and FileWriter classes

- Like the `FileInputStream` and `FileOutputStream` classes, the `FileReader` and
`FileWriter` classes contain read() and write() methods, respectively.
- These methods read/write char values instead of byte values.

### The BufferedReader and BufferedWriter Classes

```java
import java.io.*;
import java.util.*;

public class CopyTextFileSample {

  public static List<String> readFile(File source) throws IOException {
    List<String> data = new ArrayList<String>();
    try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
      String s;
      while ((s = reader.readLine()) != null) {
        data.add(s);
      }
    }
    return data;
  }

  public static void writeFile(List<String> data, File destination) throws
          IOException {
    try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(destination))) {
      for (String s : data) {
        writer.write(s);
        writer.newLine();
      }
    }
  }

  public static void main(String[] args) throws IOException {
    File source = new File("Zoo.csv");
    File destination = new File("ZooCopy.csv");
    List<String> data = readFile(source);
    for (String record : data) {
      System.out.println(record);
    }
    writeFile(data, destination);
  }
}
```

**Some important difference:**

- First, in the `readFile()` method, we use a temporary String reference s to hold the value of the data,
stop reading the file when `readLine()` returns `null`.
- Next, instead of immediately copying the data we read from the file into the output file,
we store it in a List of String objects in the readFile() method. This allows us to both
display and modify the data, prior to writing it to disk later.
- Unlike the previous examples where we had to write the code one byte at a time or by using a byte
array, we can write the entire String in a single call. The `write(String)` method is quite convenient 
in practice. We then use the `writer.newLine()` method to insert a line break into the copied file, as our
`reader.readLine()` method split on line breaks.

###  The ObjectInputStream and ObjectOutputStream Classes

- The process of converting an in-memory object to a stored data format is referred to as `serialization`, 
with the reciprocal process of converting stored data into an object, which is known as `deserialization`

**The `Serializable` Interface**

- In order to serialize objects using the java.io API, the class they belong to must implement
the java.io.Serializable interface.
- Besides, `transient` instance variables, `static` class members will also be ignored during
the serialization and deserialization process.

```java
import java.io.Serializable;

public class Animal implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private int age;
  private char type;

  public Animal(String name, int age, char type) {
    this.name = name;
    this.age = age;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public char getType() {
    return type;
  }

  public String toString() {
    return "Animal [name=" + name + ", age=" + age + ", type=" + type + "]";
  }
}
```

- The serialization process uses the serialVersionUID to identify uniquely a version of the class. 
- That way, it knows whether the serialized data for an object will match the instance variable in 
the current version of the class. 
- If an older version of the class is encountered during deserialization, an exception may be thrown.

**Serializing and Deserializing Objects**

- The java.io API provides two stream classes for object `serialization` and `deserialization`
called `ObjectInputStream` and `ObjectOutputStream`.

```java
import java.io.*;
import java.util.*;

public class ObjectStreamSample {

  public static List<Animal> getAnimals(File dataFile) throws IOException,
          ClassNotFoundException {
    List<Animal> animals = new ArrayList<Animal>();
    try (ObjectInputStream in = new ObjectInputStream(
            new BufferedInputStream(new FileInputStream(dataFile)))) {
      while (in.available() > 0) {
        Object object = in.readObject();
        if (object instanceof Animal)
          animals.add((Animal) object);
      }
    } catch (EOFException e) {
      // File end reached
    }
    return animals;
  }

  public static void createAnimalsFile(List<Animal> animals, File dataFile)
          throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream(dataFile)))) {
      for (Animal animal : animals)
        out.writeObject(animal);
    }
  }

  public static void main(String[] args) throws IOException,
          ClassNotFoundException {
    List<Animal> animals = new ArrayList<Animal>();
    animals.add(new Animal("Tommy Tiger", 5, 'T'));
    animals.add(new Animal("Peter Penguin", 8, 'P'));
    File dataFile = new File("animal.data");
    createAnimalsFile(animals, dataFile);
    System.out.println(getAnimals(dataFile));
  }
}
```

**Understanding Object Creation**

- When you `deserialize` an object, the constructor of the serialized class is not called.
- In fact, Java calls the first no-arg constructor for the first non-serializable parent class, 
skipping the constructors of any serialized class in between. Furthermore, any static variables or
default initializations are ignored.

```java
public class Animal implements Serializable {

  private static final long serialVersionUID = 2L;
  private transient String name;
  private transient int age = 10;
  private static char type = 'C';

  {
    this.age = 14;
  }

  public Animal() {
    this.name = "Unknown";
    this.age = 12;
    this.type = 'Q';
  }

  public Animal(String name, int age, char type) {
    this.name = name;
    this.age = age;
    this.type = type;
  }
}
```

### The PrintStream and PrintWriter Classes

- The PrintStream and PrintWriter classes are high-level stream classes that write
formatted representation of Java objects to a text-based output stream.

#### print()

- Overloaded with all Java primitives as well as String and Object.
- In general, these methods perform `String.valueOf()` on the argument and call the underlying stream’s `write()` method.

```java
PrintWriter out = new PrintWriter("zoo.log");
out.print(5); // PrintWriter method out.write(String.valueOf(5)); // Writer method
out.print(2.0); // PrintWriter method out.write(String.valueOf(2.0)); // Writer method
Animal animal = new Animal();
out.print(animal); // PrintWriter method
out.write(animal==null ? "null": animal.toString()); // Writer method
```

#### println()

- Virtually identical to the print() methods, except that they insert a line break after the String value is written. 
- The classes also include a version of println() that takes no arguments, which terminates the current line by writing a line separator.

#### format() and printf()

- Takes a String, an optional locale, and a set of arguments, and it writes a formatted String to the stream based on the input. 
- Method signature:

```java
public PrintWriter format(String format, Object args...) 
public PrintWriter printf(String format, Object args...)
```

**Sample PrintWriter Application**

```java
import java.io.*;

public class PrintWriterSample {
  public static void main(String[] args) throws IOException {
    File source = new File("zoo.log");
    try (PrintWriter out = new PrintWriter(
      new BufferedWriter(new FileWriter(source)))) {
        out.print("Today's weather is: "); 
        out.println("Sunny");
        out.print("Today's temperature at the zoo is: "); 
        out.print(1/3.0);
        out.println('C');
        out.format("It has rained 10.12 inches this year"); 
        out.println();
        out.printf("It may rain 21.2 more inches this year");
    } 
  }
}
```

### Review of Stream Classes

- The classes on the left side of the diagram are the abstract parent classes. 
- The classes on the right side with dotted borders are low-level streams, and the ones with solid borders are high-level streams.

![Roadmap](java.io.classess.png)