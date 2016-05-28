package zombienado_beta.client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * A communication service used for transmitting data to/from the server
 */
public class ServerCommunicator extends Thread {

    private static ServerCommunicator instance = null;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;
    private ArrayList<Player> players;
    private ArrayList<Unit> zombies;
    private ArrayList<Unit> bullets;
    private int myID = -1;
    private boolean wasPressing = false;
    private int wave = 1;
    private int timeUntilNextWave = -1;
    private boolean gameOver = false;
    private int finalScore;
    public String mapName;

    /**
     * Creates a new ServerComunicator
     * @param address The IP-address which to connect to
     * @param port The port which to connect to
     */
    public static synchronized void create(InetAddress address, int port) {
        if (instance == null)
            instance = new ServerCommunicator(address, port);
    }

    /**
     * Used for accessing the ServerCommunicator instance
     * @return the instance
     * @throws Exception if not yet initialized
     */
    public static synchronized ServerCommunicator getInstance() throws Exception {
        if (instance != null) {
            return instance;
        } else {
            throw new Exception("Client not initialized");
        }
    }

    /** Constructor
     * Sets up the connection
     * @param address the IP-adress which to connect to
     * @param port the port which to connect to
     */
    private ServerCommunicator(InetAddress address, int port) {
        players = new ArrayList<Player>();
        for (int i = 0; i < 4; i++) {
            players.add(null);
        }
        zombies = new ArrayList<Unit>();
        bullets = new ArrayList<Unit>();
        try {
            socket = new Socket(address, port);
        } catch (UnknownHostException u) {
            System.out.println("Unknown host");
        } catch (IOException e) {
            System.out.println("Connection timed out");
            e.printStackTrace();
        }

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {

        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {

        }

        this.start();
    }

    /**
     * Checks if entities are obsolete and removes them
     * An entity (except players) is considered obsolete when they have not received any updates for a certain amount of time
     */
    private synchronized void checkForObsoletions(){
        while (zombies == null ||bullets == null){
            //WAIT
        }

        while (true){
            long timeNow = System.currentTimeMillis();
            for (int i = bullets.size() - 1; i >= 0; i--){
                if (timeNow - bullets.get(i).getLastUpdate() > 100){
                    bullets.remove(i);
                }
            }
            for (int i = zombies.size() - 1; i >= 0; i--){
                if (timeNow - zombies.get(i).getLastUpdate() > 100){
                    zombies.remove(i);
                }
            }

            try {
                this.wait(30);
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    /**
     * Starts in parallel; the check for obsolete entities, and the service for listening to the server.
     */
    public void run() {
        Thread checkObsoletions = new Thread(() -> checkForObsoletions());
        checkObsoletions.start();
        listenToServer();
    }

    /**
     * The method that listens for data from the server
     */
    public void listenToServer() {
        System.out.println("listening");
        String fromServer;
        try {
            while ((fromServer = in.readLine()) != null) {
                //System.out.println("Server: " + fromServer);
                if (fromServer.equals("shutdown")) {
                    break;
                }
                serverCommand(fromServer);
            }
        } catch (IOException e) {
            System.out.println("Connection ended... ");
        }
    }

    /**
     * Sends the movement data
     * @param x the distance to move in x
     * @param y the distance to move in y
     * @param r the absolute rotation of the player
     * @param oldRotation the previous rotation of the player
     */
    public synchronized void movePlayer(float x, float y, float r, float oldRotation) {
        if (x == 0 && y == 0 && r - oldRotation == 0.0) return; //If nothing changed, do not send
        String message = "move;" + x + ";" + y + ";" + r;
        out.println(message);
    }

    /**
     * Sends request to start/stop shooting
     * @param isShooting the status
     */
    public synchronized void shoot(boolean isShooting) {
        if (wasPressing != isShooting) {
            String message = "shoot;" + isShooting;
            out.println(message);
            wasPressing = isShooting;
        }
    }

    /**
     * sends request to buy new weapon
     * @param weaponID
     */
    public synchronized void buyWeapon(int weaponID){
        String message = "weapon;" + weaponID;
        out.println(message);
    }


    /**
     * Server command parsing
     * @param s - The message that the zombienado_v1.client received
     */
    public synchronized void serverCommand(String s) {
        String[] arg = s.split(";");
        switch(arg[0]){
            case "player":
                joinCommand(arg);
                break;
            case "players":
                playersCommand(arg);
                break;
            case "deadPlayer":
                deadPlayerCommand(arg);
                break;
            case "zombies":
                zombiesCommand(arg);
                break;
            case "bullet":
                bulletsCommand(arg);
                break;
            case "wave":
                wave = Integer.parseInt(arg[1]);
                break;
            case "timeUntilNextWave":
                timeUntilNextWave = Integer.parseInt(arg[1]);
                break;
            case "gameover":
                gameOver = true;
                finalScore = Integer.parseInt(arg[1]);
                break;
        }
    }

    /**
     * Received on join, sets the ID of the client (& player)
     * @param arg
     */
    private synchronized void joinCommand(String[]arg){
        if (arg[1].equals("id")) {
            myID = Integer.parseInt(arg[2]);
            mapName = arg[4];
        }
    }

    /**
     * Received constantly, all necessary player data
     * eg. ID, Position, Rotation, Health etc.
     * @param arg
     */
    private synchronized void playersCommand(String[] arg){
        int id = Integer.parseInt(arg[1]);
        if (arg[2].equals("pos")) {
            if (players == null) return;
            if (players.get(id) == null) {
                players.set(id, new Player());
            }

            int x = Integer.parseInt(arg[3]);
            int y = Integer.parseInt(arg[4]);
            float rot = Float.parseFloat(arg[5]);
            boolean hasFired = Boolean.parseBoolean(arg[6]);
            int health = Integer.parseInt(arg[7]);
            int ammo = Integer.parseInt(arg[8]);
            int balance = Integer.parseInt(arg[9]);
            int weaponID = Integer.parseInt(arg[10]);
            players.get(id).setPosition(x, y);
            players.get(id).setRotation(rot);
            players.get(id).setHealth(health);
            players.get(id).setAmmo(ammo);
            players.get(id).setBalance(balance);
            players.get(id).setWeapon(weaponID);
            if (hasFired) {
                players.get(id).shoot();
            } else {
                players.get(id).hasShot();
            }
        }
    }

    /**
     * Received when a player dies
     * @param arg
     */
    private synchronized void deadPlayerCommand(String[] arg){
        int id  = Integer.parseInt(arg[1]);
        players.get(id).setDead(true);
    }

    /**
     * Received constantly, all necessary zombie data
     * @param arg
     */
    private synchronized void zombiesCommand(String[] arg){
        int id = Integer.parseInt(arg[1]);
        if (arg[2].equals("pos")) {

            if (zombies == null) return;
            while (zombies.size() <= id) {
                zombies.add(new Zombie());
            }

            int x = Integer.parseInt(arg[3]);
            int y = Integer.parseInt(arg[4]);
            float rot = Float.parseFloat(arg[5]);
            zombies.get(id).setPosition(x, y);
            zombies.get(id).setRotation(rot);
            zombies.get(id).setLastUpdate(System.currentTimeMillis());
        }
    }

    /**
     * Received constantly, all necessary bullet data
     * @param arg
     */
    private synchronized void bulletsCommand(String[] arg){
        int id = Integer.parseInt(arg[1]);
        if (arg[2].equals("pos")) {
            if (bullets == null) return;
            while (bullets.size() <= id) {
                bullets.add(new Bullet());
            }

            int x = Integer.parseInt(arg[3]);
            int y = Integer.parseInt(arg[4]);
            float rot = Float.parseFloat(arg[5]);

            bullets.get(id).setPosition(x, y);
            bullets.get(id).setRotation(rot);
            bullets.get(id).setLastUpdate(System.currentTimeMillis());
        }
    }

    /**
     * returns a copy of the last received players
     * @return copy - A copy of the arraylist with players
     */
    public synchronized ArrayList<Player> getPlayers() {
        ArrayList<Player> copy = new ArrayList<>();
        for (Unit player : players) {
            Player p = (Player) player;
            if (p != null)
                copy.add(p.copy());
        }
        return copy;
    }

    /**
     * returns a copy of the last received zombies
     * @return copy - A copy of the arraylist with zombies
     */
    public synchronized ArrayList<Unit> getZombies() {
        ArrayList<Unit> copy = new ArrayList<Unit>();
        for (Unit zombie : zombies) {
            Zombie z = (Zombie) zombie;
            if (z != null)
                copy.add(z.copy());
        }
        return copy;
    }

    /**
     * returns a copy of the last received bullets
     * @return copy - A copy of the arraylist with bullets
     */
    public synchronized ArrayList<Unit> getBullets() {
        ArrayList<Unit> copy = new ArrayList<Unit>();
        if(bullets!=null){
            for (Unit bullet : bullets) {
                Bullet b = (Bullet) bullet;
                if (b != null)
                    copy.add(b.copy());
            }
        }
        return copy;
    }

    /**
     * returns the clients/players ID
     * @return
     */
    public int getID(){
        return myID;
    }

    /**
     * returns the current wave
     * @return
     */
    public int getWave(){
        return wave;
    }

    /**
     * returns the time until next wave, or -1 during an ongoing wave
     * @return
     */
    public int getTimeUntilNextWave(){
        return timeUntilNextWave;
    }

    /**
     * returns if the state of the game is "game over"
     * @return
     */
    public boolean getGameOver(){
        return gameOver;
    }

    /**
     * Returns the final score
     * @return
     */
    public int getFinalScore() { return finalScore; }

    /**
     * Returns the correct map to load
     * @return
     */
    public String getMapName() { return mapName; }
}
