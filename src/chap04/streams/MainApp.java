package chap04.streams;

import java.util.Random;
import java.util.stream.Stream;

public class MainApp {

  public static void main(String[] args) {
    Stream.generate(() -> new Random(100).nextInt())
        .forEach(System.out::println);
  }
}
