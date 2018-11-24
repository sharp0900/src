package com.mycompany.a2;
public class MissileLauncher extends MoveableGameObject {
	
	public MissileLauncher(GameWorldProxy gw) {
		super(gw);
	}

	public void move(int tick) {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(90 - this.getHeading()) * this.getSpeed() * tick);
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(90 - this.getHeading()) * this.getSpeed() * tick);
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
	}
	


}
