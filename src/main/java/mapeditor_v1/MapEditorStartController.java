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

    private MapModel mapModel;

    private int width;
    private int height;
    private String name;

    @FXML private TextField heightField;
    @FXML private TextField widthField;
    @FXML private TextField nameField;
    @FXML private Label errorName;
    @FXML private Label errorWidth;
    @FXML private Label errorHeight;

    @Override
    public void initialize(URL url, ResourceBundle rb){


    }

    /**If no errors occurs, method creates a new model and sends model and the information inputted to a controller for the next scene.
     * Next scene is then shown.
     * @param event ActionEvent sent by clicking button
     * @throws IOException
     */
    @FXML
    private void doneButtonHandler(ActionEvent event) throws IOException{

        checkFields();
        if (errorName.getText().isEmpty() && errorWidth.getText().isEmpty() && errorHeight.getText().isEmpty()) {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
            name = nameField.getText();
            mapModel = new MapModel(name, width, height);


            MapEditorController mapEditorController = new MapEditorController(width, height, mapModel);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapEditor.fxml"));
            loader.setController(mapEditorController);
            Parent home_page_parent = loader.load();


            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.setResizable(true);
            app_stage.show();
        }
    }

    /**
     * Checks if textfields contains the requested information in a correct way,
     * otherwise displays error messages
     */
    private void checkFields(){
        errorHeight.setText("");
        errorWidth.setText("");
        errorName.setText("");

        if (heightField.getText().matches("[0-9]+") == false) {
            errorHeight.setText("Enter a number between 16 and 64");
        } else if(Integer.parseInt(heightField.getText()) < 16 || Integer.parseInt(heightField.getText()) > 64){
            errorHeight.setText("Enter a number between 16 and 64");
        }
        if (widthField.getText().matches("[0-9]+") == false) {
            errorWidth.setText("Enter a number between 16 and 64");
        }else if(Integer.parseInt(widthField.getText()) < 16 || Integer.parseInt(widthField.getText()) > 64){
            errorWidth.setText("Enter a number between 16 and 64");
        }
        if (nameField.getText().matches("[a-zA-Z]+") == false) {
            errorName.setText("Can only contain letters A-Z");
        }

    }
}
