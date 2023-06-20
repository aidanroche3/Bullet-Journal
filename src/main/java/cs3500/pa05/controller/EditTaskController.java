package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for editing a task
 */
public class EditTaskController implements Controller {

  private final Journal journal;
  private final Task task;

  @FXML
  private Label title;
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
   * Instantiates an EditTaskController
   *
   * @param journal a journal
   * @param index   the index of the task to edit
   */
  public EditTaskController(Journal journal, int index) {
    this.journal = journal;
    this.task = journal.getTasks().get(index);
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    title.setText("Edit Task");
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"));
    day.setValue(task.getDay().toString());
    name.setText(task.getName());
    description.setText(task.getDescription());
    status.setItems(FXCollections.observableArrayList("Complete", "Incomplete"));
    status.setValue(task.getStatus().toString());
    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(event -> updateJournal());
  }

  /**
   * Updates the task entry
   */
  private void updateJournal() {

    String enteredDay = day.getValue();
    String chosenName = name.getText();
    String compStatus = status.getValue();

    int tasksOnThisDay = 0;
    Day chosenDay = Day.valueOf(enteredDay.toUpperCase());
    for (Task t : journal.getTasks()) {
      if (t.getDay().equals(chosenDay)) {
        tasksOnThisDay++;
      }
    }
    if (journal.getPreferences().getTaskLimit() > tasksOnThisDay) {
      if (!(chosenName.equals(""))) {
        String chosenDesc = description.getText();
        CompletionStatus chosenStatus = CompletionStatus.valueOf(compStatus.toUpperCase());
        task.setName(chosenName);
        task.setDescription(chosenDesc);
        task.setDay(chosenDay);
        task.setStatus(chosenStatus);
        SceneChanger.switchToScene("WeekView.fxml",
            new MenuController(journal), "Bujo's Bullet Journal");
      } else {
        message.setText("Invalid Entry. Please check fields again.");
      }
    } else {
      message.setText("Maximum number of tasks reached for today.");
    }
  }

}
