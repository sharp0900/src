package com.mycompany.a2;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class Asteroid extends MoveableGameObject implements ISelectable{

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

	@Override
	public void setSelected(boolean yesNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		return false;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}
	
	
}
