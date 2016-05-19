package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class AutoShotgun extends Weapon {

    private final static int ID = 32;
    private final static int DAMAGE = 90;
    private final static int PRICE = 1300;
    private final static int MAXAMMO = 90;
    private final static int DISTANCE_TO_MUZZLE = 78;

    public AutoShotgun() {
        super(ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE);
    }
}
