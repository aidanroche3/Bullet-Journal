package cs3500.pa05.model;

import java.util.List;

/**
 * Class for representing a bullet journal
 */
public class Journal {

  private String name;
  private List<Event> events;
  private List<Task> tasks;

  /**
   * Instantiates a journal
   *
   * @param name the name of the journal
   * @param events a list of events for the week
   * @param tasks a list of tasks for the week
   */
  public Journal(String name, List<Event> events, List<Task> tasks) {
    this.name = name;
    this.events = events;
    this.tasks = tasks;
  }

}
