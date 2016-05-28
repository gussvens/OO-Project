package zombienado_beta.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class TripleUzi extends Weapon {

    private final static String NAME = "Triple Uzi";
    private final static int ID = 42;
    private final static int DAMAGE = 60;
    private final static int PRICE = 900;
    private final static int MAXAMMO = 1000;
    private final static int DISTANCE_TO_MUZZLE = 56;
    private final static double SPRAY = 0.7;
    private final static double RATEOFFIRE = 40;

    public TripleUzi() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
