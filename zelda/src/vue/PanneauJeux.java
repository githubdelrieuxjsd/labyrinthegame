package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controleur.Control;

import model.Case;
import model.Direction;
import model.Element;
import model.Minotaure;

public class PanneauJeux extends JPanel {

	Control control = new Control();

	Minotaure minotaure = (Minotaure) control.getListMonstre().get(0);

	public PanneauJeux() {

		this.setLayout(null);
		//this.setBackground(Color.decode("#5E9D34"));
		// this.setBackground(Color.green);

		this.addKeyListener( new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					control.deplacerHero(new Direction ("up" ));
					break;
				case KeyEvent.VK_DOWN:
					control.deplacerHero(new Direction ("down"));

					break;

				case KeyEvent.VK_RIGHT:

					control.deplacerHero(new Direction ("right"));
				
					break;
				case KeyEvent.VK_LEFT:
					control.deplacerHero(new Direction ("left" ));
					
					break;
				case KeyEvent.VK_S:
					control.animationBowHero();
					break;
				case KeyEvent.VK_D:
					control.animationAttackHero();
					break;
				case KeyEvent.VK_Q:
					control.animationPlaceBombHero();
					break;
				case KeyEvent.VK_I:
					control.getPlateau().afficher();
					break;
				default:
					;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

		});
		this.setFocusable(true);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		paintPlateau(g);
		control.action();

		
		try {
			//Thread.sleep(1000);  // 1 fps
			Thread.sleep(31,25);  // 32 fps
			//Thread.sleep(16,666666); // 60 fps
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		repaint();

	}

	private void paintPlateau(Graphics g) {
		
		for (Case c : control.getPlateau().getListCase()) {
			
		dessinBackground(g , c);
		}

		for (Case c : control.getPlateau().getListCase()) {
			
			dessinForgroundBlock(g , c);
		
			}
		for (Case c : control.getPlateau().getListCase()) {
			
			dessinForgroundUnite(g , c);

			dessinSideBar(g , c);			
			}

	}

	

	private void dessinForgroundBlock(Graphics g, Case c) {
		c.dessinBlock(control.getPlateau() ,g);

	}

	private void dessinForgroundUnite(Graphics g , Case c) {
			c.dessinUnite(control.getPlateau(), g);
	}

	private void dessinBackground(Graphics g, Case c) {
			
			ImageIcon icon = new ImageIcon("hyrule/grass1.png");
			
			if ((c.getElement().getCoordonnee().getX()+c.getElement().getCoordonnee().getY() )%2==1) {
				icon = new ImageIcon("hyrule/grass3.png");
			}
			
			Image img = icon.getImage();
			g.drawImage(img, c.getElement().getCoordonnee().getX() * 40, c.getElement().getCoordonnee().getY() * 40, 40,
					40, null);
			

	}
	
	private void dessinSideBar(Graphics g, Case c) {
		// TODO Auto-generated method stub
		
			ImageIcon icon = new ImageIcon("img/vide.png");
		 if (c.getElement().getCoordonnee().getY() == 18) {
				c.setItem();

			 if ( c.getElement().getCoordonnee().getX() > 0 && c.getElement().getCoordonnee().getX() < control.getHero().getLife() + 1 ) {
					icon = new ImageIcon("hyrule/heart/rouge.png");
					
					Image img = icon.getImage();

					g.drawImage(img, c.getElement().getCoordonnee().getX() * 40 +8 , c.getElement().getCoordonnee().getY() * 40 +10, 25,
							21, null);
				}
			 
		 }
			
			

			

		
	}

	private void gameOver(String reson) {
		// TODO Auto-generated method stub
		System.out.println(reson);
		// this.removeKeyListener( keykeykey);

		PanneauStart jeux = new PanneauStart();
		jeux.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.add(jeux);
		this.updateUI();

	}

}
