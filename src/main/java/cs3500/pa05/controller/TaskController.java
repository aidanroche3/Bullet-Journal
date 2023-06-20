package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for handling the task window
 */
public class TaskController implements Controller {

  protected final Journal journal;

  @FXML
  protected Label taskTitle;
  @FXML
  private Button cancel;
  @FXML
  private Button confirm;
  @FXML
  private ComboBox<String> day;
  @FXML
  private TextField name;
  @FXML
  private TextField description;
  @FXML
  private ComboBox<String> status;
  @FXML
  private Label message;

  /**
   * Instantiates a task controller
   *
   * @param journal the current journal
   */
  public TaskController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    taskTitle.setText("Create Event");
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"));
    status.setItems(FXCollections.observableArrayList("Complete", "Incomplete"));
    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(event -> updateJournal());
  }

  /**
   * Updates the journal with the new event
   *
   */
  private void updateJournal() {

    String enteredDay = day.getValue();
    String chosenName = name.getText();
    String compStatus = status.getValue();

    if (enteredDay != null) {
      int tasksOnThisDay = 0;
      Day chosenDay = Day.valueOf(enteredDay.toUpperCase());
      for (Task t : journal.getTasks()) {
        if (t.getDay().equals(chosenDay)) {
          tasksOnThisDay++;
        }
      }
      if (journal.getPreferences().getTaskLimit() > tasksOnThisDay) {
        if (!(chosenName.equals("") || compStatus == null)) {
          String chosenDesc = description.getText();
          CompletionStatus chosenStatus = CompletionStatus.valueOf(compStatus.toUpperCase());
          Task task = new Task(chosenName, chosenDesc, chosenDay, chosenStatus);
          journal.addTask(task);
          SceneChanger.switchToScene("WeekView.fxml",
              new MenuController(journal), "Bujo's Bullet Journal");
        } else {
          message.setText("Invalid Entry. Please check fields again.");
        }
      } else {
        message.setText("Maximum number of tasks reached for today.");
      }
    } else {
      message.setText("Please select a day.");
    }
  }
}
