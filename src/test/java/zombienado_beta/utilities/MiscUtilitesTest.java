package zombienado_beta.utilities;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Gustav on 16-05-28.
 */
public class MiscUtilitesTest extends TestCase {

    @Test
    public void testGetCertanNumberArray(){
        int theNumber = 123456789;
        int[] theNumberArray = MiscUtilites.getCertanNumberArray(10, theNumber);
        for(int i = 0; i<10; i++){
            assertTrue(theNumberArray[i] == i);
        }
    }
}