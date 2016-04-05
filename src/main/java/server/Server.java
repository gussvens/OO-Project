package server;

import server.serverUnits.ServerPlayer;
import server.serverUnits.ServerZombie;
import server.threads.SpawnerThread;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket socket = null;
	private static Server instance = null;
	private ArrayList<ServerPlayer> players;
	private int amountConnected = 0;
	private ArrayList<ServerThread> serverThreads;
	private SpawnerThread spawner;

	private Server(){
		players = new ArrayList<ServerPlayer>();
		serverThreads = new ArrayList<ServerThread>();
		spawner = SpawnerThread.getInstance();
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
				players.add(new ServerPlayer(0,0,0,amountConnected));
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
			spawner.update();

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
		players.get(id).update(x,y,r);
	}
}
