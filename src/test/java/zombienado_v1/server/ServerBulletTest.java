package zombienado_v1.server;

import junit.framework.TestCase;
import zombienado_v1.server.serverWeapon.ServerBullet;
import zombienado_v1.server.serverUnits.ServerZombie;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-05-09.
 */
public class ServerBulletTest extends TestCase {

    public void testUpdate(){
        ServerBullet b = new ServerBullet(0,0,1,0.0,0,20);
        ArrayList<ServerZombie> zombies = new ArrayList<>();
        ArrayList<Point> walls = new ArrayList<>();

        b.update(zombies,walls);
        assertTrue(b.getX() == 20);
        assertTrue(b.getY() == 0);

    }

    public void testStopping(){
        ServerBullet b = new ServerBullet(0,0,1,0.0,0,20);
        ArrayList<ServerZombie> zombies = new ArrayList<>();
        ArrayList<Point> walls = new ArrayList<>();
        Point wall = new Point(0,0);
        walls.add(wall);

        b.update(zombies,walls);
        assertTrue(b.getSpeed() == 0);
        assertTrue(b.getX() == 20);
        assertTrue(b.getY() == 0);
    }

}
