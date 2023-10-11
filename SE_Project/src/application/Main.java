package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	TextArea txaMessage;
	TextField txfName;
	TextField txfPass;

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = buildGui();
			//BorderPane root = new BorderPane(); // Delete soon //
			Scene scene = new Scene(root,250,120);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("TBD");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private GridPane buildGui() {
		GridPane login = new GridPane();
		login.add(makeHBoxName(), 0, 0);
		login.add(makeHBoxPass(), 0, 1);
		login.add(makeHBoxButtons(), 0, 2);
		return login;
	}

	private Pane makeHBoxName() {
		Label lblName = new Label("Username:");
		txfName = new TextField();
		HBox hBoxName = new HBox();
		hBoxName.getChildren().addAll(lblName, txfName);
		return hBoxName;
	}

	private Pane makeHBoxPass() {
		Label lblPass = new Label(" Password:");
		txfPass = new TextField();
		HBox hBoxPass = new HBox();
		hBoxPass.getChildren().addAll(lblPass, txfPass);
		return hBoxPass;
	}

	private Pane makeHBoxButtons() {
		Button btnSignIn = new Button("Sign in");
		Button btnGuest = new Button("Continue as Guest");
		HBox hBoxButtons = new HBox();
		hBoxButtons.getChildren().addAll(btnSignIn, btnGuest);
		return hBoxButtons;
	}


	public static void main(String[] args) {
		launch(args);
	}
}