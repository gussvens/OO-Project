package zombienado_v1.client.model;

import zombienado_v1.client.model.weapon.Weapon;

public class Player extends Unit{
    //TEST
    private Weapon weapon = new Weapon(30, 100, 100);
    private int balance;
    private int health;
    public boolean hasShot;

    /**
     * triggers sound effect in view
     */
    public void shoot(){
        hasShot = true;
    }

    public boolean hasShot(){
        if (hasShot) {
            hasShot = false;
            return true;
        }
        return false;
    }

    public Player copy (){
        Player copy = new Player();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        if (hasShot) {
            copy.shoot();
        }
        return copy;
    }

    public Weapon getWeapon(){return weapon;}

    public int getHealth(){
        return this.health;
    }

    public int getBalance(){
        return this.balance;
    }
}
