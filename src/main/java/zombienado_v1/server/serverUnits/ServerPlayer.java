package zombienado_v1.server.serverUnits;

import zombienado_v1.server.serverWeapon.*;
import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerPlayer implements ServerUnit{
    private static final int RADIUS = 16;

    private int x;
    private int y;
    private int health;
    private int id;
    private int balance;
    private int score;
    private float rotation;
    private ServerWeapon weapon;
    private long timeWhenDamaged;
    private boolean isDead = false;

    // reports if has shot
    private boolean hasShot;

    public ServerPlayer(int x, int y, float r, int id){
        this.x = x;
        this.y = y;
        this.rotation = r;
        this.id = id;

        //TODO: move weapons to server
        this.weapon = new ServerBlunderbuss();
        this.health = 100;
        this.balance = 1000;
        this.score = 0;
        this.timeWhenDamaged = System.nanoTime();
    }

    public boolean resetShot() {
        if (hasShot){
            hasShot = false;
            return true;
        }
        return false;
    }

    public void setHealth(int newHealth){
        health = newHealth;
    }

    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

    public boolean hasShot() {
        return hasShot;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getID(){
        return id;
    }

    public float getRotation(){
        return rotation;
    }

    public int getHealth(){
        return this.health;
    }

    public int getBalance(){
        return this.balance;
    }

    public int getAmmo(){
        return weapon.getAmmo();
    }

    public int getWeaponID(){
        return weapon.getId();
    }

    public int getScore(){
        return score;
    }

    public boolean getIsDead(){
        return isDead;
    }

    public void takeDamage(int damage){
        long timeDiff = System.nanoTime() - timeWhenDamaged;
        if(timeDiff>500000000){
            this.health -= 10*damage;
            if(this.health<0) this.health =0;
            this.timeWhenDamaged = System.nanoTime();
        }
    }

    public void update(int x, int y, float r, ArrayList<ServerZombie> zombies, ArrayList<Point> walls){
        if(health<=0){
            isDead = true;
        }

        int xOld = this.x;
        int yOld = this.y;

        this.x += x;
        this.y += y;
        this.rotation = r; //Absolute now

        checkWallsCollisions(xOld,yOld,walls);
        checkDamageTaking(zombies);
    }

    public void checkWallsCollisions(int xOld, int yOld, ArrayList<Point> walls){
        int tileWidth = WorldHandler.getTileWidth();

        int tileX =(this.x/tileWidth) -1;
        int tileY =(this.y/tileWidth) -1;

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                int a = (tileX + i)*tileWidth;
                int b = (tileY + j)*tileWidth;
                if(a>=0 && b>=0){
                    if(walls.contains(new Point(a,b))){
                        checkSingleWallCollision(tileWidth,xOld,yOld,a,b);
                    }
                }
            }
        }
    }

    public void checkSingleWallCollision(int tileWidth, int xOld, int yOld, int x, int y){
        if(Physics.collidesWithWall(this.x,yOld,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.x = xOld;
        }
        if(Physics.collidesWithWall(xOld,this.y,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.y = yOld;
        }
    }

    private void checkDamageTaking(ArrayList<ServerZombie> zombies){
        for(ServerZombie zombie: zombies){
            double dX = zombie.getX()-this.x;
            double dY = zombie.getY()-this.y;
            double actualDistance = Math.sqrt( (dX)*(dX) + (dY)*(dY) );
            double allowedDistance = RADIUS + ServerZombie.getRadius();
            double overlap = allowedDistance - actualDistance;

            if(overlap>0) this.takeDamage(1);
        }
    }

    public ArrayList<ServerBullet> shoot(int bulletCounter){
        System.out.println("Ammo: " + weapon.getAmmo());
        if(weapon.getAmmo() <= 0) {
            weapon = new ServerGun();
        }
        hasShot = true;

        return weapon.shoot(x,y,rotation,bulletCounter);
    }

    public boolean canShoot(){
        return weapon.canShoot();
    }

    public void addBalance(int money) {
        this.balance += money;
        this.score += money;
    }

    public void reduceBalance(int money){
        this.balance -= money;
    }

    public void switchWeapon(int weaponID){
        System.out.println("new id:" + weaponID);
        if (weaponID == 20 && balance>=400) {
            weapon = new ServerAK47();
            reduceBalance(400);
        } else if (weaponID == 32 && balance>=1300) {
            weapon = new ServerAutoShotgun();
            reduceBalance(1300);
        } else if (weaponID == 31 && balance>=700) {
            weapon = new ServerBlunderbuss();
            reduceBalance(700);
        } else if (weaponID == 41 && balance>=600) {
            weapon = new ServerDoubleUzi();
            reduceBalance(600);
        } else if (weaponID == 00) {
            weapon = new ServerGun();
        } else if (weaponID == 22 && balance>=1200) {
            weapon = new ServerM4();
            reduceBalance(1200);
        } else if (weaponID == 30 && balance>=400) {
            weapon = new ServerShotgun();
            reduceBalance(400);
        } else if (weaponID == 21 && balance>=800) {
            weapon = new ServerTommyGun();
            reduceBalance(800);
        } else if (weaponID == 42 && balance>=900) {
            weapon = new ServerTripleUzi();
            reduceBalance(900);
        } else if (weaponID == 40 && balance>=300) {
            weapon = new ServerUzi();
            reduceBalance(300);
        }
        System.out.println("new id:" + this.weapon.getId());
    }

}
