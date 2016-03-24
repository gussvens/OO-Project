package edu.chalmers.projecttemplate;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import edu.chalmers.projecttemplate.controller.ClientController;
import edu.chalmers.projecttemplate.controller.ProjectController;
import edu.chalmers.projecttemplate.model.Project;
import edu.chalmers.projecttemplate.view.ProjectView;

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
		ClientController controller = new ClientController();

		try {
			controller.setupClient(InetAddress.getByName("25.43.240.108"), 9876);
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
	}
}
