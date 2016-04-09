package server.serverUnits;


/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie {

    private int x;
    private int y;
    private double rotation;
    private int speed;
    private int[] temp;
    private int id;

    public ServerZombie(int id){
        speed = 4;
        x = 0;
        y = 0;
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

    public double getRotation(){
        return rotation;
    }

    public void update(){
        x = x + speed;
        y = y + speed;
        //rotation = rotation +1; //Kommenterade ut bara för att testa lite :-) /Erkan
    }

}
