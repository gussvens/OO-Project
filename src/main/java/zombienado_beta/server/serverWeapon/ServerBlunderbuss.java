package zombienado_beta.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerBlunderbuss extends ServerWeapon {

    private final static int ID = 31;
    private final static int DAMAGE = 120;
    private final static int PRICE = 700;
    private final static int DISTANCE_TO_MUZZLE = 80; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 50;
    private final static int SHOTS = 10;
    private final static float SPRAY = 1.0f;
    private final static double RATEOFFIRE = 1000;

    public ServerBlunderbuss() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
}
