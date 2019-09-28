package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import tool.Tool;

public class Chicken extends Monstre {

	protected int maxLife = 1;
	
	public Chicken(Coordonnee coordonnee ) {
		super();
		this.setExist(true);

		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Chicken");
		this.setLife( 2);
		this.setDamage( 0);
	}

	public Chicken(int x, int y) {
		super();
		this.setExist(true);
 
		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setNom("Chicken");
		this.setLife( 2);
		this.setDamage( 0);
		this.setDirection ("down") ;
	}

	@Override
	public void deplacer(String direction, Plateau plateau) {
		// TODO Auto-generated method stub
		if (this.isAlive()&& this.getCurentAction().equals("nothing")) {
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

	}

	@Override
	public void perdreVie(int damage, Plateau p) {
		if ( ! this.getCurentAction().equals("death")) {
			this.setLife(this.getLife() - damage);

			if (getLife() <= 0) {
				this.setCurentAction("death");
				this.setFrame(0);
			}
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
	public String getImage(Plateau p ,Case c) {
		String icon = "hyrule/link/beat/Down1.png";;
		switch (this.getCurentAction()) {
		
		case "nothing":
			icon = this.imageNothing();
			this.setFrame( (getFrame() + 1) % 8 );
			break;
		case "death":
			int num =  this.getFrame() /2 +1;
			//System.out.println(num);
			icon = "hyrule/death/"+num+".png";
			this.setFrame((getFrame() + 1) % 14);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
				int x = (int) (Math.random() * (23 + 1 - 1)) + 1;
				int y = (int) (Math.random() * (16 + 1 - 1)) + 1;
				this.mourir(p, x, y, false);
				
			}
			break;
		
		default: 
			break;

		}
				
		return icon;
	}

	
	private String imageNothing() {
		int num =    this.getFrame() /2 +1;
		//System.out.println("frame: "+this.getFrame());

		//System.out.println("num: "+num);
		
		String icon = "hyrule/chicken/R"+num+".png" ;
		switch (this.getDirection()) {

		case "up":
			icon = "hyrule/chicken/R"+num+".png";

			break;
		case "down":
			icon = "hyrule/chicken/L"+num+".png";

			break;
		case "left":
			icon = "hyrule/chicken/L"+num+".png";
			break;
		case "right":
			icon = "hyrule/chicken/R"+num+".png";

			break;
		default:
			break;

		}
		return icon;

	}
	
	
	@Override
	protected int trouverX() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getX()*40-40;
	}

	@Override
	protected int trouverY() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getY()*40-40;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 120;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 120;
	}

	
}
