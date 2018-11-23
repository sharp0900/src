package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class addAsteroidCommand extends Command{

	private GameWorld gw;
	
	public addAsteroidCommand(GameWorld gw) {
		super("Add Some New Asteroid");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getKeyEvent() != -1) {
		gw.addNewAsteroid();
		}
	}
	
}
