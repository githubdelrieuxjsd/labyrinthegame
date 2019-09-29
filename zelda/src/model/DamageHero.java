package model;

public class DamageHero extends Damage {
	
	public DamageHero (int epee, int bomb, int projectil) {
		this.setEpee(epee);
		this.setExplosion(bomb);
		this.setProjectil(projectil);
		this.setMagie(0);
	}
	
	public DamageHero (int epee,int magie, int bomb, int projectil) {
		this.setEpee(epee);
		this.setExplosion(bomb);
		this.setProjectil(projectil);
		this.setMagie(magie);
	}
	
	public DamageHero (int epee ) {
		this.setEpee(epee);
		this.setExplosion(0);
		this.setProjectil(0);
		this.setMagie(0);
	}

	
	public boolean doDamage(Element element) {
		if ( element.isHero() ) {
			return false ;
		}
		return true;
	}
	
}
