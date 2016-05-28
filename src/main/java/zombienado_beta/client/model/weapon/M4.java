package zombienado_beta.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class M4 extends Weapon {

    private final static String NAME = "M4";
    private final static int ID = 22;
    private final static int DAMAGE = 85;
    private final static int PRICE = 1200;
    private final static int MAXAMMO = 560;
    private final static int DISTANCE_TO_MUZZLE = 79;
    private final static double SPRAY = 0.1;
    private final static double RATEOFFIRE = 400;

    public M4() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
