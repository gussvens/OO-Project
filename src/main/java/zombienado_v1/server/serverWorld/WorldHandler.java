package zombienado_v1.server.serverWorld;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Marcus on 2016-04-11.
 */
public class WorldHandler {
    private static final int TILE_WIDTH = 32;

    private ArrayList<Point> wallList;
    private ArrayList<Point> spawnList;

    public WorldHandler(){
        wallList = new ArrayList<Point>();
        spawnList = new ArrayList<Point>();
    }

    public void createMap(String mapPath){
        
        File file = new File(mapPath);

        try {



            Scanner scanner = new Scanner(file);


            int yPosition = 0;

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");


                int xPosition = 0;
                int tileCounter = 0;
                for(int i = 0; i < line.length; i++) {


                    if (line[i].equals("00")) {
                        wallList.add(new Point(xPosition, yPosition));
                        tileCounter++;
                        System.out.println("Found Solid Tile " + tileCounter);

                    } else if (line[i].equals("10")) {
                        spawnList.add(new Point(xPosition, yPosition));
                        tileCounter++;
                        System.out.println("Found Spawner Tile " + xPosition + ", " + yPosition);
                    }

                    xPosition = xPosition + 32;

                }

                yPosition = yPosition + 32;

            }

            System.out.println("MapView created!");
        } catch (FileNotFoundException f){
            f.printStackTrace();
        }


    }

    public void checkCollisions(){

    }

    public ArrayList<Point> getSpawnTiles(){
        return spawnList;
    }

    public ArrayList<Point> getWallTiles(){
        return wallList;
    }

    public static int getTileWidth(){
        return TILE_WIDTH;
    }

}
