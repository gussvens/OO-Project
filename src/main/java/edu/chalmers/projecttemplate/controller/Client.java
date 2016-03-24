package edu.chalmers.projecttemplate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import edu.chalmers.projecttemplate.model.Model;

/**
 * Created by Marcus on 2016-03-23.
 */
public class Client extends Thread{

	private static Client instance = null;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;

    public static synchronized void create(InetAddress address, int port){
    	if (instance == null)
    		instance = new Client(address, port);
    }
    
    public static synchronized Client getInstance() throws Exception{
    	if (instance != null){ 
    		return instance;
    	} else {
    		throw new Exception(); //MUST FIX O.O
    	}
    }
    
    private Client(InetAddress address, int port){
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

    }
    
    public void run(){
    	listenToServer();
    }
    
    public void listenToServer(){
        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        try {
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }
                Model.passInstace().serverCommand(fromServer);
            }
        } catch (IOException e){
        	System.out.println("Connection ended... ");
        }
    }
    
    public static void sendToServer(String message){
    	out.println(message);
    }
}
