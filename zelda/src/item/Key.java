package item;

import model.Case;
import model.Plateau;

public class Key extends Item {

	

	public Key() {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setNom("Key");
	}

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "hyrule/item/key.png";
	}

	@Override
	public void etreRamasser( Case c) {
		c.setItem(new Rien());
	}

	@Override
	public int trouverX(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX() *c.getTailleCasePixel()+  c.getTailleCasePixel()*10/15/4;
	}

	@Override
	public int trouverY( Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY() *c.getTailleCasePixel();
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel()*10/15;
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel()*10/15;
	}

	
	
	
}
