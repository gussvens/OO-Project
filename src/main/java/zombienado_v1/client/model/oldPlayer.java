package zombienado_v1.client.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import zombienado_v1.client.model.weapon.Weapon;
import zombienado_v1.client.view.MapView;
import zombienado_v1.utilities.*;

public class oldPlayer {
/*	private static float RADIUS = 32;

	private int id = 0;
	private int x;
	private int y;
	private int health;
	private Weapon weapon;
	private double rotation;
	private boolean walking;

	public oldPlayer(int id, int x, int y) { //, Image sprite, Image feet){
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public synchronized int getID(){
		return id;
	}

	public synchronized int getX(){
		return x;
	}

	public synchronized int getY(){
		return y;
	}

	public synchronized double getRotation(){
		return rotation;
	}

	public float getRadius(){
		return RADIUS;
	}

	//public synchronized void setTexture(Image sprite){
	//	this.sprite = sprite;
	//}

	public synchronized void update(List<Character> pressedKeys, Point cursor, boolean isMousePressed){
		int xOld = x;
		int yOld = y;
		x += speedX;
		y += speedY;

		//COLLISION TESTING STARTS HERE
		int tileX = x / MapView.TILE_SIZE - 1;
		int tileY = y / MapView.TILE_SIZE - 1;
		Point nonCollideingCoordinate = new Point(0,0);

		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				for(Rectangle rect : walls){
					if(rect.contains((tileX + i) * MapView.TILE_SIZE, (tileY + j) * MapView.TILE_SIZE)){
						nonCollideingCoordinate = Physics.getNonCollideingCoordinate(xOld, yOld, x, y, rect);
					}
				}
			}
		}
		if(nonCollideingCoordinate!=null){
			this.x = (int)nonCollideingCoordinate.getX();
			this.y = (int)nonCollideingCoordinate.getY();
		}
	}

	public synchronized String getParsedServerString(){
		return x+";"+y+";"+rotation;
	}
*/}
