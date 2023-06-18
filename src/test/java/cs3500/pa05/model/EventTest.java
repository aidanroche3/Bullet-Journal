package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enumerations.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Event and its associated methods
 */
class EventTest {

  private Event event;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    event = new Event("Event name", "Desc",
        Day.THURSDAY, "12:00pm", 1.6);
  }

  /**
   * Tests the getStart method
   */
  @Test
  public void testGetStart() {
    assertEquals("12:00pm", event.getStart());
  }

  /**
   * Tests the getDuration method
   */
  @Test
  public void testGetDuration() {
    assertEquals(1.6, event.getDuration());
  }
}