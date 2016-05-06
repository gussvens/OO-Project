package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Gun extends Weapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999;
    private final static int DAMAGE = 10;
    private final static int PRICE = 0;
    private final static double SPRAY = 0.5;
    private final static double RATEOFFIRE = 1;

    public Gun() {
        super(ID, MAXAMMO, DAMAGE, PRICE, SPRAY, RATEOFFIRE);
    }

    @Override
    public boolean shoot(){
        if(canFire()){
            return true;
        }
        return false;
    }
}
