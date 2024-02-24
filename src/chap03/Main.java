package chap03;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

  public static void main(String[] args) {
    // Create a queue using LinkedList
    Queue<String> queue = new LinkedList<>();

    // Use the offer() method to add elements to the queue
    queue.offer("Apple");
    queue.offer("Banana");
    queue.offer("Orange");

    // Use the peek() method to view the next element without removing it
    System.out.println("Next element in the queue: " + queue.peek());

    // Use the poll() method to remove and return the next element
    String removedElement = queue.poll();
    System.out.println("Removed element from the queue: " + removedElement);

    // Use the element() method to get the next element without removing it
    String nextElement = queue.element();
    System.out.println("Next element in the queue: " + nextElement);

    // Use the remove() method to remove and return the next element
    String removedElement2 = queue.remove();
    System.out.println("Removed element from the queue: " + removedElement2);
  }
}
