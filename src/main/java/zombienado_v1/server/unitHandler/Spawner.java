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
    private static final int TIME_BETWEEN_WAVES = 10; //seconds
    private boolean timerOn = false;
    private int lapCounter;
    private int wave = 1;
    private int startingZombies = 50;
    private int amountOfZombies = 50;
    private long timeStamp;
    private long timeSinceLastWave;

    /**
     * Private constructor for Spawner
     */
    private Spawner(){
        zombies = new ArrayList<ServerZombie>();
        timeSinceLastWave = -1;
    }

    /**
     * A method that returns a Spawner. If no Spawner is created it creates one
     * @return - An instance of the S9pawner
     */
    public static Spawner getInstance(){
        if(instance == null){
            instance = new Spawner();
        }
        return instance;
    }

    /**
     * @return - The time between two waves. Returns -1 if the wave is still going
     */
    public int getTimeUntilNextWave(){
        if(!timerOn) {
            return -1;
        } else{
            return TIME_BETWEEN_WAVES - (int)(timeSinceLastWave/1000);
        }
    }

    /**
     * A method that updates the positions on all zombies and spawn a new one every 30 times this method is called.
     * This method also handles when a new wave starts.
     * @param positions - An ArrayList with all the players
     * @param spawnPoints - An ArrayList of points where zombies are allowed to spawn
     * @param walls - An ArrayList with points for all walls
     */
    public void update(ArrayList<ServerPlayer> positions, ArrayList<Point> spawnPoints, ArrayList<Point> walls) {

        if(amountOfZombies != 0 || !zombies.isEmpty()) {
            for (int i = getZombies().size() - 1; i >= 0; i--) {


                double shortestDistance = 100000000;
                double yDirection = 0;
                double xDirection = 0;
                float rotation = 0;

                for (ServerPlayer player : positions) {
                    if(!player.getIsDead()){
                        double tempX = player.getX() - zombies.get(i).getX();
                        double tempY = player.getY() - zombies.get(i).getY();
                        double distance = Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));

                        if (Math.abs(distance) < shortestDistance) {
                            shortestDistance = distance;
                            xDirection = tempX / shortestDistance;
                            yDirection = tempY / shortestDistance;
                            rotation = (float)Math.atan2(tempY, tempX);
                        }
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
            if(!timerOn){
                timeStamp = System.currentTimeMillis();
                timerOn = true;
            }
            timeSinceLastWave = System.currentTimeMillis() - timeStamp;

            if(this.getTimeUntilNextWave() <= 0){
                timerOn = false;
                timeSinceLastWave = -1;
                wave++;
                amountOfZombies = startingZombies + (wave*2);
                zombies.clear();
                System.out.println("New Wave: " + wave);
            }
        }

    }

    /**
     * @return - The current wave number
     */
    public int getWave(){
        return wave;
    }

    /**
     * @return - All spawned zombies
     */
    public synchronized ArrayList<ServerZombie> getZombies(){
        return zombies;
    }

}
