package monstre;


import controleur.Control;
import damage.Damage;
import item.Heart;
import item.Rubi;
import mobInterface.DropItem;
import mobInterface.Tirer;
import model.Case;
import model.Direction;
import model.Plateau;
import projectil.Fleche;
import projectil.Pierre;

public class Tomato extends Monstre implements DropItem, Tirer {

	int nombreAction = 0;

	public Tomato() {
		super();
		this.setSpawnable(false);

		this.setExist(true);
		this.setFrame(0);
		this.setCurentAction("disappear");
		this.setDirection(new Direction("up"));
		this.setNom("Tomato");
		this.setMaxLife(3);
		this.setLife(this.getMaxLife());
		this.setDamageMonstre(0, 0, 0, 0);
	}


	// ######################### ACTION #########################################

	@Override
	public void action(Plateau plateau) {
		if (this.getCurentAction().equals("disappear")) {

		Case c = plateau.getListCase().get(this.getNumeroCase());
		Case caseHero = plateau.getListCase().get(Monstre.getHero().getNumeroCase());


		if ( this.peuTirer(c,caseHero) && nombreAction > 3*7) {
			this.setDirection(this.choisirDirection(caseHero, c));
			this.setCurentAction("appear");
			// nombreAction = 0;
			this.setFrame(0);
		}
		}
	}

	private boolean peuTirer(Case c, Case caseHero) {
		boolean res = false ;

		if ( this.getCurentAction().equals("disappear")) {
			int diffY = caseHero.getCoordonnee().getY() - c.getCoordonnee().getY();
			int diffX = caseHero.getCoordonnee().getX() - c.getCoordonnee().getX();

			if (diffY == 0 && Math.abs(diffX) > 2) {
				res= true ;
			}
			if (diffX == 0 && Math.abs(diffY) > 2) {
				res= true ;
			}
			
		}
		
		return res;
	}

	private Direction choisirDirection(Case caseHero, Case c) {
		Direction res = new Direction("up");
		int diffY = caseHero.getCoordonnee().getY() - c.getCoordonnee().getY();
		int diffX = caseHero.getCoordonnee().getX() - c.getCoordonnee().getX();

		if (Math.abs(diffY) > Math.abs(diffX)) {
			if (diffY > 0) {
				res = new Direction("down");
			} else {
				res = new Direction("up");
			}
		} else {
			if (diffX > 0) {
				res = new Direction("right");
			} else {
				res = new Direction("left");

			}
		}

		// System.out.println("XY:"+ diffX +","+ diffY+","+ res);
		return res;
	}

	// ######################### TIRER #########################################

	@Override
	public void tirer(Plateau plateau, Case c) {
		Pierre pierre = new Pierre (this.getDirection());
		Control.tirer(pierre ,c);
	}
	
	// ######################### PERDRE VIE####################################

	@Override
	public void perdreVie(Damage damage, Plateau p) {

		if (!(this.getCurentAction().equals("animationDeath") || this.getCurentAction().equals("disappear"))) {
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
			if (x < 0 && x > 60) {
				c.setItem(new Rubi());
			} else if (x > 60 && x < 70) {
				c.setItem(new Heart());
			}

		}

	}

	// ######################### IMAGE #########################################

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// System.out.println(this.getFrame());

		String icon = "hyrule/tomato/beat/D1.png";
		;
		switch (this.getCurentAction()) {

		case "nothing":
			icon = this.imageNothing();
			this.setFrame((getFrame() + 1) % 4);
			nombreAction++;
			if (nombreAction > 4 * 8 && this.getFrame() == 0) {
				this.setCurentAction("appear");
				nombreAction = 0;
			}
			break;

		case "appear":
			icon = this.imageAppear();
			this.setFrame((getFrame() + 1) % 4);
			if (this.getFrame() == 0) {
				this.setCurentAction("animationTirer");
			}
			break;
		case "disappear":
			icon = this.imageDisappear();
			this.setFrame((getFrame() + 1) % 4);
			if (this.getFrame() == 0) {
				this.setFrame(3);
				this.setCurentAction("disappear");
				nombreAction++;
				

			}
			break;
		case "animationTirer":
			icon = this.imageTirer();
			this.setFrame((getFrame() + 1) % 4);
			if (this.getFrame() == 1) {
				this.tirer(plateau, c);
			}
			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
				nombreAction =0;
			}
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

		default:
			break;

		}

		return icon;
	}

	private String imageTirer() {
		int num = this.getFrame() + 1;
		// System.out.println("frame: "+this.getFrame());
		// System.out.println("num: "+num);

		String icon = "hyrule/tomato/tirer/Down/" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/tomato/tirer/Up/" + num + ".png";
			break;
		case "down":
			icon = "hyrule/tomato/tirer/Down/" + num + ".png";
			break;
		case "left":
			icon = "hyrule/tomato/tirer/Left/" + num + ".png";
			break;
		case "right":
			icon = "hyrule/tomato/tirer/Right/" + num + ".png";
			break;
		default:
			break;

		}

		return icon;
	}

	private String imageDisappear() {
		int num = this.getFrame() + 1;
		// System.out.println("frame: "+this.getFrame());

		// System.out.println("num: "+num);

		String icon = "hyrule/tomato/disappear/Left" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/tomato/disappear/Up/" + num + ".png";
			break;
		case "down":
			icon = "hyrule/tomato/disappear/Down/" + num + ".png";
			break;
		case "left":
			icon = "hyrule/tomato/disappear/Left/" + num + ".png";
			break;
		case "right":
			icon = "hyrule/tomato/disappear/Right/" + num + ".png";
			break;
		default:
			break;

		}

		return icon;
	}

	private String imageAppear() {
		int num = this.getFrame() + 1;
		// System.out.println("frame: "+this.getFrame());

		// System.out.println("num: "+num);

		String icon = "hyrule/tomato/appear/Left" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/tomato/appear/Up/" + num + ".png";
			break;
		case "down":
			icon = "hyrule/tomato/appear/Down/" + num + ".png";
			break;
		case "left":
			icon = "hyrule/tomato/appear/Left/" + num + ".png";
			break;
		case "right":
			icon = "hyrule/tomato/appear/Right/" + num + ".png";
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

		String icon = "hyrule/tomato/beat/Down/" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/tomato/beat/Up/" + num + ".png";
			break;
		case "down":
			icon = "hyrule/tomato/beat/Down/" + num + ".png";
			break;
		case "left":
			icon = "hyrule/tomato/beat/Left/" + num + ".png";
			break;
		case "right":
			icon = "hyrule/tomato/beat/Right/" + num + ".png";
			break;
		default:
			break;

		}

		return icon;

	}

	@Override
	public int trouverX(Case c) {

		return c.getCoordonnee().getX()*c.getTailleCasePixel()+3 - c.getTailleCasePixel();
	
	}

	@Override
	public int trouverY(Case c) {
		return c.getCoordonnee().getY()*c.getTailleCasePixel()+3 -c.getTailleCasePixel();

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
