package zombienado_v1.client.model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import zombienado_v1.client.controller.Client;
import zombienado_v1.utilities.Camera;

public class Model {
	//private oldPlayer player;
	private ArrayList<Unit> players;
	private ArrayList<Unit> zombies;
	private int myID = -1;
	private ServerCommunicator coms;
	/**
	 * Getters
	 */
	public synchronized List<Unit> getPlayers(){
		return players;
	}

	public synchronized List<Unit> getZombies(){
		return zombies;
	}

	//public synchronized oldPlayer getPlayer(){
	// return player;
	//}

	/** INITIALIZATION/LOAD.
	 * Executed before gameloop starts
	 */
	public synchronized void initialize(){
		try {
			coms = ServerCommunicator.getInstance();
		} catch (Exception e){
			e.printStackTrace();
		}
		players = new ArrayList<Unit>();
		zombies = new ArrayList<Unit>();
		for (int i = 0; i < 4; i++){ // Test. Creates 4 players in order to match ID to index.
			players.add(null);
		}
	}

	/** MAIN UPDATE METHOD
	 * Used for all game logic and events
	 * @param pressedKeys - Currently pressed keys
	 * @param cursor - Cursors position
	 * @param isMousePressed
	 */
	public synchronized void tick(List<Character> pressedKeys, Point cursor, boolean isMousePressed) {
		this.myID = coms.getID();

		if(coms.getPlayers() == null && this.getPlayers() == null){
			return;
		}

		this.players = coms.getPlayers();
		this.zombies = coms.getZombies();
		if (myID == -1) return;
		if (players.get(myID) == null){
			players.set(myID, new Player());
		}
		//players.get(myID).setPosition(comms.getPosition());
		//players.get(myID).setPosition(getPlayerVelocity(pressedKeys));
		//players.get(myID).setRotation(getPlayerRotation(cursor));
		Camera.setX(players.get(myID).getX());
		Camera.setY(players.get(myID).getY());
		Point velocityVector = getPlayerVelocity(pressedKeys);
		try {
			coms.movePlayer((int)velocityVector.getX(), (int)velocityVector.getY(), getPlayerRotation(cursor));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public double getPlayerRotation(Point cursor){
		int dX = (int)(cursor.getX() - players.get(myID).getX() + Camera.getX());
		int dY = (int)(cursor.getY() - players.get(myID).getY() + Camera.getY());
		return Math.atan2(dY, dX);
	}

	public Point getPlayerVelocity(List<Character> pressedKeys){
		int totalSpeed = 2;
		int speedX = 0;
		int speedY = 0;
		for (char key : pressedKeys){
			switch (key){
				case 'w':
				case 'W':
					speedY=-1;
					break;
				case 'a':
				case 'A':
					speedX=-1;
					break;
				case 's':
				case 'S':
					speedY=1;
					break;
				case 'd':
				case 'D':
					speedX=1;
					break;
			}
		}
		return new Point(speedX*totalSpeed, speedY*totalSpeed);
	}
}