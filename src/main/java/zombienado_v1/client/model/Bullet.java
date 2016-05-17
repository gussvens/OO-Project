package zombienado_v1.client.model;

/**
 * Created by Marcus on 2016-05-17.
 */
public class Bullet extends Unit {

    private int id;
    public Bullet (int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public Unit copy (){
        Unit copy = new Bullet(getId());
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        return copy;
    }

}
