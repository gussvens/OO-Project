package zombienado_v1.client.controller;

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

import zombienado_v1.client.proxyModel.Model;
import zombienado_v1.client.view.GameView;

public class Controller extends Thread implements KeyListener, MouseMotionListener, MouseListener{
	private final Model model;
	private final GameView gameView;
	private static final double targetFrameTime = 1d/60d;
	private static double frameTime = 0;
	
	private List<Character> pressedKeys;
	private boolean mousePress = false;
	private Point cursor = new Point(0, 0);
	
	public static Controller create(Model project, GameView projectView) {
		return new Controller(project, projectView);
	}

	private Controller(Model model, GameView gameView) {
		this.model = model;
		this.gameView = gameView;
		pressedKeys = new ArrayList<Character>();
		gameView.addKeyListener(this);
		gameView.addMouseListener(this);
		gameView.addMouseMotionListener(this);
	}
	
	public void run(){
		model.initialize();
		gameView.load();
		gameLoop();
	}

	private void gameLoop(){
		long wait;
		long startTime;
		while (true){
			startTime = System.nanoTime();
			model.tick(pressedKeys, cursor, mousePress);
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
	
	public Point getRelativeMousePosition(MouseEvent me){
		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
		double x = (double)((double)me.getX()*(double)GameView.getScreenWidth()/monitorSize.getWidth());
		double y = (double)((double)me.getY()*(double)GameView.getScreenHeight()/monitorSize.getHeight());
		return new Point((int)x,(int)y);
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

	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		cursor = getRelativeMousePosition(me);
		mousePress = true;
	}

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

	@Override
	public void mousePressed(MouseEvent me) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePress = false;
	}
}
