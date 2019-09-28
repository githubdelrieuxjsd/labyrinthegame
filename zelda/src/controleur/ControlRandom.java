package controleur;

import model.Direction;

public class ControlRandom {

	
	public ControlRandom() {
		super();
	}

	public Direction  deplacement () {
		int random = (int) (Math.random() * (3 - 0 +1 )) + 0;
		switch (random) {
		case 0:
			return  new Direction ("up" );
		case 1:
			return  new Direction  ("down" );
		case 2:
			return  new Direction ( "left" );
		case 3:
			return  new Direction ( "right") ;
		default:
			return  new Direction  ("up") ;
		}
		
	}
	
}
