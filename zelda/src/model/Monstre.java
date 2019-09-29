package model;

import java.util.List;

public abstract class Monstre extends Unite {

	private int MaxLife ;
	private static Hero hero ;
	

	
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
