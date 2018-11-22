package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class asteroidHitNps extends Command {
	private GameWorld gw;
	
	public asteroidHitNps(GameWorld gw) {
		super("Nps hits Astroid");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		//gw.asteroidHitNPS();
	}
	
}
