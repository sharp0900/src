package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class npsHitpsCommand extends Command {

	private GameWorld gw;
	
	public npsHitpsCommand(GameWorld gw) {
		super("Nps and Ps Collide");
		this.gw = gw;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		gw.hit();
	}
	
	
}
