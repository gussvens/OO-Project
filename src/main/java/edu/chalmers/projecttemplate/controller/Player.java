package edu.chalmers.projecttemplate.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.List;

import utilities.GraphicsUtils;

public class Player {
	Image sprite;
	int x;
	int y;
	double rotation;
	
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
		 * Mouse events
		 */
		Point mousePosition = MouseInfo.getPointerInfo().getLocation();
		int dX = (int)(mousePosition.getX() - x);
		int dY = (int)(mousePosition.getY() - y);
		rotation = Math.atan2(dY, dX); //Probably not working correct. Have to wait for textures in order to investigate 
		
		/**
		 * Logic
		 */
	}
	
	public void draw(Graphics2D graphics){
         
         graphics.drawImage(sprite, GraphicsUtils.Transform(sprite, x, y, rotation), null);
	}
	
	public String getParsedServerString(){
		return x+";"+y+";"+rotation;
	}
}
