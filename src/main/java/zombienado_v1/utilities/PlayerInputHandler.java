package zombienado_v1.utilities;

import java.awt.*;
import java.util.*;

/**
 * Created by Erik on 2016-05-06.
 */
public class PlayerInputHandler {

    public static double getPlayerRotation(int x, int y, Point cursor){
        int dX = (int)(cursor.getX() - x + Camera.getX());
        int dY = (int)(cursor.getY() - y + Camera.getY());
        return Math.atan2(dY, dX);
    }

    public static Point getPlayerVelocity(java.util.List<Character> pressedKeys){
        int totalSpeed = 2;
        int speedX = 0;
        int speedY = 0;
        for (char key : pressedKeys){
            switch (key){
                case 'w':
                case 'W':
                    speedY=-1;
                    break;
                case 'a':
                case 'A':
                    speedX=-1;
                    break;
                case 's':
                case 'S':
                    speedY=1;
                    break;
                case 'd':
                case 'D':
                    speedX=1;
                    break;
            }
        }
        return new Point(speedX*totalSpeed, speedY*totalSpeed);
    }
}
