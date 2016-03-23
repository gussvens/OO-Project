package server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket socket = null;
	public Server(int Port){
		try {
		socket = new ServerSocket(Port); 
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try {
				new ServerThread(socket.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}
