package zombienado_v1.server.model.serverUnits;

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
        ArrayList<ArrayList<Boolean>> solidMap = new ArrayList<ArrayList<Boolean>>();
        sZ.update(1.0,2.0,3.4,solidMap);
        assertTrue(sZ.getX() == 4);
        assertTrue(sZ.getY() == 8);
        assertTrue(sZ.getRotation() == 3.4);
    }
}