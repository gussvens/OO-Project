package zombienado_v1.interfaces;

/**
 * Created by Marcus on 2016-05-06.
 */
public interface iWeapon {

    //public iBullet shoot(int x, int y, double direction, int bulletCounter);
    int getId();
    int getAmmo();
    int getDamage();
    int getDistanceToMuzzle();
    void setAmmo(int ammo);

}
