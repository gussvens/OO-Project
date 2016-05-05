package zombienado_v1.client.proxyModel.Units;

import zombienado_v1.client.proxyModel.Unit;
import zombienado_v1.client.proxyModel.weapon.Gun;
import zombienado_v1.client.proxyModel.weapon.Weapon;

import java.util.ArrayList;

/**
 * A class representing a player in the proxy-model
 */
public class Player extends Unit {
    private static final float RADIUS = 32;
    private Weapon weapon;

    public Player(){}

    public Player(int x, int y, double r, int id){
        super(x,y,r,id,RADIUS);
        this.weapon = new Gun();
    }

    @Override
    public float getRadius() {
        return RADIUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(double x, double y, double rotation, ArrayList<ArrayList<Boolean>> solidMap) {

    }
}
