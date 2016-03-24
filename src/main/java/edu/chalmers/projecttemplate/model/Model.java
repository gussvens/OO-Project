package edu.chalmers.projecttemplate.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Model {
	private int test = 0;
	public void initialize(){
		
	}
	
	public void tick(){
		test++;
	}
	
	public void draw(Graphics graphics){
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 500, 500);
		graphics.setColor(Color.white);
		
		graphics.drawString(test+"", 30, 30);
		System.out.println("model - draw");
	}
}
