package zombienado_v1.server;

import java.awt.*;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private static int connectedUnits = 0;
	private PrintWriter output = null;
	private BufferedReader input;
	private Socket socket;
	private Server mainServer;
	private int ID;

	private int deltaX = 0;
	private int deltaY = 0;
	private double deltaRotation = 0;
	private boolean isShooting = false;

	public ServerThread(Socket socket, Server server, int id) throws SocketException{
		super("ServerThread "+connectedUnits);
		connectedUnits ++;
		this.ID = id;
		this.mainServer = server;
		this.socket = socket;

		System.out.println("oldPlayer " + id + " connected!");

	}

	/**	Getters/Resetters of delta values (Sync stuff)
	 *
	 */
	 public int getDeltaX(){
		int temp = deltaX;
		deltaX = 0;
		return temp;
	}

	public int getDeltaY(){
		int temp = deltaY;
		deltaY = 0;
		return temp;
	}

	public double getDeltaRotation(){
		double temp = deltaRotation;
		deltaRotation = 0;
		return temp;
	}

	public boolean getIsShooting(){
		return isShooting;
	}

	/** Setters of delta values
	 *
	 */
	public void pushDeltaX(int dX){
		deltaX += dX;
	}

	public void pushDeltaY(int dY){
		deltaY += dY;
	}

	public void pushDeltaRotation(double dR){
		deltaRotation += dR;
	}

	public void listen(){
		try {
			String inputString;
			while ((inputString = input.readLine()) != null) {
				String[] splits = inputString.split(";");

				if(splits[0].equals("move")) {
					pushDeltaX(Integer.parseInt(splits[1]));
					pushDeltaY(Integer.parseInt(splits[2]));
					pushDeltaRotation(Double.parseDouble(splits[3]));
				}
			}

			System.out.print("Shutting down");

			output.close();
			input.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void send(String message){
		if (output != null)
			output.println(message);
	}

	public void sendPlayerData(int id, int x, int y, double rotation){
		String s = "players;"+ id + ";pos"  + ";" + x + ";" + y + ";" + rotation;
		send(s);
	}

	public void sendZombieData(int id, int x, int y, double rotation){
		String s = "zombies;" + id + ";pos;" + x + ";" + y + ";" + rotation;
		send(s);
	}

	public void sendBulletData(int id, int x, int y, double direction){
		String s = "bullet;" + id + ";" + x + ";" + y + ";" + ";" + direction;
		send(s);
	}

	public void run(){
		try {
			output = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			send("Connected to client");
			send("player;id;" + ID);
		} catch (IOException e) {
			e.printStackTrace();
		}

		listen();
	}


}
