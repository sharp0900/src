package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class fireMissile extends Command{


	private GameWorld gw;
	
	public fireMissile(GameWorld gw) {
		super("Launch Missile");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		//gw.fireMissile();
	    if (e.getKeyEvent() != -1) {
	        gw.fireMissile();
	        System.out.println("fire missile");
	     }
	}
	
	
}
