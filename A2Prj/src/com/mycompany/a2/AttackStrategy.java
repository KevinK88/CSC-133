package com.mycompany.a2;
import com.codename1.util.MathUtil;
import java.lang.Math;
import com.codename1.charts.models.Point;
public class AttackStrategy implements IStrategy {
	private PlayerCyborg cb;
	private NonPlayerCyborg npc;	
	
	// Find the Cyborg then take the location for calculation
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
	@Override
	public void apply() {
		Point cbLocation = cb.getLocation();
		Point npcLocation = npc.getLocation();
		float temp_x = cbLocation.getX() - npcLocation.getX();
		float temp_y = cbLocation.getY() - npcLocation.getY();
		float newAngle = (float)(90 -  Math.toDegrees(MathUtil.atan2((double)temp_x,(double)temp_y)));
		int angel = 90 - (int)newAngle;
		npc.setHeading(npc.getSteeringDirection()+angel);
	}
}
