package zombienado_v1.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import zombienado_v1.client.proxyModel.Model;

/**
 * Created by Marcus on 2016-03-23.
 */
public class Client extends Thread{

	private static Client instance = null;
	private static Model model;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;

    public static synchronized void create(Model model, InetAddress address, int port){
    	if (instance == null)
    		instance = new Client(model, address, port);
    }
    
    public static synchronized Client getInstance() throws Exception{
    	if (instance != null){ 
    		return instance;
    	} else {
    		throw new Exception("Client not initialized");
    	}
    } 
    
    private Client(Model model, InetAddress address, int port){
    Client.model = model;
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
                //serverCommand(fromServer);
            }
        } catch (IOException e){
        	System.out.println("Connection ended... ");
        }
    }
    
    public static void sendToServer(String message){
    	out.println(message);
    }
}
