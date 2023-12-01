package cs3500.pa05.controller.popups;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.file.managing.FileManager;
import cs3500.pa05.model.file.managing.JsonUtils;
import java.nio.file.Path;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for loading a file path to set the path for extracting a week.
 */
public class LoadController implements PopUpController {
  @FXML
  private TextField pathText;
  @FXML
  private Button openFile;
  private Week week;

  /**
   * Initializes a new LoadController with a new Week.
   */
  public LoadController() {
    this.week = new Week();
  }


  /**
   * Initializes the controller, setting up the action for the openFile button.
   *
   * @param stage The stage on which the controller's view will be shown.
   */
  public void run(Stage stage) {
    this.openFile.setOnAction(event -> handleOpenFile(stage));
  }

  /**
   * Method handler for opening .BuJo files
   *
   * @param stage scene
   */
  public void handleOpenFile(Stage stage) {
    String path = this.pathText.getText();
    String fileData = FileManager.readFile(Path.of(path));
    this.week = new JsonUtils().deserializeWeek(fileData);
    System.out.println(this.week.toString());
    stage.close();
  }

  /**
   * @return the updated week model
   */
  public Week getWeek() {
    return this.week;
  }
}
