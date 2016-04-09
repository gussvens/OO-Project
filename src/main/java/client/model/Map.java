package client.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class Map {
	private ArrayList<Tile> tiles; //ALL tiles
	private ArrayList<Wall> solids; //Only wall tiles
	private Image tileSheet;
	
	public Map(Image tileSheet){
		tiles = new ArrayList<Tile>();
		solids = new ArrayList<Wall>();
	}
	
	public void addFloor(Floor floor){
		tiles.add(floor);
	}
	
	public void addWall(Wall wall){
		tiles.add(wall);
		solids.add(wall);
	}
	
	/**
	 * @return a list of all wall tiles
	 */
	public ArrayList<Wall> getSolids(){ //only return needed
		return solids;
	}
	
	/** Standard draw
	 * @param graphics
	 */
	public void draw(Graphics2D graphics){
		for (Tile tile : tiles){
			
		}
	}
}
