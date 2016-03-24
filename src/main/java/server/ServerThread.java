package server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private static int connectedUnits = 0;
	private Socket socket;

	public ServerThread(Socket socket) throws SocketException{
		super("ServerThread "+connectedUnits);
		connectedUnits ++;
		this.socket = socket;
	}

	public void run(){
		try {
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output.println("Connected to client");
			String inputString;
			while ((inputString = input.readLine()) != null) {

				output.println(inputString);
				System.out.println(socket.getInetAddress().toString() + inputString);

			}

			System.out.print("Shutting down");
			
			output.close();
			input.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
