package cs3500.pa05.controller.popups;

import static cs3500.pa05.controller.popups.AlertCreator.showAlert;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.JournalView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The controller for the PasswordScreen.fxml file.
 * Manages the password authentication process.
 */
public class PasswordScreenController {
  String hardCodedPassword = "OOD";
  @FXML
  PasswordField password;
  @FXML
  Button authenticate;

  /**
   * Initializes the controller, setting up the action for the authentication button.
   *
   * @param stage The stage on which the controller's view will be shown.
   */
  public void run(Stage stage) {
    authenticate.setOnAction(event -> handleAuthenticate(stage));
  }

  /**
   * Handles the authentication process when the button is clicked.
   * If the password entered is correct, it closes the current stage and shows the JournalView.
   * If the password is incorrect, it shows an alert with an error message.
   *
   * @param stage The stage on which the controller's view is shown.
   */
  private void handleAuthenticate(Stage stage) {
    if (this.password.getText().equals(hardCodedPassword)) {
      stage.close();
      showJournalView(stage);
    } else {
      showAlert("Incorrect Password", "The password you entered is incorrect.");
    }
  }

  /**
   * Displays the JournalView stage after successful authentication.
   *
   * @param stage The stage on which the JournalView will be shown.
   */
  private void showJournalView(Stage stage) {
    JournalController controller = new JournalController(new Week());
    JournalView view = new JournalView(controller);
    stage.setScene(view.load("JournalScene.fxml"));
    controller.run();
    stage.show();
    controller.updateJournalView();
  }
}
