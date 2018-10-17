package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class tickCommand extends Command{

	private GameWorld gw;
	
	public tickCommand(GameWorld gw) {
		super("Tick Tocks the Clock");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.tickTock();
	}
	
}
