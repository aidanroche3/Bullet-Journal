package cs3500.pa05.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

  private Stage stage;
  private Scene scene;

  public void switchToTask(javafx.event.ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("NewTask.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
