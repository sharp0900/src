package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerMissileLauncher extends MissileLauncher implements ISteerable,IDrawable{
	

	public PlayerMissileLauncher(GameWorldProxy gw){
		super(gw);
	}


	public void move(int tick) {
//		int newSpeed = (this.getSpeed() * tick);
//		double oldLocationX = this.getLocation().getX();
//		double deltaX = (Math.cos(Math.toRadians(90 - this.getHeading())) * newSpeed );
//		double oldLocationY = this.getLocation().getY();
//		double deltaY = (Math.sin(Math.toRadians(90 - this.getHeading())) * newSpeed);
//		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
	}
	
	
	public void steerRight() {
		this.setHeading(this.getHeading() - 10);
	}
	
	public void steerLeft() {
		this.setHeading(this.getHeading() + 10);
	}
	
	public void speedUp() {
		if(this.getSpeed() < 10 ) {
			this.setSpeed(getSpeed() + 1);
		}
	}
	
	public void speedDown() {
		if(this.getSpeed() > 0) {
			this.setSpeed(getSpeed() - 1);
		}
	}
}
