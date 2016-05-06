package zombienado_v1.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import zombienado_v1.client.model.ServerCommunicator;
import zombienado_v1.server.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.view.GameView;

/**
 * Created by Marcus on 2016-04-01.
 */
public class SetupController {

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
    private void hostGame(ActionEvent event){
        Server server = Server.getInstance();
        server.setPort(Integer.parseInt(hostPort.getText()));
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
        ServerCommunicator.create(model, ip, port);
        //Client.create(model, ip, port); TODO: maybe remove?
        Controller controller = Controller.create(model, view);
        controller.start();
    }

}
