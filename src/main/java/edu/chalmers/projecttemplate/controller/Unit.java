package edu.chalmers.projecttemplate.controller;

import java.awt.Graphics;
import java.awt.Image;

public interface Unit {
	public void setTexture(Image sprite);
	public void setPosition(int x, int y);
	public void setRotation(float rot);
	public void setAnimation();
	public void draw(Graphics graphics);
}
