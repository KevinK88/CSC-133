package com.mycompany.a3;
import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

public abstract class GameObjects implements ICollider{
	private int size;
	private int color;
	private Point grid;
	private GameWorld gw;
	private int  MAX_POS_X = 1500;
	private int MIN_POS_X = 100;
	private int MAX_POS_Y = 1000;
	private int MIN_POS_Y = 100;
	private ArrayList<GameObjects> collisions;
	public GameObjects(int color,GameWorld newGW) {
		this.color = color;
		float x = (float)(new Random().nextInt(MAX_POS_X - MIN_POS_X) + MIN_POS_X); 
		float y = (float)(new Random().nextInt(MAX_POS_Y - MIN_POS_Y) + MIN_POS_Y); 
		this.gw = newGW;
		this.grid  = new Point(x,y);
		collisions = new ArrayList<GameObjects>();
	}
	
	public GameObjects(int color, int size,GameWorld newGW) {
		this.color = color;
		this.size = size;
		this.gw = newGW;
		float x = (float)(new Random().nextInt(MAX_POS_X - MIN_POS_X) + MIN_POS_X); 
		float y = (float)(new Random().nextInt(MAX_POS_Y - MIN_POS_Y) + MIN_POS_Y); 
		this.grid  = new Point(x,y);
		collisions = new ArrayList<GameObjects>();
	}

	public void setX(float x) {
		grid.setX(x);
	}
	public float getX() {
		return grid.getX();
	}
	public void setY(float y) {
		grid.setY(y);
	}
	public float getY() {
		return grid.getY();
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getColor() {
		return color;
	}
	public int getSize() {
		return size;
	}
	protected void setSize(int size) {
		this.size = size;
	}
	public void setLocation(float x, float y)
	{
		this.grid = new Point(x,y);
	}
	public Point getLocation() {
		return this.grid;
	}
	public String getColortoString(){
		return "["+ ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color)+"]";
	}
	// From the lecture to determine whether two objects collide with each other
	@Override
	public boolean collidesWith(GameObjects otherObject) {
		boolean result = false;
		double thisCenterX = this.getX() + (otherObject.size/2);
		double thisCenterY = this.getY()+ (otherObject.size/2);
		
		double otherCenterX = otherObject.getX();
		double otherCenterY = otherObject.getY();
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		

		double distBetweenCentersSqr = (dx * dx + dy * dy);
		int thisRadius= this.getSize() / 2;
		int otherRadius= otherObject.getSize() / 2;
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true;
		}
			return result;
		
	}

	public GameWorld getGW() { return gw; }
	public ArrayList<GameObjects> getObjects () {
		return collisions;
	}
}
