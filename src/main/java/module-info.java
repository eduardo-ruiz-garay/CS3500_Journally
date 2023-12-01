module cs3500.pa05 {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.fasterxml.jackson.databind;

  opens cs3500.pa05.model to com.fasterxml.jackson.databind;
  opens cs3500.pa05.controller to javafx.fxml;

  exports cs3500.pa05;
  exports cs3500.pa05.controller;
  exports cs3500.pa05.model;
  exports cs3500.pa05.view;
  exports cs3500.pa05.model.objects;
  opens cs3500.pa05.model.objects to com.fasterxml.jackson.databind;
  exports cs3500.pa05.model.enums;
  opens cs3500.pa05.model.enums to com.fasterxml.jackson.databind;
  exports cs3500.pa05.model.file.managing;
  opens cs3500.pa05.model.file.managing to com.fasterxml.jackson.databind, javafx.fxml;
  exports cs3500.pa05.controller.popups;
  opens cs3500.pa05.controller.popups to javafx.fxml;
}
