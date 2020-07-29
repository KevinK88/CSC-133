package com.mycompany.cmd;
import com.mycompany.a2.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class RightTurnCommand extends Command{
    private GameWorld gw;
    public RightTurnCommand(GameWorld gw)
    {
        super("Turn Right");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.turnRightCyborg();
        System.out.println("Turn Right");
    }
}