package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonPlayerShip extends Ship implements IDrawable{

	private MissileLauncher npLauncher;
	private Triangle tri;
	
	NonPlayerShip(GameWorldProxy gw){
		super(gw);
		tri = new Triangle(100, 100, ColorUtil.rgb(0,0,0));
		npLauncher = new MissileLauncher(gw);
		npLauncher.setHeading(this.getHeading());
		npLauncher.setSpeed(this.getSpeed());
		npLauncher.setLocation(this.getLocation().getX(), this.getLocation().getY());
	}
	
	public void move() {
		double oldLocationX = this.getLocation().getX();
		double deltaX = (Math.cos(90 - this.getHeading()) * this.getSpeed());
		double oldLocationY = this.getLocation().getY();
		double deltaY = (Math.sin(90 - this.getHeading()) * this.getSpeed());
		this.setLocation(oldLocationX + deltaX, oldLocationY + deltaY);
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

	
}
