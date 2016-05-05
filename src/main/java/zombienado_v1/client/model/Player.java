package zombienado_v1.client.model;

/**
 * A class representing a player in the proxy-model
 */
public class Player extends Unit {
    public Player(){}

    public Player(int x, int y, double r, int id){
        super(x,y,r,id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(double x, double y, double rotation) {

    }
}
