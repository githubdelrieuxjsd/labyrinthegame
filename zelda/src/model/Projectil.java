package model;

public abstract class Projectil extends Element {
	
	private Boolean exist ;
	private Damage damage ;
	


	public Boolean getExist() {
		return exist;
	}



	public void setExist(Boolean exist) {
		this.exist = exist;
	}

	public abstract void deplacer(Plateau plateau);



	public Damage getDamage() {
		return damage;
	}



	public void setDamageHero(int epee,int magie ,int bomb ,int fleche) {
		this.damage = new DamageHero (epee ,magie, bomb , fleche);
	}

	
	
}
