package zombienado_v1.client.proxyModel;

import zombienado_v1.client.proxyModel.units.Player;
import zombienado_v1.client.proxyModel.units.Unit;
import zombienado_v1.client.proxyModel.units.Zombie;

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
public class ServerCommunicator extends Thread{

    private static ServerCommunicator instance = null;
    private static Model model;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;
    private ArrayList<Unit> players;
    private ArrayList<Unit> zombies;
    private int myID = -1;
    private boolean isReady = false;
    private Point playerPosition;

    public static synchronized void create(Model model, InetAddress address, int port){
        if (instance == null)
            instance = new ServerCommunicator(model, address, port);
    }

    public static synchronized ServerCommunicator getInstance() throws Exception{
        if (instance != null){
            return instance;
        } else {
            throw new Exception("Client not initialized");
        }
    }

    private ServerCommunicator(Model model, InetAddress address, int port){
        ServerCommunicator.model = model;
        players = new ArrayList<Unit>();
        for(int i = 0; i < 4; i++){
            players.add(null);
        }
        zombies = new ArrayList<Unit>();
        try {
            socket = new Socket(address, port);
        } catch (UnknownHostException u){
            System.out.println("Unknown host");
        } catch (IOException e){
            System.out.println("Connection timed out");
            e.printStackTrace();
        }

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e){

        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e){

        }
        this.start();
    }

    public void run(){
        listenToServer();
    }

    public void listenToServer(){
        System.out.println("listening");
        String fromServer;
        try {
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("shutdown")) {
                    break;
                }
                serverCommand(fromServer);
            }
        } catch (IOException e){
            System.out.println("Connection ended... ");
        }
    }

    public void movePlayer(int x, int y, double r){


        String message = "move;" + x + ";" + y + ";" + r;
        System.out.println(message);
        out.println(message);
    }

    public void moveLeft(){

        String message = "move;" + ";" + "-2" + ";" + 0;
        out.println(message);
    }

    public void moveRight(){

        String message = "move;" + 2 + ";" + 0;
        out.println(message);
    }

    public void moveUp(){

        String message = "move;" + 0 + ";" + "-2";
        out.println(message);
    }

    public void moveDown(){

        String message = "move;" + 0 + ";" + 2;
        out.println(message);
    }

    public static void shoot(){

    }


    /** SERVER COMMAND PARSING
     * @param s - The message that the zombienado_v1.client received
     */
    public synchronized void serverCommand(String s){

        String[] arg = s.split(";");
        if (arg[0].equals("player")){
            if (arg[1].equals("id")){
                myID = Integer.parseInt(arg[2]);
            }
        } else if (arg[0].equals("players")){
            int id = Integer.parseInt(arg[1]);
            if (arg[2].equals("pos")){
                if (players == null) return;
                if (players.get(id) == null){
                    players.set(id, new Player());
                }

                int x = Integer.parseInt(arg[3]);
                int y = Integer.parseInt(arg[4]);
                double rot = Double.parseDouble(arg[5]);
                players.get(id).setPosition(x,y);
                players.get(id).setRotation(rot);
            }
        } else if(arg[0].equals("zombies")){
            int id = Integer.parseInt(arg[1]);
            if (arg[2].equals("pos")){

                if (zombies == null) return;
                if (zombies.size() <= id){
                    zombies.add(new Zombie());
                    //zombies.get(id).setTexture(zombieSprite);
                }

                int x = Integer.parseInt(arg[3]);
                int y = Integer.parseInt(arg[4]);
                double rot = Double.parseDouble(arg[5]);
                zombies.get(id).setPosition(x,y);
                zombies.get(id).setRotation(rot);
            }
        }
        isReady = true;
    }

    public ArrayList<Unit> getPlayers(){
        return players;
    }

    public ArrayList<Unit> getZombies(){
        return zombies;
    }

    public int getID(){
        return myID;
    }

    public boolean isReady(){
        return isReady;
    }

}
