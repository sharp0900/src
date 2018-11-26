package com.mycompany.a2;

import java.util.Observable;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.mycompany.a2.GameCollection.GameVectorIterator;

public class GameWorld extends Observable implements IGameWorld {

	private int shipLife, npShipExist, ssExist, missileExist, points, gameTime;
	private SoundBG soundBG;
	private SoundMissile soundMissile;
	private soundDeath soundDeath;
	private soundHit soundHit;
	private GameCollection collection;
	private GameWorldProxy iG;
	private boolean shipExist;
	private boolean soundOn;
	private boolean pause;
	private int mWidth;
	private int mHeight;
	private int mapX;
	private int mapY;

	public GameWorld() {
		this.init();
	}

	// =================================================================
	// This will initialize the Game's info and points/scores
	public void init() {
		collection = new GameCollection();
		iG = new GameWorldProxy(this);
		soundBG = new SoundBG("mainGame.mp3");
		soundHit = new soundHit("soundHit.wav");
		soundMissile = new SoundMissile("soundMissile.wav");
		soundDeath = new soundDeath("gameOver.wav");
		soundBG.play();
		soundOn = true;
		shipExist = false;
		shipLife = 3;
		npShipExist = 0;
		ssExist = 0;
		missileExist = 0;
		points = 0;
		gameTime = 0;
		pause = false;
		this.notifyObv();
	}
	// =================================================================
	// This method will return the PlayerShip object type,
	// this way I don't have to copy and paste so much.

	private PlayerShip shipPlayer() {
		PlayerShip pShip = new PlayerShip(iG);
		GameVectorIterator coll = collection.getIterator();
		while (coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof PlayerShip) {
				pShip = (PlayerShip) temp;
			}
		}
		return pShip;
	}
	// =================================================================
	// This method will return the NonPlayerShip object type,
	// this way I don't have to copy and paste so much.

	private NonPlayerShip nonShipPlayer() {
		NonPlayerShip npShip = new NonPlayerShip(iG);
		GameVectorIterator coll = collection.getIterator();
		while (coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof NonPlayerShip) {
				npShip = (NonPlayerShip) temp;
			}
		}
		return npShip;
	}

	// ==================================================================

	// This method will check if the player ship exist and force a game over.
	// It will also decrease player's life count if life is greater than zero.
	private void isDead(GameObject ship) {

		if (shipExist) {
			if (shipLife > 0) {
				shipLife--;
				this.jump();
				this.fillMissile();
				notifyObv();
			} else if (shipLife == 0) {
				shipExist = false;
				this.soundOnOff();
				soundDeath.play();
				Boolean bOk = Dialog.show("Game Over!?", "Start a new game?", "Sure!",
						"Nah, I got better things to do...");
				if (bOk) {
					soundDeath.pause();
					newGame();
				} else {
					Display.getInstance().exitApplication();
				}
			}
		}
	}

	// ================================================================
	// This Method will check to see if the object exists in the collector

	public boolean collectionExist(GameObject obj) {
		boolean exist = false;

		GameCollection coll = this.getCollection();
		GameVectorIterator itterate = coll.getIterator();
		while (itterate.hasNext()) {
			if (itterate.next() == obj) {
				exist = true;
			}
		}

		return exist;
	}

	// =================================================================

	// This will print out all the details of the game.
	public void notifyObv() {
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	// =================================================================

	// This method will add a new Asteroid into the game world.
	public void addNewAsteroid() {
		collection.add(new Asteroid(iG));
		System.out.println("An Asteroid has spawned");
		notifyObv();
	}
	// =================================================================

	// This method will add a new Space Station into the game world.
	public void addNewStation() {
		ssExist++;
		collection.add(new SpaceStation(iG));
		System.out.println("A Space Station has spawned");
		notifyObv();
	}
	// =================================================================

	// This method will add a new Non-Player Ship into the game world.
	public void addNPShip() {
		npShipExist++;
		collection.add(new NonPlayerShip(iG));
		System.out.println("A Non-Player Ship has spawned");
		notifyObv();
	}

	// =================================================================
	// This method will add a new Player Ship into the game world.
	public void addPShip() {
		if (shipExist == false) {
			PlayerShip ps = new PlayerShip(iG);
			collection.add(ps);
			collection.add(ps.getLauncher());
			shipExist = true;
			System.out.println("A Player Ship has spawned");
			notifyObv();
		} else {
			System.out.println("Player Ship has already been launched");
		}
	}

	// =================================================================
	// This method will fire a missile from Non-Player Ship.
	public void launch() {
		if (npShipExist > 0) {
			missileExist++;
			Missile missile = new Missile(iG);
			nonShipPlayer().npsShootMissile();
			missile.setSpeed(nonShipPlayer().getSpeed() + 4);
			missile.setHeading(nonShipPlayer().getHeading());
			missile.setShip("Non-Player Ship");
			missile.setLocation(nonShipPlayer().getLocation().getX() + 10, nonShipPlayer().getLocation().getY() + 10);
			collection.add(missile);
			soundMissile = new SoundMissile("soundMissile.wav");
			if (soundOn) {
				soundMissile.play();
			}
			notifyObv();
		} else {
			System.out.println("No Non-Player Ship exists to shoot Missile.");
		}
	}

	// =================================================================
	// This method will turn the Player ship left.
	public void changeDirectLeft() {
		if (shipExist) {
			shipPlayer().steerLeft();
			System.out.println("Player Ship turned left");
		} else {
			System.out.println("Player Ship does not exist.");
		}
	}

	// =================================================================
	// This method will turn the Player ship Right.
	public void changeDirectRight() {
		if (shipExist) {
			shipPlayer().steerRight();
			System.out.println("Player Ship turned right");
		} else {
			System.out.println("Player Ship does not exist.");
		}
	}

	// =================================================================
	// This method will speed up the Player ship.
	public void speedUp() {
		if (shipExist) {
			shipPlayer().speedUp();
			System.out.println("Player Ship speed increased.");
		} else {
			System.out.println("Player Ship does not exist.");
		}
	}

	// =================================================================
	// This method will slow down the Player ship.
	public void speedDown() {
		if (shipExist) {
			shipPlayer().speedDown();
			System.out.println("Player Ship speed decreased");
		} else {
			System.out.println("Player Ship does not exist.");
		}
	}

	// =================================================================
	// This method will reset the Player ship's position.
	public void jump() {
		if (shipExist) {
			shipPlayer().setLocation(this.getMapX() + this.getMapWidth() / 2, this.getMapY() + this.getMapHeight() / 2);
			System.out.println("Player Ship jumped to center of map");
		} else {
			System.out.println("Player Ship does not exist.");
		}
	}

	// =================================================================
	// This will refill the Player ship's missile count.
	public void fillMissile() {
		if (shipExist) {
			shipPlayer().refillMissile();
			System.out.println("Player Ship is locked and loaded");
		} else {
			System.out.println("Player Ship does not exist.");
		}
	}

	public void refillMissileFuel() {
		GameVectorIterator coll = collection.getIterator();
		while (coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof ISelectable && temp instanceof Missile) {
				if (((ISelectable) temp).isSelected()) {
					((Missile) temp).setFuelLevel(100);
				}
			}
		}
	}

	// =================================================================
	// This will fire a missile from player ship.
	public void fireMissile() {
		if (shipExist && this.getMissileCount() > 0) {
			missileExist++;
			Missile missile = new Missile(iG);
			missile.setLocation(shipPlayer().getLocation().getX(), shipPlayer().getLocation().getY());
			missile.setHeading(shipPlayer().getLauncherDirection());
			missile.setShip("Player Ship");
			missile.setSpeed(shipPlayer().getSpeed() + 4);
			shipPlayer().ShootMissile();
			collection.add(missile);
			if (soundOn) {
				soundMissile = new SoundMissile("soundMissile.wav");
				soundMissile.play();
			}
			notifyObv();
		} else {
			System.out.println("Player Ship does not exist.");
		}
	}

	// =================================================================
	// This will increase the in-game clock by one and tells every movable object
	// to move. Space Stations will also blink according to the game's time as well.
	public void tickTock(int tick) {
		GameObject temp = new GameObject(iG);
		GameVectorIterator coll = collection.getIterator();
		GameVectorIterator coll2 = collection.getIterator();
		gameTime++;
		if (ssExist > 0) { // If Space Station exist, modify the blink rate.
			int blinkRate = 0;
			while (coll.hasNext()) {
				temp = coll.next();
				if (temp instanceof SpaceStation) {
					blinkRate = ((SpaceStation) temp).getBlinkRate();
					if (blinkRate % gameTime == 0) {
						((SpaceStation) temp).setLight(false);
					} else {
						((SpaceStation) temp).setLight(true);
					}
				}
			}
		}

		coll = collection.getIterator();
		if (missileExist > 0) { // If Missile exists, modify missile fuel and remove it if zero.
			while (coll.hasNext()) {
				temp = coll.next();
				if (temp instanceof Missile) {
					if (((Missile) temp).getFuelLevel() > 0) {
						((Missile) temp).setFuelLevel(((Missile) temp).getFuelLevel() - 1);
					} else {
						collection.remove(temp);
						coll = collection.getIterator();
					}
				}
			}
		}

		coll = collection.getIterator();
		while (coll.hasNext()) { // Move every movable object in the game world.
			temp = coll.next();
			if (temp instanceof IMoveable) {
				((IMoveable) temp).move(tick);
			}
		}
		notifyObv();

		//==========================================================================
		// This will check to see if anything has collided after moving
		
		coll = collection.getIterator();
		while (coll.hasNext()) {
			temp = coll.next();
			coll2 = collection.getIterator();
			while (coll2.hasNext()) {
				GameObject temp2 = coll2.next();
				if (temp2 instanceof ICollider) {
					ICollider otherObj = (ICollider) temp2;
					if (otherObj != temp) {
						if (temp.collideWith(otherObj)) {
							if (!(isShipAndShipMissile(temp, temp2))) {
								temp.handleCollision(otherObj);
								coll = collection.getIterator();
								coll2 = collection.getIterator();
							}
						}
					}
				}
			}
		}
		notifyObv();
	}

	private boolean isShipAndShipMissile(GameObject objOne, GameObject objTwo){
		boolean result = false;
		if (objOne instanceof Missile) {
			if(objTwo instanceof PlayerShip) {
				if(((Missile)objOne).getShip().equals("Player Ship")) {
					result = true;
				}
			}
		} else if (objOne instanceof PlayerShip){
			if(objTwo instanceof Missile){
				if(((Missile)objTwo).getShip().equals("Player Ship")) {
					result = true;
				}
			}
		}
		return result;
	}

	public void pause() {
		pause = true;
		notifyObv();

	}

	public void play() {
		pause = false;
		
		GameCollection coll = this.getCollection();
		GameVectorIterator itterate = coll.getIterator();
		while (itterate.hasNext()) {
			GameObject temp = itterate.next(); 
			if (temp instanceof ISelectable) {
				((ISelectable) temp).setSelected(false);
			}
		}
		notifyObv();

	}

	// =================================================================
	// This will display the details of all objects in the game world.
	public void map() {

		GameVectorIterator coll = collection.getIterator();
		System.out.println("========================");
		while (coll.hasNext()) {
			System.out.println(coll.next().toString());
		}
		System.out.println("========================");
	}

	// =================================================================
	// Enable/Disable Sound

	public void soundOnOff() {

		if (soundOn) {
			soundOn = false;
			soundBG.pause();

		} else {
			soundOn = true;
			soundBG.play();
		}

		notifyObv();
	}

	public void newGame() {
		while (collection.getIterator().hasNext()) {
			collection.remove(collection.getIterator().next());
			soundBG.pause();
			notifyObv();
		}
		this.init();
	}

	// =================================================================
	// Map width/height setters
	public void setWidthHeight(int width, int height) {
		mWidth = width;
		mHeight = height;
	}

	public void setXY(int x, int y) {
		mapX = x;
		mapY = y;
	}

	// =================================================================
	// Proxy get methods

	public boolean getPaused() {
		return this.pause;
	}

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

	// ==========================================================================
	// Collision Methods

	public void setIsDead(GameObject ship) {
		this.isDead(ship);
	}

	private void missileHit(GameObject obj) {
		Missile missile = (Missile) obj;
		if (missile.getShip().equals("Player Ship")) {
			this.points = this.points + 10;
		}
	}

	public void setCollision(GameObject currObj, GameObject collideObj) {

		if (this.collectionExist(collideObj)) {
			if (collideObj instanceof PlayerShip) {
				this.setIsDead(collideObj);
			} else if (collideObj instanceof Missile) {
				this.missileHit(collideObj);
				collection.remove(collideObj);
			} else {
				collection.remove(collideObj);
				notifyObv();
			}
		}

		if (this.collectionExist(currObj)) {
			if (currObj instanceof PlayerShip) {
				this.setIsDead(currObj);
			} else if (currObj instanceof Missile) {
				this.missileHit(currObj);
				collection.remove(currObj);
			} else {
				collection.remove(currObj);
				notifyObv();
			}
		}

		if(soundOn) {
			soundHit = new soundHit("soundHit.wav");
			soundHit.play();
		}

	}

}
