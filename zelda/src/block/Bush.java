package block;

import damage.Damage;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;

public class Bush extends Block {

	private int life;

	public Bush() {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing");
		this.setDirection(new Direction("down"));
		this.setNom("Bush");
		this.life = 1;
	}

	// ####################A FAIRE ######################
	

	// #################### PERDRE VIE ######################

	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		if (damage.doDamage(this)) {
			this.life = life - damage.getEpee();
		}
		if (life < 0) {
			this.detruir();
		}
	}

	// #################### IMAGE ######################

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		String icon = "hyrule/block/bush.png";

		if (this.getCurentAction().equals("dead")) {
			icon = "";
		}
		return icon;
	}

	@Override
	public int trouverX(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX() * c.getTailleCasePixel() - c.getTailleCasePixel();
	}

	@Override
	public int trouverY(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY() * c.getTailleCasePixel() - c.getTailleCasePixel();
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	
}
