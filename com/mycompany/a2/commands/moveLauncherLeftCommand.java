package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class moveLauncherLeftCommand extends Command{

	private GameWorld gw;
	
	public moveLauncherLeftCommand(GameWorld gw) {
		super("Move Launcher Left");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.rotateLauncher();
	}
	

}
