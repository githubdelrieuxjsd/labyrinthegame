package idea;

import damage.Damage;
import damage.DamageMonstre;
import model.Hero;
import model.Plateau;
import model.Unite;
import monstre.Chicken;

public class Spike extends Piege{

	private Damage damage ;
	private boolean visible ;

	public Spike(boolean visible) {
		super();
		this.setNom("Spike");
		this.damage = new DamageMonstre (1,0,0,0);
		this.visible = visible ;
	} 
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void attaquer (Unite m, Plateau plateau, boolean visible) {
		switch (m.getNom()) {
		case "Hero": ((Hero)m).perdreVie(damage, plateau);
			this.visible = visible ;
		break;
		case "Chicken": ((Chicken)m).perdreVie(new DamageMonstre(0,0,0,0), plateau);
			break;
		default :;  
		}
		if ( ! this.visible) {
			this.visible = visible ;	
		}
		
	}
	
	

	
}
