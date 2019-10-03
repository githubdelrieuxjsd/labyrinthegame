package vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Principale {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setPreferredSize(new Dimension(1200,680));
		frame.setLocation(50, 25);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	 //  frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setUndecorated(true);
	    
	//	frame.setContentPane(new PanneauJeux () );
		frame.setContentPane(new PanneauStart () );
		
		frame.setVisible(true);
		frame.pack();
		
		
		}
}
