package client.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.List;

import utilities.*;

public class Player {
	private Image sprite;
	private Animation feetAnimation;
	private int x;
	private int y;
	private double rotation;
	
	public Player(int x, int y, Image sprite, Image feet){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		feetAnimation = new Animation(feet, 6, 1, 10);
		feetAnimation.play();
	}
	
	public synchronized int getX(){
		return x;
	}
	
	public synchronized int getY(){
		return y;
	}
	
	public synchronized void setTexture(Image sprite){
		this.sprite = sprite;
	}
	
	public synchronized void update(List<Character> pressedKeys, Point cursor, boolean isMousePressed){
		/**
		 * Key events
		 */
		for (char key : pressedKeys){
			switch (key){
			case 'w':
			case 'W':
				y--;
				break;
			case 'a':
			case 'A':
				x--;
				break;
			case 's':
			case 'S':
				y++;
				break;
			case 'd':
			case 'D':
				x++;
				break;
			}
		}
		
		/**
		 * Mouse events
		 */
		int dX = (int)(cursor.getX() - x + Camera.getX());
		int dY = (int)(cursor.getY() - y + Camera.getY());
		rotation = Math.atan2(dY, dX); //Probably not working correct. Have to wait for textures in order to investigate 
		
		/**
		 * Logic
		 */
		feetAnimation.update();
	}
	
	public synchronized void draw(Graphics2D graphics){
         feetAnimation.draw(x - Camera.getX(), y - Camera.getY(), rotation, graphics);
         graphics.drawImage(sprite, GraphicsUtils.Transform(sprite, x - Camera.getX(), y - Camera.getY(), rotation), null);
	}
	
	public synchronized String getParsedServerString(){
		return x+";"+y+";"+rotation;
	}
}
