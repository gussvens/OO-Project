package zombienado_v1.client.model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-05-04.
 */
public class ServerCommunicator extends Thread {

    private static ServerCommunicator instance = null;
    private static Model model;
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

    public static synchronized void create(Model model, InetAddress address, int port) {
        if (instance == null)
            instance = new ServerCommunicator(model, address, port);
    }

    public static synchronized ServerCommunicator getInstance() throws Exception {
        if (instance != null) {
            return instance;
        } else {
            throw new Exception("Client not initialized");
        }
    }

    private ServerCommunicator(Model model, InetAddress address, int port) {
        ServerCommunicator.model = model;
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
     * Checks if entities are obsolete
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

    public void run() {

        Thread checkObsoletions = new Thread(() -> checkForObsoletions());
        checkObsoletions.start();
        listenToServer();
    }

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

    public synchronized void movePlayer(int x, int y, double r, double oldRotation) {
        if (x == 0 && y == 0 && r - oldRotation == 0.0) return; //If nothing changed, do not send
        String message = "move;" + x + ";" + y + ";" + r;
        out.println(message);
    }

    public synchronized void shoot(boolean isShooting) {
        if (wasPressing != isShooting) {
            String message = "shoot;" + isShooting;
            out.println(message);
            wasPressing = isShooting;
        }
    }

    public synchronized void requestReload(boolean reload) {
        if (!reload) return;
        String message = "reload;";
        out.println(message);
    }

    public synchronized void buyWeapon(int weaponID){
        String message = "weapon;" + weaponID;
        out.println(message);
    }


    /**
     * SERVER COMMAND PARSING
     *
     * @param s - The message that the zombienado_v1.client received
     */
    public synchronized void serverCommand(String s) {

        String[] arg = s.split(";");
        if (arg[0].equals("player")) {
            if (arg[1].equals("id")) {
                myID = Integer.parseInt(arg[2]);
            }
        } else if (arg[0].equals("players")) {
            int id = Integer.parseInt(arg[1]);
            if (arg[2].equals("pos")) {
                if (players == null) return;
                if (players.get(id) == null) {
                    players.set(id, new Player());
                }

                int x = Integer.parseInt(arg[3]);
                int y = Integer.parseInt(arg[4]);
                double rot = Double.parseDouble(arg[5]);
                boolean hasFired = Boolean.parseBoolean(arg[6]);
                int health = Integer.parseInt(arg[7]);
                int ammo = Integer.parseInt(arg[8]);
                int balance = Integer.parseInt(arg[9]);
                int weaponID = Integer.parseInt(arg[10]);
                players.get(id).setPosition(x, y);
                if (id != myID) {
                    players.get(id).setRotation(rot);
                }
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
        } else if (arg[0].equals("deadPlayer")){
            int id  = Integer.parseInt(arg[1]);
            System.out.println("dead: " + id);
            players.get(id-1).setDead(true);
        }else if (arg[0].equals("zombies")) {
            int id = Integer.parseInt(arg[1]);
            if (arg[2].equals("pos")) {

                if (zombies == null) return;
                if (zombies.size() <= id) {
                    zombies.add(new Zombie());
                }

                int x = Integer.parseInt(arg[3]);
                int y = Integer.parseInt(arg[4]);
                double rot = Double.parseDouble(arg[5]);
                zombies.get(id).setPosition(x, y);
                zombies.get(id).setRotation(rot);
                zombies.get(id).setLastUpdate(System.currentTimeMillis());
            }
        } else if (arg[0].equals("bullet")) {
            int id = Integer.parseInt(arg[1]);
            if (arg[2].equals("pos")) {
                if (bullets == null) return;
                if (bullets.size() <= id) {
                    int diff = id - bullets.size();
                    for (int i = 0; i<= diff; i++) {
                        bullets.add(new Bullet());
                    }
                }

                int x = Integer.parseInt(arg[3]);
                int y = Integer.parseInt(arg[4]);
                double rot = Double.parseDouble(arg[5]);

                bullets.get(id).setPosition(x, y);
                bullets.get(id).setRotation(rot);
                bullets.get(id).setLastUpdate(System.currentTimeMillis());
            }
        } else if (arg[0].equals("wave")) {
            wave = Integer.parseInt(arg[1]);
        } else if (arg[0].equals("timeUntilNextWave")){
            timeUntilNextWave = Integer.parseInt(arg[1]);
        } else if(arg[0].equals("gameover")) {
            gameOver = true;
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
     * returns a copy of the last received
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

    public int getID(){
        return myID;
    }

    public int getWave(){
        return wave;
    }

    public int getTimeUntilNextWave(){
        return timeUntilNextWave;
    }

    public boolean getGameOver(){
        return gameOver;
    }
}
