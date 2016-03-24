package server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private static int connectedUnits = 0;
	private Socket socket;
	private Server mainServer;
	private int ID;

	public ServerThread(Socket socket, Server server, int id) throws SocketException{
		super("ServerThread "+connectedUnits);
		connectedUnits ++;
		this.ID = id;
		this.mainServer = server;
		this.socket = socket;
		System.out.println("Player " + id + " connected!");
	}

	public void run(){
		try {
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output.println("Connected to client");
			String inputString;
			while ((inputString = input.readLine()) != null) {

				String[] splits = inputString.split(";");
				int[][] temp = {{0}, {0}};
				temp[0][0] = Integer.parseInt(splits[0]);
				temp[0][1] = Integer.parseInt(splits[1]);

				mainServer.updatePlayerPosition(temp, ID);

				//output.println(inputString);
				//System.out.println(socket.getInetAddress().toString() + inputString);

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
