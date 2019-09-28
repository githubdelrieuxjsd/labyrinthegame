package model;

public abstract class Projectil extends Element {
	
	private Boolean exist ;
	private int damage ;
	


	public Boolean getExist() {
		return exist;
	}



	public void setExist(Boolean exist) {
		this.exist = exist;
	}

	public abstract void deplacer(Plateau plateau);



	public int getDamage() {
		return damage;
	}



	public void setDamage(int damage) {
		this.damage = damage;
	}

	
	
}
