package edu.chalmers.projecttemplate.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.chalmers.projecttemplate.controller.Client;
import edu.chalmers.projecttemplate.controller.Controller;
import edu.chalmers.projecttemplate.controller.OtherPlayer;
import edu.chalmers.projecttemplate.controller.Player;
import edu.chalmers.projecttemplate.controller.Unit;
import edu.chalmers.projecttemplate.controller.Zombie;
import edu.chalmers.projecttemplate.view.GameView;

public class Model {
	private int test = 0;
	private int myID;
	private Player player;
	private ArrayList<Unit> otherPlayers;
	private ArrayList<Unit> zombies;
	private Image playerSprite; //TEST
	private Image zombieSprite; //TEST

	/** INITIALIZATION/LOAD.
	 *	Executed before gameloop starts
	 */
	public void initialize(){
		// TESTING STUFF BELOW
		playerSprite = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
		playerSprite.getGraphics().fillRect(0, 0, 20, 20);
		zombieSprite = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
		zombieSprite.getGraphics().setColor(Color.RED);
		zombieSprite.getGraphics().fillRect(0, 0, 20, 20);
		// END OF TESTING STUFF
		player = new Player(30, 30, playerSprite);
		otherPlayers = new ArrayList<Unit>();
		zombies = new ArrayList<Unit>();
		for (int i = 0; i < 4; i++){ // Test. Creates 4 players in order to match ID to index.
			otherPlayers.add(null);
		}
	}
	
	/** MAIN UPDATE METHOD
	 * Used for all game logic and events
	 * @param pressedKeys
	 */
	public void tick(List<Character> pressedKeys){
		test++;
		player.update(pressedKeys);
		Client.sendToServer(player.getParsedServerString());
	}

	/** RENDER METHOD
	 *  All directly graphics related code goes here
	 * @param graphics
	 */
	public void draw(Graphics2D graphics){
		Color c = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
		graphics.setColor(c);
		graphics.fillRect(0, 0, GameView.getScreenWidth(), GameView.getScreenWidth());
		graphics.setColor(Color.white);
		player.draw(graphics);
		for (Unit op : otherPlayers) {
			if (op != null)
				op.draw(graphics);
		}
		graphics.setColor(Color.black);
		graphics.drawString("FPS: "+Controller.getFramesPerSecond(), 9, 19);
		graphics.setColor(Color.white);
		graphics.drawString("FPS: "+Controller.getFramesPerSecond(), 10, 20);
	}
	
	/** SERVER COMMAND PARSING
	 * @param s
	 */
	public void serverCommand(String s){

		String[] arg = s.split(";");		
		if (arg[0].equals("player")){
			if (arg[1].equals("id")){
				myID = Integer.parseInt(arg[2]);
			}
		} else if (arg[0].equals("players")){

			int id = Integer.parseInt(arg[1]);
			if (id != myID){
				if (arg[2].equals("pos")){
					if (otherPlayers.get(id) == null){
						otherPlayers.set(id, new OtherPlayer());
						otherPlayers.get(id).setTexture(playerSprite);
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
					zombies.get(id).setTexture(zombieSprite);
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
