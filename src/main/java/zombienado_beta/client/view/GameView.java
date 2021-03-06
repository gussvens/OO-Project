package zombienado_beta.client.view;


import zombienado_beta.client.model.Model;
import zombienado_beta.utilities.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame{
	private static int WIDTH = 645;
	private static int HEIGHT = 360;

	private Model model;
	private Canvas canvas;

	private MapView mapView;
	private CharacterView characterView;
	private ZombieView zombieView;
	private BulletView bulletView;
	private HudView hudView;
	private StoreView storeView;
	private ArrayList<File> maps;

	Animation recoilSight;

	private class Canvas extends JPanel {
		Image nextFrame;
		long timeSinceLastFrame;
		float[] lastFrameTimes = new float[60];
		public int fps;
		int iteration = 0;

		public void setNextFrame(Image image){
			this.nextFrame = image;
		}
		@Override
		public synchronized void paintComponent(Graphics g){
			g.drawString("Connecting...", getWidth()/2, getHeight()/2);
			g.drawImage(nextFrame, 0, 0, this.getWidth(), this.getHeight(), null);
			calculateFps();
			g.setColor(Color.gray);
			g.drawString("FPS: "+fps, 1, 11);
			g.drawString("ZOMBINADO BETA", getWidth() - 108, 10);

			timeSinceLastFrame = System.nanoTime();
		}

		public void calculateFps(){
			if (iteration >= 60){
				iteration = 0;
			}

			double fps = System.nanoTime() - timeSinceLastFrame;
			fps /= 1000000000;
			fps = 1 / fps;
			this.lastFrameTimes[iteration] = (float)fps;

			float delta = 0;
			for (float f : lastFrameTimes){
				delta += f;
			}
			this.fps = (int)(delta/60);
			iteration++;
		}

	}

	public static int getScreenWidth(){
		return WIDTH;
	}

	public static int getScreenHeight(){
		return HEIGHT;
	}

	public GameView(Model model){
		super("Zombinado");
		canvas = new Canvas();
		canvas.setDoubleBuffered(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ZOMBIENADO InDev");
		this.setResizable(false);
		this.getContentPane().add(canvas);
		this.setUndecorated(true);
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		pack();
	    setResizable(false);

		this.model = model;
		this.setVisible(true);
	}

	public synchronized void load(){
		Image[] playerSprite = new Image[4];
		Image[] numberSprites = new Image[10];
		Image[] weaponSprites = new Image[100];
		Image hudSprite;
		Image storeSprite;
		Image bulletSprite;
		Image weaponSpriteSheet;
		Image zombieSprite;
		Image healthBar;
		SoundEffect[] gunSound = new SoundEffect[99];
		SoundEffect backgroundMusic;
		Animation[] muzzle = new Animation[4];

		// Animation[] dyingZombie = new Animation[10];

		try { //LOAD

			// ----- LOAD MAPS-----
			maps = new ArrayList<>();
			File folder = new File("src/main/resources/maps");
			for (final File map : folder.listFiles()) {
				maps.add(map);
			}
			// ----- LOAD PLAYER & ZOMBIE SPRITES -----
			playerSprite[0] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/units/playerRocker.png")));
			playerSprite[1] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/units/playerPunk.png")));
			playerSprite[2] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/units/playerGirl.png")));
			playerSprite[3] = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/units/playerDark.png")));
			zombieSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/units/zombie.png")));
			// ----- LOAD HUD & STORE SPRITES -----
			hudSprite = GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/hudTrans.png")));
			storeSprite = ImageIO.read(new File("src/main/resources/sprites/storeSprite.png"));
			healthBar = ImageIO.read(new File("src/main/resources/sprites/units/healthBar.png"));
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
			recoilSight = new Animation(GraphicsUtils.makeTransparent(ImageIO.read(new File("src/main/resources/sprites/sightWithRecoil.png"))), 6, 1, 60);
			recoilSight.setFirstFrameVisable(true);

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
			mapView = new MapView(ImageIO.read(new File("src/main/resources/sprites/tiles/tileGrid.png")), model);

			// ----- LOAD VIEWS -----
			characterView = new CharacterView(model, playerSprite, weaponSprites, healthBar, muzzle, gunSound, recoilSight);
			zombieView = new ZombieView(model, zombieSprite);
			bulletView = new BulletView(model, bulletSprite);
			hudView = new HudView(hudSprite, weaponSprites, numberSprites, model);
			storeView = new StoreView(storeSprite,weaponSprites,model);

			// ----- HIDES CURSOR -----
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
					cursorImg, new Point(0, 0), "blank cursor");
			this.getContentPane().setCursor(blankCursor);
			// ----- START BACKGROUND MUSIC -----
			backgroundMusic.play();

		} catch (IOException e) {
			System.out.println("Missing files");
			e.printStackTrace();
		}
	}

	public synchronized void loadMap(){
		if (!mapView.isLoaded() && !model.getMapName().isEmpty()) {
			for (File map : maps) {
				if (map.getName().equals(model.getMapName() + ".txt")) {
					try {
						mapView.load(map);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			System.out.println("You do not have this map");
		}
	}

	public synchronized void render() {
		if (!mapView.isLoaded() && !model.getMapName().isEmpty()){
			return;
		}
		//Creates new Frame
		Image frame = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = (Graphics2D)frame.getGraphics();

		//Renders all components to the frame
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameView.getScreenWidth(), GameView.getScreenWidth());
		mapView.draw(graphics);
		bulletView.draw(graphics);
		characterView.draw(graphics);
		zombieView.draw(graphics);
		characterView.drawHealthBars(graphics);
		mapView.drawLight(graphics);
		storeView.draw(graphics);
		recoilSight.update();
		Point mousePosition = model.getAim();
		recoilSight.draw((int)mousePosition.getX(), (int)mousePosition.getY(), 0, graphics);
		hudView.draw(graphics);

		//Passes frame to canvas and repaints
		canvas.setNextFrame(frame);
		canvas.repaint();
	}

}
