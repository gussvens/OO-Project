package zombienado_beta.server.serverModel.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerUzi extends ServerWeapon {

    private final static int ID = 40;
    private final static int DAMAGE = 30;
    private final static int PRICE = 300;
    private final static int DISTANCE_TO_MUZZLE = 56; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 500;
    private final static int SHOTS = 1;
    private final static float SPRAY = 0.5f;
    private final static double RATEOFFIRE = 80;

    public ServerUzi() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
}
