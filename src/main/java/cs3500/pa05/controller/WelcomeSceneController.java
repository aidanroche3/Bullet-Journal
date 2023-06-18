package cs3500.pa05.controller;

import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.adapter.JournalAdapter;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * Controller for the welcome scene
 */
public class WelcomeSceneController implements Controller {

  @FXML
  private Button select;

  /**
   * Runs the controller
   */
  public void run() {
    select.setOnAction(this::setAction);
  }

  /**
   * Sets the action of the button
   *
   * @param event an event
   */
  private void setAction(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("BUJO File", "*.bujo"));
    File file = chooser.showOpenDialog(null);

    try {
      JournalJson journalJson = BujoReader.produceJournal(file.toPath());
      Journal journal = JournalAdapter.toJournal(journalJson, file.toPath());
      Controller menuController = new MenuController(journal);
      SceneChanger.switchToScene(event, "WeekView.fxml", menuController, "Bujo's Bullet Journal");
    } catch (IOException e) {
      //TODO: find out what we need to do if invalid
    }
  }
}
