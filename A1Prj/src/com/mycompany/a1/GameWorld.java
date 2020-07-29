package com.mycompany.a1;
import java.util.Random;
import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
public class GameWorld {
	private int life;
	final private int gameWorldWidth = 1000;
	final private int gameWorldHeight = 1000;
	private int timer;
	private ArrayList<GameObjects> gameObjects;
	private int baseCount;
	private int energyStationCount;
	private int droneCount;
	private int cyborgCount;
	private boolean gameOver;
	Random random = new Random();
	public GameWorld(){}
	// Initialize the game
	public void init() {
		this.timer = 0;
		gameObjects = new ArrayList<GameObjects>();
		this.baseCount = 0;
		this.energyStationCount = 0;
		this.droneCount = 0;
		this.cyborgCount = 0;
		this.gameOver = false;
		addGameObjects();
	}
	 //Adding GameObjects to the GameWorld
	public void addGameObjects() {
		int baseObject = 4 + random.nextInt(5);
		int energyDroneObject = 2+ random.nextInt(5);
		
		for (int i = 1; i < baseObject; i++) {
			gameObjects.add(new Bases(i));
			 baseCount++;
		}
		for (int i = 0; i < energyDroneObject; i++) {
			gameObjects.add(new EnergyStation());
			energyStationCount++;
		}
		for (int i = 0; i < energyDroneObject ; i++) {
			gameObjects.add(new Drone());
			droneCount++;
		}
		gameObjects.add(new Cyborg());
		cyborgCount++;
	}
	// Game Methods
	// Press 'a'
	public void increaseSpeed() {
		System.out.println("Cyborg speeds up!!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				((Cyborg) gameObjects.get(i)).increaseSpeed();
			}
		}
	}
	// Press 'b'
	public void decreaseSpeed() {
		System.out.println("Cyborg speeds down!!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				((Cyborg) gameObjects.get(i)).decreaseSpeed();
			}
		}
	}
	// Press'l'
	public void turnLeftCyborg() {
		System.out.println("Cyborg has turned left!!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				((Cyborg) gameObjects.get(i)).turnLeft();
			}

		}
	}
	// Press 'r'
	public void turnRightCyborg() {
		System.out.println("Cyborg has turned right!!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				((Cyborg) gameObjects.get(i)).turnRight();
			}
		}
	}
	// Press 'c'
	public void collsionCyborg() {
		System.out.println("The collision between Cyborg with Cyborg has occured!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				if (((Cyborg) gameObjects.get(i)).isMaxDamageLevel() == true) {
					System.out.println("You Cyborg is dead. ");
					if (((Cyborg) gameObjects.get(i)).getLife() != 0) {
						int temp_last_base = ((Cyborg) gameObjects.get(i)).getLastBaseReached();
						for (int j = 0; j < gameObjects.size(); j++)
						{
							if (gameObjects.get(j) instanceof Bases)
							{
								if (temp_last_base == (((Bases) gameObjects.get(j)).getSequenceNumber()))
								{
									float base_x = (((Bases) gameObjects.get(j)).getX());
									float base_y = (((Bases) gameObjects.get(j)).getY());
									((Cyborg) gameObjects.get(i)).resetCyborg(base_x,base_y);
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
					((Cyborg) gameObjects.get(i)).increaseDamageLevel();
					((Cyborg) gameObjects.get(i)).checkDamageLevel();
					((Cyborg) gameObjects.get(i)).setColor(ColorUtil.rgb(255, 102, 102));
					}
			}
			}
			
		}

	public void collisonBases(int c) {
		System.out.println("The collision between Cyborg with Base has occured!!!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				int temp = ((Cyborg) gameObjects.get(i)).getLastBaseReached();
				temp++;
				if (temp == c) {
					System.out.println("The Cybord has reached the next base");
					((Cyborg) gameObjects.get(i)).setLastBaseReached(c);
				}
				else {
					System.out.println("Had passed or haven't reached yet");
				}
				if (temp == 9)
				{
					System.out.println("You won the game!!!!");
					System.exit(0);
				}
			}
		}
	}
	// Press 'g'
	public void collisonDrone() {
		System.out.println("The collision between Cyborg and Drone has occured!!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				if (((Cyborg) gameObjects.get(i)).isMaxDamageLevel() == true) {
					System.out.println("You Cyborg is dead. ");
					if (((Cyborg) gameObjects.get(i)).getLife() != 0) {
						int temp_last_base = ((Cyborg) gameObjects.get(i)).getLastBaseReached();
						for (int j = 0; j < gameObjects.size(); j++)
						{
							if (gameObjects.get(j) instanceof Bases)
							{
								if (temp_last_base == (((Bases) gameObjects.get(j)).getSequenceNumber()))
								{
									float base_x = (((Bases) gameObjects.get(j)).getX());
									float base_y = (((Bases) gameObjects.get(j)).getY());
									((Cyborg) gameObjects.get(i)).resetCyborg(base_x,base_y);
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
					((Cyborg) gameObjects.get(i)).increaseDamageLevel();
					((Cyborg) gameObjects.get(i)).checkDamageLevel();
					((Cyborg) gameObjects.get(i)).setColor(ColorUtil.rgb(255, 102, 102));
					}
			}
			}
	}
	// Press'e'
	public void collsionEnergyStation() {
		System.out.println("The collision between Cyborg and Energy Stations has occured");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			
			if (gameObjects.get(i) instanceof Cyborg)
			{
				for(int j = 0; j < gameObjects.size(); j++)
				{
					if (gameObjects.get(j) instanceof EnergyStation) {
						if (((EnergyStation) gameObjects.get(j)).getCapacity() !=0)
						{
							int temp = ((EnergyStation) gameObjects.get(j)).getCapacity();
						((Cyborg) gameObjects.get(i)).setEnergyLevel(temp);  
						((EnergyStation) gameObjects.get(j)).setCapacity(0);
						break;
						}
					}
				}
			}
		}
	}
	// Press 't'
	public void gameTick()
	{
		System.out.println("Game has ticked!!");
		timer++;
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				//if (((Cyborg) gameObjects.get(i)).getEnergyLevel() != 0 && ((Cyborg) gameObjects.get(i)).isMaxDamageLevel() == false)
				if (((Cyborg) gameObjects.get(i)).getEnergyLevel() != 0)
				{
					((Cyborg) gameObjects.get(i)).move();
					((Cyborg) gameObjects.get(i)).setSteeringDirection(0);
					((Cyborg) gameObjects.get(i)).energyLevelTick();
				}
				else{
					System.out.println("You has lost 1 life");
					int temp_last_base = ((Cyborg) gameObjects.get(i)).getLastBaseReached();
					for (int j = 0; j < gameObjects.size(); j++)
					{
						if (gameObjects.get(i) instanceof Bases)
						{
							if (temp_last_base == (((Bases) gameObjects.get(i)).getSequenceNumber()))
							{
								float base_x = (((Bases) gameObjects.get(i)).getX());
								float base_y = (((Bases) gameObjects.get(i)).getY());
								((Cyborg) gameObjects.get(i)).resetCyborg(base_x,base_y);
							}
						}
					}

				}
			}
			if (gameObjects.get(i) instanceof Drone)
			{
				((Drone) gameObjects.get(i)).checkBoundary();
				((Drone) gameObjects.get(i)).move();
			}
		}
	}
	// Press 'd'
	public void printMap() {
		System.out.println("Map is printed");
		for (int i = 0; i < gameObjects.size(); i++) {
			System.out.println(gameObjects.get(i).toString());
		}
	}
	// Press 'd'
	public void printCurrent() {
		System.out.println("Current status!!!");
		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i) instanceof Cyborg)
			{
				int life = ((Cyborg) gameObjects.get(i)).getLife();
				int timer = getTime();
				int lastBaseReach = ((Cyborg) gameObjects.get(i)).getLastBaseReached();
				int currentEnergy = ((Cyborg) gameObjects.get(i)).getEnergyLevel();
				int currentDamage = ((Cyborg) gameObjects.get(i)).getDamageLevel();
				System.out.println("The life left is: " + life);
				System.out.println("The timer has elapsed is: " + timer);
				System.out.println("The last base reached is: " + lastBaseReach);
				System.out.println("The current energy level is: " + currentEnergy);
				System.out.println("The current damage is: " + currentDamage);
			}
		}
	}
	// Press 'x' 
	public int getTime() {
		return this.timer;
}
	public void exit() {
		System.exit(0);
	}
}
	

