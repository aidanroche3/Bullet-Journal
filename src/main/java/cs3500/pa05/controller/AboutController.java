package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the About-window
 */
public class AboutController implements Controller {
  private Journal journal;

  @FXML
  private Button back;

  public AboutController(Journal journal) {
    this.journal = journal;
  }

  @Override
  public void run() {
    back.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
  }
}
