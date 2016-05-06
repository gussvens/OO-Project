package zombienado_v1.server.serverUnits;

import zombienado_v1.interfaces.iWeapon;
import zombienado_v1.server.serverWeapon.ServerGun;
import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerPlayer implements ServerUnit{
    private static final int RADIUS = 32;

    private int x;
    private int y;
    private double r;
    private int id;
    private iWeapon weapon;

    public ServerPlayer(int x, int y, double r, int id){
        this.x = x;
        this.y = y;
        this.r = r;
        this.id = id;

        //TODO: move weapons to server
        this.weapon = new ServerGun();
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
        return r;
    }

    public void update(int x, int y, double r, ArrayList<Point> walls){
        int tileWidth = WorldHandler.getTileWidth();

        int xOld = this.x;
        int yOld = this.y;
        this.x += x;
        this.y += y;
        this.r += r;

        int tileX =(this.x/tileWidth)-1;
        int tileY =(this.y/tileWidth)-1;

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                int a = (tileX + i)*tileWidth;
                int b = (tileY + j)*tileWidth;
                if(a>=0 && b>=0){
                    if(walls.contains(new Point(a,b))){
                        if(Physics.collidesWithWall(this.x,yOld,RADIUS,new Rectangle(a,b,tileWidth,tileWidth))){
                            this.x = xOld;
                        }
                        if(Physics.collidesWithWall(xOld,this.y,RADIUS,new Rectangle(a,b,tileWidth,tileWidth))){
                            this.y = yOld;
                        }
                    }
                }
            }
        }
    }

    public Bullet shoot(){
        return weapon.shoot(x,y,r);
    }

}
