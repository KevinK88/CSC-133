package com.mycompany.cmd;
import com.mycompany.a2.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.*;
public class CollideBaseCommand extends Command{
    private GameWorld gw;
  
    public CollideBaseCommand(GameWorld gw)
    {
        super("Collide with Base");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {   
    	Command cOk = new Command("Ok");
    	TextField myTF = new TextField();
        Dialog.show("Enter the base:", myTF, cOk);
        String text = myTF.getText().toString();
        try {
        	int temp = Integer.parseInt(text);
          if (temp > 0 && temp < 10)
          {
          	gw.collisonBases(temp);
          	System.out.println("Hit Base");
          }
          else {
        	String info = "Please enter again number between 1 - 9!!!";
        	Dialog.show("Error",info,"Ok",null);
          	System.out.println("Please enter again number between 1 - 9");
          }
        }catch(NumberFormatException e1) {
        	 String info = "Please enter number again not leter!!!";
             Dialog.show("Error",info,"Ok",null);
        } 
    }
}