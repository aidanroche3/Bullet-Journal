package cs3500.pa05.controller;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

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
    task.setOnAction(event -> SceneChanger.switchToScene(event,
        "NewTask.fxml", new TaskController()));
    event.setOnAction(event -> SceneChanger.switchToScene(event,
        "NewEvent.fxml", new EventController()));
    open.setOnAction(event -> fileChooser());
  }

  @FXML
  private void fileChooser() {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("BUJO File", "*.bujo"));
    File f = chooser.showOpenDialog(null);

    System.out.println(f.getAbsoluteFile());
  }
}
