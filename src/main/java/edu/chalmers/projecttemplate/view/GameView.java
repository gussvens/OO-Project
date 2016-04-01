package edu.chalmers.projecttemplate.view;

import edu.chalmers.projecttemplate.model.Model;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameView extends JFrame{
	private static int WIDTH = 860;
	private static int HEIGHT = 480;

	private Canvas canvas;
	private Image imageData;
	private Graphics graphics;
	private class Canvas extends JPanel {
		@Override
		public void paintComponent(Graphics g){
			g.drawImage(imageData, 0, 0, null);
		}
	}
	
	public GameView(){
		imageData = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		graphics = imageData.getGraphics();
		canvas = new Canvas();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ZOMBIE STORM 0.3 BETA DEVELOPER EDITION");
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.getContentPane().add(canvas);
		this.setVisible(true);
	}

	public Graphics getGraphicsBatch(){
		return graphics;
	}
	

	
}
