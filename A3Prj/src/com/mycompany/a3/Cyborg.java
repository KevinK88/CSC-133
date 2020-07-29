package com.mycompany.a3;
public abstract class Cyborg extends Movable implements ISteerable{
    //Cybrog properties
	private int steeringDirection;
    private int maximumSpeed;
    private int energyLevel;
    private double energyConsumptionRate;
    private int damageLevel;
    private int lastBaseReached; 

   // This one added for keeping track of the game
    private int maxDamageLevel;

	public Cyborg(int color,GameWorld gw){
		super(color,gw);
	}
 public  void setSteeringDirection(int steeringDirection)
    {
    	this.steeringDirection = steeringDirection;
    }
    public  int getSteeringDirection()
    {
    	return steeringDirection;
    }

  public  int getMaximumSpeed() 
  	{
		return maximumSpeed;
  	}
	public void setMaximumSpeed(int maximumSpeed)
	{
		this.maximumSpeed = maximumSpeed;
	}
	public int getEnergyLevel() {
		return energyLevel;
	}
	public void setEnergyLevel(double energyLevel) {
		this.energyLevel = (int)Math.round(energyLevel);
	}
	public double getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	public void setEnergyConsumptionRate(double energyConsumptionRate) {
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
	public void setMaxDamageLevel(int maxDamageLevel) {
		this.maxDamageLevel = maxDamageLevel;
	}
	public int getMaxDamageLevel() {
		return this.maxDamageLevel;
	}
	public void energyLevelTick() {
		this.setEnergyLevel(this.getEnergyLevel()- this.getEnergyConsumptionRate());
	}
	public void increaseDamageLevel() {
		int temp = this.getDamageLevel() + 1;
		this.setDamageLevel(temp);
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
		else if (this.getDamageLevel() == this.getMaxDamageLevel())
		{
			this.setSpeed(0);	
		}
		
	}
	@Override
	public void turnRight() {
		this.setSteeringDirection(this.getSteeringDirection() - 5);
		if (this.getSteeringDirection() < -40)
			this.setSteeringDirection(-40);
		this.setHeading(this.getHeading() + this.getSteeringDirection());
			
	}
	@Override
	public void turnLeft() {
		this.setSteeringDirection(this.getSteeringDirection() +5 );
		if (this.getSteeringDirection() > 40)
			this.setSteeringDirection(40);
		super.setHeading(this.getSteeringDirection()+this.getHeading());
	}
	public void decreaseSpeed() {
		int currSpeed = getSpeed();
		if (currSpeed > 0)
		{
			this.setSpeed(--currSpeed);
		}
		else {

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
			return true;
		}
			return false;
	}

}
