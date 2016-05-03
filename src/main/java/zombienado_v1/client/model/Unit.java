package zombienado_v1.client.model;

import java.awt.Graphics2D;
import java.awt.Image;

import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;

public abstract class Unit {
	private Image sprite; //TODO: remove
	private double rotation;
	private int x;
	private int y;
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setRotation(double rot) {
		this.rotation = rot;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getRotation() {
		return rotation;
	}
}
