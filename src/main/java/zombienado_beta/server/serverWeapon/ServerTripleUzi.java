package zombienado_beta.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerTripleUzi extends ServerWeapon {

    private final static int ID = 42;
    private final static int DAMAGE = 90;
    private final static int PRICE = 900;
    private final static int DISTANCE_TO_MUZZLE = 56; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 1000;
    private final static int SHOTS = 1;
    private final static float SPRAY = 0.7f;
    private final static double RATEOFFIRE = 40;

    public ServerTripleUzi() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
}
