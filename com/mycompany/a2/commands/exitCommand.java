package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class exitCommand extends Command {
	private GameWorld gw;
	
	public exitCommand(GameWorld gw) {
		super("Exit Game");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
		if (bOk){
				Display.getInstance().exitApplication();
			}
	}
	
}
