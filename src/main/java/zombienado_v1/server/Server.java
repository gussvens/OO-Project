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

	private Server(){
		players = new ArrayList<ServerPlayer>();
		serverThreads = new ArrayList<ServerThread>();
		bullets = new ArrayList<ServerBullet>();
		spawner = Spawner.getInstance();
		handler = new WorldHandler();
		bulletCounter = 0;
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
		handler.createMap("src/main/resources/maps/mapTest.txt");
		mainUpdate.start();
		listenForConnections();

	}
	public void listenForConnections(){
		while(true){
			try {
				ServerThread st = new  ServerThread(socket.accept(), this.getInstance(), amountConnected);
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
							for (int j = 0; j < players.get(i).shoot(bulletCounter).size(); j++) {
								bullets.add(temp.get(j));
								bulletCounter++;
							}
						}
					}
				}
			}

			//Update all bullets
			for(ServerBullet b : bullets) {
				b.update(spawner.getZombies(),handler.getWallTiles());
			}


			/**
			 * Send stuff to clients
			 */
			ArrayList<ServerPlayer> positions = getPlayerPositions(); //tidy this up
			spawner.update(players, handler.getSpawnTiles(),handler.getWallTiles());

			for (ServerThread serverThread : serverThreads){
				for(int i = 0; i < positions.size(); i++){
					ServerPlayer q = positions.get(i);
					serverThread.sendPlayerData(i, q.getX(), q.getY(), q.getRotation(), q.hasShot(), q.getHealth(), q.getAmmo(), q.getBalance());
				}

				for(ServerZombie zombie : spawner.getZombies()){
					if(zombie != null) {
						serverThread.sendZombieData(zombie.getID(), zombie.getX(), zombie.getY(), zombie.getRotation());
					}
				}

				for(ServerBullet bullet : bullets) {
					if (bullet.getSpeed() == 0){
						serverThread.sendRemoveBullet(bullets.indexOf(bullet));
					} else {
						serverThread.sendBulletData(bullets.indexOf(bullet), bullet.getX(), bullet.getY(), bullet.getRotation());
					}
				}
			}

			//remove old bullets
			for (int i = bullets.size() - 1; i >= 0; i--){
				if (bullets.get(i).getSpeed() == 0){
					bullets.remove(i);
				}
			}
			//remove dead zombies
			for (int i = spawner.getZombies().size() - 1; i >= 0; i--){
				if (spawner.getZombies().get(i).getHealth() <= 0){
					spawner.getZombies().remove(i);
				}
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
