package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
        while(true) {
            try {
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int[][] positions = mainServer.getPlayerPositions();
            for(int i = 0; i < positions.length; i++){
                String s = positions[i][0] + ":" + positions[i][1];
                output.println(s);
            }

            try {
                Thread.sleep((long) 0.03);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            if(positions[0][0] < -1){
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
