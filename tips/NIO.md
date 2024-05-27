### 1. Phương thức `Files.copy`

Phương thức `Files.copy` sẽ sao chép tệp `test1.txt` vào `test2.txt`. Nếu `test2.txt` không tồn tại, nó sẽ được tạo mới. Tuy nhiên, phương thức `Files.isSameFile` không kiểm tra nội dung của tệp. Nó được thiết kế để kiểm tra xem hai đối tượng đường dẫn có trỏ đến cùng một tệp hay không. Trong trường hợp này, chúng không trỏ đến cùng một tệp, do đó, nó sẽ trả về giá trị false.

Dưới đây là mô tả ngắn gọn của JavaDoc cho cả hai phương thức:

```java
public static Path copy(Path source, Path target, CopyOption... options)
                 throws IOException
```

Sao chép một tệp sang tệp đích.
Phương thức này sao chép một tệp sang tệp đích với tham số options chỉ định cách thực hiện việc sao chép. Mặc định, việc sao chép sẽ thất bại nếu tệp đích đã tồn tại hoặc là một liên kết tượng trưng, ngoại trừ khi nguồn và đích là cùng một tệp, trong trường hợp đó phương thức sẽ hoàn tất mà không sao chép tệp. Các thuộc tính tệp không bắt buộc phải sao chép sang tệp đích. Nếu hỗ trợ liên kết tượng trưng và tệp là một liên kết tượng trưng, thì đích cuối cùng của liên kết sẽ được sao chép. Nếu tệp là một thư mục, nó sẽ tạo một thư mục trống tại vị trí đích (các mục trong thư mục không được sao chép).

Tham số options có thể bao gồm bất kỳ lựa chọn nào sau đây:

- **REPLACE_EXISTING**: Nếu tệp đích đã tồn tại, tệp đích sẽ được thay thế nếu nó không phải là một thư mục không rỗng. Nếu tệp đích tồn tại và là một liên kết tượng trưng, thì chính liên kết tượng trưng, không phải đích của liên kết, sẽ được thay thế.

- **COPY_ATTRIBUTES**: Cố gắng sao chép các thuộc tính tệp liên quan từ tệp này sang tệp đích. Các thuộc tính tệp chính xác được sao chép phụ thuộc vào nền tảng và hệ thống tệp và do đó không xác định. Tối thiểu, thời gian sửa đổi cuối cùng sẽ được sao chép sang tệp đích nếu được hỗ trợ bởi cả nguồn và tệp đích. Việc sao chép dấu thời gian tệp có thể dẫn đến mất độ chính xác.

- **NOFOLLOW_LINKS**: Các liên kết tượng trưng không được theo dõi. Nếu tệp là một liên kết tượng trưng, thì chính liên kết tượng trưng, không phải đích của liên kết, sẽ được sao chép. Việc sao chép các thuộc tính tệp vào liên kết mới là cụ thể theo từng triển khai. Nói cách khác, tùy chọn COPY_ATTRIBUTES có thể bị bỏ qua khi sao chép một liên kết tượng trưng.

Một triển khai của giao diện này có thể hỗ trợ các tùy chọn cụ thể theo triển khai bổ sung.

Việc sao chép một tệp không phải là một hoạt động nguyên tử. Nếu một IOException được ném ra thì có thể tệp đích không hoàn chỉnh hoặc một số thuộc tính tệp của nó chưa được sao chép từ tệp nguồn. Khi tùy chọn REPLACE_EXISTING được chỉ định và tệp đích tồn tại, tệp đích sẽ bị thay thế. Việc kiểm tra sự tồn tại của tệp và tạo tệp mới có thể không phải là nguyên tử đối với các hoạt động hệ thống tệp khác.


### 2. Phương thức `Path.getName()`

Hãy ghi nhớ 4 điểm sau về phương thức `Path.getName()`:

1. Chỉ số của các tên đường dẫn bắt đầu từ 0.
2. Root (tức là `c:\`) không được bao gồm trong các tên đường dẫn.
3. `\` KHÔNG phải là một phần của tên đường dẫn.
4. Nếu bạn truyền vào một chỉ số âm hoặc một giá trị lớn hơn hoặc bằng số lượng phần tử, hoặc đường dẫn này không có phần tử tên nào, thì sẽ ném ra ngoại lệ `java.lang.IllegalArgumentException`. Nó KHÔNG trả về null.

Ví dụ, nếu đường dẫn của bạn là "c:\\code\\java\\PathTest.java":

- `p1.getRoot()` là `c:\` (Đối với các môi trường Unix, root thường là `/`).
- `p1.getName(0)` là `code`
- `p1.getName(1)` là `java`
- `p1.getName(2)` là `PathTest.java`
- `p1.getName(3)` sẽ gây ra ngoại lệ `IllegalArgumentException`.

### 3. Phương thức `Path getRoot()`

Trả về thành phần root của đường dẫn này dưới dạng một đối tượng Path, hoặc null nếu đường dẫn này không có thành phần root.

### 4. Phương thức `Path relativize()`

Mục đích của phương thức `relativize` là xác định một đường dẫn tương đối, khi áp dụng vào đường dẫn gốc sẽ cho bạn đường dẫn được truyền vào. Dưới đây là một ví dụ và mô tả chi tiết về cách hoạt động của phương thức này:

Ví dụ:
- "a/b" relativize "c/d" sẽ là "../../c/d" bởi vì nếu bạn đang ở thư mục `b`, bạn phải đi ngược lại hai bước rồi tiến một bước đến `c` và một bước nữa đến `d` để đến `d`.
- "a/c" relativize "a/b" sẽ là "../b" bởi vì bạn chỉ cần đi ngược lại một bước đến `a` và sau đó tiến một bước đến `b`.

##### Mô tả chi tiết phương thức relativize()

```java
public Path relativize(Path other)
```

- **Mô tả**: Phương thức này xây dựng một đường dẫn tương đối giữa đường dẫn hiện tại và một đường dẫn được cho. Quá trình tạo ra một đường dẫn tương đối là ngược lại của quá trình giải quyết (resolution). Phương thức này cố gắng tạo ra một đường dẫn tương đối sao cho khi được giải quyết với đường dẫn hiện tại, sẽ trả về một đường dẫn trỏ đến cùng một tệp như đường dẫn được cho.

Ví dụ, trên UNIX, nếu đường dẫn hiện tại là "/a/b" và đường dẫn được cho là "/a/b/c/d" thì đường dẫn tương đối kết quả sẽ là "c/d".

- **Chi tiết hoạt động**:
    - Nếu đường dẫn hiện tại và đường dẫn được cho không có thành phần root, thì có thể tạo ra một đường dẫn tương đối.
    - Không thể tạo ra một đường dẫn tương đối nếu chỉ một trong hai đường dẫn có thành phần root.
    - Nếu cả hai đường dẫn đều có thành phần root thì khả năng tạo ra một đường dẫn tương đối phụ thuộc vào từng triển khai cụ thể.
    - Nếu đường dẫn hiện tại và đường dẫn được cho bằng nhau thì sẽ trả về một đường dẫn rỗng.

- **Ví dụ khác**:
    - Với hai đường dẫn đã được chuẩn hóa `p` và `q`, nếu `q` không có thành phần root:
      ```java
      p.relativize(p.resolve(q)).equals(q)
      ```

- **Liên kết tượng trưng (symbolic links)**:
    - Khi hỗ trợ các liên kết tượng trưng, thì việc đường dẫn kết quả, khi được giải quyết với đường dẫn hiện tại, có thể được sử dụng để trỏ đến cùng một tệp như đường dẫn được cho là phụ thuộc vào từng triển khai cụ thể. Ví dụ, nếu đường dẫn hiện tại là "/a/b" và đường dẫn được cho là "/a/x" thì đường dẫn tương đối kết quả có thể là "../x". Nếu `b` là một liên kết tượng trưng thì khả năng "a/b/../x" trỏ đến cùng một tệp như "/a/x" phụ thuộc vào từng triển khai cụ thể.

##### Ví dụ cụ thể

Nếu đường dẫn của bạn là "c:\\code\\java\\PathTest.java":

- `p1.getRoot()` là `c:\` (Đối với các môi trường Unix, root thường là `/`).
- `p1.getName(0)` là `code`
- `p1.getName(1)` là `java`
- `p1.getName(2)` là `PathTest.java`
- `p1.getName(3)` sẽ gây ra ngoại lệ `IllegalArgumentException`.

Hy vọng với những thông tin trên, bạn đã hiểu rõ hơn về cách hoạt động của phương thức `relativize()` trong Java và cách nó có thể được áp dụng trong các tình huống cụ thể.

### 5. Phương thức `Path.subpath(int beginIndex, int endIndex)`

1. **Chỉ số bắt đầu từ 0**.
2. **Root (ví dụ: `c:\`) không được xem là điểm bắt đầu**.
3. **Tên tại `beginIndex` được bao gồm nhưng tên tại `endIndex` thì không**.
4. **Các đường dẫn không bắt đầu hoặc kết thúc bằng dấu `\`**.

Do đó, trong trường hợp đường dẫn "c:\\finance\\data\\reports\\daily\\pnl.txt":

- Tên tại chỉ số 0 là `finance`
- Tên tại chỉ số 2 là `reports`
- Tuy nhiên, vì tên tại `endIndex` không được bao gồm, nên `subpath(0, 2)` sẽ tương ứng với `finance\data`.

##### Mô tả API cho phương thức này:

```java
public Path subpath(int beginIndex, int endIndex)
```

**Mô tả**:
- Trả về một `Path` tương đối là một subsequence của các phần tử tên của đường dẫn này.
- Các tham số `beginIndex` và `endIndex` chỉ định subsequence của các phần tử tên.
- Tên gần với root nhất trong hệ thống thư mục có chỉ số 0.
- Tên xa nhất từ root có chỉ số `count-1`.
- Đối tượng `Path` trả về có các phần tử tên bắt đầu từ `beginIndex` và kéo dài đến phần tử tại chỉ số `endIndex-1`.

**Tham số**:
- `beginIndex` - chỉ số của phần tử đầu tiên, bao gồm.
- `endIndex` - chỉ số của phần tử cuối cùng, không bao gồm.

**Trả về**:
- Một đối tượng `Path` mới là một subsequence của các phần tử tên trong đường dẫn này.

**Ngoại lệ**:
- `IllegalArgumentException` - nếu `beginIndex` là số âm, hoặc lớn hơn hoặc bằng số lượng phần tử.
- Nếu `endIndex` nhỏ hơn hoặc bằng `beginIndex`, hoặc lớn hơn số lượng phần tử.

##### Ví dụ cụ thể

Với đường dẫn "c:\\finance\\data\\reports\\daily\\pnl.txt":

- `p1.subpath(0, 2)` sẽ trả về "finance\data".

### 6. Phương thức `Path.resolve()`:

```java
public Path resolve(Path other)
```

**Mô tả**:

- Phương thức này sẽ giải quyết (resolve) đường dẫn được cung cấp với đường dẫn hiện tại.

- Nếu tham số `other` là một đường dẫn tuyệt đối, phương thức này sẽ đơn giản trả về `other`.
- Nếu `other` là một đường dẫn rỗng, phương thức này sẽ đơn giản trả về đường dẫn hiện tại.
- Ngược lại, phương thức này coi đường dẫn hiện tại như là một thư mục và giải quyết đường dẫn được cung cấp với đường dẫn hiện tại. Trong trường hợp đơn giản nhất, đường dẫn được cung cấp không có thành phần gốc (root component), khi đó phương thức này nối đường dẫn được cung cấp vào đường dẫn hiện tại và trả về một đường dẫn kết quả kết thúc bằng đường dẫn được cung cấp. Khi đường dẫn được cung cấp có thành phần gốc, việc giải quyết sẽ phụ thuộc rất nhiều vào cách cài đặt và do đó không được xác định cụ thể.

**Tham số**:

- `other` - đường dẫn cần giải quyết với đường dẫn hiện tại

**Trả về**:

- Đường dẫn kết quả

### Phương thức `Path normalize()`:

```java
public Path normalize()
```

**Mô tả**:

- Trả về một đường dẫn là đường dẫn hiện tại với các phần tử tên dư thừa bị loại bỏ.

- Định nghĩa chính xác của phương thức này phụ thuộc vào cách cài đặt, nhưng nói chung nó sẽ từ đường dẫn hiện tại tạo ra một đường dẫn không chứa các phần tử tên dư thừa. Trong nhiều hệ thống tệp, "." và ".." là các tên đặc biệt được sử dụng để chỉ thư mục hiện tại và thư mục cha. Trong các hệ thống tệp như vậy, tất cả các trường hợp của "." được coi là dư thừa. Nếu một ".." được đặt trước bởi một tên không phải ".." thì cả hai tên được coi là dư thừa (quá trình xác định các tên như vậy được lặp lại cho đến khi không còn áp dụng nữa).

- Phương thức này không truy cập vào hệ thống tệp; đường dẫn có thể không xác định một tệp tồn tại. Việc loại bỏ ".." và một tên đứng trước từ một đường dẫn có thể dẫn đến một đường dẫn xác định một tệp khác với đường dẫn ban đầu. Điều này có thể xảy ra khi tên đứng trước là một liên kết tượng trưng (symbolic link).

**Trả về**:

- Đường dẫn kết quả hoặc đường dẫn hiện tại nếu nó không chứa các phần tử tên dư thừa; một đường dẫn rỗng được trả về nếu đường dẫn này có thành phần gốc và tất cả các phần tử tên đều dư thừa.


| Path gốc      | Path parameter | `relativize()`                      | `resolve()`                              |
|---------------|----------------|-------------------------------------|------------------------------------------|
| Absolute      | Absolute       | Tính khoảng cách tương đối          | Path parameter                           |
| Absolute      | Relative       | Tính khoảng cách tương đối          | Kết hợp Path gốc và Path parameter       |
| Relative      | Absolute       | Ném `IllegalArgumentException`      | Path parameter                           |
| Relative      | Relative       | Tính khoảng cách tương đối          | Kết hợp Path gốc và Path parameter       |
