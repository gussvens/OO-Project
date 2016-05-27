package zombienado_v1.server.serverUnits;

import javafx.scene.shape.Circle;
import zombienado_v1.server.serverWorld.WorldHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerBullet implements ServerUnit {

    private int x;
    private int y;
    private int ID;
    private float rotation;
    private int speed;
    private int damage;

    /**
     * Constructor for a Bullet
     * @param x - The starting x-position of th eBullet
     * @param y - The starting y-position of th eBullet
     * @param rotation - The rotation of the Bullet
     * @param damage - The damage of the Bullet
     * @param bulletSpeed - The speed of the Bullet
     */
    public ServerBullet(int x, int y, float rotation, int damage, int bulletSpeed){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.speed = bulletSpeed;
        this.damage = damage;
    }

    /**
     * @return - The x-position of the Bullet
     */
    public int getX(){
        return x;
    }

    /**
     * @return - The y-position of the Bullet
     */
    public int getY(){
        return y;
    }

    /**
     * @return - The rotation of the Bullet
     */
    public float getRotation() { return rotation; }

    /**
     * @return - The ID of the Bullet
     */
    public int getID(){
        return ID;
    }

    /**
     * @return - The speed of the Bullet
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * @return - The damage of the Bullet
     */
    public int getDamage(){
        return damage;
    }

    /**
     * A method that updates the position of the bullet depending on it's rotation
     * @param zombies - An ArraysList of all the ServerZombies on the map
     * @param walls - An ArrayList of all the walls on the map
     */
    public void update(ArrayList<ServerZombie> zombies, ArrayList<Point> walls){

        checkCollisionWithWalls(walls);
        checkZombieCollision(zombies);

        x = x + (int)(speed*Math.cos(rotation));
        y = y + (int)(speed*Math.sin(rotation));
    }

    /**
     * A method that checks for collision between a ServerZombie and a Bullet
     * @param zombies - An ArrayList of ServerZombies
     */
    private void checkZombieCollision(ArrayList<ServerZombie> zombies){
        for(ServerZombie zombie: zombies){
            if(new Circle(zombie.getX(),zombie.getY(),ServerZombie.getRadius()).contains(this.x,this.y)){
                zombie.takeDamage(this.damage);
                this.speed = 0;
            }
        }
    }

    /**
     * A method that checks for collision between walls and Bullets
     * @param walls - An ArrayList of all the walls on the map
     */
    private void checkCollisionWithWalls(ArrayList<Point> walls){
        int tileWidth = WorldHandler.getTileWidth();
        for(Point wall: walls){
            if(new Rectangle((int)(wall.getX()),(int)(wall.getY()), tileWidth, tileWidth).contains(this.x,this.y)){
                this.speed = 0;
            }
        }
    }

}
