package cs3500.pa05.model;

import java.util.List;

/**
 * Class for representing a bullet journal
 */
public class Journal {

  private String name;
  private List<Task> tasks;
  private List<Event> events;

  /**
   * Instantiates a journal
   *
   * @param name the name of the journal
   * @param events a list of events for the week
   * @param tasks a list of tasks for the week
   */
  public Journal(String name, List<Task> tasks, List<Event> events) {
    this.name = name;
    this.tasks = tasks;
    this.events = events;
  }

}
