package client.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import client.view.GameView;
import utilities.Camera;

public class Map {
	//how do we want to map the tiles?
	private ArrayList<ArrayList<Integer>> tiles; //ALL tiles (x, y) 
	private ArrayList<Rectangle> bounds; //Only wall tiles
	public static final int TILE_SIZE = 32;
	private static Image tileSheet;

	public Map(Image tileSheet){
		tiles = new ArrayList<ArrayList<Integer>>();
		bounds = new ArrayList<Rectangle>();
		this.tileSheet = tileSheet;
	}

	public static void setTileSheet(Image tileSheet){
		Map.tileSheet = tileSheet;
	}

	public void addTileRow(ArrayList<Integer> floorRow){ 
		tiles.add(floorRow);
	}

	public void addWall(Rectangle wall){ //same reference should also be added in the tileRow
		bounds.add(wall);
	}

	/**
	 * @return a list of all wall tiles
	 */
	public ArrayList<Rectangle> getSolids(){ //only return needed
		return bounds;
	}

	/** Standard draw
	 * @param graphics
	 */
	public void draw(Graphics2D graphics){
		int startX = Math.max(Camera.getX() / TILE_SIZE, 0);
		int startY = Math.max(Camera.getY() / TILE_SIZE, 0);
		int endX = Math.min((Camera.getX() + GameView.getScreenWidth()) / TILE_SIZE + 1, tiles.get(0).size() - 1);
		int endY = Math.min((Camera.getY() + GameView.getScreenHeight()) / TILE_SIZE + 1, tiles.size() - 1);
		for (int y = startY; y < endY; y++) {
			ArrayList<Integer> tileRow = tiles.get(y);
			for (int x = startX; x < endX; x++){

				int id = tileRow.get(x);
				int col = 0;
				int row = 0;
				
				if (id < 10){
					col = id;
				} else {
					row = id%10;
					col = id/10;
				}
				
				graphics.drawImage(tileSheet, x*TILE_SIZE - Camera.getX(), y*TILE_SIZE - Camera.getY(), (x + 1)*TILE_SIZE - Camera.getX(), (y + 1)*TILE_SIZE - Camera.getY(), col*TILE_SIZE, row*TILE_SIZE, (col + 1)*TILE_SIZE, (row + 1)*TILE_SIZE, null);
				//graphics.drawString(temp, x*32 - Camera.getX(), y*32 - Camera.getY());
			}
		}
	}
}
