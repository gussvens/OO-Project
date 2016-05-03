package zombienado_v1.client.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import zombienado_v1.client.controller.Client;
import zombienado_v1.client.controller.Controller;
import zombienado_v1.client.view.GameView;
import zombienado_v1.client.view.MapView;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;
import zombienado_v1.utilities.MapLoader;

public class Model {
	private Player player;
	private ArrayList<Unit> otherPlayers;
	private ArrayList<Unit> zombies;

	/**
	 * Getters
	 */
	public synchronized List<Unit> getOtherPlayers(){
		return otherPlayers;
	}

	public synchronized List<Unit> getZombies(){
		return zombies;
	}

	public synchronized Player getPlayer(){
		return player;
	}

	/** INITIALIZATION/LOAD.
	 *	Executed before gameloop starts
	 */
	public synchronized void initialize(){
		otherPlayers = new ArrayList<Unit>();
		zombies = new ArrayList<Unit>();
		for (int i = 0; i < 4; i++){ // Test. Creates 4 players in order to match ID to index.
			otherPlayers.add(null);
		}
	}
	
	/** MAIN UPDATE METHOD
	 * Used for all game logic and events
	 * @param pressedKeys - Currently pressed keys
	 * @param cursor - Cursors position
	 * @param isMousePressed
	 */
	public synchronized void tick(List<Character> pressedKeys, Point cursor, boolean isMousePressed) {
		if (player == null) return;
		player.update(pressedKeys, cursor, isMousePressed);
		Camera.setX(player.getX());
		Camera.setY(player.getY());
		Client.sendToServer(player.getParsedServerString());
	}
	
	/** SERVER COMMAND PARSING
	 * @param s - The message that the zombienado_v1.client received
	 */
	public synchronized void serverCommand(String s){

		String[] arg = s.split(";");		
		if (arg[0].equals("player")){
			if (arg[1].equals("id")){
				int id = Integer.parseInt(arg[2]);
				player = new Player(id, 320, 320);
			}
		} else if (arg[0].equals("players")){

			int id = Integer.parseInt(arg[1]);
			if (id != player.getID()){
				if (arg[2].equals("pos")){
					System.out.println("PLAYERS: "+otherPlayers.size());
					if (otherPlayers.get(id) == null){
						otherPlayers.set(id, new OtherPlayer());
						//otherPlayers.get(id).setTexture(playerSprite[id]);
					}

					int x = Integer.parseInt(arg[3]);
					int y = Integer.parseInt(arg[4]);
					double rot = Double.parseDouble(arg[5]);
					otherPlayers.get(id).setPosition(x,y);
					otherPlayers.get(id).setRotation(rot);
				}
			}

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
