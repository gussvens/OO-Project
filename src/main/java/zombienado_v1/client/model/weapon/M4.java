package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class M4 extends Weapon {

    private final static int ID = 22;
    private final static int DAMAGE = 85;
    private final static int PRICE = 1200;
    private final static int MAXAMMO = 560;
    private final static int DISTANCE_TO_MUZZLE = 79;

    public M4() {
        super(ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE);
    }
}
