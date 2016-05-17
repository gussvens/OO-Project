package zombienado_v1.server.serverUnits;

import zombienado_v1.interfaces.iWeapon;
import zombienado_v1.server.serverWeapon.ServerGun;
import zombienado_v1.server.serverWorld.WorldHandler;
import zombienado_v1.utilities.Physics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcus on 2016-04-05.
 */
public class ServerPlayer implements ServerUnit{
    private static final int RADIUS = 16;

    private int x;
    private int y;
    private int health;
    private double r;
    private int id;
    private iWeapon weapon;
    private long timeWhenDamaged;

    public ServerPlayer(int x, int y, double r, int id){
        this.x = x;
        this.y = y;
        this.r = r;
        this.id = id;

        //TODO: move weapons to server
        this.weapon = new ServerGun();
        this.health = 100;
        this.timeWhenDamaged = System.nanoTime();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getID(){
        return id;
    }

    public double getRotation(){
        return r;
    }

    public int getHealth(){
        return this.health;
    }

    public void takeDamage(int damage){
        long timeDiff = System.nanoTime() - timeWhenDamaged;
        if(timeDiff>1000000000){
            this.health -= damage;
            this.timeWhenDamaged = System.nanoTime();
        }
    }

    public void update(int x, int y, double r, ArrayList<ServerZombie> zombies, ArrayList<Point> walls){
        int xOld = this.x;
        int yOld = this.y;

        this.x += x;
        this.y += y;
        this.r += r;

        checkWallsCollisions(xOld,yOld,walls);
        checkDamageTaking(zombies);
    }

    public void checkWallsCollisions(int xOld, int yOld, ArrayList<Point> walls){
        int tileWidth = WorldHandler.getTileWidth();

        int tileX =(this.x/tileWidth) -1;
        int tileY =(this.y/tileWidth) -1;

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                int a = (tileX + i)*tileWidth;
                int b = (tileY + j)*tileWidth;
                if(a>=0 && b>=0){
                    if(walls.contains(new Point(a,b))){
                        checkSingleWallCollision(tileWidth,xOld,yOld,a,b);
                    }
                }
            }
        }
    }

    public void checkSingleWallCollision(int tileWidth, int xOld, int yOld, int x, int y){
        if(Physics.collidesWithWall(this.x,yOld,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.x = xOld;
        }
        if(Physics.collidesWithWall(xOld,this.y,RADIUS,new Rectangle(x,y,tileWidth,tileWidth))){
            this.y = yOld;
        }
    }

    private void checkDamageTaking(ArrayList<ServerZombie> zombies){
        for(ServerZombie zombie: zombies){
            double dX = zombie.getX()-this.x;
            double dY = zombie.getY()-this.y;
            double actualDistance = Math.sqrt( (dX)*(dX) + (dY)*(dY) );
            double allowedDistance = RADIUS + ServerZombie.getRadius();
            double overlap = allowedDistance - actualDistance;

            if(overlap>0) this.takeDamage(1);
        }
    }

    public ServerBullet shoot(int bulletCounter){
        return weapon.shoot(x,y,r,bulletCounter);
    }

    public boolean canShoot(){
        return weapon.canShoot();
    }

}
