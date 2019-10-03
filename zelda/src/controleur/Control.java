package controleur;

import java.util.ArrayList;
import java.util.List;

import block.Arbre;
import block.Block;
import block.Bush;
import block.Rock;
import block.Vide;
import item.Bomb;
import item.Key;
import model.Case;
import model.Coordonnee;
import model.Direction;
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

	private static  Hero hero;
	private static List<Monstre> listMonstre;
	private static List<Block> listBlock;
	private static List <Projectil> listProjectil ;
	
	
	private int timer;

	/**
	 * Constructeur redefinir ControlRandom creation du hero placer en (4,1) = (x,y)
	 * creation list monstre vide creation list Block vide gernerer le plateau
	 * initialisation du timer du jeux a 0;
	 */
	public Control() {
		super();

		hero = new Hero();

		listMonstre = new ArrayList<Monstre>();
		listBlock = new ArrayList<Block>();
		listProjectil = new ArrayList<Projectil>();

		Monstre.setHero(hero);

		generationPlateau();

		this.timer = 0;
	}

	/**
	 * ajout dans la liste de block des object block (rock , tree , bush ) ajout
	 * dans la liste des monstre (knight , goblin tomato , chicken) appele du
	 * constructeur du plateau
	 */
	private void generationPlateau() {
		// TODO Auto-generated method stub
		creationBlock();
		creationMonstre();
		plateau = new Plateau(hero, listBlock, listMonstre);
	}

	private void creationBlock() {
		// TODO Auto-generated method stub
		creationBush(0);
		creationArbre(0);
		creationRock(1);
	}

	private void creationMonstre() {
		// TODO Auto-generated method stub
		creationMinotaure(0);
		creationChicken(0);
		creationGoblin(0);
		creationTomato(0);
	}

	private void creationTomato(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Tomato());
		}
	}

	private void creationGoblin(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Goblin());
		}
	}

	private void creationChicken(int nombre) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Chicken());

		}
	}

	private void creationMinotaure(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listMonstre.add(new Knight());

		}

	}

	private void creationBush(int nombre) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nombre; i++) {
			listBlock.add(new Bush());

		}
	}

	private void creationRock(int nombre) {
		// TODO Auto-generated method stub

		for (int i = 0; i < nombre; i++) {
			listBlock.add(new Rock());

		}
	}

	private void creationArbre(int nombre) {
		for (int i = 0; i < nombre; i++) {
			listBlock.add(new Arbre());

		}

	}

	public boolean action(String herodescision, boolean heroaction) {

		removeProjectil();
		this.timer++;
		if (listMonstre.isEmpty() ) {
			freegame =true;
		}
		
		if (this.timer % 2 == 0) {
			 deplacerProjectil();
		}
		
		if (this.timer % 15 == 0) {

			for (Monstre monstre : this.listMonstre) {
				 monstre.action(plateau);
			}
		}
		if (this.timer % 15 == 0 || freegame) {
			if (heroaction) {
				this.hero.action(this.plateau, herodescision);
			}
			return false;
		}

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
			if (p.getCurentAction().equals("detruir") ) {
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
