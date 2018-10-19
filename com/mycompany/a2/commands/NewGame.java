package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class NewGame extends Command {

	private GameWorld gw;
	
	public NewGame(GameWorld gw) {
		super("New");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.asteroidHitNPS();
	}
}
