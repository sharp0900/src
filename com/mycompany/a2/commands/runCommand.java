package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.Game;

public class runCommand extends Command{

	private Game gw;
	
	public runCommand(Game gw) {
		super("Run Game");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.run();
	}
	
}