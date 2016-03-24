package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

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

			output.println("player;id;" + ID);

			while ((inputString = input.readLine()) != null) {
				//System.out.println(ID + " " + inputString);
				String[] splits = inputString.split(";");
				int[] temp = {0,0};
				temp[0] = Integer.parseInt(splits[0]);
				temp[1] = Integer.parseInt(splits[1]);

				mainServer.updatePlayerPosition(temp, ID);

				ArrayList<int[]> positions = mainServer.getPlayerPositions();

				for(int i = 0; i < positions.size(); i++){
					int[] q = positions.get(i);
					String s = "players;pos;" + i + ";" + q[0] + ";" + q[1];
					System.out.println("Sending Data!");
					output.println(s);
				}
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
