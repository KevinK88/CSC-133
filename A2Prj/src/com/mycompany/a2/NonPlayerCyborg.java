package com.mycompany.a2;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
public class NonPlayerCyborg extends Cyborg{
	Random random = new Random();
	private IStrategy currStrategy;
	public NonPlayerCyborg()
	{
		super(ColorUtil.BLUE);
		this.setDamageLevel(0);
		this.setMaxDamageLevel(50);
		this.setSize(30); 
		this.setHeading(random.nextInt(360));
		this.setSpeed(random.nextInt(10));
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

	public void setStrategy(IStrategy s) 
	{
		currStrategy = s;
	}
	public String getStrategy() {
		if (currStrategy instanceof AttackStrategy) {
			return "Attach Strategy";
		}
		else
			return "Base Strategy";
	}
	public void invokeStrategy() 
	{
		currStrategy.apply();
	}
	 
}
