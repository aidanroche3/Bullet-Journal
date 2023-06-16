package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  /**
   * Initializes the test data
   */
  @BeforeEach
  void setUp() {
    EventJson[] events = new EventJson[]
        {new EventJson("Name", "Desc", Day.MONDAY, "", 7.7)};
    TaskJson[] tasks = new TaskJson[]
        {new TaskJson("Name", "Desc", Day.MONDAY, CompletionStatus.COMPLETE)};
    PreferencesJson preferences = new PreferencesJson("Week 1", 8, 16);
    journal = new JournalJson(preferences, tasks, events);
  }

  @Test
  void writeJournal() {
    Path expected = Path.of("src/test/resources/BujoReaderTest.bujo");
    Path writeAt = Path.of("src/test/resources/BujoWriterTest.bujo");
    try {
      BujoWriter.writeJournal(writeAt, journal);
      assertEquals(-1, Files.mismatch(expected, writeAt));
    } catch (IOException e) {
      fail();
    }
    assertThrows(IOException.class,
        () -> BujoWriter.writeJournal(Path.of("invalid directory"), journal));
  }
}