package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import tool.Tool;


public class PanneauGameOver extends JPanel {
	PanneauJeux jeux = new PanneauJeux();

	JTextField txt_NomJeux = new JTextField("GAME OVER") {
		@Override
		public void setBorder(Border border) {
			// No!
		}
	};
	 
	JButton btn_start = new JButton("RESTART");
	

	
	public PanneauGameOver () {
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.setBackground(Color.BLACK);
		ajouter();
		
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBackground(Color.WHITE);
				start();	
			}

		});
		
		
	}

	private void ajouter() {
		// TODO Auto-generated method stub
		
		
		btn_start.setBounds(450,400,100,50);
		this.add(btn_start); 
		txt_NomJeux.setBounds(200,100,700,200);
		txt_NomJeux.setBackground(Color.BLACK);
		txt_NomJeux.setFont(new Font ("Segoe Script", Font.BOLD , 90 ));
		txt_NomJeux.setForeground(Color.WHITE);
		this.add(txt_NomJeux); 
		
	}

	private void remove() {
		// TODO Auto-generated method stub
		this.remove(txt_NomJeux);
		this.remove(btn_start);
		
		
	}	

	private void start() {
		// TODO Auto-generated method stub
		
		jeux.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		this.add(jeux);
		jeux.requestFocus();// SUPER IMPORTANT 
		this.updateUI();// SUPER IMPORTANT 
		
		remove();
		
	}
}

