package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
public class NonPlayerCyborg extends Cyborg implements IDrawable{
	Random random = new Random();
	private IStrategy currStrategy;
	private boolean flag = false;
	public NonPlayerCyborg(GameWorld gw)
	{
		super(ColorUtil.BLUE,gw);
		this.setDamageLevel(0);
		this.setMaxDamageLevel(1000);
		this.setEnergyLevel(100000);
		super.setSize(30); 
		this.setHeading(random.nextInt(360));
		this.setSpeed(new Random().nextInt(3)+5);
		this.setLastBaseReached(1);
		
	}

	@Override
	public String toString() {
		return ("Non PlayerCyborg: loc=" + Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0+
				"  color= " + this.getColortoString()+
				"  speed= " + this.getSpeed()+
				"  heading= " + this.getHeading()+
				"  size= " + this.getSize()+
				"  strategy= "+ this.getStrategy()+
				"  damageLevel=" + this.getDamageLevel()+
				"  maxDamageLevel=" + this.getMaxDamageLevel()
				);
	}
	public void setSize() {}
	public void setStrategy(IStrategy s) 
	{
		currStrategy = s;
	}
	public boolean getStrategy() {
		if (currStrategy instanceof AttackStrategy)
			return true;
		else
			return false;
	}
	public void invokeStrategy() 
	{
		currStrategy.apply();
	}
	public boolean getFlag() {
		return flag;
	}public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int x = (int)this.getX()+ (int)pCmpRelPrnt.getX();
		int y = (int)this.getY()+(int)pCmpRelPrnt.getY();
		g.drawRect(x, y, 50, 50);

	}
	@Override
	  public void handleCollision(GameObjects object) {
			  if (this instanceof NonPlayerCyborg && object instanceof PlayerCyborg) {
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
							System.out.println("Game is over!!!");
							super.getGW().exit(2);
						}	
					}
					else {
						((PlayerCyborg)object).increaseDamageLevel();
						((NonPlayerCyborg)this).increaseDamageLevel();
						((PlayerCyborg)object).checkDamageLevel();
						((NonPlayerCyborg)this).checkDamageLevel();
						((PlayerCyborg)object).setColor(ColorUtil.rgb((int)(255*0.9)/((PlayerCyborg)object).getDamageLevel(), 200, 200));
						((NonPlayerCyborg)this).setColor(ColorUtil.rgb(255, 0, 255));
					}
				}
				else if (this instanceof NonPlayerCyborg && object instanceof NonPlayerCyborg) {
					if (((NonPlayerCyborg)object).isMaxDamageLevel() == true || ((NonPlayerCyborg)this).isMaxDamageLevel() == true) {

						if (((NonPlayerCyborg)object).isMaxDamageLevel() == true) {
							((NonPlayerCyborg)this).setColor(ColorUtil.rgb(255, 0, 255));
						}
						else if (((NonPlayerCyborg)this).isMaxDamageLevel() == true) {
							((NonPlayerCyborg)object).setColor(ColorUtil.rgb(255, 0, 255));
						}
					}
					
					else {
						((NonPlayerCyborg)object).increaseDamageLevel();
						((NonPlayerCyborg)this).increaseDamageLevel();
						((NonPlayerCyborg)object).setColor(ColorUtil.rgb((int)(255*0.9)/((NonPlayerCyborg)object).getDamageLevel(), 200, 200));
						((NonPlayerCyborg)this).setColor(ColorUtil.rgb((int)(255*0.9)/((NonPlayerCyborg)this).getDamageLevel(), 200, 200));
					}
				}
				else if (this instanceof NonPlayerCyborg && object instanceof Drone) {
					((NonPlayerCyborg)this).increaseDamageLevel();
					((NonPlayerCyborg)this).setColor(ColorUtil.rgb((int)(255*0.9)/((NonPlayerCyborg)this).getDamageLevel(), 200, 200));
				}
				
	  }
}
