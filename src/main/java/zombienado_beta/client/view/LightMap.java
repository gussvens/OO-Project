package zombienado_beta.client.view;

import zombienado_beta.client.model.Model;
import zombienado_beta.client.model.Player;
import zombienado_beta.client.model.Unit;
import zombienado_beta.utilities.Camera;
import zombienado_beta.utilities.GraphicsUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Erik on 2016-05-19.
 * A class to represent a light map in the game, differentiating the see-trough level across the map
 */

public class LightMap {
    private ArrayList<Point> lights; //Only light tiles
    BufferedImage flashLight;
    BufferedImage staticLight;
    BufferedImage bulletLight;
    BufferedImage playerLumination;
    BufferedImage flickeringLight1;
    BufferedImage flickeringLight2;

    BufferedImage lightMap;
    Graphics2D graphics;

    Model model;

    public LightMap (Model model) {
        this.model = model;
        lights = new ArrayList<Point>();
        createFlashLight();
        createStaticLight(100, 10);
        createBulletLight();
        createPlayerLumination();
        createFlickeringLight1(100, 7);
        createFlickeringLight2(100, 4);

        lightMap = new BufferedImage(GameView.getScreenWidth(), GameView.getScreenHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D)lightMap.createGraphics();

    }

    /**
     * Adds a light at specified position
     * @param light The specified position
     */
    public void addLight(Point light){
        lights.add(light);
    }

    /**
     * Returns all locations for lights
     * @return All locations for lights
     */
    public ArrayList<Point> getLights(){ //only return needed
        return lights;
    }

    /**
     * Creates static light
     * @param radius Radius of light
     * @param luminosity Luminosity of light
     */
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

    /**
     * Creates a flash-ligth
     */
    private void createFlashLight(){
        flashLight = new BufferedImage(180, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)flashLight.getGraphics();

        graphics.setColor(new Color(0, 0, 0, 4));
        for (int i = 0; i < 40; i++){
            graphics.fillOval(0 + i*3, 0 + i * 5, 180 - i * 6, 180 - i * 5);
        }
    }

    /**
     * Creates a bullet light-trail
     */
    private void createBulletLight(){
        bulletLight = new BufferedImage(32, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)bulletLight.getGraphics();

        graphics.setColor(new Color(0, 0, 0, 10));
        for (int i = 0; i < 8; i++){
            graphics.fillOval(16 - i*2, 32 - i*4, i*4, i*8);
        }
    }

    /**
     * Creates player lumination light
     */
    private void createPlayerLumination(){
        playerLumination = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)playerLumination.getGraphics();

        graphics.setColor(new Color(0, 0, 0, 1));
        for (int i = 0; i < 20; i++){
            graphics.fillOval(200 - i*5, 200 - i*5, i*10, i*10);
        }
    }

    /**
     * Creates flickering light 1
     * @param radius Radius of light
     * @param luminosity Luminosity of light
     */
    public void createFlickeringLight1(int radius, int luminosity){
        flickeringLight1 = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) flickeringLight1.getGraphics();

        int step = 8;
        int numberOfSteps = radius/step;
        graphics.setColor(new Color(0, 0, 0, luminosity));
        for (int i = 0; i < numberOfSteps; i++){
            graphics.fillOval(radius - i * step, radius - i * step, i * step * 2, i * step * 2);
        }
    }

    /**
     * Creates flickering light 2
     * @param radius Radius of light
     * @param luminosity Luminosity of light
     */
    public void createFlickeringLight2(int radius, int luminosity){
        flickeringLight2 = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) flickeringLight2.getGraphics();

        int step = 8;
        int numberOfSteps = radius/step;
        graphics.setColor(new Color(0, 0, 0, luminosity));
        for (int i = 0; i < numberOfSteps; i++){
            graphics.fillOval(radius - i * step, radius - i * step, i * step * 2, i * step * 2);
        }
    }

    /**
     * Draws the light map in a separate graphics setting
     */
    private void drawLightMap(){

        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.20f));
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(0, 0, GameView.getScreenWidth(), GameView.getScreenHeight());
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT));

        for (Player player : model.getPlayers()){
            if (player != null && !player.isDead()) {
                graphics.drawImage(flashLight, GraphicsUtils.Transform(flashLight, (int) (player.getX() - Camera.getX() + Math.cos(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), (int) (player.getY() - Camera.getY() + Math.sin(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), player.getRotation()), null);
                graphics.drawImage(playerLumination, GraphicsUtils.Transform(playerLumination, (int) (player.getX() - Camera.getX() + Math.cos(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), (int) (player.getY() - Camera.getY() + Math.sin(player.getRotation()) * (player.getWeapon().getDistanceToMuzzle() - 24)), player.getRotation()), null);
            }

        }
        for (Unit bullet : model.getBullets()){
            graphics.drawImage(bulletLight, GraphicsUtils.Transform(bulletLight, (int)(bullet.getX() - Camera.getX()), (int)(bullet.getY()-Camera.getY()), bullet.getRotation()), null);
        }

        if(System.currentTimeMillis()/150%2 == 0) {
            for (Point light : getLights()) {
                graphics.drawImage(flickeringLight1, GraphicsUtils.Transform(flickeringLight1, (int) (light.getX() - Camera.getX() + 16), (int) (light.getY() - Camera.getY() + 16), 0), null);
            }
        }
        else {
            for (Point light : getLights()) {
                graphics.drawImage(flickeringLight2, GraphicsUtils.Transform(flickeringLight2, (int) (light.getX() - Camera.getX() + 16), (int) (light.getY() - Camera.getY() + 16), 0), null);
            }
        }

       // return lightMap;
    }

    /**
     * Draws the ligth map
     * @param graphics The current graphics setting
     */
    public void draw(Graphics2D graphics){
        drawLightMap();
        graphics.drawImage(lightMap, 0, 0, null);
    }
}
