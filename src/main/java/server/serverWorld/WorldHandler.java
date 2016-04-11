package server.serverWorld;

import server.serverWorld.serverTiles.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Marcus on 2016-04-11.
 */
public class WorldHandler {

    private ArrayList<SolidTile> wallList;
    private ArrayList<SpawnerTile> spawnList;

    public WorldHandler(){
        wallList = new ArrayList<SolidTile>();
        spawnList = new ArrayList<SpawnerTile>();
    }

    public void createMap(){

        File file = new File("src/main/resources/maps/mapTest.txt");

        try {
            Scanner scanner = new Scanner(file);


            int yPosition = 0;

            while (scanner.hasNextLine()) {

                int xPosition = 0;
                int tileCounter = 0;
                while (scanner.hasNextInt()) {


                    if (scanner.nextInt() == 00) {
                        wallList.add(new SolidTile(xPosition, yPosition));
                        tileCounter++;
                        System.out.println("Found Solid Tile " + tileCounter);

                    } else if (scanner.nextInt() == 10) {
                        spawnList.add(new SpawnerTile(xPosition, yPosition));
                        tileCounter++;
                        System.out.println("Found Spawner Tile " + tileCounter);
                    }

                    xPosition = xPosition + 32;

                }

                yPosition = yPosition + 32;

            }

            System.out.println("Map created!");
        } catch (FileNotFoundException f){
            f.printStackTrace();
        }


    }

    public void checkCollisions(){
        
    }


}
