package zombienado_beta.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Gun extends Weapon {

    private final static String NAME = "Gun";
    private final static int ID = 00;
    private final static int DAMAGE = 50;
    private final static int PRICE = 0;
    private final static int MAXAMMO = 999999;
    private final static int DISTANCE_TO_MUZZLE = 60;
    private final static double SPRAY = 0.3;
    private final static double RATEOFFIRE = 400;

    public Gun() {
        super(NAME, ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE, SPRAY, RATEOFFIRE);
    }
}
