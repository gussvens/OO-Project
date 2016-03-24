package edu.chalmers.projecttemplate;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import edu.chalmers.projecttemplate.controller.ClientController;
import edu.chalmers.projecttemplate.controller.Controller;
import edu.chalmers.projecttemplate.model.Model;
import edu.chalmers.projecttemplate.view.GameView;

import javax.swing.SwingUtilities;

import server.Server;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main {
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		//Server server = new Server(9876);
		//server.start();
		//ClientController client = new ClientController();
		GameView view = new GameView(); 
		Model model = new Model();
		Controller controller = Controller.create(model, view);
		controller.start();
		/*

		try {
			client.setupClient(InetAddress.getByName("25.116.78.28"), 9876);
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
		*/
	}
}
