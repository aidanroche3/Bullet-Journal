package cs3500.pa05.model.json.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.PreferencesJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts Model Classes to a full Json Object
 */
public class JournalAdapter {

  /**
   * Converts a JsonNode to a Journal
   *
   * @param node    JsonNode to convert
   * @return        Journal from JsonNode
   */
  public static Journal toJournal(JsonNode node) {
    ObjectMapper mapper = new ObjectMapper();
    JournalJson journalJson = mapper.convertValue(node, JournalJson.class);

    PreferencesJson prefJson = journalJson.preferences();
    TaskJson[] tasksJson = journalJson.tasks();
    EventJson[] eventsJson = journalJson.events();

    Preferences preferences = new Preferences(prefJson.name(), prefJson.taskLimit(),
        prefJson.eventLimit());
    List<Task> tasks = new ArrayList<>();
    List<Event> events = new ArrayList<>();

    for (TaskJson tj : tasksJson) {
      tasks.add(new Task(tj.name(), tj.description(), tj.day(), tj.status()));
    }

    for (EventJson ej : eventsJson) {
      events.add(new Event(ej.name(), ej.description(), ej.day(), ej.start(), ej.duration()));
    }

    return new Journal(preferences, tasks, events);
  }

  /**
   * Converts a Journal to a JsonNode
   *
   * @param journal    Journal to convert
   * @return           JsonNode from Journal
   */
  public static JsonNode toJson(Journal journal) {
    ObjectMapper mapper = new ObjectMapper();
    PreferencesJson prefJson = mapper.convertValue(journal.getPreferences(), PreferencesJson.class);
    List<Task> tasks = journal.getTasks();
    List<Event> events = journal.getEvents();
    TaskJson[] tasksJson = new TaskJson[tasks.size()];
    EventJson[] eventsJson = new EventJson[events.size()];

    for (int i = 0; i < tasks.size(); i++) {
      tasksJson[i] = mapper.convertValue(tasks.get(i), TaskJson.class);
    }

    for (int i = 0; i < events.size(); i++) {
      eventsJson[i] = mapper.convertValue(events.get(i), EventJson.class);
    }

    JournalJson journalJson = new JournalJson(prefJson, tasksJson, eventsJson);

    return mapper.convertValue(journalJson, JsonNode.class);
  }
}
