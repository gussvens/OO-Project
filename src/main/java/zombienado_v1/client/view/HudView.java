package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Player;
import zombienado_v1.utilities.GraphicsUtils;

import java.awt.*;

/**
 * Created by Gustav on 16-05-18.
 */
public class HudView {
    private Image hudSprite;
    private Image weaponShopSprite;
    private Image[] numberSprites;
    private Model model;
    private Player player;

    public HudView(Image hudSprite, Image weaponShopSprite, Image[] numberSprites, Model model){
        this.hudSprite = hudSprite;
        this.weaponShopSprite = weaponShopSprite;
        this.numberSprites = numberSprites;
        this.model = model;
        this.player = this.model.getPlayer();
    }

    public synchronized void draw(Graphics2D graphics){
        int x = 0;
        int y = GameView.getScreenHeight() - 96;
        int[] ammo = getCertanNumberArray(6, player.getWeapon().getAmmo());
        int[] balance = getCertanNumberArray(9, player.getBalance());
        int[] health = getCertanNumberArray(3, player.getHealth());
        //int[] waveNumber = getCertanNumberArray(3, model.getWave()); //This needs wave number from model
        int[] waveNumber = getCertanNumberArray(3, 0);

        graphics.drawImage(hudSprite,x,y,null);

        graphics.drawImage(weaponShopSprite, GraphicsUtils.Transform(weaponShopSprite, x+44, y+48, 0), null);
        //graphics.drawImage(weaponShopSprite, x+16, y+16, null);

        for(int i = 0; i<6; i++){
            graphics.drawImage(numberSprites[ammo[i]], x+111+16*i, y+52, null);
        }

        for(int i = 0; i<9; i++){
            graphics.drawImage(numberSprites[balance[i]], x+289+16*i, y+52, null);
        }

        for(int i = 0; i<3; i++){
            graphics.drawImage(numberSprites[health[i]], x+485+16*i, y+52, null);
        }

        for(int i = 0; i<3; i++){
            graphics.drawImage(numberSprites[waveNumber[i]], x+587+16*i, y+52, null);
        }
    }

    private int[] getCertanNumberArray(int length, int number){
        int[] array = new int[length];
        for(int i = length-1; i>=0; i--){
            if(number < Math.pow(10,i)){
                array[length-1-i] = 0;
            }else{
                array[length-1-i] = number/(int)Math.pow(10,i);
                number -= (number/(int)Math.pow(10,i))*(int)Math.pow(10,i);
            }
        }
        return array;
    }
}
