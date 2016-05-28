package zombienado_beta.client.view;

import zombienado_beta.client.model.Model;
import zombienado_beta.client.model.Unit;
import zombienado_beta.utilities.Camera;
import zombienado_beta.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Gustav on 16-05-09.
 * A class to represent all zombie units graphicly
 */
public class ZombieView {
    private Image zombieSprite;
    private Model model;

    public ZombieView(Model model, Image zombieSprite){
        this.model = model;
        this.zombieSprite = zombieSprite;
    }

    /**
     * Draws all zombie units in the game
     * @param graphics The current graphics setting
     */
    public synchronized void draw(Graphics2D graphics){

        for (int i = 0; i < model.getZombies().size(); i++){
            if (model.getZombies().get(i) != null) {
                Unit z = model.getZombies().get(i);
                graphics.drawImage(zombieSprite, GraphicsUtils.Transform(zombieSprite, z.getX() - Camera.getX(), z.getY() - Camera.getY(), z.getRotation()), null);
            }
        }
    }
}
