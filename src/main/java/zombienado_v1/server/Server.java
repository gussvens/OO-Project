package zombienado_v1.server;

import zombienado_v1.server.serverUnits.Bullet;
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
	private ArrayList<Bullet> bullets;
	private Spawner spawner;
	private WorldHandler handler;

	private Server(){
		players = new ArrayList<ServerPlayer>();
		serverThreads = new ArrayList<ServerThread>();
		bullets = new ArrayList<Bullet>();
		spawner = Spawner.getInstance();
		handler = new WorldHandler();
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
				players.get(i).update(st.getDeltaX(), st.getDeltaY(), st.getDeltaRotation(),handler.getWallTiles());
				if(st.getIsShooting()){
					if(players.get(i).shoot() != null) {
						bullets.add(players.get(i).shoot());
					}
				}
			}

			//Update all bullets
			for(Bullet b : bullets) {
				b.update();
			}
			
			
			
			/**
			 * Send stuff to clients
			 */
			ArrayList<ServerPlayer> positions = getPlayerPositions(); //tidy this up
			spawner.update(players, handler.getSpawnTiles(),handler.getWallTiles());

			for (ServerThread serverThread : serverThreads){
				for(int i = 0; i < positions.size(); i++){
					ServerPlayer q = positions.get(i);
					serverThread.sendPlayerData(i, q.getX(), q.getY(), q.getRotation());
				}

				for(ServerZombie zombie : spawner.getZombies()){
					if(zombie != null) {
						serverThread.sendZombieData(zombie.getID(), zombie.getX(), zombie.getY(), zombie.getRotation());
					}
				}

				for(Bullet bullet : bullets) {
					serverThread.sendBulletData(bullet.getID(), bullet.getX(), bullet.getY(), 0.3);
				}
			}
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
