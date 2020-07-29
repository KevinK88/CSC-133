package com.mycompany.a2;
import com.codename1.util.MathUtil;
import java.lang.Math;
import com.codename1.charts.models.Point;
public class BaseStrategy implements IStrategy {

	private NonPlayerCyborg npc;
	private Bases base;
	
	// Find the Base then take the location for calculation
	public BaseStrategy( GameWorldCollection gameObjects,NonPlayerCyborg npc) {
		this.npc = npc;
		IIterator itr = gameObjects.getIterator();
		while(itr.hasNext()) {
			GameObjects temp = (GameObjects) itr.getNext();
			if(temp instanceof Bases) {
				if(((Bases)temp).getSequenceNumber() == (npc.getLastBaseReached()+1)) {
					base = ((Bases)temp);
					break;
				}
				else {
					base = ((Bases)temp);
				}
			}				
		}
	}
	@Override
	public void apply() {
		Point baseLocation = base.getLocation();
		Point npcLocation = npc.getLocation();
		float temp_x = baseLocation.getX() - npcLocation.getX();
		float temp_y = baseLocation.getY() - npcLocation.getY();
		float newAngle = (float)(90 -  Math.toDegrees(MathUtil.atan2((double)temp_x,(double)temp_y)));
		int angel = 90 - (int)newAngle;
		npc.setHeading(npc.getSteeringDirection()+angel);
	}
}
