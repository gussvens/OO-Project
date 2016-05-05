package zombienado_v1.server.model.serverUnits;


import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class representing a zombie in the model
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie extends ServerUnit {
    private static final int RADIUS = 32;

    private int x;
    private int y;
    private double rotation;
    private int speed;
    private int[] temp;
    private int id;

    public ServerZombie(int id, Point spawnPoint){
        super((int)spawnPoint.getX(),(int)spawnPoint.getY(),1,id,RADIUS);
        speed = 4;
        System.out.println("New zombie spawned! X: " + spawnPoint.getX() + ", Y: " + spawnPoint.getY());
    }

    /**
     * {@inheritDoc}
     */
    public void update(double xDirection, double yDirection, double rotation, ArrayList<ArrayList<Boolean>> solidMap){
        int tileWidth = WorldHandler.getTileWidth();
        int xOld = this.x;
        int yOld = this.y;

        double tempX = xDirection * speed;
        double tempY = yDirection * speed;

        this.rotation = rotation;
        this.x = x + (int)tempX;
        this.y = y + (int)tempY;

        int tileX =x / WorldHandler.getTileWidth() - 1;
        int tileY =y / WorldHandler.getTileWidth() - 1;

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                int a = tileX + i;
                int b = tileY + j;
                if(solidMap.get(a).get(b)){
                    if(Physics.collidesWithWall(this.x,yOld,RADIUS,new Rectangle(a*tileWidth,b*tileWidth,tileWidth,tileWidth))){
                        this.x = xOld;
                    }
                    if(Physics.collidesWithWall(xOld,this.y,RADIUS,new Rectangle(a*tileWidth,b*tileWidth,tileWidth,tileWidth))){
                        this.y = yOld;
                    }
                }
            }
        }
    }

}
