package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for controlling the menu scene
 */
public class MenuController implements Controller {
  @FXML
  private Button task;
  @FXML
  private Button event;
  @FXML
  private Button save;
  @FXML
  private Button open;


  /**
   * Controls the menu scene
   */
  public MenuController() {

  }

  /**
   * Sets the button actions
   */
  @FXML
  public void run() {
    task.setOnAction(event -> SceneChanger.switchToScene(event, "NewTask.fxml", new TaskController()));
    event.setOnAction(event -> SceneChanger.switchToScene(event, "NewEvent.fxml", new EventController()));
  }
}
