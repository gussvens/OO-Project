package zombienado_v1.server.serverUnits;


import zombienado_v1.server.serverWorld.serverTiles.SpawnerTile;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie implements ServerUnit{

    private int x;
    private int y;
    private double rotation;
    private int speed;
    private int[] temp;
    private int id;

    public ServerZombie(int id, SpawnerTile spawnPoint){
        speed = 4;
        x = spawnPoint.getX();
        y = spawnPoint.getY();
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

    public void update(double xDirection, double yDirection, double rotation){
        double tempX = xDirection * speed;
        double tempY = yDirection * speed;

        this.rotation = rotation;
        x = x + (int)tempX;
        y = y + (int)tempY;
        //rotation = rotation +1; //Kommenterade ut bara för att testa lite :-) /Erkan
    }

}
