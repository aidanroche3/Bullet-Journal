package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for editing a task
 */
public class EditTaskController extends TaskController {
  private final Task task;

  /**
   * Instantiates an EditTaskController
   *
   * @param journal a journal
   * @param index   the index of the task to edit
   */
  public EditTaskController(Journal journal, int index) {
    super(journal);
    this.task = journal.getTasks().get(index);
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    taskTitle.setText("Edit Task");
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
    description.setOnKeyReleased(e -> Validator.enforceDescriptionLimit(
        description.getText(), description, message));
    name.setOnKeyReleased(e -> Validator.enforceTitleLimit(name.getText(), name, message));
  }

  /**
   * Updates the task entry
   *
   * @param name    name of the task
   * @param desc    description of the task
   * @param day     day of the task
   * @param status  status of the task
   */
  @Override
  protected void updateEntry(String name, String desc, Day day, CompletionStatus status) {
    task.setName(name);
    task.setDescription(desc);
    task.setDay(day);
    task.setStatus(status);
  }

}
