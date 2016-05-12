package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Unit;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Gustav on 16-05-09.
 */
public class ZombieView {
    private Image zombieSprite;
    private Model model;

    public ZombieView(Model model, Image zombieSprite){
        this.model = model;
        this.zombieSprite = zombieSprite;
    }

    public synchronized void draw(Graphics2D graphics){

        for (int i = 0; i < model.getZombies().size(); i++){
            if (model.getZombies().get(i) != null) {
                Unit z = model.getZombies().get(i);
                graphics.drawImage(zombieSprite, GraphicsUtils.Transform(zombieSprite, z.getX() - Camera.getX(), z.getY() - Camera.getY(), z.getRotation()), null);
            }
        }
    }
}
