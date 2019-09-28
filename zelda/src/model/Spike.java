package model;

public class Spike extends Piege{

	private int damage ;
	private boolean visible ;

	public Spike(boolean visible) {
		super();
		this.setNom("Spike");
		this.damage = 1;
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
		case "Chicken": ((Chicken)m).perdreVie(0, plateau);
			break;
		default :;  
		}
		if ( ! this.visible) {
			this.visible = visible ;	
		}
		
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	
}
