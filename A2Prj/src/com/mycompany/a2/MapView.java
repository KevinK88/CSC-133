package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.Container;
public class MapView extends Container implements Observer  {
	private TextArea gameText;
	private static int height;
	private static int width;
	private GameWorld gw;
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(10,ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		gameText = new TextArea();
		gameText.setEditable(false);
		gameText.getAllStyles().setBgTransparency(0);
		MapView.height = this.getHeight();
		MapView.width = this.getWidth();
		this.setWidth(1000);
		this.setHeight(1000);
		this.add(BorderLayout.NORTH, gameText);
	}
	public static void setMapHight(int height) {
		MapView.height = height;
	}
	public static int getMapHeight() {
		return height;
	}
	public static void setMapWidth(int width) {
		MapView.width = width;
	}
	public static int getMapWidth() {
		return width;
	}
	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld) data;
		IIterator itr = gw.getCollection().getIterator();	
		String display = "";
		while(itr.hasNext()){
			display = display + itr.getNext().toString()+"\n";
		}
		gameText.setText(display);
		this.repaint();
	}
}
