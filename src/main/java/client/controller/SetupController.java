package client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import server.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import client.model.Model;
import client.view.GameView;

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

        GameView view = new GameView();
        Model model = new Model();
        
        try {
            Client.create(model, InetAddress.getLocalHost(), Integer.parseInt(hostPort.getText()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Controller controller = Controller.create(model, view);
        controller.start();
    }

    @FXML
    private void joinGame(ActionEvent event){

        GameView view = new GameView();
        Model model = new Model();
        
        try {
            Client.create(model, InetAddress.getByName(joinIP.getText()), Integer.parseInt(joinPort.getText()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Controller controller = Controller.create(model, view);
        controller.start();
    }

}
