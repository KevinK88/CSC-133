package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
public class EnergyStation extends Fixed implements IDrawable{
	Random random = new Random();
    private int capacity;
    private GameWorld gw;
    public EnergyStation(GameWorld gw) {
    	super(ColorUtil.GRAY,gw);
    	final int MIN_SIZE = 50;
    	final int MAX_SIZE = 100;
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE); 
    	this.capacity = this.getSize();
    	this.gw = gw;
    }

    public String toString() {
    	return ("EnergyStation: loc= " + Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0 +
    			"  color= " + this.getColortoString() + 
    			"  size= " + this.getSize()+
    			"  capacity="+ this.getCapacity()
    			);
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setSize(int size){
    	System.out.println("Size cannot be changed");
    }
    
    public void setColor(int color){
    	super.setColor(color);
    }
   
    public boolean checkEnergyStation()
    {
        if (capacity == 0)
            return false;
        else
            return true;
    }
 
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int stringX = (int)Math.round(this.getX() +15) + pCmpRelPrnt.getX();
		int stringY = (int)Math.round(this.getY() +10) + pCmpRelPrnt.getY();
		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX() ;
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();	
		if (super.isSelected())
			g.drawArc(xLoc, yLoc, this.getSize(),this.getSize(), 0, 360);
		else
			g.fillArc(xLoc, yLoc, this.getSize(),this.getSize(), 0, 360);
		g.setColor(ColorUtil.rgb(255, 0, 0));
		g.drawString("" + this.capacity, stringX, stringY);
		
	}
	@Override
	  public void handleCollision(GameObjects object) {
		if (this instanceof EnergyStation && object instanceof Cyborg)  {
			if(this.getCapacity() !=0) {
				int temp = this.getCapacity();
				((Cyborg)object).setEnergyLevel(temp+((Cyborg)object).getEnergyLevel());
				this.setCapacity(0);
				this.setColor(ColorUtil.rgb(255,240,240));
				super.getGW().addObject();
			}
		}
	  }
}
