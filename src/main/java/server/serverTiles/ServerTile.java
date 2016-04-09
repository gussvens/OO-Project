package server.serverTiles;

/**
 * Created by Marcus on 2016-04-09.
 */
public abstract class ServerTile {

    private int x;
    private int y;
    private boolean passable;

    public ServerTile(int x, int y){
        this.x = x;
        this.y = y;
        this.passable = passable;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public abstract boolean getPassable();
}
