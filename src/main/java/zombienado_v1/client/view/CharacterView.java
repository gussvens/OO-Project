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
    private Image weaponSprites;
    private Animation muzzle;
    private SoundEffect gunsound;
    private Model model;

    public CharacterView(Model model, Image[] playerSprites, Image weaponSpriteSheet, Animation muzzle, SoundEffect gunsound){
        this.model = model;
        this.playerSprites = playerSprites;
        this.weaponSprites = weaponSpriteSheet;
        this.muzzle = muzzle;
        this.gunsound = gunsound;
    }

    public synchronized void draw(Graphics2D graphics){
       for (int i = 0; i < model.getPlayers().size(); i++){
            if (model.getPlayers().get(i) != null) {
                Player p = model.getPlayers().get(i);
                int weaponId = p.getWeapon().getId();
                int wX;
                int wY;
                if (weaponId < 10) {
                    wX = 0;
                    wY = weaponId;
                } else {
                    String s = "" + weaponId;
                    wX = Integer.parseInt(s.substring(0, 1));
                    wY = Integer.parseInt(s.substring(1, 2));
                }
                graphics.drawImage(playerSprites[i], GraphicsUtils.Transform(playerSprites[i], p.getX() - Camera.getX(), p.getY() - Camera.getY(), p.getRotation()), null);
                Image weapon = GraphicsUtils.getImageFromSheet(wX, wY, 64, 64, weaponSprites);
                muzzle.update();
                muzzle.draw((int)(p.getX() - Camera.getX() + Math.cos(p.getRotation())*72), (int)(p.getY() - Camera.getY() + Math.sin(p.getRotation())*72), p.getRotation(), graphics);

                graphics.drawImage(weapon, GraphicsUtils.Transform(weapon, (int)(p.getX() - Camera.getX() + Math.cos(p.getRotation())*32), (int)(p.getY() - Camera.getY() + Math.sin(p.getRotation())*32), p.getRotation()), null);
                if (p.hasShot()){
                    System.out.println("HAS SHOT");
                    gunsound.play();
                    muzzle.reset();
                    muzzle.play();
                }
            }
        }
    }
}
