package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.a2.GameCollection.GameVectorIterator;

public class MapView extends Container implements Observer{
	
	private GameWorld gw1;
	
	public MapView(GameWorld gw){
		
		Container contain = new Container();
		contain.setLayout(new BorderLayout());
		contain.getAllStyles().setBgTransparency(200);
		contain.getAllStyles().setBgColor(ColorUtil.rgb(204, 255, 229));
		contain.getAllStyles().setBgColor(ColorUtil.GREEN);

		this.add(contain);
		gw1 = gw;
		}
	
	
	public void update(Observable observable, Object data) {
		repaint();
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		GameCollection collection = gw1.getCollection();
		GameVectorIterator vector = collection.getIterator();
		while (vector.hasNext()) {
			vector.next().draw(g, new Point (getX(), getY()));
		}
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		GameVectorIterator vector = gw1.getCollection().getIterator();	
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		if (gw1.getPaused()) {
			while (vector.hasNext()) {
				GameObject temp = (GameObject) vector.next();
				if (temp instanceof ISelectable) {
					if (temp.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						temp.setSelected(true);

					} else {
						temp.setSelected(false);
					}
				}
			}
		}
		repaint();
	}
}
