package zombienado_beta.client.model.weapon;

import zombienado_beta.interfaces.iWeapon;

/**
 * Created by Gustav on 16-04-12.
 */
public abstract class Weapon implements iWeapon{

    private String name;
    private int id;
    private int damage;
    private int price;
    private int ammo;
    private int distanceToMuzzle;
    private double spray;

    private double rateOfFire;

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

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    public String getName(){
        return name;
    }

    public int getId() {
        return this.id;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

    public int getAmmo(){
        return this.ammo;
    }

    public int getDistanceToMuzzle(){
        return distanceToMuzzle;
    }

    public double getSpray(){
        return spray;
    }

    public double getRateOfFire(){
        return rateOfFire;
    }

}
