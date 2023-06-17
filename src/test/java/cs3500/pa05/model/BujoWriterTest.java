package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.PreferencesJson;
import cs3500.pa05.model.json.TaskJson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing BujoWriter and its associated methods
 */
class BujoWriterTest {

  private JournalJson journal;
  private JournalJson newJo;

  /**
   * Initializes the test data
   */
  @BeforeEach
  void setUp() {
    EventJson[] events = new EventJson[] {
        new EventJson("Name", "Desc", Day.MONDAY, "", 7.7)};
    TaskJson[] tasks = new TaskJson[]{
        new TaskJson("Name", "Desc", Day.MONDAY, CompletionStatus.COMPLETE)};
    PreferencesJson preferences = new PreferencesJson("Week 1", 8, 16);
    journal = new JournalJson(preferences, tasks, events);
    
    TaskJson[] newTasks = new TaskJson[] {
        new TaskJson("Finish Project", "Finish all the tests and fix style changes", Day.MONDAY, CompletionStatus.INCOMPLETE),
        new TaskJson("Get dinner", "Suffer through IV food", Day.WEDNESDAY, CompletionStatus.COMPLETE),
        new TaskJson("Go to Hokkaido Ramen", "Don't bring Ray with us", Day.MONDAY, CompletionStatus.COMPLETE),
        new TaskJson("Win Battle Salvo", "Make a good AI", Day.SUNDAY, CompletionStatus.INCOMPLETE) };
    EventJson[] newEvents = new EventJson[] {
        new EventJson("Go to Goodge Street", "Make sure it's not closed this time", Day.MONDAY, "12:00pm", 1.5),
        new EventJson("Khoury College Welcome day", "Meet new people", Day.SATURDAY, "1:00pm", 2.3)};
    PreferencesJson newPreferences = new PreferencesJson("Busy Week", 4, 3);
    newJo = new JournalJson(newPreferences, newTasks, newEvents);
    }

  @Test
  void writeJournal() {
    Path expected = Path.of("src/test/resources/BujoReaderTest.bujo");
    Path writeAt = Path.of("src/test/resources/BujoWriterTest.bujo");
    Path invalid = Path.of("invalid/");
    try {
      BujoWriter.writeJournal(writeAt, journal);
      BujoWriter.writeJournal(Path.of("src/test/resources/Test.bujo"), newJo);
      assertEquals(-1, Files.mismatch(expected, writeAt));
    } catch (IOException e) {
      fail();
    }
    assertThrows(IOException.class,
        () -> BujoWriter.writeJournal(Path.of("invalid directory"), journal));
    assertThrows(IOException.class, () -> BujoWriter.writeJournal(invalid, journal));
  }
}