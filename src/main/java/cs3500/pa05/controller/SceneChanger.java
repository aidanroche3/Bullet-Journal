package cs3500.pa05.controller;

import cs3500.pa05.view.FXMLViewLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for controller the switch of scenes
 */
public class SceneChanger {

  /**
   * Switches the scene to the task container
   *
   * @param event an event
   * @param fxmlFile a string of the desired FXML file to switch to
   * @param controller a controller
   */
  public static void switchToScene(javafx.event.ActionEvent event, String fxmlFile, Controller controller) {
    FXMLViewLoader loader = new FXMLViewLoader(fxmlFile, controller);
    Scene scene = loader.load();
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    controller.run();
  }

}
