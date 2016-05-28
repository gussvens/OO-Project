package mapeditor_v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Martin-610 on 2016-05-28.
 */
public class MapEditorStartController implements Initializable {

    private Model model;

    private int width;
    private int height;
    private String name;

    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField nameField;

    @Override
    public void initialize(URL url, ResourceBundle rb){


    }

    @FXML
    private void doneButtonHandler(ActionEvent event) throws IOException{

        width = Integer.parseInt(widthField.getText());
        height = Integer.parseInt(heightField.getText());
        name = nameField.getText();
        model = new Model(name, width, height);



        MapEditorController mapEditorController = new MapEditorController(width, height, model);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MapEditor.fxml"));
        loader.setController(mapEditorController);

        Parent home_page_parent = loader.load();



        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
}
