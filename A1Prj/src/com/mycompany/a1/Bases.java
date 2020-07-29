package com.mycompany.a1;
import com.codename1.charts.util.ColorUtil;
public class Bases extends Fixed {
    private int sequenceNumber;
    public Bases(int sequenceNumber) {
    	super(ColorUtil.GREEN,10);
    	this.sequenceNumber = sequenceNumber;
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
  
}
