package zombienado_beta.utilities;

/**
 * Camera class specifying what to draw
 * @author Erik
 *
 */
public class Camera {
	private static int x = 0;
	private static int y = 0;
	
	public static int getX(){
		return Camera.x;
	}
	
	public static int getY(){
		return Camera.y;
	}
	
	public static void setX(int x, int screenWidth){
		Camera.x = x - screenWidth/2;
	}
	
	public static void setY(int y, int screenHeight){
		Camera.y = y - screenHeight/2;
	}
	
	
	
	
	
}
