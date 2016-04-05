package edu.chalmers.projecttemplate.controller;

import edu.chalmers.projecttemplate.model.Model;
import edu.chalmers.projecttemplate.view.GameView;

import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Controller extends Thread implements KeyListener{
	private final Model model;
	private final GameView gameView;
	private static final double targetFrameTime = 1d/60d;
	private static double frameTime = 0;
	
	private List<Character> pressedKeys;
	
	public static Controller create(Model project, GameView projectView) {
		return new Controller(project, projectView);
	}

	private Controller(Model model, GameView gameView) {
		this.model = model;
		this.gameView = gameView;
		pressedKeys = new ArrayList<Character>();
		gameView.addKeyListener(this);
	}
	
	public void run(){
		model.initialize();
		gameLoop();
	}

	private void gameLoop(){
		long wait;
		long startTime;
		while (true){
			startTime = System.nanoTime();
			model.tick(pressedKeys);	
			model.draw(gameView.getGraphicsBatch());
			gameView.render();
			wait = Math.max((long)(targetFrameTime*1000) - ((System.nanoTime() - startTime)/1000000), 0); //ms precision, can be improved
			
			try{
				Thread.sleep(wait); 
			} catch (InterruptedException ie){
				ie.printStackTrace();
			}
			frameTime = (System.nanoTime() - startTime) * Math.pow(10, -9);
		}
	}
	
	public static int getFramesPerSecond(){
		return (int)(1 / frameTime);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if (!pressedKeys.contains(ke.getKeyChar())){
			pressedKeys.add(ke.getKeyChar());
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		int index = pressedKeys.indexOf(ke.getKeyChar());
		pressedKeys.remove(index);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
