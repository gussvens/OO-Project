package edu.chalmers.projecttemplate.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import utilities.GraphicsUtils;

public class OtherPlayer implements Unit{
	public Image sprite; 
	public Image feet;
	public int x;
	public int y;
	public double rotation;
	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void setRotation(double rot) {
		this.rotation = rot;
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setTexture(Image sprite) {
		this.sprite = sprite;
	}

	public void draw(Graphics2D graphics){
		graphics.drawImage(sprite, GraphicsUtils.Transform(sprite, x, y, rotation), null);
	}
	
}
