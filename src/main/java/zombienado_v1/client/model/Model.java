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
	//	return player;
	//}

	/** INITIALIZATION/LOAD.
	 *	Executed before gameloop starts
	 */
	public synchronized void initialize(){
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
<<<<<<< HEAD
				/**
		if (myID == -1) return;
		if (players.get(myID) == null){
			players.set(myID, new Player());
		}
		//player.update(pressedKeys, cursor, isMousePressed)
		players.get(myID).setPosition(getPlayerVelocity(pressedKeys));
		players.get(myID).setRotation(getPlayerRotation(cursor));
		Camera.setX(players.get(myID).getX());
		Camera.setY(players.get(myID).getY());
		//TODO: Send velocity vector
		//Client.sendToServer(player.getParsedServerString());
	}

	public double getPlayerRotation(Point cursor){
		int dX = (int)(cursor.getX() - players.get(myID).getX() + Camera.getX());
		int dY = (int)(cursor.getY() - players.get(myID).getY() + Camera.getY());
		return Math.atan2(dY, dX);
	}

	public Point getPlayerVelocity(List<Character> pressedKeys){
		float totalSpeed = 2;
		float speedX = 0;
		float speedY = 0;
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
		double speed = Math.hypot(speedX, speedY);
		return new Point((int)(speedX/speed), (int)(speedY/speed));
				 */

		if (player == null) return;
		player.update(pressedKeys, cursor, isMousePressed);
		Camera.setX(player.getX());
		Camera.setY(player.getY());
		for(int i = 0; i < pressedKeys.size(); i++) {

			ServerCommunicator.movePlayer(pressedKeys.get(i), (int)cursor.getX(), (int)cursor.getY());
		}

	}
	
	/** SERVER COMMAND PARSING
	 * @param s - The message that the zombienado_v1.client received
	 */
	public synchronized void serverCommand(String s){

		String[] arg = s.split(";");		
		if (arg[0].equals("player")){
			if (arg[1].equals("id")){
				myID = Integer.parseInt(arg[2]);
			}
		} else if (arg[0].equals("players")){

			int id = Integer.parseInt(arg[1]);
<<<<<<< HEAD
			if (id != myID){
=======
			//if (id != player.getID()){
>>>>>>> modelRework
				if (arg[2].equals("pos")){
					System.out.println("PLAYERS: "+players.size());
					if (players.get(id) == null){
						players.set(id, new Player());
					}

					int x = Integer.parseInt(arg[3]);
					int y = Integer.parseInt(arg[4]);
					double rot = Double.parseDouble(arg[5]);
					players.get(id).setPosition(x,y);
					players.get(id).setRotation(rot);
				}
			//}

		} else if(arg[0].equals("zombies")){
			int id = Integer.parseInt(arg[1]);
			if (arg[2].equals("pos")){
				if (zombies.size() <= id){
					zombies.add(new Zombie());
					//zombies.get(id).setTexture(zombieSprite);
				}

				int x = Integer.parseInt(arg[3]);
				int y = Integer.parseInt(arg[4]);
				double rot = Double.parseDouble(arg[5]);
				zombies.get(id).setPosition(x,y);
				zombies.get(id).setRotation(rot);
			}
		}
	}
}
