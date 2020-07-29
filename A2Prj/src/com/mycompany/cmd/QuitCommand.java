package com.mycompany.cmd;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class QuitCommand extends Command{
    public QuitCommand()
    {
        super("Quit");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	Command cOk = new Command("Yes");
    	Command cCancel = new Command("No");
    	Command []cmds = new Command[] {cOk,cCancel};
        Command c = Dialog.show("Quit","Do you want to quit?",cmds);
        if (c == cOk)
            System.exit(0);
        	
    }
}