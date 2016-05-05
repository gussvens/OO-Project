package zombienado_v1.client.model;

/**
 * A class representing a zombie in the proxy-model
 */
public class Zombie extends Unit {
    public Zombie(){}

    public Zombie(int x, int y, double r, int id){
        super(x,y,r,id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(double x, double y, double rotation) {

    }
}