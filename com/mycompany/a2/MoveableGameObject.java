package com.mycompany.a2;

import java.util.Random;

public abstract class MoveableGameObject extends GameObject implements IMoveable{

	public MoveableGameObject(GameWorldProxy gw) {
		super(gw);
	}

	private int speed = new Random().nextInt(10);
	private int heading = new Random().nextInt(359);
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	public void setSpeed(int x) {
		this.speed = x;
	}
	
	public void setHeading(int x) {
		
		if (x == 0) {
			this.heading = 0;
		}
		else{
			this.heading = this.heading + x;
				if (this.heading < 0) {
					this.heading = 359 + x;
					}
				else if(this.heading > 359){
					this.heading = x - 359;
					}
		}
	}
	
	
}
