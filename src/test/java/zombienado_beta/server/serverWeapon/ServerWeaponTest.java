package zombienado_beta.server.serverWeapon;

import junit.framework.TestCase;

import org.junit.Test;
import zombienado_beta.server.serverUnits.ServerBullet;

import java.util.ArrayList;

/**
 * Created by Gustav on 16-05-28.
 */
public class ServerWeaponTest extends TestCase {

    @Test
    public void testCanShoot(){
        ServerWeapon weapon = new ServerBlunderbuss();
        weapon.shoot(0,0,0f);
        assertTrue(!weapon.canShoot());
    }

    @Test
    public void testShoot(){
        ServerWeapon weapon = new ServerAK47();
        ArrayList<ServerBullet> bullets;
        bullets = weapon.shoot(0,0,0f);
        assertTrue(bullets != null);
    }
}