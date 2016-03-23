package server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) throws SocketException{
		super("ServerThread");
		this.socket = socket;

		System.out.println("new ServerThread");
	}

	public void run(){
		try {
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output.println("Connected to client");
			String inputString;
			//while ((inputString = input.readLine()) != null) {
			while (true) {
				inputString = input.readLine();
				System.out.println("Server: "+inputString);
				
				if (!inputString.equals("KILL")) {
					break;
				}
			}
			
			output.close();
			input.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
