package controleur;

import model.Direction;
import model.Hero;
import model.Plateau;
import monstre.Knight;
import monstre.Monstre;

public class ControlKnight {
	
	
	private Knight minotaure ;

	public ControlKnight(Knight minautore) {
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
	
	

}
