package zombienado_v1.server;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Gustav on 16-04-13.
 */
public class ServerTest extends TestCase {

    @Test
    public void testGetInstance(){
        Server s = Server.getInstance(1, "Hej");
        assertTrue(s != null);
    }

    //@Test
    //public void test

}