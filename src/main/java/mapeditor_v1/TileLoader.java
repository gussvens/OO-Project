package mapeditor_v1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Martin-610 on 2016-05-22.
 */
public class TileLoader {
    private final int width = 32;
    private final int height = 32;
    private final int rows = 10;
    private final int cols = 10;

    public BufferedImage[] loadImage(String path){
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage[] sprites = new BufferedImage[rows * cols];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
                try {
                    assert image != null;
                    sprites[(j * cols) + i] = image.getSubimage(
                            j * width,
                            i * height,
                            width,
                            height
                    );
                } catch (NullPointerException e) {

                }
        }

        return sprites;
    }
}
