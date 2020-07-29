package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public abstract class Fixed extends GameObjects implements ISelectable{
	private boolean selected;
	public Fixed(int color,GameWorld gw) {
		super(color,gw);
	}
	public Fixed(int color, int size,GameWorld gw) {
		super(color,size,gw);
	}

	@Override
	public void setLocation(float X, float Y) {
		if(selected) 
			super.setLocation(X, Y);
		}
	public void setSelected(boolean y) {
		selected = y;
	}
	public boolean isSelected() {
		return selected;
	}
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int radius = super.getSize() / 2;
		int left = (int)Math.round(super.getX() - radius + pCmpRelPrnt.getX());
		int right = left + super.getSize();
		int top = (int)Math.round(super.getY() - radius + pCmpRelPrnt.getY());
		int bottom = top + super.getSize();
		boolean checkedLR = pPtrRelPrnt.getX() > left && pPtrRelPrnt.getX() < right;
		boolean checkedTB = pPtrRelPrnt.getY() > top && pPtrRelPrnt.getY() < bottom;
		return checkedLR && checkedTB;
	}

}
	

