package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.Game;

public class playCommand extends Command{

	private Game gw;
	
	public playCommand(Game gw) {
		super("Unpause game");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.play();
	}
	
}