package zombienado_v1.server.serverWeapon;

import zombienado_v1.interfaces.iWeapon;
import zombienado_v1.server.serverUnits.ServerBullet;

import java.util.Random;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerGun implements iWeapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999999999;
    private final static int DAMAGE = 10;
    private final static int PRICE = 0;
    private final static double SPRAY = 0.3;
    private final static int DISTANCE_TO_MUZZLE = 41; //distance from player center to muzzle in pixels
    //TEST
    //private final static double RATEOFFIRE = 1000;
    private final static double RATEOFFIRE = 100;
    private final int BULLETSPEED = 20;
    private long lastFired = System.currentTimeMillis();
    private int ammo;

    public ServerGun() {

    }

    public boolean canShoot(){
        if(System.currentTimeMillis() - lastFired >= RATEOFFIRE) {
            return true;
        } else {
            return false;
        }
    }

    public ServerBullet shoot(int x, int y, double direction, int bulletCounter){
        //Fix with better values

            lastFired = System.currentTimeMillis();
        Random r = new Random();
        return new ServerBullet((int)(x + Math.cos(direction)*48), (int)(y+ Math.sin(direction)*48), bulletCounter, direction - SPRAY/2 + r.nextDouble() * SPRAY, DAMAGE, BULLETSPEED);

    }

    public int getAmmo(){
        return ammo;
    }

    public int getDistanceToMuzzle(){
        return DISTANCE_TO_MUZZLE;
    }
}
