package com.mycompany.a2;

public class PlayerMissileLauncher extends MissileLauncher implements ISteerable{
	

	public PlayerMissileLauncher(GameWorldProxy gw) {
		super(gw);
	}

	public void move(int tick) {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(Math.toRadians(90 - this.getHeading()))) * this.getSpeed() * tick;
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(Math.toRadians(90 - this.getHeading()))) * this.getSpeed() * tick;
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
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
