package utilities;

import client.model.Player;
import client.model.Unit;

import java.awt.Point;
import java.awt.Rectangle;

public class Physics {
	/**
	 * Calculates intersection 
	 * Might want to changes this....
	 * @param x
	 * @param y
	 * @param radius
	 * @param r
	 * @return the retardationVector if intersects, null otherwise
	 */
	public static Point collision(int x, int y, int radius, Rectangle r){
		return new Point(0, 0);
	}

	public static Point getAntiCollisionVector(int xPlayer, int yPlayer, int radiusPlayer, Rectangle rect){
		int cL = (int)(Math.sqrt(32*32/2)); //composant length

		//8 collision points around the player
		Point rightSide = new Point(xPlayer+32,yPlayer);
		Point rightTopSide = new Point(xPlayer+cL,yPlayer-cL);
		Point topSide = new Point(xPlayer,yPlayer-32);
		Point leftTopSide = new Point(xPlayer-cL,yPlayer-cL);
		Point leftSide = new Point(xPlayer-32,yPlayer);
		Point leftBottomSide = new Point(xPlayer-cL,yPlayer+cL);
		Point bottomSide = new Point(xPlayer,yPlayer+32);
		Point rightBottomSide = new Point(xPlayer+cL,yPlayer+cL);

		Point[] collisionPoints = new Point[]{rightSide,rightTopSide,topSide,leftTopSide,leftSide,leftBottomSide,bottomSide,rightBottomSide};

		for(Point p : collisionPoints){
			if(rect.contains(p)){
				double vectorX = rect.getCenterX()-p.getX();
				double vectorY = rect.getCenterY()-p.getY();
				if(Math.abs(vectorX)>=Math.abs(vectorY)){
					vectorX = ( vectorX/(Math.abs(vectorX)) ) * (48-Math.abs(vectorX));
					vectorY = 0;
				}else{
					vectorX = 0;
					vectorY = ( vectorY/(Math.abs(vectorY)) ) * (48-Math.abs(vectorY));
				}
				return new Point((int)vectorX,(int)vectorY);
			}
		}
		return new Point(0,0);
	}


	public static Point getAntiCollisionVector(int xPlayer, int yPlayer, int radiusPlayer, int xOther, int yOther, int radiusOther){
		double vectorX = xPlayer - xOther;
		double vectorY = yPlayer - yOther;
		double length = Math.sqrt( vectorX*vectorX + vectorY*vectorY );
		double overlap = radiusPlayer + radiusOther - length;

		if(overlap<=0){
			return new Point(0,0);
		}

		vectorX = (vectorX/length*overlap);
		vectorY = (vectorY/length*overlap);

		return new Point((int)vectorX,(int)vectorY);
	}
}

