package com.mycompany.a2;


import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;


public class Triangle{

	private Point top, bottomLeft, bottomRight ;
	private Transform myRotate, myTranslation, myScale;
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
	
	myRotate = Transform.makeIdentity();
	myTranslation = Transform.makeIdentity();
	myScale = Transform.makeIdentity();
	
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
	
	g.fillTriangle(500, tHeight/2, -tBase/2, -tHeight/2, tBase/2, -tHeight/2);
	}
	
	public void translate (float tx, float ty) {
		myTranslation.translate(tx, ty);
	}
	
	public void scale(float sx, float sy) {
		myScale.scale(sx, sy);
	}
	
	public void rotate(float degrees) {
		myRotate.rotate((float) Math.toRadians(degrees), 0, 0);
	}
	

}
