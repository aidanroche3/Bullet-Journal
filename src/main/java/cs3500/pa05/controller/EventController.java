package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for handle the event window
 */
public class EventController implements Controller {

  private Journal journal;

  @FXML
  private Button cancel;

  @FXML
  private Button confirm;

  public EventController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    cancel.setOnAction(event -> SceneChanger.switchToScene(event,
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(event -> SceneChanger.switchToScene(event,
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
  }
}
