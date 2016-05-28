package zombienado_beta.launcher;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import zombienado_beta.client.controller.Controller;
import zombienado_beta.client.model.ServerCommunicator;
import zombienado_beta.server.Server;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

import zombienado_beta.client.model.Model;
import zombienado_beta.client.view.GameView;

/**
 * Created by Marcus on 2016-04-01.
 */
public class SetupController{

    @FXML
    private TextField hostPort;

    @FXML
    private TextField joinPort;

    @FXML
    private TextField joinIP;

    @FXML
    private Button joinButton;

    @FXML
    private Button hostButton;

    @FXML
    private ComboBox map;

    @FXML
    private Label lblPort;

    @FXML
    private Label lblJoinPort;

    @FXML
    private Label lblJoinIP;

    private ArrayList<String> maps;

    /**
     * A method that initializes the combobox with all the maps stored
     */
    public void initialize() {
        maps = new ArrayList<String>();
        File folder = new File("src/main/resources/maps");
        for (final File map : folder.listFiles()) {
            maps.add(map.getName().substring(0, map.getName().length()-4));
        }
        map.getItems().addAll(maps);
        map.setValue(maps.get(0));

        //To restrict input on port
        hostPort.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 4){
                    hostPort.setText(oldValue);
                }
                if (!newValue.matches("\\d*")) {
                    hostPort.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        //To restrict input on port
        joinPort.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 4){
                    joinPort.setText(oldValue);
                }
                if (!newValue.matches("\\d*")) {
                    joinPort.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    /**
     * A method that starts a Server on the port and map selected by the user
     * @param event - A button press
     */
    @FXML
    private void hostGame(ActionEvent event){
        if(hostPort.getText().equals("")){
            lblPort.setTextFill(Color.RED);
        } else {
            String mapName = map.getValue().toString();
            Server server = Server.getInstance(Integer.parseInt(hostPort.getText()), mapName);
            server.start();

            try {
                join(InetAddress.getLocalHost(), Integer.parseInt(hostPort.getText()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A method that checks if all the textfields are correctly filled
     * @param event - A button press
     */
    @FXML
    private void joinGame(ActionEvent event){
        boolean ready = true;
        if(joinPort.getText().equals("")) {
            lblJoinPort.setTextFill(Color.RED);
            ready = false;
        } else {
            lblJoinPort.setTextFill(Color.BLACK);
        }

        if (joinIP.getText().equals("")) {
            lblJoinIP.setTextFill(Color.RED);
            ready = false;
        } else {
            lblJoinIP.setTextFill(Color.BLACK);
        }

        if (ready){
            try {
                join(InetAddress.getByName(joinIP.getText()), Integer.parseInt(joinPort.getText()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A method that creates a Model, View and Controller for a client that connects to a Server
     * @param ip - The IP adress to the Server
     * @param port - The port which will communicate with the Server
     */
    public void join(InetAddress ip, int port){
        Model model = new Model();
        GameView view = new GameView(model);
        ServerCommunicator.create(ip, port);
        Controller controller = Controller.create(model, view);
        controller.start();
    }

}
