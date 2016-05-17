package zombienado_v1.server.serverUnits;

/**
 * Created by Marcus on 2016-05-06.
 */
public class Bullet implements ServerUnit {

    private int x;
    private int y;
    private int ID;
    private double direction;
    private int speed;
    private int damage;

    public Bullet(int x, int y, int ID, double direction, int damage, int bulletSpeed){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.direction = direction;
        this.speed = bulletSpeed;
        this.damage = damage;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getID(){
        return ID;
    }

    public int getDamage(){
        return damage;
    }

    public void update(){

        //This wont work but shows how its supposed to be done
        double temp = Math.toDegrees(direction);

        x = x + (int)(speed*Math.cos(temp));
        y = y + (int)(speed*Math.sin(temp));
    }


}
