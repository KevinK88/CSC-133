package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
public class Bases extends Fixed implements IDrawable{
    private int sequenceNumber;
    private GameWorld gw;
    public Bases(int sequenceNumber,GameWorld gw) {

    	super(ColorUtil.GREEN,50,gw);
    	this.sequenceNumber = sequenceNumber;
    	this.gw = gw;
    }
    @Override
    public String toString() {
    	return ("Base: loc= " + Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0 +
    			"  color= " + this.getColortoString() + 
    			"  size= " + this.getSize()+
    			"  seqNumber= " + this.getSequenceNumber()
    			);
    }
    public void setColor(int color){
    	System.out.println("Cannot be changed");
    }
    public int getSequenceNumber(){
        return this.sequenceNumber;
    }
    public void setSequenceNumber(int sequenceNumber) {
    	this.sequenceNumber = sequenceNumber;
    }
    
   public void setSize(int size) {}
  @Override
  public void draw(Graphics g, Point pCmpRelPrnt) {
	  int stringX = (int)Math.round(this.getX() -10) + pCmpRelPrnt.getX();
	  int stringY = (int)Math.round(this.getY() -10) + pCmpRelPrnt.getY();
	  g.setColor(super.getColor());
	  int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX();
	  int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();
	  int[] xPoints = { xLoc, (xLoc - 20), (xLoc + 20), xLoc };
	  int[] yPoints = { (yLoc + 30), (yLoc - 30), (yLoc - 30), (yLoc + 30) };
	  int nPoints = 3;
	  if (super.isSelected())
		  g.drawPolygon(xPoints, yPoints, nPoints);
	  else
		  g.fillPolygon(xPoints, yPoints, nPoints);
	  g.setColor(ColorUtil.BLUE);
	  g.drawString("" + this.sequenceNumber, stringX, stringY);
	  
  }

  @Override
  public void handleCollision(GameObjects object) {
	  if (this instanceof Bases && object instanceof PlayerCyborg || this instanceof Bases && object instanceof NonPlayerCyborg ) {
		 
		  if (((Cyborg) object).getLastBaseReached() +1 == this.getSequenceNumber() && this.getSequenceNumber() != 9) {
				System.out.println("The Cybord has reached the next base");
				((Cyborg) object).setLastBaseReached(this.getSequenceNumber());
			}
			else if (this.getSequenceNumber() == 9 && object instanceof PlayerCyborg) {
				if (((PlayerCyborg)object).getLastBaseReached()+1 == 9) {
				System.out.println("Game Over, you win! Total time:" + super.getGW().getTime());
				gw.exit(0);
				}
			}
			else if (this.getSequenceNumber()== 9 && object instanceof NonPlayerCyborg) {
				if (((NonPlayerCyborg)object).getLastBaseReached()+1 == 9) {
				System.out.println("Game Over, you lose the game!!!!");
				gw.exit(1);
				}
			}
	  }
  }
  
  
}
