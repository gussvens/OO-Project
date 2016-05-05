package zombienado_v1.Interface;

import zombienado_v1.server.model.serverUnits.ServerZombie;

import java.util.ArrayList;

/**
 * Created by Gustav on 16-05-05.
 */
public interface IProjectile {
    /**
     * Returns coordinate along x axis
     * @return Coordinate along x axis
     */
    int getX();

    /**
     * Returns coordinate along y axis
     * @return Coordinate along Y axis
     */
    int getY();

    /**
     * Returns damage of projectile
     * @return Damage of projectile
     */
    int getDamage();

    /**
     * Returns speed of projectile
     * @return Speed of projectile
     */
    int getSpeed();

    /**
     * Returns rotation as radians anti-clockwise
     * @return Rotation as radians anti-clockwise
     */
    double getRotation();

    /**
     * Updates position of projectile
     */
    void update(ArrayList<IUnit> zombies,ArrayList<ArrayList<Boolean>> solidMap);

}
