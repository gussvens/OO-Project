package zombienado_v1.iModel;

/**
 * Created by Gustav on 16-05-05.
 */
public interface IWeapon {

    /**
     * Returns unit ID
     * @return Unit ID
     */
    int getId();

    /**
     * Returns current amount of bullets left
     * @return Current amount of bullets left
     */
    int getNumberOfBulletsLeft();

    /**
     * Returns damage of weapon
     * @return Damage of weapon
     */
    int getDamage();

    /**
     * Returns price of weapon
     * @return Price of weapon
     */
    int getPrice();

    /**
     * Returns spray of weapon
     * @return Spray of weapon
     */
    double getSpray();

    /**
     * Retuns the rate of fire for the weapon
     * @return The rate of fire for the weapon
     */
    double getRateOfFire();

    /**
     * Fires if possible and returns true if possible
     * @return True if possible
     */
    boolean fire();

    /**
     * Checks if its possible to fire
     * @return True if the weapon can fire
     */
    boolean canFire();

    /**
     * Updates the weapon in some way
     */
    void update();
}
