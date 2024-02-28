package data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Task {
  private int taskId;
  private String title;
  private String description;
  private LocalDate deadline;
  private String status;
  private int assignedTo;

  // Constructors, getters, and setters


  public Task() {
  }

  public Task(int taskId, String title, String description, LocalDate deadline, String status, int assignedTo) {
    this.taskId = taskId;
    this.title = title;
    this.description = description;
    this.deadline = deadline;
    this.status = status;
    this.assignedTo = assignedTo;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getAssignedTo() {
    return assignedTo;
  }

  public void setAssignedTo(int assignedTo) {
    this.assignedTo = assignedTo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return taskId == task.taskId && Objects.equals(title, task.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, title);
  }

  @Override
  public String toString() {
    return "Task{" +
            "taskId=" + taskId +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", deadline=" + deadline +
            ", status='" + status + '\'' +
            ", assignedTo=" + assignedTo +
            '}';
  }
}
