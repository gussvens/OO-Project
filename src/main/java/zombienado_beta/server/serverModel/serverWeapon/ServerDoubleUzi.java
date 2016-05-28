package zombienado_beta.server.serverModel.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerDoubleUzi extends ServerWeapon {

    private final static int ID = 41;
    private final static int DAMAGE = 60;
    private final static int PRICE = 600;
    private final static int DISTANCE_TO_MUZZLE = 56; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 750;
    private final static int SHOTS = 1;
    private final static float SPRAY = 0.6f;
    private final static double RATEOFFIRE = 60;

    public ServerDoubleUzi() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
}
