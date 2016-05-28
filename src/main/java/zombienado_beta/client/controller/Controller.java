package zombienado_beta.client.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import zombienado_beta.client.model.Model;
import zombienado_beta.client.view.GameView;

/**
 * A class that handles the pipelined background process of the game, and listens for user input
 */
public class Controller extends Thread implements KeyListener, MouseMotionListener, MouseListener{
	private final Model model;
	private final GameView gameView;
	private static final double TARGET_FRAME_TIME = 1d/60d;
	
	private static List<Character> pressedKeys;
	private static boolean mousePress = false;
	private static Point cursor = new Point(0, 0);


	/**
	 * @param model
	 * @param gameView
     * @return the controller for model & view
     */
	public static Controller create(Model model, GameView gameView) {
		return new Controller(model, gameView);
	}

	private Controller(Model model, GameView gameView) {
		this.model = model;
		this.gameView = gameView;
		pressedKeys = new ArrayList<Character>();
		gameView.addKeyListener(this);
		gameView.addMouseListener(this);
		gameView.addMouseMotionListener(this);
	}

	/**
	 * Starts the game flow on a new thread
	 */
	public void run(){
		model.initialize(GameView.getScreenWidth(), GameView.getScreenHeight());
		gameView.load();
		gameLoop();
	}

	/**
	 * Starts the actual game loop, which sends update calls to the model, and render calls to the view
	 */
	private void gameLoop(){

		long startTime;
		float deltaTime = 1;
		while (true){
			startTime = System.nanoTime();
			//Does the work
			model.tick(deltaTime, pressedKeys, cursor, mousePress);
			gameView.loadMap();
			gameView.render();
			//Calculates sleep time
			long elapsedTimeInNano = (System.nanoTime() - startTime);
			long totalDifferenceInNano = (long)(TARGET_FRAME_TIME*1000000000 - elapsedTimeInNano);
			long differenceInMillis = totalDifferenceInNano/1000000;
			long differenceInNano = totalDifferenceInNano - differenceInMillis*1000000;
			long waitMillis = Math.max((long)(differenceInMillis), 0);
			int waitNano = (int)Math.max(differenceInNano, 0);
			//Sleeps the desired time
			try{
				Thread.sleep(waitMillis, waitNano);
			} catch (InterruptedException ie){
				ie.printStackTrace();
			}
			double totalElapsedTime = (System.nanoTime() - startTime)/1000000000d;
			deltaTime = (float)(totalElapsedTime / TARGET_FRAME_TIME);
		}
	}

	/**
	 * Used to get the cursors position relative to the game's resolution
	 * @param me
	 * @return the correct cursor position
     */
	private Point getRelativeMousePosition(MouseEvent me){
		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
		double x = (double)((double)me.getX()*(double)GameView.getScreenWidth()/monitorSize.getWidth());
		double y = (double)((double)me.getY()*(double)GameView.getScreenHeight()/monitorSize.getHeight());
		return new Point((int)x,(int)y);
	}

	/**
	 * Listener
	 * Pushes newly pressed keys to the key list
	 * @param ke
     */
	@Override
	public void keyPressed(KeyEvent ke) {
		//Closes the application
		if (ke.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		//Saves the pressed keys
		if (!pressedKeys.contains(ke.getKeyChar())){
			pressedKeys.add(ke.getKeyChar());
		}
	}

	/**
	 * Listener
	 * Removes the released key from the key list
	 * @param ke
	 */
	@Override
	public void keyReleased(KeyEvent ke) {
		int index = pressedKeys.indexOf(ke.getKeyChar());
		if(index >= 0){
			pressedKeys.remove(index);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Listener
	 * Saves the cursors position, and state of the mouse button
	 * @param me
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		cursor = getRelativeMousePosition(me);
		mousePress = true;
	}

	/**
	 * Listener
	 * Saves the cursors position
	 * @param me
	 */
	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub
		cursor = getRelativeMousePosition(me);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Listener
	 * Saves the state of the mouse button
	 * @param me
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		mousePress = true;
	}

	/**
	 * Listener
	 * Saves the state of the mouse button
	 * @param me
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		mousePress = false;
	}
}
