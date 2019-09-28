package model;

public abstract class Monstre extends Unite {

	private int MaxLife ;
	
	
	
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
