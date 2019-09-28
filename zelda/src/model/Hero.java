package model;
//28:5
//7:20
//56,20
//14:46 knight
//26:42 grappin + bomb

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import tool.Tool;

public class Hero extends Unite {

	private List<Projectil> listProjectil;
	private int maxLife = 5;

	public Hero(Coordonnee coordonnee) {
		super();
		this.setExist(true);

		
		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");

		this.setLife(5);
		this.setDamage(1);
		this.listProjectil = new ArrayList<Projectil>();
	}

	public Hero(int x, int y) {
		super();
		this.setExist(true);

		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		
		this.setNom("Hero");
		this.setLife(5);
		this.setDamage(1);
		this.listProjectil = new ArrayList<Projectil>();
		this.setDirection("down");

	}

	@Override
	public void deplacer(String direction, Plateau plateau) {
		// TODO Auto-generated method stub
		// System.out.println(this.coordonnee +"," + this.getListProjectil().size() );
		this.setDirection(direction);
		if (this.isAlive()) {
			switch (direction) {

			case "up":
				Case caseUp = plateau.getCaseUp(this.getCoordonnee());
				interactionDeplacement(plateau, caseUp);
				break;
			case "down":
				Case caseDown = plateau.getCaseDown(this.getCoordonnee());
				interactionDeplacement(plateau, caseDown);
				break;
			case "left":
				Case caseLeft = plateau.getCaseLeft(this.getCoordonnee());
				interactionDeplacement(plateau, caseLeft);
				break;
			case "right":
				Case caseRight = plateau.getCaseRight(this.getCoordonnee());
				interactionDeplacement(plateau, caseRight);
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
			// System.out.println("hero est rentre dans " + c.getElement().getNom());
			break;
		case "Rock":
			interactionRock(plateau, c);
			break;
		case "Minotaure":
			break;
		case "HeroProjectil":
			if (!((HeroProjectil) c.getElement()).getExist()) {
				interactionVide(plateau, c);
			}

			break;
		default:
			// System.out.println("hero est rentrer dans " + c.getElement().getNom());
			break;
		}
	}

	private void interactionRock(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		((Rock) c.getElement()).deplacer(getDirection(), plateau);

		if (c.getElement().getNom().equals("Vide")) {
			this.deplacer(getDirection(), plateau);
		}
	}

	private void interactionVide(Plateau plateau, Case caseApres) {
		// TODO Auto-generated method stub
		this.setCurentAction("moving");
		this.setFrame(0);

		Case caseAvant = plateau.getCase(this.getCoordonnee());
		Coordonnee cordApres = new Coordonnee(caseApres.getElement().getCoordonnee());

		int num = Tool.CoordinateToNum(caseAvant.getElement().getCoordonnee());
		Vide v = new Vide(this.getCoordonnee());
		plateau.getListCase().get(num).setElement(v);

		num = Tool.CoordinateToNum(caseApres.getElement().getCoordonnee());
		plateau.getListCase().get(num).setElement(this);
		this.setCoordonnee(cordApres);

		Item item = caseApres.getItem();
		item.etreRamasser(plateau, caseApres );

	}

	@Override
	public void attaquer(Plateau plateau) {
		
		switch (this.getDirection()) {

		case "up":
			Case caseUp = plateau.getCaseUp(this.getCoordonnee());
			Case caseUpLeft = plateau.getCaseUpLeft(this.getCoordonnee());
			Case caseUpRight = plateau.getCaseUpRight(this.getCoordonnee());

			caseUp.getElement().perdreVie(this.getDamage(), plateau);
			caseUpLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseUpRight.getElement().perdreVie(this.getDamage(), plateau);

//			System.out.println(caseUpLeft+ ","+caseUp +","+caseUpRight);
			break;
		case "down":
			Case caseDownLeft = plateau.getCaseDownLeft(this.getCoordonnee());
			Case caseDown = plateau.getCaseDown(this.getCoordonnee());
			Case caseDownRight = plateau.getCaseDownRight(this.getCoordonnee());
			
			caseDown.getElement().perdreVie(this.getDamage(), plateau);
			caseDownLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseDownRight.getElement().perdreVie(this.getDamage(), plateau);

	//		System.out.println(caseDownLeft+ ","+caseDown +","+caseDownRight);

			break;
		case "left":
			Case caseLeft = plateau.getCaseLeft(this.getCoordonnee());
			caseUpLeft = plateau.getCaseUpLeft(this.getCoordonnee());
			caseDownLeft = plateau.getCaseDownLeft(this.getCoordonnee());

			caseLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseUpLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseDownLeft.getElement().perdreVie(this.getDamage(), plateau);
			
		//	System.out.println(caseUpLeft+ ","+caseLeft +","+caseDownLeft);

			
			break;
		case "right":
			Case caseRight = plateau.getCaseRight(this.getCoordonnee());
			caseUpRight = plateau.getCaseUpRight(this.getCoordonnee());
			caseDownRight = plateau.getCaseDownRight(this.getCoordonnee());

			caseRight.getElement().perdreVie(this.getDamage(), plateau);
			caseDownRight.getElement().perdreVie(this.getDamage(), plateau);
			caseUpRight.getElement().perdreVie(this.getDamage(), plateau);
			
			//System.out.println(caseUpRight+ ","+caseRight +","+caseDownRight);

			
			break;
		default:
			break;

		}

	}
	
	public void tirer(Plateau plateau) {
		// TODO Auto-generated method stub
		if (this.isAlive()) {
			Coordonnee cord = new Coordonnee(this.getCoordonnee());
			HeroProjectil heroProjectil = new HeroProjectil(cord, getDirection(), getDamage());

			// System.out.println(heroProjectil.coordonnee);
			if (!this.listProjectil.isEmpty()) {
				int num = Tool.CoordinateToNum(listProjectil.get(0).getCoordonnee());
				Vide v = new Vide(listProjectil.get(0).getCoordonnee());
				plateau.getListCase().get(num).setElement(v);

				listProjectil.get(0).setExist(false);
				listProjectil.remove(0);
			}
			listProjectil.add(heroProjectil);

			placerProjectil(plateau, heroProjectil);

		}

	}

	public List<Projectil> getListProjectil() {
		return listProjectil;
	}

	public void setListProjectil(List<Projectil> listProjectil) {
		this.listProjectil = listProjectil;
	}

	private void placerProjectil(Plateau plateau, HeroProjectil heroProjectil) {
		// TODO Auto-generated method stub
		int num = Tool.CoordinateToNum(heroProjectil.getCoordonnee());
		plateau.getListCase().get(num).setElement(heroProjectil);
		heroProjectil.deplacer(plateau);
		plateau.getListCase().get(num).setElement(this);

	}

	@Override
	public void perdreVie(int damage, Plateau p) {
		// TODO Auto-generated method stub
		this.setLife(this.getLife() - damage);

		if (getLife() <= 0) {
			this.mourir(p, 4, 1, true);
		}
	}

	@Override
	public void soigner(int soin) {
		// TODO Auto-generated method stub
		if (soin + getLife() >= maxLife) {
			this.setLife(this.maxLife);
		} else {
			this.setLife(this.getLife() + soin);
		}

	}

	public void setCoordonnee(int x, int y) {

		this.setCoordonnee(new Coordonnee(x, y));

	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println(this.getNom() + "," + this.getLife() + "," + this.getCoordonnee() + "," + this.isExist()
				+ "," + this.getDamage());
	}

	@Override
	public String getImage(Plateau plateau , Case c) {
		String icon = "hyrule/link/beat/Down1.png";
		//System.out.println(this.getCurentAction());
		//System.out.println(this.getFrame());

		switch (this.getCurentAction()) {
		
		case "nothing":
			icon = this.imageNothing();
			this.setFrame( (getFrame() + 1) % 16 );
			break;
		case "moving":
			icon = this.imageMove();
			this.setFrame((getFrame() + 1) % 6);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;
		case "animationAttaque":
			icon = this.imageAttaque();
			this.setFrame((getFrame() +1) % 7);
			
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
		int num =    this.getFrame() +1;
		String icon = "hyrule/link/beat/Down1.png";
		switch (this.getDirection()) {

		case "up":
			icon = "hyrule/link/attaque/Up"+num+".png";

			break;
		case "down":
			icon = "hyrule/link/attaque/Down"+num+".png";

			break;
		case "left":
			icon = "hyrule/link/attaque/Left"+num+".png";
			break;
		case "right":
			icon = "hyrule/link/attaque/Right"+num+".png";

			break;
		default:
			break;

		}
		return icon;
	}

	private String imageMove() {
		String icon = "hyrule/link/beat/Down1.png";
		int num =    this.getFrame()  /2  +1;

		// System.out.println(this.getFrame() + " , "+num );
		
		switch (this.getDirection()) {

		case "up":
			//icon = "image/Link/linkMoving"+this.getFrame()%6+"Up24x24.png";
			icon = "hyrule/link/move/Up"+num+".png";

			break;
		case "down":
			//icon = "image/Link/linkMoving"+this.getFrame()%6+"Down24x24.png";
			icon = "hyrule/link/move/Down"+num+".png";

			break;
		case "right":
			icon = "hyrule/link/move/Right"+num+".png";
			break;
		case "left":
			icon = "hyrule/link/move/Left"+num+".png";

			break;
		default:
			break;

		}
		//System.out.println(icon);

		return icon;		
	}

	private String imageNothing() {
		int num =    this.getFrame()  /2  +1;
		//System.out.println("frame: "+this.getFrame());

		//System.out.println("num: "+num);
		
		String icon = "hyrule/link/beat/Down1.png" ;
		switch (this.getDirection()) {

		case "up":
			icon = "hyrule/link/beat/Up"+num+".png";

			break;
		case "down":
			icon = "hyrule/link/beat/Down"+num+".png";

			break;
		case "left":
			icon = "hyrule/link/beat/Left"+num+".png";
			break;
		case "right":
			icon = "hyrule/link/beat/Right"+num+".png" ;

			break;
		default:
			break;

		}
		//System.out.println(icon);

		return icon;

	}

	@Override
	protected int trouverX() {
		int res = this.getCoordonnee().getX() * 40 -40;
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()  / 2  +1;
			res = res + num * 13 - 40 ;
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()  /2  +1;
			res = res - num * 13 +40 ;
		}
		return res;
	}

	@Override
	protected int trouverY() {
		int res = this.getCoordonnee().getY() * 40 -40;
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()  /2  +1;
			res = res - num * 13 +40 ;
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()  /2  +1;
			res = res + num * 13 - 40;
		}
		return res;
	}

	@Override
	protected int trouverlargeur() {
		int res = 120;
		return res ;
	}

	@Override
	protected int trouverlongeur() {
		int res = 120;
		return res ;
	}

	

}
