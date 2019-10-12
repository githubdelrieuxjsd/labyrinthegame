package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tool.Tool;


public class PanneauStart extends JPanel {
	PanneauJeux jeux = new PanneauJeux();
	JTextArea petit = new JTextArea("a");
	//JTextArea c = new JTextArea("a");


	JTextArea txt_NomJeux = new JTextArea("Labyrinthe");
	 
	JButton btn_start = new JButton("Start");
	

	
	public PanneauStart () {
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.setBackground(Color.PINK);
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
		petit.setBounds(0,0,4,4);
		petit.setBackground(Color.PINK);
		petit.setLineWrap(false);

		this.add(petit);
		
		btn_start.setBounds(450,400,100,50);
		this.add(btn_start); 
		txt_NomJeux.setBounds(50,100,1000,200);
		txt_NomJeux.setLineWrap(false);
		txt_NomJeux.setBackground(Color.PINK);
		txt_NomJeux.setFont(new Font ("Segoe Script", Font.BOLD , 90 ));
		txt_NomJeux.setForeground(Color.BLUE);
		this.add(txt_NomJeux); 
		
	}

	private void remove() {
		// TODO Auto-generated method stub
		this.remove(petit);
		this.remove(txt_NomJeux);
		this.remove(btn_start);
		
		
	}	

	private void start() {
		// TODO Auto-generated method stub

		jeux.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		
		this.add(jeux);
		this.updateUI();
		
		remove();
		
	}
}
