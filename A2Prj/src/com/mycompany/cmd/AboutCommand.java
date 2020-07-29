package com.mycompany.cmd;
import com.mycompany.a2.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class AboutCommand extends Command{
    public AboutCommand()
    {
        super("About");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String info = "Welcome to Silly Challange\n"
        		+ "Professor Gordon\n"
        		+"Author: Hung Quach\n"
        		+ "Version: 1.0";
        Dialog.show("About",info,"Ok",null);
    }
}