package com.mycompany.a2;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class Asteroid extends MoveableGameObject implements ISelectable,IDrawable,ICollider{

	private int size;
	private GameWorldProxy gwp;
	
	public Asteroid(GameWorldProxy gw) {
		super(gw);
		gwp = gw;
		this.size = 60 + new Random().nextInt(240);
		super.setSize(this.size);
	}
	
	public void move() {
		// This will move and animate the object to where it needs to be
		// when the tick button is pressed.
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(90 - this.getHeading()) * this.getSpeed());
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(90 - this.getHeading()) * this.getSpeed());
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
		
		// This will cause the object to show up on the opposite side of the map
		// if the object goes out of the maps bounds.
		if (this.getLocation().getX() + this.size > gwp.getMapWidth() + gwp.getMapX()) {
			this.setLocation(gwp.getMapX(), this.getLocation().getY());
		} else if (this.getLocation().getX() < gwp.getMapX()) {
			this.setLocation(gwp.getMapWidth() + gwp.getMapX() - this.getSize(), this.getLocation().getY());
		}
		if (this.getLocation().getY() + this.size > gwp.getMapHeight() + gwp.getMapY()) {
			this.setLocation(this.getLocation().getX(), gwp.getMapY());
		} else if(this.getLocation().getY() < gwp.getMapY()) {
			this.setLocation(this.getLocation().getX(), gwp.getMapHeight() + gwp.getMapY() - this.getSize());
		}
	}
	
	public String toString() {
		String text = ("Asteroid: " + 
					  " Location: " + this.getLocation() +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  	ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" +
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		return false;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(ColorUtil.argb(100,200,50,100));
		g.fillRoundRect((int)this.getLocation().getX(), 
				  		(int)this.getLocation().getY(), 
				  		this.size, this.size, 120, 360);
		
	}
	
	public boolean collideWith(ICollider gameObject) {
		GameObject obj = (GameObject) gameObject;
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX() + (this.size/2)); // find centers
		int thisCenterY = (int) (this.getLocation().getY() + (this.size/2));
		int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()/2));
		int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()/2));
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = this.size/2;
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
