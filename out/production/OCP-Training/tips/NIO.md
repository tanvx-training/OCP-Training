**Files.copy(Path source, Path target, CopyOption... options):**
- **Description**: Copies a file to a target file.
- **Behavior**:
    - Copies the source file to the target file, with options specifying how the copy is performed.
    - By default, the copy fails if the target file already exists, except if the source and target are the same file.
    - File attributes are not required to be copied.
    - If symbolic links are supported, and the file is a symbolic link, then the final target of the link is copied.
    - If the source file is a directory, an empty directory is created in the target location (entries in the directory are not copied).
- **Options**:
    - `REPLACE_EXISTING`: Replaces the target file if it exists.
    - `COPY_ATTRIBUTES`: Copies file attributes if supported by both the source and target file store.
    - `NOFOLLOW_LINKS`: Does not follow symbolic links.

**Files.isSameFile(Path path1, Path path2):**
- **Description**: Checks if two path objects resolve to the same file.
- **Behavior**:
    - Does not check the contents of the file.
    - Returns true if the two paths resolve to the same file, and false otherwise.

**Files.move(Path source, Path target, CopyOption... options):**
- **Description**: Moves or renames a file to a target file.
- **Behavior**:
    - By default, attempts to move the file to the target file, failing if the target file exists except if the source and target are the same file.
    - If the file is a symbolic link, the link itself is moved.
    - Can be used to move an empty directory.
- **Options**:
    - `REPLACE_EXISTING`: Replaces the target file if it exists.
    - `ATOMIC_MOVE`: Performs the move as an atomic file system operation.

**Important Notes**:
- File copying and moving are not atomic operations.
- If an IOException is thrown during the operation, the state of the files is not defined.

**Method: `relativize(Path other)`**

**Purpose**: Constructs a relative path between this path and a given path.

**Basic Idea**:
- Relativization is the inverse of resolution.
- The method aims to create a relative path such that when resolved against this path, it leads to the same location as the given path.

**Conditions**:
- If both paths have no root component, a relative path can be constructed.
- Relative path cannot be constructed if only one path has a root component.
- If both paths have a root component, it's implementation-dependent whether a relative path can be constructed.

**Special Cases**:
- If this path and the given path are equal, an empty path is returned.
- For any two normalized paths `p` and `q`, where `q` does not have a root component:
  - `p.relativize(p.resolve(q)).equals(q)`

**Symbolic Links**:
- When symbolic links are supported, the result may vary.
- Example: If this path is "/a/b" and the given path is "/a/x", the resulting relative path may be "../x".
- Implementation-dependent whether "b" being a symbolic link affects the outcome.

**Example**:
- If this path is "/a/b" and the given path is "/a/b/c/d", the resulting relative path would be "c/d".

>Note that if one path has a root (for example, if a path starts with a // or c:) and the other does not, relativize cannot work and it will throw an IllegalArgumentException.

**Method: `resolve(Path other)`**

**Purpose**: Resolve the given path against this path.

**Behavior**:
- If the `other` parameter is an absolute path, the method returns `other` directly.
- If `other` is an empty path, the method returns this path.
- Otherwise, if this path is considered a directory, the method resolves `other` against it.
  - If `other` does not have a root component, it is joined to this path.
  - If `other` has a root component, resolution is implementation-dependent and unspecified.

**Parameters**:
- `other`: The path to resolve against this path.

**Returns**:
- The resulting path after resolution.
