package cs3500.pa05.controller;

import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.BujoWriter;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Item;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.adapter.JournalAdapter;
import cs3500.pa05.view.FxmlViewLoader;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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

  private static final Font WEEK_NAME_FONT = Font.font("Verdana", FontWeight.BOLD, 20);
  private static final Font LABEL_FONT = Font.font("Verdana", FontWeight.MEDIUM, 10);
  private static final Color EVENT_COLOR = Color.rgb(180, 166, 213);
  private static final Color COMPLETE_TASK_COLOR = Color.rgb(146, 196, 125);
  private static final Color INCOMPLETE_TASK_COLOR = Color.rgb(233, 153, 152);
  private static final CornerRadii CORNER_RADII = new CornerRadii(2);
  private static final Insets INSETS = new Insets(2);
  private final Journal journal;
  @FXML
  private Button task;
  @FXML
  private Button event;
  @FXML
  private Button week;
  @FXML
  private Button save;
  @FXML
  private Button open;
  @FXML
  private Button edit;
  @FXML
  private Button about;
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
  @FXML
  private ScrollPane tasks;
  @FXML
  private Label border;
  @FXML
  private Label stats;
  @FXML
  private VBox queue;

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
    clearBoardVisual();
    setBorder();
    setMenubar();
    setShortcuts();
    addTasksToView();
    addEventsToView();
    addTasksToQueue();
    updateStats();
  }

  /**
   * Sets the warning border
   */
  private void setBorder() {
    border.setFont(LABEL_FONT);
    if (journal.getTasks().size() < journal.getPreferences().getTaskLimit() * 7) {
      task.setOnAction(event -> SceneChanger.switchToScene(
          "NewTask.fxml", new TaskController(journal), "Add a new task"));
    } else {
      border.setText("Maximum Amount of Tasks Reached for this Week.");
    }
    if (journal.getEvents().size() < journal.getPreferences().getEventLimit() * 7) {
      event.setOnAction(event -> SceneChanger.switchToScene(
          "NewEvent.fxml", new EventController(journal), "Add a new event"));
    } else {
      border.setText("Maximum Amount of Events Reached for this Week.");
    }

    if (journal.getTasks().size() >= journal.getPreferences().getTaskLimit() * 7
        && journal.getEvents().size() >= journal.getPreferences().getEventLimit() * 7) {
      border.setText("Maximum Amount of Tasks and Events Reached for this Week.");
    }
  }

  /**
   * Sets the menubar actions
   */
  private void setMenubar() {
    save.setOnAction(event -> fileSaver());
    open.setOnAction(event -> fileChooser());
    week.setOnAction(event -> SceneChanger.switchToScene("NewWeek.fxml",
        new WeekController(journal), "New Week"));
    name.setText(journal.getPreferences().getName());
    name.setFont(WEEK_NAME_FONT);
    edit.setOnAction(event -> SceneChanger.switchToScene("Edit.fxml",
        new EditController(journal), "Edit Tasks"));
    about.setOnAction(event -> SceneChanger.switchToScene("About.fxml",
        new AboutController(journal), "About"));
  }

  /**
   * Sets the menu shortcuts
   */
  private void setShortcuts() {
    Scene scene = SceneChanger.getScene();
    KeyCombination saveCombo = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    Runnable saveRunnable = this::fileSaver;
    scene.getAccelerators().put(saveCombo, saveRunnable);
    KeyCombination openCombo = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
    Runnable openRunnable = this::fileChooser;
    scene.getAccelerators().put(openCombo, openRunnable);
    KeyCombination weekCombo = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    Runnable weekRunnable = () -> SceneChanger.switchToScene("NewWeek.fxml",
        new WeekController(journal), "New Week");
    scene.getAccelerators().put(weekCombo, weekRunnable);
    if (journal.getTasks().size() < journal.getPreferences().getTaskLimit() * 7) {
      KeyCombination taskCombo = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
      Runnable taskRunnable = () -> SceneChanger.switchToScene(
          "NewTask.fxml", new TaskController(journal), "Add a new task");
      scene.getAccelerators().put(taskCombo, taskRunnable);
    }
    if (journal.getEvents().size() < journal.getPreferences().getEventLimit() * 7) {
      KeyCombination eventCombo = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
      Runnable eventRunnable = () -> SceneChanger.switchToScene(
          "NewEvent.fxml", new EventController(journal), "Add a new event");
      scene.getAccelerators().put(eventCombo, eventRunnable);
    }
    KeyCombination editCombo = new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN);
    Runnable editRunnable = () -> SceneChanger.switchToScene("Edit.fxml",
        new EditController(journal), "Edit Tasks");
    scene.getAccelerators().put(editCombo, editRunnable);
    KeyCombination aboutCombo = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    Runnable aboutRunnable = () -> SceneChanger.switchToScene("About.fxml",
        new AboutController(journal), "About");
    scene.getAccelerators().put(aboutCombo, aboutRunnable);

  }

  /**
   * Prompts the user to choose a .bujo file
   */
  @FXML
  private void fileChooser() {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("BUJO File", "*.bujo"));
    File f = chooser.showOpenDialog(null);

    if (f != null) {
      try {
        JournalJson journalJson = BujoReader.produceJournal(f.toPath());
        Journal journal = JournalAdapter.toJournal(journalJson, f.toPath());
        Controller menuController = new MenuController(journal);
        SceneChanger.switchToScene("WeekView.fxml",
            menuController, "Bujo's Bullet Journal");
      } catch (IOException e) {
        //TODO: what does this even catch bro
        border.setText("");
      }
    }
  }

  /**
   * Saves the journal
   */
  private void fileSaver() {
    if (!journal.getPath().toFile().isFile()) {
      Path p = journal.getPath();
      String newPath = p.toString() + "\\" + journal.getPreferences().getName() + ".bujo";
      journal.setPath(Path.of(newPath));

    }
    JournalJson journalJson = JournalAdapter.toJson(journal);
    try {
      BujoWriter.writeJournal(journal.getPath(), journalJson);
      border.setText("Journal saved in new file: " + journal.getPreferences().getName() + ".bujo");
    } catch (IOException e) {
      border.setText("Journal could not be saved.");
    }
  }

  /**
   * Adds the tasks in the journal to the week view
   */
  @FXML
  private void addTasksToView() {
    List<Task> tasks = journal.getTasks();
    for (int i = 0; i < tasks.size(); i++) {
      switch (tasks.get(i).getDay()) {
        case MONDAY -> monday.getChildren().add(generateTask(tasks.get(i), i));
        case TUESDAY -> tuesday.getChildren().add(generateTask(tasks.get(i), i));
        case WEDNESDAY -> wednesday.getChildren().add(generateTask(tasks.get(i), i));
        case THURSDAY -> thursday.getChildren().add(generateTask(tasks.get(i), i));
        case FRIDAY -> friday.getChildren().add(generateTask(tasks.get(i), i));
        case SATURDAY -> saturday.getChildren().add(generateTask(tasks.get(i), i));
        case SUNDAY -> sunday.getChildren().add(generateTask(tasks.get(i), i));
        default -> {

        }
      }
    }

  }

  /**
   * Adds tasks to task queue
   */
  @FXML
  private void addTasksToQueue() {
    for (int i = 0; i < journal.getTasks().size(); i++) {
      queue.getChildren().add(generateTask(journal.getTasks().get(i), i));
    }
    if (journal.getTasks().size() >= 10) {
      queue.setPrefWidth(177);
    } else {
      queue.setPrefWidth(192);
    }
    tasks.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    tasks.setContent(queue);
  }

  /**
   * Adds the events in the journal to the week view
   */
  @FXML
  private void addEventsToView() {
    List<Event> events = journal.getEvents();
    for (int i = 0; i < events.size(); i++) {
      switch (events.get(i).getDay()) {
        case MONDAY -> monday.getChildren().add(generateEvent(events.get(i), i));
        case TUESDAY -> tuesday.getChildren().add(generateEvent(events.get(i), i));
        case WEDNESDAY -> wednesday.getChildren().add(generateEvent(events.get(i), i));
        case THURSDAY -> thursday.getChildren().add(generateEvent(events.get(i), i));
        case FRIDAY -> friday.getChildren().add(generateEvent(events.get(i), i));
        case SATURDAY -> saturday.getChildren().add(generateEvent(events.get(i), i));
        case SUNDAY -> sunday.getChildren().add(generateEvent(events.get(i), i));
        default -> {
        }
      }
    }
  }

  /**
   * Generates a vbox representing a task
   *
   * @param task a task
   * @return the task as a vbox
   */
  private VBox generateTask(Task task, int index) {

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
    taskBox.setOnMouseClicked(event ->
        makePopup(
            Arrays.asList(
                "Day: " + task.getDay(),
                "Task: " + task.getName(),
                "Description: " + task.getDescription(),
                "Status: " + task.getStatus().toString()),
            color, index, Task.class));
    return taskBox;
  }

  /**
   * Generates a vbox representing an event
   *
   * @param event an event
   * @return the event as a vbox
   */
  private VBox generateEvent(Event event, int index) {

    VBox eventBox = new VBox();
    eventBox.setBackground(new Background(
        new BackgroundFill(EVENT_COLOR, CORNER_RADII, INSETS)));
    Label name = new Label(" " + event.getName());
    name.setFont(LABEL_FONT);
    Label description = new Label(" " + event.getDescription());
    description.setFont(LABEL_FONT);
    Label start = new Label(" " + event.getStart().toString());
    start.setFont(LABEL_FONT);
    Label duration = new Label(" " + event.getDuration());
    duration.setFont(LABEL_FONT);
    eventBox.getChildren().addAll(name, description, start, duration);

    String desc = event.getDescription();
    String formattedDesc = desc.replaceAll("(.{25})", "$1\n");

    eventBox.setOnMouseClicked(e ->
        makePopup(
            Arrays.asList(
                "Day: " + event.getDay(),
                "Event: " + event.getName(),
                "Description: " + formattedDesc,
                "Start Time: " + event.getStart().toString(),
                "Duration: " + event.getDuration() + " hours"),
            EVENT_COLOR, index, Event.class));
    return eventBox;
  }

  /**
   * Makes a mini-viewer popup when an item is clicked
   *
   * @param data a list of the item's data
   * @param color the color of the item
   */
  private void makePopup(List<String> data, Color color,
                         int index, Class<? extends Item> className) {
    if (popup != null && popup.isShowing()) {
      return;
    }
    popup = new Popup();
    FxmlViewLoader loader = new FxmlViewLoader("Popup.fxml", this);
    Scene s = loader.load();
    popup.getContent().add(s.getRoot());
    pop.setBackground(new Background(
        new BackgroundFill(color, CORNER_RADII, INSETS)));

    for (String string : data) {
      int currentIndex = 0;
      String regex = "(https:\\/\\/|http:\\/\\/)([A-Za-z0-9]+)((?<!\\.)\\.(?!\\.))([A-Za-z0-9\\/]+)";
      Pattern p = Pattern.compile(regex);
      Matcher m = p.matcher(string);

      List<String> links = new ArrayList<>();
      List<Integer> linkIndexes = new ArrayList<>();

      while (m.find()) {
        links.add(m.group());
        linkIndexes.add(m.start());
      }

      List<String> split = new ArrayList<>(Arrays.asList(string.split(regex)));

      int numTimes = split.size() + links.size();
      for (int i = 0; i < numTimes; i++) {
        if (links.size() > 0 && linkIndexes.get(0) == currentIndex) {

          Hyperlink hyperLink = new Hyperlink(links.get(0));

          String l = links.get(0);
          hyperLink.setOnAction(e -> {
            try {
              Desktop desk = Desktop.getDesktop();
              URI url = new URI(l);
              desk.browse(url);
            } catch (Exception ex) {
              System.out.println(ex.getMessage());
            }
          });

          pop.getChildren().add(hyperLink);
          currentIndex += links.get(0).length();
          links.remove(0);
          linkIndexes.remove(0);

        } else if(split.size() > 0){
          Label label = new Label(split.get(0));
          currentIndex += split.get(0).length();
          label.setFont(LABEL_FONT);
          pop.getChildren().add(label);
          split.remove(0);
        }
      }
    }

    addButtonsToPopup(index, className);

    popup.getContent().add(pop);
    Stage stage = SceneChanger.getStage();
    popup.show(stage);
  }

  /**
   * Adds buttons to the popup window
   *
   * @param index   index of this task/events position in the journal lists
   * @param className   is this item a task or event
   */
  private void addButtonsToPopup(int index, Class<? extends Item> className) {
    Button b = new Button("Done!");
    b.setPrefSize(100, 50);
    b.setOnAction(e -> popup.hide());
    pop.getChildren().add(b);

    Button c = new Button("Delete!");
    c.setPrefSize(100, 50);
    c.setOnAction(e -> hideAndDelete(index, className));
    pop.getChildren().add(c);

    if (className.equals(Task.class)) {
      Button d = new Button("Mark as Complete");
      d.setPrefSize(100, 50);
      d.setOnAction(e -> {
        journal.getTasks().get(index).setStatus(CompletionStatus.COMPLETE);
        pop.setBackground(new Background(
            new BackgroundFill(COMPLETE_TASK_COLOR, CORNER_RADII, INSETS)));
        run();
      });
      Button f = new Button("Mark as Incomplete");
      f.setPrefSize(100, 50);
      f.setOnAction(e -> {
        journal.getTasks().get(index).setStatus(CompletionStatus.INCOMPLETE);
        pop.setBackground(new Background(
            new BackgroundFill(INCOMPLETE_TASK_COLOR, CORNER_RADII, INSETS)));
        run();
      });
      pop.getChildren().add(d);
      pop.getChildren().add(f);
    }
    Button g = new Button("Edit");
    g.setPrefSize(100, 50);
    if (className.equals(Task.class)) {
      g.setOnAction(event -> {
        popup.hide();
        SceneChanger.switchToScene("NewTask.fxml",
            new EditTaskController(journal, index), "Edit");
      });
    }
    if (className.equals(Event.class)) {
      g.setOnAction(event -> {
        popup.hide();
        SceneChanger.switchToScene("NewEvent.fxml",
            new EditEventController(journal, index), "Edit");
      });
    }
    pop.getChildren().add(g);
  }

  /**
   * Hides and deletes this task from the popup menu
   *
   * @param index         index to delete from
   * @param className     class of the Task/Event to delete
   */
  private void hideAndDelete(int index, Class<? extends Item> className) {
    popup.hide();
    if (className.equals(Task.class)) {
      journal.removeTask(index);
    } else if (className.equals(Event.class)) {
      journal.removeEvent(index);
    }

    run();
  }

  /**
   * Clears the dynamic board items
   */
  private void clearBoardVisual() {
    queue.getChildren().clear();
    monday.getChildren().clear();
    tuesday.getChildren().clear();
    wednesday.getChildren().clear();
    thursday.getChildren().clear();
    friday.getChildren().clear();
    saturday.getChildren().clear();
    sunday.getChildren().clear();
    border.setText("");
  }

  /**
   * When called, updates the statistics for this object's journal
   */
  private void updateStats() {
    int completeTasks = 0;
    int incompleteTasks = 0;
    int numEvents = journal.getEvents().size();

    for (Task t : journal.getTasks()) {
      if (t.getStatus().equals(CompletionStatus.COMPLETE)) {
        completeTasks++;
      } else {
        incompleteTasks++;
      }
    }
    double percent = 0;
    if (journal.getTasks().size() > 0) {
      percent = ((0.0 + completeTasks) / (completeTasks + incompleteTasks)) * 100;
    }
    String percentString = String.format("%.2f", percent);
    String statistics = "Percentage of Tasks Complete: " + percentString + "%"
        + "\nCompleted Tasks: " + completeTasks
        + "\nIncomplete Tasks: " + incompleteTasks
        + "\nEvents: this week: " + numEvents;

    stats.setText(statistics);
  }
}