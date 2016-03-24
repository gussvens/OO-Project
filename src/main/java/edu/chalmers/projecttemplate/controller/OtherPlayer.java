package edu.chalmers.projecttemplate.controller;

import java.awt.Graphics;
import java.awt.Image;

public class OtherPlayer implements Unit{
	public Image sprite; 
	public Image feet;
	public int x;
	public int y;
	public float rotation;
	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void setRotation(float rot) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setAnimation() {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics graphics){
		graphics.drawImage(sprite, x, y, 20, 20, null);
	}
	@Override
	public void setTexture(Image sprite) {
		this.sprite = sprite;
	}
	
}
