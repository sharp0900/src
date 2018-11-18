package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.GameCollection.GameVectorIterator;

public class MapView extends Container implements Observer{
	
	private GameWorld gw1;
	
	public MapView(GameWorld gw){
		
		Container contain = new Container();
		contain.setLayout(new BorderLayout());
		contain.getAllStyles().setBgColor(ColorUtil.GREEN);
		this.add(contain);
		gw1 = gw;
		}
	
	
	public void update(Observable observable, Object data) {
//		GameVectorIterator coll = ((IGameWorld) data).getCollection().getIterator();
//		System.out.println("========================");
//		while (coll.hasNext()){
//				System.out.println(coll.next().toString());
//		}
//		System.out.println(" ");
		((GameWorld)data).setWidthHeight(getWidth(), getHeight());
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
		
//		super.paint(g);
//		int w = getWidth();
//		int h = getHeight();
//		g.setColor(ColorUtil.BLACK);
//		g.drawRect(getX(), getY(), 20, 40);
//		g.setColor(ColorUtil.GREEN);
//		g.drawRect(w/2, h/2, 20, 40);
//		g.setColor(ColorUtil.BLUE);
//		g.drawRect(getX()+w/2, getY()+h/2, 20, 40);

	}
}
