package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerCyborg extends Cyborg implements IDrawable {
		
	
	private int life;
	private static PlayerCyborg player;
	
	// This is for singleton Strategy
	private PlayerCyborg(GameWorld gw) {
		super(ColorUtil.YELLOW,gw);
		super.setSize(30);
		this.setSpeed(0);
		this.setHeading(0);
		this.life = 3;
		this.setSteeringDirection(0);
		this.setMaximumSpeed(20);
		this.setEnergyConsumptionRate(1);
		this.setEnergyLevel(2000);
		this.setDamageLevel(0);
		this.setLastBaseReached(1);
		this.setMaxDamageLevel(500);
	}
		// This is for singleton pattern
		public static PlayerCyborg getPlayerCyborg(GameWorld gw) {
			if (player == null)
				player = new PlayerCyborg(gw);
			return player;
		}
	
	
	public boolean isMaximumSpeed() {
		if (this.getSpeed() >= this.getMaximumSpeed())
		{
			System.out.println("You cannot cross maximum speed");
			return true;
		}
			return false;
	}
	public void resetCyborg(float x, float y) {
		this.setX(x);
		this.setY(y);
		this.setColor(ColorUtil.YELLOW);
		this.setHeading(0);
		this.setMaximumSpeed(20);
		this.setEnergyConsumptionRate(1);
		this.setEnergyLevel(2000);
		this.setDamageLevel(0);
		this.setMaxDamageLevel(500);
		this.life--;
		this.setSpeed(2);
	}
	
	
	public void setSize() {}
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
	@Override 
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int x =  (int)this.getX()+ (int)pCmpRelPrnt.getX();
		int y =   (int)this.getY()+(int)pCmpRelPrnt.getY();
		g.fillRect(x, y, this.getSize(), this.getSize());
		double line = Math.toRadians(90 - super.getHeading());
		double steerX = ((Math.cos(line) * 50) + this.getX() + pCmpRelPrnt.getX());
		double steerY = ((Math.sin(line) * 50) + this.getY() + pCmpRelPrnt.getY());
		g.setColor(ColorUtil.BLACK);
		g.drawLine((int)this.getX() + pCmpRelPrnt.getX(),(int)this.getY() + 10 + pCmpRelPrnt.getY(), (int)steerX, (int)steerY);
	}
	@Override
	public void handleCollision(GameObjects object) {
		  
	}
	    
	    
}