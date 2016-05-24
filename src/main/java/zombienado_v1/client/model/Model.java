package zombienado_v1.client.model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import zombienado_v1.client.controller.Client;
import zombienado_v1.client.model.weapon.*;
import zombienado_v1.client.view.GameView;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.PlayerInputHandler;

public class Model {
	//private oldPlayer player;
	private ServerCommunicator coms;
	private ArrayList<Player> players;
	private ArrayList<Unit> zombies;
	private ArrayList<Unit> bullets;
	private Store store;
	private Weapon[] weapons = new Weapon[100];
	private int myID = -1;
	private int wave = 1;
	private int timeUntilNextWave = -1;
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
	public Weapon[] getWeapons(){
		return weapons;
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
	public synchronized void tick(List<Character> pressedKeys, Point cursor, boolean isMousePressed) {
		this.myID = coms.getID();
		if (myID == -1) return;
		if(this.getPlayers() == null) return;

		this.players = coms.getPlayers();
		this.zombies = coms.getZombies();
		this.bullets = coms.getBullets();
		this.wave = coms.getWave();
		this.timeUntilNextWave = coms.getTimeUntilNextWave();
		Point velocityVector = PlayerInputHandler.getPlayerVelocity(pressedKeys);

		double oldRotation = getPlayer().getRotation();
		double newRotation = PlayerInputHandler.getPlayerRotation(getPlayer().getX(), getPlayer().getY(), cursor);
		boolean reload = PlayerInputHandler.getReload(pressedKeys);

		if (getPlayer().isDead()) {
			Camera.setX((int)(Camera.getX() + GameView.getScreenWidth()/2 + velocityVector.getX()));
			Camera.setY((int)(Camera.getY() + GameView.getScreenHeight()/2 + velocityVector.getY()));
			return;
		}

		getPlayer().setRotation(newRotation);
		Camera.setX(players.get(myID).getX());
		Camera.setY(players.get(myID).getY());
		if (timeUntilNextWave != -1) {
			store.buyWeapon(cursor, isMousePressed);
		}

		try {
			coms.movePlayer((int) velocityVector.getX(), (int) velocityVector.getY(), newRotation, oldRotation);
			if(store.hasBoughtNewWeapon()) {
				coms.buyWeapon(store.getBoughtWeapon());
			}
			if (getTimeUntilNextWave() == -1){
				coms.shoot(isMousePressed);
			} else {
				coms.shoot(false);
			}

		//	coms.requestReload(reload);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

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
}