package edu.chalmers.projecttemplate.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public interface Unit {
	public void setTexture(Image sprite);
	public void setPosition(int x, int y);
	public void setRotation(double rot);
	public void setAnimation();
	public void draw(Graphics2D graphics);
}
