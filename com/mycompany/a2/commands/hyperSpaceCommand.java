package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class hyperSpaceCommand extends Command{
	private GameWorld gw;
	
	public hyperSpaceCommand(GameWorld gw) {
		super("Jump to hyperspace");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) {
			gw.jump();
		}
	}
	
}
