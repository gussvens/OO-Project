package zombienado_v1.server.serverWorld;

import zombienado_v1.Interface.IWorldHandler;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Marcus on 2016-04-11.
 */
public class WorldHandler implements IWorldHandler{
    private static final int TILEWIDTH = 32;

    private ArrayList<ArrayList<Boolean>> solidMap;
    private ArrayList<Point> spawnList;

    public WorldHandler(){
        solidMap = new ArrayList<ArrayList<Boolean>>();
        spawnList = new ArrayList<Point>();
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public ArrayList<Point> getSpawnTiles(){
        return spawnList;
    }

    /**
     * {@inheritDoc}
     */
    public ArrayList<ArrayList<Boolean>> getSolidMap(){
        return solidMap;
    }

    /**
     * {@inheritDoc}
     */
    public static int getTileWidth(){
        return TILEWIDTH;
    }


}
