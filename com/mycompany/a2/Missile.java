package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Missile extends MoveableGameObject{

	private int FuelLevel;
	private String ship = "";
	
	Missile(){
		this.FuelLevel = 10;
		this.setSpeed(1);
	}
	
	public void move() {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(90 - this.getHeading()) * this.getSpeed());
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(90 - this.getHeading()) * this.getSpeed());
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
	}
	
	public int getFuelLevel() {
		return this.FuelLevel;
	}
	
	public int setFuelLevel() {
		return this.FuelLevel--;
	}
	
	public void setShip(String ship){
		this.ship = ship;
	}

	public String toString() {
		String text = ( this.ship + "Missile: " +
					  " Location: " + this.getLocation() +
					  " Fuel Level: " + this.FuelLevel +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  				ColorUtil.green(getColor()) + ", " + 
					  				ColorUtil.blue(getColor()) + "]" +
					  " Speed: " + this.getSpeed() +
					  " Direction: " + this.getHeading()
					  );
		return text;
	}
}
