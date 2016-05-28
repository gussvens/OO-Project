package zombienado_beta.client.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import zombienado_beta.client.model.weapon.*;
import zombienado_beta.utilities.Camera;
import zombienado_beta.utilities.PlayerInputHandler;
import zombienado_beta.utilities.Vector;

/**
 * A class to act as model on the client side of the application
 */
public class Model {
	public int screenWidth;
	public int screenHeight;
	private ServerCommunicator coms;
	private ArrayList<Player> players;
	private ArrayList<Unit> zombies;
	private ArrayList<Unit> bullets;
	private Store store;
	private Weapon[] weapons = new Weapon[100];
	private int myID = -1;
	private int wave = 1;
	private int timeUntilNextWave = -1;
	private boolean isGameOver;
	private int finalScore;
	private String mapName;
	private Point aim;

	/**
	 * Getters for view
	 */
	public synchronized List<Player> getPlayers(){
		return players;
	}
	public synchronized List<Unit> getZombies(){
		return zombies;
	}
	public synchronized List<Unit> getBullets(){
		return bullets;
	}
	public synchronized int getWave(){
		return wave;
	}
	public synchronized int getTimeUntilNextWave(){
		return timeUntilNextWave;
	}
	public synchronized boolean isGameOver(){
		return this.isGameOver;
	}
	public synchronized String getMapName(){
		return this.mapName;
	}
	public synchronized int getFinalScore(){
		return this.finalScore;
	}
	public Point getAim(){ return aim; }

	/**
	 * Returns the client's Player instance
	 * @return the client's Player instance
	 */
	public Player getPlayer(){
		if(myID<0){
			return new Player();
		} else{
			return players.get(myID);
		}
	}
	public Weapon[] getWeapons(){
		return weapons;
	}

	/** INITIALIZATION
	 * Executed before gameloop starts
	 */
	public synchronized void initialize(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		try {
			coms = ServerCommunicator.getInstance();
		} catch (Exception e){
			e.printStackTrace();
		}
		players = new ArrayList<Player>();
		zombies = new ArrayList<Unit>();
		bullets = new ArrayList<Unit>();
		for (int i = 0; i < 4; i++){ // Test. Creates 4 players in order to match ID to index.
			players.add(null);
		}

		weapons[20] = new Ak47();
		weapons[21] = new TommyGun();
		weapons[22] = new M4();
		weapons[30] = new Shotgun();
		weapons[31] = new Blunderbuss();
		weapons[32] = new AutoShotgun();
		weapons[40] = new Uzi();
		weapons[41] = new DoubleUzi();
		weapons[42] = new TripleUzi();

		store = new Store();
	}

	/** MAIN UPDATE METHOD
	 * Used for all client sided game logic and events
	 * @param pressedKeys - Currently pressed keys
	 * @param cursor - Cursors position
	 * @param isMousePressed
	 */
	public synchronized void tick(float deltaTime, List<Character> pressedKeys, Point cursor, boolean isMousePressed) {
		//Set the aim
		this.aim = cursor;
		//Stop updating if game is over
		this.isGameOver = coms.getGameOver();
		this.finalScore = coms.getFinalScore();
		if(isGameOver()) return;

		this.myID = coms.getID();
		this.mapName = coms.getMapName();
		//If myID is not set, or if no players has joined, dont update
		if (myID == -1) return;
		if(this.getPlayers() == null) return;

		//Fetch last received data
		this.players = coms.getPlayers();
		this.zombies = coms.getZombies();
		this.bullets = coms.getBullets();
		this.wave = coms.getWave();
		this.timeUntilNextWave = coms.getTimeUntilNextWave();
		float oldRotation = getPlayer().getRotation();
		//Handle input
		float newRotation = PlayerInputHandler.getPlayerRotation(getPlayer().getX(), getPlayer().getY(), cursor);
		Vector playerVelocity = PlayerInputHandler.getPlayerVelocity(pressedKeys);
		playerVelocity.setX(playerVelocity.getX() * deltaTime);
		playerVelocity.setY(playerVelocity.getY() * deltaTime);

		//Lets the new player data be presented to the view one iteration earlier (doesn't really make a difference)
		// Problem with setting position is that the player seems to be able to enter walls a tiny tiny bit
		getPlayer().setRotation(newRotation);

		//If player is not alive, freely move camera and exit out of update
		if (getPlayer().isDead()) {
			Camera.setX((int)(Camera.getX() + screenWidth/2 + playerVelocity.getX()), screenWidth);
			Camera.setY((int)(Camera.getY() + screenHeight/2 + playerVelocity.getY()), screenHeight);
			return;
		}

		//Sets camera to players position
		Camera.setX(getPlayer().getX(), screenWidth);
		Camera.setY(getPlayer().getY(), screenHeight);

		//If between two waves, check if the player buys a weapon
		if (timeUntilNextWave != -1) {
			store.buyWeapon(cursor, isMousePressed);
		}

		//Send data to server
		try {
			coms.movePlayer(playerVelocity.getX(), playerVelocity.getY(), newRotation, oldRotation);
			if(store.hasBoughtNewWeapon()) {
				coms.buyWeapon(store.getBoughtWeapon());
			}
			if (getTimeUntilNextWave() == -1){
				coms.shoot(isMousePressed);
			} else {
				coms.shoot(false);
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}
}