package vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Principale {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		//frame.setPreferredSize(new Dimension(60*20,60*10));
		frame.setPreferredSize(new Dimension(7*201,7*101));

		frame.setLocation(0,0);//50/25
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	 //  frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setUndecorated(true);
	    
	//	frame.setContentPane(new PanneauJeux () );
		frame.setContentPane(new PanneauStart () );
		
		frame.setVisible(true);
		frame.pack();
		
		
		}
}
