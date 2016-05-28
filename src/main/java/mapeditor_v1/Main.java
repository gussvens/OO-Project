package mapeditor_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Martin-610 on 2016-05-22.
 */
public class Main extends Application{


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MapEditorStart.fxml"));

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Zombienado: Map Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);


    }

}

