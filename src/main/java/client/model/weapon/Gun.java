package client.model.weapon;

import java.awt.*;

/**
 * Created by Gustav on 16-04-12.
 */
public class Gun extends Weapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999;
    private final static int DAMAGE = 10;
    private final static int PRICE = 0;
    private final static float SPRAY = 1;
    private final static float RATEOFFIRE = 1;

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
