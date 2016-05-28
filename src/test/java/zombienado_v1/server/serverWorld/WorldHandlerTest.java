package zombienado_v1.server.serverWorld;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gustav on 16-05-28.
 */
public class WorldHandlerTest {

    @Test
    public void testGetSpawnTiles(){
        WorldHandler worldHandler = new WorldHandler();
        worldHandler.createMap("src/main/resources/maps/mapPillars.txt");
        assertTrue(worldHandler.getSpawnTiles() != null);
    }

    @Test
    public void testGetWallTiles(){
        WorldHandler worldHandler = new WorldHandler();
        worldHandler.createMap("src/main/resources/maps/mapPillars.txt");
        assertTrue(worldHandler.getWallTiles() != null);
    }

    @Test
    public void testGetTileWidth(){
        assertTrue(WorldHandler.getTileWidth() == 32);
    }
}