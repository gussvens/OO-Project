package zombienado_v1.utilities;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
/** 
 * Utilities & help functions for 2D graphics
 * @author Erik
 */
public class GraphicsUtils {
	/**
	 * Does affine transformations
	 * @param imageData
	 * @param x
	 * @param y
	 * @param rotation
     * @return the transformed image
     */
	public static AffineTransform Transform(Image imageData, int x, int y, double rotation){
		AffineTransform at = new AffineTransform();
		at.translate(x, y);
		at.rotate(rotation + Math.PI/2);
		at.translate(-imageData.getWidth(null)/2, -imageData.getHeight(null)/2);
		return at;
	}

	/**
	 * Replaces white with transparent
	 * @param image
	 * @return An image copy with transparent background
     */
	public static Image makeTransparent(BufferedImage image) {
		 ImageFilter filter = new RGBImageFilter() {
	         int transparentColor = Color.white.getRGB() | 0xFF000000;

	         public final int filterRGB(int x, int y, int rgb) {
	            if ((rgb | 0xFF000000) == transparentColor) {
	               return 0x00FFFFFF & rgb;
	            } else {
	               return rgb;
	            }
	         }
	      };

		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}
}
