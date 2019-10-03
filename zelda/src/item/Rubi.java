package item;

import model.Case;
import model.Plateau;

public class Rubi extends Item{

	public Rubi() {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setNom("Rubi");
		
	}

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "hyrule/item/rubi.png";
	}

	@Override
	public void etreRamasser( Case c) {
		c.setItem();
	}

	@Override
	public int trouverX( Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX() *c.getTailleCasePixel()  ;
	}

	@Override
	public int trouverY( Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY() *c.getTailleCasePixel() -15 ;
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel();
	}

	

}
