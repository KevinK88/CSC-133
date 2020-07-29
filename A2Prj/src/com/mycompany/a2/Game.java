package com.mycompany.a2;

import com.codename1.ui.Form; 
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox; 
import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.cmd.*;
import com.codename1.charts.util.ColorUtil;

public class Game extends Form {
	private GameWorld gw;
	private MapView mp;
	private ScoreView sv;
	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		mp = new MapView();
		sv = new ScoreView();
		gw.addObserver(mp);
		gw.addObserver(sv);
		
		this.add(BorderLayout.NORTH,sv);
		this.add(BorderLayout.CENTER,mp);
		//Command Key
		setUpMenu();
		setUpCommandLeft();
		setUpCommandRight();
		setUpCommandBottom();
		
		gw.init();
		this.show();
	}	
	private void setUpMenu() {
		Toolbar tb = new Toolbar();
		this.setToolbar(tb);
		tb.setTitle("Silly Challenge Game");
	
		// Sound
		CheckBox chkBoxcmd = new CheckBox();
		chkBoxcmd.getAllStyles().setBgTransparency(255);
		chkBoxcmd.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		chkBoxcmd.setFocusable(false);
		SoundCommand snc = new SoundCommand(gw);
		chkBoxcmd.setCommand(snc);
		tb.addComponentToSideMenu(chkBoxcmd);
		
		//Acceleration
		AccelerateCommand accmd = new AccelerateCommand(gw);
		tb.addCommandToSideMenu(accmd);
		
		//About
		AboutCommand abtcmd = new AboutCommand();
		tb.addCommandToSideMenu(abtcmd);
		
		// Exit
		QuitCommand qcmd = new QuitCommand();
		tb.addCommandToSideMenu(qcmd);
		
		//Help
		HelpCommand hcmd =new HelpCommand();
		tb.addCommandToRightBar(hcmd);
	
		
	}
	private void setUpCommandLeft() {
		Container leftContainner = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		leftContainner.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		// Accelerate Button
		AccelerateCommand accmd = new AccelerateCommand(gw);
		Button btnAccelerate = new Button(accmd);
		btnAccelerate = topSide(btnAccelerate);
		leftContainner.add(btnAccelerate);
		addKeyListener('a',accmd);
		
		// Left Button
		LeftTurnCommand lftcmd = new LeftTurnCommand(gw);
		Button btnLeft = new Button(lftcmd);
		btnLeft = applyMakeup(btnLeft);
		leftContainner.add(btnLeft);
		addKeyListener('l',lftcmd);
		
		// Change Strategies Button
		ChangeStrategiesCommand strcmd = new ChangeStrategiesCommand(gw);
		Button btnStrategies = new Button(strcmd);
		btnStrategies = applyMakeup(btnStrategies);
		leftContainner.add(btnStrategies);
		this.add(BorderLayout.WEST,leftContainner);
		
	}
	
	private void setUpCommandRight() {
		
		Container rightContainner = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		rightContainner.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		// Break Button
		BreakCommand bkcmd = new BreakCommand(gw);
		Button btnBreak = new Button(bkcmd);
		btnBreak = topSide(btnBreak);
		rightContainner.add(btnBreak);
		addKeyListener('b',bkcmd);
		
		//Right Button
		RightTurnCommand rtcmd = new RightTurnCommand(gw);
		Button btnRight  = new Button(rtcmd);
		btnRight = applyMakeup(btnRight);
		rightContainner.add(btnRight);
		addKeyListener('r',rtcmd);
		this.add(BorderLayout.EAST,rightContainner);
	
	}
	
	private void setUpCommandBottom() {
		Container bottomContainer = new Container((new BoxLayout(BoxLayout.X_AXIS)));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		// Collide with NPC Button
		CollideNPCCommand clnpc = new CollideNPCCommand(gw);
		Button btnNPC = new Button(clnpc);
		btnNPC.getAllStyles().setMarginLeft(350);
		btnNPC = bothSide(btnNPC);
		bottomContainer.add(btnNPC);
		
		// Collide with Base Button
		CollideBaseCommand clBase = new CollideBaseCommand(gw);
		Button btnBase = new Button(clBase);
		btnBase = bothSide(btnBase);
		bottomContainer.add(btnBase);
		
		// Collide with Energy Station Button
		CollideEnergyStationCommand clEnergy = new CollideEnergyStationCommand(gw);
		Button btnEnergy = new Button(clEnergy);
		btnEnergy = bothSide(btnEnergy);
		bottomContainer.add(btnEnergy);
		addKeyListener('e',clEnergy);
		
		// Collide with Drone Button
		CollideDroneCommand clDrone = new CollideDroneCommand(gw);
		Button btnDrone = new Button(clDrone);
		btnDrone = bothSide(btnDrone);
		bottomContainer.add(btnDrone);
		addKeyListener('g',clDrone);
		
		// Tick Button
		TickCommand tick = new TickCommand(gw);
		Button btnTick = new Button(tick);
		btnTick = bothSide(btnTick);
		bottomContainer.add(btnTick);
		this.add(BorderLayout.SOUTH,bottomContainer);
	
		addKeyListener('t',tick);
	}
	private Button topSide(Button obj) {
		obj.getAllStyles().setBgTransparency(255);
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		obj.getAllStyles().setMarginTop(100);
		obj.getAllStyles().setPadding(TOP, 5);
		obj.getAllStyles().setPadding(BOTTOM, 5);
		return obj;
	}
	private Button applyMakeup(Button obj) {
		obj.getAllStyles().setBgTransparency(255);
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		obj.getAllStyles().setPadding(TOP, 5);
		obj.getAllStyles().setPadding(BOTTOM, 5);
		return obj;
	}
	private Button bothSide(Button obj) {
		obj.getAllStyles().setBgTransparency(255);
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		obj.getAllStyles().setPadding(TOP, 5);
		obj.getAllStyles().setPadding(BOTTOM, 5);
		return obj;
	}


	}
	
	
	
	
