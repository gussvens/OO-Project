package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.OtherPlayer;
import zombienado_v1.client.model.Unit;
import zombienado_v1.client.model.Zombie;
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
       for (int i = 0; i < model.getOtherPlayers().size(); i++){
            if (model.getOtherPlayers().get(i) != null) {
                Unit op = model.getOtherPlayers().get(i);
                graphics.drawImage(playerSprites[i], GraphicsUtils.Transform(playerSprites[i], op.getX() - Camera.getX(), op.getY() - Camera.getY(), op.getRotation()), null);
            }
        }

        graphics.drawImage(playerSprites[model.getPlayer().getID()], GraphicsUtils.Transform(playerSprites[model.getPlayer().getID()], model.getPlayer().getX() - Camera.getX(), model.getPlayer().getY() - Camera.getY(), model.getPlayer().getRotation()), null);

        for (int i = 0; i < model.getZombies().size(); i++){
            if (model.getZombies().get(i) != null) {
                Unit z = model.getZombies().get(i);
                graphics.drawImage(zombieSprite, GraphicsUtils.Transform(zombieSprite, z.getX() - Camera.getX(), z.getY() - Camera.getY(), z.getRotation()), null);
            }
        }
    }
}
