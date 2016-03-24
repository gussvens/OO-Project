package server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket socket = null;
	private static Server instance = null;
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
				new ServerThread(socket.accept(), this.getInstance()).start();
				System.out.println("Something connected");
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}
