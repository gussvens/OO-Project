package zombienado_beta.server;

import zombienado_beta.server.serverModel.ServerPlayer;
import zombienado_beta.server.serverModel.ServerZombie;
import zombienado_beta.server.serverModel.ServerBullet;
import zombienado_beta.server.serverWorld.WorldHandler;
import zombienado_beta.server.unitHandler.Spawner;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket socket = null;
	private static Server instance = null;
	private ArrayList<ServerPlayer> players;
	private int amountConnected = 0;
	private ArrayList<ServerThread> serverThreads;
	private ArrayList<ServerBullet> bullets;
	private Spawner spawner;
	private WorldHandler handler;
	private int currentWave;
	private int timeUntilNextWave;
	private boolean gameOver = false;
	private String map;

	/**
	 * private Server constructor
	 */
	private Server(int port, String map){
		players = new ArrayList<ServerPlayer>();
		serverThreads = new ArrayList<ServerThread>();
		bullets = new ArrayList<ServerBullet>();
		spawner = Spawner.getInstance();
		handler = new WorldHandler();
		currentWave = spawner.getWave();
		timeUntilNextWave = spawner.getTimeUntilNextWave();
		this.map = map;
		try {
			socket = new ServerSocket(port);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * A method that returns a Server instance. If no ServerInstance exists it creates one
	 * @return - A Server instance
     */
	public static Server getInstance(int port, String map){
		if(instance == null){
			instance = new Server(port, map);
		}

		return instance;
	}

	/**
	 * A run method that calls for methods to create a map, start update and start listening for connections
	 */
	public void run(){
		Thread mainUpdate = new Thread(() -> update());
		handler.createMap("src/main/resources/maps/" + map + ".txt");
		mainUpdate.start();
		listenForConnections();
	}

	/**
	 * A method that is constantly listening for new connections and creates a new ServerThread for each new unit connected
	 */
	public void listenForConnections(){
		while(true){
			try {
				ServerThread st = new  ServerThread(socket.accept(), amountConnected, map);
				serverThreads.add(st);
				st.start();
				amountConnected = amountConnected +1;
				players.add(new ServerPlayer(64,64,0,amountConnected));
				System.out.println("Something connected");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * An update method that is constantly looping to update everything in the game and then tells the ServerThread to send the information
	 */
	public void update(){
		while (true){

			if(!gameOver) {

				shoot();
				updateBullets();
				updateZombies();
				checkChangedWave();
				sendWaveTime();

				for (ServerThread serverThread : serverThreads) {
					checkWeaponSwitches(serverThread);
					sendPlayers(serverThread);
					sendZombies(serverThread);
					sendBullets(serverThread);
				}

				checkGameOver();

			} else {

				sendGameOver();

			}

			try {
				Thread.sleep((long) 16);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * A method that checks each ServerThread if it has received a "shoot" call and then tries to shoot if so is the case
	 */
	private void shoot(){
		for (int i = 0; i < players.size(); i++) {
			final ServerThread st = serverThreads.get(i);
			players.get(i).update(st.getDeltaX(), st.getDeltaY(), st.getRotation(), spawner.getZombies(), handler.getWallTiles());
			if (st.getIsShooting()) {
				if (players.get(i).canShoot()) {
					ArrayList<ServerBullet> temp = players.get(i).shoot();
					if (!temp.isEmpty()) {
						for (int j = 0; j < temp.size(); j++) {
							bullets.add(temp.get(j));
						}
					}
				}
			}
		}
	}

	/**
	 * A method call the update method in all Bullets
	 */
	private void updateBullets(){
		for (int i = bullets.size() - 1; i >= 0; i--) {
			bullets.get(i).update(spawner.getZombies(), handler.getWallTiles());
			if (bullets.get(i).getSpeed() == 0) {
				bullets.remove(i);
			}
		}
	}

	/**
	 * A method that updates all zombies
	 */
	private void updateZombies(){
		spawner.update(players, handler.getSpawnTiles(), handler.getWallTiles());
	}

	/**
	 * A method that checks if the wave has changed and tells the ServerThreads to send a new wave number if so is the case
	 */
	private void checkChangedWave(){
		if (spawner.getWave() != currentWave) {
			currentWave = spawner.getWave();
			for (ServerPlayer player : players) {
				if (player.getIsDead()) {
					player.addHealth(50);
				} else{
					player.addHealth(20);
				}
			}
			for (ServerThread serverThread : serverThreads) {
				serverThread.sendWaveData(currentWave);
			}
		}
	}

	/**
	 * A method that tells the ServerThreads to send the time until next wave
	 */
	private void sendWaveTime(){
		if (spawner.getTimeUntilNextWave() != timeUntilNextWave) {
			timeUntilNextWave = spawner.getTimeUntilNextWave();

			for (ServerThread serverThread : serverThreads) {
				serverThread.sendTimeUntilNextWaveData(timeUntilNextWave);
			}
		}
	}

	/**
	 * A method that checks if a ServerThread has received a "change weapon" call
	 * @param serverThread - The ServerThread to check
     */
	private void checkWeaponSwitches(ServerThread serverThread) {
		if (serverThread.getWeaponHasChanged()) {
			this.players.get(serverThread.getID()).switchWeapon(serverThread.getWeaponID());
		}
	}

	/**
	 * A method that tells the ServerThread to send data about all ServerPlayers
	 * @param serverThread - The ServerThread that will send the data
     */
	private void sendPlayers(ServerThread serverThread) {
		for (int i = 0; i < players.size(); i++) {
			ServerPlayer player = players.get(i);
			if (player.getIsDead()) {
				serverThread.sendDeadPlayerData(i);
			}
			serverThread.sendPlayerData(i, player.getX(), player.getY(), player.getRotation(), player.hasShot(), player.getHealth(), player.getAmmo(), player.getBalance(), player.getWeaponID());
		}
	}

	/**
	 * A method that tells the ServerThread to send data about all ServerZombies
	 * @param serverThread - The ServerThread that will send the data
     */
	private void sendZombies(ServerThread serverThread){
		for (ServerZombie zombie : spawner.getZombies()) {
			serverThread.sendZombieData(spawner.getZombies().indexOf(zombie), zombie.getX(), zombie.getY(), zombie.getRotation());
		}
	}

	/**
	 * A method that tells the ServerThread to send data about all ServerBullets
	 * @param serverThread - The ServerThread that will send the data
     */
	private void sendBullets(ServerThread serverThread){
		for (ServerBullet bullet : bullets) {
			serverThread.sendBulletData(bullets.indexOf(bullet), bullet.getX(), bullet.getY(), bullet.getRotation());
		}
	}

	/**
	 * A method that checks if all players are dead. If all are, then gameOver is set to true
	 */
	private void checkGameOver(){
		int deadPlayers = 0;

		for (int i = 0; i < players.size(); i++) {
			ServerPlayer q = players.get(i);
			q.resetShot();
			if (q.getIsDead()) {
				deadPlayers++;
			}
		}

		if(deadPlayers == players.size() && !players.isEmpty()) {
			gameOver = true;
		}
	}

	/**
	 * A method that tells all ServerThreads to send game over
	 */
	private void sendGameOver(){
		for(ServerThread thread : serverThreads) {
			thread.sendGameOver(players.get(0).getScore());
		}
	}
}
