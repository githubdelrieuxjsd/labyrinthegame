package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import baseDonnee.Niveau;
import block.Arbre;
import block.Block;
import block.Bush;
import block.Chest;
import block.Rock;
import block.Vide;
import item.Bomb;
import item.Key;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Element;
import model.Hero;
import model.Plateau;
import monstre.Chicken;
import monstre.Goblin;
import monstre.Knight;
import monstre.Monstre;
import monstre.Tomato;
import projectil.Aire;
import projectil.Fleche;
import projectil.Projectil;
import tool.Tool;

public class Control {

	private boolean freegame = true;

	private static Plateau plateau;

	private static Hero hero;
	private static List<Monstre> listMonstre;
	private static List<Block> listBlock;
	private static List<Projectil> listProjectil;

	private static int numPlateau = 0;
	private static int timer;

	/**
	 * Constructeur redefinir ControlRandom creation du hero placer en (4,1) = (x,y)
	 * creation list monstre vide creation list Block vide gernerer le plateau
	 * initialisation du timer du jeux a 0;
	 * 
	 * @throws IOException
	 */
	public Control() {
		super();

		hero = new Hero();

		listMonstre = new ArrayList<Monstre>();
		listBlock = new ArrayList<Block>();
		listProjectil = new ArrayList<Projectil>();

		Monstre.setHero(hero);
		generationPlateau();
		// Niveau.generationMapRandom();
	}

	/**
	 * ajout dans la liste de block des object block (rock , tree , bush ) ajout
	 * dans la liste des monstre (knight , goblin tomato , chicken) appele du
	 * constructeur du plateau
	 * 
	 * @throws IOException
	 */

	public static void generationPlateau() {
		listMonstre = new ArrayList<Monstre>();
		listBlock = new ArrayList<Block>();
		listProjectil = new ArrayList<Projectil>();

		creationBlock();
		creationMonstre();
		plateau = new Plateau(hero , listBlock, listMonstre);

		//plateau = new Plateau(hero, getMapNum());
		if (!plateau.getListCase().isEmpty()) {
			plateau.getListCase().get(0).setCompt(0);
		}
		timer = 0;
	}

	private static int getMapNum() {
		int res = numPlateau;
		switch (hero.getDirection().getDirection()) {
		case "up":
			res = res - 3;
			break;
		case "down":
			res = res + 3;
			break;
		case "left":
			if (numPlateau == 4 || numPlateau == 7 ) {
				res = 0;
			}
			else {
				res = res - 1;
			}
			break;
		case "right":
			if (numPlateau == 3 || numPlateau == 6 ) {
				res = 0;
			}
			else {
				res = res + 1;
			}
			break;
		default:
		}
		//System.out.println("numPlateau :" + res);
		numPlateau = res;
		return res;
	}

	private static void creationBlock() {
		// TODO Auto-generated method stub
		creationBush(100);
		creationArbre(100);
		creationRock(100);
		creationChest(100);
	}

	private static void creationMonstre() {
		// TODO Auto-generated method stub
		creationKnight(0);
		creationChicken(100);
		creationGoblin(0);
		creationTomato(0);
	}

	private static void creationTomato(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Tomato());
		}
	}

	private static void creationGoblin(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Goblin());
		}
	}

	private static void creationChicken(int nombre) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Chicken());

		}
	}

	private static void creationKnight(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Knight());

		}

	}

	private static void creationChest(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listBlock.add(new Chest());

		}		
	}

	
	private static void creationBush(int nombre) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nombre; i++) {
			listBlock.add(new Bush());

		}
	}

	private static void creationRock(int nombre) {
		// TODO Auto-generated method stub

		for (int i = 0; i < nombre; i++) {
			listBlock.add(new Rock());

		}
	}

	private static void creationArbre(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listBlock.add(new Arbre());

		}

	}

	public boolean action(String herodescision, boolean heroaction) {

		if (this.listMonstre.isEmpty() && false) {
			freegame = true;
			int x = (int) (Math.random() * (plateau.getNombreCaseX() - 1 + 1 - 0)) + 0;
			int y = (int) (Math.random() * (plateau.getNombreCaseY() - 1 + 1 - 0)) + 0;
			plateau.getCase(new Coordonnee(x, y, 0)).setElement(new Vide());
			plateau.getCase(new Coordonnee(x, y, 0)).setItem(new Key());
		}

		removeProjectil();
		this.timer++;

		if (this.timer % 2 == 0) {
			deplacerProjectil();
		}

		if (this.timer % 16 == 0) {

			for (Monstre monstre : this.listMonstre) {
				monstre.action(plateau);
			}
		}
		// change betwen 3 and 16
		if (this.timer % 16 == 0 || freegame) {
			if (heroaction) {
				this.hero.action(this.plateau, herodescision);
				// this.plateau.afficher();
			}
			return false;
		}
		// System.out.println(heroaction);
		return heroaction;
	}

	private void deplacerProjectil() {
		for (Projectil p : this.listProjectil) {
			p.interactionDeplacement(plateau, plateau.getListCase().get(p.getNumeroCase()), p.getDirection());
		}
	}

	public static void removeMonstre(Monstre m) {
		listMonstre.remove(m);
		plateau.getListCase().get(m.getNumeroCase()).setElement(new Vide());
	}

	public static void removeBlock(Block b) {
		listBlock.remove(b);
		plateau.getListCase().get(b.getNumeroCase()).setElement(new Vide());

	}

	public void removeProjectil() {
		List<Projectil> listProjectildead = new ArrayList<Projectil>();
		for (Projectil p : this.listProjectil) {
			if (p.getCurentAction().equals("detruir")) {
				listProjectildead.add(p);
			}
		}
		for (Projectil p : listProjectildead) {
			listProjectil.remove(p);
			plateau.getListCase().get(p.getNumeroCase()).setProjectil(new Aire());

		}
	}

	public static void tirer(Projectil projectil, Case c) {
		plateau.placerProjectil(projectil, c.getCoordonnee());
		listProjectil.add(projectil);
	}

	// ########################### Affichage des informations ##################
	public void information() {
		System.out.println();
		System.out.println("Plateau");
		this.plateau.afficher();

		System.out.println();
		System.out.println("Hero");
		this.hero.afficher();

		System.out.println();
		System.out.println("Monstre");

		for (Monstre m : listMonstre) {
			m.afficher();
		}

	}

	// ######################## GETTER SETTER ################################

	public List<Monstre> getListMonstre() {
		return listMonstre;
	}

	public List<Block> getListBlock() {
		return listBlock;
	}

	public void setListBlock(List<Block> listBlock) {
		this.listBlock = listBlock;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

}
