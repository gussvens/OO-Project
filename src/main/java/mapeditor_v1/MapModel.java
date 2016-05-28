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
    private int id;
    private String name;
    private PrintWriter writer;

    public MapModel(String name, int width, int height){
        this.name = name;
        this.height=height;
        this.width=width;
        map = new int[width][height];
    }

    public void setTile(int x, int y){
            x = x / 32;
            y = y / 32;
            map[x][y] = id;
    }

    public int getTile(int x, int y){
        return  map[x][y];
    }
    public int[][] getMap(){
        return  map;
    }

    public void fillMap(){
        for(int i=0 ; i < width; i++){
            for(int j=0 ; j < height; j++){
                map[i][j]=id;
            }
        }
    }

    public void clearMap(){
        for(int i=0 ; i < width; i++){
            for(int j=0 ; j < height; j++){
                map[i][j]= 00;
            }
        }
    }

    public void saveMap() throws IOException{
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
    }

    public void setSelectedTile(int id){
        this.id = id;
    }
}
