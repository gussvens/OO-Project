package client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Ak47 extends Weapon {

    private final static int ID = 20;
    private final static int MAXAMMO = 60;
    private final static int DAMAGE = 30;
    private final static int PRICE = 400;
    private final static double SPRAY = 1.0;
    private final static double RATEOFFIRE = 3;

    public Ak47() {
        super(ID, MAXAMMO, DAMAGE, PRICE, SPRAY, RATEOFFIRE);
    }

}
