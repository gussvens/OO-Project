package server.serverUnits;


/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie {

    private int x;
    private int y;
    private int speed;
    private int[] temp;

    public ServerZombie(){
        speed = 10;
        x = 100;
        y = 100;
        System.out.println("New zombie spawned!");

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
