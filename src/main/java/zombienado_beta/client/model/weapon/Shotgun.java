package zombienado_beta.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Shotgun extends Weapon {

    private final static String NAME = "Shotgun";
    private final static int ID = 30;
    private final static int DAMAGE = 60;
    private final static int PRICE = 400;
    private final static int MAXAMMO = 60;
    private final static int DISTANCE_TO_MUZZLE = 78;
    private final static double SPRAY = 0.3;
    private final static double RATEOFFIRE = 800;

    public Shotgun() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
