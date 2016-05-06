package zombienado_v1.interfaces;

import zombienado_v1.server.serverUnits.Bullet;

/**
 * Created by Marcus on 2016-05-06.
 */
public interface iWeapon {

    public Bullet shoot(int x, int y, double direction);
    public int getAmmo();

}
