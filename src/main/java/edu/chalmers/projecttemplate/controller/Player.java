package edu.chalmers.projecttemplate.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

public class Player {
	Image sprite;
	int x;
	int y;
	
	public Player(int x, int y, Image sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void update(List<Character> pressedKeys){
		/**
		 * Key events
		 */
		for (char key : pressedKeys){
			switch (key){
			case 'w':
			case 'W':
				y--;
				break;
			case 'a':
			case 'A':
				x--;
				break;
			case 's':
			case 'S':
				y++;
				break;
			case 'd':
			case 'D':
				x++;
				break;
			
			}
		}
		
		/**
		 * Logic
		 */
	}
	
	public void draw(Graphics graphics){
		graphics.drawImage(sprite, x, y, 20, 20, null);
	}
	
	public String getParsedServerString(){
		return x+";"+y;
	}
}
