package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class speedDownCommand extends Command{

	private GameWorld gw;
	
	public speedDownCommand(GameWorld gw) {
		super("Speed Down");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		gw.speedUp();
	}
	
}