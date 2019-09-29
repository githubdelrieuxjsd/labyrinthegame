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
import model.Hero;
import model.Key;
import model.Minotaure;
import model.Monstre;
import model.Plateau;
import model.Projectil;
import model.Rock;
import model.Vide;
import tool.Tool;

public class Control {
	
	
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
		creationMinotaure();
		creationChicken();
	}

	private void creationChicken() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {

			int random1 = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int random2 = (int) (Math.random() * (16 + 1 - 1)) + 1;

			listMonstre.add(new Chicken(random1, random2));

		}
	}

	private void creationMinotaure() {
		// TODO Auto-generated method stub
		listMonstre.add(new Minotaure(12, 12, this.hero) );
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
			for (Monstre m : listMonstre) {
				if (m.getNom().equals("Chicken")) {
					if (m.getCurentAction().equals("transformation")) {
						monstreTransform.add(m);
						
					}else {
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
	}
	
	private void actionMinotaure() {
		for (Monstre m : listMonstre) {
			if (m.getNom().equals("Minotaure")) {
				((Minotaure) m).action(plateau);
			}
		}		
	}


	public void deplacerProgectil() {

		if (!hero.getListProjectil().isEmpty()) {
			if (hero.getListProjectil().get(0).getFrame() == 3) {
				hero.getListProjectil().get(0).deplacer(plateau);
			}
		}

		Minotaure minotaure = (Minotaure) listMonstre.get(0);
		if (!minotaure.getListProjectil().isEmpty()) {
			for (Projectil p : minotaure.getListProjectil()) {
				if (p.getFrame() == 3) {
					p.deplacer(plateau);
				}
			}
		}

	}

	public void action() {
		this.timer++;
		deplacerProgectil();
		if (this.timer%16 == 0) {
			deplaceChicken();
			actionMinotaure();

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
	
	
	
	
}
