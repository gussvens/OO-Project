package server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket socket = null;
	private static Server instance = null;
	private ArrayList<int[]> playerPositions;
	private int amountConnected = 0;

	private Server(){
		playerPositions = new ArrayList<int[]>();
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
		while(true){
			try {

				new ServerThread(socket.accept(), this.getInstance(), amountConnected).start();
				//new SendThread(socket.accept(),this.getInstance()).start();
				amountConnected = amountConnected +1;
				playerPositions.add(new int[]{0,0});
				System.out.println("Something connected");


			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}

	public synchronized ArrayList<int[]> getPlayerPositions(){
		return playerPositions;
	}

	public synchronized void updatePlayerPosition(int[]newPos, int id){
		playerPositions.add(id,newPos);
	}
}
