package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixGameObject implements IDrawable{

	private GameWorldProxy gwp;
	private int blinkRate;
	private boolean lightOn;
	private int sizeB = 200;
	private int sizeH = 100;
	
	SpaceStation(GameWorldProxy gw){
		super(gw);
		gwp = gw;
		this.blinkRate = new Random().nextInt(4);
		this.lightOn = true;
	}
	
	public int getBlinkRate() {
		return this.blinkRate;
	}
	
	public void setLight(boolean x) {
		this.lightOn = x;
	}

	public boolean getLight(){
		return this. lightOn;
	}
	
	public String toString() {
		String text = ("Space Station: " + 
					  " Location: " + this.getLocation() +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  	ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" +
					  " Blinking Rate: " + this.getBlinkRate() +
					  " Is light on: " + this.getLight()
					  );
		return text;
	}
	
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.rgb(255, 100, 100));
		g.fillRect((int)this.getLocation().getX(), 
				   (int)this.getLocation().getY(), 
				   sizeB, sizeH, (byte)100);
	}
	
	public boolean collideWith(ICollider gameObject) {
		GameObject obj = (GameObject) gameObject;
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX() + (this.sizeB/2)); // find centers
		int thisCenterY = (int) (this.getLocation().getY() + (this.sizeH/2));
		int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()/2));
		int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()/2));
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = this.sizeB/2;
		int otherRadius = obj.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		+ otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		return result ;
	}

	
	public void handleCollision(ICollider gameObject) {
		GameObject go = (GameObject) gameObject;
		
		if (gwp.getExist(go)) {
			if(go instanceof PlayerShip) {
				gwp.setIsDead(go);
			} 
			else {
				gwp.getCollection().remove(go);
			}
		} 
		
		if (gwp.getExist(this)) {
			gwp.getCollection().remove(this);
		}
	}
	
}
