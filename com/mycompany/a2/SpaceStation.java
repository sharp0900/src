package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class SpaceStation extends FixGameObject{

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
}
