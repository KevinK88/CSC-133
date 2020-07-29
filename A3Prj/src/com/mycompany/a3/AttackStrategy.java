package com.mycompany.a3;
import com.codename1.util.MathUtil;
import java.lang.Math;
import com.codename1.charts.models.Point;
public class AttackStrategy implements IStrategy {
	private PlayerCyborg cb;
	private NonPlayerCyborg npc;	
	private int defaultSteer = 0;
	private double newSteer = 0;
	private static int QUARTER = 90;
	private static int HALF = 180;
	// Follow the PlayerCybog
	public AttackStrategy(GameWorldCollection gameObjects, NonPlayerCyborg npc) 
	{
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()) {
			GameObjects temp = (GameObjects) itr.getNext();
			if(temp instanceof PlayerCyborg) {
					cb = ((PlayerCyborg)temp);
					break;
				}
			}				
		this.npc = npc;
	}
	// Determine the location and angle of the target
	@Override
	public void apply() {		
		double dx = Math.abs(cb.getX() - npc.getX());
		double dy = Math.abs(cb.getY() - npc.getY());
		double tempDSteer; 
		int tempSteer;
		tempDSteer = Math.toDegrees(MathUtil.atan(dy/dx));
		tempSteer = (int)MathUtil.floor(Math.toDegrees(MathUtil.atan(dy/dx)));
		if(npc.getY() > cb.getY()&& cb.getX() > npc.getX()) { 
			tempDSteer += QUARTER;
			tempSteer += QUARTER;
		}
		else if (npc.getY() > cb.getY() && cb.getX() < npc.getX()){
			tempDSteer = HALF - tempDSteer;
			tempSteer = HALF - (int) (2*tempDSteer);;
		}
		else if(npc.getY() < cb.getY() && cb.getX() > npc.getX()) {
			tempDSteer = QUARTER - tempDSteer;
			tempSteer  = QUARTER - tempSteer; 
		}
		else if (npc.getY() < cb.getY() && cb.getX() < npc.getX())  { 
			tempDSteer = (QUARTER - tempDSteer)*-1;
			tempSteer = (QUARTER - tempSteer)*-1;		
		}
		else { 
			if(npc.getY()< cb.getY()) { 
				tempDSteer = HALF;
				tempSteer = HALF;
			}
		}
		if(defaultSteer == 0) {
			defaultSteer = tempSteer;
			newSteer = tempDSteer;
		}
		else if (Math.abs(tempDSteer - newSteer) <= 10) { 
			tempSteer = 0; 
			npc.setHeading((int)tempDSteer); 
		}else { 
			defaultSteer = tempSteer;
			newSteer = tempDSteer;
		}
		npc.setSteeringDirection(tempSteer);

		double distance = Math.sqrt(dx*dx+dy*dy);
		if(distance <= 50) {
				npc.decreaseSpeed();
			}else {
				npc.setSpeed(3);
		}	
	}
}

