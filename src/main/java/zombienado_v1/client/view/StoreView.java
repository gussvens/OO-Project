package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
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

    public StoreView(Image storeSprite, Image[] weaponSprites, Model model){
        this.storeSprite = storeSprite;
        this.weaponSprites = weaponSprites;
        this.model = model;
    }

    public synchronized void draw(Graphics2D graphics){
        if(model.getPlayer().getBalance()==500*model.getWave() + 20* MiscUtilites.factorial(model.getWave()) - 20){
            //int timeUntilNextWave = model.getTimeUntilNextWave();

            graphics.drawImage(storeSprite, 0, 0, null);
            for(int i = 2; i<5; i++){
                for(int j = 0; j<3; j++){
                    int index = i*10 + j;
                    graphics.drawImage(weaponSprites[20], GraphicsUtils.Transform(weaponSprites[index], 128+i*128, 32+j*80, 0), null);
                }
            }
            //graphics.drawString(TimeUntilNextWave, 20, 50);
        }
    }
}
