package com.mycompany.a2;
public class MissileLauncher extends MoveableGameObject {
	
	public MissileLauncher(GameWorldProxy gw) {
		super(gw);
	}

	public void move(int tick) {
		int newSpeed = (this.getSpeed() * tick);
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(Math.toRadians(90 - this.getHeading())) * newSpeed );
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(Math.toRadians(90 - this.getHeading())) * newSpeed);
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
	}
	


}
