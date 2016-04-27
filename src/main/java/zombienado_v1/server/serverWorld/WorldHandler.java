package zombienado_v1.server.serverWorld;

import zombienado_v1.server.serverWorld.serverTiles.*;

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
                        wallList.add(new SolidTile(xPosition, yPosition));
                        tileCounter++;
                        System.out.println("Found Solid Tile " + tileCounter);

                    } else if (line[i].equals("10")) {
                        spawnList.add(new SpawnerTile(xPosition, yPosition));
                        tileCounter++;
                        System.out.println("Found Spawner Tile " + xPosition + ", " + yPosition);
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

    public ArrayList<SpawnerTile> getSpawnTiles(){
        return spawnList;
    }

    public ArrayList<SolidTile> getWallTiles(){
        return wallList;
    }


}
