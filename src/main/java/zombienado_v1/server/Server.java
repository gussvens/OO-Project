package zombienado_v1.server;

import zombienado_v1.server.model.serverUnits.ServerPlayer;
import zombienado_v1.server.model.serverUnits.ServerZombie;
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
	private Spawner spawner;
	private WorldHandler handler;

	private Server(){
		players = new ArrayList<ServerPlayer>();
		serverThreads = new ArrayList<ServerThread>();
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
				//new SendThread(socket.accept(),this.getInstance()).start();
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
			
			
			
			
			/**
			 * Send stuff to clients
			 */
			ArrayList<ServerPlayer> positions = getPlayerPositions(); //tidy this up
			spawner.update(players, handler.getSpawnTiles(), handler.getSolidMap());

			for (ServerThread serverThread : serverThreads){
				for(int i = 0; i < positions.size(); i++){
					ServerPlayer q = positions.get(i);
					String s = "players;"+ i + ";pos"  + ";" + q.getX() + ";" + q.getY() + ";" + q.getRotation();
					System.out.println("Sending Player Position!");
					serverThread.send(s);
				}

				for(ServerZombie zombie : spawner.getZombies()){

					if(zombie != null) {
						String s = "zombies;" + zombie.getId() + ";pos;" + zombie.getX() + ";" + zombie.getY() + ";" + zombie.getRotation();
						System.out.println("Sending Zombie Position!");
						serverThread.send(s);
					}

				}
			}
			try {
				Thread.sleep((long) 33); //Gotta fix this
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public synchronized ArrayList<ServerPlayer> getPlayerPositions(){
		return players;
	}

	public synchronized void updatePlayerPosition(int x, int y, double r, int id){
		players.get(id).update(x, y, r,handler.getSolidMap());
	}
}
