package mapeditor_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Martin-610 on 2016-05-22.
 */
public class Main extends Application{

    /**
     * Starts new stage
     * @param stage Input window
     * @throws Exception
     */
    @Override
    public void start(Stage stage){

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MapEditorStart.fxml"));

            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Zombienado: Map Editor");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            System.err.println("Caught IOException: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        launch(args);


    }

}

