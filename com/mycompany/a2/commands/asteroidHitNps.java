package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class asteroidHitNps extends Command {
	private GameWorld gw;
	
	public asteroidHitNps(GameWorld gw) {
		super("Asteroid Hits Nps");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.asteroidHitNPS();
	}
	
}
