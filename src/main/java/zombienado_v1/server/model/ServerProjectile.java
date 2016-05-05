package zombienado_v1.server.model;

import zombienado_v1.Interface.IProjectile;
import zombienado_v1.Interface.IUnit;
import zombienado_v1.server.model.serverUnits.ServerZombie;
import zombienado_v1.server.serverWorld.WorldHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class to represent a projectile in the model
 * Created by Gustav on 16-05-05.
 */
public class ServerProjectile implements IProjectile{
    private int x;
    private int y;
    private int damage;
    private int speed;
    private double rotation;

    public ServerProjectile(int x, int y, int damage, int speed, double rotation){
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.speed = speed;
        this.rotation = rotation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRotation() {
        return this.rotation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ArrayList<IUnit> zombies, ArrayList<ArrayList<Boolean>> solidMap) {
        int tileWidth = WorldHandler.getTileWidth();

        this.x += this.speed * Math.cos(this.rotation);
        this.y += this.speed * Math.sin(this.rotation);

        int tileX =x / WorldHandler.getTileWidth();
        int tileY =y / WorldHandler.getTileWidth();

        if(solidMap.get(tileX).get(tileY)) {
            if (new Rectangle(tileX * tileWidth, tileY * tileWidth, tileWidth, tileWidth).contains(this.x, this.y)) {
                this.speed = 0;
                this.x = -1;
                this.y = -1;
            }
        }
    }
}
