package server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket socket = null;
	private static Server instance = null;
	private int[][] playerPositions;
	private int amountConnected = 1;

	public Server(){

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
				amountConnected = amountConnected +1;
				System.out.println("Something connected");


			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}

	public synchronized int[][] getPlayerPositions(){
		return playerPositions;
	}

	public synchronized void updatePlayerPosition(int[][]newPos, int id){
		playerPositions[id][0] = newPos[0][0];
		playerPositions[id][1] = newPos[0][1];
	}
}
