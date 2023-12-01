package cs3500.pa05.controller.popups;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Creates the Controller for the categories in the week.
 */
public class CategoryController  implements PopUpController {
  private final Week model;
  @FXML
  private TextField category;
  @FXML
  private Button createCategory;

  /**
   * Constructor that initializes the CategoryController with the specified week model.
   *
   * @param week the week model to be managed by this controller.
   */
  public CategoryController(Week week) {
    this.model = week;
  }

  /**
   * Initializes the controller and sets up UI elements and event handlers.
   *
   * @param eventStage the stage on which the scene controlled by this controller is displayed.
   */
  @FXML
  public void run(Stage eventStage) {
    this.createCategory.setOnAction(event -> handleCreate(eventStage));
  }

  /**
   * Handles the creation of a new category when the create category button is clicked.
   *
   * @param eventStage the stage on which the scene controlled by this controller is displayed.
   */
  private void handleCreate(Stage eventStage) {
    model.addCategory(category.getText());
    this.category.clear();
    eventStage.close();
  }
}
