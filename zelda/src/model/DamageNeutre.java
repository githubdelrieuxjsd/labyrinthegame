package model;

public class DamageNeutre extends Damage {

	
	public DamageNeutre (int epee,int magie , int bomb  ,int projectil) {
		this.setEpee(epee);
		this.setExplosion(bomb);
		this.setProjectil(projectil);
		this.setMagie(magie);
	}
	
	
	@Override
	public boolean doDamage(Element element) {
		// TODO Auto-generated method stub
		return true;
	}

}
