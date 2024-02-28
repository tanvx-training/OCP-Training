package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataManager {

  static List<String> descriptions = Arrays.asList(
      "Learning Java", "Running", "Studying Math", "Writing a report",
      "Practicing guitar", "Cooking dinner", "Reading a book", "Walking the dog",
      "Cleaning the house", "Watching a movie", "Working out", "Listening to music",
      "Coding a project", "Drawing", "Meeting a friend", "Shopping for groceries",
      "Writing code", "Planning a trip", "Playing a game", "Taking a nap",
      "Building a website", "Learning a new language"
  );

  public static List<User> generateUsers() {
    List<User> users = new ArrayList<>();
    users.add(new User(1, "john_doe", "John Doe", "john.doe@example.com"));
    users.add(new User(2, "jane_smith", "Jane Smith", "jane.smith@example.com"));
    users.add(new User(3, "mike_johnson", "Mike Johnson", "mike.johnson@example.com"));
    return users;
  }

  public static List<Task> generateTasks() {
    List<Task> tasks = new ArrayList<>();
    Random random = new Random();
    for (int i = 1; i <= descriptions.size(); i++) {
      Task task = new Task();
      task.setTaskId(i);
      task.setTitle("Task " + i);
      task.setDescription(descriptions.get(random.nextInt(descriptions.size())));
      task.setDeadline(LocalDate.now());
      task.setStatus("Pending");
      task.setAssignedTo(random.nextInt(generateUsers().size()) + 1);
      tasks.add(task);
    }
    return tasks;
  }
}
