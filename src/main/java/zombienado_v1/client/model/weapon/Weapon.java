package zombienado_v1.client.model.weapon;

import zombienado_v1.interfaces.iWeapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Weapon{

    private int id;
    private int ammo;
    private int price;

    public Weapon(int id, int ammo, int price){
        this.id = id; // Coordinate to weapon sprite in weaponSheet
        this.ammo = ammo;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public int getAmmo(){
        return this.ammo;
    }

    public int getDistanceToMuzzle(){
        return 0;
    }

}
