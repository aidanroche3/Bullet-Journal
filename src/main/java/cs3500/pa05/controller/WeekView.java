package cs3500.pa05.controller;

import cs3500.pa05.view.FXMLViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 */
public class WeekView extends Application {

  /**
   *
   * @param primaryStage the primary stage for this application, onto which
   * the application scene can be set.
   * Applications may create other stages, if needed, but they will not be
   * primary stages.
   * @throws Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLViewLoader loader = new FXMLViewLoader("WeekView.fxml");

    try {
      primaryStage.setScene(loader.load());

      primaryStage.setTitle("Welcome Screen");

      primaryStage.show();
    } catch (IllegalStateException e) {
      System.out.println("Unable to Load");
    }
  }
  /**
   *
   */
  public void run() {
    Application.launch();
  }

  /**
   *
   */
  public void switchScene() {

  }
}
