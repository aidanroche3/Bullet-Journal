package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import java.awt.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * Controller for handling the task window
 */
public class TaskController implements Controller {

  private Journal journal;

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
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"));
    status.setItems(FXCollections.observableArrayList("Complete", "Incomplete"));
    cancel.setOnAction(event -> SceneChanger.switchToScene(event,
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(this::updateJournal);
  }

  /**
   * Updates the journal with the new event
   *
   * @param event an action event
   */
  private void updateJournal(javafx.event.ActionEvent event) {

    String enteredDay = day.getValue();
    String chosenName = name.getText();
    String compStatus = status.getValue();

    if (!(enteredDay == null || chosenName.equals("") || compStatus == null)) {
      String chosenDesc = description.getText();
      Day chosenDay = Day.valueOf(enteredDay.toUpperCase());
      CompletionStatus chosenStatus = CompletionStatus.valueOf(compStatus.toUpperCase());
      Task task = new Task(chosenName, chosenDesc, chosenDay, chosenStatus);
      journal.addTask(task);
      SceneChanger.switchToScene(event, "WeekView.fxml",
          new MenuController(journal), "Bujo's Bullet Journal");
    } else {
      //TODO: Send a message to the user saying invalid entry
    }
  }
}
