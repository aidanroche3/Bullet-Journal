package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Preferences and its associated methods
 */
class PreferencesTest {
  private Preferences preferences;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    preferences = new Preferences("Prefs", 10, 2);
  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Prefs", preferences.getName());
  }

}