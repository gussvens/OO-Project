package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Ak47 extends Weapon {

    private final static int ID = 20;
    private final static int MAXAMMO = 60;
    private final static int PRICE = 400;
    private final static int DISTANCE_TO_MUZZLE = 64;

    public Ak47() {
        super(ID, MAXAMMO, PRICE);
    }

    public int getDistanceToMuzzle(){
        return DISTANCE_TO_MUZZLE;
    }

}
