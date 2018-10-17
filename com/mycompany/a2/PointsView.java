package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

public class PointsView extends Container implements Observer {

	private Label pointsValueText;
	private Label livesValueText;
	private Label soundValueText;
	private Label timeValueText;
	private Label missileValueText;
	
	public PointsView() {
		
		Label pointsText = new Label("Points: ");
		pointsValueText = new Label("XXX");
		
		Label livesText = new Label("Lives: ");
		livesValueText = new Label("XXX");
		
		Label missileText = new Label("Missiles: ");
		missileValueText = new Label("XXX");
		
		Label timeText = new Label("Time: ");
		timeValueText = new Label("XXX");
		
		Label soundText = new Label("Sound: ");
		soundValueText = new Label("XXX");
		
		Container contain = new Container();
		contain.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		contain.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		contain.add(pointsText);
		contain.add(pointsValueText);
		
		contain.add(livesText);
		contain.add(livesValueText);
		
		contain.add(missileText);
		contain.add(missileValueText);
		
		contain.add(timeText);
		contain.add(timeValueText);
		
		contain.add(soundText);
		contain.add(soundValueText);
		
		this.add(contain);
	}
	
	public void update(Observable observable, Object data) {
		
		IGameWorld gw = (IGameWorld) data;
		
		int score = gw.getPoints();
		int lives = gw.getShipLife();
		boolean sound = gw.getSound();
		int missile = gw.getMissileCount();
		int time = gw.getGameTime();
		
		pointsValueText.setText(""+score);
		timeValueText.setText(""+ time);
		missileValueText.setText(""+ missile);
		soundValueText.setText(""+ sound);
		livesValueText.setText(""+ lives);
		this.repaint();
	}
}
