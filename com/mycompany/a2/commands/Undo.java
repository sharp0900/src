package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class Undo extends Command{

	private GameWorld gw;
	
	public Undo(GameWorld gw) {
		super("Undo");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {

	}
	
}
