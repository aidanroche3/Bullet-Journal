package cs3500.pa05.controller;

import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.JsonAdapter;
import cs3500.pa05.view.FxmlViewLoader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Controller for controlling the menu scene
 */
public class MenuController implements Controller {

  private static final Font WEEK_NAME_FONT = Font.font("Verdana", FontWeight.BOLD, 10);
  private static final Font LABEL_FONT = Font.font("Verdana", FontWeight.MEDIUM, 10);
  private static final Color EVENT_COLOR = Color.rgb(180,166,213);
  private static final Color COMPLETE_TASK_COLOR = Color.rgb(146,196,125);
  private static final Color INCOMPLETE_TASK_COLOR = Color.rgb(233,153,152);
  private static final CornerRadii CORNER_RADII = new CornerRadii(2);
  private static final Insets INSETS = new Insets(2);
  private Journal journal;
  @FXML
  private Button task;
  @FXML
  private Button event;
  @FXML
  private Button save;
  @FXML
  private Button open;
  @FXML
  private VBox monday;
  @FXML
  private VBox tuesday;
  @FXML
  private VBox wednesday;
  @FXML
  private VBox thursday;
  @FXML
  private VBox friday;
  @FXML
  private VBox saturday;
  @FXML
  private VBox sunday;
  @FXML
  private Label name;
  private Popup popup;
  @FXML
  private VBox pop;

  /**
   * Controls the menu scene
   *
   * @param journal a Journal
   */
  public MenuController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Sets the button actions
   */
  @FXML
  public void run() {
    task.setOnAction(event -> SceneChanger.switchToScene(event,
        "NewTask.fxml", new TaskController(journal)));
    event.setOnAction(event -> SceneChanger.switchToScene(event,
        "NewEvent.fxml", new EventController(journal)));
    open.setOnAction(this::fileChooser);
    name.setText(journal.getPreferences().getName());
    name.setFont(WEEK_NAME_FONT);
    addTasksToView();
    addEventsToView();
  }

  /**
   * Prompts the user to choose a .bujo file
   */
  @FXML
  private void fileChooser(javafx.event.ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("BUJO File", "*.bujo"));
    File f = chooser.showOpenDialog(null);

    if (f != null) {
      try {
        JournalJson journalJson = BujoReader.produceJournal(f.toPath());
        Journal journal = JsonAdapter.jsonToJournal(journalJson);
        Controller menuController = new MenuController(journal);
        SceneChanger.switchToScene(event, "WeekView.fxml", menuController);
      } catch (IOException e) {

      }
    }
  }

  /**
   * Adds the tasks in the journal to the week view
   */
  @FXML
  private void addTasksToView() {

    for (Task task : journal.getTasks()) {
      switch (task.getDay()) {
        case MONDAY -> monday.getChildren().add(generateTask(task));
        case TUESDAY -> tuesday.getChildren().add(generateTask(task));
        case WEDNESDAY -> wednesday.getChildren().add(generateTask(task));
        case THURSDAY -> thursday.getChildren().add(generateTask(task));
        case FRIDAY -> friday.getChildren().add(generateTask(task));
        case SATURDAY -> saturday.getChildren().add(generateTask(task));
        case SUNDAY -> sunday.getChildren().add(generateTask(task));
      }
    }

  }

  /**
   * Adds the events in the journal to the week view
   */
  @FXML
  private void addEventsToView() {

    for (Event event : journal.getEvents()) {
      switch (event.getDay()) {
        case MONDAY -> monday.getChildren().add(generateEvent(event));
        case TUESDAY -> tuesday.getChildren().add(generateEvent(event));
        case WEDNESDAY -> wednesday.getChildren().add(generateEvent(event));
        case THURSDAY -> thursday.getChildren().add(generateEvent(event));
        case FRIDAY -> friday.getChildren().add(generateEvent(event));
        case SATURDAY -> saturday.getChildren().add(generateEvent(event));
        case SUNDAY -> sunday.getChildren().add(generateEvent(event));
      }
    }

  }

  /**
   * Generates a vbox representing a task
   *
   * @param task a task
   * @return the task as a vbox
   */
  private VBox generateTask(Task task) {

    VBox taskBox = new VBox();
    Color color;
    if (task.getStatus().equals(CompletionStatus.COMPLETE)) {
      color = COMPLETE_TASK_COLOR;
    } else {
      color = INCOMPLETE_TASK_COLOR;
    }
    taskBox.setBackground(new Background(
        new BackgroundFill(color, CORNER_RADII, INSETS)));
    Label name = new Label(" " + task.getName());
    name.setFont(LABEL_FONT);
    Label description = new Label(" " + task.getDescription());
    description.setFont(LABEL_FONT);
    Label status = new Label(" " + task.getStatus().toString());
    status.setFont(LABEL_FONT);
    Label empty = new Label();
    empty.setFont(LABEL_FONT);
    taskBox.getChildren().addAll(name, description, status, empty);
    taskBox.setOnMouseClicked(e -> {
      makePopup(e,
          Arrays.asList(
              "Day: " + task.getDay(),
              "Task: " + task.getName(),
              "Description: " + task.getDescription(),
              "Status: " + task.getStatus().toString()),
          color);
    });
    return taskBox;
  }

  /**
   * Generates a vbox representing an event
   *
   * @param event an event
   * @return the event as a vbox
   */
  private VBox generateEvent(Event event) {

    VBox eventBox = new VBox();
    eventBox.setBackground(new Background(
        new BackgroundFill(EVENT_COLOR, CORNER_RADII, INSETS)));
    Label name = new Label(" " + event.getName());
    name.setFont(LABEL_FONT);
    Label description = new Label(" " + event.getDescription());
    description.setFont(LABEL_FONT);
    Label start = new Label(" " + event.getStart());
    start.setFont(LABEL_FONT);
    Label duration = new Label(" " + event.getDuration());
    duration.setFont(LABEL_FONT);
    eventBox.getChildren().addAll(name, description, start, duration);

    String desc = event.getDescription();
    String formattedDesc = desc.replaceAll("(.{25})", "$1\n");

    eventBox.setOnMouseClicked(e -> {
      makePopup(e,
          Arrays.asList(
              "Day: " + event.getDay(),
              "Event: " + event.getName(),
              "Description: " + formattedDesc,
              "Start Time: " + event.getStart(),
              "Duration: " + event.getDuration() + " hours"),
          EVENT_COLOR);
    });

    return eventBox;
  }

  /**
   * Makes a mini-viewer popup when an item is clicked
   *
   * @param event the mouse event
   * @param data a list of the item's data
   * @param color the color of the item
   */
  private void makePopup(MouseEvent event, List<String> data, Color color) {
    popup = new Popup();
    FxmlViewLoader loader = new FxmlViewLoader("Popup.fxml", this);
    Scene s = loader.load();
    popup.getContent().add((Node)s.getRoot());
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    pop.setBackground(new Background(
        new BackgroundFill(color, CORNER_RADII, INSETS)));

    for (String s1 : data) {
      Label label = new Label(s1);
      label.setFont(LABEL_FONT);
      pop.getChildren().add(label);
    }

    Button b = new Button("Done!");
    b.setPrefSize(200, 200);
    b.setOnAction(e -> popup.hide());
    pop.getChildren().add(b);

    popup.getContent().add(pop);
    popup.show(stage);
  }
}