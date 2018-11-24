package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MissileRecharge extends Command{

	private GameWorld gw;
	
	public MissileRecharge(GameWorld gw) {
		super("Missle Refill Fuel");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.refillMissileFuel();
	}
	
}
