package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	private Label lifeValueLabel;
	private Label clockValueLabel;
	private Label lastBaseReachedValueLabel;
	private Label energyLevelValueLabel;
	private Label damageLevelValueLabel;
	private Label soundValueLabel;
	public ScoreView() {
		setLayout();
		setClockLabel();
		setLifeValueLabel();
		setLastBaseReachedValueLabel();
		setEnergyLevelValueLabel();
		setDamageLevelValueLabel();
		setSoundLabel();
		
	}
	public void setLayout() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
	}
	public void setClockLabel() {
		Label clockLabel = new Label("Time:");
		clockLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		clockValueLabel = new Label("0");
		clockValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		clockValueLabel.getAllStyles().setPadding(RIGHT, 2);

		this.add(clockLabel);
		this.add(clockValueLabel);
		
	}
	public void setLifeValueLabel() {
		Label lifeLabel = new Label("Lives Left:");
		lifeLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lifeLabel.getAllStyles().setPadding(1,1,1,1);
		lifeValueLabel = new Label("0");
		lifeValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lifeValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(lifeLabel);
		this.add(lifeValueLabel);
	}
	public void setLastBaseReachedValueLabel() {
		Label lastBaseLabel = new Label("Player Last Base Reached:");
		lastBaseLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastBaseLabel.getAllStyles().setPadding(1,1,1,1);
		lastBaseReachedValueLabel = new Label("0");
		lastBaseReachedValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastBaseReachedValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(lastBaseLabel);
		this.add(lastBaseReachedValueLabel);
	}
	public void setEnergyLevelValueLabel() {
		Label energyLabel = new Label("Player Energy Level:");
		energyLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		energyLabel.getAllStyles().setPadding(1,1,1,1);
		energyLevelValueLabel = new Label("0");
		energyLevelValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		energyLevelValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(energyLabel);
		this.add(energyLevelValueLabel);
	}
	public void setDamageLevelValueLabel() {
		Label damageLabel = new Label("Player Damage Level: ");
		damageLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		damageLabel.getAllStyles().setPadding(1,1,1,1);
		damageLevelValueLabel = new Label("0");
		damageLevelValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		damageLevelValueLabel.getAllStyles().setPadding(RIGHT, 5);
		this.add(damageLabel);
		this.add(damageLevelValueLabel);
	}
	public void setSoundLabel() {
		Label soundLabel = new Label("Sound: ");
		soundLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundLabel.getAllStyles().setPadding(1,1,1,1);
		soundValueLabel = new Label("OFF");
		soundValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundValueLabel.getAllStyles().setPadding(RIGHT, 5);       
		this.add(soundLabel);
		this.add(soundValueLabel);
	}
	@Override
	public void update(Observable observer, Object data) {
		gw = (GameWorld) data;
		this.lifeValueLabel.setText(" "+gw.getLife());
		this.clockValueLabel.setText(" ");
		this.clockValueLabel.setText(Integer.toString(gw.getTime()));;
		this.lastBaseReachedValueLabel.setText(" "+gw.getLastBaseReached());;
		this.energyLevelValueLabel.setText(" "+gw.getEnergyLevel());;
		this.damageLevelValueLabel.setText(" "+gw.getDamageLevel());
		if(gw.getSound() ) {
				this.soundValueLabel.setText("ON");
			}else 
				this.soundValueLabel.setText("OFF");
		this.repaint();
	}
}
