package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-03-24.
 */
public class SendThread extends Thread {

    private Socket socket;
    private Server mainServer;
    private PrintWriter output;

    public SendThread(Socket socket, Server server){
        this.socket = socket;
        this.mainServer = server;
    }

    public void run(){
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<int[]> positions = mainServer.getPlayerPositions();
        while(true) {

            for(int i = 0; i < positions.size(); i++){
                int[] q = positions.get(i);
                String s = q[0] + ";" + q[1];
                System.out.println("Sending Data!");
                output.println(s);
            }

            try {
                Thread.sleep((long) 0.03);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            if(positions.isEmpty()){
                break;
            }

        }

        System.out.print("Shutting down");

        output.close();
        try {
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
