package zombienado_v1.client.view;

import zombienado_v1.client.proxyModel.Model;
import zombienado_v1.client.proxyModel.units.Unit;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Erik on 2016-05-03.
 */
public class CharacterView {
    private Image[] playerSprites;
    private Image zombieSprite;
    private Model model;

    public CharacterView(Model model, Image[] playerSprites, Image zombieSprite){
        this.model = model;
        this.playerSprites = playerSprites;
        this.zombieSprite = zombieSprite;
    }

    public synchronized void draw(Graphics2D graphics){
       for (int i = 0; i < model.getPlayers().size(); i++){
            if (model.getPlayers().get(i) != null) {
                Unit p = model.getPlayers().get(i);
                graphics.drawImage(playerSprites[i], GraphicsUtils.Transform(playerSprites[i], p.getX() - Camera.getX(), p.getY() - Camera.getY(), p.getRotation()), null);
            }
        }

    //    graphics.drawImage(playerSprites[model.getPlayer().getID()], GraphicsUtils.Transform(playerSprites[model.getPlayer().getID()], model.getPlayer().getX() - Camera.getX(), model.getPlayer().getY() - Camera.getY(), model.getPlayer().getRotation()), null);

        for (int i = 0; i < model.getZombies().size(); i++){
            if (model.getZombies().get(i) != null) {
                Unit z = model.getZombies().get(i);
                graphics.drawImage(zombieSprite, GraphicsUtils.Transform(zombieSprite, z.getX() - Camera.getX(), z.getY() - Camera.getY(), z.getRotation()), null);
            }
        }
    }
}
