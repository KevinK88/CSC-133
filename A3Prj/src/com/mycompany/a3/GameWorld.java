package com.mycompany.a3;
import java.util.Random;
import java.util.Observable;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
public class GameWorld extends Observable  {
	private int timer;
	private GameWorldCollection gameObjects;
	private static int gameHeight = 1000;
	private static int gameWidth = 1000;
	private int countNPC = 0;
	Random random = new Random();
	private boolean pause;
	private boolean position;
	private boolean soundOn;
	private Sound baseSound;
	private Sound energySound;
	private Sound droneSound;
	private Sound npcSound;
	private Sound lifeSound;
	private BGSound bgSound;
	// Initialize the game
	public void init() {
		this.timer = 0;
		pause = false;
		this.soundOn = false;
		gameObjects = new GameWorldCollection();
		position = false;
		addGameObjects();
		this.setChanged();
		this.notifyObservers(this);
	}
	 //Adding GameObjects to the GameWorld
	public void addGameObjects() {
		int baseObject = 10;
		int energyDroneObject = 3;
		gameObjects.add(PlayerCyborg.getPlayerCyborg(this));

		for (int i = 1; i < baseObject; i++) 
			gameObjects.add(new Bases(i,this));
		for (int i = 0; i < energyDroneObject ; i++) 
			gameObjects.add(new Drone(this));
		
		for (int i = 0; i < energyDroneObject; i++) 
			gameObjects.add(new EnergyStation(this));
		for (int i = 0; i < 3; i++) {
			NonPlayerCyborg npc = new NonPlayerCyborg(this);
			if (i == 0 )
				npc.setStrategy(new  AttackStrategy(gameObjects,npc));
			else
				npc.setStrategy(new  BaseStrategy(gameObjects,npc));
		gameObjects.add(npc);
	}
		
		

}
	// Game Methods
	// Press 'a'
	public void increaseSpeed() {
//		System.out.println("Cyborg speeds up!!!");
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext())
		{
			GameObjects tempObject = itr.getNext();
			if(tempObject instanceof PlayerCyborg)
			{
				((PlayerCyborg)tempObject).increaseSpeed();
			}
		}
		this.setChanged();
		this.notifyObservers(this);

	}
	// Press 'b'
	public void decreaseSpeed() {
		System.out.println("Cyborg speeds down!!!");
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext())
		{
			GameObjects tempObject = itr.getNext();
			if(tempObject instanceof PlayerCyborg)
			{
				((PlayerCyborg)tempObject).decreaseSpeed();;
			}
		}
		this.setChanged();
		this.notifyObservers(this);

	}
	// Press'l'
	public void turnLeftCyborg() {
//		System.out.println("Cyborg has turned left!!!");
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext())
		{
			GameObjects tempObject = itr.getNext();
			if(tempObject instanceof PlayerCyborg)
			{
				((PlayerCyborg)tempObject).turnLeft();
			}
		}
		this.setChanged();
		this.notifyObservers(this);

	}
	// Press 'r'
	public void turnRightCyborg() {
//		System.out.println("Cyborg has turned right!!!");
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext())
		{
			GameObjects tempObject = itr.getNext();
			if(tempObject instanceof PlayerCyborg)
			{
				((PlayerCyborg)tempObject).turnRight();
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);

	}
	// Press 't'
	public void gameTick()
	{
		timer++;
		
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()){
			GameObjects tempObject = itr.getNext();
			if (tempObject instanceof PlayerCyborg) {
				if (((PlayerCyborg) tempObject).getEnergyLevel() >= 0 && ((PlayerCyborg) tempObject).getDamageLevel()< ((PlayerCyborg) tempObject).getMaxDamageLevel() ) {
					((PlayerCyborg) tempObject).move();
					((PlayerCyborg) tempObject).energyLevelTick();
					IIterator itr3 = gameObjects.getIterator();
					while(itr3.hasNext()){
						GameObjects tempObject_3 = itr3.getNext();
						if (tempObject_3 instanceof Drone) {
							((Drone) tempObject_3).move();
							}
						if (tempObject_3 instanceof NonPlayerCyborg) {
							if(((NonPlayerCyborg) tempObject_3).getDamageLevel()<((NonPlayerCyborg) tempObject_3).getMaxDamageLevel()) {
							((NonPlayerCyborg) tempObject_3).move();
							if (((NonPlayerCyborg) tempObject_3).getFlag() == false) {
							((NonPlayerCyborg) tempObject_3).invokeStrategy();
							}
							if (((NonPlayerCyborg) tempObject_3).getFlag() == true )
							countNPC++;
								if (countNPC == 300) {
									countNPC = 0;
									((NonPlayerCyborg) tempObject_3).setFlag(false);
								}
							}
						}
					}
				}
				else if (((PlayerCyborg) tempObject).getLife() != 0 ) {				
					System.out.println("You has lost 1 life");
					exit(3);
					int temp_last_base = ((PlayerCyborg) tempObject).getLastBaseReached();
					IIterator itr_2 = gameObjects.getIterator();
					while(itr_2.hasNext()){
						GameObjects tempObject_2 = itr_2.getNext();
						if (tempObject_2 instanceof Bases)
							{
								if (temp_last_base == (((Bases) tempObject_2).getSequenceNumber()))
								{
									float base_x = (((Bases) tempObject_2).getX());
									float base_y = (((Bases) tempObject_2).getY());
									((PlayerCyborg) tempObject).resetCyborg(base_x,base_y);
										if (getSound())
											lifeSound.play();
								}
							}
						}
				}
				else {
					exit(2);
				}
				
		    
		}
	}
		checkCollision();
		if (getSound())
			bgSound.play();
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//Check collision
	public void checkCollision() {
		IIterator iter = gameObjects.getIterator();
		while(iter.hasNext()) {
			GameObjects tempObject = iter.getNext();
			IIterator iter2 = gameObjects.getIterator();
			while(iter2.hasNext()) {
				GameObjects tempObject2 = iter2.getNext();
				if (tempObject!=tempObject2) {
					if(tempObject.collidesWith(tempObject2)) {
						if (tempObject instanceof PlayerCyborg && tempObject2 instanceof Drone) {
							if (getSound())
								droneSound.play();
						}
						else if (tempObject instanceof PlayerCyborg && tempObject2 instanceof NonPlayerCyborg) {
							if (getSound())
								npcSound.play();
						}
						else if (tempObject instanceof PlayerCyborg && tempObject2 instanceof Bases) {
							if (getSound())
								baseSound.play();
						}
						else if (tempObject instanceof PlayerCyborg && tempObject2 instanceof EnergyStation) {
							if (getSound())
								energySound.play();
						}
						tempObject.handleCollision(tempObject2);
					}
				}
			}
			}
		
	}
	
	// Set Strategy for Non Player Cyborg
	public void changeStrategy() {
		IIterator iter = gameObjects.getIterator();
		while(iter.hasNext()) {
			GameObjects tempObject = iter.getNext();
			if (tempObject instanceof NonPlayerCyborg)
			{
				NonPlayerCyborg npc = (NonPlayerCyborg)tempObject;
				if (npc.getStrategy() == true)
					npc.setStrategy(new BaseStrategy(gameObjects,npc));
				else
					npc.setStrategy(new AttackStrategy(gameObjects,npc));
			}	
			}

		this.setChanged();
		this.notifyObservers(this);
}
	// Set Sound
	public void setSound(boolean s) {
		this.soundOn = s;
		this.setChanged();
		this.notifyObservers(this);
	}
	public boolean getSound() {
		return soundOn;
	}
	// Press 'm'
	public void printMap() {
		System.out.println("Map is printed");
		IIterator iter = gameObjects.getIterator();
		while(iter.hasNext()) {
			System.out.println(((GameObjects)iter.getNext()).toString());
		}
		System.out.println("---------------------------");
	}


	// Press 'x' 
	// Get life from GameWorld for ScoreView
	public GameWorldCollection getCollection() {
		return gameObjects;
	}

	public int getLife() {
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()) {
			GameObjects gameObject = (GameObjects) itr.getNext();
			if (gameObject instanceof PlayerCyborg)
			{
				return ((PlayerCyborg)gameObject).getLife();
			}
		}
		return 0;

	}
//	// Get energyLevel from GameWorld for ScoreView
	public int getEnergyLevel() {
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()) {
			GameObjects gameObject = (GameObjects) itr.getNext();
			if (gameObject instanceof PlayerCyborg)
			{
				return ((PlayerCyborg)gameObject).getEnergyLevel();
			}
		}
		return 0;

	}
//	
//	// Get damageLevel from GameWorld for ScoreView
	public int getDamageLevel() {
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()) {
			GameObjects gameObject = (GameObjects) itr.getNext();
			if (gameObject instanceof PlayerCyborg)
			{
				return ((PlayerCyborg)gameObject).getDamageLevel();
			}
		}
		return 0;

	}
//	// Get lastBaseReached from GameWorld for ScoreView
	public int getLastBaseReached() {
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()) {
			GameObjects gameObject = (GameObjects) itr.getNext();
			if (gameObject instanceof PlayerCyborg)
			{
				return ((PlayerCyborg)gameObject).getLastBaseReached();
			}
		}
		return 0;

	}
	
	// Processing Fixed Objects when we click on that
	public void click(Point clickPoint, Point originalPoint) {
		IIterator tempObject = gameObjects.getIterator();
		while(tempObject.hasNext()) {
			if(tempObject.getNext() instanceof Fixed) {
				Fixed temp = (Fixed)tempObject.getCurrent();
				if(position && temp.isSelected()) {
					int newX = clickPoint.getX() - originalPoint.getX();
					int newY = clickPoint.getY() - originalPoint.getY();
					temp.setLocation(newX, newY);
					position = false;
					temp.setSelected(false);
				} else {	
					if(temp.contains(clickPoint, originalPoint)) 
						temp.setSelected(true);
					 else 
						temp.setSelected(false);
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void addObject() {
		gameObjects.add(new EnergyStation(this));
	}
	public void loseLifeSound() {
		if (getSound())
			lifeSound.play();
	}
	public GameWorldCollection getGameObjects() {
		return gameObjects;
	}
	public static int getGameHeight() {
		return gameHeight; 
	}
	public static int getGameWidth() {
		return gameWidth;
	}
	public void setPause(boolean newPause) {
		pause = newPause;
	}
	public boolean getPause() {
		return pause;
	}
	public void turnOnPosition() {
		if (position == true)
			position = false;
		else
			position = true;
	}
	public void exit(int number) {
		Command cOk = new Command("Ok");
		Command cOkLife = new Command("Ok");
		Command answer = new Command("");
		switch(number) {
		case 0:
			answer = Dialog.show("Congratulations!!!",  "You Win with the time is: " + this.getTime(), cOk);
			break;
		case 1:
			answer = Dialog.show("You have lost the game!!!!",  "Another cyborg has reached the last base!", cOk);
			break;
		case 2:
			answer = Dialog.show("You have lost the game!!!!",  "You Cyborg are out of lives!!!", cOk);
			break;
		case 3: 
			Dialog.show("ALERT!!!!",  "You have just lost 1 live !!!", cOkLife);
			break;
		}
		if(answer.equals(cOk)) {
			System.exit(0);
		}
	}
	
	public void disablePosition() {
		position = false;
	}
	public void createSound() {
		baseSound = new Sound("base.wav");
		droneSound = new Sound("drone.wav");
		energySound = new Sound("energy.wav");
		npcSound = new Sound("npc.wav");
		lifeSound = new Sound("life.wav");
		bgSound = new BGSound("background.wav");
	}
	public static void setGameWidth(int w) { gameWidth = w; }
	public static void setGameHeight(int h) { gameHeight = h; }
	public void turnOffSound() {
		bgSound.pause();
	}
	public void turnOnSound() {
		if(getSound())
			bgSound.play();
	}
	public int getTime() {
		return this.timer/100;
}

}
	

