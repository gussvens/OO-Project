package zombienado_beta.client.model;

import java.awt.*;

public abstract class Unit {
	private Image sprite; //TODO: remove
	private float rotation;
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
	
	public void setRotation(float rot) {
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

	public float getRotation() {
		return rotation;
	}
}
