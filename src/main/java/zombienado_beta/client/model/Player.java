package zombienado_beta.client.model;

import zombienado_beta.client.model.weapon.*;

public class Player extends Unit{
    //TEST
    private Weapon weapon = new Gun();
    private int balance;
    private int health;
    private boolean hasShot;
    private boolean isDead = false;

    /**
     * triggers sound effect in view
     */
    public void shoot(){
        hasShot = true;
    }

    public boolean hasShot(){
        if (hasShot) {
            hasShot = false;
            return true;
        }
        return false;
    }

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

    public void setHealth(int health){
        this.health = health;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void setAmmo(int ammo){
        weapon.setAmmo(ammo);
    }

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

    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

    public boolean isDead(){
        return isDead;
    }

    public Weapon getWeapon(){return weapon;}

    public int getHealth(){
        return this.health;
    }

    public int getBalance(){
        return this.balance;
    }

    public int getAmmo(){
        return weapon.getAmmo();
    }
}
