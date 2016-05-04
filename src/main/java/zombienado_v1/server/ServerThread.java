package zombienado_v1.server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private static int connectedUnits = 0;
	private PrintWriter output = null;
	private BufferedReader input;
	private Socket socket;
	private Server mainServer;
	private int ID;

	public ServerThread(Socket socket, Server server, int id) throws SocketException{
		super("ServerThread "+connectedUnits);
		connectedUnits ++;
		this.ID = id;
		this.mainServer = server;
		this.socket = socket;

		System.out.println("oldPlayer " + id + " connected!");

	}

	public void listen(){
		try {
			String inputString;
			while ((inputString = input.readLine()) != null) {
				//System.out.println(ID + " " + inputString);
				String[] splits = inputString.split(";");


				mainServer.updatePlayerPosition(Integer.parseInt(splits[0]),Integer.parseInt(splits[1]),Double.parseDouble(splits[2]), ID);

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

	public void send(){

	}

	public void send(String message){
		if (output != null)
			output.println(message);
	}

	public void run(){
		try {
			output = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			send("Connected to zombienado_v1.client");
			send("player;id;" + ID);
		} catch (IOException e) {
			e.printStackTrace();
		}

		listen();
	}


}
