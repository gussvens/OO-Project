package server.serverTiles;

/**
 * Created by Marcus on 2016-04-09.
 */
public class SolidTile extends ServerTile {

    private int x;
    private int y;
    private boolean passable = false;

    public SolidTile(int x, int y){
        super(x,y);
    }

    public boolean getPassable(){
        return passable;
    }
}
