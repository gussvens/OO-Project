package zombienado_beta.interfaces;

/**
 * Created by Marcus on 2016-05-06.
 * An interface for a weapon class in a model
 */
public interface iWeapon {

    int getId();
    int getAmmo();
    int getDamage();
    int getDistanceToMuzzle();
    void setAmmo(int ammo);

}
