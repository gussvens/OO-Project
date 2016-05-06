package zombienado_v1.interfaces;

import zombienado_v1.server.serverUnits.Bullet;

/**
 * Created by Marcus on 2016-05-06.
 */
public interface iWeapon {

    public Bullet shoot();
    public int getAmmo();

}
