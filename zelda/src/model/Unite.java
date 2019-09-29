package model;

import java.util.ArrayList;
import java.util.List;

import tool.Tool;

public abstract class Unite extends Element{

	private boolean exist ;
	private int life;
	private Damage damage;
	


	public abstract void deplacer(Direction direction, Plateau plateau);
	
	public abstract void soigner(int soin );

	public abstract void attaquer(Plateau plateau) ;
	
	public abstract void afficher();

	
	public void mourir(Plateau p , int x,int y , boolean respawn) {
		this.exist = false ;
		
		int num = Tool.CoordinateToNum(this.getCoordonnee());
		Coordonnee cord = new Coordonnee(this.getCoordonnee());
		p.getListCase().get(num).setElement(new Vide (cord));
		
		if (respawn) {
			
			num = Tool.CoordinateToNum(x ,y);
			this.soigner (1000000);
			this.setCoordonnee(new Coordonnee (x,y) );
			p.getListCase().get(num).setElement(this);
			this.exist = true;
			
		}
		
	}

	public boolean isAlive() {
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

	
	
	public Damage getDamage() {
		return damage;
	}

	public void setDamageHero(int epee ,int bomb ,int fleche) {
		this.damage = new DamageHero (epee , bomb , fleche);
	}
	public void setDamageMonstre(int epee,int magie ,int bomb ,int fleche) {
		this.damage = new DamageMonstre (epee , magie , bomb , fleche);
	}

	public boolean isExist() {
		return exist;
	}

	
	
}
