package com.mycompany.a1;
import com.codename1.ui.events.ActionListener; 

import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; 
import com.codename1.ui.Form;
import java.lang.String;
public class Game extends Form {
	private GameWorld gw;
	private char hotKey;
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}	
	private void setKey(char key)
	{
		this.hotKey=key;
	}
	private char getKey() {
		return this.hotKey;
	}

	private void play() {
		Label myLabel=new Label("Enter a Command:"); 
		this.addComponent(myLabel);
		final TextField myTextField = new TextField(); 
		this.addComponent(myTextField); 
		this.show();
			myTextField.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent evt) {
			String sCommand = myTextField.getText().toString(); 
			 myTextField.clear();
			if(sCommand.length() != 0)
				switch (sCommand.charAt(0)) { 
					case 'a':
						gw.increaseSpeed();
						break;
					case 'b':
						gw.decreaseSpeed();
						break;
					case 'l':
						gw.turnLeftCyborg();
						break;
					case 'r':
						gw.turnRightCyborg();
						break;
					case 'c':
						gw.collsionCyborg();
						break;
					// Case 1 -9
					case '1':
						gw.collisonBases(1);
						break;
					case '2':
						gw.collisonBases(2);
						break;
					case '3':
						gw.collisonBases(3);
						break;
					case '4':
						gw.collisonBases(4);
						break;
					case '5':
						gw.collisonBases(5);
						break;
					case '6':
						gw.collisonBases(6);
						break;
					case '7':
						gw.collisonBases(7);
						break;
					case '8':
						gw.collisonBases(8);
						break;
					case '9':
						gw.collisonBases(9);
						break;
					case 'e':
						gw.collsionEnergyStation();
						break;
					case 'g':
						gw.collisonDrone();
						break;
					case 't':
						gw.gameTick();
						break;
					case 'd':
						gw.printCurrent();;
						break;
					case 'm':
						gw.printMap();
						break;
					case 'x':
						setKey(sCommand.charAt(0));
						System.out.println("Are you sure to exit game?");
						break;
					 case 'N':
					 case 'n':
						if(getKey() == 'x') 
							System.out.println("Game continue");
						else {
							System.out.println("Invalid input");
						}
							break;
					 case 'Y':
					 case 'y':
						 if(getKey() == 'x')
							 gw.exit();
						 else {
							 System.out.println("Invalid input");
						 }
						 break;
					default:
						System.out.println("\nYour Input is invalid.Please enter valid command!!!\n");
						break;
				}
				} //switch
			 //actionPerformed
			}//new ActionListener() )
		);
		//addActionListener
	}//play
	}
	
	
	
	
