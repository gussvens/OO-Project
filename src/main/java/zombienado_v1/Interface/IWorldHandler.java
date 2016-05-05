package zombienado_v1.Interface;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gustav on 16-05-05.
 */
public interface IWorldHandler {
    /**
     * Creates a map from map file in input file path
     * @param mapPath File path to map file.
     */
    void createMap(String mapPath);


    /**
     * Returns a list containing all spawnpoints in current map
     * @return A list containing all spawnpoints in current map
     */
    ArrayList<Point> getSpawnTiles();

    /**
     * Returns a 2D array list with all solid tiles represented as true
     * @return A 2D array list with all solid tiles represented as true
     */
    ArrayList<ArrayList<Boolean>> getSolidMap();

    /**
     * Returns the standard width of a map tile
     * @return The standard width of a map tile
     */
    static int getTileWidth() {
        return 32;
    }
}
