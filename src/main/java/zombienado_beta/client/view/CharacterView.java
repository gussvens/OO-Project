package zombienado_beta.client.view;

import zombienado_beta.client.model.Model;
import zombienado_beta.client.model.Player;
import zombienado_beta.utilities.Animation;
import zombienado_beta.utilities.Camera;
import zombienado_beta.utilities.GraphicsUtils;
import zombienado_beta.utilities.SoundEffect;

import java.awt.*;

/**
 * Created by Erik on 2016-05-03.
 */
public class CharacterView {
    private Image[] playerSprites;
    private Image[] weaponSprites;
    private Image healthBar;
    private Animation[] muzzle;
    private Animation recoilSight;
    private SoundEffect[] gunsound;
    private Model model;

    public CharacterView(Model model, Image[] playerSprites, Image[] weaponSprites, Image healthBar, Animation[] muzzle, SoundEffect[] gunsound, Animation recoilSight){
        this.model = model;
        this.playerSprites = playerSprites;
        this.weaponSprites = weaponSprites;
        this.muzzle = muzzle;
        this.gunsound = gunsound;
        this.recoilSight = recoilSight;
        this.healthBar = healthBar;
    }

    public synchronized void draw(Graphics2D graphics){
       for (int i = 0; i < model.getPlayers().size(); i++){
           if (model.getPlayers().get(i) != null && !model.getPlayers().get(i).isDead()) {
               Player p = model.getPlayers().get(i);
               double rotation = p.getRotation();
               drawWeapon(graphics, p, i);
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
                gunsound[weaponId].play(p.getX(),p.getY(),model.getPlayer().getX(),model.getPlayer().getY());
            } catch (NullPointerException e){
                gunsound[0].play(p.getX(),p.getY(),model.getPlayer().getX(),model.getPlayer().getY());
            }
            muzzle[i].reset();
            muzzle[i].play();
            if (p == model.getPlayer()){
                recoilSight.reset();
                recoilSight.play();
            }
        }
    }

    public synchronized void drawHealthBars(Graphics2D graphics){
        for (Player p : model.getPlayers()) {
            if (p != null && !p.isDead()) {
                if (p != model.getPlayer()) {
                    graphics.drawImage(healthBar, p.getX() - Camera.getX() - healthBar.getWidth(null) / 2, p.getY() - Camera.getY() - 30, null);
                    graphics.setColor(Color.green);
                    if (p.getHealth() <= 30) graphics.setColor(Color.red);
                    graphics.fillRect(p.getX() - Camera.getX() - healthBar.getWidth(null) / 2 + 1, p.getY() - Camera.getY() - 30 + 1, (healthBar.getWidth(null) - 2) * p.getHealth() / 100, 3);
                }
            }
        }
    }
}
