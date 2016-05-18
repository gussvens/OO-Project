package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Gun extends Weapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999;
    private final static int PRICE = 0;
    private final static int DISTANCE_TO_MUZZLE = 41;

    public Gun() {
        super(ID, MAXAMMO, PRICE);
    }

    public int getDistanceToMuzzle(){
        return DISTANCE_TO_MUZZLE;
    }
}
