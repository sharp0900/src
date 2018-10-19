package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.GameCollection.GameVectorIterator;

public class MapView extends Container implements Observer{
	
	public MapView(){
		Container contain = new Container();
		contain.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		contain.getAllStyles().setPadding(Component.LEFT, 10);
		contain.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GREEN));
		this.add(contain);
		
		
	}
	
	
	public void update(Observable observable, Object data) {
		
		GameVectorIterator coll = ((IGameWorld) data).getCollection().getIterator();
		System.out.println("========================");
		while (coll.hasNext()){
				System.out.println(coll.next().toString());
		}
		System.out.println(" ");
		
	}

	
}
