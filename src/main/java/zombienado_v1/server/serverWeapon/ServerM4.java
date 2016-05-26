package zombienado_v1.server.serverWeapon;

import zombienado_v1.server.serverUnits.ServerBullet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marcus on 2016-05-06.
 */
public class ServerM4 extends ServerWeapon {

    private final static int ID = 22;
    private final static int DAMAGE = 85;
    private final static int PRICE = 1200;
    private final static int DISTANCE_TO_MUZZLE = 79; //distance from player center to muzzle in pixels
    private final static int BULLETSPEED = 25;
    private final static int MAXAMMO = 560;
    private final static int SHOTS = 3;
    private final static float SPRAY = 0.1f;
    private final static double RATEOFFIRE = 400;



    @Override
    public ArrayList<ServerBullet> shoot(int x, int y, float direction, int bulletCounter){
        //Fix with better values
        if(super.getAmmo() > 0) {

            super.setlastFired(System.currentTimeMillis());
            Random r = new Random();

            ArrayList<ServerBullet> bullets = new ArrayList<ServerBullet>();
            for (int i = 0; i < SHOTS; i++) {
                bullets.add(new ServerBullet((int) (x + Math.cos(direction) * 48), (int) (y + Math.sin(direction) * 48), bulletCounter, direction - SPRAY / 2 + r.nextFloat() * SPRAY, DAMAGE, (BULLETSPEED-i*7)));
            }

            //ammo--;
            super.setAmmo(super.getAmmo()-3);

            return bullets;
        } else {
            return new ArrayList<ServerBullet>();
        }

    }

    public ServerM4() {
        super(ID,DAMAGE,PRICE,DISTANCE_TO_MUZZLE,BULLETSPEED,MAXAMMO,SHOTS,SPRAY,RATEOFFIRE);
    }


}
