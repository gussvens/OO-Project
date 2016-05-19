package zombienado_v1.client.model;

import java.awt.*;

import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;

public abstract class Unit {
	private Image sprite; //TODO: remove
	private double rotation;
	private int x;
	private int y;

	private long lastUpdate;

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPosition(Point position) {
		this.x = (int)position.getX();
		this.y = (int)position.getY();
	}
	
	public void setRotation(double rot) {
		this.rotation = rot;
	}

	public void setLastUpdate(long timeStamp){
		this.lastUpdate = timeStamp;
	}

	public long getLastUpdate(){
		return this.lastUpdate;
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
