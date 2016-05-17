package zombienado_v1.server.serverUnits;

import junit.framework.TestCase;

/**
 * Created by Marcus on 2016-05-09.
 */
public class ServerBulletTest extends TestCase {

    public void testUpdate(){

        Bullet b = new Bullet(0,0,1,0.0,0,20);
        b.update();
        assertTrue(b.getX() == 20);
        assertTrue(b.getY() == 0);

    }

}
