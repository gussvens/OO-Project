package server.threads;

import server.serverUnits.ServerZombie;

import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class SpawnerThread extends Thread{

    private static ArrayList<ServerZombie> zombies;
    private static int idCounter = 0;

    public SpawnerThread(){
        zombies = new ArrayList<ServerZombie>();
    }

    public void run(){
        while(true){

            if(zombies.size() < 34) {

                zombies.add(new ServerZombie(idCounter));

                idCounter++;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static synchronized ArrayList<ServerZombie> getZombies(){
        return zombies;
    }

}
