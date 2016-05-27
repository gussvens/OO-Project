package zombienado_v1.utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * A simple animation
 * @author Erik
 */
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
	private boolean looping = false;
	private boolean firstFrameVisable;

	/**
	 * @param spriteSheet the animation to play
	 * @param cols how many columns the animation have
	 * @param rows how many rows the animation have
     * @param fps the desiered frame rate
     */
	public Animation(Image spriteSheet, int cols, int rows, int fps){
		this.sheet = spriteSheet;
		this.width = spriteSheet.getWidth(null) / cols;
		this.height = spriteSheet.getHeight(null) / rows;
		this.rows = rows;
		this.cols = cols;
		this.frameTime = 1/(double)fps;

	}

	/**
	 * If the first frame should be visible when the animation is not running
	 * @param visable
     */
	public void setFirstFrameVisable(boolean visable){
		firstFrameVisable = visable;
	}

	/**
	 * If the animation should loop
	 * @param loop
     */
	public void loop(boolean loop){
		looping = loop;
	}

	/**
	 * @return if the animation is running
     */
	public boolean isPlaying(){
		return playing;
	}

	/**
	 * starts the animation
	 */
	public void play(){
		if (!playing)
			playing = true;
	}

	/**
	 * resets the animation by stopping it and resetting the pointers
	 */
	public void reset(){
		playing = false;
		startX = 0;
		startY = 0;
	}

	/**
	 * updates the animation
	 */
	public void update(){
		if (playing){
			if (System.currentTimeMillis() - lastUpdate >= frameTime * 1000){
				startX += width;
				if (startX >= cols * width) {
					startX = 0;
					startY += height;
					if (startY >= rows * height){
						startY = 0;
						if(!looping){
							reset();
						}
					}
				}
				lastUpdate = System.currentTimeMillis();
			}
		}
	}

	/**
	 * draws the animation
	 * @param x the x-coordinate for where to draw the animation
	 * @param y the y-coordinate for where to draw the animation
	 * @param rotation the rotation for which to translate the animation
     * @param graphics the graphics object
     */
	public void draw(int x, int y, double rotation, Graphics2D graphics){
		if (playing || firstFrameVisable) {
			thisImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			g = (Graphics2D) thisImage.getGraphics();
			g.drawImage(sheet, 0, 0, width, height, startX, startY, startX + width, startY + height, null);
			graphics.drawImage(thisImage, GraphicsUtils.Transform(thisImage, x, y, rotation), null);
		}
	}
}
