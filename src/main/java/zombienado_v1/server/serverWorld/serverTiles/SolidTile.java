package zombienado_v1.server.serverWorld.serverTiles;

/**
 * Created by Marcus on 2016-04-09.
 */
public class SolidTile extends ServerTile {

    private static boolean PASSABLE = false;

    public SolidTile(int x, int y){
        super(x,y,PASSABLE);
    }
}
