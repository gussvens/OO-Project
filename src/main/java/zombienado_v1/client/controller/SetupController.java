package zombienado_v1.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import zombienado_v1.client.model.ServerCommunicator;
import zombienado_v1.server.Server;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.view.GameView;

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

    private ArrayList<String> maps;

    public void initialize() {
        maps = new ArrayList<String>();
        File folder = new File("src/main/resources/maps");
        for (final File map : folder.listFiles()) {
            maps.add(map.getName().substring(0, map.getName().length()-4));
        }
        map.getItems().addAll(maps);
        map.setValue(maps.get(0));
    }

    @FXML
    private void hostGame(ActionEvent event){
        String mapName = map.getValue().toString();
        Server server = Server.getInstance(Integer.parseInt(hostPort.getText()), mapName);
        server.start();

        try {
            join(InetAddress.getLocalHost(), Integer.parseInt(hostPort.getText()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void joinGame(ActionEvent event){
        try {
            join(InetAddress.getByName(joinIP.getText()), Integer.parseInt(joinPort.getText()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void join(InetAddress ip, int port){
        Model model = new Model();
        GameView view = new GameView(model);
        ServerCommunicator.create(ip, port);
        Controller controller = Controller.create(model, view);
        controller.start();
    }

}
