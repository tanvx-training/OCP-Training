BufferedWriter does not have writeUTF method, but it does have newLine and write(String) methods. So the code will fail to compile at //1.
You should remember that following points:
1. BufferedWriter only adds the functionality of buffering on top of a Writer. It doesn't directly deal with encoding. Encoding is handled by the underlying Writer object.
2. FileWriter is a concrete subclass of java.io.Writer that writes data to the underlying file in default encoding. If you want to write text in a different encoding, you will need to create an OutputStreamWriter with that encoding.

For example, OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("utf8.txt"), Charset.forName("UTF-8").newEncoder()  );
You can then create a BufferedWriter over this OutputStreamWriter.