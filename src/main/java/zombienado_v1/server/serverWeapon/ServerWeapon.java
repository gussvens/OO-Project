package zombienado_v1.server.serverWeapon;

import zombienado_v1.interfaces.iWeapon;
import zombienado_v1.server.ServerBullet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gustav on 16-05-18.
 */
public class ServerWeapon implements iWeapon {
    private int id;
    private int damage;
    private int price;
    private int distanceToMuzzle; //distance from player center to muzzle in pixels
    private int bulletSpeed;
    private int ammo;
    private int shots;
    private double spray;
    private double rateOfFire;
    private long lastFired = System.currentTimeMillis();

    public ServerWeapon(int id, int damage, int price, int distanceToMuzzle, int bulletSpeed, int ammo, int shots, double spray, double rateOfFire) {
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

    public boolean canShoot(){
        if(System.currentTimeMillis() - lastFired >= rateOfFire && ammo > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<ServerBullet> shoot(int x, int y, double direction, int bulletCounter){
        //Fix with better values
        if(ammo > 0) {

            lastFired = System.currentTimeMillis();
            Random r = new Random();

            ArrayList<ServerBullet> bullets = new ArrayList<ServerBullet>();
            for (int i = 0; i < shots; i++) {
                bullets.add(new ServerBullet((int) (x + Math.cos(direction) * 48), (int) (y + Math.sin(direction) * 48), bulletCounter, direction - spray / 2 + r.nextDouble() * spray, damage, bulletSpeed));
            }

            ammo--;


            return bullets;
        } else {
            return new ArrayList<ServerBullet>();
        }

    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
    public void setlastFired(long lastFired){
        this.lastFired = lastFired;
    }

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
