package zombienado_v1.client.model;

import java.awt.*;

/**
 * Created by Gustav on 16-05-24.
 */
public class Store {
    private Rectangle[][] buyButtons = new Rectangle[3][3];
    private boolean hasBoughtNewWeapon = false;
    private int boughtWeaponId;

    public Store(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                buyButtons[i][j] = new Rectangle(170 * i + 165, 90 * j + 143, 64, 12);
            }
        }
    }

    public int buyWeapon(Point cursor){
        for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(buyButtons[i][j].contains(cursor)){
                        return (2+i)*10 +j;
                    }
                }
        }
        return -1;
    }

    public void setHasBoughtNewWeapon(boolean hasBoughtNewWeapon){
        this.hasBoughtNewWeapon = hasBoughtNewWeapon;
    }

    public boolean hasBoughtNewWeapon(){
        return hasBoughtNewWeapon;
    }

    public int getBoughtWeapon(){
        hasBoughtNewWeapon = false;
        return boughtWeaponId;
    }
}
