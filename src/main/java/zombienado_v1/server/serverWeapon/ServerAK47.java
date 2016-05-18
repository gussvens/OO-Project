package zombienado_v1.server.serverWeapon;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerAK47 extends ServerWeapon {

    private final static int ID = 20;
    private final static int DAMAGE = 60;
    private final static int PRICE = 400;
    private final static int DISTANCE_TO_MUZZLE = 80; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 360;
    private final static int SHOTS = 1;
    private final static double SPRAY = 0.3;
    private final static double RATEOFFIRE = 100;

    public ServerAK47() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }
}
