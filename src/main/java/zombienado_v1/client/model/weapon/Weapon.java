package zombienado_v1.client.model.weapon;

import zombienado_v1.interfaces.iWeapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Weapon implements iWeapon{

    private String name;
    private int id;
    private int damage;
    private int price;
    private int ammo;
    private int distanceToMuzzle;

    public Weapon(String name, int id, int damage, int ammo, int price, int distanceToMuzzle){
        this.name = name;
        this.id = id; // Coordinate to weapon sprite in weaponSheet
        this.damage = damage;
        this.price = price;
        this.ammo = ammo;
        this.distanceToMuzzle = distanceToMuzzle;
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

}
