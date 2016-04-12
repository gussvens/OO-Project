package client.model.weapon;

import java.awt.*;

/**
 * Created by Gustav on 16-04-12.
 */
public class Gun extends Weapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999;
    private final static int DAMAGE = 10;
    private final static int PRICE = 0;
    private final static float SPRAY = 1;
    private final static float RATEOFFIRE = 1;
    private static Image storeImage; // A image showing the gun in the store
    private static Image gameSprite; // The sprite to overlap with playerSprites

    public Gun() {
        super(ID, MAXAMMO, DAMAGE, PRICE, SPRAY, RATEOFFIRE, storeImage, gameSprite);
    }

    @Override
    public void update() {
        //Do stuff repeatedly
    }

    @Override
    public void draw(Graphics2D graphics) {
        //Do stuff
    }
}
