import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
    Path path = Paths.get("/home/user/documents/../java/./file.txt");

    Path normalizedPath = path.normalize();
    System.out.println("Normalized Path: " + normalizedPath);
  }
}