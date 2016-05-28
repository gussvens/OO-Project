package zombienado_v1.server.serverModel.serverWeapon;

import junit.framework.TestCase;
import org.junit.Test;
import zombienado_v1.server.serverModel.ServerBullet;
import zombienado_v1.server.serverModel.ServerZombie;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-05-09.
 */
public class ServerBulletTest extends TestCase {

    @Test
    public void testUpdate(){
        ServerBullet b = new ServerBullet(0,0,0.0f,0,20);
        ArrayList<ServerZombie> zombies = new ArrayList<>();
        ArrayList<Point> walls = new ArrayList<>();

        b.update(zombies,walls);
        assertTrue(b.getX() == 20);
        assertTrue(b.getY() == 0);

    }

    @Test
    public void testStopping(){
        ServerBullet b = new ServerBullet(0,0,0.0f,0,20);
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
