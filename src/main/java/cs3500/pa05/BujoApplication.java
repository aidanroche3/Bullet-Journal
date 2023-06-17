package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.MenuController;
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
    Controller controller = new MenuController();
    FxmlViewLoader loader = new FxmlViewLoader("WeekView.fxml", controller);

    try {
      primaryStage.setScene(loader.load());
      primaryStage.setTitle("Bujo's Bullet Journal");

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
