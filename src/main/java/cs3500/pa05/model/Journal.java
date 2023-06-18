package cs3500.pa05.model;

import java.nio.file.Path;
import java.util.List;

/**
 * Class for representing a bullet journal
 */
public class Journal {

  private Preferences preferences;
  private List<Task> tasks;
  private List<Event> events;

  private Path path;



  /**
   * Instantiates a journal
   *
   * @param preferences the preferences of the journal
   * @param events a list of events for the week
   * @param tasks a list of tasks for the week
   */
  public Journal(Preferences preferences, List<Task> tasks, List<Event> events, Path path) {
    this.preferences = preferences;
    this.tasks = tasks;
    this.events = events;
    this.path = path;
  }

  public Preferences getPreferences() {
    return this.preferences;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public List<Event> getEvents() {
    return this.events;
  }

  public void addTask(Task task) {
    this.tasks.add(task);
  }

  public void addEvent(Event event) {
    this.events.add(event);
  }

  public Path getPath() {
    return this.path;
  }

  public void setPath(Path path) {
    this.path = path;
  }
}
