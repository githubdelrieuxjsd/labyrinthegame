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

import block.Rock;
import block.Vide;
import controleur.Control;
import damage.Damage;
import item.Bomb;
import item.Item;
import mobInterface.Attaque;
import mobInterface.Deplacement;
import mobInterface.Dessin;
import mobInterface.PerdreVie;
import mobInterface.Soigner;
import mobInterface.Tirer;
import projectil.Fleche;
import projectil.Projectil;
import tool.Tool;

public class Hero extends Unite implements Deplacement,Attaque,Tirer,Soigner  {

	// voir inventaire 

	public Hero( ) {
		super();
		this.setNom("Hero");
		this.setExist(true);

		this.setFrame(0);
		this.setCurentAction("nothing");
		this.setDirection( new Direction ("down") );

		this.setMaxLife(5);
		this.setLife( this.getMaxLife() );
		
		this.setDamageHero( 2,0,0 );
		
		
	}
	
	//######################### A FAIRE  #########################################	
		@Override
		public void mourir() {
			this.soigner(this.getMaxLife());
		}
		//######################### A FAIRE  Tirer #########################################

		@Override
		public void tirer(Plateau plateau, Case c) {
			Fleche fleche = new Fleche (this.getDirection());
			Control.tirer(fleche,c);
		}
	//######################### ACTION  #########################################	

	public void action(Plateau plateau, String descision) {
		
		Case c = plateau.getListCase().get(this.getNumeroCase()) ;
		
		switch (descision) {
		case "moveUp":
			this.interactionDeplacement(plateau,c, new Direction("up"));
			break;
			
		case "moveDown":
			this.interactionDeplacement(plateau,c, new Direction("down"));
			break;
			
		case "moveRight":
			this.interactionDeplacement(plateau,c, new Direction("right"));
			break;
			
		case "moveLeft":
			this.interactionDeplacement(plateau,c, new Direction("left"));
			break;
			
		case "tirer":
			this.setCurentAction("animationTirer");
			this.setFrame(0);
			break;
			
		case "attaque":
			this.setCurentAction("animationAttaque");
			this.setFrame(0);
			break;
		case "bomb":
			this.setCurentAction("animationPlacerBomb");
			this.setFrame(0);
			plateau.getCase(c.getCoordonnee()).setItem(new Bomb());
			break;
		default:
			break;
		}		
	}
	
	
	//######################### DEPLACEMENT #########################################
	@Override
	public void deplacer (Plateau plateau ,Case caseAvant, Case caseApres) {
		this.setCurentAction("moving");
		this.setFrame(0);

		Projectil p = caseApres.getProjectil() ;
		if ( ! p.getNom().equals("Aire")) {
			this.perdreVie(p.getDamage(),plateau);
		}
		
		Coordonnee cordApres = new Coordonnee(caseApres.getCoordonnee());

		int num = Tool.CoordinateToNum(caseAvant.getCoordonnee());
		Vide v = new Vide( );
		plateau.getListCase().get(num).setElement(v);

		num = Tool.CoordinateToNum(caseApres.getCoordonnee());
		
		plateau.getListCase().get(num).setElement(this);
		this.setNumeroCase(num);
		
		this.Ramasser(caseApres);
		
	}
	
	@Override
	public void interactionDeplacement(Plateau plateau,Case caseAvant, Direction direction) {
		this.setDirection(direction);
		Case caseApres = plateau.getCaseDevant(caseAvant, this.getDirection());
		
		
		
		
		switch (caseApres.getElement().getNom()) {

		case "Vide":
			this.deplacer(plateau,caseAvant, caseApres);
			break;
		case "Rock":
			((Rock) caseApres.getElement()).interactionDeplacement( plateau, caseApres,this.getDirection());

			if (caseApres.getElement().getNom().equals("Vide")) {
				this.deplacer( plateau ,caseAvant,caseApres);
			}
			break;
		default:
			// System.out.println("hero est rentrer dans " + c.getElement().getNom());
			break;
		}
	}

	private void Ramasser(Case c) {
		Item item = c.getItem();

		switch (item.getNom()) {
		case "Heart" :  item.etreRamasser(c);
		this.soigner(1);
			break;
		case "Rubi" :  item.etreRamasser(c);
			break;
		default :
			break;
		}
	}
	
	//######################### ATTAQUE #########################################

	@Override
	public void attaquer(Plateau plateau, Case c) {
		
		switch (this.getDirection().getDirection()) {

		case "up":
			Case caseUp = plateau.getCaseUp(c.getCoordonnee());
			Case caseUpLeft = plateau.getCaseUpLeft(c.getCoordonnee());
			Case caseUpRight = plateau.getCaseUpRight(c.getCoordonnee());

			caseUp.getElement().perdreVie(this.getDamage(), plateau);
			caseUpLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseUpRight.getElement().perdreVie(this.getDamage(), plateau);

//			System.out.println(caseUpLeft+ ","+caseUp +","+caseUpRight);
			break;
		case "down":
			Case caseDownLeft = plateau.getCaseDownLeft(c.getCoordonnee());
			Case caseDown = plateau.getCaseDown(c.getCoordonnee());
			Case caseDownRight = plateau.getCaseDownRight(c.getCoordonnee());
			
			caseDown.getElement().perdreVie(this.getDamage(), plateau);
			caseDownLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseDownRight.getElement().perdreVie(this.getDamage(), plateau);

	//		System.out.println(caseDownLeft+ ","+caseDown +","+caseDownRight);

			break;
		case "left":
			Case caseLeft = plateau.getCaseLeft(c.getCoordonnee());
			caseUpLeft = plateau.getCaseUpLeft(c.getCoordonnee());
			caseDownLeft = plateau.getCaseDownLeft(c.getCoordonnee());

			caseLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseUpLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseDownLeft.getElement().perdreVie(this.getDamage(), plateau);
			
		//	System.out.println(caseUpLeft+ ","+caseLeft +","+caseDownLeft);

			
			break;
		case "right":
			Case caseRight = plateau.getCaseRight(c.getCoordonnee());
			caseUpRight = plateau.getCaseUpRight(c.getCoordonnee());
			caseDownRight = plateau.getCaseDownRight(c.getCoordonnee());

			caseRight.getElement().perdreVie(this.getDamage(), plateau);
			caseDownRight.getElement().perdreVie(this.getDamage(), plateau);
			caseUpRight.getElement().perdreVie(this.getDamage(), plateau);
			
			//System.out.println(caseUpRight+ ","+caseRight +","+caseDownRight);

			
			break;
		default:
			break;

		}

	}
	
	


	//######################### PERDRE VIE  #########################################

	@Override
	public void perdreVie(Damage damage, Plateau p) {

		if (damage.doDamage(this)) {
			this.setLife(this.getLife() - damage.getExplosion() - damage.getEpee() -damage.getMagie() - damage.getProjectil());
			this.setCurentAction("hurt");
			this.setFrame(0);
		}
		//System.out.println("life : "+this.getLife());
		if (getLife() <= 0) {
			this.mourir();
		}
	}
	
	
	//######################### SOIGNER  #########################################
	
	@Override
	public void soigner(int soin) {
		// TODO Auto-generated method stub
		if (soin + getLife() >= getMaxLife()) {
			this.setLife(this.getMaxLife());
		} else {
			this.setLife(this.getLife() + soin);
		}

	}

	// ######################### IMAGE #########################################

	@Override
	public String trouverImage(Plateau plateau , Case c) {
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
			this.setFrame((getFrame() + 1) % 3);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;
		case "hurt":
			icon = this.imageHurt();
			this.setFrame((getFrame() + 1) % 6);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;
		case "animationAttaque":
			icon = this.imageAttaque();
			this.setFrame((getFrame() +1) % 7);
			
			if (this.getFrame() == 2) {
				this.attaquer(plateau, c);
			}
			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			
			break;
		case "animationTirer":
			icon = this.imageBow();
			this.setFrame((getFrame() +1) % 9);
			
			if (this.getFrame() == 5) {
				this.tirer(plateau,c);
			}
			
			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			
			break;
		default: 
			break;

		}
		
		return icon;
	}
	
	

	private String imageHurt() {
		String icon = "hyrule/link/beat/Down1.png";
		int num =    this.getFrame() /2  +1;		
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/link/hurt/U"+num+".png";
			break;
		case "down":
			icon = "hyrule/link/hurt/D"+num+".png";
			break;
		case "right":
			icon = "hyrule/link/hurt/R"+num+".png";
			break;
		case "left":
			icon = "hyrule/link/hurt/L"+num+".png";
			break;
		default:
			break;
		}
		//System.out.println(icon);

		return icon;		
	}

	private String imageBow() {
		
		int num =    this.getFrame() +1;
		String icon = "hyrule/link/arrow/Down/1.png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/link/arrow/Up/"+num+".png";

			break;
		case "down":
			icon = "hyrule/link/arrow/Down/"+num+".png";

			break;
		case "left":
			icon = "hyrule/link/arrow/Left/"+num+".png";
			break;
		case "right":
			icon = "hyrule/link/arrow/Right/"+num+".png";

			break;
		default:
			break;

		}
		return icon;
	}


	private String imageAttaque() {
		int num =    this.getFrame() +1;
		String icon = "hyrule/link/beat/Down1.png";
		switch (this.getDirection().getDirection()) {

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
		int num =    this.getFrame()    +1;

		// System.out.println(this.getFrame() + " , "+num );
		
		switch (this.getDirection().getDirection()) {

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
		switch (this.getDirection().getDirection()) {

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
	public int trouverX(Case c) {
		int res = c.getCoordonnee().getX() * c.getTailleCasePixel() -c.getTailleCasePixel();
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()   +1;
			res = res + num * c.getTailleCasePixel()/3 - c.getTailleCasePixel() ;
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()   +1;
			res = res - num * c.getTailleCasePixel()/3 +c.getTailleCasePixel() ;
		}
		
		
		return res;
	}

	@Override
	public int trouverY(Case c) {
		int res = c.getCoordonnee().getY() * c.getTailleCasePixel() -c.getTailleCasePixel();
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()    +1;
			res = res - num * c.getTailleCasePixel()/3 +c.getTailleCasePixel() ;
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()    +1;
			res = res + num * c.getTailleCasePixel()/3 - c.getTailleCasePixel();
		}
		
		return res;
	}


	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	//######################### INFORMATION  #########################################


	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println(this.getNom() + "," + this.getLife() + "," + this.isExist()
				+ "," + this.getDamage());
	}

	
	
	
	//######################### GETTER SETTER  #########################################




	

	

}
