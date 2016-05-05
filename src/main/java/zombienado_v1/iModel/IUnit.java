package zombienado_v1.iModel;

import java.util.ArrayList;

/**
 * Created by Gustav on 16-05-05.
 */
public interface IUnit {
    /**
     * Returns current position along x axis
     * @return Current position along x axis
     */
    int getX();

    /**
     * Returns current position along y axis
     * @return Current position along y axis
     */
    int getY();

    /**
     * Returns unit ID
     * @return Unit ID
     */
    int getId();

    /**
     * Returns radius of unit
     * @return Radius of unit
     */
    float getRadius();

    /**
     * Returns current rotation in radiants anti-clockwise
     * @return Current rotation in radiants anti-clockwise
     */
    double getRotation();

    /**
     * Updates position and rotation of unit.
     * @param x Movement along x axis
     * @param y Movement along y axis
     * @param rotation Current rotation in radians anti-clockwise
     * @param solidMap A 2d list representing solid tiles as true
     */
    void update(double x, double y, double rotation, ArrayList<ArrayList<Boolean>> solidMap);
}
