package zombienado_v1.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerM4 extends ServerWeapon {

    private final static int ID = 22;
    private final static int DAMAGE = 85;
    private final static int PRICE = 1200;
    private final static int DISTANCE_TO_MUZZLE = 79; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 560;
    private final static int SHOTS = 3;
    private final static double SPRAY = 0.1;
    private final static double RATEOFFIRE = 400;

    public ServerM4() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
}
