package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerShip extends Ship implements ISteerable,IDrawable{

	private GameWorldProxy gwp;
	private PlayerMissileLauncher shipLauncher;
	private Triangle tri;
	private int sizeH = 200;
	private int sizeB = 200;
	
	PlayerShip(GameWorldProxy gw){
		super(gw);
		this.gwp = gw;
		this.setHeading(0);
		this.setSpeed(0);
		shipLauncher = new PlayerMissileLauncher(gw);
		this.setLocation(gw.getMapX()+ gw.getMapWidth()/2,
						 gw.getMapY()+ gw.getMapHeight()/2);
		this.shipLauncher.setSpeed(this.getSpeed());
		this.shipLauncher.setHeading(this.getHeading());
		this.shipLauncher.setLocation(gw.getMapX()+ gw.getMapWidth()/2 + sizeB,
				 					  gw.getMapY()+ gw.getMapHeight()/2 + sizeH);
		
		this.tri = new Triangle(sizeB, sizeH, ColorUtil.argb(60,255,0,255));
	}
	
	public void move(int tick) {
		double oldLocationX = this.getLocation().getX();
		double deltaX = ((float)(Math.cos(90 - this.getHeading()) * (this.getSpeed() * tick)));
		double oldLocationY = this.getLocation().getY();
		double deltaY = ((float)(Math.sin(90 - this.getHeading())) * (this.getSpeed() * tick));
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
	
	public void refillMissile() {
		this.setMissileCount(350);
	}
	
	public void ShootMissile(){
		if (this.getMissileCount() > 0) {
			this.setMissileCount(this.getMissileCount() - 1);
			System.out.println("Missile Fired");
		}
		else {
			System.out.println("Player Ship out of missile.");
		}
	}
	
	public void steerRight() {
		this.setHeading(-10);
		this.shipLauncher.setHeading(this.getHeading());
	}
	
	public void steerLeft() {
		this.setHeading(10);
		this.shipLauncher.setHeading(this.getHeading());
	}
	
	public void rotateLauncher(){
		this.shipLauncher.setHeading(10);
	}
	
	public int getLauncherDirection() {
		return this.shipLauncher.getHeading();
	}
	
	public void speedUp() {
		if(this.getSpeed() < 10 ) {
			this.setSpeed(getSpeed() + 1);
			this.shipLauncher.setSpeed(this.getSpeed());
		}
	}
	
	public void speedDown() {
		if(this.getSpeed() > 0) {
			this.setSpeed(getSpeed() - 1);
			this.shipLauncher.setSpeed(this.getSpeed());
		}
	}

	public String toString() {
		String text = ("Player Ship: " + 
					  " Location: " + this.getLocation() +
					  " Color: [" + ColorUtil.red(getColor()) + ", "  + 
					  	ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" +
					  " Speed: " + this.getSpeed() +
					  " Missiles: " + this.getMissileCount() +
					  " Ship Direction: " + this.getHeading() +
					  " Missile Launcher Direction: " + this.shipLauncher.getHeading()
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
			gwp.getCollection().remove(go);
		}
		if (gwp.getExist(this)) {
			gwp.setIsDead(this);
		}
	}

	
	
}
