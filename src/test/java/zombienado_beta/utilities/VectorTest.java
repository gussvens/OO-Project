package zombienado_beta.utilities;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Gustav on 16-05-28.
 */
public class VectorTest extends TestCase {

    @Test
    public void testNormalize(){
        Vector vector = new Vector(2.5f,-11f);
        vector.normalize(1.0f);
        float length = (float)Math.sqrt(Math.pow(vector.getX(),2) + Math.pow(vector.getY(),2));
        assertTrue(length == 1f);
    }
}