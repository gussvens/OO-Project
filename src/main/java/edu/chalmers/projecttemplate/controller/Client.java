package edu.chalmers.projecttemplate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by Marcus on 2016-03-23.
 */
public class Client extends Thread{

    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;

    public Client(InetAddress address, int Port){
    	try {
            socket = new Socket(address, Port);
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
            }
        } catch (IOException e){
        	System.out.println("Connection ended... ");
        }
    }
    
    public static void sendToServer(String message){
    	out.println(message);
    }
}
