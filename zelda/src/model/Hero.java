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

public class Hero extends Unite implements Deplacement, Attaque, Tirer, Soigner {

	private String perso = "link";
	// voir inventaire
	private int nombreRubi = 0;
	
	public Hero() {
		super();
		this.setNumeroCase(-1);

		this.setNom("Hero");
		this.setExist(true);

		this.setFrame(0);
		this.setCurentAction("nothing");
		this.setDirection(new Direction("down"));

		this.setMaxLife(5);
		this.setLife(this.getMaxLife());

		this.setDamageHero(2, 0, 0);

	}

	// ######################### A FAIRE #########################################
	@Override
	public void mourir() {
		this.nombreRubi = 0;
		this.soigner(this.getMaxLife());
	}
	// ######################### A FAIRE INTERATCTIONAction#########################################
	private void interactionAction(Plateau plateau, Case c) {
		Case caseDevant = plateau.getCaseDevantMemeZ(c, this.getDirection());

		switch (caseDevant.getElement().getNom()) {
		case "Rock": lift(plateau , caseDevant) ;
		break;
		default :
			break;
		}
		
	}

	public boolean hold() {
		return this.getCurentAction().equals("nothingHolding")
				|| this.getCurentAction().equals("movingHolding")
				|| this.getCurentAction().equals("animationLift")
				|| this.getCurentAction().equals("animationThrow");
	}
	
	private void lift(Plateau plateau, Case caseDevant) {
		if (caseDevant.getCoordonnee().getZ()< plateau.getNombreCaseZ()-1) {
			if (
					plateau.getListCase().get(
					Tool.CoordinateToNum(caseDevant.getCoordonnee().getX(), caseDevant.getCoordonnee().getY(), caseDevant.getCoordonnee().getZ()+1)
					).isEmpty()
					){
				this.setCurentAction("animationLift");
				this.setFrame(0);
				caseDevant.setElement(new Vide());

			}
		}
		else{
			this.setCurentAction("animationLift");
			this.setFrame(0);
			caseDevant.setElement(new Vide());
		}
		
	}
	private void animationThrow(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		this.setCurentAction("animationThrow");
		this.setFrame(0);
	}
	private void throwObject(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		Case caseDevant = plateau.getCaseDevant(c, this.getDirection());
		if (caseDevant.getElement().getNom().equals("Vide")) {
			caseDevant.setElement(new Rock());
		}
	}


	// ######################### ACTION #########################################

	//Appuie sur bouton action -> le bonhomme se postionne
	//Touche de deplacement -> tire/pousse
	//Appuie sur bouton action -> le bonhomme revient a la normale

	
	public void action(Plateau plateau, String descision) {
		if (this.canDoAction()) {
			Case c = plateau.getListCase().get(this.getNumeroCase());
			switch (descision) {
			case "toucheAction": 
				if (this.getCurentAction().equals("nothingHolding")) {
					this.animationThrow(plateau, c);
				}
				else {
					interactionAction(plateau,c);
				}
				break;
			case "moveUp":
				this.interactionDeplacement(plateau, c, new Direction("up"));
				break;

			case "moveDown":
				this.interactionDeplacement(plateau, c, new Direction("down"));
				break;

			case "moveRight":
				this.interactionDeplacement(plateau, c, new Direction("right"));
				break;

			case "moveLeft":
				this.interactionDeplacement(plateau, c, new Direction("left"));
				break;

			case "tirer":
				if ( ! this.hold()) {
				this.setCurentAction("animationTirer");
				this.setFrame(0);
				}
				break;

			case "attaque":
				if ( ! this.hold()) {
				this.setCurentAction("animationAttaque");
				this.setFrame(0);
				}
				break;
			case "bomb":
				if ( ! this.hold()) {
				//this.setCurentAction("animationPlacerBomb");
				this.setFrame(0);
				plateau.getCase(c.getCoordonnee()).setItem(new Bomb());

				}
				break;
			default:
				break;
			}
		}
		
	}

	private boolean canDoAction() {
		
		return this.getCurentAction().equals("nothing")||this.getCurentAction().equals("nothingHolding");
	}

	
	
	// ######################### DEPLACEMENT  #########################################
	@Override
	public void deplacer(Plateau plateau, Case caseAvant, Case caseApres) {
		if (this.hold()) {
			this.setCurentAction("movingHolding");
			this.setFrame(0);
		}
		else {
			this.setCurentAction("moving");
			this.setFrame(0);
		}

		Projectil p = caseApres.getProjectil();
		if (!p.getNom().equals("Aire")) {
			this.perdreVie(p.getDamage(), plateau);
		}

		Coordonnee cordApres = new Coordonnee(caseApres.getCoordonnee());

		int num = Tool.CoordinateToNum(caseAvant.getCoordonnee());
		Vide v = new Vide();
		plateau.getListCase().get(num).setElement(v);

		num = Tool.CoordinateToNum(caseApres.getCoordonnee());

		plateau.getListCase().get(num).setElement(this);
		this.setNumeroCase(num);
		this.Ramasser(caseApres);

	}

	@Override
	public void interactionDeplacement(Plateau plateau, Case caseAvant, Direction direction) {
		this.setDirection(direction);

		if (this.changementdeMap(plateau,caseAvant, direction)) {
			Control.generationPlateau();
		} else {

			Case caseApres = plateau.getCaseDevant(caseAvant, this.getDirection());

			switch (caseApres.getElement().getNom()) {

			case "Vide":
				this.deplacer(plateau, caseAvant, caseApres);
				break;
			case "Rock":
				((Rock) caseApres.getElement()).interactionDeplacement(plateau, caseApres, this.getDirection());

				if (caseApres.getElement().getNom().equals("Vide")) {
					this.deplacer(plateau, caseAvant, caseApres);
				}
				break;
			default:
				// System.out.println("hero est rentrer dans " + c.getElement().getNom());
				break;
			}
		}
	}

	private boolean changementdeMap(Plateau plateau,Case caseAvant, Direction direction) {
		boolean res = false;

		switch (direction.getDirection()) {

		case "up":
			if (caseAvant.getCoordonnee().getY() == 0) {
				this.setNumeroCase( Tool.CoordinateToNum( caseAvant.getCoordonnee().getX(), plateau.getNombreCaseY()-1) );
				res = true;
			}
			break;
		case "down":
			if (caseAvant.getCoordonnee().getY() == plateau.getNombreCaseY()-1 ) {
				this.setNumeroCase( Tool.CoordinateToNum( caseAvant.getCoordonnee().getX(), 0) );

				res = true;
			}
			break;
		case "left":
			if (caseAvant.getCoordonnee().getX() == 0) {
				this.setNumeroCase( Tool.CoordinateToNum(plateau.getNombreCaseX()-1, caseAvant.getCoordonnee().getY() ) );
				res = true;
			}
			break;
		case "right":
			if (caseAvant.getCoordonnee().getX() == plateau.getNombreCaseX()-1) {
				this.setNumeroCase( Tool.CoordinateToNum(0, caseAvant.getCoordonnee().getY() ) );

				res = true;
			}
			break;

		default:
			break;
		}

		return res;
	}

	// ######################### RAMASSER ###################################
	private void Ramasser(Case c) {
		Item item = c.getItem();

		switch (item.getNom()) {
		case "Heart":
			item.etreRamasser(c);
			this.soigner(1);
			break;
		case "Rubi":
			nombreRubi++;
			item.etreRamasser(c);
			break;
		case "Key":
			item.etreRamasser(c);
			break;	
		default:
			break;
		}
	}

	// ######################### ATTAQUE #########################################

	@Override
	public void attaquer(Plateau plateau, Case c) {

		switch (this.getDirection().getDirection()) {

		case "up":
			Case caseUp = plateau.getCaseUpMemeZ(c.getCoordonnee());
			Case caseUpLeft = plateau.getCaseUpLeftMemeZ(c.getCoordonnee());
			Case caseUpRight = plateau.getCaseUpRightMemeZ(c.getCoordonnee());

			caseUp.getElement().perdreVie(this.getDamage(), plateau);
			caseUpLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseUpRight.getElement().perdreVie(this.getDamage(), plateau);

//			System.out.println(caseUpLeft+ ","+caseUp +","+caseUpRight);
			break;
		case "down":
			Case caseDownLeft = plateau.getCaseDownLeftMemeZ(c.getCoordonnee());
			Case caseDown = plateau.getCaseDown(c.getCoordonnee());
			Case caseDownRight = plateau.getCaseDownRightMemeZ(c.getCoordonnee());

			caseDown.getElement().perdreVie(this.getDamage(), plateau);
			caseDownLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseDownRight.getElement().perdreVie(this.getDamage(), plateau);

			// System.out.println(caseDownLeft+ ","+caseDown +","+caseDownRight);

			break;
		case "left":
			Case caseLeft = plateau.getCaseLeftMemeZ(c.getCoordonnee());
			caseUpLeft = plateau.getCaseUpLeftMemeZ(c.getCoordonnee());
			caseDownLeft = plateau.getCaseDownLeftMemeZ(c.getCoordonnee());

			caseLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseUpLeft.getElement().perdreVie(this.getDamage(), plateau);
			caseDownLeft.getElement().perdreVie(this.getDamage(), plateau);

			// System.out.println(caseUpLeft+ ","+caseLeft +","+caseDownLeft);

			break;
		case "right":
			Case caseRight = plateau.getCaseRightMemeZ(c.getCoordonnee());
			caseUpRight = plateau.getCaseUpRightMemeZ(c.getCoordonnee());
			caseDownRight = plateau.getCaseDownRightMemeZ(c.getCoordonnee());

			caseRight.getElement().perdreVie(this.getDamage(), plateau);
			caseDownRight.getElement().perdreVie(this.getDamage(), plateau);
			caseUpRight.getElement().perdreVie(this.getDamage(), plateau);

			// System.out.println(caseUpRight+ ","+caseRight +","+caseDownRight);

			break;
		default:
			break;

		}

	}
	// ######################### Tirer
		// #########################################

		@Override
		public void tirer(Plateau plateau, Case c) {
			Fleche fleche = new Fleche(this.getDirection());
			Control.tirer(fleche, c);
		}

	// ######################### PERDRE VIE
	// #########################################

	@Override
	public void perdreVie(Damage damage, Plateau p) {

		if (damage.doDamage(this)) {
			this.setLife(this.getLife() - damage.getExplosion() - damage.getEpee() - damage.getMagie()
					- damage.getProjectil());
			this.setCurentAction("hurt");
			this.setFrame(0);
		}
		// System.out.println("life : "+this.getLife());
		if (getLife() <= 0) {
			this.mourir();
		}
	}

	// ######################### SOIGNER #########################################

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
	public String trouverImage(Plateau plateau, Case c) {
		String icon = "hyrule/link/beat/Down1.png";
		//System.out.println(this.getCurentAction());
		// System.out.println(this.getFrame());

		switch (this.getCurentAction()) {

		case "nothing":
			icon = this.imageNothing();
			this.setFrame((getFrame() + 1) % 16);
			break;
		case "nothingHolding":
			icon = this.imageNothingHolding();
			this.setFrame((getFrame() + 1) % 1);
			break;
		case "moving":
			icon = this.imageMove();
			this.setFrame((getFrame() + 1) % 3);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;
		case "movingHolding":
			icon = this.imageMoveHolding();
			this.setFrame((getFrame() + 1) % 3);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothingHolding");
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
			this.setFrame((getFrame() + 1) % 7);

			if (this.getFrame() == 2) {
				this.attaquer(plateau, c);
			}
			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}

			break;
		case "animationTirer":
			icon = this.imageBow();
			this.setFrame((getFrame() + 1) % 9);

			if (this.getFrame() == 5) {
				this.tirer(plateau, c);
			}

			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}

			break;
		case "animationLift":
			icon = this.imagelift();
			this.setFrame((getFrame() + 1) % 5);

			if (this.getFrame() == 0) {
				this.setCurentAction("nothingHolding");			
				}
			break;
		case "animationThrow":
			icon = this.imageThrow();
			this.setFrame((getFrame() + 1) % 5);

			if (this.getFrame() == 3) {
				this.throwObject(plateau, c);
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

	private String imageThrow() {
		int num = this.getFrame() + 1;
		String icon = "hyrule/"+perso+"/throw/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon;
	}

	private String imageMoveHolding() {
		int num = this.getFrame() + 1;
		String icon = "hyrule/"+perso+"/moveHolding/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon;
	}

	private String imageNothingHolding() {
		int num = this.getFrame() + 1;
		String icon = "hyrule/"+perso+"/nothingHolding/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon;
	}

	private String imagelift() {
		int num = this.getFrame() + 1;
		String icon = "hyrule/"+perso+"/lift/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon;
	}

	private String imageHurt() {
		int num = this.getFrame() / 2 + 1;
		String icon = "hyrule/"+perso+"/hurt/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon;
	}

	private String imageBow() {
		int num = this.getFrame() + 1;
		String icon = "hyrule/"+perso+"/bow/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon;
	}

	private String imageAttaque() {
		int num = this.getFrame() + 1;
		String icon = "hyrule/"+perso+"/attaque/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon ;
	}

	private String imageMove() {
		int num = this.getFrame() + 1;
		String icon = "hyrule/"+perso+"/move/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		return icon;
	}

	private String imageNothing() {
		int num = this.getFrame()/2 + 1;
		String icon = "hyrule/"+perso+"/beat/"+this.getDirection().getDirection().substring(0,1).toUpperCase()+""+num+".png";
		// System.out.println("frame: "+this.getFrame());
		//System.out.println(icon);
		return icon;

	}

	@Override
	public int trouverX(Case c) {
		int res = c.getCoordonnee().getX() * c.getTailleCasePixel() - c.getTailleCasePixel();
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res + num * c.getTailleCasePixel() / 3 - c.getTailleCasePixel();
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res - num * c.getTailleCasePixel() / 3 + c.getTailleCasePixel();
		}

		return res;
	}

	@Override
	public int trouverY(Case c) {
		int res = c.getCoordonnee().getY() * c.getTailleCasePixel() - c.getTailleCasePixel()
				- c.getCoordonnee().getZ()*c.getTailleCasePixel()/2;
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res - num * c.getTailleCasePixel() / 3 + c.getTailleCasePixel();
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num = this.getFrame() + 1;
			res = res + num * c.getTailleCasePixel() / 3 - c.getTailleCasePixel();
		}

		return res;
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 3 * c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 3 * c.getTailleCasePixel();
	}

	// ######################### INFORMATION
	// #########################################

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println(this.getNom() + "," + this.getLife() + "," + this.isExist() + "," + this.getDamage());
	}

	

	// ######################### GETTER SETTER
	// #########################################
	public int getNombreRubi() {
		return nombreRubi;
	}

	public void setNombreRubi(int nombreRubi) {
		this.nombreRubi = nombreRubi;
	}
	
	
	
	
	
}
