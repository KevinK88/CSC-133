package com.mycompany.cmd;
import com.mycompany.a2.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class TickCommand extends Command{
    private GameWorld gw;
    public TickCommand(GameWorld gw)
    {
        super("Tick");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.gameTick();
        System.out.println("Game has ticked");
    }
}