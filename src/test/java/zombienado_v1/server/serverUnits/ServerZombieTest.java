package zombienado_v1.server.serverUnits;

import junit.framework.TestCase;
import org.junit.Test;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gustav on 16-04-13.
 */
public class ServerZombieTest extends TestCase {

    @Test
    public void testUpdate(){
        ServerZombie sZ = new ServerZombie(0, new Point(0,0));
        ArrayList<Point> walls = new ArrayList<>();
        ArrayList<ServerZombie> zombies = new ArrayList<>();
        sZ.update(1.0,2.0,3.4,zombies,walls);
        assertTrue(sZ.getX() == 4);
        assertTrue(sZ.getY() == 8);
        assertTrue(sZ.getRotation() == 3.4);
    }
}