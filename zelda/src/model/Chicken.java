package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import controleur.Control;
import tool.Tool;

public class Chicken extends Monstre {

	
	public Chicken(Coordonnee coordonnee ) {
		super();
		this.setExist(true);

		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection( new Direction ("down" ) );
		this.setNom("Chicken");
		
		this.setDamageMonstre(0,0,0,0);
	}

	public Chicken(int x, int y) {
		super();
		this.setExist(true);
		this.setNom("Chicken");

		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setMaxLife(2);
		this.setLife( this.getMaxLife());	
		this.setDamageMonstre(0,0,0,0);
		this.setDirection ( new Direction ("down" ) ) ;
		

	}

	@Override
	public void deplacer(Direction direction, Plateau plateau) {
		// TODO Auto-generated method stub
		if (this.isAlive()&& this.getCurentAction().equals("nothing")) {
			
			this.setDirection(direction);
			switch (direction.getDirection()) {

			case "up":
				this.setDirection(new Direction ("up" ));

				Case caseUp = plateau.getCaseUp(this.getCoordonnee());
				interactionDeplacement(plateau, caseUp );
				break;
			case "down":
				this.setDirection(new Direction ("down" ));

				Case caseDown = plateau.getCaseDown(this.getCoordonnee());
				interactionDeplacement(plateau, caseDown );
				break;
			case "left":
				this.setDirection(new Direction ("left" ));

				Case caseLeft = plateau.getCaseLeft(this.getCoordonnee());
				interactionDeplacement(plateau, caseLeft );

				break;
			case "right":
				this.setDirection( new Direction ("right" ) );

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
		
		this.setCurentAction("moving");
		
		this.transformation(caseApres);

	}

	private void transformation(Case c) {
		Item item = c.getItem() ; 
		if (item.getNom().equals("Key")) {
			item.etreRamasser(c);
			this.setCurentAction("transformation");
			this.setFrame(0);
		}
	}

	@Override
	public void perdreVie(Damage damage, Plateau p) {
		
			if ( damage.doDamage(this)) {
			this.setLife(this.getLife() - damage.getEpee() - damage.getProjectil() - damage.getExplosion() );
			}
			if (getLife() <= 0) {
				this.setCurentAction("death");
				this.setFrame(0);
			}
	}

	@Override
	public void soigner(int soin) {
		// TODO Auto-generated method stub
		if (soin + getLife() >= getMaxLife()) {
			setLife(getMaxLife());
		} else {
			this.setLife( getLife() + soin );
		}
	}

	private void dropItem(Case c) {
		if (c.getItem().getNom().equals("Rien")) {
			int x = (int) (Math.random() * (100 + 1 - 1)) + 1;
			if (x<50) {
				c.setItem(new Rubi());
			}else {
				c.setItem(new Heart());
			}

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

			if (this.getFrame() == 4) {
				this.dropItem( c );

			}
			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
				int x = (int) (Math.random() * (23 + 1 - 1)) + 1;
				int y = (int) (Math.random() * (16 + 1 - 1)) + 1;
				this.mourir(p, x, y, false);
				
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
			 num =  this.getFrame() /2 +1;
			//System.out.println(num);
			icon = "hyrule/death/"+num+".png";
			this.setFrame((getFrame() + 1) % 14);
			break;

		}
				
		return icon;
	}

	

	
	private String imageNothing() {
		int num =    this.getFrame() /2 +1;
		//System.out.println("frame: "+this.getFrame());

		//System.out.println("num: "+num);
		
		String icon = "hyrule/chicken/R"+num+".png" ;
		switch (this.getDirection().getDirection()) {

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
		int res = this.getCoordonnee().getX() * 40 -40;
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()    +1;
			res = res + num * 5 - 40 ;
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame() +1;
			res = res - num * 5 +40 ;
		}
		return res;
	}

	@Override
	protected int trouverY() {
		int res = this.getCoordonnee().getY() * 40 -40;
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()  /2 +1;
			res = res - num * 10 +40 ;
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()  /2  +1;
			res = res + num * 10 - 40;
		} else if (this.getCurentAction().equals("moving")) {
			int num =  - ( this.getFrame() -4 )* ( this.getFrame() -4 ) + 10 ;
			res = res - num  ;
			//System.out.println(this.getFrame() +","+ (num) );
		}
		return res;
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

	@Override
	public void animationAttaque() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int trouverBarreVieX() {
		// TODO Auto-generated method stub
		return this.trouverX()+45;
	}


	@Override
	public int trouverBarreVieY() {
		// TODO Auto-generated method stub
		return this.trouverY() +30;
	}

}
