package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
public class Drone extends Movable implements IDrawable  {
	Random random = new Random();
	public Drone (GameWorld gw) {
		super(ColorUtil.BLUE,gw);
		final int MIN_SIZE = 10;
    	final int MAX_SIZE = 50;
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE); 
		this.setHeading(random.nextInt(360));
		this.setSpeed(new Random().nextInt(10-5)+3);
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

	public void draw(Graphics g, Point pCmpRelPrnt) {
		  g.setColor(ColorUtil.CYAN);
		  int x = (int)this.getX() + pCmpRelPrnt.getX();
		  int y = (int)this.getY() + pCmpRelPrnt.getY();
		  int[] xPoints = { x, (x - 20), (x + 20), x };
		  int[] yPoints = { (y + 30), (y - 30), (y- 30), (y + 30) };
		  int nPoints = 3;
		  g.drawPolygon(xPoints, yPoints, nPoints);
	}


	@Override
	public void handleCollision(GameObjects object) {
			if (this instanceof Drone && object instanceof PlayerCyborg) {
				if (((PlayerCyborg)object).isMaxDamageLevel() == true) {
					System.out.println("Your Cyborg is dead. ");
					if (((PlayerCyborg)object).getLife() != 0){
						super.getGW().exit(3);
						super.getGW().loseLifeSound();
						int temp_last_base = ((PlayerCyborg) object).getLastBaseReached();
						IIterator itr2 = super.getGW().getGameObjects().getIterator();
						while(itr2.hasNext()){
							GameObjects tempObject_2 = itr2.getNext();
							if(tempObject_2 instanceof Bases) {
								if(temp_last_base == ((Bases)tempObject_2).getSequenceNumber()) {
									float base_x = ((Bases)tempObject_2).getX();
									float base_y = ((Bases)tempObject_2).getY();
									((PlayerCyborg) object).resetCyborg(base_x,base_y);
								}
							}
						}
					}
					else {
						super.getGW().exit(2);
					}	
				}
				else {
					((PlayerCyborg)object).increaseDamageLevel();
					((PlayerCyborg)object).checkDamageLevel();
					((PlayerCyborg)object).setColor(ColorUtil.rgb((int)(255*0.9)/((PlayerCyborg)object).getDamageLevel(), 100, 100));
				}
			}
			else if (this instanceof Drone && object instanceof NonPlayerCyborg) {
				if (((NonPlayerCyborg)object).isMaxDamageLevel() == true) {
					((NonPlayerCyborg)object).setSpeed(0);
				}
				else {
					((NonPlayerCyborg)object).increaseDamageLevel();
					((NonPlayerCyborg)object).checkDamageLevel();
					((NonPlayerCyborg)object).setColor(ColorUtil.rgb((int)(255*0.9)/((NonPlayerCyborg)object).getDamageLevel(), 200, 200));
				}
			}
			
		
		
	}
}
