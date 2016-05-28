package zombienado_beta.client.view;

import zombienado_beta.client.model.Model;
import zombienado_beta.client.model.Unit;
import zombienado_beta.utilities.Camera;
import zombienado_beta.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Gustav on 16-05-17.
 * A class to represent the bullets graphicly
 */
public class BulletView {
    private Image bulletSprite;
    private Model model;

    public BulletView(Model model, Image bulletSprite){
        this.model = model;
        this.bulletSprite = bulletSprite;
    }

    /**
     * Draws all bullets in the game
     * @param graphics The current graphics setting
     */
    public synchronized void draw(Graphics2D graphics){

        for (int i = 0; i < model.getBullets().size(); i++){
            if (model.getBullets().get(i) != null) {
                Unit b = model.getBullets().get(i);
                graphics.drawImage(bulletSprite, GraphicsUtils.Transform(bulletSprite, b.getX() - Camera.getX(), b.getY() - Camera.getY(), b.getRotation()), null);
            }
        }
    }
}
