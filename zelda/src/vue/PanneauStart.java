package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tool.Tool;


public class PanneauStart extends JPanel {
	PanneauJeux jeux ;
	//JTextArea c = new JTextArea("a");


	JLabel titreLab ;
	 
	JButton modeJeuxBtn ;
	JButton modeDebugBtn ;	

	
	public PanneauStart () {
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.setBackground(Color.PINK);
		ajouter();
	
	}

	private void ajouter() {
		// TODO Auto-generated method stub
		
		
		titreLab = new JLabel ("Zeldo Adventur") ;
		titreLab.setBounds(50,100,1500,200);
		titreLab.setBackground(Color.PINK);
		titreLab.setFont(new Font ("Segoe Script", Font.BOLD , 120 ));
		titreLab.setForeground(Color.BLUE);
		this.add(titreLab); 
		
		
		
		modeJeuxBtn = new JButton("Jeux");
		modeJeuxBtn.setBounds(400,350,300,70);
		this.add(modeJeuxBtn); 
		modeJeuxBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBackground(Color.WHITE);
				start("jeux");	
			}

		});
		
		
		
		modeDebugBtn = new JButton("Debug");
		modeDebugBtn.setBounds(400,450,300,70);
		this.add(modeDebugBtn); 
		modeDebugBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBackground(Color.WHITE);
				start("debug");	
			}

		});

	}


	

	private void start(String gameMode) {
		// TODO Auto-generated method stub
		remove();
		jeux = new PanneauJeux(gameMode);
		if(gameMode.equals("jeux")) {
			Principale.getFrame().setPreferredSize(new Dimension(60*20,60*10));
			Principale.getFrame().setContentPane( jeux);
			Principale.getFrame().pack();
			

		}else {
			Principale.getFrame().setPreferredSize(new Dimension(7*201+100,7*101));
			Principale.getFrame().setContentPane(jeux );
			Principale.getFrame().pack();
		}
		jeux.requestFocus();// SUPER IMPORTANT
		this.updateUI();

	}

	private void remove() {
		// TODO Auto-generated method stub
		remove(titreLab);
		remove(modeJeuxBtn);
		remove(modeDebugBtn);

	}


}
