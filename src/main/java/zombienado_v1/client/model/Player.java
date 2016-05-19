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

    public void setWeapon(int weaponID){
        if(weapon.getId() != weaponID) {
            if (weaponID == 20) {
                weapon = new Ak47();
            } else if (weaponID == 32) {
                weapon = new AutoShotgun();
            } else if (weaponID == 31) {
                weapon = new Blunderbuss();
            } else if (weaponID == 41) {
                weapon = new DoubleUzi();
            } else if (weaponID == 00) {
                weapon = new Gun();
            } else if (weaponID == 22) {
                weapon = new M4();
            } else if (weaponID == 30) {
                weapon = new Shotgun();
            } else if (weaponID == 21) {
                weapon = new TommyGun();
            } else if (weaponID == 42) {
                weapon = new TripleUzi();
            } else if (weaponID == 40) {
                weapon = new Uzi();
            }

            System.out.println("Switched weapons!");
        }

    }

    public iWeapon getWeapon(){return weapon;}

    public int getHealth(){
        return this.health;
    }

    public int getBalance(){
        return this.balance;
    }

    public int getAmmo(){
        return weapon.getAmmo();
    }
}
