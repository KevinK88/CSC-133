package com.mycompany.a1;
import java.lang.Math;
public  class Movable extends GameObjects{
	private int heading;
	private int speed;
	public Movable(int color) {
		super(color);
	}
	public Movable( int color, int size) {
		super(color, size);
		// TODO Auto-generated constructor stub
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setHeading(int heading) {
		this.heading = heading;
	}
	public int getHeading() {
		return heading;
	}
	public void move() {
		float radian = (90 - this.getHeading()) * (float)Math.PI / 180 ;
		float newX = this.getX() + (float)Math.cos(radian) * this.getSpeed();
		float newY = this.getY() + (float)Math.sin(radian) * this.getSpeed();
		this.setLocation(newX, newY);
	}
	public void checkBoundary() {
		
	}
}
