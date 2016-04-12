package client.model.weapon;

import java.awt.*;

/**
 * Created by Gustav on 16-04-12.
 */
public class Weapon {

    private int id;
    private int ammo;
    private int damage;
    private int price;
    private float spray;
    private float rateOfFire;

    public Weapon(int id, int ammo, int damage, int price, float spray, float rateOfFire){
        this.id = id; // Coordinate to weapon sprite in weaponSheet
        this.ammo = ammo;
        this.damage = damage; // Damage dealt on impact with foe (Zombie)
        this.price = price;
        this.spray = spray; // An max angle difference between bullet direction and aim
        this.rateOfFire = rateOfFire; // The number of firable bullets per second
    }

    public int getId() {
        return this.id;
    }

    public int getAmmo() {
        return this.ammo;
    }

    public int getDamage() {
        return this.damage;
    }


    public int getPrice() {
        return this.price;
    }

    public float getSpray() {
        return this.spray;
    }

    public float getRateOfFire() {
        return this.rateOfFire;
    }

    public boolean shoot(){
        if(canFire()){
            this.ammo--;
            return true;
        }
        return false;
    }

    public boolean canFire(){
        // TODO
        // check if enough time has passed since last fire, use rateOfFire variable
        // return true if yes, else false.
        return false;
    }

    public void update(){
        // do stuff repeatedly
    };

    public void draw(Graphics2D graphics){
        // do stuff with id
    };
}
