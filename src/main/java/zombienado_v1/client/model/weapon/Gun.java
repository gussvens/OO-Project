package zombienado_v1.client.model.weapon;

/**
 * Created by Gustav on 16-04-12.
 */
public class Gun extends Weapon {

    private final static int ID = 00;
    private final static int MAXAMMO = 999;
    private final static int PRICE = 0;

    public Gun() {
        super(ID, MAXAMMO, PRICE);
    }
}
