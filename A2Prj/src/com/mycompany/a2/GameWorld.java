package com.mycompany.a2;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;
public class GameWorld extends Observable  {
	private int timer;
	private GameWorldCollection gameObjects;
	private PlayerCyborg player;
	Random random = new Random();
	private boolean soundOn;
	public GameWorld(){}
	// Initialize the game
	public void init() {
		this.timer = 0;
		this.soundOn = false;
		gameObjects = new GameWorldCollection();
		addGameObjects();
		this.setChanged();
		this.notifyObservers(this);
	}
	 //Adding GameObjects to the GameWorld
	public void addGameObjects() {
		float x = 0, y=0;
		int baseObject = 9;
		int energyDroneObject = 3;
		gameObjects.add(PlayerCyborg.getPlayerCyborg());
		player = PlayerCyborg.getPlayerCyborg();
		for (int i = 1; i < baseObject; i++) 
			gameObjects.add(new Bases(i));
		for (int i = 0; i < energyDroneObject; i++) 
			gameObjects.add(new EnergyStation());

		for (int i = 0; i < energyDroneObject ; i++) 
			gameObjects.add(new Drone());
		
		for (int i = 0; i < 3; i++) {
			NonPlayerCyborg npc = new NonPlayerCyborg();
			int choice = random.nextInt(2);
			if (choice == 0) 
				npc.setStrategy(new  AttackStrategy(gameObjects,npc));
			else
				npc.setStrategy(new  BaseStrategy(gameObjects,npc));
			gameObjects.add(npc);
		}
		
		
		// Get x and y Base 1 location
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext())
		{
			GameObjects tempObject = itr.getNext();
			if(tempObject instanceof Bases)
			{
				if(((Bases)tempObject).getSequenceNumber() == 1) {
					 x = ((Bases)tempObject).getX();
					 y = ((Bases)tempObject).getY();
				}
			}
		}
		
		// Assign x,y to Cyborg and NonCyborg
		IIterator itr2 = gameObjects.getIterator();
		while(itr2.hasNext())
		{
			GameObjects tempObject_2 = itr2.getNext();
			if (tempObject_2 instanceof PlayerCyborg){
				((PlayerCyborg)tempObject_2).setX(x);
				((PlayerCyborg)tempObject_2).setY(y);
			}
			else if (tempObject_2 instanceof NonPlayerCyborg)
			{
				((NonPlayerCyborg)tempObject_2).setX(x+random.nextInt(100));
				((NonPlayerCyborg)tempObject_2).setY(y+random.nextInt(100));
			}

		}
		// Assign x,y to Cyborg and NonCyborg

		
//		IIterator itr3 = gameObjects.getIterator();
//		while(itr3.hasNext())
//		{
//			GameObjects tempObject_3 = itr3.getNext();
//			if (tempObject_3 instanceof NonPlayerCyborg){
//				((NonPlayerCyborg)tempObject_3).setStrategy();
//				((NonPlayerCyborg)tempObject_3).setY(y);
//			}
//			
//
//		}
		
		
		
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
		System.out.println("Cyborg has turned left!!!");
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
		System.out.println("Cyborg has turned right!!!");
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
	// Press 'c'
	public void collsionCyborg() {
		System.out.println("The collision between PlayerCyborg with NonPlayerCyborg has occured!!");
		
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()){
			GameObjects tempObject = itr.getNext();
			if(tempObject instanceof PlayerCyborg){
				if (((PlayerCyborg)tempObject).isMaxDamageLevel() == true) {
					System.out.println("You Cyborg is dead.");
					if (((PlayerCyborg)tempObject).getLife() != 0){
						int temp_last_base = ((PlayerCyborg) tempObject).getLastBaseReached();
						IIterator itr2 = gameObjects.getIterator();
						while(itr2.hasNext()){
							GameObjects tempObject_2 = itr2.getNext();
							if(tempObject_2 instanceof Bases) {
								if(temp_last_base == ((Bases)tempObject_2).getSequenceNumber()) {
									float base_x = ((Bases)tempObject_2).getX();
									float base_y = ((Bases)tempObject_2).getY();
									((PlayerCyborg) tempObject).resetCyborg(base_x,base_y);
								}
							}
						}
					}
					else {
						System.out.println("Game is over!!!");
						System.exit(0);
					}
				}
				else {
					((PlayerCyborg)tempObject).increaseDamageLevel();
					((PlayerCyborg)tempObject).checkDamageLevel();
					((PlayerCyborg)tempObject).setColor(ColorUtil.rgb((int)(255*0.9)/((PlayerCyborg)tempObject).getDamageLevel(), 255, 0));
					IIterator itr3 = gameObjects.getIterator();
					while(itr3.hasNext()){
						GameObjects tempObject_3 = itr3.getNext();
						if(tempObject_3 instanceof NonPlayerCyborg) {
							if (((NonPlayerCyborg)tempObject_3).getDamageLevel() < ((NonPlayerCyborg)tempObject_3).getMaxDamageLevel()){
								((NonPlayerCyborg)tempObject_3).increaseDamageLevel();
								break;
							}	
						}
					}
				}	
			}
		}

		this.setChanged();
		this.notifyObservers(this);
		}

	public void collisonBases(int c) {
		System.out.println("The collision between Cyborg with Base has occured!!!!");
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()){
		GameObjects tempObject = itr.getNext();
		if (tempObject instanceof PlayerCyborg ){
			int temp = ((PlayerCyborg) tempObject).getLastBaseReached();
			temp++;
			if (temp == c && c != 9) {
				//displayLastBaseReached = temp;
				System.out.println("The Cybord has reached the next base");
				((PlayerCyborg) tempObject).setLastBaseReached(c);
			}
			else if (temp == 9) {
				//displayLastBaseReached = 9;
				System.out.println("Game Over, you win! Total time:" + this.timer);
				System.exit(0);
			}
			else {
				System.out.println("Had passed or haven't reached yet");
			}
		}	
	}

		this.setChanged();
		this.notifyObservers(this);
	}
	// Press 'g'
	public void collisonDrone() {
		System.out.println("The collision between Cyborg and Drone has occured!!!");
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()){
			GameObjects tempObject = itr.getNext();
			if(tempObject instanceof PlayerCyborg){
				if (((PlayerCyborg)tempObject).isMaxDamageLevel() == true) {
					System.out.println("Your Cyborg is dead. ");
					if (((PlayerCyborg)tempObject).getLife() != 0){
						int temp_last_base = ((PlayerCyborg) tempObject).getLastBaseReached();
						IIterator itr2 = gameObjects.getIterator();
						while(itr2.hasNext()){
							GameObjects tempObject_2 = itr2.getNext();
							if(tempObject_2 instanceof Bases) {
								if(temp_last_base == ((Bases)tempObject_2).getSequenceNumber()) {
									float base_x = ((Bases)tempObject_2).getX();
									float base_y = ((Bases)tempObject_2).getY();
									((PlayerCyborg) tempObject).resetCyborg(base_x,base_y);
								}
							}
						}
					}
					else {
						System.out.println("Game is over!!!");
						System.exit(0);
					}
				}
				else {
					((PlayerCyborg)tempObject).increaseDamageLevel();
					//displayPlayerDamage = ((PlayerCyborg)tempObject).getDamageLevel();
					((PlayerCyborg)tempObject).checkDamageLevel();
					((PlayerCyborg)tempObject).setColor(ColorUtil.rgb((int)(255*0.9)/((PlayerCyborg)tempObject).getDamageLevel(), 255, 0));
				}
			}
		}	
		this.setChanged();
		this.notifyObservers(this);
	}
	// Press'e'
	public void collsionEnergyStation() {
		System.out.println("The collision between Cyborg and Energy Stations has occured");
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()){
			GameObjects tempObject = itr.getNext();
			if (tempObject instanceof PlayerCyborg) {
				IIterator itr_2 = gameObjects.getIterator();
				while(itr_2.hasNext()){
					GameObjects tempObject_2 = itr_2.getNext();
					if(tempObject_2 instanceof EnergyStation) {
						if(((EnergyStation)tempObject_2).getCapacity() !=0) {
							int temp = ((EnergyStation) tempObject_2).getCapacity();
							((PlayerCyborg) tempObject).setEnergyLevel(temp+((PlayerCyborg) tempObject).getEnergyLevel()); 
							//displayPlayerEnergy = ((PlayerCyborg) tempObject).getEnergyLevel();
							((EnergyStation) tempObject_2).setCapacity(0);
							((EnergyStation) tempObject_2).setColor(ColorUtil.rgb(100,100,100));
							gameObjects.add(new EnergyStation());
							break;
						}
					}
				}
			}
		}	

		this.setChanged();
		this.notifyObservers(this);
	}
	// Press 't'
	public void gameTick()
	{
		System.out.println("Game has ticked!!");
		timer++;
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()){
			GameObjects tempObject = itr.getNext();
			if (tempObject instanceof PlayerCyborg) {
				if (((PlayerCyborg) tempObject).getEnergyLevel() != 0  ) {
					((PlayerCyborg) tempObject).move();
					((PlayerCyborg) tempObject).setSteeringDirection(0);
					((PlayerCyborg) tempObject).energyLevelTick();
					
					IIterator itr3 = gameObjects.getIterator();
					while(itr3.hasNext()){
						GameObjects tempObject_3 = itr3.getNext();
						if (tempObject_3 instanceof Drone) {
							((Drone) tempObject_3).checkBoundary();
							((Drone) tempObject_3).moveDrone();
							((Drone) tempObject_3).move();
							}
						}
					IIterator itr4 = gameObjects.getIterator();
					while(itr4.hasNext()){
						GameObjects tempObject_4 = itr4.getNext();
						if (tempObject_4 instanceof NonPlayerCyborg) {
							if(((NonPlayerCyborg) tempObject_4).getDamageLevel()<((NonPlayerCyborg) tempObject_4).getMaxDamageLevel()) {
							((NonPlayerCyborg) tempObject_4).move();
							((NonPlayerCyborg) tempObject_4).invokeStrategy();
							}
						}
						}
				}
				else if (((PlayerCyborg) tempObject).getLife() != 0 ) {				
					System.out.println("You has lost 1 life");
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
								}
							}
						}
				}
				else {
					System.out.println("Game is over!!!");
					System.exit(0);
				}
	
		    
		}
	}
		this.setChanged();
		this.notifyObservers(this);
	}
	// Set Strategy for Non Player Cyborg
	public void changeStrategy() {
		IIterator iter = gameObjects.getIterator();
		while(iter.hasNext()) {
			GameObjects tempObject = iter.getNext();
			if (tempObject instanceof NonPlayerCyborg)
			{
				NonPlayerCyborg npc = (NonPlayerCyborg)tempObject;
				if (((NonPlayerCyborg)tempObject).getLastBaseReached()!=9){
					int temp = ((NonPlayerCyborg)tempObject).getLastBaseReached();
					temp++;
					((NonPlayerCyborg)tempObject).setLastBaseReached(temp);
					
					int strategyChoice = random.nextInt(2);
					if (strategyChoice == 0) {

						npc.setStrategy(new AttackStrategy(gameObjects,npc));
					}
					else {
						npc.setStrategy(new BaseStrategy(gameObjects,npc));
					}
				}
				else if (((NonPlayerCyborg)tempObject).getLastBaseReached() == 9) {
					System.out.println("Game is Over, you lose!!!!");
					System.exit(0);
				}
			/*	else {
					
					
					int strategyChoice = random.nextInt(2);
					if (strategyChoice == 0) {
						IIterator iter2 = gameObjects.getIterator();
						while(iter2.hasNext()) {
							GameObjects tempObject_2 = iter2.getNext();
							if (tempObject_2 instanceof PlayerCyborg)
							{
								cb = (PlayerCyborg)tempObject_2;
							}
						}
						npc.setStrategy(new AttackStrategy(cb,npc));
						break;
					}
					else {
						npc.setStrategy(new BaseStrategy(npc, gameObjects));
						break;
					}
			}*/
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
	// Press 'd'
	public void printCurrent() {
		System.out.println("Current status!!!");
		
		IIterator iter = gameObjects.getIterator();
		while(iter.hasNext()) {
			GameObjects tempObj = iter.getNext();
			if (tempObj instanceof PlayerCyborg) {
				int life = ((PlayerCyborg) tempObj).getLife() ;
				int timer = getTime();
				int lastBaseReach = ((PlayerCyborg) tempObj).getLastBaseReached();
				int currentEnergy = ((PlayerCyborg) tempObj).getEnergyLevel();
				int currentDamage = ((PlayerCyborg) tempObj).getDamageLevel();
				System.out.println("The life left is: " + life);
				System.out.println("The timer has elapsed is: " + timer);
				System.out.println("The last base reached is: " + lastBaseReach);
				System.out.println("The current energy level is: " + currentEnergy);
				System.out.println("The current damage is: " + currentDamage);
			}
		}
		


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
	
	public int getTime() {
		return this.timer;
}
	public void exit() {
		System.exit(0);
	}
}
	

