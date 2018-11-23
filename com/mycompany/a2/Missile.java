package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missile extends MoveableGameObject implements ISelectable,IDrawable{

	private GameWorldProxy gwp;
	private int sizeB = 50;
	private int sizeH = 50;
	private int FuelLevel;
	private String ship = "";
	private Triangle tri;
	private boolean selected;
	
	Missile(GameWorldProxy gw){
		super(gw);
		this.gwp = gw;
		tri = new Triangle(50,50, ColorUtil.rgb(255,0,0));
		this.FuelLevel = 350;
		this.setSpeed(10);
	}
	
	public void move(int tick) {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(Math.toRadians(90 - this.getHeading()))) * this.getSpeed() * tick;
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(Math.toRadians(90 - this.getHeading()))) * this.getSpeed() * tick;
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
	
	public int getFuelLevel() {
		return this.FuelLevel;
	}
	
	public int setFuelLevel() {
		return this.FuelLevel--;
	}
	
	public void setShip(String ship){
		this.ship = ship;
	}

	public String toString() {
		String text = ( this.ship + "Missile: " +
					  " Location: " + this.getLocation() +
					  " Fuel Level: " + this.FuelLevel +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  				ColorUtil.green(getColor()) + ", " + 
					  				ColorUtil.blue(getColor()) + "]" +
					  " Speed: " + this.getSpeed() +
					  " Direction: " + this.getHeading()
					  );
		return text;
	}

	@Override
	public void setSelected(boolean yesNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		boolean temp = false;
		int ptrX = pPtrRelPrnt.getX();
		int ptrY = pPtrRelPrnt.getY();
		int locX = ptrX + (int)this.getLocation().getX();
		int locY = ptrY + (int)this.getLocation().getY();
		return temp;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		System.out.println();
		tri.draw(g, new Point((int) this.getLocation().getX(), 
	                          (int) this.getLocation().getY()));
		g.drawRect((int) this.getLocation().getX() - sizeB/2, 
				   (int) this.getLocation().getY() - sizeH*3 + sizeH/2, 
				   sizeB, sizeH*2);
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
		gwp.setCollision(this, go);
	}
}

