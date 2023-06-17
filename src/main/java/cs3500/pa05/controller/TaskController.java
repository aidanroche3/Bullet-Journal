package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for handling the task window
 */
public class TaskController implements Controller {

  private Journal journal;

  @FXML
  private Button cancel;
  @FXML
  private Button confirm;

  public TaskController(Journal journal) {
    this.journal = journal;
  }

  @Override
  public void run() {
    cancel.setOnAction(event -> SceneChanger.switchToScene(event,
        "WeekView.fxml", new MenuController(journal)));
    confirm.setOnAction(event -> SceneChanger.switchToScene(event,
        "WeekView.fxml", new MenuController(journal)));
  }
}
