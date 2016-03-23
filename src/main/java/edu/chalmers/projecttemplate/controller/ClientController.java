package edu.chalmers.projecttemplate.controller;

import java.io.IOException;
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

    public ClientController(byte[] address){

        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException s){
            System.out.println("Something wrong with socket");
        }

        try {
        	IPAddress = InetAddress.getLocalHost();
        	System.out.println("Localhost is: "+ InetAddress.getLocalHost());
        } catch (UnknownHostException u){
            System.out.println("Unknown host");
        }

        sendData = new byte[1024];
        receiveData = new byte[1024];
        running = true;

        update();
    }

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


    }

}
