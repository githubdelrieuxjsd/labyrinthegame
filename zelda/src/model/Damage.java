package model;

public abstract class Damage {

	
	private int epee ;
	private int magie ; 
	private int explosion ;
	private int projectil ;
	
	
	public abstract boolean doDamage(Element element );
	
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

	public int getExplosion() {
		return explosion;
	}

	public void setExplosion(int explosion) {
		this.explosion = explosion;
	}

	public int getProjectil() {
		return projectil;
	}

	public void setProjectil(int projectil) {
		this.projectil = projectil;
	}

	
	
	
	
	
}
