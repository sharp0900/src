package com.mycompany.a2;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;


public class Asteroid extends MoveableGameObject{

	private int size;
	public Asteroid() {
		this.size = 6 + new Random().nextInt(24);
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void move() {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(90 - this.getHeading()) * this.getSpeed());
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(90 - this.getHeading()) * this.getSpeed());
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
	}
	
	public String toString() {
		String text = ("Asteroid: " + 
					  " Location: " + this.getLocation() +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  	ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" +
					  " Speed: " + this.getSpeed() +
					  " Direction: " + this.getHeading()
					  );
		return text;
	}
	
	
}
