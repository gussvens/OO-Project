package zombienado_v1.client.model;

import zombienado_v1.client.model.weapon.Weapon;

public class Player extends Unit{
    //TEST
    private Weapon weapon = new Weapon(0, 100, 100, 100, 100, 100);

    public Player copy (){
        Player copy = new Player();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        return copy;
    }

    public Weapon getWeapon(){return weapon;}
}
