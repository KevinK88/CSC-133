package com.mycompany.cmd;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.CheckBox; 
public class SoundCommand extends Command{
    private GameWorld gw;
    public SoundCommand(GameWorld gw)
    {
        super("Sound ON/OFF");
        this.gw = gw;
  
    }
    @Override
    public void actionPerformed(ActionEvent e) {
	if(((CheckBox) e.getComponent()).isSelected()) {
		gw.setSound(true);
	}
	else {
		gw.setSound(false);
	}
}
}