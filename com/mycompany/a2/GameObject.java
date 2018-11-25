package com.mycompany.a2;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class GameObject extends GameCollection{

	private Point2D locate = new Point2D(0,0);
	private int color = ColorUtil.rgb(255,255,255);
	private int size;


	public GameObject(GameWorldProxy gw){
		this.locate.setX(new Random().nextInt((gw.getMapWidth() - gw.getMapX()) + 1) + gw.getMapX());
		this.locate.setY(new Random().nextInt((gw.getMapHeight() - gw.getMapY()) + 1) + gw.getMapY());
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
	
	public int getSize() {
		return this.size;
	}
	
	public void setSize(int x) {
		this.size = x;
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {	
	}


	public boolean collideWith(ICollider gameObject) {
		return false;
	}


	public void handleCollision(ICollider gameObject) {
		
	}

	public void setSelected(boolean b) {
		
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		return false;
	}

}
