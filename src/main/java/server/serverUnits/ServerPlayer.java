package server.serverUnits;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerPlayer {

    private int x;
    private int y;
    private float r;
    private int id;

    public ServerPlayer(int x, int y, float r, int id){
        this.x = x;
        this.y = y;
        this.r = r;
        this.id = id;
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
        return r;
    }

    public void update(int x, int y, float r){
        this.x = x;
        this.y = y;
        this.r = r;
    }



}
