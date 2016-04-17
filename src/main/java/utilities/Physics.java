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
	public static int collisionX(int x, int y, int radius, Rectangle r){
		if (y + radius > r.getY() + 2 && y - radius < r.getY() + r.getHeight() - 2) {
			if (x + radius > r.getX() && 
					x + radius < r.getX() + r.getWidth()/2 &&
					y + radius > r.getY() &&
					y - radius < r.getY() + r.getHeight()){
				return (int)r.getX() - radius;
			} else if (x - radius < r.getX() + r.getWidth() &&
					x - radius > r.getX() + r.getWidth()/2 &&
					y + radius > r.getY() &&
					y - radius < r.getY() + r.getHeight()){
				return (int)(r.getX() + r.getWidth() + radius);
			}
		}
		return x;
	}

	public static int collisionY(int x, int y, int radius, Rectangle r){
		if (x + radius > r.getX() + 2 && x - radius < r.getX() + r.getWidth() - 2) {
			if (y + radius > r.getY() &&
					y + radius < r.getY() + r.getHeight()/2 &&
					x + radius > r.getX() &&
					x - radius < r.getX() + r.getWidth()){
				return (int)r.getY() - radius;
			} else if (y - radius < r.getY() + r.getHeight() &&
					y - radius > r.getY() + r.getHeight()/2  &&
					x + radius > r.getX() &&
					x - radius < r.getX() + r.getWidth()){
				return (int)(r.getY() + r.getHeight() + radius);
			}
		}
		return y;
	}

	public static Point getAntiCollisionVector(int xPlayer, int yPlayer, float radiusPlayer, Rectangle rect){
		int cL = (int)(Math.sqrt(32*32/2)); //composant length
		double vDist = rect.getHeight()/2;
		double hDist = rect.getWidth()/2;

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
					vectorX = ( vectorX/(Math.abs(vectorX)) ) * (hDist-Math.abs(vectorX) + 2);
					vectorY = 0;
				}else{
					vectorX = 0;
					vectorY = ( vectorY/(Math.abs(vectorY)) ) * (vDist-Math.abs(vectorY) + 2);
				}
				return new Point((int)vectorX,(int)vectorY);
			}
		}
		return new Point(0,0);
	}


	public static Point getAntiCollisionVector(int xPlayer, int yPlayer, float radiusPlayer, int xOther, int yOther, float radiusOther){
		double vectorX = xOther - xPlayer;
		double vectorY = yOther - yPlayer;
		double length = Math.sqrt( vectorX*vectorX + vectorY*vectorY );
		double overlap = radiusPlayer + radiusOther - length;

		if(overlap<=0){
			return new Point(0,0);
		}

		vectorX = (vectorX/length)*overlap;
		vectorY = (vectorY/length)*overlap;

		return new Point((int)vectorX,(int)vectorY);
	}

	public static Point addVector(Point vectorA, Point vectorB){
		double x = vectorA.getX() + vectorB.getX();
		double y = vectorA.getY() + vectorB.getY();
		return new Point((int)x,(int)y);
	}
}

