package com.mycompany.cmd;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class BreakCommand extends Command{
    private GameWorld gw;
    public BreakCommand(GameWorld gw)
    {
        super("Brake");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.decreaseSpeed();
        System.out.println("Decrease speed");
    }
}