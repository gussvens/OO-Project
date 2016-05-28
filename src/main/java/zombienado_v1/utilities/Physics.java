package zombienado_v1.utilities;


import java.awt.Point;
import java.awt.Rectangle;

public class Physics {
	/**
	 * Checks if a unit is colliding with a wall if standing at given coordinates
	 * @param xUnit Coordinate along x axis for unit
	 * @param yUnit Coordinate along y axis for unit
	 * @param radiusUnit Radius for unit
	 * @param wall Wallpice possibly overlapped by unit
	 * @return True if unit is overlapping the wall
	 */
	public static boolean collidesWithWall(int xUnit, int yUnit, float radiusUnit, Rectangle wall){
		Rectangle rect = new Rectangle(xUnit - (int)radiusUnit, yUnit - (int)radiusUnit, 2*(int)radiusUnit, 2*(int)radiusUnit);
		if (rect.intersects(wall)) return true;
		return false;
	}
	
	/**
	 * Returns a bounce vector that prevents overlap between two units
	 * @param xThisUnit Coordinate along x axis for this unit
	 * @param yThisUnit Coordinate along y axis for this unit
	 * @param radiusThisUnit Radius for this unit
	 * @param xOtherUnit Coordinate along x axis for other unit
	 * @param yOtherUnit Coordinate along y axis for other unit
	 * @param radiusOtherUnit Radius for other unit
	 * @return
	 */
	public static Point bounce(int xThisUnit, int yThisUnit, float radiusThisUnit, int xOtherUnit, int yOtherUnit, float radiusOtherUnit){
		double dX = xOtherUnit-xThisUnit;
		double dY = yOtherUnit-yThisUnit;
		double actualDistance = Math.sqrt( (dX)*(dX) + (dY)*(dY) );
		dX /= actualDistance;
		dY /= actualDistance;
		double allowedDistance = radiusThisUnit + radiusOtherUnit;
		double overlap = allowedDistance - actualDistance;

		if(overlap<=0) return new Point(0,0);

		dX *= overlap;
		dY *= overlap;
		return new Point((int)(-dX), (int)(-dY));
	}
}

