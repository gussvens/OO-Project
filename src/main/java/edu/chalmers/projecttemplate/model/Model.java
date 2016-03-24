package edu.chalmers.projecttemplate.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import edu.chalmers.projecttemplate.controller.Client;
import edu.chalmers.projecttemplate.controller.OtherPlayer;
import edu.chalmers.projecttemplate.controller.Player;
import edu.chalmers.projecttemplate.controller.Unit;

public class Model {
	private static Model instance;
	private int test = 0;
	private int myID;
	private Player player; // TEST
	private Unit otherPlayer; //TEST
	private Client client;

	public Model(){
		instance = this; //NO!!!!
	}
	public void initialize(){
		Image playerSprite = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
		playerSprite.getGraphics().fillRect(0, 0, 20, 20);
		player = new Player(30, 30, playerSprite);
		otherPlayer = new OtherPlayer();
		otherPlayer.setTexture(playerSprite);
		try {
			client = Client.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick(List<Character> pressedKeys){
		test++;
		player.update(pressedKeys);
		client.sendToServer(player.getParsedServerString());
	}

	public void draw(Graphics graphics){
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 500, 500);
		graphics.setColor(Color.white);
		player.draw(graphics);
		otherPlayer.draw(graphics);
		graphics.drawString(test+"", 30, 30);
	}

	public void serverCommand(String s){ //Temporary solution (like everything else)

		String[] arg = s.split(";");		//something here is not working 
		if (arg[0].equals("player")){
			if (arg[1].equals("id")){
				myID = Integer.parseInt(arg[2]);
			}
		} else if (arg[0].equals("players")){

			if (!arg[1].equals("pos")){
				int id = Integer.parseInt(arg[2]);

				System.out.println("My ID: " + myID);
				if (id != myID){

					System.out.println("ID: " + myID);
					int x = Integer.parseInt(arg[3]);
					int y = Integer.parseInt(arg[4]);
					otherPlayer.setPosition(x,y);
				}
			}

		}
	}

	public static Model passInstace(){ //NO!!!!
		return instance;
	}
}
