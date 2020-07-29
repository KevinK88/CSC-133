package com.mycompany.a3;

import com.codename1.ui.Form; 
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox; 
import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.cmd.*;
import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.util.UITimer;
public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private final int GAME_TICK = 20;
	private CheckBox chkBoxcmd;
	private SoundCommand snc;
	private AccelerateCommand accmd ;
	private AboutCommand abtcmd;
	private QuitCommand qcmd;
	private HelpCommand hcmd ;
	private Button btnAccelerate;
	private LeftTurnCommand lftcmd;
	private Button btnLeft;
	private ChangeStrategiesCommand strcmd ;
	private Button btnStrategies;
	private BreakCommand bkcmd;
	private Button btnBreak;
	private RightTurnCommand rtcmd;
	private Button btnRight;
	private Button btnPause;
	private PauseCommand pause;

	private PositionCommand position ;
	private Button btnPosition ;
	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		this.add(BorderLayout.NORTH,sv);
		this.add(BorderLayout.CENTER,mv);
		//Command Key
		setUpMenu();
		setUpCommandLeft();
		setUpCommandRight();
		setUpCommandBottom();
		gw.init();
		this.show();
		gw.createSound();
		timer  =  new UITimer(this);
		timer.schedule(GAME_TICK, true, this);
		// Set up GUI 
		System.out.println("Game GUI Setup Completed with the following stats :");
		System.out.println("Game World size : "+ GameWorld.getGameHeight() + ","+GameWorld.getGameWidth());
		System.out.println("Form Content pane size : " + this.getWidth() + "," + this.getHeight());
		System.out.println("MapView size : " + mv.getWidth() + "," + mv.getHeight());
		System.out.println("MapView Origin : " + mv.getX() + "," + mv.getY());
		
		GameWorld.setGameWidth(mv.getWidth());
		GameWorld.setGameHeight(mv.getHeight());
		
		mv.setMapViewOrigin(new Point(mv.getX() , mv.getY()));
		MapView.setMapViewWidth(mv.getWidth());
		MapView.setMapViewHeight(mv.getHeight());
	}	
	private void setUpMenu() {
		Toolbar tb = new Toolbar();
		this.setToolbar(tb);
		tb.setTitle("Silly Challenge Game");
	
		// Sound
		chkBoxcmd = new CheckBox();
		chkBoxcmd.getAllStyles().setBgTransparency(255);
		chkBoxcmd.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		chkBoxcmd.setFocusable(false);
		
		
	    snc = new SoundCommand(gw);
		chkBoxcmd.setCommand(snc);
		tb.addComponentToSideMenu(chkBoxcmd);
		
		//Acceleration
		accmd = new AccelerateCommand(gw);
		tb.addCommandToSideMenu(accmd);
		
		//About
		abtcmd = new AboutCommand();
		tb.addCommandToSideMenu(abtcmd);
		
		// Exit
		qcmd = new QuitCommand();
		tb.addCommandToSideMenu(qcmd);
		
		//Help
		hcmd =new HelpCommand();
		tb.addCommandToRightBar(hcmd);
	
		
	}
	private void setUpCommandLeft() {
		Container leftContainner = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		leftContainner.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		// Accelerate Button
		accmd = new AccelerateCommand(gw);
	    btnAccelerate = new Button(accmd);
		btnAccelerate = topSide(btnAccelerate);
		leftContainner.add(btnAccelerate);
		addKeyListener('a',accmd);
		
		// Left Button
	    lftcmd = new LeftTurnCommand(gw);
		btnLeft = new Button(lftcmd);
		btnLeft = applyMakeup(btnLeft);
		leftContainner.add(btnLeft);
		addKeyListener('l',lftcmd);
		
		// Change Strategies Button
		strcmd = new ChangeStrategiesCommand(gw);
	    btnStrategies = new Button(strcmd);
		btnStrategies = applyMakeup(btnStrategies);
		leftContainner.add(btnStrategies);
		this.add(BorderLayout.WEST,leftContainner);
		
	}
	
	private void setUpCommandRight() {
		
		Container rightContainner = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		rightContainner.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		// Break Button
	    bkcmd = new BreakCommand(gw);
		btnBreak = new Button(bkcmd);
		btnBreak = topSide(btnBreak);
		rightContainner.add(btnBreak);
		addKeyListener('b',bkcmd);
		
		//Right Button
		rtcmd = new RightTurnCommand(gw);
		btnRight  = new Button(rtcmd);
		btnRight = applyMakeup(btnRight);
		rightContainner.add(btnRight);
		addKeyListener('r',rtcmd);
		this.add(BorderLayout.EAST,rightContainner);
	
	}
	
	private void setUpCommandBottom() {
		Container bottomContainer = new Container((new BoxLayout(BoxLayout.X_AXIS)));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));

		//Pause Command
		
		btnPause = new Button("Pause");
		btnPause = bothSide(btnPause);
		btnPause.getAllStyles().setMarginLeft(850);
		pause  = new PauseCommand(gw,this,btnPause);
		btnPause.setCommand(pause);
		bottomContainer.add(btnPause);
		
		//Position Button
		position = new PositionCommand(gw);
		btnPosition = new Button(position);
		btnPosition = bothSide(btnPosition);
		bottomContainer.add(btnPosition);

		this.add(BorderLayout.SOUTH,bottomContainer);
	
		
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
	// This one is from runnable
	@Override
	public void run() {
		gw.gameTick();
		
	}
	public void pressedPause() {
		if (!gw.getPause()) {
			timer.cancel();
			removeKeyListener('a', accmd);
			removeKeyListener('b', bkcmd);
			removeKeyListener('l', lftcmd);
			removeKeyListener('r', rtcmd);
			accmd.setEnabled(false);
			bkcmd.setEnabled(false);
			lftcmd.setEnabled(false);
			rtcmd.setEnabled(false);
			strcmd.setEnabled(false);
			btnAccelerate.setEnabled(false);
			btnBreak.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
		    btnStrategies.setEnabled(false);
		    position.setEnabled(true);
			btnPosition.setEnabled(true);
			gw.turnOffSound();
			gw.setPause(!gw.getPause());
		}
		else {
			timer.schedule(GAME_TICK, true, this);
			addKeyListener('a', accmd);
			addKeyListener('b', bkcmd);
			addKeyListener('l', lftcmd);
			addKeyListener('r', rtcmd);
			accmd.setEnabled(true);
			bkcmd.setEnabled(true);
			lftcmd.setEnabled(true);
			rtcmd.setEnabled(true);
			strcmd.setEnabled(true);
			btnAccelerate.setEnabled(true);
			btnBreak.setEnabled(true);
			btnLeft.setEnabled(true);
			btnRight.setEnabled(true);
		    btnStrategies.setEnabled(true);
		    position.setEnabled(false);
			btnPosition.setEnabled(false);
			gw.turnOnSound();
			gw.setPause(!gw.getPause());
		}
	}
}
	
	
	
	
