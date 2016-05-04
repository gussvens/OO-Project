package zombienado_v1.client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Marcus on 2016-05-04.
 */
public class ServerCommunicator extends Thread{

    private static ServerCommunicator instance = null;
    private static Model model;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;

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
                model.serverCommand(fromServer);
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

}
