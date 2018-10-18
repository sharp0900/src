package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class turnShipLeftCommand extends Command{

	private GameWorld gw;
	
	public turnShipLeftCommand(GameWorld gw) {
		super("Move Launcher Right");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.changeDirectLeft();
	}
}
