package utilities;

import edu.chalmers.projecttemplate.view.GameView;

/**
 * Camera class specifying viewport kind of :)
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
	
	public static void setX(int x){
		Camera.x = x - GameView.getScreenWidth()/2;
	}
	
	public static void setY(int y){
		Camera.y = y - GameView.getScreenHeight()/2;
	}
	
	
	
	
	
}
