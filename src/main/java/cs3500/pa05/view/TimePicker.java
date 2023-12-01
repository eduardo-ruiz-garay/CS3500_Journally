package cs3500.pa05.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * This class extends TextField and is used to input and validate a time in the format HH:MM as a
 * String.
 */
public class TimePicker extends TextField {

  /**
   * Default constructor that initializes the TimePicker with a prompt text and adds required
   * listeners.
   */
  public TimePicker() {
    this.setPromptText("HH:MM");
    this.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String oldValue,
                          String newValue) {
        if (!newValue.matches("\\d{0,2}(:\\d{0,2})?")) {
          setText(oldValue);
        } else if (newValue.length() == 2 && oldValue.length() != 3) {
          setText(newValue + ":");
        } else if (newValue.length() > 5) {
          setText(oldValue);
        }
      }
    });
    this.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue) {
        final int size = getText().length();
        if (size <= 0) {
          this.setText("00:00");
        } else if (size <= 2) {
          this.setText(this.getText() + ":00");
        } else if (size <= 3) {
          this.setText(this.getText() + "00");
        } else if (size <= 4) {
          this.setText(this.getText() + "0");
        }
        validateTimeFormat();
      }
    });
  }

  /**
   * This method validates the time format entered in the text field.
   * If the format is incorrect, an error alert will be shown to the user.
   */
  private void validateTimeFormat() {
    String[] timeParts = this.getText().split(":");
    int hours = Integer.parseInt(timeParts[0]);
    int minutes = Integer.parseInt(timeParts[1]);

    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Invalid Time");
      alert.setHeaderText("Invalid Time Format");
      alert.setContentText("Please enter a valid time in 24-hour format (HH:MM).");
      alert.showAndWait();

      this.setText("");
    }
  }

  /**
   * Method to retrieve the time input from the text field.
   *
   * @return the time as a String in HH:MM format.
   */
  public String getTime() {
    String timeParts = this.getText();
    return timeParts;
  }
}


