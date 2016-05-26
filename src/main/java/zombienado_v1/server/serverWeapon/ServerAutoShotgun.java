package zombienado_v1.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerAutoShotgun extends ServerWeapon {

    private final static int ID = 32;
    private final static int DAMAGE = 90;
    private final static int PRICE = 1300;
    private final static int DISTANCE_TO_MUZZLE = 78; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 90;
    private final static int SHOTS = 5;
    private final static float SPRAY = 0.5f;
    private final static double RATEOFFIRE = 200;

    public ServerAutoShotgun() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
    
}
