package zombienado_beta.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Blunderbuss extends Weapon {

    private final static String NAME = "Blunderbuss";
    private final static int ID = 31;
    private final static int DAMAGE = 120;
    private final static int PRICE = 700;
    private final static int MAXAMMO = 50;
    private final static int DISTANCE_TO_MUZZLE = 80;
    private final static double SPRAY = 1.0;
    private final static double RATEOFFIRE = 1000;

    public Blunderbuss() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
