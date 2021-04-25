
// Omar R. Gebril 	SID 23323978 	CSc 210

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BoggleGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private Scanner dict;
	private BorderPane window = new BorderPane();
	private GridPane windowGrid = new GridPane();
	private TextField answers = new TextField("Enter Answer");
	private Label takeGuess = new Label("*Take a Guess*");
	private Label takeGuess2 = new Label("*ANY GUESS!*");
	private Label decision = new Label("<- Decision? ->");
	private Button newGame = new Button("New Game");
	private Button gameOver = new Button("Game Over");
	private String foundWords = "";
	private String inCorrect = "";
	private String possibleWords = "";
	private int possibleWordCount = 0;
	private int score = 0;

	DiceTray wt = new DiceTray();
	private Label tray = new Label(wt.toString());
	private Label trayTitle = new Label("~New_Tray~");

	@Override
	public void start(Stage stage) throws Exception {

		window.setCenter(windowGrid);

		Scene scene = new Scene(window, 400, 300);

		stage.setTitle("BOGGLE");

		windowGrid.setHgap(5);
		windowGrid.setVgap(5);
		answers.setMaxWidth(100.0);
		windowGrid.setStyle("-fx-background-color: #f5f5dc;");

		windowGrid.add(trayTitle, 12, 3);
		windowGrid.add(tray, 12, 4);
		windowGrid.add(answers, 12, 5);
		windowGrid.add(takeGuess, 6, 5);
		windowGrid.add(takeGuess2, 18, 5);
		windowGrid.add(newGame, 6, 7);
		windowGrid.add(decision, 12, 7);
		windowGrid.add(gameOver, 18, 7);

		stage.setScene(scene);
		stage.show();

		EventHandler<ActionEvent> textFieldHandler = new TextFieldHandler();
		answers.setOnAction(textFieldHandler);

		EventHandler<ActionEvent> buttonHandler = new ButtonHandler();
		newGame.setOnAction(buttonHandler);
		gameOver.setOnAction(buttonHandler);
	}

	private String possibleWords() throws FileNotFoundException {
		possibleWords = "";
		Scanner newDict = new Scanner(new File("BoggleWords.txt"));
		while (newDict.hasNext()) {
			String word = newDict.next().trim();
			if (DiceTray.foundInBoggleTray(word)) {
				possibleWords += (word + " ");
				possibleWordCount++;
			}
		}
		Scanner repeatedWords = new Scanner(possibleWords);
		while (repeatedWords.hasNext()) {
			String word2 = repeatedWords.next().trim();
			Scanner z = new Scanner(foundWords);
			while (z.hasNext()) {
				String word3 = z.next().trim();
				if (word3.equals(word2)) {
					possibleWords = possibleWords.replaceFirst((word2 + " "), "");
					possibleWordCount--;
				}
			}
		}
		return possibleWords;
	}

	private class TextFieldHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			String answer = answers.getText().trim();
			if (DiceTray.foundInBoggleTray(answer)) {
				boolean dictFlag = false;
				try {
					dict = new Scanner(new File("BoggleWords.txt"));
					while (dict.hasNext()) {
						if (dict.next().trim().equals(answer.trim().toLowerCase())) {
							dictFlag = true;
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Scanner x = new Scanner(foundWords);
				boolean flag = true;
				while (x.hasNext() && dictFlag == true) {
					if (answer.toLowerCase().equals(x.next().toLowerCase())) {
						flag = false;
					}
				}
				if (flag == true && dictFlag == true) {
					foundWords += (answer.toLowerCase() + " ");
					int lnth = answer.trim().length();
					if (lnth == 3 || lnth == 4) {
						score++;
					} else if (lnth == 5) {
						score += 2;
					} else if (lnth == 6) {
						score += 3;
					} else if (lnth == 7) {
						score += 5;
					} else if (lnth > 7) {
						score += 11;
					}
				}

				Scanner lastScanner = new Scanner(foundWords);
				boolean lastFlag = false;
				while (lastScanner.hasNext()) {
					if (lastScanner.next().trim().equals(answer.toLowerCase())) {
						lastFlag = true;
					}
				}

				if (lastFlag == false) {
					Scanner y = new Scanner(inCorrect);
					boolean flag2 = true;
					while (y.hasNext()) {
						if (answer.toLowerCase().equals(y.next().toLowerCase())) {
							flag2 = false;

						}
					}
					if (flag2 == true) {
						inCorrect += (answer.toLowerCase() + " ");
					}
				}
			} else {
				Scanner x = new Scanner(inCorrect);
				boolean flag = true;
				while (x.hasNext()) {
					if (answer.toLowerCase().equals(x.next().toLowerCase())) {
						flag = false;
					}
				}
				if (flag == true) {
					inCorrect += (answer.toLowerCase() + " ");
				}
			}
		}
	}

	private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource().equals(newGame)) {
				if (windowGrid.getStyle().equals("-fx-background-color: #f5f5dc;")
						|| windowGrid.getStyle().equals("-fx-background-color: #C0C0C0;")) {
					windowGrid.setStyle("-fx-background-color: #f0ffff;");
				} else if (windowGrid.getStyle().equals("-fx-background-color: #f0ffff;")) {
					windowGrid.setStyle("-fx-background-color: #f5f5dc;");
				}
				windowGrid.getChildren().remove(tray);
				wt = new DiceTray();
				tray = new Label(wt.toString());
				windowGrid.add(tray, 12, 4);
				foundWords = "";
				inCorrect = "";
				score = 0;
				possibleWordCount = 0;
				possibleWords = "";
			} else if (event.getSource().equals(gameOver)) {
				windowGrid.setStyle("-fx-background-color: #C0C0C0;");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game Information");
				alert.setHeaderText("Game Ended Mate!");
				try {
					String finalWords = possibleWords();
					alert.setContentText("Your Score: " + score + "\n\n" + "Words you found:\n" + foundWords.trim()
							+ "\n\n" + "Incorrect words:\n" + inCorrect.trim() + "\n\n" + "You could have found "
							+ possibleWordCount + " words:\n" + finalWords);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				alert.show();
			}
		}
	}
}
