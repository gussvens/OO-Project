package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Unit;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Erik on 2016-05-03.
 */
public class CharacterView {
    private Image[] playerSprites;
    //private Image[] weaponSprites;
    private Model model;

    public CharacterView(Model model, Image[] playerSprites){
        this.model = model;
        this.playerSprites = playerSprites;
        //this.weaponSprites = weaponSprites;
    }

    public synchronized void draw(Graphics2D graphics){
       for (int i = 0; i < model.getPlayers().size(); i++){
            if (model.getPlayers().get(i) != null) {
                Unit p = model.getPlayers().get(i);
                graphics.drawImage(playerSprites[i], GraphicsUtils.Transform(playerSprites[i], p.getX() - Camera.getX(), p.getY() - Camera.getY(), p.getRotation()), null);
                //graphics.drawImage(weaponSprites[p.getWeapon().getId()], GraphicsUtils.Transform(weaponSprites[p.getWeapon().getId()], p.getX() - Camera.getX() + Math.cos(p.getRotation())*32, p.getY() - Camera.getY() + Math.sin(p.getRotation())*32, p.getRotation()), null);
            }
        }
    }
}
