package monstre;

import block.Rock;
import block.Vide;
import damage.Damage;
import item.Heart;
import item.Item;
import item.Rubi;
import mobInterface.Attaque;
import mobInterface.Deplacement;
import mobInterface.DropItem;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;
import projectil.Fleche;
import projectil.Projectil;
import tool.Tool;

public class Goblin extends Monstre implements Deplacement,DropItem,Attaque{

	public Goblin() {
		super();
		this.setSpawnable(false);

		this.setExist(true);
		this.setFrame(0);
		this.setCurentAction("nothing");
		this.setDirection(new Direction("down"));
		this.setNom("Goblin");
		this.setMaxLife(2);
		this.setLife(this.getMaxLife() );
		this.setDamageMonstre(1, 0, 0, 0);
	}

	
	// ######################### A FAIRE #########################################

		
		// ######################### ACTION #########################################

		@Override
		public void action(Plateau plateau) {

			if (this.getCurentAction().equals("nothing")) {

			Case c = plateau.getListCase().get(this.getNumeroCase());

			int random = (int) (Math.random() * (3 - 0 + 1)) + 0;
			switch (random) {
			case 0:
				this.interactionDeplacement(plateau, c, new Direction("up"));
				break;

			case 1:
				this.interactionDeplacement(plateau, c, new Direction("down"));

				break;
			case 2:
				this.interactionDeplacement(plateau, c, new Direction("right"));
				break;

			case 3:
				this.interactionDeplacement(plateau, c, new Direction("left"));
				break;

			default:
				break;
			}
			}
		}

		// ######################### DEPLACEMENT #########################################

		@Override
		public void deplacer(Plateau plateau, Case caseAvant, Case caseApres) {
			this.setCurentAction("moving");
			this.setFrame(0);

			
			Projectil p = caseApres.getProjectil() ;
			if ( ! p.getNom().equals("Aire")) {
				this.perdreVie(p.getDamage(),plateau);
			}
			
			Coordonnee cordApres = new Coordonnee(caseApres.getCoordonnee());

			int num = plateau.coordinateToNum(caseAvant.getCoordonnee());
			Vide v = new Vide();
			plateau.getListCase().get(num).setElement(v);

			num = plateau.coordinateToNum(caseApres.getCoordonnee());

			plateau.getListCase().get(num).setElement(this);
			this.setNumeroCase(num);
			
		//	this.attaquer(plateau, caseApres);
		}

		@Override
		public void interactionDeplacement(Plateau plateau, Case caseAvant, Direction direction) {
			this.setDirection(direction);
			Case caseApres = plateau.getCaseDevantMemeZ(caseAvant, this.getDirection());

			switch (caseApres.getElement().getNom()) {

			case "Vide":
				this.deplacer(plateau,caseAvant, caseApres);
				break;
			case "Hero":
				this.setCurentAction("animationAttaque");
				this.attaquer(plateau, caseAvant);
				break;
			default:
				// System.out.println("hero est rentrer dans " + c.getElement().getNom());
				break;
			}
		}
		
		
		//######################### ATTAQUE #########################################

		// note he attaque the case in front if the case "c" given by the attribut 
		@Override
		public void attaquer(Plateau plateau, Case c) {
			
			plateau.getCaseDevantMemeZ(c, this.getDirection()).getElement().perdreVie(this.getDamage(), plateau);
			
		}
		

	//######################### PERDRE VIE #########################################	

		@Override
		public void perdreVie(Damage damage, Plateau p) {
			if (! this.getCurentAction().equals("animationDeath")) {
				if (damage.doDamage(this)) {
					this.setLife(this.getLife() - damage.getEpee() - damage.getProjectil() - damage.getExplosion());
				}
				if (getLife() <= 0) {
					this.setCurentAction("animationDeath");
					this.setFrame(0);
				}
			}
		}

		// ######################### DROP ITEM #########################################

		@Override
		public void dropItem(Case c) {
			if (c.getItem().getNom().equals("Rien")) {
				int x = (int) (Math.random() * (100 + 1 - 1)) + 1;
				if (x > 30) {
					c.setItem(new Rubi());
				} else if (x < 10) {
					c.setItem(new Heart());
				}

			}

		}

		// ######################### IMAGE #########################################



	@Override
	public String trouverImage(Plateau p, Case c) {
		// System.out.println(this.getFrame());

		String icon = "hyrule/goblin/beat/D1.png";
		;
		switch (this.getCurentAction()) {

		case "nothing":
			icon = this.imageNothing();
			this.setFrame((getFrame() + 1) % 6);
			break;

		case "animationDeath":
			int num = this.getFrame() / 2 + 1;
			// System.out.println(num);
			icon = "hyrule/death/" + num + ".png";
			this.setFrame((getFrame() + 1) % 14);

			if (this.getFrame() == 4) {
				this.dropItem(c);
			}
			if (this.getFrame() == 0) {
				this.setCurentAction("dead");
				this.mourir();
			}
			break;
		case "moving":
			icon = this.imageMoving();
			this.setFrame((getFrame() + 1) % 6);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;
		case "animationAttaque":
			icon = this.imageAttaque();
			this.setFrame((getFrame() + 1) % 6);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;

		default:
			break;

		}

		return icon;
	}

	private String imageAttaque() {
		int num = this.getFrame() + 1;
		// System.out.println("frame: "+this.getFrame());

		// System.out.println("num: "+num);

		String icon = "hyrule/goblin/attaque/R" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/goblin/attaque/R" + num + ".png";

			break;
		case "down":
			icon = "hyrule/goblin/attaque/L" + num + ".png";

			break;
		case "left":
			icon = "hyrule/goblin/attaque/L" + num + ".png";
			break;
		case "right":
			icon = "hyrule/goblin/attaque/R" + num + ".png";

			break;
		default:
			break;

		}
		return icon;

	}

	private String imageMoving() {
		int num = this.getFrame() + 1;
		// System.out.println("frame: "+this.getFrame());

		// System.out.println("num: "+num);

		String icon = "hyrule/goblin/moving/R" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/goblin/moving/R" + num + ".png";

			break;
		case "down":
			icon = "hyrule/goblin/moving/L" + num + ".png";

			break;
		case "left":
			icon = "hyrule/goblin/moving/L" + num + ".png";
			break;
		case "right":
			icon = "hyrule/goblin/moving/R" + num + ".png";

			break;
		default:
			break;

		}
		return icon;

	}

	private String imageNothing() {
		int num = this.getFrame() + 1;
		// System.out.println("frame: "+this.getFrame());

		// System.out.println("num: "+num);

		String icon = "hyrule/goblin/beat/R" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/goblin/beat/R" + num + ".png";
			break;
		case "down":
			icon = "hyrule/goblin/beat/L" + num + ".png";
			break;
		case "left":
			icon = "hyrule/goblin/beat/L" + num + ".png";
			break;
		case "right":
			icon = "hyrule/goblin/beat/R" + num + ".png";
			break;
		default:
			break;

		}

		return icon;

	}

	@Override
	public int trouverX(Case c) {
		int res = c.getCoordonnee().getX()*c.getTailleCasePixel()+15-2*c.getTailleCasePixel();
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res + num * 5 - c.getTailleCasePixel();
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res - num * 5 + c.getTailleCasePixel();
		}
		return res;
	}

	@Override
	public int trouverY(Case c) {
		int res = c.getCoordonnee().getY()*c.getTailleCasePixel() -15 -c.getTailleCasePixel();
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame()  + 1;
			res = res - num * c.getTailleCasePixel()/6 + c.getTailleCasePixel();
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame()  + 1;
			res = res + num * c.getTailleCasePixel()/6 - c.getTailleCasePixel();
		} else if (this.getCurentAction().equals("moving")) {
			int num = -(this.getFrame() - 6) * (this.getFrame() - 6) + 10;
			res = res - num;
			// System.out.println(this.getFrame() +","+ (num) );
		}
		return res;
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}


	@Override
	public int trouverBarreVieX(Case c) {
		// TODO Auto-generated method stub
		return this.trouverX(c) + 45;
	}

	@Override
	public int trouverBarreVieY(Case c) {
		// TODO Auto-generated method stub
		return this.trouverY(c) + 20;
	}


	

	
	
	

}
