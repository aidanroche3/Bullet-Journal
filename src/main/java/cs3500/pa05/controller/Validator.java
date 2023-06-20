package cs3500.pa05.controller;

import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;

/**
 * Class for validating inputs
 */
public class Validator {

  /**
   * Validates the given name
   *
   * @param name a name
   * @return the name
   */
  public static String validateName(String name) {
    return name;
  }

  /**
   * Validates the day
   * .
   * @param day a day
   * @return the day or null if the given is null
   */
  public static Day validateDay(String day) {
    if(day == null) {
      return null;
    }
    return Day.valueOf(day.toUpperCase());
  }

  /**
   * Validates the BujoTime
   *
   * @param givenHour an hour
   * @param givenMinute a minute
   * @param meridiem a meridiem
   * @return a BujoTime or null if any of the given are null
   */
  public static BujoTime validateTime(String givenHour, String givenMinute, Meridiem meridiem) {
    if(givenHour.equals("") || givenMinute.equals("")) {
      return null;
    } else {
      try {
        int hour = Integer.parseInt(givenHour);
        int minute = Integer.parseInt(givenMinute);

        return new BujoTime(hour, minute, meridiem);
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * Validates the duration
   *
   * @param givenDuration a duration
   * @return the duration or null if the given is null
   */
  public static Double validateDuration(String givenDuration) {
    if(givenDuration.equals("")) {
      return null;
    } else {
      try {
        return Double.parseDouble(givenDuration);
      } catch(NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * Validates the status
   *
   * @param givenStatus
   * @return
   */
  public static CompletionStatus validateStatus(String givenStatus) {
    if(givenStatus == null) {
      return null;
    }
    return CompletionStatus.valueOf(givenStatus.toUpperCase());
  }
}
