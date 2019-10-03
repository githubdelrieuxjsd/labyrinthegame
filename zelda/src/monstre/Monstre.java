package monstre;

import java.util.List;

import controleur.Control;
import model.Case;
import model.Hero;
import model.Plateau;
import model.Unite;
import tool.Tool;

public abstract class Monstre extends Unite {

	private static Hero hero ;
	
	
	public abstract void action (Plateau plateau) ;

	public void mourir () {
		Control.removeMonstre(this);
	}

	// affichage in game life bar
	public abstract int trouverBarreVieX(Case c) ;
	public abstract int trouverBarreVieY(Case c) ;


	
	
	//###########################Affichage des informations ##############################
	
	@Override
	public void afficher () {
		System.out.println("#####  "+this.getNom()+"  ######");
		System.out.println("Case: " +this.getNumeroCase());

		System.out.println("Life : " +this.getLife());
		System.out.println("Damage : " +this.getDamage());
		System.out.println("Frame : " +this.getFrame());
		System.out.println("Action : " +this.getCurentAction());
		System.out.println("		###########");
		
		}
	

	//######################## GETTER SETTER ##########################################

	
	public static Hero getHero() {
		return hero;
	}

	public static void setHero(Hero hero) {
		Monstre.hero = hero;
	}






	
	
}
