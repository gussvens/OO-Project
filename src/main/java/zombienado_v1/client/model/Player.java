package zombienado_v1.client.model;

public class Player extends Unit{

    public Unit copy (){
        Unit copy = new Player();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        return copy;
    }
}
