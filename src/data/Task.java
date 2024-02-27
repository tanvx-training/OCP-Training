package data;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
  private String description;
  private boolean completed;
  private LocalDate dueDate;
  private int priority;

  // Constructor
  public Task(String description, LocalDate dueDate, int priority) {
    this.description = description;
    this.completed = false; // Initialize as incomplete
    this.dueDate = dueDate;
    this.priority = priority;
  }

  // Getter and setter for description
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // Getter and setter for completed status
  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  // Getter and setter for due date
  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  // Getter and setter for priority
  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  // Method to mark task as completed
  public void completeTask() {
    this.completed = true;
  }

  // Method to mark task as incomplete
  public void markIncomplete() {
    this.completed = false;
  }

  // Override equals and hashCode methods for comparing tasks
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return completed == task.completed &&
        priority == task.priority &&
        Objects.equals(description, task.description) &&
        Objects.equals(dueDate, task.dueDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, completed, dueDate, priority);
  }

  // Override toString method for better printing
  @Override
  public String toString() {
    return "Task{" +
        "description='" + description + '\'' +
        ", completed=" + completed +
        ", dueDate=" + dueDate +
        ", priority=" + priority +
        '}';
  }
}

