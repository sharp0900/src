package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerShip extends Ship implements ISteerable{

	private PlayerMissileLauncher shipLauncher;
	private Triangle tri;
	
	PlayerShip(GameWorldProxy gw){
		super(gw);
		shipLauncher = new PlayerMissileLauncher(gw);
		this.setLocation(gw.getMapX()+ gw.getMapWidth()/2,
						 gw.getMapY()+ gw.getMapHeight()/2);
		this.setHeading(0);
		this.setSpeed(0);
		this.shipLauncher.setHeading(this.getHeading());
		this.shipLauncher.setLocation(gw.getMapX()+ gw.getMapWidth()/2,
				 					  gw.getMapY()+ gw.getMapHeight()/2);
		this.shipLauncher.setSpeed(this.getSpeed());
		this.tri = new Triangle(200, 200, ColorUtil.rgb(255,0,255));
	}
	
	public void move() {
		double oldLocationX = this.getLocation().getX();
		double deltaX = ((Math.cos(90 - this.getHeading())) * (this.getSpeed()));
		double oldLocationY = this.getLocation().getY();
		double deltaY = ((Math.sin(90 - this.getHeading())) * (this.getSpeed()));
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
	}
	
	public void refillMissile() {
		this.setMissileCount(10);
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
		if(this.getSpeed() < 60 ) {
			this.setSpeed(getSpeed() + 5);
			this.shipLauncher.setSpeed(this.getSpeed());
		}
	}
	
	public void speedDown() {
		if(this.getSpeed() > 0) {
			this.setSpeed(getSpeed() - 5);
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
	
	
}
