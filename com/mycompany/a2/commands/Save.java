package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class Save extends Command{

	private GameWorld gw;
	
	public Save(GameWorld gw) {
		super("Save");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
