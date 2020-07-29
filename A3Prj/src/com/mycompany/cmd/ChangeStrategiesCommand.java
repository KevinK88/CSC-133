package com.mycompany.cmd;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class ChangeStrategiesCommand extends Command{
    private GameWorld gw;
    public ChangeStrategiesCommand(GameWorld gw)
    {
        super("Change Strategy");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.changeStrategy();

    }
}