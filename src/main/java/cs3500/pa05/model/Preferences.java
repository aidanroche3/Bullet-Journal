package cs3500.pa05.model;

public class Preferences {
  private String name;
  private int taskLimit;
  private int eventLimit;

  public Preferences (String name, int taskLimit, int eventLimit) {
    this.name = name;
    this.taskLimit = taskLimit;
    this.eventLimit = eventLimit;
  }
}
