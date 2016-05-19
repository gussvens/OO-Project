package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Uzi extends Weapon {

    private final static int ID = 40;
    private final static int DAMAGE = 30;
    private final static int PRICE = 300;
    private final static int MAXAMMO = 500;
    private final static int DISTANCE_TO_MUZZLE = 56;

    public Uzi() {
        super(ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE);
    }
}
