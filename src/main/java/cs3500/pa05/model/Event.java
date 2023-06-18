package cs3500.pa05.model;

import cs3500.pa05.model.enumerations.Day;

/**
 * Class for representing a journal event
 */
public class Event extends Item {

  private final String start;
  private final double duration;

  /**
   * Instantiates an event
   *
   * @param name the name of the event
   * @param description a description of the event
   * @param day the day of the week the event occurs
   * @param start the start time of the event
   * @param duration the duration of the event
   */
  public Event(String name, String description, Day day, String start, double duration) {
    super(name, description, day);
    this.start = start;
    this.duration = duration;
  }

  /**
   * Gets the start of the event
   *
   * @return the start
   */
  public String getStart() {
    return this.start;
  }

  /**
   * Gets the duration of the event
   *
   * @return the duration
   */
  public double getDuration() {
    return this.duration;
  }

}
