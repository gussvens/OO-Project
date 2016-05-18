package zombienado_v1.client.view;

import zombienado_v1.client.controller.Controller;
import zombienado_v1.client.model.Model;
import zombienado_v1.client.model.Player;
import zombienado_v1.utilities.Animation;
import zombienado_v1.utilities.GraphicsUtils;
import zombienado_v1.utilities.MapLoader;
import zombienado_v1.utilities.SoundEffect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame{
	//private static int WIDTH = 860;
	//private static int HEIGHT = 480;
	private static int WIDTH = 645;
	private static int HEIGHT = 360;



	private Model model;
	private Canvas canvas;
	private Image imageData;
	private Graphics2D graphics;
	private Graphics renderer;

	private MapView mapView;
	private CharacterView characterView;
	private ZombieView zombieView;
	private BulletView bulletView;
	private HudView hudView;

	private class Canvas extends JPanel {
		@Override
		public synchronized void paintComponent(Graphics g){
			g.drawImage(imageData, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

	public static int getScreenWidth(){
		return WIDTH;
	}

	public static int getScreenHeight(){
		return HEIGHT;
	}

	public GameView(Model model){
		super("fullscreen");
		imageData = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		graphics = (Graphics2D)imageData.getGraphics();
		canvas = new Canvas();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ZOMBIE STORM 0.3 BETA DEVELOPER EDITION");
		this.setResizable(false);
		this.getContentPane().add(canvas);
		getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		pack();
	    setResizable(false);

		this.model = model;
		this.setVisible(true);
		renderer = canvas.getGraphics();
	}

	public synchronized void load(){
		Image[] playerSprite = new Image[4];
		Image[] numberSprites = new Image[10];
		Image hudSprite;
		Image hudWeaponShopSprite;
		Image bulletSprite;
		Image weaponSpriteSheet;
		Image zombieSprite;
		SoundEffect gunSound;
		SoundEffect backgroundMusic;
		Animation muzzle;

		try { //LOAD
			playerSprite[0] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerRocker.png")));
			playerSprite[1] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerPunk.png")));
			playerSprite[2] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerGirl.png")));
			playerSprite[3] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerDark.png")));
			numberSprites[0] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/0.png")));
			numberSprites[1] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/1.png")));
			numberSprites[2] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/2.png")));
			numberSprites[3] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/3.png")));
			numberSprites[4] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/4.png")));
			numberSprites[5] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/5.png")));
			numberSprites[6] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/6.png")));
			numberSprites[7] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/7.png")));
			numberSprites[8] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/8.png")));
			numberSprites[9] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/numbers/9.png")));
			hudSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/hudTrans.png")));
			hudWeaponShopSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/weapons/weaponAutoShotgun.png")));
			weaponSpriteSheet = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/weapons/weaponGrid.png")));
			bulletSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/weapons/bullet2.png")));
			//playerFeetSheet = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/testFeet.png")));
			zombieSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/zombie.png")));
			gunSound = new SoundEffect(new File("src/main/resources/soundeffects/gunshot.wav"));
			backgroundMusic = new SoundEffect(new File("src/main/resources/soundeffects/backgroundmusic2.wav"));
			muzzle = new Animation(GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/weapons/muzzle.png"))), 8, 1, 60);
			mapView = new MapView(ImageIO.read(new File("src/main/resources/sprites/tiles/tileGrid.png")));
			MapLoader.Load(mapView, new File("src/main/resources/maps/mapTest.txt"));
			characterView = new CharacterView(model, playerSprite, weaponSpriteSheet, muzzle, gunSound);
			zombieView = new ZombieView(model, zombieSprite);
			bulletView = new BulletView(model, bulletSprite);
			hudView = new HudView(new Player(), hudSprite, hudWeaponShopSprite, numberSprites, model);
			backgroundMusic.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void render() {
		/**
		 * TODO: renderstuff ?
		 */

		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameView.getScreenWidth(), GameView.getScreenWidth());
		mapView.draw(graphics);
		bulletView.draw(graphics);
		characterView.draw(graphics);
		zombieView.draw(graphics);
		hudView.draw(graphics);
		graphics.drawString("FPS: " + Controller.getFramesPerSecond(), 9, 19);
		graphics.setColor(Color.white);
		graphics.drawString("FPS: " + Controller.getFramesPerSecond(), 10, 20);
		graphics.drawString("Zombinado Beta", GameView.getScreenWidth() - 100, 20);

		canvas.repaint();
		//canvas.paintComponent(renderer);
	}
}
