package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Item and its associated methods
 */
class ItemTest {

  private Item item;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    item = new Task("item name", "desc", Day.MONDAY, CompletionStatus.COMPLETE);
  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("item name", item.getName());
  }

  /**
   * Tests the getDescription method
   */
  @Test
  public void testGetDescription() {
    assertEquals("desc", item.getDescription());
  }

  /**
   * Tests the getDay method
   */
  @Test
  public void testGetDay() {
    assertEquals(Day.MONDAY, item.getDay());
  }

}