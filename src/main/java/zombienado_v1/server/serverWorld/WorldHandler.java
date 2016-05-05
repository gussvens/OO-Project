package zombienado_v1.server.serverWorld;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for handling a map in the game
 * Created by Marcus on 2016-04-11.
 */
public class WorldHandler{
    private static final int TILEWIDTH = 32;

    private ArrayList<ArrayList<Boolean>> solidMap;
    private ArrayList<Point> spawnList;

    public WorldHandler(){
        solidMap = new ArrayList<ArrayList<Boolean>>();
        spawnList = new ArrayList<Point>();
    }

    /**
     * Creates a map from map file in input file path
     * @param mapPath File path to map file.
     */
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
                    solidMap.add(new ArrayList<Boolean>());

                    if (line[i].equals("00")) {
                        solidMap.get(i).add(true);
                        tileCounter++;
                        System.out.println("Found Solid Tile " + tileCounter);

                    } else if (line[i].equals("10")) {
                        solidMap.get(i).add(false);
                        spawnList.add(new Point(xPosition, yPosition));
                        tileCounter++;
                        System.out.println("Found Spawner Tile " + xPosition + ", " + yPosition);
                    }

                    xPosition = xPosition + TILEWIDTH;

                }

                yPosition = yPosition + TILEWIDTH;

            }

            System.out.println("MapView created!");
        } catch (FileNotFoundException f){
            f.printStackTrace();
        }


    }

    /**
     * Returns a list containing all spawnpoints in current map
     * @return A list containing all spawnpoints in current map
     */
    public ArrayList<Point> getSpawnTiles(){
        return spawnList;
    }

    /**
     * Returns a 2D array list with all solid tiles represented as true
     * @return A 2D array list with all solid tiles represented as true
     */
    public ArrayList<ArrayList<Boolean>> getSolidMap(){
        return solidMap;
    }

    /**
     * Returns the standard width of a map tile
     * @return The standard width of a map tile
     */
    public static int getTileWidth(){
        return TILEWIDTH;
    }


}
