package zombienado_v1.client.view;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import zombienado_v1.client.model.Model;
import zombienado_v1.utilities.Camera;

public class MapView {
	//how do we want to map the tiles?
	private ArrayList<ArrayList<Integer>> tiles; //ALL tiles (x, y) 
	private ArrayList<Rectangle> bounds; //Only wall tiles
	public static final int TILE_SIZE = 32;
	private static Image tileSheet;
	private LightMap lightMap;
	private Model model;
	private boolean isLoaded = false;

	public MapView(Image tileSheet, Model model){
		tiles = new ArrayList<ArrayList<Integer>>();
		bounds = new ArrayList<Rectangle>();
		this.tileSheet = tileSheet;
		this.model = model;
	}

	public boolean isLoaded(){
		return isLoaded;
	}

	public void load(File mapData) throws IOException {
		LightMap lightMap = new LightMap(model);
		try (BufferedReader br = new BufferedReader(new FileReader(mapData))) {
			String line;
			int x = 0;
			int y = 0;
			while ((line = br.readLine()) != null) {
				String thisLine = line;
				String[] tiles = thisLine.split(" ");
				ArrayList<Integer> thisRow = new ArrayList<Integer>();
				for (String tile : tiles){
					int id = Integer.parseInt(tile);
					thisRow.add(id);
					if (id < 10){
						addWall(new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
					}
					else if (id >=30 && id<40){
						lightMap.addLight(new Point(x * MapView.TILE_SIZE, y * MapView.TILE_SIZE));
					}
					x++;
				}
				addTileRow(thisRow);
				y++;
				x = 0;
			}
		}
		setLightMap(lightMap);
		isLoaded = true;
	}

	public void setLightMap(LightMap lightMap){
		this.lightMap = lightMap;
	}

	public static void setTileSheet(Image tileSheet){
		MapView.tileSheet = tileSheet;
	}

	public void addTileRow(ArrayList<Integer> floorRow){ 
		tiles.add(floorRow);
	}

	public void addWall(Rectangle wall){
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
		if (!isLoaded) return;
		int startX = Math.max(Camera.getX() / TILE_SIZE - 1, 0);
		int startY = Math.max(Camera.getY() / TILE_SIZE - 1, 0);
		int endX = Math.min((Camera.getX() + GameView.getScreenWidth()) / TILE_SIZE + 2, tiles.get(0).size() - 1);
		int endY = Math.min((Camera.getY() + GameView.getScreenHeight()) / TILE_SIZE + 2, tiles.size() - 1);
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
				
				graphics.drawImage(tileSheet, x*TILE_SIZE/** - TILE_SIZE/2 */- Camera.getX(), y*TILE_SIZE/** - TILE_SIZE/2 */ - Camera.getY(), (x + 1)*TILE_SIZE/** - TILE_SIZE/2 */ - Camera.getX(), (y + 1)*TILE_SIZE/** - TILE_SIZE/2 */ - Camera.getY(), col*TILE_SIZE, row*TILE_SIZE, (col + 1)*TILE_SIZE, (row + 1)*TILE_SIZE, null);
			}
		}
	}

	public void drawLight(Graphics2D graphics){
		if (!isLoaded) return;
		lightMap.draw(graphics);
	}
}
