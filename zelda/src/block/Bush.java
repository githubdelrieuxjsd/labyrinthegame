package block;

import damage.Damage;
import item.Heart;
import item.Key;
import item.Rubi;
import mobInterface.DropItem;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;

public class Bush extends Block implements DropItem {

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
			dropItem(plateau.getListCase().get(this.getNumeroCase()));
			this.detruir();
		}
	}
	
	// ######################### DROP ITEM #########################################

		@Override
		public void dropItem(Case c) {
			if (c.getItem().getNom().equals("Rien")) {
				c.setItem(new Key());
				/**
				 * 
				int x = (int) (Math.random() * (100 + 1 - 1)) + 1;
				if (x > 30) {
					c.setItem(new Rubi());
				} else if (x < 10) {
					c.setItem(new Heart());
				}
				*/
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
		return c.getCoordonnee().getX()*c.getTailleCasePixel()+15-2*c.getTailleCasePixel();
	}

	@Override
	public int trouverY(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY()*c.getTailleCasePixel() -15 -c.getTailleCasePixel();
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
