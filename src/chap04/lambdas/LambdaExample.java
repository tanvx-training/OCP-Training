package chap04.lambdas;

public class LambdaExample {
  public static void main(String[] args) {
    int x = 10; // Effectively final variable
    int y = 20; // Non-final variable

    // Lambda expression accessing local variable
    Runnable r = () -> {
      // Accessing local variables x and y
      System.out.println("x: " + x);
      System.out.println("y: " + y);
      // y++; // Error: Variable 'y' is accessed from within inner class, needs to be final or effectively final
    };

    x = 30; // Modifying x after lambda declaration, but it's effectively final
    // y = 40; // Error: Variable 'y' is accessed from within inner class, needs to be final or effectively final

    r.run(); // Execute the lambda
  }
}

