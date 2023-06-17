package cs3500.pa05.model;

import java.util.List;

/**
 * Class for representing a bullet journal
 */
public class Journal {

  private Preferences preferences;
  private List<Task> tasks;
  private List<Event> events;

  /**
   * Instantiates a journal
   *
   * @param preferences the preferences of the journal
   * @param events a list of events for the week
   * @param tasks a list of tasks for the week
   */
  public Journal(Preferences preferences, List<Task> tasks, List<Event> events) {
    this.preferences = preferences;
    this.tasks = tasks;
    this.events = events;
  }

}
