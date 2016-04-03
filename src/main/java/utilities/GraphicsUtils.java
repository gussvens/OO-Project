package utilities;

import java.awt.Image;
import java.awt.geom.AffineTransform;

public class GraphicsUtils {
	public static AffineTransform Transform(Image imageData, int x, int y, float rotation){
		AffineTransform at = new AffineTransform();
		at.translate(x, y);
		at.rotate(rotation);
        at.translate(-imageData.getWidth(null)/2, -imageData.getHeight(null)/2);
		return at;
	}
}
