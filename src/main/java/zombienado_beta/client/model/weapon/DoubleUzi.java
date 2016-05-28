package zombienado_beta.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class DoubleUzi extends Weapon {

    private final static String NAME = "Double Uzi";
    private final static int ID = 41;
    private final static int DAMAGE = 60;
    private final static int PRICE = 600;
    private final static int MAXAMMO = 750;
    private final static int DISTANCE_TO_MUZZLE = 56;
    private final static double SPRAY = 0.6;
    private final static double RATEOFFIRE = 60;

    public DoubleUzi() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
