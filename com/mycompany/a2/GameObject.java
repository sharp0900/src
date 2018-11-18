package com.mycompany.a2;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class GameObject extends GameWorld{

	private Point2D locate = new Point2D(0,0);
	private int color = ColorUtil.rgb(255,255,255);


	public GameObject(){
		this.locate.setX(new Random().nextInt(this.getMapWidth()));
		this.locate.setY(new Random().nextInt(this.getMapWidth()));
	}
	
	public Point2D getLocation() {
		return locate;
	}
	
	public void setLocation(double x, double y) {
		this.locate.setX(x);
		this.locate.setY(y);
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int r, int g, int b) {
		 this.color = ColorUtil.rgb(r, g, b);
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}

	public int getMapX() {
		return super.getMapWidth();
	}
	
	public int getMapY() {
		return super.getMapHeight();
	}
}
