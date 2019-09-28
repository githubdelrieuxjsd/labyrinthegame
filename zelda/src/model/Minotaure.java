package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import tool.Tool;

public class Minotaure extends Monstre{

	private List<Projectil> listProjectil;
	private int maxLife = 5 ;
	
	public Minotaure(Coordonnee coordonnee) {
		super();
		this.setExist(true);

		// TODO Auto-generated constructor stu
		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Minotaure");
		this.setLife( 5);
		this.setDamage( 3,0,0,0);
		this.listProjectil = new ArrayList<Projectil>();
	}

	
	public Minotaure(int x, int y) {
		super();
		this.setExist(true);

		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Minotaure");
		this.setLife( 5);
		this.setDamage( 3,0,0,0);
		this.listProjectil = new ArrayList<Projectil>();
	}


	@Override
	public void deplacer(String direction, Plateau plateau) {
		// TODO Auto-generated method stub
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
				((Hero)c.getElement()).perdreVie(this.getDamage(), plateau);
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

			}*/

			public List<Projectil> getListProjectil() {
				return listProjectil;
			}

			public void setListProjectil(List<Projectil> listProjectil) {
				this.listProjectil = listProjectil;
			}

			private void placerProjectil(Plateau plateau, MinotaureProjectil heroProjectil) {
				// TODO Auto-generated method stub
				int num = Tool.CoordinateToNum(heroProjectil.getCoordonnee());
				plateau.getListCase().get(num).setElement(heroProjectil);
				heroProjectil.deplacer(plateau);
				plateau.getListCase().get(num).setElement(this);

			}



	@Override
	public void perdreVie(Damage damage , Plateau p) {
		// TODO Auto-generated method stub
		this.setLife(getLife() -damage.getEpee() - damage.getFleche());
		
		if (getLife() <= 0) {
			this.mourir(p, 15, 13, true);
		}
		
	}


	@Override
	public void soigner(int soin) {
		// TODO Auto-generated method stub
		if ( soin+getLife() >= maxLife ) {
			this.setLife(this.maxLife);
		}
		else {
			this.setLife(getLife() + soin);
		}
	}


	@Override
	public void attaquer(Plateau p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println(this.getNom() +","+this.getLife()+","+this.getCoordonnee()+","+this.isExist()+","+this.getDamage());
	}


	@Override
	public String getImage(Plateau plateau , Case c) {
		
		String icon = "hyrule/knight/Down1.png";

		if (getDirection().equals("up")) {
			icon = "hyrule/knight/Up1.png";
		} else if (getDirection().equals("down")) {
			icon = "hyrule/knight/Down1.png";
		} else if (getDirection().equals("left")) {
			icon = "hyrule/knight/Left1.png";
		} else if (getDirection().equals("right")) {
			icon = "hyrule/knight/Right1.png";
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
