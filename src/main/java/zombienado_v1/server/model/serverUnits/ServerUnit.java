package zombienado_v1.server.model.serverUnits;

import zombienado_v1.iModel.IUnit;

/**
 * A class representing a unit in the model
 * Created by Marcus on 2016-04-11.
 */
public abstract class ServerUnit implements IUnit{
    private int x;
    private int y;
    private double r;
    private int id;
    private float radius;

    public ServerUnit(int x, int y, double r, int id, float radius){
        this.x = x;
        this.y = y;
        this.r = r;
        this.id = id;
        this.radius = radius;
    }
    /**
     * {@inheritDoc}
     */
    public int getX(){
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public int getY(){
        return y;
    }

    /**
     * {@inheritDoc}
     */
    public int getId(){
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public float getRadius(){
        return radius;
    }

    /**
     * {@inheritDoc}
     */
    public double getRotation(){
        return r;
    }
}
