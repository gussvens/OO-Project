package mapeditor_v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Martin-610 on 2016-05-22.
 */
public class TooltipLoader {
    private String[] lines;

    /**
     * Method that loads a .txt file and divides each row of the file into a string
     * @param path path of .txt file
     * @return returns array of strings
     */
    public String[] loadText(String path){
        String[] lines= new String[100];
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < 100; i++){
                lines[i]=bufferedReader.readLine();
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
