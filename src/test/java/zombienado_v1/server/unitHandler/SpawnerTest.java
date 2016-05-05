package zombienado_v1.server.unitHandler;

import junit.framework.TestCase;
import org.junit.Test;
import zombienado_v1.server.serverUnits.ServerPlayer;

import java.awt.*;
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
        ArrayList<Point> spawns = new ArrayList<>();

        spawns.add(new Point(0,0));
        pos.add(0, new ServerPlayer(0,0,0,0));

        for(int i = 0; i<=30; i++){
            s.update(pos, spawns);
        }

        assertTrue(s.getZombies().get(0) != null);
        assertTrue(s.getZombies().get(0).getX() == 0);
    }
}