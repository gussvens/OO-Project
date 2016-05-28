package zombienado_beta.server.serverUnits;

import zombienado_beta.server.serverWorld.WorldHandler;
import zombienado_beta.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class that represents a Zombie from the client
 */
public class ServerZombie implements ServerUnit{
    private static final int RADIUS = 16;

    private float x;
    private float y;
    private int health;
    private float rotation;
    private double speed;


    /**
     * Constructor for ServerZombie
     * @param spawnPoint - A Point where the zombie can spawn
     */
    public ServerZombie(Point spawnPoint){
        this.speed = 2;
        this.x = (int)spawnPoint.getX()+32;
        this.y = (int)spawnPoint.getY()+32;
        this.rotation = 1;
        this.health = 100;
    }

    //Getters
    public static int getRadius(){
        return RADIUS;
    }

    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }

    public float getRotation(){
        return rotation;
    }

    public int getHealth(){
        return this.health;
    }

    /**
     * A method that reduces the zombies health by a certain amount
     * @param damage - The amount of health removed
     */
    public void takeDamage(int damage){
        this.health -= damage;
    }

    /**
     * An update method that moves the zombie in a certain direction
     * @param xDirection - How many pixels to move in the x-axis
     * @param yDirection - How many pixels to move in the y-axis
     * @param rotation - The ServerZombie's new rotation
     * @param zombies - An ArrayList of all ServerZombies alive
     * @param walls - An ArrayList of all walls on the map
     */
    public void update(double xDirection, double yDirection, float rotation, ArrayList<ServerZombie> zombies, ArrayList<Point> walls){
        this.rotation = rotation;
        int xOld = (int)this.x;
        int yOld = (int)this.y;

        double xDiff = xDirection * speed;
        double yDiff = yDirection * speed;

        this.x += xDiff;
        this.y += yDiff;

        checkZombiesCollisions(zombies);
        checkWallsCollisions(xOld, yOld, walls);
    }

    /**
     * A method that checks for collision between ServerZombies
     * @param zombies - An ArrayList of all ServerZombies alive
     */
    public void checkZombiesCollisions(ArrayList<ServerZombie> zombies){
        for(ServerZombie zombie: zombies){
            if (this != zombie){
                Point bounce = Physics.bounce((int)this.x,(int)this.y,RADIUS,zombie.getX(),zombie.getY(),RADIUS);
                this.x += bounce.getX();
                this.y += bounce.getY();
            }
        }

    }

    /**
     * A method that checks for collision between a ServerZombie and walls
     * @param xOld - The x-posiiton before the ServerZombie moved
     * @param yOld - The y-posiiton before the ServerZombie moved
     * @param walls - An ArrayList with all walls on the map
     */
    public void checkWallsCollisions(int xOld, int yOld, ArrayList<Point> walls){
        int tileWidth = WorldHandler.getTileWidth();

        int tileX =((int)this.x/tileWidth) -1;
        int tileY =((int)this.y/tileWidth) -1;

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

    /**
     * A method that checks for collision between a wall and a ServerZombie
     * @param tileWidth - The width of a tile on the map
     * @param xOld - The x-posiiton before the ServerZombie moved
     * @param yOld - The y-posiiton before the ServerZombie moved
     * @param x - The current x position of the ServerZombie
     * @param y - The current y posiiton of the ServerZombie
     */
    public void checkSingleWallCollision(int tileWidth, int xOld, int yOld, int x, int y){
        double xDiff = this.x - xOld;
        double yDiff = this.y - yOld;

        if(Physics.collidesWithWall((int)this.x,yOld,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.x = xOld;
            int temp = (int)(yOld + yDiff*0.5);
            if(Physics.collidesWithWall((int)this.x,temp,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
                this.y = temp;
            }
        }
        if(Physics.collidesWithWall(xOld,(int)this.y,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.y = yOld;
            int temp = (int)(xOld + xDiff*0.5);
            if(Physics.collidesWithWall(temp,(int)this.y,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
                this.x = temp;
            }
        }
    }
}
