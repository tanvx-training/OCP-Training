package data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskManager {

  static List<String> descriptions = Arrays.asList(
      "Learning Java", "Running", "Studying Math", "Writing a report",
      "Practicing guitar", "Cooking dinner", "Reading a book", "Walking the dog",
      "Cleaning the house", "Watching a movie", "Working out", "Listening to music",
      "Coding a project", "Drawing", "Meeting a friend", "Shopping for groceries",
      "Writing code", "Planning a trip", "Playing a game", "Taking a nap",
      "Building a website", "Learning a new language", "Volunteering", "Solving puzzles",
      "Attending a workshop", "Painting", "Attending a concert", "Traveling",
      "Writing poetry", "Singing karaoke", "Attending a lecture", "Teaching a class",
      "Attending a seminar", "Fishing", "Gardening", "Doing yoga",
      "Attending a party", "Exploring a new city", "Trying new food", "Biking",
      "Playing tennis", "Skiing", "Snowboarding", "Hiking",
      "Going to the beach", "Picnicking", "Bird watching", "Star gazing",
      "Photography", "Camping", "Reading news", "Meditating",
      "Watching a play", "Attending a festival", "Learning an instrument", "Playing chess",
      "Going for a drive", "Writing a blog", "Building a model", "Solving riddles",
      "Doing crossword puzzles", "Taking photographs", "Knitting", "Playing board games",
      "Exploring nature", "Visiting a museum", "Joining a club", "Writing letters",
      "Creating art", "Making crafts", "Listening to podcasts", "Going for a walk",
      "Doing a puzzle", "Attending a sports event", "Watching TV", "Trying new recipes",
      "Attending a performance", "Joining a book club", "Playing video games", "Collecting stamps",
      "Going for a run", "Attending a conference", "Practicing meditation", "Joining a choir",
      "Taking a dance class", "Going to the gym", "Trying a new sport", "Doing volunteer work",
      "Attending a webinar", "Going to a pub", "Doing a home workout", "Playing with pets",
      "Building a model kit", "Going to the zoo", "Doing a DIY project", "Watching a documentary"
  );

  public static List<Task> generateTasks(int count) {
    Random random = new Random();
    return IntStream.range(0, count)
        .mapToObj(i -> new Task(descriptions.get(random.nextInt(descriptions.size())), LocalDate.now().plusDays(i), i % 5))
        .collect(Collectors.toList());
  }
}
