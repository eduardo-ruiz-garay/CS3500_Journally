package cs3500.pa05.controller.popups;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Task;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A controller for modifying tasks. This class manages the modifications made to a task
 * within a given week model. It provides functionalities such as task update and deletion.
 */
public class TaskModificationController implements PopUpController {
  private final Week model;
  private final Task task;
  @FXML
  private ComboBox<DaysWeek> dayPickerTask;

  @FXML
  private ComboBox<Completion> completion;

  @FXML
  private Button createTask;

  @FXML
  private TextField taskName;

  @FXML
  private TextField taskDescription;

  @FXML
  private ComboBox<String> categoryPicker;
  @FXML
  private AnchorPane anchorPane;

  private AlertCreator alertController;

  private static final String taskBackgroundColor = "-fx-background-color: green;";

  private static final String buttonBackgroundColor = "-fx-background-color: red;";

  /**
   * Initializes a new TaskModificationController with the given week model and task.
   *
   * @param week The week model in which the task resides.
   * @param t    The task to be modified.
   */
  public TaskModificationController(Week week, Task t) {
    this.model = week;
    this.task = t;
  }

  /**
   * Initializes the controller by setting the values of the FXML fields to the
   * task's properties and adding an action event to the 'Update' and 'Delete' buttons.
   *
   * @param stage The stage on which the task modification view will be shown.
   */
  public void run(Stage stage) {
    this.dayPickerTask.getItems().addAll(DaysWeek.values());
    this.dayPickerTask.setValue(task.getDay());
    this.completion.getItems().addAll(Completion.values());
    this.completion.setValue(task.getCompletion());
    taskName.setText(task.getName());
    taskDescription.setText(task.getDescription());
    this.categoryPicker.getItems().addAll(this.model.getCategories().stream()
        .map(category -> category.getName())
        .collect(Collectors.toList()));
    if (!(task.getCategory() == null)) {
      this.categoryPicker.setValue(task.getCategory().getName());
    }
    this.createTask.setText("Update");
    createTask.setStyle(taskBackgroundColor);
    this.createTask.setOnAction(event -> handleUpdate(stage));
    Button button = new Button("Delete");
    button.setOnAction(event -> {
      this.model.removeTask(this.task, this.task.getDay().ordinal());
      stage.close();
    });
    button.setLayoutX(200);
    button.setLayoutY(190);
    button.setStyle(buttonBackgroundColor);
    anchorPane.getChildren().add(button);
  }

  /**
   * Handles the task update process. It does this by retrieving the user's input, validating it,
   * creating a new task with these details, removing the old task from the model and adding the
   * new one.
   *
   * @param taskStage The stage on which the task modification view is shown.
   */
  private void handleUpdate(Stage taskStage) {
    String taskNameText = taskName.getText();
    DaysWeek selectedDay = dayPickerTask.getSelectionModel().getSelectedItem();
    Completion selectedCompletion = completion.getSelectionModel().getSelectedItem();
    Category category = null;
    for (Category c : this.model.getCategories()) {
      if (!(categoryPicker.getSelectionModel().getSelectedItem() == null)) {
        if (c.getName().equals(categoryPicker.getSelectionModel().getSelectedItem())) {
          category = c;
        }
      }
    }
    if (taskNameText.isEmpty() || selectedDay == null || selectedCompletion == null) {
      alertController.showAlert("Missing Required Fields", "Please fill in all required fields.");
      return;
    }
    Task newTask = new Task(taskNameText, selectedDay, selectedCompletion,
        taskDescription.getText(), category);
    model.removeTask(task, task.getDay().ordinal());
    model.addTask(newTask, selectedDay.ordinal());
    taskStage.close();
  }
}
