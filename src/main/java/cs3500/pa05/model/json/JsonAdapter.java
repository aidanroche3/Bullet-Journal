package cs3500.pa05.model.json;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for adapting jsons
 */
public class JsonAdapter {

  /**
   * Converts a journal json to a journal
   *
   * @param json a journal json
   * @return the json as a journal
   */
  public static Journal jsonToJournal(JournalJson json) {
    Preferences preferences = new Preferences(json.preferences().name(),
        json.preferences().taskLimit(), json.preferences().eventLimit());

    List<Task> tasks = new ArrayList<>();
    for (TaskJson t : json.tasks()) {
      Task task = new Task(t.name(), t.description(), t.day(), t.status());
      tasks.add(task);
    }

    List<Event> events = new ArrayList<>();
    for (EventJson e : json.events()) {
      Event event = new Event(e.name(), e.description(), e.day(), e.start(), e.duration());
      events.add(event);
    }

    return new Journal(preferences, tasks, events);
  }

}
