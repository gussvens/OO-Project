package zombienado_v1.server;

import zombienado_v1.server.serverUnits.ServerBullet;
import zombienado_v1.server.serverUnits.ServerPlayer;
import zombienado_v1.server.serverUnits.ServerZombie;
import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.server.unitHandler.Spawner;

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
	private int bulletCounter;
	private int currentWave;
	private int timeUntilNextWave;

	private Server(){
		players = new ArrayList<ServerPlayer>();
		serverThreads = new ArrayList<ServerThread>();
		bullets = new ArrayList<ServerBullet>();
		spawner = Spawner.getInstance();
		handler = new WorldHandler();
		bulletCounter = 0;
		currentWave = spawner.getWave();
		timeUntilNextWave = spawner.getTimeUntilNextWave();
	}

	public static Server getInstance(){
		if(instance == null){
			instance = new Server();
		}

		return instance;
	}

	public void setPort(int port){
		try {
			socket = new ServerSocket(port);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void run(){
		Thread mainUpdate = new Thread(() -> update());
		handler.createMap("src/main/resources/maps/mapTestSmall.txt");
		mainUpdate.start();
		listenForConnections();

	}
	public void listenForConnections(){
		while(true){
			try {
				ServerThread st = new  ServerThread(socket.accept(), amountConnected);
				serverThreads.add(st);
				st.start();
				amountConnected = amountConnected +1;
				players.add(new ServerPlayer(100,100,0,amountConnected));
				System.out.println("Something connected");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(){
		while (true){

			/**
			 * Logic
			 */
			for (int i = 0; i < players.size(); i++) {
				final ServerThread st = serverThreads.get(i);
				players.get(i).update(st.getDeltaX(), st.getDeltaY(), st.getDeltaRotation(),spawner.getZombies(),handler.getWallTiles());
				if(st.getIsShooting()){
					if(players.get(i).canShoot()) {
						ArrayList<ServerBullet> temp = players.get(i).shoot(bulletCounter);
						if(!temp.isEmpty()) {
							for (int j = 0; j < temp.size(); j++) {
								bullets.add(temp.get(j));
								bulletCounter++;
							}
						}
					}
				}
			}

			for (int i = bullets.size() - 1; i >= 0; i--){
				bullets.get(i).update(spawner.getZombies(), handler.getWallTiles());
				if (bullets.get(i).getSpeed() == 0){
					bullets.remove(i);
				}
			}

			/**
			 * Send stuff to clients
			 */
			ArrayList<ServerPlayer> players = getPlayerPositions(); //tidy this up
			spawner.update(this.players, handler.getSpawnTiles(),handler.getWallTiles());

			if(spawner.getWave() != currentWave) {
				currentWave = spawner.getWave();
				for(ServerPlayer player : players){
					if(player.getIsDead()){
						player.setHealth(100);
						player.setDead(false);
					}
				}
				for(ServerThread serverThread : serverThreads) {
					serverThread.sendWaveData(currentWave);
				}
			}

			if(spawner.getTimeUntilNextWave() != timeUntilNextWave) {
				timeUntilNextWave = spawner.getTimeUntilNextWave();

				for (ServerThread serverThread : serverThreads) {
					serverThread.sendTimeUntilNextWaveData(timeUntilNextWave);
				}
			}

			for (ServerThread serverThread : serverThreads){
				if(serverThread.getWeaponHasChanged()) {
					this.players.get(serverThread.getID()).switchWeapon(serverThread.getWeaponID());
				}

				for(int i = 0; i < players.size(); i++){
					ServerPlayer player = players.get(i);
					if(player.getIsDead()){
						serverThread.sendDeadPlayerData(player.getID());
					}
					serverThread.sendPlayerData(i, player.getX(), player.getY(), player.getRotation(), player.hasShot(), player.getHealth(), player.getAmmo(), player.getBalance(), player.getWeaponID());
				}

				for(ServerZombie zombie : spawner.getZombies()){
					serverThread.sendZombieData(spawner.getZombies().indexOf(zombie), zombie.getX(), zombie.getY(), zombie.getRotation());
				}

				for(ServerBullet bullet : bullets) {
					serverThread.sendBulletData(bullets.indexOf(bullet), bullet.getX(), bullet.getY(), bullet.getRotation());

				}


			}

			for(int i = 0; i < players.size(); i++){
				ServerPlayer q = players.get(i);
				q.resetShot();
			}

			int deadPlayers = 0;
			for(int i = 0; i < players.size(); i++){
				if(players.get(i).getIsDead()) {
					deadPlayers++;
				}
			}
			if(deadPlayers == players.size()){
				for(ServerThread thread : serverThreads) {
					thread.sendGameOver();
				}
				break;
			}

			System.out.println("SERVER: bullets: " + bullets.size());
			try {
				Thread.sleep((long) 16); //Gotta fix this
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public synchronized ArrayList<ServerPlayer> getPlayerPositions(){
		return players;
	}
}
