package com.mycompany.a1;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

public abstract class GameObjects {
	private int size;
	private int color;
	private Point grid;

	public GameObjects(int color) {
		this.color = color;
		Random rnd = new Random();
		float x = (float)rnd.nextDouble() *  1000;
		float y = (float)rnd.nextDouble() * 1000;
		this.grid  = new Point(x,y);
	}
	
	public GameObjects(int color, int size) {
		this.color = color;
		this.size = size;
		Random rnd = new Random();
		float x = (float)rnd.nextDouble() *  1000;
		float y = (float)rnd.nextDouble() * 1000;
		this.grid  = new Point(x,y);
		
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
	public void setSize(int size) {
		this.size = size;
	}
	public void setLocation(float x, float y)
	{
		this.grid = new Point(x,y);
	}
	public String getColortoString(){
		return "["+ ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color)+"]";
	}

}
