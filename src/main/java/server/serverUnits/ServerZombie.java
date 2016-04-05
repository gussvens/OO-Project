package server.serverUnits;


/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie {

    private int x;
    private int y;
    private float rotation;
    private int speed;
    private int[] temp;
    private int id;

    public ServerZombie(int id){
        speed = 10;
        x = 100;
        y = 100;
        rotation = 1;
        this.id = id;
        System.out.println("New zombie spawned!");

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getId(){
        return id;
    }

    public float getRotation(){
        return rotation;
    }

}
