package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import block.Arbre;
import block.Bush;
import block.Chest;
import block.PanneauAffichage;
import block.Rock;
import block.Vide;
import controleur.Control;

import model.Case;
import model.Coordonnee;
import model.Hero;
import model.Plateau;
import monstre.Chicken;
import monstre.Goblin;
import monstre.Knight;
import monstre.Monstre;
import monstre.Tomato;
import tool.Tool;

public class PanneauJeux extends JPanel {

	private Control control;
	// 31,25
	private long fps;
	private String herodescision;
	private boolean heroaction;

	KeyListener key;

	private String gameMode;
	private boolean pauseGame;
	private JButton pauseGameBtn;
	private JButton removeBtn;
	private JButton addBtn;
	private JComboBox comboAdd;

	private String ActionMouse;

	private JTextField nombreRubi = new JTextField("" + 0) {
		@Override
		public void setBorder(Border border) {
			// No!
		}
	};

	public PanneauJeux(String gameMode) {
		this.gameMode = gameMode;

		Control control = new Control();
		fps = 31;
		herodescision = "nothing";
		heroaction = false;

		Case c = new Case(new Coordonnee(0, 0, 0));
		if (gameMode.equals("debug")) {
			this.setBackground(Color.red);
			this.pauseGame = true;
			ActionMouse = "nothing";
			c.setTailleCasePixel(14);
			ajoutJcomponantDebug();
		} else {
			this.pauseGame = false;
			this.setBackground(new Color(147, 117, 56));
			c.setTailleCasePixel(60);
		}

		this.setLayout(null);

		// this.setBackground(new Color(14, 137, 56));

		// this.setBackground(new Color(147, 117, 56));
		// this.setBackground(Color.green);
		this.nombreRubi.setOpaque(false);
		this.nombreRubi.setBounds(5, 35, 100, 30);
		this.nombreRubi.setFont(new Font("Segoe Script", Font.BOLD, 25));
		this.nombreRubi.setForeground(Color.WHITE);

		this.addKeyListener(key = new KeyListener() {

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
				case KeyEvent.VK_G:
					GameOver();
					break;
				case KeyEvent.VK_ESCAPE:
					backToStartingMenu();
					break;
				default:
					;
				}
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyTyped(KeyEvent e) {

			}

		});
		this.setFocusable(true);// SUPER IMPORTANT

	}

	private void addFocusListener(ActionListener actionListener) {
		// TODO Auto-generated method stub

	}

	private void ajoutJcomponantDebug() {

		// TODO Auto-generated method stub
		pauseGameBtn = new JButton("START");
		pauseGameBtn.setBounds(1405, 50, 100, 30);
		this.add(pauseGameBtn);
		pauseGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pauseGame) {
					pauseGame = false;
					pauseGameBtn.setText("STOP");
				} else {
					pauseGame = true;
					pauseGameBtn.setText("START");

				}
				requestFocusInWindow();
			}

		});

		removeBtn = new JButton("REMOVE");
		removeBtn.setBounds(1405, 110, 100, 30);
		this.add(removeBtn);
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ActionMouse = "remove";
				requestFocusInWindow();
			}

		});

		addBtn = new JButton("ADD");
		addBtn.setBounds(1405, 170, 100, 30);
		this.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ActionMouse = "add";
				requestFocusInWindow();
			}

		});

		comboAdd = new JComboBox();
		comboAdd.addItem("Arbre");
		comboAdd.addItem("Bush");
		comboAdd.addItem("Chest");
		comboAdd.addItem("PanneauAffichage");
		comboAdd.addItem("Rock");
		
		comboAdd.addItem("Chicken");
		comboAdd.addItem("Goblin");
		comboAdd.addItem("Knight");
		comboAdd.addItem("Tomato");

		comboAdd.setBounds(1405, 230, 100, 30);
		this.add(comboAdd);
		
		MouseMotionListener mml = new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();

				System.out.println(ActionMouse + ","+ x+" , "+y);
				Case c = new Case(new Coordonnee(0, 0, 0));
				int taille = c.getTailleCasePixel();
				
				switch (ActionMouse) {
				case "remove":
					Case cRemove = Control.getPlateau().getListCase().get(Control.getPlateau()
							.coordinateToNum(x / c.getTailleCasePixel(), y / c.getTailleCasePixel()));
					if (!cRemove.getElement().isHero()) {
						cRemove.setElement(new Vide());
					}

					break;
				case "add":
					Case cadd = Control.getPlateau().getListCase().get(Control.getPlateau()
							.coordinateToNum(x / c.getTailleCasePixel(), y / c.getTailleCasePixel()));
					if (!cadd.getElement().isHero()) {
						
						switch (comboAdd.getSelectedItem().toString()) {
						case "Arbre":
							cadd.setElement(new Arbre());

							break;
						case "Bush":
							cadd.setElement(new Bush());
							break;
						case "Chest":
							cadd.setElement(new Chest());
							break;
						case "PanneauAffichage":
							cadd.setElement(new PanneauAffichage());
							break;
						case "Rock":
							cadd.setElement(new Rock());
							break;
						case "Chicken":
							Monstre m = new Chicken();
							cadd.setElement(m);
							Control.getListMonstre().add(m);
							break;
						case "Goblin":
							m = new Goblin();
							cadd.setElement(m);
							Control.getListMonstre().add(m);
							break;
						case "Knight":
							m = new Knight();
							cadd.setElement(m);
							Control.getListMonstre().add(m);
							break;
						case "Tomato":
							m = new Tomato();
							cadd.setElement(m);
							Control.getListMonstre().add(m);
							break;
						default:
							break;
						}
					}

					break;
				default:
					break;
				}

				repaint();

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};
		this.addMouseMotionListener(mml);

		MouseListener ml = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				int x = e.getX();
				int y = e.getY();

				System.out.println(ActionMouse + ","+ x+" , "+y);
				Case c = new Case(new Coordonnee(0, 0, 0));
				int taille = c.getTailleCasePixel();
				
				switch (ActionMouse) {
				case "remove":
					Case cRemove = Control.getPlateau().getListCase().get(Control.getPlateau()
							.coordinateToNum(x * c.getTailleCasePixel(), y * c.getTailleCasePixel()));
					if (!cRemove.getElement().isHero()) {
						cRemove.setElement(new Vide());
					}

					break;
				default:
					break;
				}

				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();

				System.out.println(ActionMouse + ","+ x+" , "+y);
				Case c = new Case(new Coordonnee(0, 0, 0));
				int taille = c.getTailleCasePixel();
				
				switch (ActionMouse) {
				case "remove":
					Case cRemove = Control.getPlateau().getListCase().get(Control.getPlateau()
							.coordinateToNum(x * c.getTailleCasePixel(), y * c.getTailleCasePixel()));
					if (!cRemove.getElement().isHero()) {
						cRemove.setElement(new Vide());
					}

					break;
				default:
					break;
				}

				repaint();
			}
		};

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (Control.isGameOver()) {
			this.GameOver();
		}

		// System.out.println("here4"+ control.getHero().getNombreRubi());

		nombreRubi.setText("" + Control.getNombreEtage());

		if (gameMode.equals("jeux")) {
			paintPlateauCentrerSurHero(g);
		} else {
			paintPlateau(g);
		}

		if (!this.pauseGame) {
			heroaction = Control.action(this.herodescision, heroaction);

		}

		try {
			// Thread.sleep(1000); // 1 fps
			Thread.sleep(fps); // 32 fps
			// Thread.sleep(16,666666); // 60 fps
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		repaint();

	}

	private void paintPlateauCentrerSurHero(Graphics g) {
		Plateau plateau = control.getPlateau();
		List<Case> listCaseAfficher = plateau.getListCaseAfficher(control.getHero());
		// dessinBackground (g ,listCaseAfficher );
		dessinForground(g, listCaseAfficher);

		dessinDark(g);
		dessinSideBar(g, control.getPlateau().getListCase().get(0));
	}

	private void dessinBackground(Graphics g, List<Case> listCaseAfficher) {
		Case c = listCaseAfficher.get(0);
		for (int x = 0; x < 20 + 2; x++) {
			for (int y = 0; y < 10 + 2; y++) {
				ImageIcon icon = new ImageIcon("hyrule/grass1.png");
				if ((x + y) % 2 == 1) {
					icon = new ImageIcon("hyrule/grass3.png");
				}
				/**
				 * if (control.getTimer()%32 < 16) { icon = new ImageIcon("hyrule/grass3.png");
				 * 
				 * 
				 * if ((x + y) % 2 == 1) { icon = new ImageIcon("hyrule/grass1.png"); } }
				 */
				Image img = icon.getImage();
				// g.drawImage(img,c.getElement().getCoordonneeVisuel(x, y, c,
				// control.getHero())[0]-c.getTailleCasePixel(),
				// c.getElement().getCoordonneeVisuel(x, y, c, control.getHero())[1] ,
				// 3*c.getTailleCasePixel(),3*c.getTailleCasePixel(), null);

				g.drawImage(img, x * c.getTailleCasePixel(), y * c.getTailleCasePixel(), c.getTailleCasePixel(),
						c.getTailleCasePixel(), null);
			}
		}
	}

	private void dessinForground(Graphics g, List<Case> listCaseAfficher) {
		Plateau plateau = control.getPlateau();
		int x = 0;
		int y = 0;
		// plateau.getListCase().get(control.getHero().getNumeroCase()).afficher();
		// System.out.println(listCaseAfficher.size());
		for (Case c : listCaseAfficher) {
			// System.out.println(x+" , "+y);
			if (!(c.isEmpty())) {

				g.drawImage(c.trouverTrapImage(plateau), c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[0],
						c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[1], 3 * c.getTailleCasePixel(),
						3 * c.getTailleCasePixel(), null);

				if (c.getElement().getNom().equals("Hero")) {
					g.drawImage(c.trouverElementImage(plateau), x * c.getTailleCasePixel() - c.getTailleCasePixel(),
							y * c.getTailleCasePixel() - c.getTailleCasePixel(), 3 * c.getTailleCasePixel(),
							3 * c.getTailleCasePixel(), null);
					g.drawImage(c.trouverItemImage(plateau),
							c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[0],
							c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[1], 3 * c.getTailleCasePixel(),
							3 * c.getTailleCasePixel(), null);

					if (((Hero) c.getElement()).hold()) {
						ImageIcon icon = new ImageIcon("hyrule/block/rock.png");
						Image img = icon.getImage();
						g.drawImage(img, x * c.getTailleCasePixel() - c.getTailleCasePixel(),
								y * c.getTailleCasePixel() - c.getTailleCasePixel() - c.getTailleCasePixel() * 2 / 3
										- (((Hero) c.getElement()).getFrame() + 1) * c.getTailleCasePixel() / 10,
								3 * c.getTailleCasePixel(), 3 * c.getTailleCasePixel(), null);

					}

				} else if (c.getElement().isMonstre()) {
					g.drawImage(c.trouverItemImage(plateau),
							c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[0],
							c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[1], 3 * c.getTailleCasePixel(),
							3 * c.getTailleCasePixel(), null);

					g.drawImage(c.trouverElementImage(plateau),
							c.getElement().getCoordonneeVisuel(x, y, c, control.getHero())[0],
							c.getElement().getCoordonneeVisuel(x, y, c, control.getHero())[1],
							3 * c.getTailleCasePixel(), 3 * c.getTailleCasePixel(), null);

					if (c.getElement().isMonstre()) {
						if (((Monstre) c.getElement()).getLife() < ((Monstre) c.getElement()).getMaxLife()
								&& ((Monstre) c.getElement()).getLife() > 0) {
							for (int i = 0; i < ((Monstre) c.getElement()).getMaxLife(); i++) {
								ImageIcon icon = new ImageIcon("hyrule/heart/noir.png");

								if (i < ((Monstre) c.getElement()).getLife()) {
									icon = new ImageIcon("hyrule/heart/rouge.png");
								}
								Image img = icon.getImage();
								int barreVieY = 3;
								switch (c.getElement().getNom()) {
								case "Knight":
									barreVieY = 10;
									break;
								case "Chicken":
									barreVieY = 3 / 2;
									break;
								case "Goblin":
									barreVieY = 2;
									break;
								case "Tomato":
									barreVieY = 2;
									break;
								default:
									break;
								}

								g.drawImage(img,
										c.getElement().getCoordonneeVisuel(x, y, c, control.getHero())[0]
												+ c.getTailleCasePixel() + 15 * i,
										c.getElement().getCoordonneeVisuel(x, y, c, control.getHero())[1]
												+ c.getTailleCasePixel() / barreVieY,
										15, 15, null);
							}
						}
					}
				} else {
					g.drawImage(c.trouverItemImage(plateau),
							c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[0],
							c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[1], 3 * c.getTailleCasePixel(),
							3 * c.getTailleCasePixel(), null);
					// g.drawImage(c.trouverElementImage(plateau),x*c.getTailleCasePixel()-c.getTailleCasePixel(),
					// y*c.getTailleCasePixel()-c.getTailleCasePixel() ,
					// 3*c.getTailleCasePixel(),3*c.getTailleCasePixel(), null);

					g.drawImage(c.trouverElementImage(plateau),
							c.getElement().getCoordonneeVisuel(x, y, c, control.getHero())[0],
							c.getElement().getCoordonneeVisuel(x, y, c, control.getHero())[1],
							3 * c.getTailleCasePixel(), 3 * c.getTailleCasePixel(), null);

				}

				g.drawImage(c.trouverProjectilImage(plateau),
						c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[0],
						c.getItem().getCoordonneeVisuel(x, y, c, control.getHero())[1], 3 * c.getTailleCasePixel(),
						3 * c.getTailleCasePixel(), null);

			}
			x++;
			if (x == 20) {
				x = 0;
				y++;
			}
		}

	}

	private void paintPlateau(Graphics g) {
		Plateau plateau = control.getPlateau();

		for (int i = 0; i < plateau.getNombreCaseX() * plateau.getNombreCaseY(); i++) {
			Case c = control.getPlateau().getListCase().get(i);
			dessinBackground(g, c);

		}
		for (int y = 0; y < plateau.getNombreCaseY(); y++) {
			for (int x = 0; x < plateau.getNombreCaseX(); x++) {
				for (int z = 0; z < plateau.getNombreCaseZ(); z++) {
					Case c = plateau.getListCase().get(plateau.coordinateToNum(x, y, z));
					dessinForgroundBlock(g, c);
					dessinForgroundUnite(g, c);
				}
			}

		}

		// dessinDark(g);
		// dessinSideBar(g, control.getPlateau().getListCase().get(0));

	}

	private void dessinDark(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		ImageIcon icon = new ImageIcon("hyrule/transparant.png");
		Image img = icon.getImage();

		if (img != null) {

			g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

			Area outter = new Area(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
			Case c = control.getPlateau().getListCase().get(control.getHero().getNumeroCase());
			int diametre = 400;
			Ellipse2D.Double inner = new Ellipse2D.Double(
					this.getWidth() / 2 - diametre / 2 - c.getTailleCasePixel() / 2,
					this.getHeight() / 2 - diametre / 2 - c.getTailleCasePixel() / 2, diametre, diametre);
			outter.subtract(new Area(inner));// remove the ellipse from the original area

			g2d.setColor(new Color(89, 89, 89));
			g2d.setColor(new Color(0, 0, 0, .7f));

			g2d.fill(outter);
		}
		g2d.dispose();
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
		g.drawImage(img, c.getCoordonnee().getX() * c.getTailleCasePixel() + 3,
				c.getCoordonnee().getY() * c.getTailleCasePixel() + 3, c.getTailleCasePixel(), c.getTailleCasePixel(),
				null);

	}

	private void dessinSideBar(Graphics g, Case c) {
		// TODO Auto-generated method stub
		this.add(this.nombreRubi);

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

	private void backToStartingMenu() {
		// TODO Auto-generated method stub
		remove(this.nombreRubi);
		Principale.getFrame().setPreferredSize(new Dimension(60 * 20, 60 * 10));
		Principale.getFrame().setContentPane(new PanneauStart());
		Principale.getFrame().pack();
		this.updateUI();
	}

	private void GameOver() {
		// this.removeKeyListener(key);
		remove(this.nombreRubi);
		Principale.getFrame().setPreferredSize(new Dimension(60 * 20, 60 * 10));
		Principale.getFrame().setContentPane(new PanneauGameOver());
		Principale.getFrame().pack();
		this.updateUI();
	}

}
