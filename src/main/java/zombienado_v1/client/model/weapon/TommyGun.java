package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class TommyGun extends Weapon {

    private final static int ID = 21;
    private final static int DAMAGE = 70;
    private final static int PRICE = 800;
    private final static int MAXAMMO = 400;
    private final static int DISTANCE_TO_MUZZLE = 77;

    public TommyGun() {
        super(ID, DAMAGE, PRICE, MAXAMMO, DISTANCE_TO_MUZZLE);
    }
}
