package server.serverUnits;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Gustav on 16-04-13.
 */
public class ServerZombieTest extends TestCase {

    @Test
    public void testUpdate(){
        ServerZombie sZ = new ServerZombie(0);
        sZ.update(1.0,2.0,3.4);
        assertTrue(sZ.getX() == 4);
        assertTrue(sZ.getY() == 8);
        assertTrue(sZ.getRotation() == 3.4);
    }
}