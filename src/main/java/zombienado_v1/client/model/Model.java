package zombienado_v1.client.model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import zombienado_v1.client.controller.Client;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.PlayerInputHandler;

public class Model {
	//private oldPlayer player;
	private ServerCommunicator coms;
	private ArrayList<Unit> players;
	private ArrayList<Unit> zombies;
	private int myID = -1;
	/**
	 * Getters for view
	 */
	public synchronized List<Unit> getPlayers(){
		return players;
	}
	public synchronized List<Unit> getZombies(){
		return zombies;
	}

	/** INITIALIZATION
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
	 * Used for all client sided game logic and events
	 * @param pressedKeys - Currently pressed keys
	 * @param cursor - Cursors position
	 * @param isMousePressed
	 */
	public synchronized void tick(List<Character> pressedKeys, Point cursor, boolean isMousePressed) {
		this.myID = coms.getID();
		if (myID == -1) return;
		if(this.getPlayers() == null) return;

		this.players = coms.getPlayers();
		this.zombies = coms.getZombies();
		Camera.setX(players.get(myID).getX());
		Camera.setY(players.get(myID).getY());
		Point velocityVector = PlayerInputHandler.getPlayerVelocity(pressedKeys);
		double deltaRotation = PlayerInputHandler.getPlayerRotation(players.get(myID).getX(), players.get(myID).getY(), cursor) - players.get(myID).getRotation();
		try {
			coms.movePlayer((int)velocityVector.getX(), (int)velocityVector.getY(), deltaRotation);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}