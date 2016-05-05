package zombienado_v1.server.model.serverUnits;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Gustav on 16-04-13.
 */
public class ServerPlayerTest extends TestCase {

    @Test
    public void testUpdate(){
        ServerPlayer sP = new ServerPlayer(0,0,0.0,0);
        ArrayList<ArrayList<Boolean>> solidMap = new ArrayList<ArrayList<Boolean>>();
        sP.update(1,2,3.4,solidMap);
        assertTrue(sP.getX() == 1);
        assertTrue(sP.getY() == 2);
    }
}