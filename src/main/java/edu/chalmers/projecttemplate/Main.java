package edu.chalmers.projecttemplate;

import java.net.InetAddress;

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
		Server server = new Server(9876);
		server.start();
		new ClientController("127.0.0.1".getBytes());
		
		/*SwingUtilities.invokeLater(() -> {
                    final Project project = new Project();
                    final ProjectView projectView = new ProjectView(project);
                    
                    ProjectController.create(project, projectView);
                    projectView.setVisible(true);
                });
        */
	}
}
