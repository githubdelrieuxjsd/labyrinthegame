package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import tool.Tool;

public class Chicken extends Monstre {

	protected int maxLife = 1;
	
	public Chicken(Coordonnee coordonnee ) {
		super(coordonnee);
		// TODO Auto-generated constructor stub
		this.setNom("Chicken");
		this.setLife( 1);
		this.setDamage( 0);
		this.setDirection ("down") ;
	}

	public Chicken(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.setNom("Chicken");
		this.setLife( 1);
		this.setDamage( 0);
		this.setDirection ("down") ;
	}

	@Override
	public void deplacer(String direction, Plateau plateau) {
		// TODO Auto-generated method stub
		if (this.isAlive()) {
			this.setDirection(direction);
			switch (direction) {

			case "up":
				this.setDirection("up");

				Case caseUp = plateau.getCaseUp(this.getCoordonnee());
				interactionDeplacement(plateau, caseUp );
				break;
			case "down":
				this.setDirection("down");

				Case caseDown = plateau.getCaseDown(this.getCoordonnee());
				interactionDeplacement(plateau, caseDown );
				break;
			case "left":
				this.setDirection("left");

				Case caseLeft = plateau.getCaseLeft(this.getCoordonnee());
				interactionDeplacement(plateau, caseLeft );

				break;
			case "right":
				this.setDirection("right");

				Case caseRight = plateau.getCaseRight(this.getCoordonnee());
				interactionDeplacement(plateau, caseRight );

				break;
			default:
				break;

			}
		}
	}

	private void interactionDeplacement(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		switch (c.getElement().getNom()) {

		case "Vide":
			interactionVide(plateau, c);
			break;
		case "Arbre":
			break;
		case "Rock":
			break;
		case "Hero":
			break;
		case "HeroProjectil":
			if (!((HeroProjectil) c.getElement()).getExist()) {
				interactionVide(plateau, c);
			}

			break;
		default:
			//System.out.println("Chicken est rentrer dans " + c.getElement().getNom());
			break;
		}
	}

	private void interactionVide(Plateau plateau, Case caseApres) {
		// TODO Auto-generated method stub
		Case caseAvant = plateau.getCase(this.getCoordonnee());
		Coordonnee cordApres = new Coordonnee(caseApres.getElement().getCoordonnee());

		int num = Tool.CoordinateToNum(caseAvant.getElement().getCoordonnee());
		Vide v = new Vide(this.getCoordonnee());
		plateau.getListCase().get(num).setElement(v);

		num = Tool.CoordinateToNum(caseApres.getElement().getCoordonnee());
		plateau.getListCase().get(num).setElement(this);
		this.setCoordonnee(cordApres);

		ObjetCacher objCacher = caseApres.getObjetCacher();
		switch (objCacher.getNom()) {

		case "Spike":
			((Spike) objCacher).attaquer(this, plateau ,false);
			break;
		default:
			;
		}
	}

	@Override
	public void perdreVie(int damage, Plateau p) {
		// TODO Auto-generated method stub
		this.setLife(this.getLife() - damage);

		if (getLife() <= 0) {
			int x = (int) (Math.random() * (23 + 1 - 1)) + 1;
			int y = (int) (Math.random() * (16 + 1 - 1)) + 1;
			this.mourir(p, x, y, false);
		}

	}

	@Override
	public void soigner(int soin) {
		// TODO Auto-generated method stub
		if (soin + getLife() >= maxLife) {
			setLife(maxLife);
		} else {
			this.setLife( getLife() + soin );
		}
	}

	@Override
	public void attaquer(Plateau m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		//System.out.println(this.nom + "," + this.life + "," + this.coordonnee + "," + this.exist + "," + this.damage+ "," + this.direction);
	}

	
	@Override
	public String getImage(Case c) {
		String icon = "img/chickenLeft2.png";
		((Unite)c.getElement()).afficher();
		if (getDirection().equals("up")) {
			icon = "img/chickenUp.png";
		} else if (getDirection().equals("down")) {
			icon = "img/chickenDown.png";
		} else if (getDirection().equals("left")) {
			icon = "img/chickenLeft.png";
		} else if (getDirection().equals("right")) {
			icon = "img/chickenRight.png";
		}
		return icon;
	}

	@Override
	protected int trouverX() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getX()*40;
	}

	@Override
	protected int trouverY() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getY()*40;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 40;
	}

	
}
