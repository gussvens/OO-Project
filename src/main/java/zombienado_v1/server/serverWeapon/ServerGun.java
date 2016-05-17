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
    private final static long RATEOFFIRE = 1000;
    private final int BULLETSPEED = 20;
    private int lastFired = 2000;
    private int ammo;

    public ServerGun() {

    }

    public ServerBullet shoot(int x, int y, double direction, int ID){
        //Fix with better values
        if(System.currentTimeMillis() - lastFired >= RATEOFFIRE) {
            return new ServerBullet(x, y, ID, direction, DAMAGE, BULLETSPEED);
        } else {
            return null;
        }
    }

    public int getAmmo(){
        return ammo;
    }

}
