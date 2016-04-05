package server.serverUnits;

import javax.swing.text.Position;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerZombie {

    private Position pos;
    private int speed;

    public ServerZombie(){
        speed = 10;
        System.out.println("New zombie spawned!");

    }

    public Position getPosition(){
        return pos;
    }

}
