package zombienado_v1.utilities;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import zombienado_v1.client.view.LightMap;
import zombienado_v1.client.view.MapView;

public class MapLoader {
	public static void Load(MapView map,LightMap lightMap, File file) throws IOException{
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
						map.addWall(new Rectangle(x * MapView.TILE_SIZE, y * MapView.TILE_SIZE, MapView.TILE_SIZE, map.TILE_SIZE));
					}
					else if (id >=30 && id<40){
						lightMap.addLight(new Point(x * MapView.TILE_SIZE, y * MapView.TILE_SIZE));
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