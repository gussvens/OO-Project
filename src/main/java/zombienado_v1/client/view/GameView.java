package zombienado_v1.client.view;

import zombienado_v1.client.controller.Controller;
import zombienado_v1.client.model.Model;
import zombienado_v1.utilities.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame{
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
	private LightMap lightMap;
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
		canvas.setDoubleBuffered(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ZOMBIE STORM 0.3 BETA DEVELOPER EDITION");
		this.setResizable(false);
		this.getContentPane().add(canvas);
		this.setUndecorated(true);
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
		Image[] weaponSprites = new Image[100];
		Image hudSprite;
		Image bulletSprite;
		Image weaponSpriteSheet;
		Image zombieSprite;
		SoundEffect[] gunSound = new SoundEffect[99];
		SoundEffect backgroundMusic;
		Animation[] muzzle = new Animation[4];
		// Animation[] dyingZombie = new Animation[10];

		try { //LOAD

			// ----- LOAD PLAYER & ZOMBIE SPRITES -----
			playerSprite[0] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerRocker.png")));
			playerSprite[1] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerPunk.png")));
			playerSprite[2] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerGirl.png")));
			playerSprite[3] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/playerDark.png")));
			zombieSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/zombie.png")));

			// ----- LOAD HUD SPRITES -----
			hudSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/hudTrans.png")));
			for(int i = 0; i<numberSprites.length; i++){
				String str = "src/main/resources/sprites/numbers/" + i + ".png";
				numberSprites[i] = GraphicsUtils.makeTransparent(ImageIO.read(new File(str)));
			}

			// ----- LOAD WEAPON SPRITES -----
			bulletSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/weapons/bullet2.png")));
			weaponSpriteSheet = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/weapons/weaponGrid.png")));
			for(int i = 0; i<weaponSprites.length; i++){
				weaponSprites[i] = GraphicsUtils.getImageFromSheet(i / 10, i - (i / 10)*10, 64, 64, weaponSpriteSheet);
			}

			// ----- LOAD ANIMATIONS -----
			for (int i = 0; i < muzzle.length; i++) {
				muzzle[i] = new Animation(GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/weapons/muzzle.png"))), 8, 1, 60);
			}
			/*for (int i = 0; i < dyingZombie.length; i++) {
				dyingZombie[i] = new Animation(GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/zombieDeath.png"))), 8, 1, 60);
			}*/
			//playerFeetSheet = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/testFeet.png")));

			// ----- LOAD SOUND EFFECTS -----
			gunSound[0] = new SoundEffect(new File("src/main/resources/soundeffects/gunshot.wav"));
			gunSound[20] = new SoundEffect(new File("src/main/resources/soundeffects/ak47.wav"));
			gunSound[21] = new SoundEffect(new File("src/main/resources/soundeffects/tommygun.wav"));
			gunSound[22] = new SoundEffect(new File("src/main/resources/soundeffects/m4.wav"));
			gunSound[30] = new SoundEffect(new File("src/main/resources/soundeffects/shotgun.wav"));
			gunSound[31] = new SoundEffect(new File("src/main/resources/soundeffects/blunderbuss.wav"));
			gunSound[32] = new SoundEffect(new File("src/main/resources/soundeffects/autoshotgun.wav"));
			gunSound[40] = new SoundEffect(new File("src/main/resources/soundeffects/uzi.wav"));
			gunSound[41] = new SoundEffect(new File("src/main/resources/soundeffects/doubleuzi.wav"));
			gunSound[42] = new SoundEffect(new File("src/main/resources/soundeffects/tripleuzi.wav"));
			backgroundMusic = new SoundEffect(new File("src/main/resources/soundeffects/ambientnoise.wav"), true);

			// ----- LOAD MAPS -----
			mapView = new MapView(ImageIO.read(new File("src/main/resources/sprites/tiles/tileGrid.png")));
			MapLoader.Load(mapView, new File("src/main/resources/maps/mapPillars.txt"));


			// ----- LOAD VIEWS -----
			characterView = new CharacterView(model, playerSprite, weaponSprites, muzzle, gunSound);
			zombieView = new ZombieView(model, zombieSprite);
			bulletView = new BulletView(model, bulletSprite);
			hudView = new HudView(hudSprite, weaponSprites, numberSprites, model);
			lightMap = new LightMap(model);

			// ----- START BACKGROUND MUSIC -----
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
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		bulletView.draw(graphics);
		characterView.draw(graphics);
		zombieView.draw(graphics);
		lightMap.draw(graphics);
		hudView.draw(graphics);
		graphics.setColor(Color.black);
		graphics.drawString("FPS: " + Controller.getFramesPerSecond(), 9, 19);
		graphics.setColor(Color.white);
		graphics.drawString("FPS: " + Controller.getFramesPerSecond(), 10, 20);
		graphics.drawString("Zombinado Beta", GameView.getScreenWidth() - 100, 20);

		if(System.getProperty("os.name").substring(0,7).equalsIgnoreCase("windows")){	//paintcomponent does not work on mac, but is optimal for windows
			canvas.paintComponent(canvas.getGraphics());
		} else{
			canvas.repaint();
		}

	}
}
