package mapeditor_v1;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Martin-610 on 2016-05-22.
 */
public class MapModel {

    private int[][] map;
    private int width;
    private int height;
    private int selectedID;
    private String name;
    private PrintWriter writer;

    /**
     * Constructor for mapmodel
     * @param name Name of the map
     * @param width Width of the map
     * @param height Height of the map
     */
    public MapModel(String name, int width, int height){
        this.name = name;
        this.height=height;
        this.width=width;
        map = new int[width][height];
    }

    /**
     * A method that sets tile at x,y position to selected ID
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    public void setTile(int x, int y){
            x = x / 32;
            y = y / 32;
            map[x][y] = selectedID;
    }

    /**
     * Returns ID of tile at position x,y
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return ID
     */
    public int getTile(int x, int y){
        return  map[x][y];
    }

    /**
     *
     * @return The map
     */
    public int[][] getMap(){
        return  map;
    }

    /**
     * Fills the map with selectedTile
     */
    public void fillMap(){
        for(int i=0 ; i < width; i++){
            for(int j=0 ; j < height; j++){
                map[i][j]= selectedID;
            }
        }
    }

    /**
     * Clears the map, which in this case means setting all tiles' ID to 0;
     */
    public void clearMap(){
        for(int i=0 ; i < width; i++){
            for(int j=0 ; j < height; j++){
                map[i][j]= 00;
            }
        }
    }

    /**
     * Saves the map to a .txt file.
     * The map is saved according to a structure which the main application, Zombienado can read.
     * @throws IOException
     */
    public void saveMap() throws IOException{
        try {
            writer = new PrintWriter("src/main/resources/maps/"+name +".txt", "UTF-8");

            for(int i=0 ; i < width+1; i++){
                writer.print("00 ");
            }
            writer.println("00");
            for(int i=0 ; i < height; i++){
                writer.print("00 ");
                for(int j=0 ; j < width; j++){
                    if(map[j][i] <10){
                        writer.print("0" + map[j][i] + " ");
                    } else {
                        writer.print(map[j][i] + " ");
                    }
                }
                writer.println("00");
            }
            for(int i=0 ; i < width+2; i++){
                writer.print("00 ");
            }
            writer.close();
        }catch (IOException e){
            System.err.println("Caught IOException: " + e.getMessage());
        }

    }

    /**
     * Sets the selected ID to inputted ID
     * @param id Identification number of tile-type
     */
    public void setSelectedTile(int id){
        this.selectedID = id;
    }
}
