package chap04.streams;

import data.TaskManager;

public class MainApp {

  public static void main(String[] args) {
    TaskManager.generateTasks(20)
        .forEach(System.out::println);
  }
}
