package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EventController implements Controller{
  /**
   * Runs the controller
   */

  @FXML
  Button cancel;

  @FXML
  Button confirm;

  @Override
  public void run() {
    cancel.setOnAction(event -> SceneChanger.switchToScene(event, "WeekView.fxml", new MenuController()));
    confirm.setOnAction(event -> SceneChanger.switchToScene(event, "WeekView.fxml", new MenuController()));
  }
}
