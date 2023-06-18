package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import  cs3500.pa05.model.Journal;
import cs3500.pa05.model.enumerations.Day;
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

  private final Journal journal;

  @FXML
  private Button cancel;

  @FXML
  private Button confirm;

  @FXML
  private TextField name;

  @FXML
  private TextField start;

  @FXML
  private TextField duration;

  @FXML
  private TextField description;

  @FXML
  private ComboBox<String> day;

  @FXML
  private Label message;

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
    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(event -> updateJournal());
  }

  /**
   * Updates the journal with a new event
   *
   */
  public void updateJournal() {
    String enteredDay = day.getValue();
    String chosenName = name.getText();
    String enteredStart = start.getText();
    String enteredDuration = duration.getText();

    if (enteredDay != null) {
      int eventsOnThisDay = 0;
      Day chosenDay = Day.valueOf(enteredDay.toUpperCase());
      for (Event e : journal.getEvents()) {
        if (e.getDay().equals(chosenDay)) {
          eventsOnThisDay++;
        }
      }
      if (journal.getPreferences().getEventLimit() > eventsOnThisDay) {
        if (!(chosenName.equals("")
            || enteredStart.equals("")
            || enteredDuration.equals(""))) {
          try {
            String desc = description.getText();
            double duration = Double.parseDouble(enteredDuration);
            Event newEvent = new Event(chosenName, desc, chosenDay,
                enteredStart, duration);
            journal.addEvent(newEvent);
            SceneChanger.switchToScene("WeekView.fxml",
                new MenuController(journal), "Bujo's Bullet Journal");
          } catch (NumberFormatException err) {
            message.setText("Invalid duration. Enter a valid duration.");
          }
        } else {
          message.setText("Invalid Entry. Please check fields again.");
        }
      } else {
        message.setText("Maximum number of events reached for today.");
      }
    } else {
      message.setText("Please select a day.");
    }
  }
}
