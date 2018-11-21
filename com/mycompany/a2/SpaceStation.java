package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixGameObject implements IDrawable{

	private int blinkRate;
	private boolean lightOn;
	
	SpaceStation(GameWorldProxy gw){
		super(gw);
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
				   200, 100, (byte)100);
	}
}
