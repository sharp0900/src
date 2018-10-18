package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.mycompany.a2.commands.*;

public class Game extends Form{

	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	
	public Game() {
		gw = new GameWorld();		// create "Observable"
		mv = new MapView();			// create an “Observer” for the map
		pv = new PointsView();	    // create an “Observer” for the points
		gw.addObserver(mv);			// register the map Observer
		gw.addObserver(pv);			// register the points observer
		gw.init();
		
		//=================================================================================
		//Command Buttons to be put on left side of GUI
		Button addSpaceShip = new Button();
		addSpaceShip.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		addSpaceShip.getAllStyles().setBgTransparency(255);
		
		Button addAsteroidButton = new Button();
		addAsteroidButton.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		addAsteroidButton.getAllStyles().setBgTransparency(255);
		
		Button addNonPlayerShip = new Button();
		addNonPlayerShip.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		addNonPlayerShip.getAllStyles().setBgTransparency(255);
			
		Button firePSMissile = new Button();
		firePSMissile.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		firePSMissile.getAllStyles().setBgTransparency(255);
		
		Button launchNPCMissle = new Button();
		launchNPCMissle.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		launchNPCMissle.getAllStyles().setBgTransparency(255);
		
		Button jump = new Button();
		jump.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		jump.getAllStyles().setBgTransparency(255);
		
		Button refill = new Button();
		refill.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		refill.getAllStyles().setBgTransparency(255);
		
		Button moveLauncherLeft = new Button();
		moveLauncherLeft.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		moveLauncherLeft.getAllStyles().setBgTransparency(255);
		
		Button moveLauncherRight = new Button();
		moveLauncherRight.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		moveLauncherRight.getAllStyles().setBgTransparency(255);
		
		Button missileHitAsteroid = new Button();
		missileHitAsteroid.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		missileHitAsteroid.getAllStyles().setBgTransparency(255);
		
		Button missileHitNps = new Button();
		missileHitNps.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		missileHitNps.getAllStyles().setBgTransparency(255);
		
		Button missileHitPs = new Button();
		missileHitPs.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		missileHitPs.getAllStyles().setBgTransparency(255);
		
		Button asteroidHitPs = new Button();
		asteroidHitPs.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		asteroidHitPs.getAllStyles().setBgTransparency(255);
		
		Button asteroidHitNps = new Button();
		asteroidHitNps.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		asteroidHitNps.getAllStyles().setBgTransparency(255);
		
		Button npsHitPs = new Button();
		npsHitPs.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		npsHitPs.getAllStyles().setBgTransparency(255);
		
		Button asteroidHitAsteroid = new Button();
		asteroidHitAsteroid.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		asteroidHitAsteroid.getAllStyles().setBgTransparency(255);
		
		Button npsHitAsteroid = new Button();
		npsHitAsteroid.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		npsHitAsteroid.getAllStyles().setBgTransparency(255);
		
		Button tick = new Button();
		tick.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		tick.getAllStyles().setBgTransparency(255);
		
		Button exit = new Button();
		exit.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		exit.getAllStyles().setBgTransparency(255);
		

		//================================================================================
		//Command keyboard input
		
		
		
		addShip addShipcommand = new addShip(gw);
		addSpaceShip.setCommand(addShipcommand);
		addKeyListener('s', addShipcommand);
		
		addAsteroidCommand addAsteroid = new addAsteroidCommand(gw);
		addAsteroidButton.setCommand(addAsteroid);
		addKeyListener('a', addAsteroid);
		
		nonPlayerShipCommand addNPSShip = new nonPlayerShipCommand(gw);
		addNonPlayerShip.setCommand(addNPSShip);
		addKeyListener('y', addNPSShip);
		
		fireMissile fireMissile = new fireMissile(gw);
		firePSMissile.setCommand(fireMissile);
		addKeyListener(-90, fireMissile);
		
		fireNPCMissleCommand fireNPCMissile = new fireNPCMissleCommand(gw);
		launchNPCMissle.setCommand(fireNPCMissile);
		addKeyListener('l', fireNPCMissile);
		
		hyperSpaceCommand hyperSpace = new hyperSpaceCommand(gw);
		jump.setCommand(hyperSpace);
		addKeyListener('j', hyperSpace);
		
		tickCommand tickTock = new tickCommand(gw);
		tick.setCommand(tickTock);
		addKeyListener('t', tickTock);
		
		exitCommand exitGame = new exitCommand(gw);
		exit.setCommand(exitGame);
		addKeyListener('q', exitGame);
		
		missleRefillCommand refillMissile = new missleRefillCommand(gw);
		refill.setCommand(refillMissile);
		addKeyListener('n', refillMissile);
		
		asteroidHitNps asteroidHitNpsCommand = new asteroidHitNps(gw);
		npsHitAsteroid.setCommand(asteroidHitNpsCommand);
		addKeyListener('h', asteroidHitNpsCommand);
		
		asteroidHitPs asteroidhitPsCommand = new asteroidHitPs(gw);
		asteroidHitPs.setCommand(asteroidhitPsCommand);
		addKeyListener('o', asteroidhitPsCommand);
		
		astHitAstCommand astHitast = new astHitAstCommand(gw);
		asteroidHitAsteroid.setCommand(astHitast);
		
		missHitAsCommand missileHitAs = new missHitAsCommand(gw);
		missileHitAsteroid.setCommand(missileHitAs);
		
		missHitNpsCommand missileNpsHit = new missHitNpsCommand(gw);
		missileHitNps.setCommand(missileNpsHit);
		
		missHitPs missileHitPlayer = new missHitPs(gw);
		missileHitPs.setCommand(missileHitPlayer);
		
		moveLauncherLeftCommand movelauncherleft = new moveLauncherLeftCommand(gw);
		moveLauncherLeft.setCommand(movelauncherleft);
		
		npsHitpsCommand npsHitps = new npsHitpsCommand(gw);
		npsHitPs.setCommand(npsHitps);
		
		speedDownCommand speedDown = new speedDownCommand(gw);
		addKeyListener(-92,speedDown);
		
		speedUpCommand speedUp = new speedUpCommand(gw);
		addKeyListener(-91,speedUp);
		
		turnShipLeftCommand turnLeft = new turnShipLeftCommand(gw);
		addKeyListener(-93,turnLeft);
		
		turnShipRightCommand turnRight = new turnShipRightCommand(gw);
		addKeyListener(-94,turnRight);
		

		//================================================================
		//Containers, in other words, the GUI
		this.setLayout(new BorderLayout());
		
		//Points View
		add(BorderLayout.NORTH, pv);
		
		//Map View
	    add(BorderLayout.CENTER,mv);
		
		//Command Buttons Container
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.LEFT, 10);
		leftContainer.getAllStyles().setPadding(Component.RIGHT, 10);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		leftContainer.add(addSpaceShip);
		leftContainer.add(addAsteroidButton);
		leftContainer.add(addNonPlayerShip);
		leftContainer.add(firePSMissile);
		leftContainer.add(launchNPCMissle);
		leftContainer.add(jump);
		leftContainer.add(refill);
		leftContainer.add(asteroidHitPs);
		leftContainer.add(asteroidHitAsteroid);
		leftContainer.add(missileHitAsteroid);
		leftContainer.add(missileHitNps);
		leftContainer.add(missileHitPs);
		leftContainer.add(asteroidHitNps);
		leftContainer.add(npsHitPs);
		leftContainer.add(npsHitAsteroid);
		leftContainer.add(tick);
		leftContainer.add(exit);
		add(BorderLayout.WEST,leftContainer);
		
		this.show();
	}
}

