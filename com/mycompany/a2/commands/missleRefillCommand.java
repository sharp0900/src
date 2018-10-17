package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class missleRefillCommand extends Command{

	private GameWorld gw;
	
	public missleRefillCommand(GameWorld gw) {
		super("Missle Restock");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.fillMissile();
	}
	
}
