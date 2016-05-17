package zombienado_v1.server.serverWeapon;

import zombienado_v1.interfaces.iWeapon;
import zombienado_v1.server.serverUnits.ServerBullet;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerGun implements iWeapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999;
    private final static int DAMAGE = 10;
    private final static int PRICE = 0;
    private final static double SPRAY = 0.5;
    private final static double RATEOFFIRE = 1000;
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
            return new ServerBullet(x, y, bulletCounter, direction, DAMAGE, BULLETSPEED);

    }

    public int getAmmo(){
        return ammo;
    }

}
