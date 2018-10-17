package com.mycompany.a2;
import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {

	GameWorld gw;
	
	GameWorldProxy(GameWorld gw){
		this.gw = gw;
	}
	
	public int getPoints() {
		// TODO Auto-generated method stub
		return gw.getPoints();
	}

	@Override
	public int getGameTime() {
		// TODO Auto-generated method stub
		return gw.getGameTime();
	}

	@Override
	public int getShipLife() {
		// TODO Auto-generated method stub
		return gw.getShipLife();
	}


	@Override
	public boolean getSound() {
		// TODO Auto-generated method stub
		return gw.getSound();
	}

	@Override
	public int getMissileCount() {
		return gw.getMissileCount();
	}

	@Override
	public GameCollection getCollection() {
		// TODO Auto-generated method stub
		return gw.getCollection();
	}

	
		


}
