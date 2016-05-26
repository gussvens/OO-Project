package zombienado_v1.utilities;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Erik on 2016-05-06.
 */
public class PlayerInputHandler {

    static float moveX = 0;
    static float moveY = 0;
    static float rotation = 0;

    public static void calculatePlayerRotation(int x, int y, Point cursor){
        int dX = (int)(cursor.getX() - x + Camera.getX());
        int dY = (int)(cursor.getY() - y + Camera.getY());
        rotation = (float)Math.atan2(dY, dX);
    }

    public static void calculatePlayerVelocity(java.util.List<Character> pressedKeys){
        float totalSpeed = 3;
        float speedX = 0;
        float speedY = 0;
        for (char key : pressedKeys){
            switch (key){
                case 'w':
                case 'W':
                    speedY=-1f;
                    break;
                case 'a':
                case 'A':
                    speedX=-1f;
                    break;
                case 's':
                case 'S':
                    speedY=1f;
                    break;
                case 'd':
                case 'D':
                    speedX=1f;
                    break;
            }
        }
        float length = (float)Math.sqrt(speedX*speedX + speedY*speedY);
        moveX = (speedX / length) * totalSpeed;
        moveY = (speedY / length) * totalSpeed;
    }

    public static float getMovementX(){
        return moveX;
    }

    public static float getMovementY(){
        return moveY;
    }

    public static float getRotation(){
        return rotation;
    }
}
