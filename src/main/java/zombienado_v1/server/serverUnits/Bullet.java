package zombienado_v1.server.serverUnits;

import javafx.scene.shape.Circle;
import zombienado_v1.server.serverWorld.WorldHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-05-06.
 */
public class Bullet implements ServerUnit {

    private int x;
    private int y;
    private int ID;
    private double direction;
    private int speed;
    private int damage;

    public Bullet(int x, int y, int ID, double direction, int damage, int bulletSpeed){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.direction = direction;
        this.speed = bulletSpeed;
        this.damage = damage;
        System.out.println("Shot fired!");
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getID(){
        return ID;
    }

    public int getSpeed(){
        return speed;
    }

    public int getDamage(){
        return damage;
    }

    public void update(ArrayList<ServerZombie> zombies, ArrayList<Point> walls){

        checkCollisionWithWalls(walls);
        checkZombieCollision(zombies);

        x = x + (int)(speed*Math.cos(direction));
        y = y + (int)(speed*Math.sin(direction));
    }

    private void checkZombieCollision(ArrayList<ServerZombie> zombies){
        for(ServerZombie zombie: zombies){
            if(new Circle(zombie.getX(),zombie.getY(),ServerZombie.getRadius()).contains(this.x,this.y)){
                zombie.takeDamage(this.damage);
                this.speed = 0;
            }
        }
    }

    private void checkCollisionWithWalls(ArrayList<Point> walls){
        int tileWidth = WorldHandler.getTileWidth();
        for(Point wall: walls){
            if(new Rectangle((int)(wall.getX()),(int)(wall.getY()), tileWidth, tileWidth).contains(this.x,this.y)){
                this.speed = 0;
            }
        }
    }

}
