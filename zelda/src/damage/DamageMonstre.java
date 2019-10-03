package damage;

import model.Element;

public class DamageMonstre extends Damage{

	public DamageMonstre (int epee,int magie , int bomb  ,int projectil) {
		this.setEpee(epee);
		this.setExplosion(bomb);
		this.setProjectil(projectil);
		this.setMagie(magie);
	}
	
	@Override
	public boolean doDamage(Element element) {
		if (element.isHero() ) {
			return true ;
		}
		return false;
	}
	
}
