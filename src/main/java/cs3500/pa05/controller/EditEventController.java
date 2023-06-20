package cs3500.pa05.controller;

import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for editing an event
 */
public class EditEventController implements Controller {
  private Journal journal;
  private Event event;

  @FXML
  private Label title;

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


  public EditEventController(Journal journal, int index) {
    this.journal = journal;
    this.event = journal.getEvents().get(index);
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    title.setText("Edit Event");
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"));
    day.setValue(event.getDay().toString());
    name.setText(event.getName());
    start.setText(event.getStart().toString());
    duration.setText("" + event.getDuration());
    description.setText(event.getDescription());
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

    //TODO: refactor inputs to get all necessary data to make a BujoTime
    String enteredStart = start.getText();

    String enteredDuration = duration.getText();

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
          event.setStart(new BujoTime(1, 1, Meridiem.AM));
          event.setDuration(duration);
          event.setDay(chosenDay);
          event.setName(chosenName);
          event.setDescription(desc);
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
  }

}
