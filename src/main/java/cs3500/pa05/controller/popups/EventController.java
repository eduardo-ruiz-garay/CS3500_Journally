package cs3500.pa05.controller.popups;

import static cs3500.pa05.controller.popups.AlertCreator.showAlert;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Event;
import cs3500.pa05.view.TimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Creates the event controller for the fxml page.
 */
public class EventController implements PopUpController {
  private final Week model;
  @FXML
  private TextField eventName;
  @FXML
  private TextField eventDescription;
  @FXML
  private TimePicker startTime;
  @FXML
  private TimePicker endTime;
  @FXML
  private ComboBox<DaysWeek> dayPickerEvent;
  @FXML
  private Button createEvent;
  @FXML
  private ComboBox<String> categoryPickerEvent;
  protected static final String eventBackgroundColor = "-fx-background-color: green;";


  /**
   * Constructor for the controller
   *
   * @param week week
   */
  public EventController(Week week) {
    this.model = week;
  }

  /**
   * Initializes the controller to create events
   *
   * @param eventStage stage
   */
  @FXML
  public void run(Stage eventStage) {
    this.dayPickerEvent.getItems().addAll(DaysWeek.values());
    for (int i = 0; i < this.model.getCategories().size(); i++) {
      this.categoryPickerEvent.getItems().addAll(this.model.getCategories().get(i).getName());
    }
    this.createEvent.setStyle(eventBackgroundColor);
    this.createEvent.setOnAction(event -> handleCreate(eventStage));
  }

  /**
   * Handles the creation of a new event when the create event button is clicked.
   *
   * @param eventStage the stage on which the scene controlled by this controller is displayed.
   */
  public void handleCreate(Stage eventStage) {
    String eventName = this.eventName.getText();
    DaysWeek selectedDay = dayPickerEvent.getSelectionModel().getSelectedItem();
    String startTimeText = startTime.getTime();
    String endTimeText = endTime.getTime();
    if (eventName.isEmpty()
        || selectedDay == null
        || startTimeText.isEmpty()
        || endTimeText.isEmpty()) {
      showAlert("Missing Required Fields", "Please fill in all required fields.");
      return;
    }
    Event newEvent = new Event(eventName, selectedDay, eventDescription.getText(),
        startTimeText, endTimeText, this.categoryFinder());
    model.addEvent(newEvent, selectedDay.ordinal());
    this.eventName.clear();
    this.eventDescription.clear();
    this.startTime.clear();
    this.endTime.clear();
    eventStage.close();
  }

  /**
   * Searches for a category in the model with the specified name and returns it.
   * If no such category exists, null is returned.
   *
   * @return the category with the specified name, or null if no such category exists.
   */
  private Category categoryFinder() {
    Category category = null;
    for (Category c : this.model.getCategories()) {
      if (c.getName().equals(categoryPickerEvent.getSelectionModel().getSelectedItem())) {
        category = c;
        break;
      }
    }
    return category;
  }
}
