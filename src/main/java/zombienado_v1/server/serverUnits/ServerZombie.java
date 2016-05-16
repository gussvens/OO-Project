package zombienado_v1.server.serverUnits;

import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie implements ServerUnit{
    private static final int RADIUS = 16;

    private int x;
    private int y;
    private int id;
    private int health;
    private double rotation;
    private double speed;


    public ServerZombie(int id, Point spawnPoint){
        this.speed = Math.random()*1 + 2;
        this.x = (int)spawnPoint.getX();
        this.y = (int)spawnPoint.getY();
        this.rotation = 1;
        this.id = id;
        this.health = 100;
        System.out.println("New zombie spawned! X: " + x + ", Y: " + y);
    }

    public static int getRadius(){
        return RADIUS;
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

    public double getRotation(){
        return rotation;
    }

    public int getHealth(){
        return this.health;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }


    public void update(double xDirection, double yDirection, double rotation, ArrayList<ServerZombie> zombies, ArrayList<Point> walls){
        this.rotation = rotation;
        int xOld = this.x;
        int yOld = this.y;

        double xDiff = xDirection * speed;
        double yDiff = yDirection * speed;

        this.x += (int)xDiff;
        this.y += (int)yDiff;

        checkZombiesCollisions(zombies);
        checkWallsCollisions(xOld, yOld, walls);
    }

    public void checkZombiesCollisions(ArrayList<ServerZombie> zombies){
        for(ServerZombie zombie: zombies){
            if (this.getID()!=zombie.getID()){
                Point bounce = Physics.bounce(this.x,this.y,RADIUS,zombie.getX(),zombie.getY(),RADIUS);
                this.x += bounce.getX();
                this.y += bounce.getY();
            }
        }

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
        double xDiff = this.x - xOld;
        double yDiff = this.y - yOld;

        if(Physics.collidesWithWall(this.x,yOld,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.x = xOld;
            int temp = (int)(yOld + yDiff*0.5);
            if(Physics.collidesWithWall(this.x,temp,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
                this.y = temp;
            }
        }
        if(Physics.collidesWithWall(xOld,this.y,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.y = yOld;
            int temp = (int)(xOld + xDiff*0.5);
            if(Physics.collidesWithWall(temp,this.y,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
                this.x = temp;
            }
        }
    }
}
