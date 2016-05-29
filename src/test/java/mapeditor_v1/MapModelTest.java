package mapeditor_v1;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marti on 2016-05-29.
 */
public class MapModelTest extends TestCase {

    @Test
    public void testFillMap() throws Exception {
        MapModel map = new MapModel("test",32,32);
        map.setSelectedTile(21);
        map.fillMap();
        for (int i = 0; i < 32; i++){
            for(int j = 0; j < 32 ; j++){
                assertTrue(map.getTile(i,j) == 21);
            }
        }
    }

    @Test
    public void testClearMap() throws Exception {
        MapModel map = new MapModel("test",32,32);
        map.setSelectedTile(21);
        map.fillMap();
        map.clearMap();
        for (int i = 0; i < 32; i++){
            for(int j = 0; j < 32 ; j++){
                assertTrue(map.getTile(i,j) == 00);
            }
        }
    }

}