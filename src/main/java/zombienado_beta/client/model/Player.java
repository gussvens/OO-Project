package zombienado_beta.client.model;

import zombienado_beta.client.model.weapon.*;

/**
 * A class that represents a player unit on the client side of the application
 */
public class Player extends Unit{

    private Weapon weapon = new Gun();
    private int balance;
    private int health;
    private boolean hasShot;
    private boolean isDead = false;

    /**
     * Triggers sound effect in view
     */
    public void shoot(){
        hasShot = true;
    }

    /**
     * Returns true if the player has shot
     * @return True if the player has shot
     */
    public boolean hasShot(){
        if (hasShot) {
            hasShot = false;
            return true;
        }
        return false;
    }

    /**
     * Returns a copy of this player unit
     * @return A copy of this player unit
     */
    public Player copy (){
        Player copy = new Player();
        copy.setPosition(getX(), getY());
        copy.setRotation(getRotation());
        copy.setAmmo(getAmmo());
        copy.setBalance(getBalance());
        copy.setHealth(getHealth());
        copy.setWeapon(this.weapon.getId());
        if(getHealth() <= 0) copy.setDead(isDead());
        if (hasShot) {
            copy.shoot();
        }
        return copy;
    }

    /**
     * Sets the health to a new value
     * @param health The new health value
     */
    public void setHealth(int health){
        this.health = health;
    }

    /**
     * Sets the balance to a new value
     * @param balance The new balance value
     */
    public void setBalance(int balance){
        this.balance = balance;
    }

    /**
     * Sets the ammo to a new value
     * @param ammo The new ammo value
     */
    public void setAmmo(int ammo){
        weapon.setAmmo(ammo);
    }

    /**
     * Sets the weapon to a new one
     * @param weaponID id of the new weapon
     */
    public void setWeapon(int weaponID){
        int ammo = weapon.getAmmo();
        if(weapon.getId() != weaponID) {
            switch(weaponID){
                case 0:
                    weapon = new Gun();
                    break;
                case 20:
                    weapon = new Ak47();
                    break;
                case 21:
                    weapon = new TommyGun();
                    break;
                case 22:
                    weapon = new M4();
                    break;
                case 30:
                    weapon = new Shotgun();
                    break;
                case 31:
                    weapon = new Blunderbuss();
                    break;
                case 32:
                    weapon = new AutoShotgun();
                    break;
                case 40:
                    weapon = new Uzi();
                    break;
                case 41:
                    weapon = new DoubleUzi();
                    break;
                case 42:
                    weapon = new TripleUzi();
                    break;
            }
            weapon.setAmmo(ammo);
        }

    }

    /**
     * Sets the if the player is dead or not
     * @param isDead Death status of player
     */
    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

    /**
     * Returns true if player is dead
     * @return True if player is dead
     */
    public boolean isDead(){
        return isDead;
    }

    /**
     * Returns the players weapon
     * @return The players weapon
     */
    public Weapon getWeapon(){return weapon;}

    /**
     * Returns the players health
     * @return The players health
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Returns the players balance
     * @return The players balance
     */
    public int getBalance(){
        return this.balance;
    }

    /**
     * Returns the players weapons ammo
     * @return The players weapons ammo
     */
    public int getAmmo(){
        return weapon.getAmmo();
    }
}
