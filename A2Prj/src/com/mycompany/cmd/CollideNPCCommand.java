package com.mycompany.cmd;
import com.mycompany.a2.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class CollideNPCCommand extends Command{
    private GameWorld gw;
    public CollideNPCCommand(GameWorld gw)
    {
        super("Collide with NPC");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.collsionCyborg();
        System.out.println("Hit Cyborg");
    }
}