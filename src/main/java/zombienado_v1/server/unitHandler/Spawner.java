package zombienado_v1.server.unitHandler;

import zombienado_v1.server.serverUnits.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class Spawner{

    private static ArrayList<ServerZombie> zombies;
    private static int idCounter = 0;
    private static Spawner instance;
    private int lapCounter;

    private Spawner(){
        zombies = new ArrayList<ServerZombie>();
    }

    public static Spawner getInstance(){
        if(instance == null){
            instance = new Spawner();
        }
        return instance;
    }

    public void update(ArrayList<ServerPlayer> positions, ArrayList<Point> spawnPoints, ArrayList<Point> walls) {
        for(ServerZombie zombie : zombies){

            double shortestDistance = 100000000;
            double yDirection = 0;
            double xDirection = 0;
            double rotation = 0;

            for(ServerPlayer player : positions) {
                double tempX = player.getX() - zombie.getX();
                double tempY = player.getY() - zombie.getY();
                double distance = Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));

                if(Math.abs(distance) < shortestDistance){
                    shortestDistance = distance;
                    xDirection = tempX / shortestDistance;
                    yDirection = tempY / shortestDistance;
                    rotation = Math.atan2(tempY, tempX);
                }
            }

            zombie.update(xDirection, yDirection, rotation, zombies, walls);

        }

        if (lapCounter == 30) {

            int point = (int)(Math.random() * spawnPoints.size());


            zombies.add(new ServerZombie(idCounter, spawnPoints.get(point)));

            idCounter++;
            lapCounter = 0;

        } else {
            lapCounter ++;
        }
    }





    public synchronized ArrayList<ServerZombie> getZombies(){
        return zombies;
    }

}
