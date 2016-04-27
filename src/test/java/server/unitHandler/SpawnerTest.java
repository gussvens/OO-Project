package server.unitHandler;

import junit.framework.TestCase;
import org.junit.Test;
import server.serverUnits.ServerPlayer;
import server.serverUnits.ServerZombie;
import server.serverWorld.serverTiles.SpawnerTile;

import java.util.ArrayList;

/**
 * Created by Gustav on 16-04-13.
 */
public class SpawnerTest extends TestCase {

    @Test
    public void testGetInstance(){
        Spawner s = Spawner.getInstance();
        assertTrue(s != null);
    }

    @Test
    public void testGetZombies(){
        Spawner s = Spawner.getInstance();
        ArrayList<ServerPlayer> pos = new ArrayList<ServerPlayer>();
        ArrayList<SpawnerTile> spawns = new ArrayList<>();

        spawns.add(new SpawnerTile(0,0));
        pos.add(0, new ServerPlayer(0,0,0,0));

        for(int i = 0; i<=30; i++){
            s.update(pos, spawns);
        }

        assertTrue(s.getZombies().get(0) != null);
        assertTrue(s.getZombies().get(0).getX() == 0);
    }
}