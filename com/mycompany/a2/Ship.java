package com.mycompany.a2;

public abstract class Ship extends MoveableGameObject {

	private int missileCount;
	
	Ship(GameWorldProxy gw){
		super(gw);
		missileCount = 10;
	}
	
	public int getMissileCount() {
		return this.missileCount;
	}
	
	public void setMissileCount(int x) {
		this.missileCount = x;
	}
}
