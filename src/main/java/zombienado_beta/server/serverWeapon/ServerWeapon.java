package zombienado_beta.server.serverWeapon;

import zombienado_beta.interfaces.iWeapon;
import zombienado_beta.server.serverUnits.ServerBullet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gustav on 16-05-18.
 */
public abstract class ServerWeapon implements iWeapon {
    private int id;
    private int damage;
    private int price;
    private int distanceToMuzzle; //distance from player center to muzzle in pixels
    private int bulletSpeed;
    private int ammo;
    private int shots;
    private float spray;
    private double rateOfFire;
    private long lastFired = System.currentTimeMillis();

    /**
     * Constructor for a ServerWeapon
     * @param id - The ID of the ServerWeapon
     * @param damage - The damage of the ServerWeapon
     * @param price - The price of the ServerWeapon
     * @param distanceToMuzzle - The distance from the player to the muzzle
     * @param bulletSpeed - How fast bullets fired from this ServerWeapon travels
     * @param ammo - The amount of ammo
     * @param shots - How many bullets will be created everytime the ServerWeapon fires
     * @param spray - How accurate the ServerWeapon is
     * @param rateOfFire - How fast the ServerWeapon fires
     */
    public ServerWeapon(int id, int damage, int price, int distanceToMuzzle, int bulletSpeed, int ammo, int shots, float spray, double rateOfFire) {
        this.id = id;
        this.damage = damage;
        this.price = price;
        this.distanceToMuzzle = distanceToMuzzle;
        this.bulletSpeed = bulletSpeed;
        this.ammo = ammo+1;
        this.shots = shots;
        this.spray = spray;
        this.rateOfFire = rateOfFire;
    }

    /**
     * A method that determines if the weapon can fire depending on the rate of fire
     * @return - True if it can shoot, false otherwise
     */
    public boolean canShoot(){
        if(System.currentTimeMillis() - lastFired >= rateOfFire && ammo > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that creates an ArrayList of ServerBullets and reduces ammo with the amount of bullets created
     * @param x - The ServerBullets starting x position
     * @param y - The ServerBullets starting x position
     * @param direction - In wich direction the ServerBullers will be traveling
     * @return - An ArrayList of ServerBullets
     */
    public ArrayList<ServerBullet> shoot(int x, int y, float direction){
        //Fix with better values
        if(ammo > 0) {

            lastFired = System.currentTimeMillis();
            Random r = new Random();

            ArrayList<ServerBullet> bullets = new ArrayList<ServerBullet>();
            for (int i = 0; i < shots; i++) {
                bullets.add(new ServerBullet((int) (x + Math.cos(direction) * 48), (int) (y + Math.sin(direction) * 48), direction - spray / 2 + r.nextFloat() * spray, damage, bulletSpeed));
            }

            ammo--;


            return bullets;
        } else {
            return new ArrayList<ServerBullet>();
        }

    }

    //Setters
    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
    public void setlastFired(long lastFired){
        this.lastFired = lastFired;
    }

    ///Getters
    public int getId() {
        return id;
    }

    public int getDamage() {
        return ammo;
    }

    public int getPrice() {
        return price;
    }

    public int getDistanceToMuzzle(){
        return distanceToMuzzle;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public int getAmmo(){
        return ammo-1;
    }

    public double getSpray() {
        return spray;
    }

    public double getRateOfFire() {
        return rateOfFire;
    }

    public long getLastFired() {
        return getLastFired();
    }


}
