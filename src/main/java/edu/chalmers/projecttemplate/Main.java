package edu.chalmers.projecttemplate;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main extends Application{


	@Override
	public void start(Stage stage) throws Exception {
		System.out.println(getClass().getResource("view/Setup.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Setup.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
