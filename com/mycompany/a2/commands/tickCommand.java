package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.Game;
import com.mycompany.a2.GameWorld;

public class tickCommand extends Command{

	private Game gw;
	
	public tickCommand(Game game) {
		super("Run the game");
		this.gw = game;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.run();
	}
	
}
