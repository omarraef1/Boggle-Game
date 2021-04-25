package view_controller;

// This is a event driven program with a graphical user interface 
// to play the game of Boggle. This code begins as the boilerplate
// code that is needed to start any JavaFX application.  It also
// has a recommended GridPane as the backing pane to store the
// DiceTray on the left and a GridPane with three elements on the right.
//
// @author Rick Mercer and Your Name
//
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BoggleMain extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private GridPane window = new GridPane();

  public void start(Stage stage) {
    setUpWindow();
    Scene scene = new Scene(window, 500, 300);
    stage.setScene(scene);
    stage.show();
  }

  private void setUpWindow() {
    window = new GridPane();
    // Add a label to column 0, row 0
    window.add(new Label(" left side      "), 0, 0);
    // Add a label to column 1, row 0
    window.add(new Label("             middle            "), 1, 0);
    // Add a label to column 2, row 0
    window.add(new Label("               right side"), 2, 0);
  }
}