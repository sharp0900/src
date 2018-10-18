package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class turnShipRightCommand extends Command{

	private GameWorld gw;
	
	public turnShipRightCommand(GameWorld gw) {
		super("Move Launcher Right");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.changeDirectRight();
	}
}
