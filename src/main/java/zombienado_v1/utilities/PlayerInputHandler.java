package zombienado_v1.utilities;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Erik on 2016-05-06.
 */
public class PlayerInputHandler {
    public static float getPlayerRotation(int x, int y, Point cursor){
        int dX = (int)(cursor.getX() - x + Camera.getX());
        int dY = (int)(cursor.getY() - y + Camera.getY());
        return (float)Math.atan2(dY, dX);
    }

    public static Vector getPlayerVelocity(java.util.List<Character> pressedKeys){
        float totalSpeed = 3;
        Vector v = new Vector();
        for (char key : pressedKeys){
            switch (key){
                case 'w':
                case 'W':
                    v.setY(-1f);
                    break;
                case 'a':
                case 'A':
                    v.setX(-1f);
                    break;
                case 's':
                case 'S':
                    v.setY(1f);
                    break;
                case 'd':
                case 'D':
                    v.setX(1f);
                    break;
            }
        }
        v.normalize(totalSpeed);
        return v;
    }

}
