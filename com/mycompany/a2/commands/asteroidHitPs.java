package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class asteroidHitPs extends Command{

	private GameWorld gw;
	
	public asteroidHitPs(GameWorld gw) {
		super("PlayerShip Crashes");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.crash();
	}
	
}
