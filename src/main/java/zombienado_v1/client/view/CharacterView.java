package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Player;
import zombienado_v1.client.model.Unit;
import zombienado_v1.utilities.Animation;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;
import zombienado_v1.utilities.SoundEffect;

import java.awt.*;

/**
 * Created by Erik on 2016-05-03.
 */
public class CharacterView {
    private Image[] playerSprites;
    private Image[] weaponSprites;
    private Animation[] muzzle;
    private SoundEffect[] gunsound;
    private Model model;

    public CharacterView(Model model, Image[] playerSprites, Image[] weaponSprites, Animation[] muzzle, SoundEffect[] gunsound){
        this.model = model;
        this.playerSprites = playerSprites;
        this.weaponSprites = weaponSprites;
        this.muzzle = muzzle;
        this.gunsound = gunsound;
    }

    public synchronized void draw(Graphics2D graphics){
       for (int i = 0; i < model.getPlayers().size(); i++){
           if (model.getPlayers().get(i) != null) {
               Player p = model.getPlayers().get(i);
               double rotation = p.getRotation();
               drawWeapon(graphics,p,i);
               graphics.drawImage(playerSprites[i], GraphicsUtils.Transform(playerSprites[i], p.getX() - Camera.getX(), p.getY() - Camera.getY(), rotation), null);
            }
        }
    }

    public synchronized void drawWeapon(Graphics2D graphics, Player p, int i){
        int weaponId = p.getWeapon().getId();
        double rotation = p.getRotation();
        int distanceToMuzzle = p.getWeapon().getDistanceToMuzzle();

        muzzle[i].update();
        muzzle[i].draw((int)(p.getX() - Camera.getX() + Math.cos(rotation)*distanceToMuzzle), (int)(p.getY() - Camera.getY() + Math.sin(rotation)*distanceToMuzzle), rotation, graphics);

        graphics.drawImage(weaponSprites[weaponId], GraphicsUtils.Transform(weaponSprites[weaponId], (int)(p.getX() - Camera.getX() + Math.cos(rotation)*32), (int)(p.getY() - Camera.getY() + Math.sin(rotation)*32), rotation), null);

        if (p.hasShot()){
            try {
                gunsound[weaponId].play();
            } catch (NullPointerException e){
                gunsound[0].play();
            }
            muzzle[i].reset();
            muzzle[i].play();
        }
    }
}
