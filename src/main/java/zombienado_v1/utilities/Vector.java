package zombienado_v1.utilities;

/**
 * Created by Erik on 2016-05-26.
 */
public class Vector {
    private float x;
    private float y;

    public Vector(){
        this(0, 0);
    }

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void normalize(float length){
        if (x == 0 && y == 0) return;
        float hypot = (float)Math.sqrt(x*x + y*y);
        x = (x/hypot) * length;
        y = (y/hypot) * length;
    }

}
