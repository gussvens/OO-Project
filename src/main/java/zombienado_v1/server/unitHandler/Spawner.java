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
    private int wave = 1;
    private int amountOfZombies = 20;
    private int timeBetweenWaves;

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

        System.out.println("Wave: " + wave + " Zombies left: " + amountOfZombies);

        if(amountOfZombies != 0 || !zombies.isEmpty()) {
            for (int i = getZombies().size() - 1; i >= 0; i--) {


                double shortestDistance = 100000000;
                double yDirection = 0;
                double xDirection = 0;
                double rotation = 0;

                for (ServerPlayer player : positions) {
                    double tempX = player.getX() - zombies.get(i).getX();
                    double tempY = player.getY() - zombies.get(i).getY();
                    double distance = Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));

                    if (Math.abs(distance) < shortestDistance) {
                        shortestDistance = distance;
                        xDirection = tempX / shortestDistance;
                        yDirection = tempY / shortestDistance;
                        rotation = Math.atan2(tempY, tempX);
                    }
                }

                zombies.get(i).update(xDirection, yDirection, rotation, zombies, walls);
                if (getZombies().get(i).getHealth() <= 0) {
                    getZombies().remove(i);
                    for (ServerPlayer p : positions) {
                        p.addBalance(10);
                    }
                }
            }

            if (lapCounter == 30) {

                if(amountOfZombies > 0) {

                    int point = (int) (Math.random() * spawnPoints.size());


                    zombies.add(new ServerZombie(idCounter, spawnPoints.get(point)));
                    amountOfZombies--;

                    idCounter++;
                    lapCounter = 0;
                }

            } else {
                lapCounter++;
            }
        } else {
            if(timeBetweenWaves == 1200){
                wave++;
                amountOfZombies += wave;
                zombies.clear();
                timeBetweenWaves = 0;
                System.out.println("New Wave: " + wave);
            } else {
                timeBetweenWaves++;
                System.out.println("Time to next wave: " + timeBetweenWaves);
            }
        }

    }


    public int getWave(){
        return wave;
    }


    public synchronized ArrayList<ServerZombie> getZombies(){
        return zombies;
    }

}
