package zombienado_v1.server.serverWeapon;

import zombienado_v1.server.serverUnits.ServerBullet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerGun extends ServerWeapon {

    private final static int ID = 00;
    private final static int DAMAGE = 20;
    private final static int PRICE = 0;
    private final static int DISTANCE_TO_MUZZLE = 60; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 20;
    private final static int MAXAMMO = 999999;
    private final static int SHOTS = 1;
    private final static float SPRAY = 0.3f;
    private final static double RATEOFFIRE = 200;

    public ServerGun() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }

    @Override
    public ArrayList<ServerBullet> shoot(int x, int y, float direction, int bulletCounter){
        //Fix with better values
        if(MAXAMMO > 0) {

            super.setlastFired(System.currentTimeMillis());
            Random r = new Random();

            ArrayList<ServerBullet> bullets = new ArrayList<ServerBullet>();
            for (int i = 0; i < SHOTS; i++) {
                bullets.add(new ServerBullet((int) (x + Math.cos(direction) * 48), (int) (y + Math.sin(direction) * 48), bulletCounter, direction - SPRAY / 2 + r.nextFloat() * SPRAY, DAMAGE, BULLETSPEED));
            }
            return bullets;
        } else {
            return new ArrayList<ServerBullet>();
        }

    }
}
