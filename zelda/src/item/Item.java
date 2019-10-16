package item;

import mobInterface.Dessin;
import model.Case;
import model.Hero;
import model.Plateau;

public abstract class Item implements Dessin{

	private String nom;
	private int frame ;
	private String curentAction;
	private int numeroCase ;
	
	
	
	public abstract void etreRamasser(Case c) ;
	
	public int[] getCoordonneeVisuel(int x, int y, Case c, Hero hero) {
		// System.out.println (hero.getFrame());
		int[] res = { x * c.getTailleCasePixel() - c.getTailleCasePixel(),
				y * c.getTailleCasePixel() - c.getTailleCasePixel() };
		
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
	
	
	
	
	@Override
	public String toString() {
		return  nom ;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	

	public String getCurentAction() {
		return curentAction;
	}

	public void setCurentAction(String curentAction) {
		this.curentAction = curentAction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setNumeroCase(int numeroCase) {
		this.numeroCase = numeroCase;
	}


	
	
	
	
}
