package com.mycompany.cmd;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Button;
public class PauseCommand extends Command{
	private GameWorld gw;
	private Game game;
	private Button button;
	public PauseCommand(GameWorld gw, Game game,Button button) {
		super("Pause");
		this.gw = gw;
		this.game = game;
		this.button = button;
	}
	@Override 
	public void actionPerformed(ActionEvent e) {
		if (!gw.getPause()) {
			game.pressedPause();
			button.setText("Play");
		}
		else {
			game.pressedPause();
			button.setText("Pause");
			
		}
	}
}
