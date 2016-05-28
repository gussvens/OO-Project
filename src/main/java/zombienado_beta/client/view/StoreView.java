package zombienado_beta.client.view;

import zombienado_beta.client.model.Model;
import zombienado_beta.client.model.weapon.Weapon;
import zombienado_beta.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Gustav on 16-05-21.
 * A class to represent the store graphicly between rounds
 */
public class StoreView {
    private Image storeSprite;
    private Image[] weaponSprites;
    private Model model;
    private Weapon[] weapons;

    public StoreView(Image storeSprite, Image[] weaponSprites, Model model){
        this.storeSprite = storeSprite;
        this.weaponSprites = weaponSprites;
        this.model = model;
        weapons = model.getWeapons();
    }

    /**
     * Draws the store between stores
     * @param graphics The current graphics setting
     */
    public synchronized void draw(Graphics2D graphics){
        int timeUntilNextWave = model.getTimeUntilNextWave();
        if(timeUntilNextWave != -1){
            graphics.setColor(Color.RED);
            Font f = graphics.getFont();
            graphics.setFont(new Font(f.getName(), f.getStyle(), f.getSize() - 3));

            graphics.drawImage(storeSprite, 0, 0, null);
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    int index = (i+2)*10 + j;
                    graphics.drawImage(weaponSprites[index], GraphicsUtils.Transform(weaponSprites[index], 170 * i + 165, 90 * j + 75, 0), null);
                    graphics.drawString(weapons[index].getName(), 170 * i + 242, 90 * j + 50);
                    graphics.drawString(weapons[index].getPrice() + "", 170*i + 242, 90*j + 63);
                    graphics.drawString(weapons[index].getDamage() + "", 170*i + 242, 90*j + 76);
                    graphics.drawString("" + (int)(1/weapons[index].getSpray()), 170*i + 242, 90*j + 89);
                    graphics.drawString((int)((1/weapons[index].getRateOfFire())*10000) + "", 170*i + 242, 90*j + 102);
                    graphics.drawString(weapons[index].getAmmo() + "", 170*i + 242, 90*j + 115);
                }
            }
            graphics.setFont(f);
            if(timeUntilNextWave>=10){
                graphics.drawString("" + timeUntilNextWave, 33, 66);
            } else{
                graphics.drawString("" + timeUntilNextWave, 38, 66);
            }
            graphics.setColor(Color.BLACK);
        }
    }
}
