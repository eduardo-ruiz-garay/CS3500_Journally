package cs3500.pa05.controller.popups;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Helps with polymorphism and creates the different controllers.
 */
public interface PopUpController {

  /**
   * Initializes and runs the controller, setting up the necessary UI components
   * and handling any user interaction.
   *
   * @param stage The stage on which the controller's view will be shown.
   */
  @FXML
  void run(Stage stage);
}
