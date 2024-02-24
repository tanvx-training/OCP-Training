import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    }

    public static void display(List<? extends Number> list) {
        list.forEach(System.out::println);
    }
}