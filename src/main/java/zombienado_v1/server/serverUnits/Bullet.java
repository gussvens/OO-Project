package zombienado_v1.server.serverUnits;

/**
 * Created by Marcus on 2016-05-06.
 */
public class Bullet implements ServerUnit {

    private int x;
    private int y;
    private int ID;
    private double direction;

    public Bullet(int x, int y, int ID, double direction){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.direction = direction;
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

    public void update(){
        
    }


}
