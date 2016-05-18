package zombienado_v1.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerGun extends ServerWeapon {

    private final static int ID = 00;
    private final static int DAMAGE = 10;
    private final static int PRICE = 0;
    private final static int DISTANCE_TO_MUZZLE = 41; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 999999999;
    private final static double SPRAY = 0.3;
    private final static double RATEOFFIRE = 100;

    public ServerGun() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SPRAY,RATEOFFIRE);
    }
}
