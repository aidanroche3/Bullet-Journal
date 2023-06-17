package cs3500.pa05.model;

import cs3500.pa05.model.enumerations.Day;

/**
 * Abstract class for a journal item
 */
public abstract class Item {

  private String name;
  private String description;
  private Day day;

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

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public Day getDay() {
    return this.day;
  }

}
