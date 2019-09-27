package controleur;

import model.Hero;
import model.Minotaure;

public class ContolKnight {
	
	
	private Hero hero ;
	private Minotaure minotaure ;

	public ContolKnight(Hero hero , Minotaure minautore) {
		super();
		this.hero = hero;
		this.minotaure = minautore;
	}
	
	
	public String deplacementOptimal(){
		String res = "up";
		String UporDown = "up" ;
		String RightorLeft = "left";
		int chance = 50 ; 
		int diffY = hero.getCoordonnee().getY() - minotaure.getCoordonnee().getY();
		int diffX = hero.getCoordonnee().getX() - minotaure.getCoordonnee().getX() ;
		int diffXY = Math.abs(diffX) - Math.abs(diffY);
		
		if (diffY>0) {
			UporDown = "down"  ;
		}
		if (diffX>0) {
			RightorLeft = "right";
		}
		
		int random = (int) (Math.random() * (100 - 1 +1 )) + 1;

		chance = chance + diffXY * 3 ;
		if (random > chance) {
			res = UporDown;
		}else {
			res = RightorLeft ;
		}
		//System.out.println("XY: " + diffXY +",chance Y :"+chance+","+  res);
		
		return res ;
	}

}
