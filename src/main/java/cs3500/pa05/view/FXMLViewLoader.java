package cs3500.pa05.view;

import cs3500.pa05.controller.SceneController;
import java.io.IOException;
import java.nio.file.Path;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 *
 */
public class FXMLViewLoader {
  FXMLLoader loader;

  /**
   *
   * @param s string
   */
  public FXMLViewLoader(String s) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(s));
  }

  /**
   * Loads a scene from a Whack-a-Mole GUI layout.
   *
   * @return the layout
   */
  public Scene load() {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
