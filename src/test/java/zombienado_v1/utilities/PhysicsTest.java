package zombienado_v1.utilities;

import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;


/**
 * 
 * @author Erik
 *
 */
public class PhysicsTest extends TestCase {
    @Test
    public void testcollidesWithWall(){
		Rectangle wall = new Rectangle(0,0,32,32);
		int xA = 0;
		int xB = 64;
		int yA = 0;
		int yB = 64;
		float radius = 32f;

		assertTrue(Physics.collidesWithWall(xA,yA,radius,wall));
		assertTrue(!Physics.collidesWithWall(xB,yA,radius,wall));
		assertTrue(!Physics.collidesWithWall(xA,yB,radius,wall));
		assertTrue(!Physics.collidesWithWall(xB,yB,radius,wall));
    }

	@Test
	public void testBounce(){
		int thisXA = 0;
		int thisXB = 64;
		int thisYA = 0;
		int thisYB = 64;
		float thisRadius = 32f;

		int otherX = 10;
		int otherY = 10;
		float otherRadius = 16f;
		Point p1 = Physics.bounce(thisXA,thisYA,thisRadius,otherX,otherY,otherRadius);
		Point p2 = Physics.bounce(thisXB,thisYB,thisRadius,otherX,otherY,otherRadius);

		assertTrue(p1.getX() < 0 || p1.getY() < 0);
		assertTrue(p2.getX() == 0 && p2.getY() == 0);
	}
}