package zombienado_v1.server.model.serverUnits;

import zombienado_v1.Interface.IWeapon;
import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class representing a player in the model
 * Created by Marcus on 2016-04-05.
 */
public class ServerPlayer extends ServerUnit {
    private static final int RADIUS = 32;

    private IWeapon weapon;
    private int x;
    private int y;
    private double rotation;
    private int id;

    public ServerPlayer(int x, int y, double rotation, int id){
        super(x,y,rotation,id,RADIUS);
    }

    /**
     * Returns current weapon
     * @return Return
     */
    public IWeapon getWeapon(){
        return weapon;
    }

    /**
     * {@inheritDoc}
     */
    public void update(double x, double y, double rotation, ArrayList<ArrayList<Boolean>> solidMap){
        int tileWidth = WorldHandler.getTileWidth();

        int xOld = this.x;
        int yOld = this.y;
        this.x = this.x + (int)x;
        this.y = this.y + (int)y;
        this.rotation = rotation;

        int tileX =(int) (x / WorldHandler.getTileWidth() - 1);
        int tileY =(int) (y / WorldHandler.getTileWidth() - 1);

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
