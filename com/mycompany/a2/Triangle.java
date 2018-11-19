package com.mycompany.a2;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class Triangle{

	private Point top, bottomLeft, bottomRight ;
	private int color ;
	int tHeight;
	int tBase;
	
	public Triangle (int base, int height, int c) {
	tHeight = height;
	tBase = base;
	top = new Point (0, height/2);
	bottomLeft = new Point (-base/2, -height/2);
	bottomRight = new Point (base/2, -height/2);
	color = c;
	}
	
	public void draw (Graphics g, Point pCmpRelPrnt) {
	g.setColor(color);
	
	g.drawLine( (pCmpRelPrnt.getX()+ top.getX()), 
	(pCmpRelPrnt.getY()+top.getY()),
	(pCmpRelPrnt.getX()+bottomLeft.getX()),
	(pCmpRelPrnt.getY()+bottomLeft.getY()));
	
	g.drawLine((pCmpRelPrnt.getX()+bottomLeft.getX()),
	(pCmpRelPrnt.getY()+bottomLeft.getY()),
	(pCmpRelPrnt.getX()+bottomRight.getX()),
	(pCmpRelPrnt.getY()+bottomRight.getY()));
	
	g.drawLine((pCmpRelPrnt.getX()+bottomRight.getX()),
	(pCmpRelPrnt.getY()+bottomRight.getY()),
	(pCmpRelPrnt.getX()+top.getX()),
	(pCmpRelPrnt.getY()+top.getY()));
	
	g.fillTriangle(1000, tHeight/2, -tBase/2, -tHeight/2, tBase/2, -tHeight/2);
	
	}
	

}
