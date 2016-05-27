package zombienado_v1.server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private static int connectedUnits = 0;
	private PrintWriter output = null;
	private BufferedReader input;
	private Socket socket;
	private int ID;
	private int weaponID;
	private boolean weaponHasChanged = false;

	private float deltaX = 0;
	private float deltaY = 0;
	private float rotation = 0; //absolute
	private boolean isShooting = false;

	/**
	 * Constructor for a ServerThread
	 * @param socket - The socket it will listen to
	 * @param id - The ID of this ServerThread
	 * @throws SocketException
     */
	public ServerThread(Socket socket, int id) throws SocketException{
		super("ServerThread "+connectedUnits);
		connectedUnits ++;
		this.ID = id;
		this.socket = socket;
	}

	/**	Getters/Resetters of delta values (Sync stuff)
	 *
	 */
	 public synchronized  int getDeltaX(){
		int temp = (int)deltaX;
		deltaX = 0;
		return temp;
	}

	public  synchronized int getDeltaY(){
		int temp = (int)deltaY;
		deltaY = 0;
		return temp;
	}

	/**
	 * @return weaponID the ID of the players weapon
     */
	public synchronized  int getWeaponID(){
		return weaponID;
	}

	/**
	 * @return the ID of this ServerThread
     */
	public synchronized  int getID(){
		return ID;
	}

	/**
	 * @return if the weapon has changed or not
     */
	public synchronized boolean getWeaponHasChanged(){
		if(weaponHasChanged) {
			weaponHasChanged = false;
			return true;
		} else {
			return false;
		}
	}

	public synchronized float getRotation(){
		return rotation;
	}

	public synchronized boolean getIsShooting(){
		return isShooting;
	}

	/** Setters of delta values
	 *
	 */
	public synchronized void pushDeltaX(float dX){
		deltaX += dX;
	}

	public synchronized  void pushDeltaY(float dY){
		deltaY += dY;
	}

	public synchronized  void setRotation(float dR){
		rotation = dR;
	}

	/**
	 * Method that constantly is listening for input from the client
	 */
	public void listen(){
		try {
			String inputString;
			while ((inputString = input.readLine()) != null) {
				String[] splits = inputString.split(";");

				if(splits[0].equals("move")) {
					pushDeltaX(Float.parseFloat(splits[1]));
					pushDeltaY(Float.parseFloat(splits[2]));
					setRotation(Float.parseFloat(splits[3]));
				} else if(splits[0].equals("shoot")) {
					isShooting = Boolean.parseBoolean(splits[1]);
				} else if(splits[0].equals("weapon")) {
					weaponID = Integer.parseInt(splits[1]);
					weaponHasChanged = true;
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

	/**
	 * Method that sends messages to the client
	 * @param message - The message to be sent to the client
     */
	private synchronized void send(String message){
		if (output != null)
			output.println(message);
	}

	/**
	 * A method that creates a string of the player's data and sends it to the client
	 * @param id - The player's ID
	 * @param x - The player's x-coordinate
	 * @param y - The player's y-coordinate
	 * @param rotation - The player's roation
	 * @param hasFired - If the player has fired
	 * @param health - The player's health
	 * @param ammo - The player's ammo
     * @param balance - The player's funds
     * @param weaponID - The ID of the weapon the player is wielding
     */
	public void sendPlayerData(int id, int x, int y, float rotation, boolean hasFired, int health, int ammo, int balance, int weaponID){
		String s = "players;"+ id + ";pos"  + ";" + x + ";" + y + ";" + rotation+";"+hasFired + ";" + health + ";" + ammo + ";" + balance + ";" + weaponID;
		send(s);
	}

	/**
	 * A mehtod that creates a string of data from a zombie
	 * @param id - The zombie's ID
	 * @param x - The zombie's x-coordinate
	 * @param y - The zombies y-coordinate
	 * @param rotation - The zombie's rotation
     */
	public void sendZombieData(int id, int x, int y, double rotation){
		String s = "zombies;" + id + ";pos;" + x + ";" + y + ";" + rotation;
		send(s);
	}

	/**
	 * A mehtod that creates a string of data from a bullet
	 * @param id - The ID of the bullet
	 * @param x - The x-coordinate if the bullet
	 * @param y - The y-coordinate of the bullet
	 * @param direction - The direction of the bullet
     */
	public void sendBulletData(int id, int x, int y, double direction){
		String s = "bullet;" + id + ";pos;" + x + ";" + y + ";" + direction;
		send(s);
	}

	/**
	 * Creates a string of the current wave number
	 * @param wave - The current wave number
     */
	public void sendWaveData(int wave){
		String s = "wave;" + wave;
		send(s);
	}

	/**
	 * Creates a string with how much time until the next wave starts
	 * @param timeUntilNextWave - The time until the next wave starts
     */
	public void sendTimeUntilNextWaveData(int timeUntilNextWave){
		String s = "timeUntilNextWave;" + timeUntilNextWave;
		send(s);
	}

	public void sendGameOver(int score) {
		String message = "gameover;" + score;
		send(message);
	}

	/**
	 * Creates a string with the id of a dead player
	 * @param id - The id of the dead player
	 */
	public void sendDeadPlayerData(int id){
		String s = "deadPlayer;" + id;
		send(s);
	}

	/**
	 * Run-method
	 */
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
