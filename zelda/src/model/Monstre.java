package model;

import java.util.List;

import tool.Tool;

public abstract class Monstre extends Unite {

	private int MaxLife ;
	private static Hero hero ;
	
	
	@Override
	public void afficher () {
		System.out.println("#####  "+this.getNom()+"  ######");
		System.out.println("Life : " +this.getLife());
		System.out.println("Damage : " +this.getDamage());
		System.out.println("Action : " +this.getCurentAction());
		System.out.println("Case : " +Tool.CoordinateToNum(this.getCoordonnee() ));
		System.out.println("		###########");
		
		}
	
	public static Hero getHero() {
		return hero;
	}

	public static void setHero(Hero hero) {
		Monstre.hero = hero;
	}

	public int getMaxLife() {
		return MaxLife;
	}

	public void setMaxLife(int maxLife) {
		MaxLife = maxLife;
	}

	public abstract int trouverBarreVieX() ;
	public abstract int trouverBarreVieY() ;

	public abstract void animationAttaque();

	

	
	
}
