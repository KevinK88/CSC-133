package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;
public class Cyborg extends Movable implements ISteerable{
    private int steeringDirection;
    private int maximumSpeed;
    private int energyLevel;
    private int energyConsumptionRate;
    private int damageLevel;
    private int lastBaseReached;
    private int life;
    private int maxDamageLevel;
    private Bases base;
	public Cyborg( ) {
		super(ColorUtil.GRAY,30);
		super.setSpeed(0);
		super.setHeading(0);
		this.steeringDirection = 0;
		this.maximumSpeed= 30;
		this.energyConsumptionRate = 1;
		this.energyLevel = 20;
		this.damageLevel = 0;
		this.lastBaseReached = 1;
		life = 3;
		this.maxDamageLevel = 30;
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
	}
	public void increaseSpeed() {
		int currentSpeed = getSpeed();
		if (!isMaximumSpeed()) {
			this.setSpeed(++currentSpeed);
		}	
	}
	public boolean isMaximumSpeed() {
		if (this.getSpeed() >= maximumSpeed)
		{
			System.out.println("You cannot cross maximum speed");
			return true;
		}
			return false;
		
	}
	public boolean isMaxDamageLevel() {
		if (this.getDamageLevel() == maxDamageLevel)
		{
			return true;
		}
		return false;
	}
	public void increaseDamageLevel() {
		this.damageLevel++;
	}
	public void checkDamageLevel() {
		if(damageLevel > 0 && damageLevel < maxDamageLevel)
		{
			
			if (this.getSpeed() < this.getMaximumSpeed())
			{
				this.setMaximumSpeed(this.maxDamageLevel-this.damageLevel);
			}
			else {
				this.setSpeed(this.getMaximumSpeed());
			}
		}
		else if (damageLevel == maxDamageLevel)
		{
			this.setSpeed(0);	
		}
		
	}
	public void energyLevelTick() {
		this.setEnergyLevel(this.getEnergyLevel()- this.getEnergyConsumptionRate());
	}
	 

	
	public void resetCyborg(float x, float y) {
		this.setX(x);
		this.setY(y);
		this.setHeading(0);
		this.maximumSpeed= 30;
		this.energyConsumptionRate = 1;
		this.energyLevel = 20;
		this.damageLevel = 0;
		this.lastBaseReached = base.getSequenceNumber();
		this.maxDamageLevel = 50;
		life--;
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
				"  damageLevel=" + this.getDamageLevel()
				);
	}
	
	public int getMaxDamageLevel() {
		return this.maxDamageLevel;
	}
	
	
	public void setLife(int x)
	{
		life = x;
	}
	public int getLife() {
		return life;
	}
    public void setSteeringDirection(int steeringDirection) {
    	this.steeringDirection = steeringDirection;
    }
    public int getSteeringDirection() {
    	return steeringDirection;
    }
   
    public int getMaximumSpeed() {
		return maximumSpeed;
	}
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
		
	}
	public int getEnergyLevel() {
		return energyLevel;
	}
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	public int getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	public void setEnergyConsumptionRate(int energyConsumptionRate) {
		this.energyConsumptionRate = energyConsumptionRate;
	}
	public int getDamageLevel() {
		return damageLevel;
	}
	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}
	public void setSize(int size) {}	
    
    
}
