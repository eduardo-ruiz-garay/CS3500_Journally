package cs3500.pa05.controller.popups;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for handling the saving of data to a file.
 * Allows the user to specify the path of the file where the data should be saved.
 */
public class SaveController implements PopUpController {
  @FXML
  private TextField pathText;
  @FXML
  private Button openFile;
  private String savePath;

  /**
   * Initializes the controller by setting up the UI components
   * and adding an event listener.
   *
   * @param scene The stage on which the save view will be shown.
   */
  public void run(Stage scene) {
    this.openFile.setText("Save");
    this.openFile.setOnAction(event -> handleOpenFile(scene));
  }

  /**
   * Handles the event when the "Open File" button is clicked.
   * Retrieves the user's input from the path text field and then closes the scene.
   *
   * @param scene The stage on which the save view is shown.
   */
  public void handleOpenFile(Stage scene) {
    this.savePath = this.pathText.getText();
    scene.close();
  }

  /**
   * Returns the path specified by the user where the data should be saved.
   *
   * @return The path to save the data to.
   */
  public String getSavePath() {
    return this.savePath;
  }
}
