package zombienado_v1.utilities;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import zombienado_v1.client.model.Map;

public class MapLoader {
	public static void Load(Map map, File file) throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
						map.addWall(new Rectangle(x * Map.TILE_SIZE, y * Map.TILE_SIZE, Map.TILE_SIZE, map.TILE_SIZE));
						System.out.println("WALL LOADED @:" + x*Map.TILE_SIZE + "," + y * Map.TILE_SIZE);
					}
					x++;
				}
				map.addTileRow(thisRow);
				y++;
				x = 0;
			}
		}
	}
}