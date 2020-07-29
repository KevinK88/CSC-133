package com.mycompany.a3;
import java.lang.Math;
public abstract class Movable extends GameObjects{
	private int heading;
	private int speed;
	private boolean flag = false;
	public Movable(int color,GameWorld gw) {
		super(color,gw);
	}
	public Movable( int color, int size,GameWorld gw) {
		super(color, size,gw);
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setHeading(int newHeading) {
		while(newHeading >= 360.0) {
			newHeading -= 360.0;
		} 
		while(newHeading < 0.0) {
			newHeading += 360.0;
		}
		heading = newHeading;
		
	}
	public int getHeading() {
		return heading;
	}
	public boolean getFlag() {
		return flag;
	}public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void move() {
		float newX = this.getX() + (float)Math.cos( Math.toRadians(90-this.heading)  )* speed;
 		float newY = this.getY() + (float)Math.sin( Math.toRadians(90-this.heading)  )* speed;		
 		int offset = 0;
 		 if(this instanceof Drone) {
 			offset = 50;
 		}else if(this instanceof PlayerCyborg) {
 			offset = 50;
 		}else if(this instanceof NonPlayerCyborg) {
 			offset = 50;
 		}
 		
 		int orginalX = (int)MapView.getMapViewOrigin().getX();
 		int orginalY = (int)MapView.getMapViewOrigin().getY();
 		// Right wall 
			if(orginalX + newX + offset >=   MapView.getMapViewWidth() + orginalX && (heading != 0 || heading != 180)) 
			{
				setHeading(360-heading);
				if (this instanceof NonPlayerCyborg) {
					this.setFlag(true);
				}
			}
			
		// Left wall 
			if (this instanceof Drone) {
				if(orginalX + newX  <= orginalX + 20 && (heading != 0 || heading != 180)  ) {
					setHeading(360-heading);
				}
			}
			else {
				if(orginalX + newX  <= orginalX && (heading != 0 || heading != 180)  ) {
					setHeading(360-heading);
					if (this instanceof NonPlayerCyborg) {
						this.setFlag(true);
					}
				}
			}
			// Bottom wall
			if(orginalY + newY + offset >= orginalY + GameWorld.getGameHeight() ) {
				setHeading((360-heading+180)%360);
				if (this instanceof NonPlayerCyborg) {
					this.setFlag(true);
				}
			}		
			if (this instanceof Drone) {
			if(orginalY + newY  <= orginalY + 25) {
				setHeading((360-heading+180)%360);
			}
			}
			// Top wall
			else {
				if(orginalY + newY  <= orginalY ) {
					setHeading((360-heading+180)%360);
					if (this instanceof NonPlayerCyborg) {
						this.setFlag(true);
					}
				}
			}
		this.setLocation(newX, newY);

	}
}

