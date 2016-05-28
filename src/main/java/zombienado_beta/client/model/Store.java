package zombienado_beta.client.model;

import java.awt.*;

/**
 * Created by Gustav on 16-05-24.
 * A class to represent a store that functions between rounds in the game
 */
public class Store {
    private Rectangle[][] buyButtons = new Rectangle[3][3]; // Purchase buttons area on screen.
    private boolean wasMousePressed;
    private boolean hasBoughtNewWeapon = false;
    private int boughtWeaponId;

    public Store(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                buyButtons[i][j] = new Rectangle(170 * i + 140, 90 * j + 113, 64, 11);
            }
        }
    }

    /**
     * Sets hasBoughtNewWeapon to true if players has bought a weapon
     * @param cursor The coordinates where the cursor is on the screen
     * @param isMousePressed Wheter the mouse is pressed or not
     */
    public void buyWeapon(Point cursor, boolean isMousePressed){
        if (isMousePressed != wasMousePressed && isMousePressed){
        for(int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buyButtons[i][j].contains(cursor)) {
                    boughtWeaponId = (i + 2) * 10 + j;
                    hasBoughtNewWeapon = true;
                    return;
                }
            }
        }
        }
        wasMousePressed = isMousePressed;
    }

    /**
     * Returns true if the player has bought a new weapon
     * @return True if the player has bought a new weapon
     */
    public boolean hasBoughtNewWeapon(){
        return hasBoughtNewWeapon;
    }

    /**
     * Returns the id of the bought weapon
     * @return The id of the bought weapon
     */
    public int getBoughtWeapon(){
        hasBoughtNewWeapon = false;
        return boughtWeaponId;
    }
}
