package zombienado_v1.server.serverWeapon;

import zombienado_v1.interfaces.iWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerGun implements iWeapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999;
    private final static int DAMAGE = 10;
    private final static int PRICE = 0;
    private final static double SPRAY = 0.5;
    private final static double RATEOFFIRE = 1;
    private int ammo;

    public ServerGun() {

    }

    public void shoot(){

    }

    public int getAmmo(){
        return ammo;
    }

}
