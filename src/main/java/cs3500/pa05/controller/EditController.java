package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * Controller for the edit window
 */
public class EditController implements Controller {
  private final Journal journal;

  @FXML
  private Button cancel;
  @FXML
  private Button confirm;
  @FXML
  private Slider taskSlider;
  @FXML
  private Label taskLimit;
  @FXML
  private Slider eventSlider;
  @FXML
  private Label eventLimit;
  @FXML
  private TextField name;

  public EditController(Journal journal) {
    this.journal = journal;
  }

  @Override
  public void run() {
    name.setText(journal.getPreferences().getName());
    taskLimit.setText("New Task Limit: " + journal.getPreferences().getTaskLimit());
    eventLimit.setText("New Event Limit: " + journal.getPreferences().getEventLimit());
    eventSlider.setValue(journal.getPreferences().getEventLimit());
    taskSlider.setValue(journal.getPreferences().getTaskLimit());
    eventSlider.setMin(journal.getEvents().size());
    taskSlider.setMin(journal.getTasks().size());
    eventSlider.setSnapToTicks(true);
    taskSlider.setSnapToTicks(true);

    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));

    taskSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      int tasks = (int) taskSlider.getValue();
      taskLimit.setText("New Task Limit: " + tasks);
    });
    eventSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      int events = (int) eventSlider.getValue();
      eventLimit.setText("New Event Limit: " + events);
    });
    confirm.setOnAction(event -> setNew());
  }

  private void setNew() {
    if (name.getText() == "") {
      return;
    }
    String newName = name.getText();
    int tasks = (int) taskSlider.getValue();
    int events = (int) eventSlider.getValue();
    journal.getPreferences().setName(newName);
    journal.getPreferences().setEventLimit(events);
    journal.getPreferences().setTaskLimit(tasks);
    SceneChanger.switchToScene("WeekView.fxml",
        new MenuController(journal), "Bujo's Bullet Journal");

  }
}
