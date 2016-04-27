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
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;
import zombienado_v1.utilities.MapLoader;

public class Model {
	private int myID = -1;
	private boolean idIsSet = false;
	private Player player;
	private ArrayList<Unit> otherPlayers;
	private ArrayList<Unit> zombies;
	private Image[] playerSprite = new Image[4]; 
	private Image playerFeetSheet; //TEST
 	private Image zombieSprite;
	private Map map;
	

	/** INITIALIZATION/LOAD.
	 *	Executed before gameloop starts
	 */
	public synchronized void initialize(){
		try { //LOAD
			playerSprite[0] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerRocker.png")));
			playerSprite[1] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerPunk.png")));
			playerSprite[2] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerGirl.png")));
			playerSprite[3] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerDark.png")));
			playerFeetSheet = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/testFeet.png")));
			zombieSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/zombie.png")));
			map = new Map(ImageIO.read(new File("src/main/resources/sprites/tiles/tileGrid.png")));
			MapLoader.Load(map, new File("src/main/resources/maps/mapTest.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		player = new Player(320, 320, playerSprite[0], playerFeetSheet);
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
	public synchronized void tick(List<Character> pressedKeys, Point cursor, boolean isMousePressed){
		if (myID != -1 && !idIsSet){ //to set correct texture, must set this here due to thread stuff
			player.setTexture(playerSprite[myID]);
			idIsSet = true;
		}
		player.update(pressedKeys, cursor, isMousePressed, map.getSolids());
		Camera.setX(player.getX());
		Camera.setY(player.getY());
		Client.sendToServer(player.getParsedServerString());
	}

	/** RENDER METHOD
	 *  All directly graphics related code goes here
	 * @param graphics
	 */
	public synchronized void draw(Graphics2D graphics){
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameView.getScreenWidth(), GameView.getScreenWidth());
		map.draw(graphics);
		player.draw(graphics);
		for (Unit op : otherPlayers) {
			if (op != null)
				op.draw(graphics);
		}
		
		for (Unit zombie : zombies) {
			if (zombie != null)
				zombie.draw(graphics);
		}
		graphics.drawString("FPS: "+Controller.getFramesPerSecond(), 9, 19);
		graphics.drawString("Zombinado Beta", GameView.getScreenWidth() - 101, GameView.getScreenHeight() - 11);
		graphics.setColor(Color.white);
		graphics.drawString("FPS: "+Controller.getFramesPerSecond(), 10, 20);
		graphics.drawString("Zombinado Beta", GameView.getScreenWidth() - 100, GameView.getScreenHeight() - 10);
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
			if (id != myID){
				if (arg[2].equals("pos")){
					System.out.println("PLAYERS: "+otherPlayers.size());
					if (otherPlayers.get(id) == null){
						otherPlayers.set(id, new OtherPlayer());
						otherPlayers.get(id).setTexture(playerSprite[id]);
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
