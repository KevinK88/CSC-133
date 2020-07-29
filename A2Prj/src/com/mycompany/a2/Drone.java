package com.mycompany.a2;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
public class Drone extends Movable  {
	Random random = new Random();
	public Drone () {
		super(ColorUtil.BLUE);
		final int MIN_SIZE = 10;
    	final int MAX_SIZE = 50;
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE); 
		super.setHeading(random.nextInt(360));
		super.setSpeed(random.nextInt(10));
	}
	
	@Override
	public String toString() {
		return ("Drone: loc= " + Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0 +
    			"  color= " + this.getColortoString() + 
    			"  size= " + this.getSize()+
    			"  heading="+ this.getHeading()+
    			"  speed= " + this.getSpeed()
    			);
	}
	
	public void setSize(int size) {}

	public void setColor(int color){}
	public void moveDrone() {
		this.setHeading(this.getHeading()+random.nextInt(5));

	}
	public void boundaryDrone() {
		if ((this.getSize() + this.getX()) > 1000 || (this.getX() + this.getSize() > 0)) {
			this.setHeading(random.nextInt(180));
			
		}
		if ((this.getSize() + this.getY()) > 1000 || (this.getSize() + this.getY()) < 0 ) {
			this.setHeading(random.nextInt(180));
		}
	}

}
