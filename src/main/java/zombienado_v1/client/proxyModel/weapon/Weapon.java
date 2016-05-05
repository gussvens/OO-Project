package zombienado_v1.client.proxyModel.weapon;

import zombienado_v1.Interface.IWeapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Weapon implements IWeapon{

    private int id;
    private int ammo;
    private int damage;
    private int price;
    private double spray;
    private double rateOfFire;

    public Weapon(int id, int ammo, int damage, int price, double spray, double rateOfFire){
        this.id = id; // Coordinate to weapon sprite in weaponSheet
        this.ammo = ammo;
        this.damage = damage; // Damage dealt on impact with foe (Zombie)
        this.price = price;
        this.spray = spray; // An max angle difference between bullet direction and aim
        this.rateOfFire = rateOfFire; // The number of firable bullets per second
    }

    /**
     * {@inheritDoc}
     */
    public int getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumberOfBulletsLeft() {
        return this.ammo;
    }

    /**
     * {@inheritDoc}
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * {@inheritDoc}
     */
    public double getSpray() {
        return this.spray;
    }

    /**
     * {@inheritDoc}
     */
    public double getRateOfFire() {
        return this.rateOfFire;
    }

    /**
     * {@inheritDoc}
     */
    public boolean fire(){
        if(canFire()){
            this.ammo--;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canFire(){
        // TODO
        // check if enough time has passed since last fire, use rateOfFire variable
        // return true if yes, else false.
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void update(){
        // do stuff repeatedly
    }

    /*
    public void draw(Graphics2D graphics){
        // do stuff with id
    };
    */
}
