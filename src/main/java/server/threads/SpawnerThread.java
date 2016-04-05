package server.threads;

import server.serverUnits.ServerZombie;

import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class SpawnerThread extends Thread{

    private ArrayList<ServerZombie> zombies;

    public SpawnerThread(){
        zombies = new ArrayList<ServerZombie>();
    }

    public void run(){
        while(true){

            zombies.add(new ServerZombie());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<ServerZombie> getZombies(){
        return zombies;
    }

}
