package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Unit;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Gustav on 16-05-17.
 */
public class BulletView {
    private Image bulletSprite;
    private Model model;

    public BulletView(Model model, Image bulletSprite){
        this.model = model;
        this.bulletSprite = bulletSprite;
    }

    public synchronized void draw(Graphics2D graphics){

        for (int i = 0; i < model.getBullets().size(); i++){
            if (model.getBullets().get(i) != null) {
                Unit b = model.getBullets().get(i);
                graphics.drawImage(bulletSprite, GraphicsUtils.Transform(bulletSprite, b.getX() - Camera.getX(), b.getY() - Camera.getY(), b.getRotation()), null);
            }
        }
    }
}
