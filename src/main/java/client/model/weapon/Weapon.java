package client.model.weapon;

import java.awt.*;

/**
 * Created by Gustav on 16-04-12.
 */
public abstract class Weapon {

    private int id;
    private int ammo;
    private int damage;
    private int price;
    private float spray;
    private float rateOfFire;
    private Image storeImage; // A image showing the gun in the store
    private Image gameSprite; // The sprite to overlap with playerSprites

    public Weapon(int id, int ammo, int damage, int price, float spray, float rateOfFire, Image storeImage, Image gameSprite){
        this.id = id;
        this.ammo = ammo;
        this.damage = damage;
        this.price = price;
        this.spray = spray;
        this.rateOfFire = rateOfFire;
        this.storeImage = storeImage;
        this.gameSprite = gameSprite;
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
        if(false){ // Check if we can shoot, i.e. rate of fire allows it
            this.ammo--;
            return true;
        }
        return false;
    }

    public Image getStoreImage() {
        return storeImage;
    }

    public Image getGameSprite() {
        return gameSprite;
    }

    public abstract void update();

    public abstract void draw(Graphics2D graphics);
}
