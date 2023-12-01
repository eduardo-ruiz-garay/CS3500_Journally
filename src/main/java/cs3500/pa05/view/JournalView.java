package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * This class is responsible for loading a UI layout from a specified location.
 * It sets a provided JournalController as the controller for the loaded UI layout.
 */
public class JournalView {
  private final FXMLLoader loader;


  /**
   * Constructor for JournalView class.
   * Initializes a FXMLLoader and sets the provided JournalController as the controller for it.
   *
   * @param controller the JournalController to be used with this JournalView.
   */
  public JournalView(JournalController controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
  }

  /**
   * This method is responsible for loading a layout from the provided location.
   * The location is set as the FXMLLoader's location and the layout is loaded from there.
   *
   * @param location the location of the layout to be loaded as a String.
   * @return a Scene containing the loaded layout.
   */
  public Scene load(String location) {
    try {
      this.loader.setLocation(getClass().getClassLoader().getResource(location));
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout");
    }
  }
}
