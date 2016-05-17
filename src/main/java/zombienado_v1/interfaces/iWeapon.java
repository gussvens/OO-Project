package zombienado_v1.interfaces;

import zombienado_v1.server.serverUnits.ServerBullet;

/**
 * Created by Marcus on 2016-05-06.
 */
public interface iWeapon {

    public ServerBullet shoot(int x, int y, double direction, int ID);
    public int getAmmo();

}
