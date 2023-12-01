package cs3500.pa05.controller;

import static cs3500.pa05.controller.popups.AlertCreator.showAlert;

import cs3500.pa05.controller.popups.CategoryController;
import cs3500.pa05.controller.popups.EventController;
import cs3500.pa05.controller.popups.EventModificationController;
import cs3500.pa05.controller.popups.LoadController;
import cs3500.pa05.controller.popups.SaveController;
import cs3500.pa05.controller.popups.TaskController;
import cs3500.pa05.controller.popups.TaskModificationController;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.file.managing.FileManager;
import cs3500.pa05.model.file.managing.JsonUtils;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Event;
import cs3500.pa05.model.objects.Task;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main controller that then abstracts to others through the command pattern.
 */

public class JournalController {
  private Week week;
  private ObservableList<String> tasks;
  @FXML
  private Button addEvent;
  @FXML
  private Button addTask;
  @FXML
  private Button addCategory;
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
  private TextField title;
  @FXML
  private Button save;
  @FXML
  private Button openFile;
  @FXML
  private VBox taskQueue;
  @FXML
  private ComboBox<Integer> maxTasks;
  @FXML
  private ComboBox<Integer> maxEvents;
  @FXML
  private VBox categoryList;
  @FXML
  private Label totalEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private Label completedTasks;
  @FXML
  private ListView<String> taskListView;
  @FXML
  private VBox searchTask;

  /**
   * @param week the week object to be used by the application. it contains all the data relevant to
   *             the user
   */
  public JournalController(Week week) {
    this.week = Objects.requireNonNull(week);
  }

  /**
   * The main entry point to the controller. it initializes all buttons and objects in the ui that
   * the user can interact with.
   */
  @FXML
  public void run() {
    this.addEvent.setOnAction(event -> handleAddEvent());
    this.addTask.setOnAction(event -> handleAddTask());
    this.addCategory.setOnAction(event -> handleAddCategory());
    this.title.setOnMouseClicked(event -> handleChangeTitle());
    this.save.setOnAction(event -> handleSave());
    this.openFile.setOnAction(event -> handleOpen());
    this.maxTasks.setItems(FXCollections.observableArrayList(createNumberList(50)));
    this.maxEvents.setItems(FXCollections.observableArrayList(createNumberList(50)));
    this.maxTasks.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue != null) {
        this.week.setMaxTasks(newValue);
      }
    });
    this.maxEvents.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue != null) {
        this.week.setMaxEvents(newValue);
      }
    });
    this.innitTaskSearch();
  }

  /**
   * it loads a new scene from which the user can enter a path to open a '.bujo' file. then it
   * waits for the user to input the path and opens the journal that the user previously saved.
   */
  @FXML
  public void handleOpen() {
    FXMLLoader loader = new FXMLLoader();
    LoadController controller = new LoadController();
    loader.setController(controller);
    loader.setLocation(getClass().getClassLoader().getResource("PathChooser.fxml"));
    Stage openEvent = new Stage();
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Could not load scene when opening file");
    }
    controller.run(openEvent);
    openEvent.setScene(scene);
    openEvent.showAndWait();
    this.week = controller.getWeek();
    this.updateJournalView();
  }

  /**
   * it loads a new scene from which the user can enter a path to save a '.bujo' file. then it
   * waits for the user to input the path and saves the data of the model in the file using JSON
   * format.
   */
  @FXML
  public void handleSave() {
    String toSave = new JsonUtils().seralizeWeek(this.week);
    FXMLLoader loader = new FXMLLoader();
    SaveController controller = new SaveController();
    loader.setController(controller);
    loader.setLocation(getClass().getClassLoader().getResource("PathChooser.fxml"));
    Stage openEvent = new Stage();
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Could not load scene when saving file");
    }
    controller.run(openEvent);
    openEvent.setScene(scene);
    openEvent.showAndWait();
    String savePath = controller.getSavePath();
    if (!(savePath == null)) {
      FileManager.writeFile(toSave, Path.of(savePath));
    }
  }

  /**
   * it allows the user to change the text field containing the title of the journal
   */
  @FXML
  public void handleChangeTitle() {
    this.title.setEditable(true);
    title.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable,
                          String oldValue,
                          String newValue) {
        week.setTitle(newValue);
      }
    });
  }

  /**
   * loads a new scene from which the user can create a new event. then it
   * waits for the user to create the event and updates the view once it is created.
   */
  @FXML
  public void handleAddEvent() {
    if (maxEvents.getValue() == null) {
      showAlert("Maximum Events Not Set",
          "Please set the maximum number of events before adding an event.");
      return;
    }
    int max = maxEvents.getValue();
    int currentTasks = week.getAllEvents().size();
    if (currentTasks >= max) {
      showAlert("Maximum Events Reached",
          "You have reached the maximum limit of " + max + " events for the week.");
      return;
    }
    FXMLLoader loader = new FXMLLoader();
    EventController controller = new EventController(this.week);
    loader.setController(controller);
    loader.setLocation(getClass().getClassLoader().getResource("NewEvent.fxml"));
    Stage newEvent = new Stage();
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("could not load scene when for adding event");
    }
    controller.run(newEvent);
    newEvent.setScene(scene);
    newEvent.showAndWait();
    this.updateJournalView();
  }

  /**
   * loads a new scene from which the user can create a new task. then it
   * waits for the user to create the task and updates the view once it is created.
   */
  @FXML
  public void handleAddTask() {
    if (maxTasks.getValue() == null) {
      showAlert("Maximum Tasks Not Set",
          "Please set the maximum number of tasks before adding a task.");
      return;
    }
    int max = maxTasks.getValue();
    int currentTasks = week.getAllTasks().size();
    if (currentTasks >= max) {
      showAlert("Maximum Tasks Reached",
          "You have reached the maximum limit of " + max + " tasks for the week.");
      return;
    }
    FXMLLoader loader = new FXMLLoader();
    TaskController taskController = new TaskController(this.week);
    loader.setController(taskController);
    loader.setLocation(getClass().getClassLoader().getResource("NewTask.fxml"));
    Stage newTask = new Stage();
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("could not load scene when for adding task");
    }
    taskController.run(newTask);
    newTask.setScene(scene);
    newTask.showAndWait();
    this.updateJournalView();
  }

  /**
   * loads a new scene from which the user can  create a new category. then it
   * waits for the user to create the category and updates the view once it is created.
   */
  @FXML
  public void handleAddCategory() {
    FXMLLoader loader = new FXMLLoader();
    CategoryController controller = new CategoryController(this.week);
    loader.setController(controller);
    loader.setLocation(getClass().getClassLoader().getResource("NewCategory.fxml"));
    Stage newCategory = new Stage();
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("could not load scene when for adding category");
    }
    controller.run(newCategory);
    newCategory.setScene(scene);
    newCategory.showAndWait();
    this.updateJournalView();
  }

  /**
   * loads a new scene from which the user can modify a selected task. then it
   * waits for the user to modify the task and updates the view once it is updated.
   *
   * @param t the task to be modified
   */
  private void handleTaskModification(Task t) {
    FXMLLoader loader = new FXMLLoader();
    TaskModificationController controller = new TaskModificationController(this.week, t);
    loader.setController(controller);
    loader.setLocation(getClass().getClassLoader().getResource("NewTask.fxml"));
    Stage stage = new Stage();
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("could not load scene when for task modification");
    }
    controller.run(stage);
    stage.setScene(scene);
    stage.showAndWait();
    this.updateJournalView();
  }

  /**
   * loads a new scene from which the user can modify a selected event. then it
   * waits for the user to modify the event and updates the view once it is updated.
   *
   * @param event the event to be modified
   */
  private void handleEventModification(Event event) {
    FXMLLoader loader = new FXMLLoader();
    EventModificationController controller = new EventModificationController(this.week, event);
    loader.setController(controller);
    loader.setLocation(getClass().getClassLoader().getResource("NewEvent.fxml"));
    Stage stage = new Stage();
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Could not add event");
    }
    controller.run(stage);
    stage.setScene(scene);
    stage.showAndWait();
    this.updateJournalView();
  }

  /**
   * clears the journal view, and then it updates it with all the data that the model contains at
   * that particular point in time.
   */
  @FXML
  public void updateJournalView() {
    for (DaysWeek d : DaysWeek.values()) {
      ArrayList<TextArea> dayData = this.updateDayData(d);
      if (d.equals(DaysWeek.MONDAY)) {
        this.monday.getChildren().clear();
        this.monday.getChildren().addAll(dayData);
      } else if (d.equals(DaysWeek.TUESDAY)) {
        this.tuesday.getChildren().clear();
        this.tuesday.getChildren().addAll(dayData);
      } else if (d.equals(DaysWeek.WEDNESDAY)) {
        this.wednesday.getChildren().clear();
        this.wednesday.getChildren().addAll(dayData);
      } else if (d.equals(DaysWeek.THURSDAY)) {
        this.thursday.getChildren().clear();
        this.thursday.getChildren().addAll(dayData);
      } else if (d.equals(DaysWeek.FRIDAY)) {
        this.friday.getChildren().clear();
        this.friday.getChildren().addAll(dayData);
      } else if (d.equals(DaysWeek.SATURDAY)) {
        this.saturday.getChildren().clear();
        this.saturday.getChildren().addAll(dayData);
      } else {
        this.sunday.getChildren().clear();
        this.sunday.getChildren().addAll(dayData);
      }
    }
    this.title.setText(this.week.getTitle());
    this.maxTasks.setValue(this.week.getMaxTasks());
    this.maxEvents.setValue(this.week.getMaxEvents());
    this.updateTaskQueue();
    this.updateCategoryList();
    this.updateStatistics();
    this.innitTaskSearch();
  }

  /**
   * updates the journal statistics with all the data in the model at this particular point in time
   */
  private void updateStatistics() {
    int eventNum = this.week.getAllEvents().size();
    String eventStatistics = "Total Events: " + eventNum;
    totalEvents.setText(eventStatistics);
    int taskNum = this.week.getAllTasks().size();
    String taskStatistics = "Total Tasks: " + taskNum;
    totalTasks.setText(taskStatistics);
    int completedTaskNum = 0;
    for (Task t : this.week.getAllTasks()) {
      if (t.getCompletion().equals(Completion.COMPLETE)) {
        completedTaskNum++;
      }
    }
    String completedTasks = "Completed Tasks: " + completedTaskNum;
    this.completedTasks.setText(completedTasks);
  }

  /**
   * updates the list of categories with all the data in the model at this particular point in time
   */
  private void updateCategoryList() {
    this.categoryList.getChildren().clear();
    for (Category c : this.week.getCategories()) {
      TextField field = new TextField(c.getName());
      String colorSetter = "-fx-control-inner-background: "
          + c.getColor().toString().toLowerCase();
      field.setStyle(colorSetter);
      field.setEditable(false);
      this.categoryList.getChildren().add(field);
    }
  }

  /**
   * updates the task queue with all the data in the model at this particular point in time
   */
  private void updateTaskQueue() {
    this.taskQueue.getChildren().clear();
    for (Task t : this.week.getAllTasks()) {
      Label taskLabel = new Label("- " + t.getName() + " -> " + t.getCompletion().toString());
      taskLabel.setWrapText(true);
      this.taskQueue.getChildren().add(taskLabel);
    }
  }

  /**
   * Processes the events and tasks of a specific day and returns them as an ArrayList of TextAreas.
   *
   * @param day The day to process.
   * @return An ArrayList of TextAreas representing the events and tasks of the day.
   */
  private ArrayList<TextArea> updateDayData(DaysWeek day) {
    ArrayList<TextArea> fields = new ArrayList<>();
    fields.addAll(processEvents(day));
    fields.addAll(processTasks(day));
    return fields;
  }

  /**
   * Processes the events of a specific day and returns them as an ArrayList of TextAreas.
   *
   * @param day The day to process.
   * @return An ArrayList of TextAreas representing the events and tasks of the day.
   */
  private ArrayList<TextArea> processEvents(DaysWeek day) {
    ArrayList<TextArea> fields = new ArrayList<>();
    for (Event e : this.week.getEventInDay(day)) {
      fields.add(createEventTextArea(e));
    }
    return fields;
  }

  /**
   * Processes the tasks of a specific day and returns them as an ArrayList of TextAreas.
   *
   * @param day The day to process.
   * @return An ArrayList of TextAreas representing the tasks of the day.
   */
  private ArrayList<TextArea> processTasks(DaysWeek day) {
    ArrayList<TextArea> fields = new ArrayList<>();
    for (Task t : this.week.getTasksInDay(day)) {
      fields.add(createTaskTextArea(t));
    }
    return fields;
  }

  /**
   * Creates a TextArea that represents the specified event.
   *
   * @param e The event to represent.
   * @return A TextArea representing the event.
   */
  private TextArea createEventTextArea(Event e) {
    Appendable field = new StringBuilder();
    String event = "Event:\n";
    try {
      field.append(event);
      field.append("Name: ").append(e.getName() + System.lineSeparator());
      field.append("Description: ").append(e.getDescription() + System.lineSeparator());
      field.append("Start time: ").append(e.getStartTime() + System.lineSeparator());
      field.append("Duration: ").append(e.getDuration() + System.lineSeparator());

      TextArea area = new TextArea(field.toString());
      area.setOnMouseClicked(click -> handleEventModification(e));
      styleTextArea(area, e.getCategory());
      return area;
    } catch (IOException err) {
      System.err.println("could not append event data");
      return null;
    }
  }

  /**
   * Creates a TextArea that represents the specified task.
   *
   * @param t The event to represent.
   * @return A TextArea representing the event.
   */
  private TextArea createTaskTextArea(Task t) {
    Appendable field = new StringBuilder();
    String task = "Task:" + System.lineSeparator();
    try {
      field.append(task);
      field.append("Name: ").append(t.getName() + System.lineSeparator());
      field.append("Description: ").append(t.getDescription() + System.lineSeparator());
      field.append("Completion: ").append(t.getCompletion().toString() + System.lineSeparator());

      TextArea area = new TextArea(field.toString());
      area.setOnMouseClicked(click -> handleTaskModification(t));
      styleTextArea(area, t.getCategory());
      return area;
    } catch (IOException err) {
      System.err.println(err.getStackTrace());
      return null;
    }
  }

  /**
   * Styles the color of the specified TextArea based on the specified category.
   *
   * @param area The TextArea to style.
   * @param category The category to base the styling on.
   */
  private void styleTextArea(TextArea area, Category category) {
    if (category != null) {
      String colorSetter = "-fx-control-inner-background: "
          + category.getColor().toString().toLowerCase();
      area.setStyle(colorSetter);
    }
    area.setWrapText(true);
    area.setEditable(false);
  }

  /**
   * Creates a List of Integers from 1 to the provided maxValue.
   *
   * @param maxValue The maximum value in the List.
   * @return A List of Integers from 1 to maxValue.
   */
  private static List<Integer> createNumberList(int maxValue) {
    List<Integer> numberList = new ArrayList<>();
    for (int i = 1; i <= maxValue; i++) {
      numberList.add(i);
    }
    return numberList;
  }

  /**
   * Initializes the task search field so that tasks can be searched by name and displayed bellow
   * the searchTask text field.
   */
  public void innitTaskSearch() {
    this.tasks = FXCollections.observableArrayList();
    this.searchTask.getChildren().clear();
    TextField searchField = new TextField();
    this.searchTask.getChildren().add(searchField);
    this.searchTask.getChildren().add(this.taskListView);

    String grayBorderStyle = "-fx-border-color: gray;";
    searchField.setStyle(grayBorderStyle);

    List<String> taskNames = this.week.getAllTasks().stream().map(
        t -> t.getName()).collect(Collectors.toList());
    this.tasks.addAll(taskNames);

    searchField.setPromptText("Search tasks...");
    FilteredList<String> filteredTasks = new FilteredList<>(this.tasks, p -> true);
    this.taskListView.setItems(filteredTasks);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredTasks.setPredicate(taskName -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }
        String lowerCaseFilter = newValue.toLowerCase();
        return taskName.toLowerCase().contains(lowerCaseFilter);
      });
    });
  }
}
