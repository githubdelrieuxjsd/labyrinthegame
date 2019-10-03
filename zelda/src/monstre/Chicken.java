package monstre;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import block.Vide;
import controleur.Control;
import damage.Damage;
import item.Heart;
import item.Item;
import item.Rubi;
import mobInterface.Deplacement;
import mobInterface.DropItem;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;
import projectil.Fleche;
import projectil.Projectil;
import tool.Tool;

public class Chicken extends Monstre implements Deplacement, DropItem {

	public Chicken() {
		super();
		this.setExist(true);
		this.setFrame(0);
		this.setCurentAction("nothing");
		this.setDirection(new Direction("down"));
		this.setNom("Chicken");
		this.setDamageMonstre(0, 0, 0, 0);
		this.setMaxLife(2);
		this.setLife(this.getMaxLife());
	}

	// ######################### A FAIRE #########################################


	private void transformation(Case c) {
		Item item = c.getItem();
		if (item.getNom().equals("Key")) {
			item.etreRamasser(c);
			this.setCurentAction("transformation");
			this.setFrame(0);
		}
	}

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

		int num = Tool.CoordinateToNum(caseAvant.getCoordonnee());
		Vide v = new Vide();
		plateau.getListCase().get(num).setElement(v);

		num = Tool.CoordinateToNum(caseApres.getCoordonnee());

		plateau.getListCase().get(num).setElement(this);
		this.setNumeroCase(num);
	}

	@Override
	public void interactionDeplacement(Plateau plateau, Case caseAvant, Direction direction) {
		this.setDirection(direction);
		Case caseApres = plateau.getCaseDevant(caseAvant, this.getDirection());

		if (caseApres.getElement().getNom().equals("Vide")) {
			this.deplacer(plateau, caseAvant, caseApres);
		}
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
			if (x > 50) {
				c.setItem(new Rubi());
			} else if (x < 20) {
				c.setItem(new Heart());
			}

		}

	}

	// ######################### IMAGE #########################################

	@Override
	public String trouverImage(Plateau p, Case c) {
		String icon = "hyrule/link/beat/Down1.png";
		;
		switch (this.getCurentAction()) {

		case "nothing":
			icon = this.imageNothing();
			this.setFrame((getFrame() + 1) % 8);
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
			icon = this.imageNothing();
			this.setFrame((getFrame() + 1) % 8);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;

		default:
			break;

		}

		return icon;
	}

	private String imageNothing() {
		int num = this.getFrame() / 2 + 1;
		// System.out.println("frame: "+this.getFrame());

		// System.out.println("num: "+num);

		String icon = "hyrule/chicken/R" + num + ".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/chicken/R" + num + ".png";

			break;
		case "down":
			icon = "hyrule/chicken/L" + num + ".png";

			break;
		case "left":
			icon = "hyrule/chicken/L" + num + ".png";
			break;
		case "right":
			icon = "hyrule/chicken/R" + num + ".png";

			break;
		default:
			break;

		}
		return icon;

	}

	@Override
	public int trouverX(Case c) {
		int res = c.getCoordonnee().getX() * c.getTailleCasePixel() - c.getTailleCasePixel();
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res + num * 7 - c.getTailleCasePixel();
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res - num * 7 + c.getTailleCasePixel();
		}
		return res;
	}

	@Override
	public int trouverY(Case c) {
		int res = c.getCoordonnee().getY() * c.getTailleCasePixel() - c.getTailleCasePixel();
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() / 2 + 1;
			res = res - num * c.getTailleCasePixel()/4 + c.getTailleCasePixel();
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() / 2 + 1;
			res = res + num * c.getTailleCasePixel()/4 - c.getTailleCasePixel();
		} else if (this.getCurentAction().equals("moving")) {
			int num = -(this.getFrame() - 4) * (this.getFrame() - 4) + 10;
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

	// ######################### BARRE VIE #########################################

	@Override
	public int trouverBarreVieX(Case c) {
		// TODO Auto-generated method stub
		return this.trouverX(c) + 45;
	}

	@Override
	public int trouverBarreVieY(Case c) {
		// TODO Auto-generated method stub
		return this.trouverY(c) + 30;
	}

}
