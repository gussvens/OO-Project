package zombienado_beta.server.serverWorld;

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

    /**
     * Constructor for a WorldHandler
     */
    public WorldHandler(){
        wallList = new ArrayList<Point>();
        spawnList = new ArrayList<Point>();
    }

    /**
     * A method that creates a "map" from the specified text file and stores walls and spawnpoints int two different ArrayLists
     * @param mapPath - The path to the text file
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


                    if (line[i].equals("00")) {
                        wallList.add(new Point(xPosition, yPosition));
                        tileCounter++;

                    } else if (line[i].equals("10")) {
                        spawnList.add(new Point(xPosition, yPosition));
                        tileCounter++;
                    }

                    xPosition = xPosition + 32;

                }

                yPosition = yPosition + 32;

            }
        } catch (FileNotFoundException f){
            f.printStackTrace();
        }


    }

    /**
     * @return - The ArrayList of spawnpoints
     */
    public ArrayList<Point> getSpawnTiles(){
        return spawnList;
    }

    /**
     * @return - The ArrayList of walls
     */
    public ArrayList<Point> getWallTiles(){
        return wallList;
    }

    /**
     * @return - The width of the tiles
     */
    public static int getTileWidth(){
        return TILE_WIDTH;
    }

}
