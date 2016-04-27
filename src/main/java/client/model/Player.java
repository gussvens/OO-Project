package client.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import client.model.weapon.Weapon;
import client.view.GameView;
import utilities.*;

public class Player {
	private static float RADIUS = 32;

	private Image sprite;
	private Animation feetAnimation;
	private int x;
	private int y;
	private int health;
	private Weapon weapon;
	private double rotation;
	private boolean walking;

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

	public float getRadius(){
		return RADIUS;
	}

	public synchronized void setTexture(Image sprite){
		this.sprite = sprite;
	}

	public synchronized void update(List<Character> pressedKeys, Point cursor, boolean isMousePressed, ArrayList<Rectangle> walls){
		/**
		 * Key events
		 */
		walking = false;
		int speedX = 0;
		int speedY = 0;
		for (char key : pressedKeys){
			switch (key){
			case 'w':
			case 'W':
				speedY=-2;
				walking = true;
				break;
			case 'a':
			case 'A':
				speedX=-2;
				walking = true;
				break;
			case 's':
			case 'S':
				speedY=2;
				walking = true;
				break;
			case 'd':
			case 'D':
				speedX=2;
				walking = true;
				break;
			}
		}

		/**
		 * Mouse events
		 */

		int dX = (int)(cursor.getX() - x + Camera.getX());
		int dY = (int)(cursor.getY() - y + Camera.getY());
		rotation = Math.atan2(dY, dX);

		/**
		 * Logic
		 */
		int xOld = x;
		int yOld = y;
		x += speedX;
		y += speedY;

		//COLLISION TESTING STARTS HERE
		int tileX = x / Map.TILE_SIZE - 1;
		int tileY = y / Map.TILE_SIZE - 1;
		Point nonCollideingCoordinate = new Point(0,0);

		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				for(Rectangle rect : walls){
					if(rect.contains((tileX + i) * Map.TILE_SIZE, (tileY + j) * Map.TILE_SIZE)){
						nonCollideingCoordinate = Physics.getNonCollideingCoordinate(xOld, yOld, x, y, rect);
					}
				}
			}
		}
		if(nonCollideingCoordinate!=null){
			this.x = (int)nonCollideingCoordinate.getX();
			this.y = (int)nonCollideingCoordinate.getY();
		}

		if (walking){
			feetAnimation.play();
		} else {
			feetAnimation.reset();
		}

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
