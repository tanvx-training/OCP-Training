# NIO

## Introducing NIO.2

- The NIO API introduced the concepts of buffers and channels in place of java.io streams. 
- The basic idea is that you load the data from a file channel into a temporary buffer that, unlike byte streams, can be read forward and backward without blocking on the underlying resource.

### Introducing Path

- The java.nio.file.Path interface, or Path interface for short, is the primary entry point for working with the NIO.2 API.
- A Path object represents a hierarchical path on the storage system to a file or directory. 
- Unlike the File class, the Path interface contains support for symbolic links. A symbolic link is a special file within an operating system that serves as a reference or pointer to another file or directory.

#### Creating Instances with Factory and Helper Classes

**NIO.2 class and interface relationships**

![Roadmap](NIO.2-class-and-interface-relationships.png)

### Creating Paths

#### Using the Paths Class

- The simplest and most straightforward way to obtain a Path object is using the java.nio.files.Paths factory class, or Paths for short.

```java
Paths.getPath(String);

// Example 
Path path1 = Paths.get("pandas/cuddly.png");

Path path2 = Paths.get("c:\\zooinfo\\November\\employees.txt"); 

Path path3 = Paths.get("/home/zoodirector");
```

```java
Paths.get(String,String...);

// Example
Path path1 = Paths.get("pandas","cuddly.png");

Path path2 = Paths.get("c:","zooinfo","November","employees.txt"); 

Path path3 = Paths.get("/","home","zoodirector");

```

> Absolute vs Relative is file system dependent
> 
> - If a path starts with a forward slash, it is an absolute path, such as /bird/parrot. 
> 
> - If a path starts with a drive letter, it is an absolute path, such as C:\bird\emu. 
> 
> - Otherwise, it is a relative path, such as ..\eagle.