package server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket socket = null;
	private static Server instance = null;
	private ArrayList<int[]> playerPositions;
	private int amountConnected = 0;
	private ArrayList<ServerThread> serverThreads;

	private Server(){
		playerPositions = new ArrayList<int[]>();
		serverThreads = new ArrayList<ServerThread>();
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
				playerPositions.add(new int[]{0,0});
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
			ArrayList<int[]> positions = getPlayerPositions(); //tidy this up

			for (ServerThread serverThread : serverThreads){
				for(int i = 0; i < positions.size(); i++){
					int[] q = positions.get(i);
					String s = "players;pos;" + i + ";" + q[0] + ";" + q[1];
					System.out.println("Sending Data!");
					serverThread.send(s);
				}
			}
			try {
				Thread.sleep((long) 33); //Gotta fix this
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public synchronized ArrayList<int[]> getPlayerPositions(){
		return playerPositions;
	}

	public synchronized void updatePlayerPosition(int[]newPos, int id){
		playerPositions.set(id,newPos);
	}
}
