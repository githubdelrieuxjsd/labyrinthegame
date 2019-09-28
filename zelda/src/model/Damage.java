package model;

public class Damage {

	
	private int epee ;
	private int magie ; 
	private int bomb ;
	private int fleche ;
	
	public Damage(int epee, int magie, int bomb, int fleche) {
		super();
		this.epee = epee;
		this.magie = magie;
		this.bomb = bomb;
		this.fleche = fleche;
	}

	public int getEpee() {
		return epee;
	}

	public void setEpee(int epee) {
		this.epee = epee;
	}

	public int getMagie() {
		return magie;
	}

	public void setMagie(int magie) {
		this.magie = magie;
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}

	public int getFleche() {
		return fleche;
	}

	public void setFleche(int fleche) {
		this.fleche = fleche;
	}
	
	
	
	
}
