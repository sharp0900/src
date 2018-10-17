package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class NonPlayerShip extends Ship{

	private MissileLauncher npLauncher = new MissileLauncher();
	
	NonPlayerShip(){
		npLauncher.setHeading(this.getHeading());
		npLauncher.setSpeed(this.getSpeed());
		npLauncher.setLocation(this.getLocation().getX(), this.getLocation().getY());
	}
	
	public void move() {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(90 - this.getHeading()) * this.getSpeed());
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(90 - this.getHeading()) * this.getSpeed());
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
	}
	
	public void npsShootMissile(){
		if (this.getMissileCount() > 0) {
			this.setMissileCount(this.getMissileCount() - 1);
			System.out.println("Non-Player Ship Missile Fired");
		}
		else {
			System.out.println("Non-Player Ship out of missile.");
		}
	}
	
	public String toString() {
		String text = ("Non-Player Ship: " + 
					  " Location: " + this.getLocation() +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  	ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" +
					  " Speed: " + this.getSpeed() +
					  " Missiles: " + this.getMissileCount() +
					  " Direction: " + this.getHeading()
					  );
		return text;
	}
	
}
