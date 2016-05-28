package zombienado_beta.server.serverModel;

import zombienado_beta.server.serverModel.serverWeapon.*;
import zombienado_beta.server.serverWorld.WorldHandler;
import zombienado_beta.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerPlayer implements ServerUnit{
    private static final int RADIUS = 12;

    private int x;
    private int y;
    private int health;
    private int balance;
    private int score;
    private float rotation;
    private ServerWeapon weapon;
    private long timeWhenDamaged;
    private boolean isDead = false;

    // reports if has shot
    private boolean hasShot;

    /**
     * A constructor that returns a new ServerPlayer
     * @param x - The ServerPlayer's starting x-coordinate
     * @param y - The ServerPlayer's starting y-coordinate
     * @param r - The ServerPlayer's starting rotation
     * @param id - The ServerPlayers ID
     */
    public ServerPlayer(int x, int y, float r, int id){
        this.x = x;
        this.y = y;
        this.rotation = r;

        //TODO: move weapons to server
        this.weapon = new ServerBlunderbuss();
        this.health = 100;
        this.balance = 1000;
        this.score = 0;
        this.timeWhenDamaged = System.nanoTime();
    }

    /**
     * A method that returns true if the player has shot
     * @return - Returns true if player has shot, false otherwise
     */
    public boolean resetShot() {
        if (hasShot){
            hasShot = false;
            return true;
        }
        return false;
    }

    /**
     * A method that sets the health of the player
     * @param newHealth - The new health of the player
     */
    public void setHealth(int newHealth){
        health = newHealth;
    }

    /**
     * Method that change if the player is dead or not
     * @param isDead - Boolean that tells wether the player is dead or not
     */
    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

    /**
     * @return - If the player has shot or not
     */
    public boolean hasShot() {
        return hasShot;
    }

    /**
     * @return - The player's x-coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * @return - The player's y-coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * @return - The player's rotation
     */
    public float getRotation(){
        return rotation;
    }

    /**
     * @return The player's health
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * @return - The player's current balance
     */
    public int getBalance(){
        return this.balance;
    }

    /**
     * @return - The ammo left in the weapon
     */
    public int getAmmo(){
        return weapon.getAmmo();
    }

    /**
     * @return - The ID of the current weapon
     */
    public int getWeaponID(){
        return weapon.getId();
    }

    /**
     * @return - The player's score
     */
    public int getScore(){
        return score;
    }

    /**
     * @return - If the player is dead or not
     */
    public boolean getIsDead(){
        return isDead;
    }

    /**
     * A method that reduces the player's health by a set amount
     * @param damage - The amount of health removed
     */
    public void takeDamage(int damage){
        long timeDiff = System.nanoTime() - timeWhenDamaged;
        if(timeDiff>500000000){
            this.health -= 10*damage;
            if(this.health<0) this.health =0;
            this.timeWhenDamaged = System.nanoTime();
        }
    }

    /**
     * A method that updates the player's position and rotation aswell as checking for colission with walls and zombies
     * @param x - How far the player has moved on the x-axis
     * @param y - How far the player has moved on the y-axis
     * @param r - How the player is rotated
     * @param zombies - All zombies on the map
     * @param walls - All wall tiles on the map
     */
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

    /**
     * A method that checks if the player has moved into a wall
     * @param xOld - The x-position before the player moved
     * @param yOld - The y-position before the player moved
     * @param walls - All walls on the map
     */
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

    /**
     * A method that checks if the player should take damage
     * @param zombies - All zombies on the map
     */
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

    /**
     * A method that shoots the current weapon. If the weapon is out of ammo, the player switches to the weapon ServerGun
     * @return - An ArrayList with bullets
     */
    public ArrayList<ServerBullet> shoot(){
        if(weapon.getAmmo() <= 0) {
            weapon = new ServerGun();
        }
        hasShot = true;

        return weapon.shoot(x,y,rotation);
    }

    /**
     * A method that checks if the player can shoot
     * @return - True if player can shoot, otherwise false
     */
    public boolean canShoot(){
        return weapon.canShoot();
    }

    /**
     * A method that updates the score and balance of the player
     * @param money - The amount of score and balance that will be added
     */
    public void addBalance(int money) {
        this.balance += money;
        this.score += money;
    }

    /**
     * A method that reduces the balance of the player
     * @param money - The amount of balance to be removed
     */
    public void reduceBalance(int money){
        this.balance -= money;
    }

    /**
     * A method that switches to a specified weapon
     * @param weaponID - The ID of the weapon to switch to
     */
    public void switchWeapon(int weaponID){
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
    }

}
