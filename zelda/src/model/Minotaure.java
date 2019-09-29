package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import controleur.ControlKnight;
import tool.Tool;

public class Minotaure extends Monstre{

	private int epee = 2 ;
	
	private List<Projectil> listProjectil;
	private ControlKnight ctr ;
	
	public Minotaure(Coordonnee coordonnee ,Hero hero) {
		super();
		this.setExist(true);
		this.setMaxLife(5);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
		this.setNom("Minotaure");
		this.setLife( this.getMaxLife());
		this.setDamageMonstre( epee ,0,0,0);
		this.listProjectil = new ArrayList<Projectil>();
		Monstre.setHero(hero);
		this.ctr = new ControlKnight (this);
	}

	
	public Minotaure(int x, int y ,Hero hero) {
		super();
		this.setExist(true);
		this.setMaxLife(5);
		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
		this.setNom("Minotaure");
		this.setLife( this.getMaxLife());
		this.setDamageMonstre( epee ,0,0,0);
		this.listProjectil = new ArrayList<Projectil>();
		Monstre.setHero(hero);
		this.ctr = new ControlKnight (this);

	}
	
	public Minotaure(int x, int y ) {
		super();
		this.setExist(true);
		this.setMaxLife(5);
		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
		this.setNom("Minotaure");
		this.setLife( this.getMaxLife());
		this.setDamageMonstre( epee ,0,0,0);
		this.listProjectil = new ArrayList<Projectil>();
		Monstre.setHero(Monstre.getHero());
		this.ctr = new ControlKnight (this);

	}
	
	public Minotaure(Coordonnee coordonnee) {
		super();
		this.setExist(true);
		this.setMaxLife(5);
		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
		this.setNom("Minotaure");
		this.setLife( this.getMaxLife());
		this.setDamageMonstre( epee ,0,0,0);
		this.listProjectil = new ArrayList<Projectil>();
		Monstre.setHero(Monstre.getHero());
		this.ctr = new ControlKnight (this);
	}
	
	
	public void action(Plateau plateau) {
		this.ctr.action(plateau);		
	}
	


	@Override
	public void deplacer(Direction direction, Plateau plateau) {
		// TODO Auto-generated method stub
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
					this.setDirection( new Direction ("left" ));

					Case caseLeft = plateau.getCaseLeft(this.getCoordonnee());
					interactionDeplacement(plateau, caseLeft );

					break;
				case "right":
					this.setDirection(new Direction ("right" ));

					Case caseRight = plateau.getCaseRight(this.getCoordonnee());
					interactionDeplacement(plateau, caseRight );

					break;
				default:
					break;

				}
				
				

			}

			private void interactionDeplacement(Plateau plateau, Case c) {
				// TODO Auto-generated method stub
				switch (c.getElement().getNom()) {

				case "Vide":
					interactionVide(plateau, c);
					//this.tirer( plateau);
					break;
				case "Arbre":
					break;
				case "Rock": interactionRock(plateau, c) ;
					break;
				case "Hero": interactionHero(plateau, c);
					break;
				
				default:
					//System.out.println("Minotaure est rentrer dans " + c.getElement().getNom());
					break;
				}
			}

			private void interactionRock(Plateau plateau, Case c) {
				// TODO Auto-generated method stub
				((Rock) c.getElement()).deplacer(getDirection(), plateau , true);
				
				if (c.getElement().getNom().equals("Vide")) {
					this.deplacer(getDirection(), plateau);
				}
			}
			
			private void interactionHero(Plateau plateau, Case c) {
				// TODO Auto-generated method stub
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
				this.setCoordonnee( cordApres );
				
				this.setCurentAction("moving");
				this.setFrame(0);

			}

			/**
			public void tirer( Plateau plateau) {
				// TODO Auto-generated method stub
				if (this.isAlive()) {
					Coordonnee cord1 = new Coordonnee(this.getCoordonnee());
					MinotaureProjectil MinotaureProjectil1 = new MinotaureProjectil(cord1, getDirection(), getDamage());
					


					
					if ( this.listProjectil.size() == 2) {
						
						Coordonnee corddetruit = new Coordonnee(listProjectil.get(0).getCoordonnee());
 
						int num = Tool.CoordinateToNum(corddetruit);
						Vide v = new Vide(corddetruit);
						plateau.getListCase().get(num).setElement(v);
						
						listProjectil.get(0).setExist(false);
						
						Coordonnee cord0 = 	new Coordonnee (this.listProjectil.get(1).getCoordonnee() );
						String direction0 = this.listProjectil.get(1).getDirection() ;
						boolean exist = this.listProjectil.get(1).getExist() ;
						MinotaureProjectil MinotaureProjectil0 = new MinotaureProjectil(cord0, direction0, getDamage());
						MinotaureProjectil0.setExist(exist);
						this.listProjectil.set(0, MinotaureProjectil0);
						
						listProjectil.remove(1);
						
						
					}
					
					listProjectil.add(MinotaureProjectil1);

					placerProjectil(plateau, MinotaureProjectil1);
					

				}

			}
			private void placerProjectil(Plateau plateau, MinotaureProjectil heroProjectil) {
				// TODO Auto-generated method stub
				int num = Tool.CoordinateToNum(heroProjectil.getCoordonnee());
				plateau.getListCase().get(num).setElement(heroProjectil);
				heroProjectil.deplacer(plateau);
				plateau.getListCase().get(num).setElement(this);

			}
			*
			*/

			public List<Projectil> getListProjectil() {
				return listProjectil;
			}

			public void setListProjectil(List<Projectil> listProjectil) {
				this.listProjectil = listProjectil;
			}

			



	@Override
	public void perdreVie(Damage damage , Plateau p) {
		if (damage.doDamage(this)) {
			this.setLife(getLife() -damage.getEpee() - damage.getProjectil());
		}
		
		if (getLife() <= 0) {
			this.setCurentAction("death");
		}
		
	}


	@Override
	public void soigner(int soin) {
		// TODO Auto-generated method stub
		if ( soin+getLife() >= getMaxLife() ) {
			this.setLife(this.getMaxLife());
		}
		else {
			this.setLife(getLife() + soin);
		}
	}

	@Override
	public void animationAttaque() {
		this.setCurentAction("animationAttaque");
		this.setFrame(0);
	}
	
	@Override
	public void attaquer(Plateau plateau) {
		switch (this.getDirection().getDirection()) {

		case "up":
			plateau.getCaseUp(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpLeft(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpRight(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseLeft(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseRight(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);

//			System.out.println(caseUpLeft+ ","+caseUp +","+caseUpRight);
			break;
		case "down":
			plateau.getCaseDown(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownLeft(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownRight(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseLeft(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseRight(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);

			break;
		case "left":
			plateau.getCaseUp(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDown(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpLeft(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownLeft(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseLeft(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);

			break;
		case "right":
			plateau.getCaseUp(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDown(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseDownRight(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseUpRight(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);
			plateau.getCaseRight(this.getCoordonnee()).getElement().perdreVie(this.getDamage(), plateau);


			
			break;
		default:
			break;

		}		
	}


	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println(this.getNom() +","+this.getLife()+","+this.getCoordonnee()+","+this.isExist()+","+this.getDamage());
	}	
	
	
	@Override
	public String getImage(Plateau plateau ,Case c) {
		String icon ="hyrule/knight/Down1.png" ;
		switch (this.getCurentAction()) {
		
		case "nothing":
			icon = this.imageNothing();
			this.setFrame( (getFrame() + 1) % 24 );
			break;
			
		case "death":
			int num =  this.getFrame() /2 +1;
			//System.out.println(num);
			icon = "hyrule/death/"+num+".png";
			this.setFrame((getFrame() + 1) % 14);
			if (this.getFrame() == 0) {
				this.setCurentAction("dead");
				int x = (int) (Math.random() * (23 + 1 - 1)) + 1;
				int y = (int) (Math.random() * (16 + 1 - 1)) + 1;
				this.mourir(plateau, x, y, false);
				
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
				this.attaquer(plateau);
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
	protected int trouverX() {
		int res = this.getCoordonnee().getX() * 40 -40;
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()   +1;
			res = res + num * 6 - 40 ;
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame() +1;
			res = res - num * 6 +40 ;
		}
		return res;
	}

	@Override
	protected int trouverY() {
		int res = this.getCoordonnee().getY() * 40 -40;
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()   +1;
			res = res - num * 6 +40 ;
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			int num =    this.getFrame()   +1;
			res = res + num * 6 - 40;
		} else if (this.getCurentAction().equals("moving")) {
			int num =  - ( this.getFrame() -3 )* ( this.getFrame() -3 ) + 10 ;
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
	public int trouverBarreVieX() {
		// TODO Auto-generated method stub
		return this.trouverX()+30;
	}


	@Override
	public int trouverBarreVieY() {
		// TODO Auto-generated method stub
		return this.trouverY();
	}


	public boolean isInRange(Plateau plateau) {
		boolean res = false ;
		if (plateau.getCaseUp(this.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseUpLeft(this.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseUpRight(this.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseLeft(this.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseRight(this.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseDownRight(this.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseDown(this.getCoordonnee()).getElement().isHero()
		|| plateau.getCaseDownRight(this.getCoordonnee()).getElement().isHero()) {
			res = true ;
		}
		
		
		
		return res;
	}


	


	
	

	

}
