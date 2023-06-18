package cs3500.pa05.controller;

import cs3500.pa05.view.FxmlViewLoader;
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
   * @param sceneName the name of the scene to change to
   */
  public static void switchToScene(javafx.event.ActionEvent event,
                                   String fxmlFile, Controller controller,
                                   String sceneName) {
    FxmlViewLoader loader = new FxmlViewLoader(fxmlFile, controller);
    Scene scene = loader.load();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle(sceneName);
    stage.show();
    controller.run();
  }
}
