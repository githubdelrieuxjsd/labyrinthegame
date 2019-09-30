package controleur;

import java.util.ArrayList;
import java.util.List;

import model.Arbre;
import model.Block;
import model.Bomb;
import model.Bush;
import model.Case;
import model.Chicken;
import model.Coordonnee;
import model.Direction;
import model.Goblin;
import model.Hero;
import model.Key;
import model.Minotaure;
import model.Monstre;
import model.Plateau;
import model.Projectil;
import model.Rock;
import model.Tomato;
import model.Vide;
import tool.Tool;

public class Control {
	
	private boolean freegame = true;
	
	private ControlRandom ctrRandom ;
	
	private Plateau plateau;
	private Hero hero;

	private static List<Monstre> listMonstre;
	private List<Block> listBlock;
	
	private int scort;
	private int timer;

	
	public Control() {
		super();
		this.ctrRandom = new ControlRandom();
		start();
		this.scort = 0;
		this.timer = 0;
	}

	public void start() {

		listMonstre = new ArrayList<Monstre>();
		listBlock = new ArrayList<Block>();
		hero = new Hero(4, 1);
		creationBlock();
		creationMonstre();
		plateau = new Plateau(hero, listBlock, listMonstre);
	}

	

	private void creationMonstre() {
		// TODO Auto-generated method stub
		creationMinotaure(0);
		creationChicken(0);
		creationGoblin(0);
		creationTomato(10);
	}
	
	private void creationTomato(int nombre) {
		for (int i = 0; i < nombre; i++) {
			int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;
			listMonstre.add(new Tomato(random1, random2));
		}		
}

	private void creationGoblin(int nombre) {
				for (int i = 0; i < nombre; i++) {
					int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
					int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;
					listMonstre.add(new Goblin(random1, random2));
				}		
	}

	private void creationChicken(int nombre) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nombre; i++) {

			int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;

			listMonstre.add(new Chicken(random1, random2));

		}
	}

	private void creationMinotaure(int nombre) {
		for (int i = 0; i < nombre; i++) {

			int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;

			listMonstre.add(new Minotaure(random1, random2, this.hero) );

		}
		
	}

	private void creationBlock() {
		// TODO Auto-generated method stub
		creationBush();
		creationArbre();
		creationRock();
	}

	private void creationBush() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 30; i++) {

			int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;

			listBlock.add(new Bush(random1, random2));

		}
	}

	private void creationRock() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 30; i++) {

			int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;

			listBlock.add(new Rock(random1, random2));

		}
	}

	private void creationArbre() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 30; i++) {

			int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;

			listBlock.add(new Arbre(random1, random2));

		}

		contourArbre();
	}

	private void contourArbre() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 25; i++) {
			listBlock.add(new Arbre(0, i));
			listBlock.add(new Arbre(i, 0));
			listBlock.add(new Arbre(i, 17));
			listBlock.add(new Arbre(24, i));
		}
	}

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

	public void deplaceChicken() {
		List <Monstre> monstreTransform = new ArrayList<Monstre>();
		List <Monstre> monstreDead = new ArrayList<Monstre>();

			for (Monstre m : listMonstre) {
				if (m.getNom().equals("Chicken") || m.getNom().equals("Goblin")) {
					if (m.getCurentAction().equals("transformation")) {
						monstreTransform.add(m);
						
					}else if (m.getCurentAction().equals("dead")) {
						monstreDead.add(m);
					}
					else {
						m.deplacer(ctrRandom.deplacement(), plateau);
					}
				}
			}
			
			for (Monstre m : monstreTransform) {
				this.listMonstre.remove(m);
				Minotaure minotaure = new Minotaure(m.getCoordonnee()) ;
				this.listMonstre.add(minotaure);
				this.plateau.getCase(m.getCoordonnee()).setElement(minotaure);
			}
			for (Monstre m : monstreDead) {
				this.listMonstre.remove(m);
			}
	}
	
	private void actionMinotaure() {
		List <Monstre> monstreDead = new ArrayList<Monstre>();

		for (Monstre m : listMonstre) {
			 if (m.getCurentAction().equals("dead")) {
					monstreDead.add(m);
				}
			if (m.getNom().equals("Minotaure")) {
				((Minotaure) m).action(plateau);
			}
		}		
		for (Monstre m : monstreDead) {
			this.listMonstre.remove(m);
		}
	}


	public void deplacerProgectil() {
		List <Projectil> projectilDead = new ArrayList<Projectil>();

		if (!hero.getListProjectil().isEmpty()) {
			for(Projectil p : hero.getListProjectil() ) {
				if (p.getFrame() == 3 ) {
					p.deplacer(plateau);
				}
				if ( ! p.getExist()) {
					projectilDead.add(p);
				}
			}
			
		}
		for (Projectil p : projectilDead) {
			this.hero.getListProjectil().remove(p);
		}
	}

	public boolean action(String herodescision , boolean heroaction) {
		
		this.timer++;
		deplacerProgectil();

		if (this.timer%32 == 0) {

			deplaceChicken();
			actionMinotaure();
			if (heroaction) {
				actionHero(herodescision);
			}
			return false;
		}
		if (listMonstre.isEmpty() || freegame) {
			if (heroaction) {
			//System.out.println("Win");
			actionHero(herodescision);
			return false;
			}
		}
		return heroaction;
	}


	
	private void actionHero(String descision) {
		switch(descision) {
		case "moveUp": deplacerHero(new Direction ("up"));
		break;
		case "moveDown": deplacerHero(new Direction ("down"));
			break;
		case "moveRight": deplacerHero(new Direction ("right"));
			break;
		case "moveLeft":deplacerHero(new Direction ("left"));
			break;
		case "tirer" : animationBowHero(); 
			break;
		case "attaque": animationAttackHero();
			break;
		case "bomb": animationPlaceBombHero();
			break;
		default :
			break;
		}
	}

	public void deplacerHero(Direction direction) {
		// TODO Auto-generated method stub
		if (this.hero.getCurentAction().equals("nothing")&& onTheBeat() ) {
			this.hero.deplacer(direction, plateau);
		}
	}

	public void animationBowHero() {
		// TODO Auto-generated method stub
		if (this.hero.getCurentAction().equals("nothing") && onTheBeat() ) {
			this.hero.animationBow();;
		}
	}
	public void animationPlaceBombHero() {
		if (this.hero.getCurentAction().equals("nothing")&& onTheBeat() ) {
			this.hero.placerBomb(plateau);
		}		
	}
	
	public void animationAttackHero() {
		// TODO Auto-generated method stub
		if (this.hero.getCurentAction().equals("nothing")&& onTheBeat() ) {
			this.hero.animationSword();
		}
	}

	


	
	private boolean onTheBeat () {
		if (timer%16 == 0 || timer%16 == 1 || timer%16 == 15 || timer%16 == 14 || timer%16 == 2 
				 ||timer%16 == 3 || timer%16 == 13 
				|| timer%16 == 4 || timer%16 == 12
				|| timer%16 == 5 || timer%16 == 11
				) {
			return true ;
			}
		return true ;
		}

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
	
	
	
	
}
