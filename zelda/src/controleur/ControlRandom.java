package controleur;

public class ControlRandom {

	
	public ControlRandom() {
		super();
	}

	public String  deplacement () {
		int random = (int) (Math.random() * (3 - 0 +1 )) + 0;
		switch (random) {
		case 0:
			return  "up";
		case 1:
			return "down" ;
		case 2:
			return "left" ;
		case 3:
			return "right" ;
		default:
			return "up" ;
		}
		
	}
	
}
