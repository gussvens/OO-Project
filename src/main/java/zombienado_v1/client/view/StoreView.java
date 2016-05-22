package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.weapon.Weapon;
import zombienado_v1.utilities.GraphicsUtils;
import zombienado_v1.utilities.MiscUtilites;

import java.awt.*;

/**
 * Created by Gustav on 16-05-21.
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

    public synchronized void draw(Graphics2D graphics){
        int timeUntilNextWave = model.getTimeUntilNextWave();
        System.out.println("time:" + timeUntilNextWave);
        if(timeUntilNextWave != -1){
            graphics.setColor(Color.RED);
            Font f = graphics.getFont();
            graphics.setFont(new Font(f.getName(),f.getStyle(),f.getSize()-3));

            graphics.drawImage(storeSprite, 0, 0, null);
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    int index = (i+2)*10 + j;
                    graphics.drawImage(weaponSprites[index], GraphicsUtils.Transform(weaponSprites[index], 170 * i + 165, 90 * j + 75, 0), null);
                    graphics.drawString(weapons[index].getName(), 170 * i + 242, 90 * j + 50);
                    graphics.drawString(weapons[index].getPrice() + "", 170*i + 242, 90*j + 63);
                    graphics.drawString(weapons[index].getDamage() + "", 170*i + 242, 90*j + 76);
                    //graphics.drawString(1/weapons[index].getSpray() + "", 170*i + 242, 90*j + 89);
                    //graphics.drawString(weapons[index].getRateOfFire() + "", 170*i + 242, 90*j + 102);
                    graphics.drawString(weapons[index].getAmmo() + "", 170*i + 242, 90*j + 115);
                }
            }
            graphics.setFont(f);
            graphics.drawString("" + timeUntilNextWave, 38, 66);
            graphics.setColor(Color.BLACK);
        }
    }
}
