package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class PlayerCyborg extends Cyborg implements ISteerable {
	

private int life;
private static PlayerCyborg player;

// This is for singleton Strategy
private PlayerCyborg() {
	super(ColorUtil.GRAY);
	this.setSize(30);
	this.setSpeed(0);
	this.setHeading(0);
	this.life = 3;
	this.setSteeringDirection(0);
	this.setMaximumSpeed(30);
	this.setEnergyConsumptionRate(1);
	this.setEnergyLevel(20);
	this.setDamageLevel(0);
	this.setLastBaseReached(1);
	this.setLastBaseReached(1);
}
	// This is for singleton pattern
	public static PlayerCyborg getPlayerCyborg() {
		if (player == null)
			player = new PlayerCyborg();
		return player;
	}

@Override
public void turnLeft() {
	int currheading = this.getSteeringDirection();
	if (currheading == 0) {
		currheading = currheading - 5;
		this.setSteeringDirection(currheading);
		if (this.getHeading() == 0 )
		{
			this.setHeading(360+currheading);
		}
		else {
			this.setHeading(this.getHeading() + currheading);
		}
		}
	else {
		if (currheading > -40)
		{
			currheading -=5;
			this.setSteeringDirection(currheading);
			if (this.getHeading() == 0 )
			{
				this.setHeading(360+currheading);
			}
			else {
				this.setHeading(this.getHeading() + currheading);
			}
		}
		else {
			System.out.println("Can only turn left max 40");
		}
	}
}

@Override
public void turnRight() {
	int currheading = this.getSteeringDirection();
	if (currheading == 0) {
		currheading = currheading + 5;
		this.setSteeringDirection(currheading);
		if (this.getHeading() == 0 )
		{
			this.setHeading(currheading);
		}
		else {
			this.setHeading(this.getHeading() + currheading);
		}
	}
	else {
		if (currheading < 40)
		{
			currheading +=5;
			this.setSteeringDirection(currheading);
			if (this.getHeading() == 0 )
			{
				this.setHeading(currheading);
			}
			else {
				this.setHeading(this.getHeading() + currheading);
			}
		}
		else {
			System.out.println("Can only turn left max 40");
		}
	}
	
}
public void decreaseSpeed() {
	int currSpeed = getSpeed();
	if (currSpeed > 0)
	{
		this.setSpeed(--currSpeed);
	}
	else {
		System.out.println("Your Cyborg is at the minimum speed. Cannot decrease anymore");
	}
}
public void increaseSpeed() {
	int currentSpeed = getSpeed();
	if (!isMaximumSpeed()) {
		this.setSpeed(++currentSpeed);
	}	
}
public boolean isMaximumSpeed() {
	if (this.getSpeed() >= this.getMaximumSpeed())
	{
		System.out.println("You cannot cross maximum speed");
		return true;
	}
		return false;
}
public boolean isMaxDamageLevel() {
	if (this.getDamageLevel() == this.getMaxDamageLevel())
	{
		return true;
	}
	return false;
}

public void checkDamageLevel() {
	if (this.getDamageLevel()>0 && this.getDamageLevel() < this.getMaxDamageLevel())
	{
		
		if (this.getSpeed() < this.getMaximumSpeed())
		{
			this.setMaximumSpeed(this.getMaxDamageLevel()-this.getDamageLevel());
		}
		else {
			this.setSpeed(this.getMaximumSpeed());
		}
	}
	else if (this.getDamageLevel() == this.getDamageLevel())
	{
		this.setSpeed(0);	
	}
	
}


public void resetCyborg(float x, float y) {
	this.setX(x);
	this.setY(y);
	this.setHeading(0);
	this.setMaximumSpeed(30);
	this.setEnergyConsumptionRate(1);
	this.setEnergyLevel(20);
	this.setDamageLevel(0);
	this.setMaxDamageLevel(30);
	this.life--;
}



@Override
public String toString() {
	return ("Cyborg: loc=" + Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0+
			"  color= " + this.getColortoString()+
			"  speed= " + this.getSpeed()+
			"  heading= " + this.getHeading()+
			"  size= " + this.getSize()+
			"  maxSpeed=" + this.getMaximumSpeed()+
			"  sterringDirection=" + this.getSteeringDirection()+
			"  energyLevel=" + this.getEnergyLevel()+
			"  damageLevel=" + this.getDamageLevel()+
			"  maxDamageLevel=" + this.getMaxDamageLevel()
			);
		}

		public void setLife(int x)
		{
			life = x;
		}
		public int getLife() {
			return life;
		}
		

	    
	    
}