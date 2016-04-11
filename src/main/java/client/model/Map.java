package client.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class Map {
	//how do we want to map the tiles?
	private ArrayList<ArrayList<Tile>> tiles; //ALL tiles (x, y) 
	private ArrayList<Wall> solids; //Only wall tiles
	private static Image tileSheet;

	public Map(){
		tiles = new ArrayList<ArrayList<Tile>>();
		solids = new ArrayList<Wall>();
	}

	public static void setTileSheet(Image tileSheet){
		Map.tileSheet = tileSheet;
	}

	public void addTileRow(ArrayList<Tile> floor){ 
		tiles.add(floor);
	}

	public void addWall(Wall wall){ //same reference should also be added in the tileRow
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
		for (ArrayList<Tile> tileRow : tiles)
			for (Tile tile : tileRow){
				//draw from sheet
			}
	}
}

