package zombienado_beta.client.model.weapon;

import zombienado_beta.interfaces.iWeapon;

/**
 * Created by Gustav on 16-04-12.
 * A class to represent all values of a weapon in the application
 */
public abstract class Weapon implements iWeapon{

    private String name; // Name of the weapon to display in shop
    private int id; // The id of the weapon is specific to the subclass
    private int damage; // Damage that weapon deals upon hit
    private int price; // Price in $ for weapon
    private int ammo; // Current number of ammunitions left in the weapon
    private int distanceToMuzzle; // In number of pixels from player center to muzzle
    private double spray; // Maximum divergence from aim when fired, in radiants
    private double rateOfFire; // Time between firing

    public Weapon(String name, int id, int damage, int ammo, int price, int distanceToMuzzle, double spray, double rateOfFire){
        this.name = name;
        this.id = id; // Coordinate to weapon sprite in weaponSheet
        this.damage = damage;
        this.price = price;
        this.ammo = ammo;
        this.distanceToMuzzle = distanceToMuzzle;
        this.rateOfFire = rateOfFire;
        this.spray = spray;
    }

    /**
     * Sets the weapons ammo
     * @param ammo The new ammo
     */
    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    /**
     * Returns the weapons name
     * @return The Weapons name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the weapons id
     * @return The weapons id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the weapons damage
     * @return The weapons damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the weapons price
     * @return The weapons price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the weapons ammo
     * @return The weapons ammo
     */
    public int getAmmo(){
        return this.ammo;
    }

    /**
     * Returns the weapons distance to muzzle
     * @return The weapons distance to muzzle
     */
    public int getDistanceToMuzzle(){
        return distanceToMuzzle;
    }

    /**
     * Returns the weapons spray
     * @return The weapons spray
     */
    public double getSpray(){
        return spray;
    }

    /**
     * Returns the weapons rate of fire
     * @return The weapons rate of fire
     */
    public double getRateOfFire(){
        return rateOfFire;
    }

}
