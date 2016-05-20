package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Player;
import zombienado_v1.utilities.GraphicsUtils;
import zombienado_v1.utilities.MiscUtilites;

import java.awt.*;

/**
 * Created by Gustav on 16-05-18.
 */
public class HudView {
    private Image hudSprite;
    private Image[] weaponSprites;
    private Image[] numberSprites;
    private Model model;

    public HudView(Image hudSprite, Image[] weaponSprites, Image[] numberSprites, Model model){
        this.hudSprite = hudSprite;
        this.weaponSprites = weaponSprites;
        this.numberSprites = numberSprites;
        this.model = model;
    }

    public synchronized void draw(Graphics2D graphics){
        Player player = model.getPlayer();
        int x = 0;
        int y = GameView.getScreenHeight() - 96;
        int[] ammo = MiscUtilites.getCertanNumberArray(6, player.getAmmo());
        int[] balance = MiscUtilites.getCertanNumberArray(9, player.getBalance());
        int[] health = MiscUtilites.getCertanNumberArray(3, player.getHealth());
        int[] waveNumber = MiscUtilites.getCertanNumberArray(3, model.getWave());
        int weaponId = player.getWeapon().getId();

        graphics.drawImage(hudSprite,x,y,null);

        graphics.drawImage(weaponSprites[weaponId], GraphicsUtils.Transform(weaponSprites[weaponId], x+44, y+48, 0), null);

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
}
