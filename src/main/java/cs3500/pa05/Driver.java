package cs3500.pa05;

import cs3500.pa05.controller.popups.WelcomeScreenController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class that drives the application. It creates a new JournalController and JournalView,
 * sets the scene for the stage, and then shows the stage.
 */
public class Driver extends Application {
  /**
   * Starts the JavaFX application. Creates a new JournalController with a new Week,
   * creates a new JournalView with the controller, and sets the scene for the stage with the view.
   * It then runs the controller, shows the stage, and updates the view.
   *
   * @param stage The primary stage for this application, onto which
   *              the application scene can is set.
   */
  @Override
  public void start(Stage stage) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getClassLoader().getResource("WelcomeScreen.fxml"));
    WelcomeScreenController controller = new WelcomeScreenController();
    loader.setController(controller);
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Could not load Welcome Screen");
    }
    controller.run(stage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * The main method that launches the JavaFX application.
   *
   * @param args Command line arguments, not used in this application.
   */
  public static void main(String[] args) {
    launch();
  }
}