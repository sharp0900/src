package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class speedUpCommand extends Command{

	private GameWorld gw;
	
	public speedUpCommand(GameWorld gw) {
		super("Speed Up");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.speedDown();
	}
	
}

