package zombienado_beta.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerShotgun extends ServerWeapon {

    private final static int ID = 30;
    private final static int DAMAGE = 60;
    private final static int PRICE = 400;
    private final static int DISTANCE_TO_MUZZLE = 78; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 15;
    private final static int MAXAMMO = 60;
    private final static int SHOTS = 5;
    private final static float SPRAY = 0.3f;
    private final static double RATEOFFIRE = 800;

    public ServerShotgun() {
        super(ID, DAMAGE, PRICE, DISTANCE_TO_MUZZLE, BULLETSPEED, MAXAMMO, SHOTS, SPRAY, RATEOFFIRE);
    }

}
