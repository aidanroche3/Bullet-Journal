package cs3500.pa05.controller;

import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.Event;
import  cs3500.pa05.model.Journal;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for handle the event window
 */
public class EventController implements Controller {

  protected final Journal journal;

  @FXML
  protected Label title;
  @FXML
  protected Button cancel;

  @FXML
  protected Button confirm;

  @FXML
  protected TextField name;

  @FXML
  protected TextField start;

  @FXML
  protected TextField duration;

  @FXML
  protected TextField description;

  @FXML
  protected ComboBox<String> day;

  @FXML
  protected TextField startHour;

  @FXML
  protected TextField startMinute;

  @FXML
  protected ComboBox<String> meridiem;

  @FXML
  protected Label message;

  /**
   * Instantiates an EventController
   *
   * @param journal a journal
   */
  public EventController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"));
    meridiem.setItems(FXCollections.observableArrayList("AM", "PM"));
    meridiem.setValue("AM");
    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(event -> updateJournal());
  }

  /**
   * Updates the journal with a new event
   *
   */
  protected void updateJournal() {
    //Checked Values
    String checkedName = Validator.validateName(name.getText());
    String checkedDesc = description.getText();
    Day checkedDay = Validator.validateDay(day.getValue());
    Meridiem checkedMeridiem = Meridiem.valueOf(meridiem.getValue());
    BujoTime checkedTime = Validator.validateTime(startHour.getText(), startMinute.getText(), checkedMeridiem);
    Double checkedDuration = Validator.validateDuration(duration.getText());

     if (checkedDay == null) {
      message.setText("Please select a day.");
    } else if (journal.getPreferences().getEventLimit() <= getEventsOnThisDay(checkedDay)) {
      message.setText("Maximum number of events reached for today.");
    } else if (checkedName == null) {
       message.setText("Please enter a name for the event.");
    } else if (checkedTime == null) {
       message.setText("Please enter a valid start time.");
    } else if (checkedDuration == null) {
      message.setText("Invalid duration. Enter a valid duration.");
    } else {
      updateEntry(checkedName, checkedDesc, checkedDay, checkedTime, checkedDuration);
       SceneChanger.switchToScene("WeekView.fxml",
           new MenuController(journal), "Bujo's Bullet Journal");
    }
  }

  /**
   * Gets the number of events on this day
   *
   * @param day a day
   * @return the number of events on this day
   */
  private int getEventsOnThisDay(Day day) {
    int eventsOnThisDay = 0;
    for (Event e : journal.getEvents()) {
      if (e.getDay().equals(day)) {
        eventsOnThisDay++;
      }
    }
    return eventsOnThisDay;
  }

  /**
   * Adds the new entry to the task list
   *
   * @param name a name
   * @param desc a description
   * @param day a day
   * @param start a start time
   * @param duration a duration
   */
  protected void updateEntry(String name, String desc, Day day, BujoTime start, double duration) {
    Event e = new Event(name, desc, day, start, duration);
    journal.addEvent(e);
  }

}
