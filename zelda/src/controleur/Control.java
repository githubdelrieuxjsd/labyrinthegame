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
import item.Rien;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Element;
import model.Hero;
import model.Plateau;
import model.TrapVide;
import monstre.Chicken;
import monstre.Goblin;
import monstre.Knight;
import monstre.Monstre;
import monstre.Tomato;
import option.Cheat;
import projectil.Aire;
import projectil.Fleche;
import projectil.Projectil;
import tool.Tool;

public class Control {


	private static Plateau plateau;

	private static Hero hero;
	private static List<Monstre> listMonstre;
	private static List<Block> listBlock;
	private static List<Projectil> listProjectil;

	private static int numPlateau = 0;
	
	private static int nombreEtage ; 
	
	private static boolean gameOver ;

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
		nombreEtage = 0;
		gameOver = false ;
		
		hero = new Hero();
		Monstre.setHero(hero);		
		
		creerNouveauPlateau();
	}

	public static void creerNouveauPlateau() {
		listMonstre = new ArrayList<Monstre>();
		listBlock = new ArrayList<Block>();
		listProjectil = new ArrayList<Projectil>();

		creationBlock();
		creationMonstre();
		//plateau = new Plateau(100,50);
		plateau = new Plateau(hero, generationListCase(100, 50, 1, 10), listMonstre,listBlock, 100, 50);
		// generationPlateau();
		// Niveau.generationMapRandom();
		timer = 0;
	}
	
	private static List<Case> generationListCase(int nombreCaseX, int nombreCaseY, int nombreCaseZ, int nombreSalle) {
		List<Case> res = new ArrayList<Case>();
		Case c = new Case (new Coordonnee(0,0,0));
		c.setCompt(0);

		// ################## ajout Arbre sur tt la map ######################

		for (int z = 0; z < nombreCaseZ; z++) {
			for (int y = 0; y < nombreCaseY; y++) {
				for (int x = 0; x < nombreCaseX; x++) {
					Case caseVide = new Case(new Coordonnee(x, y, z));
					caseVide.setElement(new Arbre());
					// caseVide.setElement(new Bush());
					caseVide.setItem(new Rien());
					// caseVide.setItem(new Rubi());
					caseVide.setProjectil(new Aire());
					caseVide.setTrap(new TrapVide());
					/**
					 * if ( y<5 || y>nombreCaseY-5 || x<5 || x>nombreCaseX-5) {
					 * caseVide.setElement(new Arbre()); }
					 */
					res.add(caseVide);
				}
			}
		}
		// ################## ajout salle ######################
		int posx = 0;
		int posy = 0;
		int[][] detailSalle = new int[nombreSalle][4];

		for (int i = 0; i < nombreSalle; i++) {
			boolean casePlacer = false;
			while (!casePlacer) {
				int tailleSalleY = (int) (Math.random() * ((12) - 4 + 1)) + 4;
				int tailleSalleX = (int) (Math.random() * ((12) - 4 + 1)) + 4;
				// tailleSalleY = 10;
				// tailleSalleX = 10;

				int xSalle = (int) (Math.random() * ((nombreCaseX - 6 - tailleSalleX) - 6 + 1)) + 6;
				int ySalle = (int) (Math.random() * ((nombreCaseY - 6 - tailleSalleY) - 6 + 1)) + 6;

				// xSalle = posx*25 +5;
				// ySalle = posy*20 +5;

				// System.out.println(" "+xSalle+" ,"+ySalle);

				boolean peuetreplacer = true;
				for (int y = ySalle - 4; y < ySalle + tailleSalleY + 4; y++) {
					for (int x = xSalle - 4; x < xSalle + tailleSalleX + 4; x++) {
						
						int num = x + nombreCaseX * y;
						if (res.get(num).getElement().getNom().equals("Vide")) {
							peuetreplacer = false;
						}
					}
				}

				if (peuetreplacer) {
					detailSalle[i][0] = xSalle + tailleSalleX / 2;
					detailSalle[i][1] = ySalle + tailleSalleY / 2;

					casePlacer = true;
					for (int y = ySalle; y < ySalle + tailleSalleY; y++) {
						for (int x = xSalle; x < xSalle + tailleSalleX; x++) {
							int num = x + nombreCaseX * y;
							res.get(num).setElement(new Vide(true));
						}
					}
					int num = detailSalle[i][0] + nombreCaseX * detailSalle[i][1];
					res.get(num).setElement(new Arbre());

					posx++;
					if (posx == 8) {
						posx = 0;
						posy++;
					}
				}
			}
		}

		// ################## liaison salle ######################
		
		int[][] tableauLiaison = definirLiaison(detailSalle);
		//System.out.print(tableauLiaison.length);
		//int[][] tableauLiaison = { { 60,20,50,10 }, { 10, 25, 17, 17 } };
		//int [][] tableauLiaison = {{50,10,60,20},{17,17,10,25}} ;

		for (int i = 0; i < tableauLiaison.length; i++) {
			int point1x = tableauLiaison[i][0];
			int point1y = tableauLiaison[i][1];
			int point2x = tableauLiaison[i][2];
			int point2y = tableauLiaison[i][3];

			
			if (point1x < point2x && point1y < point2y ) {
				for (int x = point1x; x < point2x+1; x++) {
					

					res.get(x + nombreCaseX * point1y).setElement(new Vide());
				}
				for (int y = point1y; y < point2y; y++) {

					res.get(point2x + nombreCaseX * y).setElement(new Vide());
				}
				
			} else if (point1x > point2x && point1y < point2y) {
				for (int x = point2x; x < point1x+1; x++) {
					res.get(x + nombreCaseX * point2y).setElement(new Vide());
				}
				for (int y = point1y; y < point2y; y++) {
					res.get(point1x + nombreCaseX * y).setElement(new Vide());
				}
			} else if (point1x > point2x && point1y > point2y) {
				for (int x = point2x; x < point1x+1; x++) {
					res.get(x + nombreCaseX * point2y).setElement(new Vide());
				}
				for (int y = point2y; y < point1y; y++) {
					res.get(point1x + nombreCaseX * y).setElement(new Vide());
				}
			} else {
				for (int x = point1x; x < point2x+1; x++) {
					res.get(x + nombreCaseX * point1y).setElement(new Vide());
				}
				for (int y = point2y; y < point1y; y++) {
					res.get(point2x + nombreCaseX * y).setElement(new Vide());
				}
			}

			//res.get(Tool.CoordinateToNum(point1x, point1y, 0)).setElement(new Vide());
			//res.get(Tool.CoordinateToNum(point2x, point2y, 0)).setElement(new Vide());

		}

		return res;
	}

	private static int[][] definirLiaison(int[][] detailSalle) {
		int nombreLiaison = (detailSalle.length - 2) * 2;
		int[][] detailSalleTrier = new int[detailSalle.length][4];

		for (int n = 0; n < detailSalle.length; n++) {
			int minSalle = 0;
			double minDistance = Math.sqrt(Math.pow(detailSalle[0][0], 2) + Math.pow(detailSalle[0][1], 2));

			for (int i = 1; i < detailSalle.length; i++) {
				double distance = Math.sqrt(Math.pow(detailSalle[i][0], 2) + Math.pow(detailSalle[i][1], 2));
				if (distance < minDistance) {
					minDistance = distance;
					minSalle = i;
				}
			}
			// System.out.println(minDistance);
			int transX = detailSalle[minSalle][0];
			int transY = detailSalle[minSalle][1];

			detailSalleTrier[n][0] = transX;
			detailSalleTrier[n][1] = transY;

			detailSalle[minSalle][0] = 8000;
			detailSalle[minSalle][1] = 8000;
		}

		int[][] res = new int[nombreLiaison][4];
		int numPoint = 0;
		for (int n = 0; n < (detailSalle.length-2)*2 ; n += 2) {
			int transX1 = detailSalleTrier[numPoint][0];
			int transY1 = detailSalleTrier[numPoint][1];
			int transX2 = detailSalleTrier[numPoint + 1][0];
			int transY2 = detailSalleTrier[numPoint + 1][1];
			int transX3 = detailSalleTrier[numPoint + 2][0];
			int transY3 = detailSalleTrier[numPoint + 2][1];

			res[n][0] = transX1;
			res[n][1] = transY1;
			res[n][2] = transX2;
			res[n][3] = transY2;

			res[n + 1][0] = transX1;
			res[n + 1][1] = transY1;
			res[n + 1][2] = transX3;
			res[n + 1][3] = transY3;

			numPoint++;
		}
/**
		System.out.println();
		System.out.println();

		for (int n = 0; n < res.length; n++) {
			System.out.print(res[n][0] + ", ");
			System.out.print(res[n][1] + ", ");
			System.out.print(res[n][2] + ", ");
			System.out.print(res[n][3] + ", ");
			System.out.println();
		}
*/
		return res;
	}

	/**
	 * ajout dans la liste de block des object block (rock , tree , bush ) ajout
	 * dans la liste des monstre (knight , goblin tomato , chicken) appele du
	 * constructeur du plateau
	 *
	 */

	public static void generationPlateau() {
		listMonstre = new ArrayList<Monstre>();
		listBlock = new ArrayList<Block>();
		listProjectil = new ArrayList<Projectil>();

		creationBlock();
		creationMonstre();
		plateau = new Plateau(hero, listBlock, listMonstre);

		// plateau = new Plateau(hero, getMapNum());
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
			if (numPlateau == 4 || numPlateau == 7) {
				res = 0;
			} else {
				res = res - 1;
			}
			break;
		case "right":
			if (numPlateau == 3 || numPlateau == 6) {
				res = 0;
			} else {
				res = res + 1;
			}
			break;
		default:
		}
		// System.out.println("numPlateau :" + res);
		numPlateau = res;
		return res;
	}

	private static void creationBlock() {
		// TODO Auto-generated method stub
		creationBush(100);
		creationArbre(0);
		creationRock(50);
		creationChest(5);
	}

	private static void creationMonstre() {
		// TODO Auto-generated method stub
		creationKnight(20);
		creationChicken(70);
		creationGoblin(100);
		creationTomato(10);
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

	public static boolean action(String herodescision, boolean heroaction) {

		if (Control.listMonstre.isEmpty() && false) {
			int x = (int) (Math.random() * (plateau.getNombreCaseX() - 1 + 1 - 0)) + 0;
			int y = (int) (Math.random() * (plateau.getNombreCaseY() - 1 + 1 - 0)) + 0;
			plateau.getCase(new Coordonnee(x, y, 0)).setElement(new Vide());
			plateau.getCase(new Coordonnee(x, y, 0)).setItem(new Key());
		}

		removeProjectil();
		Control.timer++;

		if (Control.timer % 2 == 0) {
			deplacerProjectil();
		}

		if (Control.timer % 16 == 0 && Cheat.monstreCanDoAction() ) {

			for (Monstre monstre : Control.listMonstre) {
				monstre.action(plateau);
			}
		}
		// change betwen 3 and 16
		if (Control.timer % 3 == 0 || Cheat.heroCanDoActionEvryTick()) {
			if (heroaction) {
				Control.hero.action(Control.plateau, herodescision);
				// this.plateau.afficher();
			}
			return false;
		}
		// System.out.println(heroaction);
		return heroaction;
	}

	private static void deplacerProjectil() {
		for (Projectil p : Control.listProjectil) {
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

	public static void removeProjectil() {
		List<Projectil> listProjectildead = new ArrayList<Projectil>();
		for (Projectil p : Control.listProjectil) {
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

	public static List<Monstre> getListMonstre() {
		return listMonstre;
	}

	public static List<Block> getListBlock() {
		return listBlock;
	}

	public static void setListBlock(List<Block> listBlock) {
		Control.listBlock = listBlock;
	}

	public static Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public static  Hero getHero() {
		return hero;
	}

	public static void setHero(Hero hero) {
		Control.hero = hero;
	}

	public int getTimer() {
		// TODO Auto-generated method stub
		return timer;
	}
	
	public static int getNombreEtage() {
		return nombreEtage;
	}

	public static void setNombreEtage(int nombreEtage) {
		Control.nombreEtage = nombreEtage;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		Control.gameOver = gameOver;
	}

	


}
