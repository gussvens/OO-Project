package edu.chalmers.projecttemplate.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import edu.chalmers.projecttemplate.controller.Client;
import edu.chalmers.projecttemplate.controller.OtherPlayer;
import edu.chalmers.projecttemplate.controller.Player;
import edu.chalmers.projecttemplate.controller.Unit;
import edu.chalmers.projecttemplate.view.GameView;

public class Model {
	private int test = 0;
	private int myID;
	private Player player;
	private ArrayList<Unit> otherPlayers;
	private Image playerSprite; //TEST

	public void initialize(){
		playerSprite = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
		playerSprite.getGraphics().fillRect(0, 0, 20, 20);
		
		player = new Player(30, 30, playerSprite);
		otherPlayers = new ArrayList<Unit>();
		for (int i = 0; i < 4; i++){ // Test. Creates 4 players in order to match ID to index.
			otherPlayers.add(null);
		}
	}

	public void tick(List<Character> pressedKeys){
		test++;
		player.update(pressedKeys);
	}

	public void draw(Graphics graphics){
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameView.getScreenWidth(), GameView.getScreenWidth());
		graphics.setColor(Color.white);
		player.draw(graphics);
		for (Unit op : otherPlayers) {
			if (op != null)
				op.draw(graphics);
		}
		graphics.drawString(test+"", 30, 30);
	}

	public void serverCommand(String s){ //Temporary solution (like everything else)

		String[] arg = s.split(";");		
		if (arg[0].equals("player")){
			if (arg[1].equals("id")){
				myID = Integer.parseInt(arg[2]);
			}
		} else if (arg[0].equals("players")){

			if (arg[1].equals("pos")){
				int id = Integer.parseInt(arg[2]);

				System.out.println("My ID: " + myID);
				if (id != myID){
					if (otherPlayers.get(id) == null){
						otherPlayers.set(id, new OtherPlayer());
						otherPlayers.get(id).setTexture(playerSprite);
					}
					
					int x = Integer.parseInt(arg[3]);
					int y = Integer.parseInt(arg[4]);
					otherPlayers.get(id).setPosition(x,y);
				}
			}

		}
	}
}
