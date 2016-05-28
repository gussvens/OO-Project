package zombienado_beta.client.model;

/**
 * Created by Marcus on 2016-05-17.
 */
public class Bullet extends Unit {

    /**
     * Returns a copy of this unit
     * @return A copy of this unit
     */
    public Unit copy (){
        Unit copy = new Bullet();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        return copy;
    }

}
