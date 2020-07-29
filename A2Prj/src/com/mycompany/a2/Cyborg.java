package com.mycompany.a2;
public abstract class Cyborg extends Movable{
    //Cybrog properties
	private int steeringDirection;
    private int maximumSpeed;
    private int energyLevel;
    private int energyConsumptionRate;
    private int damageLevel;
    private int lastBaseReached;
   // This one added for keeping track of the game
    private int maxDamageLevel;

	public Cyborg(int color){
		super(color);
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
	public void setSize(int size) {}	




    
    
}
