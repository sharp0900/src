package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonPlayerShip extends Ship implements IDrawable{

	private GameWorldProxy gwp;
	private MissileLauncher npLauncher;
	private Triangle tri;
	private int sizeH = 100;
	private int sizeB = 100;
	
	
	NonPlayerShip(GameWorldProxy gw){
		super(gw);
		this.gwp = gw;
		tri = new Triangle(sizeB, sizeH, ColorUtil.rgb(0,0,0));
		npLauncher = new MissileLauncher(gw);
		npLauncher.setHeading(this.getHeading());
		npLauncher.setSpeed(this.getSpeed());
		npLauncher.setLocation(this.getLocation().getX(), this.getLocation().getY());
	}
	
	public void move(int tick) {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(90 - this.getHeading()) * this.getSpeed() * tick);
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(90 - this.getHeading()) * this.getSpeed() * tick);
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
		
		// This will cause the object to show up on the opposite side of the map
		// if the object goes out of the maps bounds.
		if (this.getLocation().getX() + this.sizeB/2 > gwp.getMapWidth() + gwp.getMapX()) {
			this.setLocation(gwp.getMapX(), this.getLocation().getY());
		} else if (this.getLocation().getX() < gwp.getMapX()) {
			this.setLocation(gwp.getMapWidth() + gwp.getMapX() - this.sizeB/2, this.getLocation().getY());
		}
		if (this.getLocation().getY() + this.sizeH/2 > gwp.getMapHeight() + gwp.getMapY()) {
			this.setLocation(this.getLocation().getX(), gwp.getMapY());
		} else if(this.getLocation().getY() < gwp.getMapY()) {
			this.setLocation(this.getLocation().getX(), gwp.getMapHeight() + gwp.getMapY() - this.sizeH/2);
		}
	}
	
	public void npsShootMissile(){
		if (this.getMissileCount() > 0) {
			this.setMissileCount(this.getMissileCount() - 1);
			System.out.println("Non-Player Ship Missile Fired");
		}
		else {
			System.out.println("Non-Player Ship out of missile.");
		}
	}
	
	public String toString() {
		String text = ("Non-Player Ship: " + 
					  " Location: " + this.getLocation() +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  	ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" +
					  " Speed: " + this.getSpeed() +
					  " Missiles: " + this.getMissileCount() +
					  " Direction: " + this.getHeading()
					  );
		return text;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		tri.draw(g, new Point((int) this.getLocation().getX(), 
	              			  (int) this.getLocation().getY()));
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
