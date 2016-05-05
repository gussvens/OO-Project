package zombienado_v1.client.proxyModel.Units;

import java.awt.*;

import zombienado_v1.Interface.IUnit;

/**
 * A class representing a unit in the proxy-model
 */
public abstract class Unit implements IUnit{
	private Image sprite; //TODO: remove
	private int id;
	private double rotation;
	private int x;
	private int y;
	private float radius;

	public Unit(){}

	public Unit(int x, int y, double rotation, int id, float radius){
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.id = id;
		this.radius = radius;
	}

	/**
	 * Sets position of unit in x and y plane
	 * @param x New coordinate along x axis
	 * @param y New coordinate along y axis
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets position of unit in x and y plane
	 * @param position New position in x and y plane
	 */
	public void setPosition(Point position) {
		this.x = (int)position.getX();
		this.y = (int)position.getY();
	}

	/**
	 * Sets rotation of unit in radians anti-clockwise
	 * @param rot New rotation of unit in radians anti-clockwise
	 */
	public void setRotation(double rot) {
		this.rotation = rot;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getX() {
		return x;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getY() {
		return y;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getId(){
		return id;
	}
}
