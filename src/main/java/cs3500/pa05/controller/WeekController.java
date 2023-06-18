package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import java.io.File;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import java.nio.file.Path;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class WeekController implements Controller {
  private Journal journal;

  @FXML
  private TextField newName;
  @FXML
  private Button newPath;
  @FXML
  private Button newCancel;
  @FXML
  private Button newConfirm;
  @FXML
  private Label pathLabel;

  private Path path;

  public WeekController(Journal journal) {
    this.journal = journal;
  }

  @Override
  public void run() {
    newPath.setOnAction(this::getDirectory);
    newConfirm.setOnAction(this::createNewWeek);

    if (!journal.getPath().toString().equals("")) {
      newCancel.setOnAction(event -> SceneChanger.switchToScene(
          "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    } else {
      newCancel.setOnAction(event -> SceneChanger.switchToScene(
          "Welcome.fxml", new WelcomeSceneController(), "Welcome!"));
    }
  }

  private void getDirectory(javafx.event.ActionEvent event) {
    DirectoryChooser chooser = new DirectoryChooser();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    File dir = chooser.showDialog(stage);
    if (dir != null) {
      pathLabel.setText(dir.getAbsolutePath());
      path = Path.of(dir.getAbsolutePath());
    }

  }

  /**
   * Creates new week
   */
  private void createNewWeek(javafx.event.ActionEvent event) {
    String name = newName.getText();
    if (Objects.equals(name, "") || path == null) {
      return;
    }

    Preferences preferences = new Preferences(name, 50, 50);
    Journal newJournal = new Journal(preferences, new ArrayList<>(), new ArrayList<>(), path);
    Controller menuController = new MenuController(newJournal);
    SceneChanger.switchToScene("WeekView.fxml", menuController, "Bujo's Bullet Journal");
  }
}
