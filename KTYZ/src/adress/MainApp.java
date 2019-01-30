package adress;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			// Read file fxml and draw interface.
			Parent root = FXMLLoader.load(getClass().getResource("/adress/model/view/MainInterface.fxml"));

			primaryStage.setTitle("My Application");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}