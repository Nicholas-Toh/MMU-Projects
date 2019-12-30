//edited by Justin Tey
// This class is a GUI that will pop up when the game end. It takes in a parameter to determine the end game outcome.

import javax.swing.*;  

public class Result {  
	JFrame f;  
	Result (String color){  
	    f=new JFrame();  
	    if(color == "Draw")
	    {
	    	JOptionPane.showMessageDialog(f,"Draw!");
	    }
	    else
	    {
	    	JOptionPane.showMessageDialog(f,color + " has won the game!");
	    }
	}  
}