package com.mycompany.a2;
import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {

	GameWorld gw;
	
	GameWorldProxy(GameWorld gw){
		this.gw = gw;
	}
	
	public boolean getExist(GameObject obj) {
		return gw.collectionExist(obj);
	}
	
	public int getPoints() {
		// TODO Auto-generated method stub
		return gw.getPoints();
	}

	public int getGameTime() {
		// TODO Auto-generated method stub
		return gw.getGameTime();
	}

	public int getShipLife() {
		// TODO Auto-generated method stub
		return gw.getShipLife();
	}


	public boolean getSound() {
		// TODO Auto-generated method stub
		return gw.getSound();
	}

	public int getMissileCount() {
		return gw.getMissileCount();
	}

	public GameCollection getCollection() {
		// TODO Auto-generated method stub
		return gw.getCollection();
	}
	
	public void setCollision(GameObject currObj, GameObject collideObj) {
		gw.setCollision(currObj, collideObj);
	}
	
	public int getMapX() {
		return gw.getMapX();
	}
	
	public int getMapY() {
		return gw.getMapY();
	}
	
	public int getMapWidth() {
		return gw.getMapWidth();
	}
	
	public int getMapHeight() {
		return gw.getMapHeight();		
	}
	
	public void setIsDead(GameObject ship) {
		 gw.setIsDead(ship);
	}
	
}
