package zombienado_v1.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerTommyGun extends ServerWeapon {

    private final static int ID = 21;
    private final static int DAMAGE = 70;
    private final static int PRICE = 800;
    private final static int DISTANCE_TO_MUZZLE = 77; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 400;
    private final static int SHOTS = 1;
    private final static double SPRAY = 0.4;
    private final static double RATEOFFIRE = 120;

    public ServerTommyGun() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
}
