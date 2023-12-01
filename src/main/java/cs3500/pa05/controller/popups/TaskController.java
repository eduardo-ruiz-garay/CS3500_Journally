package cs3500.pa05.controller.popups;

import static cs3500.pa05.controller.popups.AlertCreator.showAlert;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for creating and modifying tasks within a week.
 * It interacts with the task view and the model data to process task creation.
 */
public class TaskController implements PopUpController {
  private final Week model;
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
  private static final String eventBackgroundColor = "-fx-background-color: green;";



  /**
   * Initializes a new TaskController with the given week model.
   *
   * @param week The week model that this controller operates on.
   */
  public TaskController(Week week) {
    this.model = week;
  }


  /**
   * Initializes the controller by setting up the UI components
   * and adding event listeners to the relevant UI elements.
   *
   * @param taskStage The stage on which the task creation view will be shown.
   */
  @FXML
  public void run(Stage taskStage) {
    this.dayPickerTask.getItems().addAll(DaysWeek.values());
    this.completion.getItems().addAll(Completion.values());
    createTask.setStyle(eventBackgroundColor);
    for (int i = 0; i < this.model.getCategories().size(); i++) {
      this.categoryPicker.getItems().addAll(this.model.getCategories().get(i).getName());
    }
    this.createTask.setOnAction(event -> handleCreate(taskStage));
  }

  /**
   * Handles the task creation process by retrieving the user's input,
   * validating it, creating a new task with these details and adding it to the model.
   * Finally, it closes the stage.
   *
   * @param taskStage The stage on which the task creation view is shown.
   */
  private void handleCreate(Stage taskStage) {
    String taskNameText = taskName.getText();
    DaysWeek selectedDay = dayPickerTask.getSelectionModel().getSelectedItem();
    Completion selectedCompletion = completion.getSelectionModel().getSelectedItem();
    categoryChanger();
    if (taskNameText.isEmpty() || selectedDay == null || selectedCompletion == null) {
      showAlert("Missing Required Fields", "Please fill in all required fields.");
      return;
    }
    Task newTask = new Task(taskNameText, selectedDay, selectedCompletion,
        taskDescription.getText(), categoryChanger());
    model.addTask(newTask, selectedDay.ordinal());
    taskStage.close();
  }

  /**
   * Retrieves the category selected by the user from the category picker.
   * If no category is selected, null is returned.
   *
   * @return The selected category or null if none is selected.
   */
  private Category categoryChanger() {
    Category category = null;
    for (Category c : this.model.getCategories()) {
      if (c.getName().equals(categoryPicker.getSelectionModel().getSelectedItem())) {
        category = c;
      }
    }
    return category;
  }
}


