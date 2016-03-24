package edu.chalmers.projecttemplate.controller;

import edu.chalmers.projecttemplate.model.Model;
import edu.chalmers.projecttemplate.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends Thread{
	private final Model model;
	private final GameView gameView;

	public static Controller create(Model project, GameView projectView) {
		return new Controller(project, projectView);
	}

	private Controller(Model model, GameView gameView) {
		this.model = model;
		this.gameView = gameView;
	}
	
	public void run(){
		model.initialize();
		gameLoop();
	}

	private void gameLoop(){
		long wait = (long)(1000/60);
		System.out.println(wait + "");
		while (true){
			model.tick();	
			model.draw(gameView.getGraphicsBatch());
			try{
				Thread.sleep(wait); //Not the final solution (no pun intended)
			} catch (InterruptedException ie){
				ie.printStackTrace();
			}
			gameView.repaint();
		}
	}
	
	
}
