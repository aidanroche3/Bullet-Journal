package cs3500.pa05.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Controls splash screen
 */
public class SplashController implements Controller {
  @FXML
  private Label load;

  @FXML
  private Rectangle progress;

  @Override
  public void run() {
    final String[] dots = {"."};
    Timeline loads = new Timeline(new KeyFrame(Duration.millis(500), e -> {
      if (dots[0].equals("....")) {
        dots[0] = ".";
      }
      load.setText("LOADING" + dots[0]);
      dots[0] += ".";
    }));
    Timeline bar = new Timeline(new KeyFrame(Duration.millis(50), e -> {
      progress.setWidth(progress.getWidth() + 2.1125);
    }));
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
      SceneChanger.switchToScene("Welcome.fxml", new WelcomeSceneController(), "Welcome");
    }));
    loads.setCycleCount(16);
    loads.play();
    bar.setCycleCount(160);
    bar.play();
    timeline.setCycleCount(1);
    timeline.play();
  }

}
