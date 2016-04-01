package edu.chalmers.projecttemplate;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main extends Application{


	@Override
	public void start(Stage stage) throws Exception {
		System.out.println(getClass().getClassLoader().getResource("../../../../resources/Setup.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/Setup.fxml"));
		Scene scene = new Scene(root, 860, 600);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
