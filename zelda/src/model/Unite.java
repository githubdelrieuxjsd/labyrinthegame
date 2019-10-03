package model;

import java.util.ArrayList;
import java.util.List;

import block.Vide;
import damage.Damage;
import damage.DamageHero;
import damage.DamageMonstre;
import tool.Tool;

public abstract class Unite extends Element {

	// ??
	private boolean exist ;
	
	private Damage damage;
	private int MaxLife ;
	private int life;

	
	public abstract void mourir();
	
	
	public abstract void afficher();

	
	//######################## GETTER SETTER ##########################################

	public boolean isAlive() {
		return exist;
	}
	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public int getMaxLife() {
		return MaxLife;
	}

	public void setMaxLife(int maxLife) {
		MaxLife = maxLife;
	}

	public Damage getDamage() {
		return damage;
	}

	public void setDamageHero(int epee ,int bomb ,int fleche) {
		this.damage = new DamageHero (epee , bomb , fleche);
	}
	public void setDamageMonstre(int epee,int magie ,int bomb ,int fleche) {
		this.damage = new DamageMonstre (epee , magie , bomb , fleche);
	}

	

	
	
}
