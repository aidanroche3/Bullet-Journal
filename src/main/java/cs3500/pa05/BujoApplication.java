package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.MenuController;
import cs3500.pa05.controller.SceneChanger;
import cs3500.pa05.controller.WelcomeSceneController;
import cs3500.pa05.view.FxmlViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 */
public class BujoApplication extends Application {

  /**
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    SceneChanger.setStage(primaryStage);
    Controller controller = new WelcomeSceneController();
    FxmlViewLoader loader = new FxmlViewLoader("Welcome.fxml", controller);

    try {
      primaryStage.setScene(loader.load());
      primaryStage.setTitle("Welcome!");
      primaryStage.setResizable(false);

      controller.run();

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

}
