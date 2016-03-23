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

    private DatagramSocket clientSocket;
    private InetAddress IPAddress;
    private byte[] sendData;
    private byte[] receiveData;
    private boolean running = false;
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ClientController(InetAddress address){
        try {
            socket = new Socket(address, 9876);
        } catch (UnknownHostException u){
            System.out.println("Unknown host");
        } catch (IOException e){
            System.out.println("Stuff");
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
