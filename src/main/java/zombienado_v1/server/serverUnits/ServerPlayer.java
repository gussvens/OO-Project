package zombienado_v1.server.serverUnits;

import zombienado_v1.client.model.weapon.Gun;
import zombienado_v1.client.model.weapon.Weapon;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerPlayer implements ServerUnit{

    private int x;
    private int y;
    private double r;
    private int id;
    private Weapon weapon;

    public ServerPlayer(int x, int y, double r, int id){
        this.x = x;
        this.y = y;
        this.r = r;
        this.id = id;

        //TODO: move weapons to server
        this.weapon = new Gun();
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

    public void update(int x, int y, double r, boolean isShooting){
        this.x = this.x + x;
        this.y = this.y + y;
        this.r = this.r + r;

        if(isShooting == true){
            weapon.shoot();
        }
    }



}
