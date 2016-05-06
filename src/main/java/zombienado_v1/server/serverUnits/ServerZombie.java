package zombienado_v1.server.serverUnits;


import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie implements ServerUnit{
    private static final int RADIUS = 32;

    private int x;
    private int y;
    private double rotation;
    private int speed;
    private int[] temp;
    private int id;

    public ServerZombie(int id, Point spawnPoint){
        speed = 4;
        x = (int)spawnPoint.getX();
        y = (int)spawnPoint.getY();
        rotation = 1;
        this.id = id;
        System.out.println("New zombie spawned! X: " + x + ", Y: " + y);

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

    public void update(double xDirection, double yDirection, double rotation, ArrayList<ServerZombie> zombies, ArrayList<Point> walls){
        int tileWidth = WorldHandler.getTileWidth();
        double tempX = xDirection * speed;
        double tempY = yDirection * speed;

        this.rotation = rotation;
        int xOld = this.x;
        int yOld = this.y;
        this.x += (int)tempX;
        this.y += (int)tempY;

        int tileX =(this.x/tileWidth) -1;
        int tileY =(this.y/tileWidth) -1;

        for(ServerZombie zombie:zombies){
            boolean overlaps = true;

            while( this.getID()!=zombie.getID() && overlaps && (this.x == xOld && this.y == yOld)){
                if(!Physics.collidesWithUnit(this.x,this.y,RADIUS,zombie.getX(),zombie.getY(),RADIUS)){
                    overlaps = false;
                } else{
                    this.x = (int)(xOld + 0.9*tempX);
                    this.y = (int)(yOld + 0.9*tempY);
                }
            }
        }

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                int a = (tileX + i)*tileWidth;
                int b = (tileY + j)*tileWidth;
                if(a>=0 && b>=0){
                    if(walls.contains(new Point(a,b))){
                        if(Physics.collidesWithWall(this.x,yOld,RADIUS,new Rectangle(a,b,tileWidth,tileWidth))){
                            this.x = xOld;
                            int temp = (int)(yOld + yDirection/Math.abs(yDirection) * speed);
                            if(!Physics.collidesWithWall(this.x,temp,RADIUS,new Rectangle(a,b,tileWidth,tileWidth))){
                                this.y = temp;
                            }
                        }
                        if(Physics.collidesWithWall(xOld,this.y,RADIUS,new Rectangle(a,b,tileWidth,tileWidth))){
                            this.y = yOld;
                            int temp = (int)(xOld + xDirection/Math.abs(xDirection) * speed);
                            if(!Physics.collidesWithWall(temp,this.y,RADIUS,new Rectangle(a,b,tileWidth,tileWidth))){
                                this.x = temp;
                            }
                        }
                    }
                }
            }
        }
    }

}
