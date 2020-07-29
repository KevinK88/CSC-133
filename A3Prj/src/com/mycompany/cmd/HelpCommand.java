package com.mycompany.cmd;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class HelpCommand extends Command{
    public HelpCommand()
    {
        super("Help");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    	String info = "A: Acceleration\n"+
        			  "B: Brake\n"+
        			  "E: Collide with Energy Station\n"+
        			  "G: Collide with Drone\n"+
        		      "L: Turn Left\n"+
        			  "R: Turn Right\n"+
        		      "T: Tick\n"   
        		       	+ "";
        Dialog.show("Help Comand",info,"Ok",null);
    }
}