package server.serverTiles;

/**
 * Created by Marcus on 2016-04-09.
 */
public class SpawnerTile extends ServerTile {

    private static final boolean PASSABLE = true;

    public SpawnerTile(int x, int y){
        super(x,y,PASSABLE);
    }
}
