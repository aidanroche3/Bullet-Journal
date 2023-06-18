package cs3500.pa05.model;

import cs3500.pa05.model.enumerations.Day;

/**
 * Abstract class for a journal item
 */
public abstract class Item {

  private final String name;
  private final String description;
  private final Day day;

  /**
   * Instantiates an Item
   *
   * @param name the name of the item
   * @param description a description of the item
   * @param day the day of the week the item occurs on
   */
  public Item(String name, String description, Day day) {
    this.name = name;
    this.description = description;
    this.day = day;
  }

  /**
   * Gets the name of the item
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of the item
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the day of the item
   *
   * @return the day
   */
  public Day getDay() {
    return this.day;
  }

}
