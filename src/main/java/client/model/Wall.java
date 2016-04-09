package client.model;

import java.awt.Rectangle;

public class Wall extends Tile {
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	public Wall(int x, int y, int id) {
		super(x, y, id);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(super.x, super.y, WIDTH, HEIGHT);
	}
	
}
