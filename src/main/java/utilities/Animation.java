package utilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Animation {
	private boolean playing;
	private Image sheet;
	private BufferedImage thisImage;
	private Graphics2D g;
	private int width;
	private int height;
	private int rows;
	private int cols;
	private double frameTime;
	private int startX = 0;
	private int startY = 0;
	private long lastUpdate;

	public Animation(Image spriteSheet, int cols, int rows, int fps){
		this.sheet = spriteSheet;
		this.width = spriteSheet.getWidth(null) / cols;
		this.height = spriteSheet.getHeight(null) / rows;
		this.rows = rows;
		this.cols = cols;
		this.frameTime = 1/(double)fps;

	}

	public void play(){
		if (!playing)
			playing = true;
	}

	public void reset(){
		playing = false;
		startX = 0;
		startY = 0;
	}

	public void update(){
		if (playing){
			System.out.println(System.currentTimeMillis() - lastUpdate);
			if (System.currentTimeMillis() - lastUpdate >= frameTime * 1000){
				startX += width;
				if (startX >= cols * width) {
					startX = 0;
					startY += height;
					if (startY >= rows * height){
						startY = 0;
					}
				}
				lastUpdate = System.currentTimeMillis();
			}
		}
	}

	public void draw(int x, int y, double rotation, Graphics2D graphics){
		thisImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		g = (Graphics2D)thisImage.getGraphics();
		g.drawImage(sheet, 0, 0, width, height, startX, startY, startX + width, startY + height, null);
		graphics.drawImage(thisImage, GraphicsUtils.Transform(thisImage, x, y, rotation), null);
	}
}
