package com.mycompany.cmd;
import com.mycompany.a2.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class CollideDroneCommand extends Command{
    private GameWorld gw;
    public CollideDroneCommand(GameWorld gw)
    {
        super("Collide with Drone");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.collisonDrone();
        System.out.println("Hit Drone");
    }
}