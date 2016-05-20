package zombienado_v1.client.view;

import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Player;
import zombienado_v1.client.model.Unit;
import zombienado_v1.utilities.Camera;
import zombienado_v1.utilities.GraphicsUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Created by Erik on 2016-05-19.
 */


/**
 *
 *  THIS CLASS NEEDS MASSIVE IMPROVEMENTS
 *    -- DROPS PERFORMANCE TOO MUCH --
 *
 */
public class LightMap {
    BufferedImage flashLight;
    BufferedImage staticLight;
    BufferedImage bulletLight;
    BufferedImage playerLumination;

    BufferedImage lightMap;
    Graphics2D graphics;

    Model model;

    public LightMap (Model model) {
        this.model = model;
        createFlashLight();
        createStaticLight(100, 10);
        createBulletLight();
        createMuzzleLight();

        lightMap = new BufferedImage(GameView.getScreenWidth(), GameView.getScreenHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D)lightMap.createGraphics();

    }

    public void createStaticLight(int radius, int luminosity){
        staticLight = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)staticLight.getGraphics();

        int step = 2;
        int numberOfSteps = radius/step;
        graphics.setColor(new Color(0, 0, 0, luminosity));
        for (int i = 0; i < numberOfSteps; i++){
            graphics.fillOval(radius - i * step, radius - i * step, i * step * 2, i * step * 2);
        }
    }

    private void createFlashLight(){
        flashLight = new BufferedImage(180, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)flashLight.getGraphics();

        graphics.setColor(new Color(0, 0, 0, 4));
        for (int i = 0; i < 40; i++){
            graphics.fillOval(0 + i*3, 0 + i * 5, 180 - i * 6, 180 - i * 5);
        }
    }

    private void createBulletLight(){
        bulletLight = new BufferedImage(32, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)bulletLight.getGraphics();

        graphics.setColor(new Color(0, 0, 0, 10));
        for (int i = 0; i < 8; i++){
            graphics.fillOval(16 - i*2, 32 - i*4, i*4, i*8);
        }
    }

    private void createMuzzleLight(){
        playerLumination = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)playerLumination.getGraphics();

        graphics.setColor(new Color(0, 0, 0, 1));
        for (int i = 0; i < 20; i++){
            graphics.fillOval(200 - i*5, 200 - i*5, i*10, i*10);
        }
    }

    private Image getLightMap(){

        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.20f));
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(0, 0, GameView.getScreenWidth(), GameView.getScreenHeight());
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT));

        for (Player player : model.getPlayers()){
            graphics.drawImage(flashLight, GraphicsUtils.Transform(flashLight, (int)(player.getX() - Camera.getX() + Math.cos(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), (int)(player.getY()-Camera.getY()+ Math.sin(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), player.getRotation()), null);
            graphics.drawImage(playerLumination, GraphicsUtils.Transform(playerLumination, (int)(player.getX() - Camera.getX() + Math.cos(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), (int)(player.getY()-Camera.getY()+ Math.sin(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), player.getRotation()), null);


        }
        for (Unit bullet : model.getBullets()){
            graphics.drawImage(bulletLight, GraphicsUtils.Transform(bulletLight, (int)(bullet.getX() - Camera.getX()), (int)(bullet.getY()-Camera.getY()), bullet.getRotation()), null);
        }

        return lightMap;
    }

    public void draw(Graphics2D graphics){
        graphics.drawImage(getLightMap(), 0, 0, null);
    }
}
