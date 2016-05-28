package zombienado_beta.client.model;

/**
 * A class to represent a zombie unit on the client side of the application
 */
public class Zombie extends Unit{
    /**
     * Returns a copy of this zombie unit
     * @return A copy of this zombie unit
     */
    public Unit copy (){
        Unit copy = new Zombie();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        return copy;
    }
}