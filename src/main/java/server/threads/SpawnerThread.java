package server.threads;

import server.serverUnits.ServerZombie;

import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class SpawnerThread{

    private static ArrayList<ServerZombie> zombies;
    private static int idCounter = 0;
    private static SpawnerThread instance;
    private int lapCounter;

    private SpawnerThread(){
        zombies = new ArrayList<ServerZombie>();
    }

    public static SpawnerThread getInstance(){
        if(instance == null){
            instance = new SpawnerThread();
        }
        return instance;
    }

    public void update() {

        for(ServerZombie zombie : zombies){
            zombie.update();
        }

        if (lapCounter == 30) {
            if (zombies.size() < 34) {

                zombies.add(new ServerZombie(idCounter));

                idCounter++;
                lapCounter = 0;
            }
        } else {
            lapCounter ++;
        }
    }





    public synchronized ArrayList<ServerZombie> getZombies(){
        return zombies;
    }

}
