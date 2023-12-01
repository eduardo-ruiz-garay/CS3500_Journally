package cs3500.pa05.controller.popups;

import javafx.scene.control.Alert;

/**
 * Generates Alert PopUps
 */
public class AlertCreator {

  /**
   * Shows the alert
   *
   * @param title the alert title
   * @param message the alert message
   */
  public static void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
