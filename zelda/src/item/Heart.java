package item;

import model.Case;
import model.Plateau;

public class Heart extends Item{

	
	public Heart() {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setNom("Heart");
		
	}

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "hyrule/heart/rouge.png";
	}

	@Override
	public void etreRamasser( Case c) {
		c.setItem();
	}

	@Override
	public int trouverX( Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX()*c.getTailleCasePixel()+15- c.getTailleCasePixel() ;
	}

	@Override
	public int trouverY( Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY()*c.getTailleCasePixel() -15 ;
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel()/2;
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel()/2;
	}

	
	
	
}
