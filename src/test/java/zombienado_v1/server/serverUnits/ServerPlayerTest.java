package zombienado_v1.server.serverUnits;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Gustav on 16-04-13.
 */
public class ServerPlayerTest extends TestCase {

    @Test
    public void testUpdate(){
        ServerPlayer sP = new ServerPlayer(0,0,0.0,0);
        sP.update(1,2,3,4);
        assertTrue(sP.getX() == 1);
        assertTrue(sP.getY() == 2);
    }
}