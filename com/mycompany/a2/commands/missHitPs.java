package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class missHitPs extends Command {

	private GameWorld gw;
	
	public missHitPs(GameWorld gw) {
		super("Fire NPC Missles");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.launch();
	}
	
}
