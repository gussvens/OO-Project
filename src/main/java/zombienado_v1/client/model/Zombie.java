package zombienado_v1.client.model;

public class Zombie extends Unit{
    public Unit copy (){
        Unit copy = new Zombie();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        return copy;
    }
}