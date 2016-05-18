package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Player;

import java.awt.*;

/**
 * Created by Gustav on 16-05-18.
 */
public class HudView {
    private Image hudSprite;
    private Image weaponShopSprite;
    private Image[] numberSprites;
    private Player player;
    private Model model;

    public HudView(Player player, Image hudSprite, Image weaponShopSprite, Image[] numberSprites, Model model){
        this.player = player;
        this.hudSprite = hudSprite;
        this.weaponShopSprite = weaponShopSprite;
        this.numberSprites = numberSprites;
        this.model = model;
    }

    public synchronized void draw(Graphics2D graphics){
        int x = 289;
        int y = 264;
        int[] ammo = getCertanNumberArray(9, player.getWeapon().getAmmo());
        int[] balance = getCertanNumberArray(9, player.getBalance());
        int[] health = getCertanNumberArray(3, player.getHealth());
        //int[] waveNumber = getCertanNumberArray(3, model.getWave()); //This needs wave number from model
        int[] waveNumber = getCertanNumberArray(3, 0);

        graphics.drawImage(hudSprite,x,y,null);
        graphics.drawImage(weaponShopSprite, x+16, y+16, null);

        for(int i = 0; i<9; i++){
            graphics.drawImage(numberSprites[ammo[i]], x+112, y+12, null);
        }

        for(int i = 0; i<9; i++){
            graphics.drawImage(numberSprites[balance[i]], x+112, y+52, null);
        }

        for(int i = 0; i<3; i++){
            graphics.drawImage(numberSprites[health[i]], x+298, y+12, null);
        }
    }

    private int[] getCertanNumberArray(int length, int number){
        int[] array = new int[length];
        for(int i = length-1; i>=0; i--){
            if(number < Math.pow(10,i)){
                array[length-i] = 0;
            }else{
                array[length-i] = number/(int)Math.pow(10,i);
            }
        }
        return array;
    }
}
