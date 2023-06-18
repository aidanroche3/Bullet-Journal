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

  /**
   * Gets the name of the week
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the task limit
   *
   * @return the task limit
   */
  public int getTaskLimit() {
    return this.taskLimit;
  }

  /**
   * Gets the event limit
   *
   * @return the event limit
   */
  public int getEventLimit() {
    return eventLimit;
  }

}
