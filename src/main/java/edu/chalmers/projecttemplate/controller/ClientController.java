package edu.chalmers.projecttemplate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by Marcus on 2016-03-23.
 */
public class ClientController {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientController(){
    	
    }
    
    public void setupClient(InetAddress address, int Port){
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

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        try {
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (IOException e){

        }
    }
/*
    private void update(){
        while(running) {
            String text = "TEST";
            sendData = text.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                System.out.println("Couldn't send!");
            }

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                clientSocket.receive(receivePacket);
            } catch (IOException e) {
                System.out.println("Couldn't receive data!");
            }
        }


    }*/

}
