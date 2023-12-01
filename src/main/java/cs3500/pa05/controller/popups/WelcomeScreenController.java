package cs3500.pa05.controller.popups;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A controller for the WelcomeScreen.fxml. This class is responsible for handling the logic behind
 * the WelcomeScreen view.It maintains a scene and a boolean to keep track of whether the password
 * screen has been shown.
 */
public class WelcomeScreenController {
  @FXML
  Scene scene;
  private boolean passwordShown = false;

  /**
   * Initializes the welcome screen controller by setting an on-click event to the scene and
   * starting a timeline that shows the password screen after 2000 milliseconds
   * if it hasn't been shown yet.
   *
   * @param stage The stage to be used for showing the password screen and used by the welcome
   *              screen.
   */
  @FXML
  public void run(Stage stage) {
    this.scene.setOnMouseClicked(event -> handleClick(stage));
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), event -> {
      if (!passwordShown) {
        showPassword(stage);
        passwordShown = true;
      }
    }));
    timeline.play();
  }

  /**
   * Handles the scene click event. If the password screen has not been shown yet, it will show
   * the password screen.
   *
   * @param stage The stage to be used for showing the password screen.
   */
  private void handleClick(Stage stage) {
    if (!passwordShown) {
      showPassword(stage);
      passwordShown = true;
    }
  }

  /**
   * Shows the password screen. It does this by loading the PasswordScreen.fxml
   * and setting it on the given stage.
   *
   * @param stage The stage on which the password screen will be shown.
   */
  private void showPassword(Stage stage) {
    stage.close();
    FXMLLoader loader = new FXMLLoader();
    PasswordScreenController controller = new PasswordScreenController();
    loader.setController(controller);
    loader.setLocation(getClass().getClassLoader().getResource("PasswordScreen.fxml"));
    Scene scene = null;
    try {
      scene = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Could not load Password Screen");
    }
    controller.run(stage);
    stage.setScene(scene);
    stage.show();
  }
}

