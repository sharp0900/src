package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.Game;

public class pauseCommand extends Command{

	private Game gw;
	
	public pauseCommand(Game gw) {
		super("Pause Game");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) {
			gw.pause();
		}
	}
}