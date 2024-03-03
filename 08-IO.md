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

```java
import java.io.File;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        // Using different constructors to create File objects
        File file1 = new File("/path/to/file.txt");

        URI uri = URI.create("file:///path/to/anotherfile.txt");
        File file2 = new File(uri);

        File parentDirectory = new File("/parent/directory");
        File file3 = new File(parentDirectory, "childfile.txt");

        // Displaying information about the File objects
        System.out.println("File 1 path: " + file1.getAbsolutePath());
        System.out.println("File 1 exists: " + file1.exists());

        System.out.println("File 2 path: " + file2.getAbsolutePath());
        System.out.println("File 2 exists: " + file2.exists());

        System.out.println("File 3 path: " + file3.getAbsolutePath());
        System.out.println("File 3 exists: " + file3.exists());
    }
}
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

The `FileInputStream` and `FileOutputStream` classes in Java are part of the `java.io` package and are used for reading from and writing to files, respectively. Here's an introduction to these classes:

1. **FileInputStream**:
  - The `FileInputStream` class is used to read bytes from a file in the file system.
  - It is a subclass of the `InputStream` class, which is an abstract class representing input streams of bytes.
  - `FileInputStream` takes the path of the file to be read as its constructor argument.
  - It provides various methods for reading bytes from the file, such as `read()`, `read(byte[])`, and `skip()`.

Example of using `FileInputStream`:
```java
import java.io.*;

public class ReadFileExample {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("example.txt");
            int data;
            while ((data = inputStream.read()) != -1) {
                System.out.print((char) data);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. **FileOutputStream**:
  - The `FileOutputStream` class is used to write bytes to a file in the file system.
  - It is a subclass of the `OutputStream` class, which is an abstract class representing output streams of bytes.
  - `FileOutputStream` takes the path of the file to be written as its constructor argument.
  - It provides various methods for writing bytes to the file, such as `write(int)`, `write(byte[])`, and `flush()`.

Example of using `FileOutputStream`:
```java
import java.io.*;

public class WriteFileExample {
    public static void main(String[] args) {
        try {
            FileOutputStream outputStream = new FileOutputStream("example.txt");
            String data = "Hello, World!";
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Both `FileInputStream` and `FileOutputStream` are low-level I/O classes and are often used together with higher-level classes such as `BufferedInputStream` and `BufferedOutputStream` for improved performance and functionality. Additionally, they should be properly closed using the `close()` method or in a try-with-resources block to release system resources after use.

### The BufferedInputStream and BufferedOutputStream Classes

The `BufferedInputStream` and `BufferedOutputStream` classes in Java are used to improve the performance of input and output operations by buffering the data read from or written to a file or other input/output streams. Here's an introduction to these classes:

1. **BufferedInputStream**:
  - The `BufferedInputStream` class is a subclass of the `FilterInputStream` class.
  - It adds buffering functionality to an input stream, which reduces the number of system calls made for reading data from the underlying input stream.
  - `BufferedInputStream` takes another input stream (e.g., `FileInputStream`) as its constructor argument.
  - It provides methods for reading bytes from the buffered input stream, such as `read()`, `read(byte[])`, and `skip()`.

Example of using `BufferedInputStream`:
```java
import java.io.*;

public class BufferedInputStreamExample {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("example.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                System.out.print((char) data);
            }

            bufferedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. **BufferedOutputStream**:
  - The `BufferedOutputStream` class is a subclass of the `FilterOutputStream` class.
  - It adds buffering functionality to an output stream, which reduces the number of system calls made for writing data to the underlying output stream.
  - `BufferedOutputStream` takes another output stream (e.g., `FileOutputStream`) as its constructor argument.
  - It provides methods for writing bytes to the buffered output stream, such as `write(int)`, `write(byte[])`, and `flush()`.

Example of using `BufferedOutputStream`:
```java
import java.io.*;

public class BufferedOutputStreamExample {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("example.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            String data = "Hello, World!";
            bufferedOutputStream.write(data.getBytes());
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Both `BufferedInputStream` and `BufferedOutputStream` are often used to wrap other input and output streams, such as `FileInputStream` and `FileOutputStream`, to improve their performance by reducing the number of system calls. They should be properly closed after use to release system resources.

### The FileReader and FileWriter classes

The `FileReader` and `FileWriter` classes in Java are used to read characters from and write characters to files, respectively. Here's an introduction to these classes:

1. **FileReader**:
  - The `FileReader` class is used to read character data from a file in the file system.
  - It is a subclass of the `InputStreamReader` class and is specifically designed for reading character-based data.
  - `FileReader` takes the path of the file to be read as its constructor argument.
  - It provides methods for reading characters from the file, such as `read()`, `read(char[])`, and `skip()`.

Example of using `FileReader`:
```java
import java.io.*;

public class FileReaderExample {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("example.txt");

            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. **FileWriter**:
  - The `FileWriter` class is used to write character data to a file in the file system.
  - It is a subclass of the `OutputStreamWriter` class and is specifically designed for writing character-based data.
  - `FileWriter` takes the path of the file to be written as its constructor argument.
  - It provides methods for writing characters to the file, such as `write(int)`, `write(char[])`, and `flush()`.

Example of using `FileWriter`:
```java
import java.io.*;

public class FileWriterExample {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("example.txt");

            String data = "Hello, World!";
            fileWriter.write(data);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Both `FileReader` and `FileWriter` are commonly used for reading and writing text files. They should be properly closed after use to release system resources. Additionally, you can use them together with other higher-level classes, such as `BufferedReader` and `BufferedWriter`, for improved performance and functionality.

### The BufferedReader and BufferedWriter Classes

The `BufferedReader` and `BufferedWriter` classes in Java are used to improve the performance of reading characters from and writing characters to files, respectively. They provide buffering functionality, which reduces the number of system calls made for reading from or writing to the underlying file or input/output streams. Here's an introduction to these classes:

1. **BufferedReader**:
  - The `BufferedReader` class is used to read characters from a character-input stream with efficiency by using a buffer to reduce the number of I/O operations.
  - It is a subclass of the `Reader` class and is typically used to wrap other character input streams, such as `FileReader` or `InputStreamReader`.
  - `BufferedReader` provides methods for reading characters from the input stream, such as `readLine()`, `read()`, and `skip()`.

Example of using `BufferedReader`:
```java
import java.io.*;

public class BufferedReaderExample {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("example.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. **BufferedWriter**:
  - The `BufferedWriter` class is used to write characters to a character-output stream with efficiency by using a buffer to reduce the number of I/O operations.
  - It is a subclass of the `Writer` class and is typically used to wrap other character output streams, such as `FileWriter` or `OutputStreamWriter`.
  - `BufferedWriter` provides methods for writing characters to the output stream, such as `write(String)`, `newLine()`, and `flush()`.

Example of using `BufferedWriter`:
```java
import java.io.*;

public class BufferedWriterExample {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("example.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String data = "Hello, World!";
            bufferedWriter.write(data);

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Both `BufferedReader` and `BufferedWriter` are commonly used for reading and writing text files. They should be properly closed after use to release system resources. Additionally, they can be used together with other higher-level classes, such as `FileReader` and `FileWriter`, for improved performance and functionality.

###  The ObjectInputStream and ObjectOutputStream Classes

The `ObjectInputStream` and `ObjectOutputStream` classes in Java are used to read and write objects from and to streams, respectively. They are part of the `java.io` package and are used for serialization and deserialization of objects. Here's an introduction to these classes:

1. **ObjectInputStream**:
  - The `ObjectInputStream` class is used to deserialize objects from an input stream.
  - It is a subclass of the `InputStream` class and is specifically designed for reading serialized objects.
  - `ObjectInputStream` takes an input stream (e.g., `FileInputStream`) as its constructor argument.
  - It provides methods for reading objects from the input stream, such as `readObject()`.

Example of using `ObjectInputStream`:
```java
import java.io.*;

public class ObjectInputStreamExample {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("objects.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Object obj = objectInputStream.readObject();
            System.out.println(obj);

            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

2. **ObjectOutputStream**:
  - The `ObjectOutputStream` class is used to serialize objects and write them to an output stream.
  - It is a subclass of the `OutputStream` class and is specifically designed for writing serialized objects.
  - `ObjectOutputStream` takes an output stream (e.g., `FileOutputStream`) as its constructor argument.
  - It provides methods for writing objects to the output stream, such as `writeObject()`.

Example of using `ObjectOutputStream`:
```java
import java.io.*;

public class ObjectOutputStreamExample {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("objects.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            Object obj = "Hello, World!";
            objectOutputStream.writeObject(obj);

            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Both `ObjectInputStream` and `ObjectOutputStream` are commonly used for reading and writing serialized objects. They can serialize and deserialize objects of classes that implement the `Serializable` interface. It's important to properly handle exceptions and close the streams after use to release system resources.

**Serializing and Deserializing Objects**

Serialization is the process of converting an object into a stream of bytes, allowing the object to be saved to a file, sent over a network, or stored in a database. Deserialization is the reverse process of converting a stream of bytes back into an object. In Java, you can serialize and deserialize objects using the `ObjectOutputStream` and `ObjectInputStream` classes, respectively. Here's an example of serializing and deserializing objects:

1. **Serializing Objects**:
```java
import java.io.*;

public class SerializationExample {
    public static void main(String[] args) {
        try {
            // Create an object to serialize
            Student student = new Student("John", 20);

            // Create an ObjectOutputStream to write the object to a file
            FileOutputStream fileOutputStream = new FileOutputStream("student.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write the object to the file
            objectOutputStream.writeObject(student);

            // Close the streams
            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. **Deserializing Objects**:
```java
import java.io.*;

public class DeserializationExample {
    public static void main(String[] args) {
        try {
            // Create an ObjectInputStream to read the object from the file
            FileInputStream fileInputStream = new FileInputStream("student.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Read the object from the file
            Student student = (Student) objectInputStream.readObject();

            // Close the streams
            objectInputStream.close();
            fileInputStream.close();

            // Print the deserialized object
            System.out.println("Deserialized Student:");
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

3. **Student Class**:
```java
import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

In this example, we have a `Student` class that implements the `Serializable` interface. We serialize an instance of the `Student` class to a file using `ObjectOutputStream` in the `SerializationExample` class. Then, we deserialize the object back into an instance of the `Student` class using `ObjectInputStream` in the `DeserializationExample` class. Finally, we print the deserialized student's information.

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

The `PrintStream` and `PrintWriter` classes in Java are used for writing formatted data to various output streams. They provide convenient methods for writing different types of data, such as primitive types, strings, and objects, to output streams like files, standard output (console), or network sockets. Here's an introduction to these classes:

1. **PrintStream**:
  - The `PrintStream` class provides methods to print formatted representations of objects to an output stream.
  - It is a subclass of the `OutputStream` class and is typically used to write to standard output (`System.out`) or files.
  - `PrintStream` provides methods like `print()` and `println()` to write various types of data, including primitive types, strings, and objects, to the output stream.

Example of using `PrintStream`:
```java
import java.io.*;

public class PrintStreamExample {
    public static void main(String[] args) {
        try {
            PrintStream printStream = new PrintStream("output.txt");

            // Writing to the output stream
            printStream.println("Hello, World!");
            printStream.printf("PI: %.2f\n", Math.PI);

            // Closing the stream
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. **PrintWriter**:
  - The `PrintWriter` class provides methods to print formatted representations of objects to a text-output stream.
  - It is a subclass of the `Writer` class and is typically used to write to files, network sockets, or other character-based output streams.
  - `PrintWriter` provides methods like `print()` and `println()` similar to `PrintStream` for writing various types of data to the output stream.

Example of using `PrintWriter`:
```java
import java.io.*;

public class PrintWriterExample {
    public static void main(String[] args) {
        try {
            PrintWriter printWriter = new PrintWriter("output.txt");

            // Writing to the output stream
            printWriter.println("Hello, World!");
            printWriter.printf("PI: %.2f\n", Math.PI);

            // Closing the stream
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Both `PrintStream` and `PrintWriter` provide methods to write formatted data to output streams. They handle various types of data and automatically convert them into their string representations. Additionally, they manage the output stream's buffering and character encoding.

### Review of Stream Classes

- The classes on the left side of the diagram are the abstract parent classes. 
- The classes on the right side with dotted borders are low-level streams, and the ones with solid borders are high-level streams.

![Roadmap](java.io.classess.png)

**Other Stream Classes**

## Interacting with Users

### The Old Way

If you prefer to use `BufferedReader` instead of `Console` for reading input from the console, you can do so as well. Here's how you can achieve that:

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputExample {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter your name: ");
            String name = reader.readLine();

            System.out.print("Enter your password: ");
            String password = reader.readLine();

            System.out.println("Name: " + name);
            System.out.println("Password: " + password);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

In this example, we use `BufferedReader` to read input from the console. We create a `BufferedReader` object by wrapping the standard input stream (`System.in`) with an `InputStreamReader`. Then, we read lines of text from the console using the `readLine()` method of `BufferedReader`. Finally, we print out the input received from the user. Remember to handle any potential `IOException` that might occur during input reading.

### The New Way

The `Console` class in Java provides methods for interacting with the console, including reading input from the console and writing output to the console. It's primarily used when the Java application is run in a console environment, such as a command-line interface. Here's an overview of the `Console` class:

1. **Reading Input from Console**:
  - The `Console` class provides methods like `readLine()` and `readPassword()` for reading input from the console.
  - `readLine()` reads a line of text entered by the user.
  - `readPassword()` reads a password entered by the user without echoing characters to the console.

Example of reading input using `Console`:
```java
import java.io.Console;

public class ConsoleInputExample {
    public static void main(String[] args) {
        Console console = System.console();

        if (console != null) {
            String input = console.readLine("Enter your name: ");
            char[] password = console.readPassword("Enter your password: ");

            System.out.println("Name: " + input);
            System.out.println("Password: " + new String(password));
        } else {
            System.out.println("Console not available.");
        }
    }
}
```

2. **Writing Output to Console**:
  - The `Console` class also provides methods like `printf()` and `format()` for writing formatted output to the console.
  - `printf()` and `format()` are similar to `System.out.printf()` for formatted printing.

Example of writing output using `Console`:
```java
import java.io.Console;

public class ConsoleOutputExample {
    public static void main(String[] args) {
        Console console = System.console();

        if (console != null) {
            console.printf("Hello, %s!%n", "World");
            console.format("PI: %.2f%n", Math.PI);
        } else {
            System.out.println("Console not available.");
        }
    }
}
```

The `Console` class provides a convenient way to interact with the console when running Java applications in a console environment. However, it's important to note that the availability of the `Console` object depends on the underlying platform and how the Java application is executed.

**Console methods**

| Method           | Description                                                                                                                                                                      |
|------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `reader()`       | Returns a Reader object for reading text from the console.                                                                                                                       |
| `writer()`       | Returns a PrintWriter object for writing text to the console.                                                                                                                    |
| `format()`       | Writes a formatted string to this console's output stream using the specified format string and arguments.                                                                       |
| `printf()`       | A convenience method to write a formatted string to this console's output stream using the specified format string and arguments.                                                |
| `flush()`        | Flushes the console's output stream.                                                                                                                                             |
| `readLine()`     | Reads a single line of text from the console.                                                                                                                                    |
| `readPassword()` | Reads a password or passphrase from the console with echoing disabled. The password is returned as a character array.                                                            |

# Summary

1. Understand files, directories, and streams.

- Files are records that store data to a persistent storage device that is available after the application has finished executing. 
- Files are organized within a file system in directories, which in turn may contain other directories. 
- Files can be accessed using streams, which present the data in sequential blocks.

2. Be able to use the java.io.File class.

- Java File instances can be created by passing a path String to the new File() constructor. 
- The File class includes a number of instance methods for retrieving information about both files and directories. 
- It also includes methods to create/delete files and directories, as well as retrieve a list of files within the directory.

3. Distinguish between byte and character streams.

- The java.io API supports both byte and character streams. 
- Byte streams have the word InputStream or OutputStream in their name and are useful for interacting with binary data. 
- Character streams have the word Reader or Writer in their name and are convenient for working with String or character data.

4. Distinguish between low-level and high-level streams.

- A low-level stream is one that operates directly on the underlying resource, such as a stream that reads file data from the file system. 
- A high-level stream is one that operates on a low-level or other high-level stream to filter or convert data or to improve read/write performance with the buffer.

5. Be able to recognize and know how to use the following classes: BufferedReader, BufferedWriter, File, FileReader, FileWriter, FileInputStream, FileOutputStream, ObjectOutputStream, ObjectInputStream, and PrintWriter.

- The java.io API reuses terms in the stream class name, which are useful in decoding the function of the class, such as InputStream, OutputStream, Reader, Writer, Buffered, File, Object, and Print. 
- You should know how to use the stream classes listed here, including how to chain the streams together.

6. Be able to perform common stream operations including close(), flush(), mark(), markSupported(), reset(), and skip().

- The java.io API includes numerous methods common to both input and output stream classes. 
- The close() method is shared by all stream classes and can be used implicitly by using try-with-resource syntax. 
- The flush() method is used in output stream classes to force the writing of the data to the underlying resource. 
- The markSupported(), mark(), and reset() methods are used in conjunction with the input stream classes to mark a position in the stream and return to it later on. 
- Not all java.io input stream classes support the mark() and reset() operations. Finally, the skip() method is used in input stream classes to skip past a number of bytes.

7. Understand how to use Java serialization.

- Classes can implement the java. io.Serializable interface to indicate that they support serializing their data to disk. 
- The interface requires that all instance members of the class are Serializable or marked transient. 
- The String class and all Java primitives are Serializable. 
- The ObjectInputStream and ObjectOutputStream classes can be used to read and write a Serializable object from and to a stream, respectively.

8. Be able to interact with the user via the Console class.

- Java 6 introduced the Console class as a replacement to System.in and System.out for reading and writing data from the user, respectively. 
- The Console class includes special methods for retrieving passwords that are more secure than the standard ways of retrieving String values.