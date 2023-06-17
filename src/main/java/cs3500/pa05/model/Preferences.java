package cs3500.pa05.model;

/**
 * Class for representing user preferences
 */
public class Preferences {
  private final String name;
  private final int taskLimit;
  private final int eventLimit;

  /**
   * Instantiates a user preference
   *
   * @param name the name of the week
   * @param taskLimit the task limit
   * @param eventLimit the event limit
   */
  public Preferences(String name, int taskLimit, int eventLimit) {
    this.name = name;
    this.taskLimit = taskLimit;
    this.eventLimit = eventLimit;
  }
}
