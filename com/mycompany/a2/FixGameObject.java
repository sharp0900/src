package com.mycompany.a2;
import java.util.Random;

public class FixGameObject extends GameObject{

	private static int id;
	
	public FixGameObject(GameWorldProxy gw) {
		super(gw);
		this.id = new Random().nextInt(255);
	}
	
	public int getID() {
		return this.id;
	}
}
