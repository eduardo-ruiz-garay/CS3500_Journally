package cs3500.pa05.controller.popups;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Event;
import cs3500.pa05.view.TimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Creates the event modifier controller that controls the events when they are altered.
 */
public class EventModificationController implements PopUpController {
  private final Week model;
  private final Event event;
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
  @FXML
  private AnchorPane anchorPane;

  private AlertCreator alertController;
  private static final String eventBackgroundColor = "-fx-background-color: lightgreen;";

  private static final String buttonBackgroundColor = "-fx-background-color: red;";

  private static final int buttonLayoutX = 50;
  private static final int buttonLayoutY = 233;

  public EventModificationController(Week week, Event event) {
    this.model = week;
    this.event = event;
  }

  /**
   * initializes the controller to update an event
   *
   * @param stage stage
   */
  public void run(Stage stage) {
    this.dayPickerEvent.getItems().addAll(DaysWeek.values());
    this.eventName.setText(event.getName());
    this.eventDescription.setText(event.getDescription());
    this.startTime.setText(event.getStartTime());
    this.endTime.setText(event.getDuration());
    for (int i = 0; i < this.model.getCategories().size(); i++) {
      this.categoryPickerEvent.getItems().addAll(this.model.getCategories().get(i).getName());
    }
    if (!(event.getCategory() == null)) {
      this.categoryPickerEvent.setValue(event.getCategory().getName());
    }
    this.dayPickerEvent.setValue(event.getDay());
    this.createEvent.setText("Update");
    this.createEvent.setOnAction(event -> handleUpdate(stage));
    createEvent.setStyle(eventBackgroundColor);
    Button button = new Button("Delete");
    button.setOnAction(event -> {
      this.model.removeEvent(this.event, this.event.getDay().ordinal());
      stage.close();
    });
    button.setLayoutX(buttonLayoutX);
    button.setLayoutY(buttonLayoutY);
    button.setStyle(buttonBackgroundColor);
    anchorPane.getChildren().add(button);
  }

  /**
   * Method handler to update an event
   *
   * @param eventStage stage
   */
  public void handleUpdate(Stage eventStage) {
    String eventName = this.eventName.getText();
    DaysWeek selectedDay = dayPickerEvent.getSelectionModel().getSelectedItem();
    String startTimeText = startTime.getTime();
    String endTimeText = endTime.getTime();
    Category category = null;
    for (Category c : this.model.getCategories()) {
      if (!(categoryPickerEvent.getSelectionModel().getSelectedItem() == null)) {
        if (c.getName()
            .equals(categoryPickerEvent.getSelectionModel().getSelectedItem())) {
          category = c;
        }
      }
    }
    if (eventName.isEmpty()
        || selectedDay == null
        || startTimeText.isEmpty()
        || endTimeText.isEmpty()) {
      alertController.showAlert("Missing Required Fields",
          "Please fill in all required fields.");
      return;
    }
    Event newEvent = new Event(eventName,
        selectedDay,
        eventDescription.getText(),
        startTimeText,
        endTimeText,
        category);
    model.removeEvent(event, event.getDay().ordinal());
    model.addEvent(newEvent, selectedDay.ordinal());
    this.eventName.clear();
    this.eventDescription.clear();
    this.startTime.clear();
    this.endTime.clear();
    eventStage.close();
  }
}
