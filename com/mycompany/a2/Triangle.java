package com.mycompany.a2;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class Triangle{

	private Point top, bottomLeft, bottomRight ;
	private int color ;
	
	public Triangle (int base, int height) {
	top = new Point (0, height/2);
	bottomLeft = new Point (-base/2, -height/2);
	bottomRight = new Point (base/2, -height/2);
	color = ColorUtil.BLACK;
	}
	
	public void draw (Graphics g, Point pCmpRelPrnt) {
	g.setColor(color);
	g.drawLine( (pCmpRelPrnt.getX()+ top.getX()), (pCmpRelPrnt.getY()+top.getY()),
	(pCmpRelPrnt.getX()+bottomLeft.getX()),
	(int)(pCmpRelPrnt.getY()+bottomLeft.getY()));
	g.drawLine((int)(pCmpRelPrnt.getX()+bottomLeft.getX()),
	(int) (pCmpRelPrnt.getY()+bottomLeft.getY()),
	(int) (pCmpRelPrnt.getX()+bottomRight.getX()),
	(int) (pCmpRelPrnt.getY()+bottomRight.getY()));
	g.drawLine((int)(pCmpRelPrnt.getX()+bottomRight.getX()),(int)(pCmpRelPrnt.getY()+bottomRight.getY()),
	(int) (pCmpRelPrnt.getX()+top.getX()),
	(int) (pCmpRelPrnt.getY()+top.getY()));
	}
}
