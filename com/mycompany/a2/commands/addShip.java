package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.*;

public class addShip extends Command {

	private GameWorld gw;
	
	public addShip(GameWorld gw) {
		super("Add Space Ship");
		this.gw = gw;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		gw.addPShip();
	}
	
	
}
