package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Ak47 extends Weapon {

    private final static String NAME = "AK-47";
    private final static int ID = 20;
    private final static int DAMAGE = 60;
    private final static int PRICE = 400;
    private final static int MAXAMMO = 360;
    private final static int DISTANCE_TO_MUZZLE = 80;

    public Ak47() {
        super(NAME,ID, DAMAGE, MAXAMMO, PRICE, DISTANCE_TO_MUZZLE);
    }
}
