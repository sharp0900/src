package com.mycompany.a2;
import java.util.Observable;
import java.util.Random;
import com.mycompany.a2.GameCollection.GameVectorIterator;

public class GameWorld extends Observable implements IGameWorld{
	
	private int shipLife, asteroidExist, 
				npShipExist, ssExist, missileExist,
				points, gameTime;
	
	private GameCollection collection;
	private boolean shipExist;
	private boolean soundOn;
	private int mWidth;
	private int mHeight;
	private int mapX;
	private int mapY;

	
	public GameWorld() {
		collection = new GameCollection();
		this.init();
	}
	
	//=================================================================
	// This will initialize the Game's info and points/scores
	public void init() {
		shipExist = false;
		shipLife = 3;
		asteroidExist = 0;
		npShipExist = 0;
		ssExist = 0;
		missileExist = 0;
		points = 0;
		this.notifyObv();
	}
	//=================================================================
	// This method will return the PlayerShip object type, 
	// this way I don't have to copy and paste so much.
	
	private PlayerShip shipPlayer() { 
		PlayerShip pShip = new PlayerShip();
		GameVectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof PlayerShip) {
				pShip = (PlayerShip) temp;
			}	
		}
		return pShip;
	}
	//=================================================================
	// This method will return the NonPlayerShip object type, 
	// this way I don't have to copy and paste so much.
	
	private NonPlayerShip nonShipPlayer() { 
		NonPlayerShip npShip = new NonPlayerShip();
		GameVectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof NonPlayerShip) {
				npShip = (NonPlayerShip) temp;
			}	
		}
		return npShip;
	}
	
	//==================================================================
	
	// This method will check if the player ship exist and force a game over.
	// It will also decrease player's life count if life is greater than zero. 
	private void isDead() {
		if (shipExist){
			if (shipLife > 0) {
				shipLife--;
				notifyObv();
			}
			else if (shipLife == 0){
				shipExist = false;
				GameVectorIterator coll = collection.getIterator(); 
				while (coll.hasNext()) {
					GameObject temp = coll.next();
					if(temp instanceof PlayerShip) {
						collection.remove(temp);
						notifyObv();
					}
				}
				System.exit(0);
			}
		}
	}
	//=================================================================
	
	//This will print out all the details of the game.
	public void notifyObv() {
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	//=================================================================
	
	// This method will add a new Asteroid into the game world.
	public void addNewAsteroid() {
		asteroidExist++;
		collection.add(new Asteroid());		
		System.out.println("An Asteroid has spawned");
	}
	//=================================================================

	// This method will add a new Space Station into the game world.
	public void addNewStation() {
		ssExist++;
		collection.add(new SpaceStation());
		System.out.println("A Space Station has spawned");
	}
	//=================================================================
	
	// This method will add a new Non-Player Ship into the game world.
	public void addNPShip() {
		npShipExist++;
		collection.add( new NonPlayerShip());
		System.out.println("A Non-Player Ship has spawned");
	}
	//=================================================================
	// This method will add a new Player Ship into the game world.
	public void addPShip() {
		if (shipExist == false) {
			collection.add(new PlayerShip());
			shipExist = true;
			System.out.println("A Player Ship has spawned");
		}
		else {
			System.out.println("Player Ship has already been launched");	
		}
	}
	//=================================================================
	// This method will fire a missile from Non-Player Ship.
	public void launch() {
		if(npShipExist > 0) {
			missileExist++;
			Missile missile = new Missile();
			nonShipPlayer().npsShootMissile();
			missile.setSpeed(nonShipPlayer().getSpeed() + 1);
			missile.setHeading(nonShipPlayer().getHeading());
			missile.setShip("Non-Player Ship");
			missile.setLocation(nonShipPlayer().getLocation().getX(), nonShipPlayer().getLocation().getY());
			collection.add(missile);
			notifyObv();
		}
		else{
			System.out.println("No Non-Player Ship exists to shoot Missile.");
		}
	}
	//=================================================================
	// This method will turn the Player ship left.
	public void changeDirectLeft() {
		if (shipExist) {
			shipPlayer().steerLeft();
			System.out.println("Player Ship turned left");
		}
		else {
			System.out.println("Player Ship does not exist.");
		}
	}
	//=================================================================
	// This method will turn the Player ship Right.
	public void changeDirectRight() {
		if (shipExist) {
			shipPlayer().steerRight();
			System.out.println("Player Ship turned right");
		}
		else {
			System.out.println("Player Ship does not exist.");
		}
	}
	//=================================================================
	// This method will speed up the Player ship.
	public void speedUp() {
		if (shipExist) {
			shipPlayer().speedUp();
			System.out.println("Player Ship speed increased.");
		}
		else {
			System.out.println("Player Ship does not exist.");
		}
	}
	//=================================================================
	// This method will slow down the Player ship.
	public void speedDown() {
		if (shipExist) {
			shipPlayer().speedDown();
			System.out.println("Player Ship speed decreased");
		}
		else {
			System.out.println("Player Ship does not exist.");
		}
	}
	//=================================================================
	// This method will reset the Player ship's position.
	public void jump() {
		if (shipExist) {
			shipPlayer().setLocation(512, 384);
			System.out.println("Player Ship jumped to center of map");
		}
		else {
			System.out.println("Player Ship does not exist.");
		}
	}
	//=================================================================
	// This will refill the Player ship's missile count.
	public void fillMissile() {
		if (shipExist) {
			shipPlayer().refillMissile();
			System.out.println("Player Ship is locked and loaded");
		}
		else {
			System.out.println("Player Ship does not exist.");
		}
	}
	//=================================================================
	// This will fire a missile from player ship.
	public void fireMissile() {
		if (shipExist) {
			missileExist++;
			Missile missile = new Missile();
			missile.setLocation(shipPlayer().getLocation().getX(), shipPlayer().getLocation().getY());
			missile.setHeading(shipPlayer().getLauncherDirection());
			missile.setShip("Player Missile");
			missile.setSpeed(shipPlayer().getSpeed() + 2);
			shipPlayer().ShootMissile();
			collection.add(missile);
		}
		else {
			System.out.println("Player Ship does not exist.");
		}
	}
	//=================================================================
	// Assuming that a missile is fired and hits a non-player ship,
	// this will remove a non-player ship and missile from the game world.
	public void eliminate(){
		boolean flagNPS = false;
		boolean flagM = false;
		
		if (missileExist <= 0 || npShipExist <= 0) {
			System.out.println("No ship/non-player ship exist");
		}
		else {
			points = points + 10;
			missileExist--;
			npShipExist--;
			GameObject temp = new GameObject();
			GameVectorIterator coll = collection.getIterator();
			while(coll.hasNext()) {
				temp = coll.next();
				if (!flagNPS && temp instanceof NonPlayerShip) {
					flagNPS = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
				if (!flagM && temp instanceof Missile) {
					flagM = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
			}
			System.out.println("Critical hit! Ship Destroyed!");
		}
	}
	//=================================================================
	// This will rotate the ship's missile launcher
	public void rotateLauncher() {
		if(shipExist == false) {
			System.out.print("Player Ship does not exist");
		}
		else {
			shipPlayer().rotateLauncher();
		}
	}
	//=================================================================
	// Assuming a Missile and asteroid exist in the game world and hit 
	// each other, this will remove both of them from the world.
	public void destroyAsteroid() {
		boolean flagA = false;
		boolean flagM = false;
		
		if (asteroidExist <= 0 || missileExist <= 0) {
			System.out.println("No asteroids/missile currently to be destroyed");
		}
		else {
			missileExist--;
			asteroidExist--;
			points = points + 10;
			
			GameObject temp = new GameObject();
			GameVectorIterator coll = collection.getIterator();
			while(coll.hasNext()) {
				temp = coll.next();
				if (!flagA && temp instanceof Asteroid) {
					flagA = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
				if (!flagM && temp instanceof Missile) {
					flagM = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
			}
			notifyObv();
			System.out.println("Direct hit to asteroid from missile!");
		}
	}
	//=================================================================
	// Assuming a Missile and Player ship exist in the game world and 
	// hit each other, this will remove both of them from the world.
	public void missileHitPShip() {
		boolean flagM = false;
		isDead();
		
		if(shipExist == false || missileExist <= 0) {
			System.out.println("No player ship/missile currently to be destroyed");
		}
		else {
			missileExist--;
			GameObject temp = new GameObject();
			GameVectorIterator coll = collection.getIterator();
			while(coll.hasNext()) {
				temp = coll.next();
				if (!flagM && temp instanceof Missile) {
					flagM = true;
					collection.remove(temp);
				}
			}
			notifyObv();
			System.out.println("You Died");
		}	
	}
	//==================================================================
	// Assuming a Player ship and asteroid exist in the game world and 
	// hit each other, this will remove both of them from the world.
	public void crash() {
		boolean flagA = false;
		isDead();
		
		if(shipExist == false || asteroidExist <= 0) {
			System.out.println("No player ship/Asteroid currently to be destroyed");
		}
		else {
			asteroidExist--;
			GameObject temp = new GameObject();
			GameVectorIterator coll = collection.getIterator();
			while(coll.hasNext()) {
				temp = coll.next();
				if (!flagA && temp instanceof Asteroid){
					flagA = true;
					collection.remove(temp);
				}
			}
			notifyObv();
			System.out.println("You Died");
		}
	}
	//=================================================================
	// Assuming a Non-Player Ship and Player Ship exist in the game world 
	// and hit each other, this will remove both of them from the world.
	public void hit() {
		boolean flag = false;
		isDead();
		
		if(shipExist == false || npShipExist <= 0) {
			System.out.println("No player ship/Non-PlayerShip currently to be destroyed");
		}
		else {
			npShipExist--;
			GameObject temp = new GameObject();
			GameVectorIterator coll = collection.getIterator();
			while(coll.hasNext()) {
				temp = coll.next();
				if (!flag && temp instanceof NonPlayerShip) {
					flag = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
			}
			notifyObv();
			System.out.println("You Died");
		}
	}
	//=================================================================
	// Assuming two Asteroids exist in the game world and hit each other,
	// this will remove both of them from the world.
	public void destroyTwoAsteroid() {
		boolean flagA = false;
		boolean flagAA = false;
		
		if (asteroidExist < 1) {
			System.out.println("No asteroid currently to be destroyed");
		}
		else {
			asteroidExist = asteroidExist - 2;
			GameObject temp = new GameObject();
			GameVectorIterator coll = collection.getIterator();
			while(coll.hasNext()) {
				temp = coll.next();
				if (!flagA && temp instanceof Asteroid) {
					flagA = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
				if (!flagAA && temp instanceof Asteroid) {
					flagAA = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
			}
			notifyObv();
		}
	}
	//=================================================================
	// Assuming a Non-Player Ship and an Asteroid exist in the game world 
	// and hit each other, this will remove both of them from the world.
	public void asteroidHitNPS() {
		boolean flagA = false;
		boolean flagnps = false;
		
		if (asteroidExist <= 0 || npShipExist <= 0) {
			System.out.println("No asteroid/non-player ship currently to be destroyed");
		}
		else {
			asteroidExist--;
			npShipExist--;
			GameObject temp = new GameObject();
			GameVectorIterator coll = collection.getIterator();
			while(coll.hasNext()) {
				temp = coll.next();
				if (!flagA && temp instanceof Asteroid) {
					flagA = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
				if (!flagnps && temp instanceof NonPlayerShip) {
					flagnps = true;
					collection.remove(temp);
					coll = collection.getIterator();
				}
			}
			notifyObv();
		}
	}
	//=================================================================
	// This will increase the in-game clock by one and tells every movable object
	// to move. Space Stations will also blink according to the game's time as well.
	public void tickTock() {
		GameObject temp = new GameObject();
		GameVectorIterator coll = collection.getIterator();
		gameTime++;
		if(ssExist > 0) {	// If Space Station exist, modify the blink rate.
			int blinkRate = 0;
			while(coll.hasNext()) {
				temp = coll.next();
				if(temp instanceof SpaceStation){
					blinkRate = ((SpaceStation) temp).getBlinkRate();
					if (blinkRate % gameTime == 0) {
						((SpaceStation) temp).setLight(false);
					}
					else{
						((SpaceStation) temp).setLight(true);
					}
				}
			}
		}
		
		coll = collection.getIterator();
		if (missileExist > 0) {	// If Missile exists, modify missile fuel and remove it if zero.
			while(coll.hasNext()) {
				temp = coll.next();
				if (temp instanceof Missile){
					if (((Missile) temp).getFuelLevel() > 0) {
						((Missile) temp).setFuelLevel();
					}
					else {
						 collection.remove(temp);
						 coll = collection.getIterator();
					}
				}
			}
		}
		coll = collection.getIterator();
		while(coll.hasNext()) {	// Move every movable object in the game world.
			temp = coll.next();
			if( temp instanceof IMoveable) {
				((IMoveable) temp).move();
			}
		}
		
		notifyObv();
	}
	//=================================================================

	// This will display the details of all objects in the game world.
	public void map() {
		
		GameVectorIterator coll = collection.getIterator();
		System.out.println("========================");
		while (coll.hasNext()){
				System.out.println(coll.next().toString());
		}
		System.out.println("========================");
	}	
	
	//=================================================================
	// Enable/Disable Sound
	public void soundOnOff(){
	
		if (soundOn) {
			soundOn = false;
		}else {
			soundOn = true;
		}
		
		notifyObv();
	}
	
	//=================================================================
	// Map width/height setters
	public void setWidthHeight(int width, int height) {
		mWidth = width;
		mHeight = height;
	}	
	
	public void setXY(int x, int y) {
		mapX = x;
		mapY = y;
	}
	
	//=================================================================
	//Proxy get methods
	
	public int getMapX() {
		return mapX;
	}
	
	public int getMapY() {
		return mapY;
	}
	
	public int getMapWidth() {
		return this.mWidth;
	}
	
	public int getMapHeight() {
		return this.mHeight;		
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public int getGameTime() {
		return this.gameTime;
	}
	
	public int getShipLife() {
		return this.shipLife;
	}
	
	public int getMissileCount() {
		return shipPlayer().getMissileCount();
	}
	
	public boolean getSound() {
		return this.soundOn;
	}

	public GameCollection getCollection() {
		return this.collection;
	}
	
}
