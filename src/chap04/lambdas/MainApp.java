package chap04.lambdas;

public class MainApp {

  static String run = "run";
  String walk = "walk";

  void play(Gorilla gorilla){
    System.out.println(gorilla.move());
  }

  void everyonePlay(boolean baby) {
    String approach = "amble";
    play(() -> run); // Access static variables
    play(() -> walk); // Access instance variables
    play(() -> baby ? "hitch a ride" : "run"); // Access method parameters
    play(() -> approach); // Access local variables
  }

  public static void main(String[] args) {
    MainApp app = new MainApp();
    app.everyonePlay(true);
  }
}
interface Gorilla {
  String move();
}
