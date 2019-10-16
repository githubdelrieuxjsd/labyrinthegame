package monstre;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import block.Rock;
import block.Vide;
import damage.Damage;
import mobInterface.Attaque;
import mobInterface.Deplacement;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;
import projectil.Projectil;
import tool.Tool;

public class Knight extends Monstre implements Attaque,Deplacement{

		
	
	public Knight( ) {
		super();
		this.setSpawnable(false);

		this.setExist(true);
		this.setMaxLife(5);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
		this.setNom("Knight");
		this.setLife( this.getMaxLife());
		this.setDamageMonstre( 2 ,0,0,0);
		
	}
	
	// ######################### A FAIRE #########################################


	
	//######################### ACTION  #########################################	

	public void action(Plateau plateau) {
		
		if (this.getCurentAction().equals("nothing")) {
			Case c = plateau.getListCase().get(this.getNumeroCase());
			
			if (this.isInRange(plateau,c) ) {
				int random = (int) (Math.random() * (100 - 1 +1 )) + 1;
				if (random > 20) {
					this.animationAttaque();
				}else {
					this.interactionDeplacement( plateau,c, this.deplacementOptimal(plateau,c));
				}	

			}
			else {
				this.interactionDeplacement( plateau,c, this.deplacementOptimal(plateau,c));
			}
			
		}
			}
	
	public boolean isInRange(Plateau plateau, Case c) {
		boolean res = false ;
		if (plateau.getCaseUpMemeZ(c.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseUpLeftMemeZ(c.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseUpRightMemeZ(c.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseLeftMemeZ(c.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseRightMemeZ(c.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseDownRightMemeZ(c.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseDownMemeZ(c.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseDownRightMemeZ(c.getCoordonnee()).getElement().isHero()) {
			res = true ;
		}
		return res;
	}
	
	public Direction deplacementOptimal(Plateau plateau ,Case c){
		Case caseHero = plateau.getListCase().get(Monstre.getHero().getNumeroCase());
		Direction res = new Direction ("up" );
		String UporDown = "up" ;
		String RightorLeft = "left";
		int chance = 50 ; 
		int diffY = caseHero.getCoordonnee().getY() - c.getCoordonnee().getY();
		int diffX = caseHero.getCoordonnee().getX() - c.getCoordonnee().getX() ;
		int diffXY = Math.abs(diffX) - Math.abs(diffY);
		
		if (diffY>0) {
			UporDown = "down"  ;
		}
		if (diffX>0) {
			RightorLeft = "right";
		}
		
		int random = (int) (Math.random() * (100 - 1 +1 )) + 1;

		chance = chance + diffXY * 4 ;
		if (random > chance) {
			res = new Direction ( UporDown );
		}else {
			res = new Direction ( RightorLeft ) ;
		}
		//System.out.println("XY: " + diffXY +",chance Y :"+chance+","+  res);
		return res ;
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

			int num = plateau.coordinateToNum(caseAvant.getCoordonnee());
			Vide v = new Vide( );
			plateau.getListCase().get(num).setElement(v);

			num = plateau.coordinateToNum(caseApres.getCoordonnee());
			
			plateau.getListCase().get(num).setElement(this);
			this.setNumeroCase(num);
						
		}
		
		@Override
		public void interactionDeplacement(Plateau plateau,Case caseAvant, Direction direction) {
			this.setDirection(direction);
			Case caseApres = plateau.getCaseDevantMemeZ(caseAvant, this.getDirection());
			
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

			

	

	//######################### ATTAQUE #########################################

	
	public void animationAttaque() {
		this.setCurentAction("animationAttaque");
		this.setFrame(0);
	}
	
	@Override
	public void attaquer(Plateau plateau, Case c) {
		switch (this.getDirection().getDirection()) {

		case "up":
			plateau.getCaseUpMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpLeftMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpRightMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseLeftMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseRightMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);

//			System.out.println(caseUpLeft+ ","+caseUp +","+caseUpRight);
			break;
		case "down":
			plateau.getCaseDownMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownLeftMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownRightMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseLeftMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseRightMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);

			break;
		case "left":
			plateau.getCaseUpMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpLeftMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownLeftMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseLeftMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);

			break;
		case "right":
			plateau.getCaseUpMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownRightMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpRightMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseRightMemeZ(c.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			
			break;
		default:
			break;

		}		
	}

	
	//######################### PERDRE VIE #########################################




	@Override
	public void perdreVie(Damage damage , Plateau p) {
		if (! this.getCurentAction().equals("animationDeath")) {
			
		if (damage.doDamage(this)) {
			this.setLife(getLife() -damage.getEpee() - damage.getProjectil());
		}
		
		if (getLife() <= 0) {
			this.setCurentAction("animationDeath");
			this.setFrame(0);
		}
		}
	}
	
	
	//######################### IMAGE #########################################

	
	@Override
	public String trouverImage(Plateau plateau ,Case c) {
		String icon ="hyrule/knight/Down1.png" ;
		switch (this.getCurentAction()) {
		
		case "nothing":
			icon = this.imageNothing();
			this.setFrame( (getFrame() + 1) % 24 );
			break;
			
		case "animationDeath":
			int num = this.getFrame() / 2 + 1;
			// System.out.println(num);
			icon = "hyrule/death/" + num + ".png";
			this.setFrame((getFrame() + 1) % 14);
			
			if (this.getFrame() == 0) {
				this.setCurentAction("dead");
				this.mourir();
			}
			break;
			
		case "animationAttaque":
			icon = this.imageAnimationAttaque();
			this.setFrame((getFrame() +1) % 15);
			
			if (this.getFrame() == 0) {
				this.setCurentAction("attaque");
			}
			
			break;
			
		case "attaque":
			icon = this.imageAttaque();
			this.setFrame((getFrame() +1) % 16);
			
			if (this.getFrame() == 2) {
				this.attaquer(plateau, c);
			}
			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			
			break;
			
		case "moving":
			icon = this.imageMoving();
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
		int num =    this.getFrame() /4 +1;
		String icon = "hyrule/knight/beat/Down1.png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/knight/attaque/U"+num+".png";

			break;
		case "down":
			icon = "hyrule/knight/attaque/D"+num+".png";

			break;
		case "left":
			icon = "hyrule/knight/attaque/L"+num+".png";
			break;
		case "right":
			icon = "hyrule/knight/attaque/R"+num+".png";

			break;
		default:
			break;

		}
		return icon;
	}


	private String imageAnimationAttaque() {
		int num =    this.getFrame() /5 +1;
		String icon = "hyrule/knight/beat/Down1.png";
		switch (this.getDirection().getDirection()) {

		case "up":
			icon = "hyrule/knight/prepattaque/U"+num+".png";

			break;
		case "down":
			icon = "hyrule/knight/prepattaque/D"+num+".png";

			break;
		case "left":
			icon = "hyrule/knight/prepattaque/L"+num+".png";
			break;
		case "right":
			icon = "hyrule/knight/prepattaque/R"+num+".png";

			break;
		default:
			break;

		}
		return icon;
	}
	
	
	private String imageMoving() {
		int num =    this.getFrame() +1;
		//System.out.println("frame: "+this.getFrame());

		//System.out.println("num: "+num);
		
		String icon = "hyrule/knight/beat/Up/"+num+".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			 icon = "hyrule/knight/beat/Up/"+num+".png";
			break;
		case "down":
			 icon = "hyrule/knight/beat/Down/"+num+".png";
			break;
		case "left":
			 icon = "hyrule/knight/beat/Left/"+num+".png";
			 break;
		case "right":
			 icon = "hyrule/knight/beat/Right/"+num+".png";
			break;
		default:
			break;

		}
		return icon;
	}


	private String imageNothing() {
		int num =    this.getFrame() /4 +1;
		//System.out.println("frame: "+this.getFrame());

		//System.out.println("num: "+num);
		
		String icon = "hyrule/knight/beat/Up/"+num+".png";
		switch (this.getDirection().getDirection()) {

		case "up":
			 icon = "hyrule/knight/beat/Up/"+num+".png";
			break;
		case "down":
			 icon = "hyrule/knight/beat/Down/"+num+".png";
			break;
		case "left":
			 icon = "hyrule/knight/beat/Left/"+num+".png";
			 break;
		case "right":
			 icon = "hyrule/knight/beat/Right/"+num+".png";
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
			int num =    this.getFrame()   +1;
			res = res + num * 6 - c.getTailleCasePixel() ;
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame() +1;
			res = res - num * 6 +c.getTailleCasePixel() ;
		}
		return res;
	}

	@Override
	public int trouverY(Case c) {
		int res = c.getCoordonnee().getY() * c.getTailleCasePixel() -2*c.getTailleCasePixel();
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()   +1;
			res = res - num * 6 +c.getTailleCasePixel() ;
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()   +1;
			res = res + num * 6 - c.getTailleCasePixel();
		} else if (this.getCurentAction().equals("moving")) {
			int num =  - ( this.getFrame() -3 )* ( this.getFrame() -3 ) + 10 ;
			res = res - num  ;
			//System.out.println(this.getFrame() +","+ (num) );
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
		return this.trouverX(c)+30;
	}


	@Override
	public int trouverBarreVieY(Case c) {
		// TODO Auto-generated method stub
		return this.trouverY(c);
	}
	

}
