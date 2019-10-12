package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controleur.Control;

import model.Case;
import model.Plateau;
import tool.Tool;

public class PanneauJeux extends JPanel {

	private Control control = new Control();
	// 31,25
	private long fps = 31;
	private String herodescision = "nothing";
	private boolean heroaction = false;

	private JTextField nombreRubi = new JTextField("" + 0) {
		@Override
		public void setBorder(Border border) {
			// No!
		}
	};

	public PanneauJeux() {
		
		this.setLayout(null);
		// this.setBackground(Color.decode("#5E9D34"));
		// this.setBackground(Color.green);
		this.nombreRubi.setOpaque(false);
		this.nombreRubi.setBounds(5, 35, 100, 30);
		this.nombreRubi.setFont(new Font("Segoe Script", Font.BOLD, 25));
		this.nombreRubi.setForeground(Color.WHITE);
		this.add(this.nombreRubi);

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_Z:
					herodescision = "toucheAction";
					heroaction = true;
					break;
				case KeyEvent.VK_UP:
					herodescision = "moveUp";
					heroaction = true;
					break;
				case KeyEvent.VK_DOWN:
					herodescision = "moveDown";
					heroaction = true;

					break;

				case KeyEvent.VK_RIGHT:
					herodescision = "moveRight";
					heroaction = true;

					break;
				case KeyEvent.VK_LEFT:
					herodescision = "moveLeft";
					heroaction = true;

					break;
				case KeyEvent.VK_S:
					herodescision = "tirer";
					heroaction = true;
					break;
				case KeyEvent.VK_D:
					herodescision = "attaque";
					heroaction = true;
					break;
				case KeyEvent.VK_Q:
					herodescision = "bomb";
					heroaction = true;
					break;
				case KeyEvent.VK_O:
					if (fps == 31) {
						fps = 500;
					} else {
						fps = 31;
					}
					break;
				case KeyEvent.VK_P:
					if (fps == 31) {
						fps = 1000;
					} else {
						fps = 31;
					}
					break;
				case KeyEvent.VK_I:
					if (fps == 31) {
						fps = 3000;
					} else {
						fps = 31;
					}
					control.information();
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
		nombreRubi.setText("" + control.getHero().getNombreRubi());
		paintPlateau(g);
		heroaction = control.action(this.herodescision, heroaction);

		try {
			// Thread.sleep(1000); // 1 fps
			Thread.sleep(fps); // 32 fps
			// Thread.sleep(16,666666); // 60 fps
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		repaint();

	}

	private void paintPlateau(Graphics g) {
		Plateau plateau = control.getPlateau() ;
		for (int i = 0; i < plateau.getNombreCaseX() * plateau.getNombreCaseY(); i++) {
			Case c = control.getPlateau().getListCase().get(i);
			dessinBackground(g, c);

		}

			for (int y = 0; y < plateau.getNombreCaseY(); y++) {
				for (int x = 0; x < plateau.getNombreCaseX(); x++) {
					for (int z = 0; z < plateau.getNombreCaseZ(); z++) {
						Case c = plateau.getListCase().get(Tool.CoordinateToNum(x, y, z));
						dessinForgroundBlock(g, c);
						dessinForgroundUnite(g, c);
				}
			}
		}
/**
		for (Case c : control.getPlateau().getListCase()) {
			dessinForgroundBlock(g, c);
			dessinForgroundUnite(g, c);
		}
*/
		dessinSideBar(g, control.getPlateau().getListCase().get(0));

		// for (Case c : control.getPlateau().getListCase()) {

		// }

	}

	private void dessinForgroundBlock(Graphics g, Case c) {
		c.dessinItemBlockProjectil(control.getPlateau(), g);

	}

	private void dessinForgroundUnite(Graphics g, Case c) {
		c.dessinUnite(control.getPlateau(), g);
	}

	private void dessinBackground(Graphics g, Case c) {

		ImageIcon icon = new ImageIcon("hyrule/grass1.png");

		if ((c.getCoordonnee().getX() + c.getCoordonnee().getY()) % 2 == 1) {
			icon = new ImageIcon("hyrule/grass3.png");
		}

		Image img = icon.getImage();
		g.drawImage(img, c.getCoordonnee().getX() * c.getTailleCasePixel(),
				c.getCoordonnee().getY() * c.getTailleCasePixel(), c.getTailleCasePixel(), c.getTailleCasePixel(),
				null);

	}

	private void dessinSideBar(Graphics g, Case c) {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon("hyrule/heart/rouge.png");
		// ImageIcon icon = new ImageIcon("img/vide.png");
		for (int i = 0; i < control.getHero().getMaxLife(); i++) {
			icon = new ImageIcon("hyrule/heart/noir.png");

			if (i < control.getHero().getLife()) {
				icon = new ImageIcon("hyrule/heart/rouge2.png");
			}
			Image img = icon.getImage();

			g.drawImage(img, c.getTailleCasePixel() / 10 + 25 * i, c.getTailleCasePixel() / 10, 25, 25, null);

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
