package model;

import mobInterface.Dessin;
import mobInterface.PerdreVie;

public abstract class Element implements PerdreVie, Dessin {

	private String nom;
	private Direction direction;
	private String curentAction;
	private int numeroCase;

	private boolean spawnable ;
	
	

	private int frame;

	public boolean isMonstre() {
		boolean res = false;
		switch (this.getNom()) {
		case "Knight":
			res = true;
			break;
		case "Chicken":
			res = true;
			break;
		case "Goblin":
			res = true;
			break;
		case "Tomato":
			res = true;
			break;
		default:
			break;
		}
		return res;
	}

	public boolean isHero() {
		return this.getNom().equals("Hero");
	}

	public int[] getCoordonneeVisuel(int x, int y, Case c, Hero hero) {
		// System.out.println (hero.getFrame());
		int[] res = { x * c.getTailleCasePixel() - c.getTailleCasePixel(),
				y * c.getTailleCasePixel() - c.getTailleCasePixel() };

		
		if (this.isMonstre() && this.curentAction.equals("moving")){
			switch (this.getDirection().getDirection()) {
			case "up":
				res[1] = res[1] - (this.getFrame()+1) * c.getTailleCasePixel() / 6 +c.getTailleCasePixel() ;
				break;
			case "down":
				
				res[1] = res[1] + (this.getFrame()+1) * c.getTailleCasePixel() / 6  -c.getTailleCasePixel();
			
				break;
			case "left":
				int coefDeplacement = - (this.getFrame() -3) * (this.getFrame() -3) +3*3 ;
				res[0] = res[0] - (this.getFrame()+1) * c.getTailleCasePixel() / 6  +c.getTailleCasePixel();
				res[1] = res [1] - coefDeplacement;
				break;
			case "right":
				coefDeplacement = - (this.getFrame()-3 ) * (this.getFrame() -3) + 3*3  ;
				res[0] = res[0] + (this.getFrame()+1) * c.getTailleCasePixel() / 6  -c.getTailleCasePixel();
				res[1] = res [1] - coefDeplacement;
				break;
			default:
				break;
			}
		}
		
		if (hero.getCurentAction().equals("moving") || hero.getCurentAction().equals("movingHolding")) {

			switch (hero.getDirection().getDirection()) {
			case "up":
				res[1] = res[1] + (hero.getFrame()+1) * c.getTailleCasePixel() / 3 -c.getTailleCasePixel();
				if (y>4 || y==4 && x>9 ) {
					res [1] = res [1] -c.getTailleCasePixel()/3;
					
				}
				break;
			case "down":
				res[1] = res[1] - (hero.getFrame()+1) * c.getTailleCasePixel() / 3 +c.getTailleCasePixel();
				if (y>4 || y==4 && x>9 ) {
					res [1] = res [1] +c.getTailleCasePixel()/3;
					
				}
				break;
			case "left":
				res[0] = res[0] + (hero.getFrame()+1) * c.getTailleCasePixel() / 3 -c.getTailleCasePixel();
				if (y>4 || y==4 && x>9 ) {
					res [0] = res [0] -c.getTailleCasePixel()/3;
					
				}
				break;
			case "right":
				res[0] = res[0] - (hero.getFrame()+1) * c.getTailleCasePixel() / 3 + c.getTailleCasePixel();
				if (y>4 || y==4 && x>9 ) {
					res [0] = res [0] +c.getTailleCasePixel()/3;
					
				}
				break;
			default:
				break;
			}
		}
	
		
		return res;
	}
	
	// ######################## TO STRING ##########################################

	public void afficher() {

		System.out.println("######" + nom + " ######");
		System.out.println("numero Case " + this.getNumeroCase());
		System.out.println("Curent action  " + this.getCurentAction());

		System.out.println("Frame " + this.getFrame());

		System.out.println("##########################");

	}

	@Override
	public String toString() {
		return nom;
	}

	// ######################## GETTER SETTER
	// ##########################################

	public String getCurentAction() {
		return curentAction;
	}

	public void setCurentAction(String curentAction) {
		this.curentAction = curentAction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getNumeroCase() {
		return numeroCase;
	}

	public void setNumeroCase(int numeroCase) {
		this.numeroCase = numeroCase;
	}


	
	
	public boolean isSpawnable() {
		return spawnable;
	}

	public void setSpawnable(boolean spawnable) {
		this.spawnable = spawnable;
	}
	
	

}
