package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains data about a journal's preferences
 */
public class Preferences {
  String name;
  int taskLimit;
  int eventLimit;

  public Preferences(String name, int taskLimit, int eventLimit) {
    this.name = name;
    this.taskLimit = taskLimit;
    this.eventLimit = eventLimit;
  }

  public String getName() {
    return this.name;
  }

  public int getTaskLimit() {
    return this.taskLimit;
  }

  public int getEventLimit() {
    return eventLimit;
  }
}
