package zombienado_v1.client.model;

import zombienado_v1.client.model.weapon.Weapon;

public class Player extends Unit{
    private Weapon weapon;

    public Player copy (){
        Player copy = new Player();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        return copy;
    }

    public Weapon getWeapon(){return weapon;}
}
