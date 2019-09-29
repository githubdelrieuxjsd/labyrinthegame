package controleur;

import model.Direction;
import model.Hero;
import model.Minotaure;
import model.Monstre;
import model.Plateau;

public class ControlKnight {
	
	
	private Minotaure minotaure ;

	public ControlKnight(Minotaure minautore) {
		super();
		this.minotaure = minautore;
	}
	
	
	public void action(Plateau plateau) {
		if (this.minotaure.getCurentAction().equals("nothing")) {
			
			if (this.minotaure.isInRange(plateau) ) {
				int random = (int) (Math.random() * (100 - 1 +1 )) + 1;
				if (random > 20) {
					this.minotaure.animationAttaque();
				}else {
					this.minotaure.deplacer(this.deplacementOptimal() , plateau);
				}	

			}
			else {
				this.minotaure.deplacer(this.deplacementOptimal() , plateau);
			}
			
		}
		
	}
	
	public Direction deplacementOptimal(){
		Direction res = new Direction ("up" );
		String UporDown = "up" ;
		String RightorLeft = "left";
		int chance = 50 ; 
		int diffY = Monstre.getHero().getCoordonnee().getY() - minotaure.getCoordonnee().getY();
		int diffX = Monstre.getHero().getCoordonnee().getX() - minotaure.getCoordonnee().getX() ;
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

}
