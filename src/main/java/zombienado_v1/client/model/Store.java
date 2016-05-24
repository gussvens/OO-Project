package zombienado_v1.client.model;

import java.awt.*;

/**
 * Created by Gustav on 16-05-24.
 */
public class Store {
    private Rectangle[][] buyButtons = new Rectangle[3][3];
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

    public void buyWeapon(Point cursor, boolean isMousePressed){
        if (isMousePressed != wasMousePressed && isMousePressed){
        for(int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buyButtons[i][j].contains(cursor)) {
                    boughtWeaponId = (i + 2) * 10 + j;
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    System.out.println("BOUGHT: "+boughtWeaponId);
                    hasBoughtNewWeapon = true;
                    return;
                }
            }
        }
        }
        wasMousePressed = isMousePressed;
    }

    public boolean hasBoughtNewWeapon(){
        return hasBoughtNewWeapon;
    }

    public int getBoughtWeapon(){
        hasBoughtNewWeapon = false;
        return boughtWeaponId;
    }
}
