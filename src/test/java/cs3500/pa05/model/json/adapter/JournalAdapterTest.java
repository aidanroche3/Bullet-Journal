package cs3500.pa05.model.json.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.json.JournalJson;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JournalAdapterTest {

  Journal journal1;
  JournalJson json1;

  ObjectMapper mapper;
  @BeforeEach
  void setUp() {
    Preferences p = new Preferences("Week 1", 5, 6);
    Task t1 = new Task("Task 1", "Description", Day.FRIDAY,  CompletionStatus.INCOMPLETE);
    Task t2 = new Task("Task 2", "Description", Day.SUNDAY,  CompletionStatus.COMPLETE);
    Event e1 = new Event("Event 1", "Description", Day.MONDAY, "", 2.5);
    Event e2 = new Event("Event 2", "Description", Day.TUESDAY, "", 3.0);

    try {
      json1 = BujoReader.produceJournal(Path.of("src/test/resources/BujoReaderTest.bujo"));
    } catch(IOException e) {
      fail();
    }

    journal1 = new Journal(p, Arrays.asList(t1, t2), Arrays.asList(e1, e2));

    mapper = new ObjectMapper();
  }

  @Test
  void toJournal() {
    JsonNode node = this.mapper.convertValue(json1, JsonNode.class);
    System.out.println(JournalAdapter.toJournal(node));

    assertEquals("{\"preferences\":{\"name\":\"Week 1\",\"taskLimit\":8,\"eventLimit\""
        + ":16},\"tasks\":[{\"name\":\"Name\",\"description\":\"Desc\",\"day\":\""
        + "MONDAY\",\"status\":\"COMPLETE\"}],\"events\":[{\"name\":\"Name\",\"des"
        + "cription\":\"Desc\",\"day\":\"MONDAY\",\"start\":\"\",\"duration\":7.7}"
        + "]}", node.toString());
  }

  @Test
  void toJson() {
    assertEquals("{\"preferences\":{\"name\":\"Week 1\",\"taskLimit\":5,\"eventLimit"
        + "\":6},\"tasks\":[{\"name\":\"Task 1\",\"description\":\"Description\",\"d"
        + "ay\":\"FRIDAY\",\"status\":\"INCOMPLETE\"},{\"name\":\"Task 2\",\"descrip"
        + "tion\":\"Description\",\"day\":\"SUNDAY\",\"status\":\"COMPLETE\"}],\"eve"
        + "nts\":[{\"name\":\"Event 1\",\"description\":\"Description\",\"day\":\"MO"
        + "NDAY\",\"start\":\"\",\"duration\":2.5},{\"name\":\"Event 2\",\"descripti"
        + "on\":\"Description\",\"day\":\"TUESDAY\",\"start\":\"\",\"duration\":3.0}]}",
        JournalAdapter.toJson(journal1).toString());
  }
}