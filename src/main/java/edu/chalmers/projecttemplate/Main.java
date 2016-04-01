package edu.chalmers.projecttemplate;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import edu.chalmers.projecttemplate.controller.Client;
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
		//Server server = Server.getInstance();
		//server.setPort(9876);
		//server.start();
		try {
			Client.create(InetAddress.getByName("25.43.240.108"), 9876);
			Client client = Client.getInstance();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GameView view = new GameView(); 
		Model model = new Model();
		Controller controller = Controller.create(model, view);
		controller.start();
	}
}
