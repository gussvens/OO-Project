package edu.chalmers.projecttemplate.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import utilities.Camera;
import utilities.GraphicsUtils;

public abstract class Unit {
	private Image sprite;
	private double rotation;
	private int x;
	private int y;
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setRotation(double rot) {
		this.rotation = rot;
	}
	
	public abstract void setAnimation();
	
	public void setTexture(Image sprite) {
		this.sprite = sprite;
	}

	public void draw(Graphics2D graphics){
		graphics.drawImage(sprite, GraphicsUtils.Transform(sprite, x - Camera.getX(), y - Camera.getY(), rotation), null);
	}
	
}
