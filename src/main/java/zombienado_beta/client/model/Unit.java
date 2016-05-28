package zombienado_beta.client.model;

import java.awt.*;

/**
 * A class to represent a unit on the client side of the application
 */
public abstract class Unit {
	private float rotation;
	private int x;
	private int y;
	private long lastUpdate;

	/**
	 * Sets the x and y coordinate of the unit
	 * @param x New coordinate along the x axis
	 * @param y New coordinate along the y axis
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the coordinate of the unit
	 * @param position New coordinate along xy plane
	 */
	public void setPosition(Point position) {
		this.x = (int)position.getX();
		this.y = (int)position.getY();
	}

	/**
	 * Sets the rotation of the unit
	 * @param rot The new rotation of the unit
	 */
	public void setRotation(float rot) {
		this.rotation = rot;
	}

	/**
	 * Sets when the unit was last updated
	 * @param timeStamp When the unit was last updated
	 */
	public void setLastUpdate(long timeStamp){
		this.lastUpdate = timeStamp;
	}

	/**
	 * Returns when the unit was last updated
	 * @return When the unit was last updated
	 */
	public long getLastUpdate(){
		return this.lastUpdate;
	}

	/**
	 * Returns the units coordinate along the x axis
	 * @return The units coordinate along the x axis
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the units coordinate along the y axis
	 * @return The units coordinate along the y axis
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the units rotation
	 * @return The units rotation
	 */
	public float getRotation() {
		return rotation;
	}
}
