package zombienado_v1.client.model;

import zombienado_v1.client.model.weapon.*;
import zombienado_v1.interfaces.iWeapon;

public class Player extends Unit{
    //TEST
    private iWeapon weapon = new TommyGun();
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

    public void setHealth(int health){
        this.health = health;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void setAmmo(int ammo){
        weapon.setAmmo(ammo);
    }

    public iWeapon getWeapon(){return weapon;}

    public int getHealth(){
        return this.health;
    }

    public int getBalance(){
        return this.balance;
    }
}
