package zombienado_beta.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class TommyGun extends Weapon {

    private final static String NAME = "Tommy Gun";
    private final static int ID = 21;
    private final static int DAMAGE = 70;
    private final static int PRICE = 800;
    private final static int MAXAMMO = 400;
    private final static int DISTANCE_TO_MUZZLE = 77;
    private final static double SPRAY = 0.4;
    private final static double RATEOFFIRE = 120;

    public TommyGun() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
