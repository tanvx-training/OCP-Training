package chap04.streams;

import data.DataManager;
import data.Task;
import data.User;

import java.util.List;

public class MainApp {

  public static void main(String[] args) {
    List<User> users = DataManager.generateUsers();
    users.forEach(System.out::println);
    List<Task> tasks = DataManager.generateTasks();
    tasks.forEach(System.out::println);
  }
}
