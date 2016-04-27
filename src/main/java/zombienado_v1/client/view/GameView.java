package zombienado_v1.client.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame{
	private static int WIDTH = 860;
	private static int HEIGHT = 480;

	private Canvas canvas;
	private Image imageData;
	private Graphics graphics;
	private Graphics renderer;
	private class Canvas extends JPanel {
		@Override
		public void paintComponent(Graphics g){
			g.drawImage(imageData, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

	public static int getScreenWidth(){
		return WIDTH;
	}
	

	public static int getScreenHeight(){
		return HEIGHT;
	}
	
	public GameView(){
		super("fullscreen");
		imageData = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		graphics = imageData.getGraphics();
		canvas = new Canvas();
		//this.setIgnoreRepaint(true); //To prevent unnecessary render operations (Note that this is causing G's Mac to ignore all repaints)
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ZOMBIE STORM 0.3 BETA DEVELOPER EDITION");
		//this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.getContentPane().add(canvas);
		//this.setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
	    
		pack();
	    setResizable(false);
		this.setVisible(true);
		renderer = canvas.getGraphics();
	}
	
	public void render(){
		canvas.paintComponent(renderer);
	}

	public Graphics2D getGraphicsBatch(){
		return (Graphics2D)graphics; //Is it ok to cast every time?
	}

}
