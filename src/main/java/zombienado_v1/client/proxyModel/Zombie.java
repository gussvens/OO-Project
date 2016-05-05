package zombienado_v1.client.proxyModel;

import java.util.ArrayList;

/**
 * A class representing a zombie in the proxy-model
 */
public class Zombie extends Unit {
    private static final float RADIUS = 32;

    public Zombie(){}

    public Zombie(int x, int y, double r, int id){
        super(x,y,r,id,RADIUS);
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