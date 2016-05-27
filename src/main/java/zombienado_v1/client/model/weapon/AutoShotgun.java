package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class AutoShotgun extends Weapon {

    private final static String NAME = "Auto Shotgun";
    private final static int ID = 32;
    private final static int DAMAGE = 90;
    private final static int PRICE = 1300;
    private final static int MAXAMMO = 90;
    private final static int DISTANCE_TO_MUZZLE = 78;
    private final static double SPRAY = 0.5;
    private final static double RATEOFFIRE = 200;

    public AutoShotgun() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
