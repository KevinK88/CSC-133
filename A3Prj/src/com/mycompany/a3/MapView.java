package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
public class MapView extends Container implements Observer  {
	//private TextArea gameText;
	private static int height;
	private static int width;
	static private Point mapViewOrigin;
	private GameWorld gw;
	public MapView() {
		MapView.height = this.getHeight();
		MapView.width = this.getWidth();
		this.getAllStyles().setBorder(Border.createLineBorder(10,ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
	}
	public static int getMapViewWidth() { return width; }
	public static int getMapViewHeight() { return height; }
	public static void setMapViewWidth(int width) { MapView.width = width; }
	public static void setMapViewHeight(int height) { MapView.height = height; }
	
	public void setMapViewOrigin(Point p) { MapView.mapViewOrigin = p; }
	public static Point getMapViewOrigin() { return mapViewOrigin; }

	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld) data;
		this.repaint();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator itr = gw.getCollection().getIterator();
		while(itr.hasNext()) {
			GameObjects tempObject = itr.getNext();
			if (tempObject instanceof IDrawable) {
				((IDrawable) tempObject).draw(g, pCmpRelPrnt);
			}
		}
	}
	@Override
	public void pointerPressed(int x, int y ) {
		Point clickPoint = new Point(x - getParent().getAbsoluteX(), y - getParent().getAbsoluteY());
		Point originalPoint = new Point(getX(), getY());
		if(gw.getPause()) {
			gw.click(clickPoint, originalPoint);
		}
	}
}    
